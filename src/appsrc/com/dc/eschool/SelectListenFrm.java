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
 * Title:        选择听力的窗体
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public class SelectListenFrm extends JDialog
{
  /**  SystemControl实例        */
  private SystemControl systemControl;
  /**  ListenIF 接口            */
  private ListenIF      IListen=null;
  /**  JMF基础类实例             */
  private JMFAPI        jmfAPI=null;
  /**  ExerciseTableModel表模型  */
  private ExerciseTableModel exerciseTableModel;
  /**  SnippetTableModel表模型   */
  private SnippetTableModel  snippetTableModel;
  /**   保存听力练习ID的矢量       */
  private Vector          exerciseID=new Vector();
  /**   听力练习ID               */
  private String          projectID=null;
  /**   听力片断ID               */
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
  /**  自定义构造器  */
  public SelectListenFrm(Frame frame,SystemControl systemControl)
  {
    this(frame, "", false);
    this.systemControl=systemControl;
    init();
  }
  void jbInit() throws Exception
  {
    border3 = BorderFactory.createCompoundBorder(new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"听力练习列表"),BorderFactory.createEmptyBorder(4,6,6,6));
    border4 = BorderFactory.createCompoundBorder(new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"听力片断列表"),BorderFactory.createEmptyBorder(4,6,6,6));
    btnSelect.setText("备选");
    btnSelect.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnSelect_actionPerformed(e);
      }
    });
    btnConfirm.setText("确定");
    btnConfirm.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnConfirm_actionPerformed(e);
      }
    });

    this.setTitle("选择听力");

    buttonPanel.setLayout(borderLayout3);
    btnCancel.setText("取消");
    btnCancel.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnCancel_actionPerformed(e);
      }
    });
    btnTestListen.setToolTipText("");
    btnTestListen.setText("试听");
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
  /**   界面的初始化   */
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
      TableColumn tcl=tbExercise.getColumn("练习ID");
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
      TableColumn tc=tbSnippet.getColumn("片断ID");
      tbSnippet.removeColumn(tc);
      jScrollPane1.getViewport().add(tbExercise, null);
      jScrollPane2.getViewport().add(tbSnippet, null);
  }
  /**  试听   */
  void btnTestListen_actionPerformed(ActionEvent e)
  {
      String audioPort=null;
      if (snippetID==null)
      {
          showMessageDialog("请先选择听力片断,然后再试听。","选择听力");
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
              showMessageDialog("无效听力文件！","选择听力");
              return;
          }
          jmfAPI.audioRecieve(IPAddress,audioPort);
      }
      catch (Exception evt)
      {
          return;
      }
  }
  /**  备选   */
  void btnSelect_actionPerformed(ActionEvent e)
  {
      if (projectID==null)
      {
          showMessageDialog("没有选择任何练习！","选择听力");
          return;
      }
      if (exerciseID.contains(projectID))
      {
          showMessageDialog("你已经选择了该练习！","选择听力");
          return;
      }
      exerciseID.addElement(projectID);
  }
  /**  保存至数据库  */
  void btnConfirm_actionPerformed(ActionEvent e)
  {
      if (exerciseID.size()==0)
      {
          showMessageDialog("你没有选择任何听力练习","选择听力");
      }
      else
      {
          IListen.saveListenExerciseInfo(exerciseID);
      }
      this.dispose();
  }
  /**  取消退出   */
  void btnCancel_actionPerformed(ActionEvent e)
  {
      this.dispose();
  }
  /**  听力练习表格的单击事件  */
  void tbExercise_mousePressed(MouseEvent e)
  {
      /**   行号  */
      int row=0;
      row=tbExercise.getSelectedRow();
      /*  获取考试ID  */
      projectID=(String)tbExercise.getModel().getValueAt(row,0);
      snippetTableModel.resetData(projectID);
  }
  /**  听力片断表格的单击     */
  void tbSnippet_mouseClicked(MouseEvent e)
  {
      /**   行号  */
      int row=0;
      row=tbSnippet.getSelectedRow();
      /*  获取考试ID  */
      snippetID=(String)tbSnippet.getModel().getValueAt(row,0);
  }
  /**  显示提示框  */
  private void showMessageDialog(String messageContent,String messageTitle)
  {
      JOptionPane.showMessageDialog(this,
                                    messageContent,
                                    messageTitle,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null);
  }
  /**  练习标模式  */
  class ExerciseTableModel extends AbstractTableModel
  {
      private Vector              data=new Vector();
      private Object[]            columnNames={"练习ID","练习名称","时间","说明"};
      private ListenExerciseModel listenExerciseModel=null;
      public ExerciseTableModel()
      {
          String   courseID=null;
          courseID=systemControl.getCourseID();
          Collection listenExerciseInfo=null;
          listenExerciseInfo=IListen.getListenExerciseInfo(courseID);
          if (listenExerciseInfo==null)
          {
              systemControl.writeLog("在选择听力时,数据库连接出现问题,无法获得听力练习信息！");
              showMessageDialog("没有听力练习信息！","选择听力");
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
      /**  是否可编辑  */
      public boolean isCellEditable(int x, int y)
      {
          return false;
      }
       /**   获得列数    */
      public int getColumnCount()
      {
          return columnNames.length;
      }
      /**   获得指定位置的值   */
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
      /**   获得行数    */
      public int getRowCount()
      {
          return data.size();
      }
      /**    获得列名 */
      public String getColumnName(int col)
      {
          return (String)columnNames[col];
      }
      /**  获得列的类型  */
      public Class getColumnClass(int col)
      {
          return getValueAt(0,col).getClass();
      }
  }
  /**  片断表模式  */
  class SnippetTableModel extends AbstractTableModel
  {
      private Vector             data=new Vector();
      private Object[]           columnNames={"片断ID","片断名称","说明"};
      private ListenSnippetModel listenSnippetModel;
      /**  构造器  */
      public SnippetTableModel()
      {
      }
      /**  是否可编辑  */
      public boolean isCellEditable(int x, int y)
      {
          return false;
      }
       /**   获得列数    */
      public int getColumnCount()
      {
          return columnNames.length;
      }
      /**   获得指定位置的值   */
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
      /**   获得行数    */
      public int getRowCount()
      {
          return data.size();
      }
      /**    获得列名 */
      public String getColumnName(int col)
      {
          return (String)columnNames[col];
      }
      /**  获得列的类型  */
      public Class getColumnClass(int col)
      {
          return getValueAt(0,col).getClass();
      }
      /**  重设数据*/
      public void resetData(String projectID)
      {
          Collection listenSnippetInfo=null;
          data.removeAllElements();
          listenSnippetInfo=IListen.getListenSnippetInfo(projectID);
          if (listenSnippetInfo==null)
          {
              systemControl.writeLog("在选择听力时,数据库连接出现问题,无法获得听力练习信息！");
              showMessageDialog("没有听力片断信息！","选择听力");
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