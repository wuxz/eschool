package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.dc.eschool.exam.ExamIF;
import com.dc.eschool.SelectExamFrm;
import com.dc.eschool.exam.Examination;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.DataModel.ExamContentInfoModel;
import com.dc.eschool.group.MyFileFilter;
import com.borland.jbcl.control.*;
import com.borland.jbcl.layout.*;



/**
 * Title:        考试分组
 * Description:  老师利用该界面给学生分配考题。
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class ExamGroupFrm extends JDialog {

  private static final int COLUMNCOUNT=4;

  JTable tbContent = null;
  JButton btnDelete = new JButton();
  JList lstNoContentGroup = null;
  JList lstContentGroup = null;
  JButton btnFinish = new JButton();

  /**  SystemControl实例  */
  private  SystemControl      systemControl=null;
  /**  考试接口*/
  private  ExamIF             IExam;
  /**  考试内容表模型 */
  private  CustomTableModel   contentTableModel=null;
  /**  试题ID */
  private  String             contentID=null;
  /**  文件名  */
  private  String             fileName;
  /**  被选中的行号 */
  private  int                row=0;
  /**  上一个被选中的行号 */
  private  int                oldRow=-1;
  /**   存放考试内容的二维数组  */
  private  Object[][]         examContentInfo=null;
  /**  表头  */
  private  Object[]           contentTableHeader={"试题ID","试题名称","答题纸","状态"};
  /**  无试题小组列表模型 */
  private  DefaultListModel   noContentGroupModel=null;
  /**  有试题小组立标模型  */
  private  DefaultListModel   contentGroupModel=null;
  /**  试题ID响量 */
  private  Vector             vectContentID=new Vector();
  /**  学生列表向量 它的元素是矢量 */
  private  Vector             vectStudentList=new Vector();
  /**  分组矢量该变量存储者所有的小组信息 */
  private  Vector             vectAllGroupInfo=new Vector();
  /**  未分配试题的小组信息*/
  private  Vector             vectNoContentGroupInfo=new Vector();
  /**  存放考题ID和小组名称矢量的哈希表 */
  private  Hashtable          htContenIDGroupName=new Hashtable();
  JPanel centerButtonGroupPanel = new JPanel();
  JPanel rightButtonGroupPanel = new JPanel();
  JPanel leftButtonGroupPanel = new JPanel();
  JPanel buttonPanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JButton btnCancel = new JButton();
  JPanel contentPanel = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  GroupBox contentListGroupBox = new GroupBox();
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane projectListScrollPane = new JScrollPane();
  JPanel centerButtonGroupPanel1 = new JPanel();
  JPanel rightButtonGroupPanel1 = new JPanel();
  JPanel leftButtonGroupPanel1 = new JPanel();
  JPanel buttonPanel1 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout6 = new BorderLayout();
  GroupBox GroupBox1 = new GroupBox();
  JLabel lbContent = new JLabel();
  JPanel selectPanel = new JPanel();
  GroupBox unselectGroupBox = new GroupBox();
  JPanel selectButtonToolPanel = new JPanel();
  GroupBox selectedGroupBox = new GroupBox();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JScrollPane unselectScrollPane = new JScrollPane();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JScrollPane selectedScrollPane = new JScrollPane();
  JButton btnMoveRight = new JButton();
  JButton btnMoveLeft = new JButton();
  JButton btnAllMoveRight = new JButton();
  JButton btnAllMoveLeft = new JButton();
  JPanel topPanel = new JPanel();
  GridLayout gridLayout2 = new GridLayout();




  public ExamGroupFrm(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public ExamGroupFrm()
  {
    this(null, "", false);
  }

  /**   构造器  */
  public ExamGroupFrm(Frame frame,SystemControl systemControl)  throws Exception
  {
    this(frame, "", false);
    this.systemControl=systemControl;
    init();
  }

  void jbInit() throws Exception {
    btnDelete.setText("删除");
    btnDelete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnDelete_actionPerformed(e);
      }
    });
    this.setModal(true);
    this.setTitle("试题分组");
    btnFinish.setText("分配结束");
    btnFinish.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnFinish_actionPerformed(e);
      }
    });

    buttonPanel.setLayout(borderLayout2);
    btnCancel.setText("取消");
    btnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnCancel_actionPerformed(e);
      }
    });
    contentPanel.setLayout(gridLayout1);
    gridLayout1.setRows(2);
    gridLayout1.setColumns(1);
    gridLayout1.setVgap(2);
    contentListGroupBox.setLayout(borderLayout3);
    contentListGroupBox.setLabel("考试列表");
    contentListGroupBox.setLabel("备选试题列表");
    contentListGroupBox.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        contentListGroupBox_actionPerformed(e);
      }
    });
    buttonPanel1.setLayout(borderLayout4);
    GroupBox1.setLayout(borderLayout6);
    GroupBox1.setLabel("考试列表");
    GroupBox1.setLabel("分组");
    GroupBox1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        GroupBox1_actionPerformed(e);
      }
    });
    //lbContent1.setForeground(Color.black);
    lbContent.setText("试卷");
    selectPanel.setLayout(gridLayout2);
    unselectGroupBox.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        unselectGroupBox_actionPerformed(e);
      }
    });
    unselectGroupBox.setLabel("sdfasfasfasdf");
    unselectGroupBox.setLabel("未分配到试题的组");
    unselectGroupBox.setLayout(borderLayout1);
    selectedGroupBox.setLabel("未分配到试题的组");
    selectedGroupBox.setLabel("试卷分配到以下组");
    selectedGroupBox.setLayout(borderLayout5);
    selectedGroupBox.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        selectedGroupBox_actionPerformed(e);
      }
    });
    selectButtonToolPanel.setLayout(verticalFlowLayout1);
    btnMoveRight.setText(">");
    btnMoveRight.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnMoveRight_actionPerformed(e);
      }
    });
    btnMoveLeft.setText("<");
    btnMoveLeft.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnMoveLeft_actionPerformed(e);
      }
    });
    btnAllMoveRight.setText(">>");
    btnAllMoveRight.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnAllMoveRight_actionPerformed(e);
      }
    });
    btnAllMoveLeft.setText("<<");
    btnAllMoveLeft.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnAllMoveLeft_actionPerformed(e);
      }
    });
    verticalFlowLayout1.setAlignment(VerticalFlowLayout.MIDDLE);

    gridLayout2.setColumns(3);
    buttonPanel.add(leftButtonGroupPanel, BorderLayout.WEST);
    buttonPanel.add(rightButtonGroupPanel, BorderLayout.EAST);
    rightButtonGroupPanel.add(btnFinish, null);
    rightButtonGroupPanel.add(btnCancel, null);
    buttonPanel.add(centerButtonGroupPanel, BorderLayout.CENTER);
    this.getContentPane().add(contentPanel,  BorderLayout.CENTER);
    contentPanel.add(contentListGroupBox, null);
    contentListGroupBox.add(projectListScrollPane, BorderLayout.CENTER);
    contentListGroupBox.add(buttonPanel1,  BorderLayout.SOUTH);
    buttonPanel1.add(leftButtonGroupPanel1, BorderLayout.WEST);
    buttonPanel1.add(rightButtonGroupPanel1, BorderLayout.EAST);
    rightButtonGroupPanel1.add(btnDelete, null);
    buttonPanel1.add(centerButtonGroupPanel1, BorderLayout.CENTER);
    contentPanel.add(GroupBox1, null);
    GroupBox1.add(lbContent, BorderLayout.NORTH);
    GroupBox1.add(selectPanel, BorderLayout.CENTER);
    selectPanel.add(unselectGroupBox, null);
    unselectGroupBox.add(unselectScrollPane, BorderLayout.CENTER);
    selectPanel.add(selectButtonToolPanel, null);
    selectPanel.add(selectedGroupBox, null);
    selectedGroupBox.add(selectedScrollPane, BorderLayout.CENTER);
    selectButtonToolPanel.add(btnMoveLeft, null);
    selectButtonToolPanel.add(btnMoveRight, null);
    selectButtonToolPanel.add(btnAllMoveLeft, null);
    selectButtonToolPanel.add(btnAllMoveRight, null);
    this.getContentPane().add(topPanel, BorderLayout.NORTH);
    this.getContentPane().add(buttonPanel,  BorderLayout.SOUTH);
  }

  /**  初始化界面  */
  private  void init() throws Exception
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
     /**   存放考试内容的数据集   */
     Collection contentInfo=null;
     /**  试题信息实例  */
     ExamContentInfoModel  examContentInfoModel=null;
     /**  从接口接受考试信息  */
     courseID=systemControl.getCourseID();
     contentInfo=IExam.getContentList(courseID);
     if (contentInfo==null)
     {
         systemControl.writeLog("在试题分组时,没有发现试题信息！");
         JOptionPane.showMessageDialog(this,
                                       "没有发现试题信息！",
                                       "试题信息",
                                       JOptionPane.INFORMATION_MESSAGE,
                                       null);
     }
     else
     {
         examContentInfo=new Object[contentInfo.size()][COLUMNCOUNT];
         Iterator iterator=contentInfo.iterator();
         int j=0;
         /**  下面的处理是将考试放入在Object[][]中 */
         while (iterator.hasNext())
         {
             examContentInfoModel=(ExamContentInfoModel)iterator.next();
             examContentInfo[j][0]=examContentInfoModel.getContentID();
             examContentInfo[j][1]=examContentInfoModel.getContentName();
             examContentInfo[j][2]=examContentInfoModel.getContentAnswerPaper();
             examContentInfo[j][3]=examContentInfoModel.getContentState();
             j++;
         }
         /**  构造表模型  */
         contentTableModel=new CustomTableModel(examContentInfo,contentTableHeader);
         /**  构造表格,并让试题ID列不可见  */
         tbContent=new JTable(contentTableModel);
         projectListScrollPane.getViewport().add(tbContent, null);
         TableColumn tcl=tbContent.getColumn("试题ID");
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
             public void mousePressed(MouseEvent e)
             {
                 tbContent_mousePressed(e);
             }
         });
      }
     /**   取得分组文件名  */
     JFileChooser fileChooser=new  JFileChooser();
     fileChooser.setDialogTitle("打开分组文件");
     MyFileFilter filter = new MyFileFilter();
     filter.addExtension("xml");
     filter.setDescription("xml File");
     FileFilter f=fileChooser.getFileFilter();
     fileChooser.removeChoosableFileFilter(f);
     fileChooser.setFileFilter(filter);
     fileChooser.showOpenDialog(this);
     if (fileChooser.getSelectedFile()==null)
     {
         JOptionPane.showMessageDialog(this,
                                       "没有取到文件,无法获得分组信息！",
                                       "考试分组",
                                       JOptionPane.INFORMATION_MESSAGE,
                                       null);
         throw new Exception();
     }
     fileName=fileChooser.getSelectedFile().getAbsolutePath();
     /**  构造列表模型 */
     noContentGroupModel=new DefaultListModel();
     contentGroupModel=new  DefaultListModel();
     /**   取得分组信息 */
     try
     {
         vectAllGroupInfo=IExam.getGroupsName(fileName);
     }
     catch(Exception e)
     {
         vectAllGroupInfo=null;
     }
     /**   给未分配试题的小组矢量赋值 */
     if (vectAllGroupInfo==null)
     {
         systemControl.writeLog("在考试分组时无法取得小组信息！");
         JOptionPane.showMessageDialog(this,
                                       "无法获得小组信息！",
                                       "考试分组",
                                       JOptionPane.INFORMATION_MESSAGE,
                                       null);
         throw new Exception();
     }
     else
     {
         vectNoContentGroupInfo=(Vector)vectAllGroupInfo.clone();
         /**   将数据填入列表模型中 */
         for (int i=0;i<vectAllGroupInfo.size();i++)
         {
            noContentGroupModel.addElement(vectAllGroupInfo.get(i));
         }
         /**   构造列表 */
         lstNoContentGroup=new JList(noContentGroupModel);
         lstContentGroup=new JList(contentGroupModel);
         unselectScrollPane.getViewport().add(lstNoContentGroup, null);
         selectedScrollPane.getViewport().add(lstContentGroup, null);
     }
  }

  /**  表格的单击*/
  void tbContent_mousePressed(MouseEvent e)
  {
      if (oldRow!=-1)
      {
          saveInfo();
      }
      /** 试题名称  */
      String contentName;
      /**  存放临时矢量 */
      Vector vectTemp=new Vector();
      /** 取得试题ID */
      row=tbContent.getSelectedRow();
      contentID=(String)tbContent.getModel().getValueAt(row,0);
      /** 取得试题名称 */
      contentName=(String)tbContent.getValueAt(row,0);
      contentName=contentName+"试题";
      /** 刷新考试标签 */
      lbContent.setText(contentName);
      //变化列表的内容
      contentGroupModel.clear();
      vectTemp=(Vector)htContenIDGroupName.get(contentID);
      contentGroupModel.clear();
      noContentGroupModel.clear();
      if (vectTemp==null)
      {
          for (int m=0;m<vectNoContentGroupInfo.size();m++)
              noContentGroupModel.addElement(vectNoContentGroupInfo.get(m));
      }
      else
      {
          for (int i=0;i<vectTemp.size();i++)
              contentGroupModel.addElement(vectTemp.get(i));
          for (int j=0;j<vectNoContentGroupInfo.size();j++)
              noContentGroupModel.addElement(vectNoContentGroupInfo.get(j));
      }
      lstNoContentGroup.updateUI();
      lstContentGroup.updateUI();
      oldRow=row;
  }
  void btnDelete_actionPerformed(ActionEvent e)
  {
      if (tbContent==null)
      {
          return;
      }
      row=tbContent.getSelectedRow();
      /**  如果表格中还有数据就继续删除，并刷新表格 */
      if (row>-1)
      {
          contentTableModel.removeRow(row);
          tbContent.updateUI();
      }
      else
      {
          JOptionPane.showMessageDialog(this,
                                        "没有可以删除的项目了",
                                        "试题分组",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
      }
  }

  void btnCancel_actionPerformed(ActionEvent e)
  {
      /**  关闭窗口  */
      this.dispose();
  }


  void btnFinish_actionPerformed(ActionEvent e)
  {
      saveInfo();
      /**  试题ID*/
      String  tempContentID;
      /**  临时Vector */
      Vector  tempGroupVect=new Vector();
      /**  临时组学生Vector  */
      Vector  tempStudentVecor=new Vector();
      int     count=0;
      if (tbContent==null)
      {
          JOptionPane.showMessageDialog(this,
                                        "没有信息可以保存！",
                                        "考试分组",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
          return;
      }
      /**  课程ID    */
      String courseID=systemControl.getCourseID();
      String teacherID=systemControl.getUserID();
      count=tbContent.getModel().getRowCount();
      for(int i=0;i<count;i++)
      {
          tempContentID=(String)tbContent.getModel().getValueAt(i,0);
          tempGroupVect=(Vector)htContenIDGroupName.get(tempContentID);
          if (tempGroupVect==null)
          {
          }
          else
          {
             for (int j=0;j<tempGroupVect.size();j++)
             {
                tempStudentVecor=IExam.getStudentbyGroupsName((String)tempGroupVect.get(j),fileName);
                IExam.SaveContentStudent(tempStudentVecor,tempContentID,courseID,teacherID);
             }
          }
      }
      this.dispose();

  }

  void btnMoveRight_actionPerformed(ActionEvent e)
  {
       if (lstNoContentGroup==null)
       {
           JOptionPane.showMessageDialog(this,
                                        "没有备选小组！",
                                        "考试分组",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
           return;
       }
       /**  转移小组到考试一边 */
       String selectedGroup;
       selectedGroup=(String)lstNoContentGroup.getSelectedValue();
       if (selectedGroup==null)
       {
           JOptionPane.showMessageDialog(this,
                                        "请先选择小组！",
                                        "考试分组",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
       }
       else
       {
           noContentGroupModel.removeElement(selectedGroup);
           contentGroupModel.addElement(selectedGroup);
           lstNoContentGroup.updateUI();
           lstContentGroup.updateUI();
       }
  }


  /**  转移小组到不考试的一边 */
  void btnMoveLeft_actionPerformed(ActionEvent e)
  {
       if (lstContentGroup==null)
       {
           JOptionPane.showMessageDialog(this,
                                        "没有备选小组！",
                                        "考试分组",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
           return;
       }
       String selectedGroup;
       selectedGroup=(String)lstContentGroup.getSelectedValue();
       if (selectedGroup==null)
       {
           JOptionPane.showMessageDialog(this,
                                        "请先选择小组！",
                                        "考试分组",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
       }
       else
       {
           noContentGroupModel.addElement(selectedGroup);
           contentGroupModel.removeElement(selectedGroup);
           lstNoContentGroup.updateUI();
           lstContentGroup.updateUI();
       }
  }

  void btnAllMoveRight_actionPerformed(ActionEvent e)
  {
      /**把所有没有考试的小组都加到考试的一边 */
      Object[] allGroups;
      if (noContentGroupModel.isEmpty())
      {
           JOptionPane.showMessageDialog(this,
                                        "没有备选小组！",
                                        "考试分组",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
           return;
      }
      else
      {
          allGroups=noContentGroupModel.toArray();
          for (int i=0;i<allGroups.length;i++)
              contentGroupModel.addElement(allGroups[i]);
          noContentGroupModel.clear();
          lstNoContentGroup.updateUI();
          lstContentGroup.updateUI();
      }
  }

  void btnAllMoveLeft_actionPerformed(ActionEvent e)
  {
      /**把所有考试的小组都加到不考试的一边 */
      Object[] allGroups;
      if (contentGroupModel.isEmpty())
      {
           JOptionPane.showMessageDialog(this,
                                        "没有备选小组！",
                                        "考试分组",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
           return;
      }
      else
      {
          allGroups=contentGroupModel.toArray();
          for (int i=0;i<allGroups.length;i++)
              noContentGroupModel.addElement(allGroups[i]);
          contentGroupModel.clear();
          lstNoContentGroup.updateUI();
          lstContentGroup.updateUI();
      }
  }
  private void saveInfo()
  {
       Vector  vectTempGroupName=new Vector();
       /**  将分配到考题的小组信息写入Vector 中 */
       if (contentID==null)
       {
           JOptionPane.showMessageDialog(this,
                                         "没有选中试题,无法保存！",
                                         "考试分组",
                                         JOptionPane.INFORMATION_MESSAGE,
                                         null);
           return;
       }
       if ((contentGroupModel.isEmpty())&&(htContenIDGroupName.get(contentID)==null))
          return;
       else
       {
           for (int i=0;i<contentGroupModel.size();i++)
           {
                vectTempGroupName.addElement(contentGroupModel.get(i));
           }
           htContenIDGroupName.put(contentID,vectTempGroupName);
           vectNoContentGroupInfo.clear();
           for (int j=0;j<noContentGroupModel.size();j++)
           {
               vectNoContentGroupInfo.addElement(noContentGroupModel.get(j));
           }
       }
  }
  void GroupBox1_actionPerformed(ActionEvent e)
  {

  }
  void unselectGroupBox_actionPerformed(ActionEvent e)
  {

  }
  void selectedGroupBox_actionPerformed(ActionEvent e)
  {

  }
  void contentListGroupBox_actionPerformed(ActionEvent e)
  {

  }

}