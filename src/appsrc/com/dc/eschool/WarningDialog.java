package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.Vector;

import com.dc.eschool.inspection.*;
import com.dc.eschool.systemControl.SystemControl;

/**
 * Title:        警告
 * Description:  该窗体是老师用来给学生发送警告信息的
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class WarningDialog extends JDialog {

  SystemControl    systemControl;
  InspectionIF     IInspection;
  String           userID;
  Vector           studentID=null;

  JPanel panel1 = new JPanel();
  JTextArea taWarn = new JTextArea();
  Border border1;
  JLabel jLabel1 = new JLabel();
  JButton btnSend = new JButton();
  JButton btnCancel = new JButton();
  JScrollPane jScrollPane1 = new JScrollPane();
  GridBagLayout gridBagLayout1 = new GridBagLayout();

  public WarningDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public WarningDialog()
  {
    this(null, "", false);
  }
  /**   构造器1  */
  public WarningDialog(Frame frame,String userID,SystemControl systemControl)
  {
      this(frame, "", false);
      this.systemControl=systemControl;
      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }
      this.userID=userID;
      this.studentID=null;
  }
  /**   构造器   */
  public WarningDialog(Frame frame,SystemControl systemControl,Vector studentID)
  {
      this(frame, null,systemControl);
      this.studentID=studentID;
  }

  void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(178, 178, 178),new Color(124, 124, 124));
    panel1.setLayout(gridBagLayout1);
    taWarn.setBorder(border1);
    taWarn.setLineWrap(true);
    taWarn.setRows(10);
    jLabel1.setText("警告信息");
    btnSend.setText("发送");
    btnSend.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnSend_actionPerformed(e);
      }
    });
    btnCancel.setText("取消");
    btnCancel.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnCancel_actionPerformed(e);
      }
    });
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("警告");
    getContentPane().add(panel1);
    panel1.add(jLabel1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(9, 20, 0, 0), 85, 6));
    panel1.add(jScrollPane1,  new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 20, 0, 14), 15, -50));
    panel1.add(btnCancel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(6, 47, 8, 48), 0, 0));
    panel1.add(btnSend, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(6, 69, 8, 0), 0, 0));
    jScrollPane1.getViewport().add(taWarn, null);
  }
  /**   发送消息  */
  void btnSend_actionPerformed(ActionEvent e)
  {
      if (studentID==null)
      {
          IInspection.warnStudent(userID,taWarn.getText());
      }
      else
      {
          IInspection.warnStudent(studentID,taWarn.getText());
      }
      this.dispose();
  }
  /**   取消发送   */
  void btnCancel_actionPerformed(ActionEvent e)
  {
       this.dispose();
  }
}