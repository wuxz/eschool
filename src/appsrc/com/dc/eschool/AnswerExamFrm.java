package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;

import com.dc.eschool.exam.ExamIF;
import com.dc.eschool.exam.Examination;
import com.dc.eschool.DataModel.AnswerExamItemModel;
import com.dc.eschool.systemControl.SystemControl;
import com.borland.jbcl.control.*;

/**
 * Title:         答题
 * Description:   学生调用该界面回答老师的试题
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class AnswerExamFrm extends JDialog {

  private  static     final          String    SELECTTYPE="选择题";
  /**   私有变量   */
  private  static     int            columnCount=3;

  /**  SystemControl 类  */
  private  SystemControl            systemControl;
  /**  考试接口*/
  private  ExamIF                    IExam;
  /**    用户ID */
  private  String                    userID;
  /**    课程ID  */
  private  String                    courseID;
  /**   答题表模型 */
  private  AnswerQuestionTableModel  anawerTableModel;


  JButton btnSave = new JButton();
  JTable tbAnswerExam = null;
  JPanel leftButtonGroupPanel = new JPanel();
  JPanel rightButtonGroupPanel = new JPanel();
  JPanel centerButtonGroupPanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel buttonPanel = new JPanel();
  JButton btnHandin = new JButton();
  JButton btnRestartDoc = new JButton();
  JPanel answerPaperPanel = new JPanel();
  GroupBox answerPaperGroupBox = new GroupBox();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel topPanel = new JPanel();


  public AnswerExamFrm(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public AnswerExamFrm()
  {
    this(null, "", false);
  }

  /**  构造器 */
  public AnswerExamFrm(SystemControl systemControl)
  {
     this(systemControl.mainFrm, "", false);
     this.systemControl=systemControl;
     init();
  }

  void jbInit() throws Exception {
    btnSave.setText("保存");
    btnSave.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        btnSave_mouseMoved(e);
      }
    });
    btnSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnSave_actionPerformed(e);
      }
    });
    this.setTitle("答题");
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    buttonPanel.setLayout(borderLayout2);
    btnHandin.setText("交卷");
    btnHandin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        btnHandin_mouseMoved(e);
      }
    });
    btnHandin.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnHandin_actionPerformed(e);
      }
    });
    btnRestartDoc.setText("重新调入试题");
    btnRestartDoc.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnRestartDoc_actionPerformed(e);
      }
    });
    answerPaperGroupBox.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        answerPaperGroupBox_actionPerformed(e);
      }
    });
    answerPaperPanel.setLayout(borderLayout1);
    answerPaperGroupBox.setLabel("答题纸");
    answerPaperGroupBox.setLayout(borderLayout3);
    this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    buttonPanel.add(leftButtonGroupPanel, BorderLayout.WEST);
    leftButtonGroupPanel.add(btnRestartDoc, null);
    buttonPanel.add(rightButtonGroupPanel, BorderLayout.EAST);
    rightButtonGroupPanel.add(btnHandin, null);
    rightButtonGroupPanel.add(btnSave, null);
    buttonPanel.add(centerButtonGroupPanel, BorderLayout.CENTER);
    this.getContentPane().add(answerPaperPanel,  BorderLayout.CENTER);
    answerPaperPanel.add(answerPaperGroupBox, BorderLayout.CENTER);
    answerPaperGroupBox.add(jScrollPane1,  BorderLayout.CENTER);
    this.getContentPane().add(topPanel, BorderLayout.NORTH);
    this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

  }

  /**  界面的初始化 */
  private void init()
  {
     courseID=systemControl.getCourseID();
     anawerTableModel=new AnswerQuestionTableModel();
     anawerTableModel.refreshData();
     /**    构造表格  */
     tbAnswerExam=new JTable(anawerTableModel);
     tbAnswerExam.setDefaultRenderer(AnswerExamItemModel.class,new CheckTableCellRender());
     tbAnswerExam.setDefaultEditor(AnswerExamItemModel.class,
                                   new CheckBoxEditor(new CheckPanel(),
                                                      new JCheckBox()));
     TableColumn tc = tbAnswerExam.getColumn("标记");
     tc.setMaxWidth(50);
     tc.setMinWidth(50);
     tc.setPreferredWidth(0);
     tc = tbAnswerExam.getColumn("题号");
     tc.setMaxWidth(50);
     tc.setMinWidth(50);
     tc.setPreferredWidth(0);
     tbAnswerExam.setRowHeight(20);
     jScrollPane1.getViewport().add(tbAnswerExam, null);
  }

  /** 交卷 */
  void btnHandin_actionPerformed(ActionEvent e)
  {
      handin();
      this.dispose();
  }

  /** 保存答题纸 */
  void btnSave_actionPerformed(ActionEvent e)
  {
      tbAnswerExam.repaint();
      Hashtable  htAnswerItem;
      htAnswerItem=anawerTableModel.getAnswerItems();
      IExam.saveExamAnsweerPaper(userID,courseID,htAnswerItem);
  }
  public void handin()
  {
      Hashtable  htAnswerItem;
      /**   保存试题  */
      htAnswerItem=anawerTableModel.getAnswerItems();
      System.out.println(htAnswerItem.toString());
      IExam.saveExamAnsweerPaper(userID,courseID,htAnswerItem);
      String userID=systemControl.getUserID();
      try
      {
          IExam.notifyHandin(userID);
          IExam.closeWordDoc();
      }
      catch (Exception evt)
      {
          systemControl.writeLog("在交卷时,通知老师失败！");
      }
  }
  /**  鼠标移动   */
  void btnHandin_mouseMoved(MouseEvent e)
  {
      TableCellEditor cellEdit=tbAnswerExam.getCellEditor();
      if (cellEdit!=null)
      {
          cellEdit.stopCellEditing();
      }
  }
  /**  鼠标移动   */
  void btnSave_mouseMoved(MouseEvent e)
  {
      btnHandin_mouseMoved(e);
  }
  /**
   * 自定义的表模型
   */
  class AnswerQuestionTableModel extends AbstractTableModel
  {
       /**  答题项  */
       private AnswerExamItemModel answerExamItemModel=null;
       //数据
       private Vector              data=new Vector();
       //表头数据
       private Object[]            columnNames={"标记","题号","答案"};;
       //哈西表
       private Hashtable           htData=new Hashtable();

       /**   构造器   */
       public AnswerQuestionTableModel()
       {
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
          answerExamItemModel=(AnswerExamItemModel)data.get(row);
          switch (col)
          {
             case 0:
                 tempObject=answerExamItemModel.getMark();
                 break;
             case 1:
                 tempObject=answerExamItemModel.getExamCode();
                 break;
             case 2:
                 tempObject=answerExamItemModel;
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
       /**  设置列的可编辑性   */
       public boolean isCellEditable(int row, int col)
       {
           if (col==1)
              return false;
           else
              return true;
       }
       /**  设置指定位置的指 */
       public void setValueAt(Object aValue, int row, int col)
       {
           answerExamItemModel=(AnswerExamItemModel)data.get(row);
           switch (col)
           {
              case 0:
                  answerExamItemModel.setMark((Boolean)aValue);
                  break;
              case 1:
                  break;
              case 2:
                  if (((AnswerExamItemModel)data.get(row)).getAnswerType().equals(SELECTTYPE))
                  {
                      answerExamItemModel.setAnswerKey(((CheckPanel)aValue).getCbsState());
                  }
                  else
                  {
                      answerExamItemModel.setAnswerKey(((CheckPanel)aValue).getTextFieldState());
                  }
                  break;
           }
           data.set(row,answerExamItemModel);
           this.fireTableDataChanged();
       }
       /** 将数据填充到哈西表中  */
       public Hashtable getAnswerItems()
       {
           AnswerExamItemModel tempModel;
           for(int i=0;i<data.size();i++)
           {
               tempModel=(AnswerExamItemModel)data.get(i);
               htData.put(tempModel.getExamCode(),tempModel.getAnswerKey());
           }
           return htData;
       }
       /**   获取数据  */
       public void refreshData()
       {
           AnswerExamItemModel tempModel=null;
           userID=systemControl.getUserID();
           if (systemControl.IExam==null)
           {
                IExam=new Examination(systemControl);
                systemControl.IExam=IExam;
           }
           else
           {
                IExam=systemControl.IExam;
            }
           data.clear();
           String courseID=systemControl.getCourseID();
           Collection questionInfo=IExam.getQuestionInfo(userID,courseID);
           Iterator oneInfo=questionInfo.iterator();
           while (oneInfo.hasNext())
           {
               tempModel=(AnswerExamItemModel)oneInfo.next();
               data.addElement(tempModel);
           }
           this.fireTableDataChanged();
       }
  }
  /**  表的渲染器  */
  class CheckTableCellRender extends JPanel
                             implements TableCellRenderer
  {
      public Component getTableCellRendererComponent(JTable table,
                                                     Object value,
                                                     boolean isSelected,
                                                     boolean hasFocus,
                                                     int row,
                                                     int column)


      {
       	  this.removeAll();
     	  this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
          if (value!=null)
          {
     	      JTextField tf=new JTextField(((AnswerExamItemModel)value).getAnswerKey());
              this.add(tf);
          }
          return this;
      }


  }
  /**  表格的编辑器 */
  class CheckBoxEditor  extends  DefaultCellEditor
  {
      CheckPanel panel=null;
      /**   构造器  */
      public CheckBoxEditor(CheckPanel checkPanel,JCheckBox checkBox)
      {
    	  super(checkBox);
          this.panel=checkPanel;
      }
      /**  获得编辑工具  */
      public Component getTableCellEditorComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   int row,
                                                   int column)
      {
           panel.removeAll();
           if (value==null)
               return  panel;
           panel.resetPanel(((AnswerExamItemModel)value).getAnswerType(),
                            ((AnswerExamItemModel)value).getItemCount());
           if (((AnswerExamItemModel)value).getAnswerType().equals(SELECTTYPE))
               panel.setCbsState(((AnswerExamItemModel)value).getAnswerKey());
           else
               panel.setTextFieldState(((AnswerExamItemModel)value).getAnswerKey());
           return panel;
      }

      public Object getCellEditorValue()
      {
          return panel;
      }

  }
  /**    包含复选框的面板  */
  class CheckPanel extends JPanel
  {
      private Vector       cbs=new Vector();
      private JCheckBox    cb;
      private JTextField   tf;
      private String[]     chars={"A","B","C","D","E","F","G","H","I","J"};
      /**   构造器   */
      public  CheckPanel()
      {
      }
      /**   重置面板  */
      public  void resetPanel(String questionType,int count)
      {
          this.removeAll();
          setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
          if (questionType.equals(SELECTTYPE))
          {
              cbs.clear();
              for (int i=0;i<count;i++)
              {
                 cb=new JCheckBox(chars[i]);
                 cb.setSelected(false);
                 cbs.add(cb);
                 add(cb);
               }
           }
           else
           {
               tf=new JTextField();
               add(tf);
           }
      }
      /**  设置复选框的状态 */
      public void setCbsState(String state)
      {
           String strTemp;
           int    intTemp;
           for(int i=0;i<state.length();i++)
           {
               for(int j=0;j<chars.length;j++)
               {
                   strTemp=state.substring(i,i+1);
                   if (strTemp.equals(chars[j]))
                   {
                        ((JCheckBox)cbs.get(j)).setSelected(true);
                   }
               }
           }
      }
      /**  取得夫选框的状态  */
      public String getCbsState()
      {
          String strTemp="";
          for(int i=0;i<cbs.size();i++)
          {
              if (((JCheckBox)cbs.get(i)).isSelected())
              {
                  strTemp=strTemp+chars[i];
              }
          }
          return strTemp;
      }
      /**   设置编辑框的状态  */
      public void setTextFieldState(String content)
      {
          tf.setText(content);
      }
      /**   获取编辑框的状态  */
      public String getTextFieldState()
      {
          return tf.getText();
      }
  }
  /**  重新启动文档  */
  void btnRestartDoc_actionPerformed(ActionEvent e)
  {
      try
      {
          RestartDoc restartDoc=new RestartDoc(systemControl);
          restartDoc.start();
      }
      catch (Exception evt)
      {
          systemControl.writeLog("重新启动Word文档失败！");
      }
  }
  /**  下载试题线程  */
  class RestartDoc extends Thread
  {
      /**   考试接口       */
      private   ExamIF         IExam;
      /**   SystemControl */
      private   SystemControl  systemControl;

      public RestartDoc(SystemControl systemControl)
      {
          this.systemControl=systemControl;
          if (systemControl.IExam==null)
          {
              IExam=new Examination(systemControl);
              systemControl.IExam=IExam;
          }
          else
          {
              IExam=systemControl.IExam;
          }
      }
      public void run()
      {
          try
          {
              IExam.restartWordDoc();
          }
          catch (Exception e)
          {
              JOptionPane.showMessageDialog(null,
                                            "重新打开试题失败！",
                                            "考试",
                                            JOptionPane.INFORMATION_MESSAGE);
          }
      }
  }
  //垃圾
  void answerPaperGroupBox_actionPerformed(ActionEvent e)
  {

  }

}