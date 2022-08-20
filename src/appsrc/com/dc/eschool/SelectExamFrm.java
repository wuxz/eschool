package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.event.*;

import com.dc.eschool.exam.ExamIF;
import com.dc.eschool.exam.Examination;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.DataModel.ExamProjectInfoModle;
import com.dc.eschool.DataModel.ExamContentInfoModel;
import com.borland.jbcl.control.*;


/**
 * Title:        选择考试
 * Description:  老师利用该界面选择考试试题
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

class CustomTableModelListener implements TableModelListener
{
  public CustomTableModelListener()
  {
  }
  public void tableChanged(TableModelEvent e)
  {
  }
}

/**  继承DefaultTableModel,让JTable不能编辑. */
class CustomTableModel extends DefaultTableModel
{
    public CustomTableModel(Object[][] data, Object[] colname)
    {
       super(data, colname);
    }

    public boolean isCellEditable(int x, int y)
    {
       return false;
    }

    public void addTableModelListener(CustomTableModelListener l)
    {
       super.addTableModelListener(l);
    }
}

public class SelectExamFrm extends JDialog
{
  JTable tbExam = null;
  JTable tbContent =null;

  /**   静态常量  */
  private  static  final String      EXAMID   =  "考试ID";
  private  static  final String      CONTENTID=  "试题ID";
  private  static  final Object[]    CONTENTTABLEHEADER={"试题ID","试题名称","答题纸","状态"};
  private  static  final Object[]    EXAMTABLEHEADER={"考试ID","考试名称","时间","说明"};
  private  static  final int         COLUMNCOUNT=4;

  /**   SystemControl 实例 */
  private  SystemControl        systemControl=null;
  /**  考试接口声明   */
  private  ExamIF               IExam=null;
  /**  考试表模型   */
  private  CustomTableModel     examTableModel=null;
  /**  考试内容表模型 */
  private  CustomTableModel     contentTableModel=null;
  /**  考试ID */
  private  String               projectID=null;
  /**  试题ID */
  private  String               contentID=null;
  /**  考试ID数组 */
  private  Vector               vectProjectID=new Vector();
  /**  答题窗口   */
  private  AnswerExamFrm        answerExamFrm=null;
  JPanel buttonPanel = new JPanel();
  JPanel leftButtonGroupPanel = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel rightButtonGroupPanel = new JPanel();
  JButton btnCancel = new JButton();
  JButton btnSelect = new JButton();
  JButton btnView = new JButton();
  JPanel contentPanel = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  GroupBox projectListGroupBox = new GroupBox();
  BorderLayout borderLayout2 = new BorderLayout();
  GroupBox contentListGroupBox = new GroupBox();
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane projectScrollPane = new JScrollPane();
  JScrollPane contentScrollPane = new JScrollPane();
  JPanel topPanel = new JPanel();
  JPanel centerButtonPanel = new JPanel();


  public SelectExamFrm(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public SelectExamFrm()
  {
    this(null, "", false);
  }

  /**   自定义的构造器  */
  public SelectExamFrm(Frame frame,SystemControl systemControl)
  {
    this(frame, "", false);
    this.systemControl=systemControl;
    init();
  }

  /**   界面的初始化  */
  void jbInit() throws Exception
  {
    this.setModal(true);
    this.setTitle("选择考试");



    buttonPanel.setLayout(borderLayout1);
    btnCancel.setText("取消");
    btnCancel.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnCancel_actionPerformed(e);
      }
    });
    btnSelect.setText("备选");
    btnSelect.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnSelect_actionPerformed(e);
      }
    });
    btnView.setText("预览");
    btnView.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnView_actionPerformed(e);
      }
    });
    contentPanel.setLayout(gridLayout1);
    gridLayout1.setRows(2);
    gridLayout1.setColumns(1);
    gridLayout1.setHgap(5);
    gridLayout1.setVgap(15);
    projectListGroupBox.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        projectListGroupBox_actionPerformed(e);
      }
    });
    projectListGroupBox.setLabel("sdfasfasfasdf");
    projectListGroupBox.setForeground(UIManager.getColor("Label.foreground"));
    projectListGroupBox.setLabel("考试列表");
    projectListGroupBox.setLayout(borderLayout2);
    contentListGroupBox.setLayout(borderLayout3);
    contentListGroupBox.setLabel("考试列表");
    contentListGroupBox.setForeground(UIManager.getColor("Label.foreground"));
    contentListGroupBox.setLabel("试题列表");
    contentListGroupBox.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        contentListGroupBox_actionPerformed(e);
      }
    });
    this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    buttonPanel.add(leftButtonGroupPanel,  BorderLayout.WEST);
    buttonPanel.add(rightButtonGroupPanel,  BorderLayout.EAST);
    rightButtonGroupPanel.add(btnSelect, null);
    rightButtonGroupPanel.add(btnView, null);
    rightButtonGroupPanel.add(btnCancel, null);
    buttonPanel.add(centerButtonPanel,  BorderLayout.CENTER);
    this.getContentPane().add(contentPanel,  BorderLayout.CENTER);
    contentPanel.add(projectListGroupBox, null);
    contentPanel.add(contentListGroupBox, null);
    projectListGroupBox.add(projectScrollPane,  BorderLayout.CENTER);
    contentListGroupBox.add(contentScrollPane,  BorderLayout.CENTER);
    this.getContentPane().add(topPanel, BorderLayout.NORTH);

  }

  /** 界面表格的初始化 */
  private void  init()
  {
     if (systemControl.IExam==null)
     {
         IExam=new Examination(systemControl);
         systemControl.IExam=IExam;
     }
     else
     {
         IExam=systemControl.IExam;
     }
     /**  课程ID*/
     String     courseID=null;
     /**  存放考试信息的数据集 */
     Collection projectInfo=null;
     /**  存放考试信息的二维数组 */
     Object[][] examInfo=null;
     /**  表头  */
     Object[] examTableHeader=EXAMTABLEHEADER;
     /**  考试实例  */
     ExamProjectInfoModle examProjectInfoModle=null;
     /**  从接口接受考试信息 */
     courseID=systemControl.getCourseID();
     projectInfo=IExam.getExamInfo(courseID);
     /** 给数组分配空间  */
     if (projectInfo==null)
     {
         systemControl.writeLog("没有考试信息！");
         JOptionPane.showMessageDialog(this,"没有考试信息！","选择考试",
                                       JOptionPane.INFORMATION_MESSAGE,null);
     }
     else
     {
         examInfo=new Object[projectInfo.size()][COLUMNCOUNT];
         Iterator iterator=projectInfo.iterator();
         /**  下面的处理是将考试放入在Object[][]中 */
         int  i=0;
         while (iterator.hasNext())
         {
             examProjectInfoModle=(ExamProjectInfoModle)iterator.next();
             examInfo[i][0]=examProjectInfoModle.getProjectID();
             examInfo[i][1]=examProjectInfoModle.getProjectName();
             examInfo[i][2]=examProjectInfoModle.getProjectDate();
             examInfo[i][3]=examProjectInfoModle.getProjectInfo();
             i++;
         }
         /**  构造表模型  */
         examTableModel=new CustomTableModel(examInfo,examTableHeader);
         examTableModel.addTableModelListener(new  CustomTableModelListener());
         tbExam=new JTable(examTableModel);
         TableColumn tcol=tbExam.getColumn(EXAMID);
         tbExam.removeColumn(tcol);
         tcol=tbExam.getColumn("考试名称");
         tcol.setMaxWidth(150);
         tcol.setMinWidth(150);
         tbExam.sizeColumnsToFit(0);
         tcol=tbExam.getColumn("时间");
         tcol.setMaxWidth(80);
         tcol.setMinWidth(80);
         tbExam.sizeColumnsToFit(0);
          tbExam.addMouseListener(new java.awt.event.MouseAdapter()
          {
              public void mousePressed(MouseEvent e)
              {
                  tbExam_mousePressed(e);
              }
          });
          projectScrollPane.getViewport().add(tbExam, null);
     }
     /**  构造表模型  */
     //examTableModel=new CustomTableModel(examInfo,examTableHeader);
     //examTableModel.addTableModelListener(new  CustomTableModelListener());
     /**  构造表格   */
     //tbExam=new JTable(examTableModel);
     //TableColumn tcol=tbExam.getColumn(EXAMID);
     //tbExam.removeColumn(tcol);


     /**   存放考试内容的数据集   */
     Collection contentInfo=null;
     /**   存放考试内容的二维数组  */
     Object[][] examContentInfo=null;
     /**  表头  */
     Object[] contentTableHeader=CONTENTTABLEHEADER;
     /**  试题信息实例  */
     ExamContentInfoModel  examContentInfoModel=null;
     /**  从接口接受考试信息  */
     projectID=(String)tbExam.getModel().getValueAt(0,0);
     contentInfo=IExam.getContentInfo(projectID);
     if (contentInfo==null)
     {
     }
     else
     {
         examContentInfo=new Object[contentInfo.size()][COLUMNCOUNT];
         Iterator   contentIterator=contentInfo.iterator();
         int j=0;
         /**  下面的处理是将考试放入在Object[][]中 */
         while (contentIterator.hasNext())
         {
             examContentInfoModel=(ExamContentInfoModel)contentIterator.next();
             examContentInfo[j][0]=examContentInfoModel.getContentID();
             examContentInfo[j][1]=examContentInfoModel.getContentName();
             examContentInfo[j][2]=examContentInfoModel.getContentAnswerPaper();
             examContentInfo[j][3]=examContentInfoModel.getContentState();
             j++;
         }
         /**  构造表模型  */
         contentTableModel=new CustomTableModel(examContentInfo,contentTableHeader);

         /**  构造表格   */
         tbContent=new JTable(contentTableModel);
         TableColumn tcl=tbContent.getColumn(CONTENTID);
         tbContent.removeColumn(tcl);
         tcl=tbContent.getColumn("试题名称");
         tcl.setMaxWidth(150);
         tcl.setMinWidth(150);
         tbContent.sizeColumnsToFit(0);
         tcl=tbContent.getColumn("答题纸");
         tcl.setMaxWidth(50);
         tcl.setMinWidth(50);
         tbContent.sizeColumnsToFit(0);
         tbContent.addMouseListener(new java.awt.event.MouseAdapter()
         {
            public void mouseClicked(MouseEvent e)
            {
                tbContent_mouseClicked(e);
            }
         });
         contentScrollPane.getViewport().add(tbContent, null);
     }
  }

  /**  改变考试内容表模式的内容  */
  private void changeExamContent(String projectID)
  {
     /**   存放考试内容的数据集   */
     Collection contentInfo=null;
     /**  表头  */
     Object[] contentTableHeader=CONTENTTABLEHEADER;
     /**   存放考试内容的二维数组  */
     Object[][] examContentInfo=null;
     /**  试题信息实例  */
     ExamContentInfoModel  examContentInfoModel=null;
     /**  从接口接受考试信息  */
     contentInfo=IExam.getContentInfo(projectID);
     examContentInfo=new Object[contentInfo.size()][COLUMNCOUNT];
     Iterator  iterator=contentInfo.iterator();
     int i=0;
     /**  下面的处理是将考试放入在Object[][]中 */
     while (iterator.hasNext())
     {
         examContentInfoModel=(ExamContentInfoModel)iterator.next();
         examContentInfo[i][0]=examContentInfoModel.getContentID();
         examContentInfo[i][1]=examContentInfoModel.getContentName();
         examContentInfo[i][2]=examContentInfoModel.getContentAnswerPaper();
         examContentInfo[i][3]=examContentInfoModel.getContentState();
         i++;
     }
     /**  构造表模型  */
     ((CustomTableModel)tbContent.getModel()).setDataVector(examContentInfo,contentTableHeader);
  }


  void tbContent_mouseClicked(MouseEvent e)
  {
      /**   行号  */
      int row=0;
      row=tbContent.getSelectedRow();
      /*  获取考试ID  */
      contentID=(String)tbContent.getModel().getValueAt(row,0);
  }

  void btnSelect_actionPerformed(ActionEvent e)
  {
      /**  添加备选考试  */
      if (projectID==null)
      {
          JOptionPane.showMessageDialog(this,
                                        "请先选择试题！",
                                        "选择考试",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
          return;
      }
      if (vectProjectID.contains(projectID))
      {
          JOptionPane.showMessageDialog(this,
                                        "你已经选择了该套试题！",
                                        "选择考试",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
          return;
      }
      vectProjectID.addElement(projectID);
      /**  保存考试 ,并关闭窗口 **/
      IExam.saveContent(vectProjectID);
      this.dispose();
  }

  void btnCancel_actionPerformed(ActionEvent e)
  {
       /**  不保存，窗口关闭  */
       this.dispose();
  }

  /**  预览试题  */
  void btnView_actionPerformed(ActionEvent e)
  {
      if (contentID==null)
      {
          JOptionPane.showMessageDialog(this,
                                        "你没有选择试题,无法进行预览！",
                                        "选择试题",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
          return;
      }
      IExam.getExamContent(contentID);

  }
  void projectListGroupBox_actionPerformed(ActionEvent e)
  {

  }
  void contentListGroupBox_actionPerformed(ActionEvent e)
  {

  }

  void tbExam_mousePressed(MouseEvent e)
  {
     /**   行号  */
     int row=0;
     row=tbExam.getSelectedRow();
     /*  获取考试ID  */
     projectID=(String)tbExam.getModel().getValueAt(row,0);
     /** 刷新试题列表 */
     changeExamContent(projectID);
     TableColumn tcl=tbContent.getColumn(CONTENTID);
     tbContent.removeColumn(tcl);
     tcl=tbContent.getColumn("试题名称");
     tcl.setMaxWidth(150);
     tcl.setMinWidth(150);
     tbContent.sizeColumnsToFit(0);
     tcl=tbContent.getColumn("答题纸");
     tcl.setMaxWidth(50);
     tcl.setMinWidth(50);
     tbContent.sizeColumnsToFit(0);
     tbContent.updateUI();
  }

}