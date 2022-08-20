package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.event.*;
import java.net.URL;
import java.net.InetAddress;
import javax.swing.table.TableColumn;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.listen.Listen;
import com.dc.eschool.listen.ListenIF;
import com.dc.eschool.DataModel.ListenExerciseModel;
import com.dc.eschool.DataModel.ListenSnippetModel;
import com.dc.eschool.util.JMFAPI;

/**
 * Title:        ѡ�������Ĵ���
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public class SelectListenFrm extends JDialog
{
  /**  SystemControlʵ��        */
  private SystemControl systemControl;
  /**  ListenIF �ӿ�            */
  private ListenIF      IListen=null;
  /**  JMF������ʵ��             */
  private JMFAPI        jmfAPI=null;
  /**  ExerciseTableModel��ģ��  */
  private ExerciseTableModel exerciseTableModel;
  /**  SnippetTableModel��ģ��   */
  private SnippetTableModel  snippetTableModel;
  /**   ����������ϰID��ʸ��       */
  private Vector          exerciseID=new Vector();
  /**   ������ϰID               */
  private String          projectID=null;
  /**   ����Ƭ��ID               */
  private String          snippetID=null;

  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  TitledBorder titledBorder2;
  JButton btnSelect = new JButton();
  JButton btnConfirm = new JButton();
  JTable tbExercise = null;
  JTable tbSnippet = null;
  JPanel leftButtonGroupPanel = new JPanel();
  JPanel rightButtonGroupPanel = new JPanel();
  JPanel centerButtonGroupPanel = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel buttonPanel = new JPanel();
  JButton btnCancel = new JButton();
  JButton btnTestListen = new JButton();
  JPanel contentPanel = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel1 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane2 = new JScrollPane();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JPanel topPanel = new JPanel();
  Border border3;
  Border border4;

  public SelectListenFrm(Frame frame, String title, boolean modal)
  {
    super(frame, title, modal);
    try
    {
      jbInit();
      pack();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public SelectListenFrm()
  {
    this(null, "", false);
  }
  /**  �Զ��幹����  */
  public SelectListenFrm(Frame frame,SystemControl systemControl)
  {
    this(frame, "", false);
    this.systemControl=systemControl;
    init();
  }
  void jbInit() throws Exception
  {
    border3 = BorderFactory.createCompoundBorder(new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"������ϰ�б�"),BorderFactory.createEmptyBorder(4,6,6,6));
    border4 = BorderFactory.createCompoundBorder(new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"����Ƭ���б�"),BorderFactory.createEmptyBorder(4,6,6,6));
    btnSelect.setText("��ѡ");
    btnSelect.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnSelect_actionPerformed(e);
      }
    });
    btnConfirm.setText("ȷ��");
    btnConfirm.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnConfirm_actionPerformed(e);
      }
    });

    this.setTitle("ѡ������");

    buttonPanel.setLayout(borderLayout3);
    btnCancel.setText("ȡ��");
    btnCancel.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnCancel_actionPerformed(e);
      }
    });
    btnTestListen.setToolTipText("");
    btnTestListen.setText("����");
    btnTestListen.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnTestListen_actionPerformed(e);
      }
    });
    contentPanel.setLayout(gridLayout1);
    gridLayout1.setRows(2);
    gridLayout1.setColumns(1);
    gridLayout1.setHgap(15);
    gridLayout1.setVgap(15);
    jPanel1.setBorder(border3);
    jPanel1.setLayout(borderLayout1);
    jPanel2.setBorder(border4);
    jPanel2.setLayout(borderLayout2);
    this.getContentPane().add(contentPanel,  BorderLayout.CENTER);
    contentPanel.add(jPanel1, null);
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    contentPanel.add(jPanel2, null);
    jPanel2.add(jScrollPane2, BorderLayout.CENTER);
    this.getContentPane().add(buttonPanel,  BorderLayout.SOUTH);
    buttonPanel.add(leftButtonGroupPanel, BorderLayout.WEST);
    buttonPanel.add(rightButtonGroupPanel, BorderLayout.EAST);
    rightButtonGroupPanel.add(btnTestListen, null);
    rightButtonGroupPanel.add(btnSelect, null);
    rightButtonGroupPanel.add(btnConfirm, null);
    rightButtonGroupPanel.add(btnCancel, null);
    buttonPanel.add(centerButtonGroupPanel, BorderLayout.CENTER);
    this.getContentPane().add(topPanel, BorderLayout.NORTH);
  }
  /**   ����ĳ�ʼ��   */
  private void  init()
  {
      if (systemControl.IListen==null)
      {
          IListen=new Listen(systemControl);
          systemControl.IListen=IListen;
      }
      else
      {
          IListen=systemControl.IListen;
      }
      jmfAPI=new JMFAPI();
      exerciseTableModel=new ExerciseTableModel();
      tbExercise=new JTable(exerciseTableModel);
      TableColumn tcl=tbExercise.getColumn("��ϰID");
      tbExercise.removeColumn(tcl);
      tbExercise.addMouseListener(new java.awt.event.MouseAdapter()
      {
          public void mousePressed(MouseEvent e)
          {
              tbExercise_mousePressed(e);
          }
      });
      snippetTableModel=new SnippetTableModel();
      tbSnippet=new JTable(snippetTableModel);
      tbSnippet.addMouseListener(new java.awt.event.MouseAdapter()
      {
          public void mouseClicked(MouseEvent e)
          {
              tbSnippet_mouseClicked(e);
          }
      });
      TableColumn tc=tbSnippet.getColumn("Ƭ��ID");
      tbSnippet.removeColumn(tc);
      jScrollPane1.getViewport().add(tbExercise, null);
      jScrollPane2.getViewport().add(tbSnippet, null);
  }
  /**  ����   */
  void btnTestListen_actionPerformed(ActionEvent e)
  {
      String audioPort=null;
      if (snippetID==null)
      {
          showMessageDialog("����ѡ������Ƭ��,Ȼ����������","ѡ������");
          return;
      }
      String tempURL=IListen.getListenSnippetURL(snippetID);
      try
      {
          //InetAddress inetAddress=InetAddress.getLocalHost();
          //String IPAddress=inetAddress.getHostAddress();
          String IPAddress="224.1.1.1";
          audioPort=jmfAPI.audioTransmit(tempURL,IPAddress,false);
          if(audioPort==null)
          {
              showMessageDialog("��Ч�����ļ���","ѡ������");
              return;
          }
          jmfAPI.audioRecieve(IPAddress,audioPort);
      }
      catch (Exception evt)
      {
          return;
      }
  }
  /**  ��ѡ   */
  void btnSelect_actionPerformed(ActionEvent e)
  {
      if (projectID==null)
      {
          showMessageDialog("û��ѡ���κ���ϰ��","ѡ������");
          return;
      }
      if (exerciseID.contains(projectID))
      {
          showMessageDialog("���Ѿ�ѡ���˸���ϰ��","ѡ������");
          return;
      }
      exerciseID.addElement(projectID);
  }
  /**  ���������ݿ�  */
  void btnConfirm_actionPerformed(ActionEvent e)
  {
      if (exerciseID.size()==0)
      {
          showMessageDialog("��û��ѡ���κ�������ϰ","ѡ������");
      }
      else
      {
          IListen.saveListenExerciseInfo(exerciseID);
      }
      this.dispose();
  }
  /**  ȡ���˳�   */
  void btnCancel_actionPerformed(ActionEvent e)
  {
      this.dispose();
  }
  /**  ������ϰ���ĵ����¼�  */
  void tbExercise_mousePressed(MouseEvent e)
  {
      /**   �к�  */
      int row=0;
      row=tbExercise.getSelectedRow();
      /*  ��ȡ����ID  */
      projectID=(String)tbExercise.getModel().getValueAt(row,0);
      snippetTableModel.resetData(projectID);
  }
  /**  ����Ƭ�ϱ��ĵ���     */
  void tbSnippet_mouseClicked(MouseEvent e)
  {
      /**   �к�  */
      int row=0;
      row=tbSnippet.getSelectedRow();
      /*  ��ȡ����ID  */
      snippetID=(String)tbSnippet.getModel().getValueAt(row,0);
  }
  /**  ��ʾ��ʾ��  */
  private void showMessageDialog(String messageContent,String messageTitle)
  {
      JOptionPane.showMessageDialog(this,
                                    messageContent,
                                    messageTitle,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null);
  }
  /**  ��ϰ��ģʽ  */
  class ExerciseTableModel extends AbstractTableModel
  {
      private Vector              data=new Vector();
      private Object[]            columnNames={"��ϰID","��ϰ����","ʱ��","˵��"};
      private ListenExerciseModel listenExerciseModel=null;
      public ExerciseTableModel()
      {
          String   courseID=null;
          courseID=systemControl.getCourseID();
          Collection listenExerciseInfo=null;
          listenExerciseInfo=IListen.getListenExerciseInfo(courseID);
          if (listenExerciseInfo==null)
          {
              systemControl.writeLog("��ѡ������ʱ,���ݿ����ӳ�������,�޷����������ϰ��Ϣ��");
              showMessageDialog("û��������ϰ��Ϣ��","ѡ������");
          }
          else
          {
              Iterator iterator=listenExerciseInfo.iterator();
              while (iterator.hasNext())
              {
                  listenExerciseModel=(ListenExerciseModel)iterator.next();
                  data.addElement(listenExerciseModel);
              }
          }
      }
      /**  �Ƿ�ɱ༭  */
      public boolean isCellEditable(int x, int y)
      {
          return false;
      }
       /**   �������    */
      public int getColumnCount()
      {
          return columnNames.length;
      }
      /**   ���ָ��λ�õ�ֵ   */
      public Object getValueAt(int row, int col)
      {
          Object  tempObject=null;
          listenExerciseModel=(ListenExerciseModel)data.get(row);
          switch (col)
          {
             case 0:
                 tempObject=listenExerciseModel.getProjectID();
                 break;
             case 1:
                 tempObject=listenExerciseModel.getProjectName();
                 break;
             case 2:
                 tempObject=listenExerciseModel.getProjectDate();
                 break;
             case 3:
                 tempObject=listenExerciseModel.getProjectInfo();
                 break;
          }
          return tempObject;
      }
      /**   �������    */
      public int getRowCount()
      {
          return data.size();
      }
      /**    ������� */
      public String getColumnName(int col)
      {
          return (String)columnNames[col];
      }
      /**  ����е�����  */
      public Class getColumnClass(int col)
      {
          return getValueAt(0,col).getClass();
      }
  }
  /**  Ƭ�ϱ�ģʽ  */
  class SnippetTableModel extends AbstractTableModel
  {
      private Vector             data=new Vector();
      private Object[]           columnNames={"Ƭ��ID","Ƭ������","˵��"};
      private ListenSnippetModel listenSnippetModel;
      /**  ������  */
      public SnippetTableModel()
      {
      }
      /**  �Ƿ�ɱ༭  */
      public boolean isCellEditable(int x, int y)
      {
          return false;
      }
       /**   �������    */
      public int getColumnCount()
      {
          return columnNames.length;
      }
      /**   ���ָ��λ�õ�ֵ   */
      public Object getValueAt(int row, int col)
      {
          Object  tempObject=null;
          listenSnippetModel=(ListenSnippetModel)data.get(row);
          switch (col)
          {
             case 0:
                 tempObject=listenSnippetModel.getSnippetID();
                 break;
             case 1:
                 tempObject=listenSnippetModel.getSnippetName();
                 break;
             case 2:
                 tempObject=listenSnippetModel.getSnippetInfo();
                 break;
          }
          return tempObject;
      }
      /**   �������    */
      public int getRowCount()
      {
          return data.size();
      }
      /**    ������� */
      public String getColumnName(int col)
      {
          return (String)columnNames[col];
      }
      /**  ����е�����  */
      public Class getColumnClass(int col)
      {
          return getValueAt(0,col).getClass();
      }
      /**  ��������*/
      public void resetData(String projectID)
      {
          Collection listenSnippetInfo=null;
          data.removeAllElements();
          listenSnippetInfo=IListen.getListenSnippetInfo(projectID);
          if (listenSnippetInfo==null)
          {
              systemControl.writeLog("��ѡ������ʱ,���ݿ����ӳ�������,�޷����������ϰ��Ϣ��");
              showMessageDialog("û������Ƭ����Ϣ��","ѡ������");
          }
          else
          {
              Iterator iterator=listenSnippetInfo.iterator();
              while (iterator.hasNext())
              {
                  listenSnippetModel=(ListenSnippetModel)iterator.next();
                  data.addElement(listenSnippetModel);
              }
          }
          this.fireTableDataChanged();
      }
  }



}