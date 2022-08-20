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
 * Title:        ���Է���
 * Description:  ��ʦ���øý����ѧ�����俼�⡣
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

  /**  SystemControlʵ��  */
  private  SystemControl      systemControl=null;
  /**  ���Խӿ�*/
  private  ExamIF             IExam;
  /**  �������ݱ�ģ�� */
  private  CustomTableModel   contentTableModel=null;
  /**  ����ID */
  private  String             contentID=null;
  /**  �ļ���  */
  private  String             fileName;
  /**  ��ѡ�е��к� */
  private  int                row=0;
  /**  ��һ����ѡ�е��к� */
  private  int                oldRow=-1;
  /**   ��ſ������ݵĶ�ά����  */
  private  Object[][]         examContentInfo=null;
  /**  ��ͷ  */
  private  Object[]           contentTableHeader={"����ID","��������","����ֽ","״̬"};
  /**  ������С���б�ģ�� */
  private  DefaultListModel   noContentGroupModel=null;
  /**  ������С������ģ��  */
  private  DefaultListModel   contentGroupModel=null;
  /**  ����ID���� */
  private  Vector             vectContentID=new Vector();
  /**  ѧ���б����� ����Ԫ����ʸ�� */
  private  Vector             vectStudentList=new Vector();
  /**  ����ʸ���ñ����洢�����е�С����Ϣ */
  private  Vector             vectAllGroupInfo=new Vector();
  /**  δ���������С����Ϣ*/
  private  Vector             vectNoContentGroupInfo=new Vector();
  /**  ��ſ���ID��С������ʸ���Ĺ�ϣ�� */
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

  /**   ������  */
  public ExamGroupFrm(Frame frame,SystemControl systemControl)  throws Exception
  {
    this(frame, "", false);
    this.systemControl=systemControl;
    init();
  }

  void jbInit() throws Exception {
    btnDelete.setText("ɾ��");
    btnDelete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnDelete_actionPerformed(e);
      }
    });
    this.setModal(true);
    this.setTitle("�������");
    btnFinish.setText("�������");
    btnFinish.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnFinish_actionPerformed(e);
      }
    });

    buttonPanel.setLayout(borderLayout2);
    btnCancel.setText("ȡ��");
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
    contentListGroupBox.setLabel("�����б�");
    contentListGroupBox.setLabel("��ѡ�����б�");
    contentListGroupBox.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        contentListGroupBox_actionPerformed(e);
      }
    });
    buttonPanel1.setLayout(borderLayout4);
    GroupBox1.setLayout(borderLayout6);
    GroupBox1.setLabel("�����б�");
    GroupBox1.setLabel("����");
    GroupBox1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        GroupBox1_actionPerformed(e);
      }
    });
    //lbContent1.setForeground(Color.black);
    lbContent.setText("�Ծ�");
    selectPanel.setLayout(gridLayout2);
    unselectGroupBox.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        unselectGroupBox_actionPerformed(e);
      }
    });
    unselectGroupBox.setLabel("sdfasfasfasdf");
    unselectGroupBox.setLabel("δ���䵽�������");
    unselectGroupBox.setLayout(borderLayout1);
    selectedGroupBox.setLabel("δ���䵽�������");
    selectedGroupBox.setLabel("�Ծ���䵽������");
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

  /**  ��ʼ������  */
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
     /**  �γ�ID*/
     String     courseID=null;
     /**   ��ſ������ݵ����ݼ�   */
     Collection contentInfo=null;
     /**  ������Ϣʵ��  */
     ExamContentInfoModel  examContentInfoModel=null;
     /**  �ӽӿڽ��ܿ�����Ϣ  */
     courseID=systemControl.getCourseID();
     contentInfo=IExam.getContentList(courseID);
     if (contentInfo==null)
     {
         systemControl.writeLog("���������ʱ,û�з���������Ϣ��");
         JOptionPane.showMessageDialog(this,
                                       "û�з���������Ϣ��",
                                       "������Ϣ",
                                       JOptionPane.INFORMATION_MESSAGE,
                                       null);
     }
     else
     {
         examContentInfo=new Object[contentInfo.size()][COLUMNCOUNT];
         Iterator iterator=contentInfo.iterator();
         int j=0;
         /**  ����Ĵ����ǽ����Է�����Object[][]�� */
         while (iterator.hasNext())
         {
             examContentInfoModel=(ExamContentInfoModel)iterator.next();
             examContentInfo[j][0]=examContentInfoModel.getContentID();
             examContentInfo[j][1]=examContentInfoModel.getContentName();
             examContentInfo[j][2]=examContentInfoModel.getContentAnswerPaper();
             examContentInfo[j][3]=examContentInfoModel.getContentState();
             j++;
         }
         /**  �����ģ��  */
         contentTableModel=new CustomTableModel(examContentInfo,contentTableHeader);
         /**  ������,��������ID�в��ɼ�  */
         tbContent=new JTable(contentTableModel);
         projectListScrollPane.getViewport().add(tbContent, null);
         TableColumn tcl=tbContent.getColumn("����ID");
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
             public void mousePressed(MouseEvent e)
             {
                 tbContent_mousePressed(e);
             }
         });
      }
     /**   ȡ�÷����ļ���  */
     JFileChooser fileChooser=new  JFileChooser();
     fileChooser.setDialogTitle("�򿪷����ļ�");
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
                                       "û��ȡ���ļ�,�޷���÷�����Ϣ��",
                                       "���Է���",
                                       JOptionPane.INFORMATION_MESSAGE,
                                       null);
         throw new Exception();
     }
     fileName=fileChooser.getSelectedFile().getAbsolutePath();
     /**  �����б�ģ�� */
     noContentGroupModel=new DefaultListModel();
     contentGroupModel=new  DefaultListModel();
     /**   ȡ�÷�����Ϣ */
     try
     {
         vectAllGroupInfo=IExam.getGroupsName(fileName);
     }
     catch(Exception e)
     {
         vectAllGroupInfo=null;
     }
     /**   ��δ���������С��ʸ����ֵ */
     if (vectAllGroupInfo==null)
     {
         systemControl.writeLog("�ڿ��Է���ʱ�޷�ȡ��С����Ϣ��");
         JOptionPane.showMessageDialog(this,
                                       "�޷����С����Ϣ��",
                                       "���Է���",
                                       JOptionPane.INFORMATION_MESSAGE,
                                       null);
         throw new Exception();
     }
     else
     {
         vectNoContentGroupInfo=(Vector)vectAllGroupInfo.clone();
         /**   �����������б�ģ���� */
         for (int i=0;i<vectAllGroupInfo.size();i++)
         {
            noContentGroupModel.addElement(vectAllGroupInfo.get(i));
         }
         /**   �����б� */
         lstNoContentGroup=new JList(noContentGroupModel);
         lstContentGroup=new JList(contentGroupModel);
         unselectScrollPane.getViewport().add(lstNoContentGroup, null);
         selectedScrollPane.getViewport().add(lstContentGroup, null);
     }
  }

  /**  ���ĵ���*/
  void tbContent_mousePressed(MouseEvent e)
  {
      if (oldRow!=-1)
      {
          saveInfo();
      }
      /** ��������  */
      String contentName;
      /**  �����ʱʸ�� */
      Vector vectTemp=new Vector();
      /** ȡ������ID */
      row=tbContent.getSelectedRow();
      contentID=(String)tbContent.getModel().getValueAt(row,0);
      /** ȡ���������� */
      contentName=(String)tbContent.getValueAt(row,0);
      contentName=contentName+"����";
      /** ˢ�¿��Ա�ǩ */
      lbContent.setText(contentName);
      //�仯�б������
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
      /**  �������л������ݾͼ���ɾ������ˢ�±�� */
      if (row>-1)
      {
          contentTableModel.removeRow(row);
          tbContent.updateUI();
      }
      else
      {
          JOptionPane.showMessageDialog(this,
                                        "û�п���ɾ������Ŀ��",
                                        "�������",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
      }
  }

  void btnCancel_actionPerformed(ActionEvent e)
  {
      /**  �رմ���  */
      this.dispose();
  }


  void btnFinish_actionPerformed(ActionEvent e)
  {
      saveInfo();
      /**  ����ID*/
      String  tempContentID;
      /**  ��ʱVector */
      Vector  tempGroupVect=new Vector();
      /**  ��ʱ��ѧ��Vector  */
      Vector  tempStudentVecor=new Vector();
      int     count=0;
      if (tbContent==null)
      {
          JOptionPane.showMessageDialog(this,
                                        "û����Ϣ���Ա��棡",
                                        "���Է���",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
          return;
      }
      /**  �γ�ID    */
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
                                        "û�б�ѡС�飡",
                                        "���Է���",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
           return;
       }
       /**  ת��С�鵽����һ�� */
       String selectedGroup;
       selectedGroup=(String)lstNoContentGroup.getSelectedValue();
       if (selectedGroup==null)
       {
           JOptionPane.showMessageDialog(this,
                                        "����ѡ��С�飡",
                                        "���Է���",
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


  /**  ת��С�鵽�����Ե�һ�� */
  void btnMoveLeft_actionPerformed(ActionEvent e)
  {
       if (lstContentGroup==null)
       {
           JOptionPane.showMessageDialog(this,
                                        "û�б�ѡС�飡",
                                        "���Է���",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null);
           return;
       }
       String selectedGroup;
       selectedGroup=(String)lstContentGroup.getSelectedValue();
       if (selectedGroup==null)
       {
           JOptionPane.showMessageDialog(this,
                                        "����ѡ��С�飡",
                                        "���Է���",
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
      /**������û�п��Ե�С�鶼�ӵ����Ե�һ�� */
      Object[] allGroups;
      if (noContentGroupModel.isEmpty())
      {
           JOptionPane.showMessageDialog(this,
                                        "û�б�ѡС�飡",
                                        "���Է���",
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
      /**�����п��Ե�С�鶼�ӵ������Ե�һ�� */
      Object[] allGroups;
      if (contentGroupModel.isEmpty())
      {
           JOptionPane.showMessageDialog(this,
                                        "û�б�ѡС�飡",
                                        "���Է���",
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
       /**  �����䵽�����С����Ϣд��Vector �� */
       if (contentID==null)
       {
           JOptionPane.showMessageDialog(this,
                                         "û��ѡ������,�޷����棡",
                                         "���Է���",
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