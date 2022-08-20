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
 * Title:        ѡ����
 * Description:  ��ʦ���øý���ѡ��������
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

/**  �̳�DefaultTableModel,��JTable���ܱ༭. */
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

  /**   ��̬����  */
  private  static  final String      EXAMID   =  "����ID";
  private  static  final String      CONTENTID=  "����ID";
  private  static  final Object[]    CONTENTTABLEHEADER={"����ID","��������","����ֽ","״̬"};
  private  static  final Object[]    EXAMTABLEHEADER={"����ID","��������","ʱ��","˵��"};
  private  static  final int         COLUMNCOUNT=4;

  /**   SystemControl ʵ�� */
  private  SystemControl        systemControl=null;
  /**  ���Խӿ�����   */
  private  ExamIF               IExam=null;
  /**  ���Ա�ģ��   */
  private  CustomTableModel     examTableModel=null;
  /**  �������ݱ�ģ�� */
  private  CustomTableModel     contentTableModel=null;
  /**  ����ID */
  private  String               projectID=null;
  /**  ����ID */
  private  String               contentID=null;
  /**  ����ID���� */
  private  Vector               vectProjectID=new Vector();
  /**  ���ⴰ��   */
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

  /**   �Զ���Ĺ�����  */
  public SelectExamFrm(Frame frame,SystemControl systemControl)
  {
    this(frame, "", false);
    this.systemControl=systemControl;
    init();
  }

  /**   ����ĳ�ʼ��  */
  void jbInit() throws Exception
  {
    this.setModal(true);
    this.setTitle("ѡ����");



    buttonPanel.setLayout(borderLayout1);
    btnCancel.setText("ȡ��");
    btnCancel.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnCancel_actionPerformed(e);
      }
    });
    btnSelect.setText("��ѡ");
    btnSelect.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnSelect_actionPerformed(e);
      }
    });
    btnView.setText("Ԥ��");
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
    projectListGroupBox.setLabel("�����б�");
    projectListGroupBox.setLayout(borderLayout2);
    contentListGroupBox.setLayout(borderLayout3);
    contentListGroupBox.setLabel("�����б�");
    contentListGroupBox.setForeground(UIManager.getColor("Label.foreground"));
    contentListGroupBox.setLabel("�����б�");
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

  /** ������ĳ�ʼ�� */
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
     /**  �γ�ID*/
     String     courseID=null;
     /**  ��ſ�����Ϣ�����ݼ� */
     Collection projectInfo=null;
     /**  ��ſ�����Ϣ�Ķ�ά���� */
     Object[][] examInfo=null;
     /**  ��ͷ  */
     Object[] examTableHeader=EXAMTABLEHEADER;
     /**  ����ʵ��  */
     ExamProjectInfoModle examProjectInfoModle=null;
     /**  �ӽӿڽ��ܿ�����Ϣ */
     courseID=systemControl.getCourseID();
     projectInfo=IExam.getExamInfo(courseID);
     /** ���������ռ�  */
     if (projectInfo==null)
     {
         systemControl.writeLog("û�п�����Ϣ��");
         JOptionPane.showMessageDialog(this,"û�п�����Ϣ��","ѡ����",
                                       JOptionPane.INFORMATION_MESSAGE,null);
     }
     else
     {
         examInfo=new Object[projectInfo.size()][COLUMNCOUNT];
         Iterator iterator=projectInfo.iterator();
         /**  ����Ĵ����ǽ����Է�����Object[][]�� */
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
         /**  �����ģ��  */
         examTableModel=new CustomTableModel(examInfo,examTableHeader);
         examTableModel.addTableModelListener(new  CustomTableModelListener());
         tbExam=new JTable(examTableModel);
         TableColumn tcol=tbExam.getColumn(EXAMID);
         tbExam.removeColumn(tcol);
         tcol=tbExam.getColumn("��������");
         tcol.setMaxWidth(150);
         tcol.setMinWidth(150);
         tbExam.sizeColumnsToFit(0);
         tcol=tbExam.getColumn("ʱ��");
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
     /**  �����ģ��  */
     //examTableModel=new CustomTableModel(examInfo,examTableHeader);
     //examTableModel.addTableModelListener(new  CustomTableModelListener());
     /**  ������   */
     //tbExam=new JTable(examTableModel);
     //TableColumn tcol=tbExam.getColumn(EXAMID);
     //tbExam.removeColumn(tcol);


     /**   ��ſ������ݵ����ݼ�   */
     Collection contentInfo=null;
     /**   ��ſ������ݵĶ�ά����  */
     Object[][] examContentInfo=null;
     /**  ��ͷ  */
     Object[] contentTableHeader=CONTENTTABLEHEADER;
     /**  ������Ϣʵ��  */
     ExamContentInfoModel  examContentInfoModel=null;
     /**  �ӽӿڽ��ܿ�����Ϣ  */
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
         /**  ����Ĵ����ǽ����Է�����Object[][]�� */
         while (contentIterator.hasNext())
         {
             examContentInfoModel=(ExamContentInfoModel)contentIterator.next();
             examContentInfo[j][0]=examContentInfoModel.getContentID();
             examContentInfo[j][1]=examContentInfoModel.getContentName();
             examContentInfo[j][2]=examContentInfoModel.getContentAnswerPaper();
             examContentInfo[j][3]=examContentInfoModel.getContentState();
             j++;
         }
         /**  �����ģ��  */
         contentTableModel=new CustomTableModel(examContentInfo,contentTableHeader);

         /**  ������   */
         tbContent=new JTable(contentTableModel);
         TableColumn tcl=tbContent.getColumn(CONTENTID);
         tbContent.removeColumn(tcl);
         tcl=tbContent.getColumn("��������");
         tcl.setMaxWidth(150);
         tcl.setMinWidth(150);
         tbContent.sizeColumnsToFit(0);
         tcl=tbContent.getColumn("����ֽ");
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

  /**  �ı俼�����ݱ�ģʽ������  */
  private void changeExamContent(String projectID)
  {
     /**   ��ſ������ݵ����ݼ�   */
     Collection contentInfo=null;
     /**  ��ͷ  */
     Object[] contentTableHeader=CONTENTTABLEHEADER;
     /**   ��ſ������ݵĶ�ά����  */
     Object[][] examContentInfo=null;
     /**  ������Ϣʵ��  */
     ExamContentInfoModel  examContentInfoModel=null;
     /**  �ӽӿڽ��ܿ�����Ϣ  */
     contentInfo=IExam.getContentInfo(projectID);
     examContentInfo=new Object[contentInfo.size()][COLUMNCOUNT];
     Iterator  iterator=contentInfo.iterator();
     int i=0;
     /**  ����Ĵ����ǽ����Է�����Object[][]�� */
     while (iterator.hasNext())
     {
         examContentInfoModel=(ExamContentInfoModel)iterator.next();
         examContentInfo[i][0]=examContentInfoModel.getContentID();
         examContentInfo[i][1]=examContentInfoModel.getContentName();
         examContentInfo[i][2]=examContentInfoModel.getContentAnswerPaper();
         examContentInfo[i][3]=examContentInfoModel.getContentState();
         i++;
     }
     /**  �����ģ��  */
     ((CustomTableModel)tbContent.getModel()).setDataVector(examContentInfo,contentTableHeader);
  }


  void tbContent_mouseClicked(MouseEvent e)
  {
      /**   �к�  */
      int row=0;
      row=tbContent.getSelectedRow();
      /*  ��ȡ����ID  */
      contentID=(String)tbContent.getModel().getValueAt(row,0);
  }

  void btnSelect_actionPerformed(ActionEvent e)
  {
      /**  ��ӱ�ѡ����  */
      if (projectID==null)
      {
          JOptionPane.showMessageDialog(this,
                                        "����ѡ�����⣡",
                                        "ѡ����",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
          return;
      }
      if (vectProjectID.contains(projectID))
      {
          JOptionPane.showMessageDialog(this,
                                        "���Ѿ�ѡ���˸������⣡",
                                        "ѡ����",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
          return;
      }
      vectProjectID.addElement(projectID);
      /**  ���濼�� ,���رմ��� **/
      IExam.saveContent(vectProjectID);
      this.dispose();
  }

  void btnCancel_actionPerformed(ActionEvent e)
  {
       /**  �����棬���ڹر�  */
       this.dispose();
  }

  /**  Ԥ������  */
  void btnView_actionPerformed(ActionEvent e)
  {
      if (contentID==null)
      {
          JOptionPane.showMessageDialog(this,
                                        "��û��ѡ������,�޷�����Ԥ����",
                                        "ѡ������",
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
     /**   �к�  */
     int row=0;
     row=tbExam.getSelectedRow();
     /*  ��ȡ����ID  */
     projectID=(String)tbExam.getModel().getValueAt(row,0);
     /** ˢ�������б� */
     changeExamContent(projectID);
     TableColumn tcl=tbContent.getColumn(CONTENTID);
     tbContent.removeColumn(tcl);
     tcl=tbContent.getColumn("��������");
     tcl.setMaxWidth(150);
     tcl.setMinWidth(150);
     tbContent.sizeColumnsToFit(0);
     tcl=tbContent.getColumn("����ֽ");
     tcl.setMaxWidth(50);
     tcl.setMinWidth(50);
     tbContent.sizeColumnsToFit(0);
     tbContent.updateUI();
  }

}