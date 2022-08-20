package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.inspection.InspectionIF;
import com.dc.eschool.inspection.Inspection;

/**
 * Title:        老师点名时，学生端弹出的窗口。
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public class CheckOnWorkFrm extends JDialog
{
  /**   SystemControl实例  */
  SystemControl    systemControl=null;
  /**   监视接口    */
  InspectionIF     IInspection;
  /**     是否是老师 */
  boolean          teacherOrNot;

  JPanel panel1 = new JPanel();
  JLabel lbContent = new JLabel();
  JButton btnConfirm = new JButton();
  GridBagLayout gridBagLayout1 = new GridBagLayout();

  public CheckOnWorkFrm(Frame frame, String title, boolean modal)
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

  public CheckOnWorkFrm()
  {
    this(null, "", false);
  }

  /**   自定义构造器  */
  public CheckOnWorkFrm(boolean teacherOrNot,SystemControl systemControl,String strTalk)
  {
      this(null, "", false);
      this.systemControl=systemControl;
      this.teacherOrNot=teacherOrNot;
      IInspection=new Inspection(systemControl);
      lbContent.setText(strTalk);
  }

  void jbInit() throws Exception
  {
    panel1.setLayout(gridBagLayout1);
    btnConfirm.setText("确定");
    btnConfirm.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnConfirm_actionPerformed(e);
      }
    });
    lbContent.setText("你好");
    this.setResizable(false);
    this.setTitle("注意");
    getContentPane().add(panel1);
    panel1.add(lbContent,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(13, 55, 0, 36), 202, 15));
    panel1.add(btnConfirm,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(23, 116, 14, 137), 0, 0));
  }

  void btnConfirm_actionPerformed(ActionEvent e)
  {
      if (teacherOrNot==true)
      {
      }
      else
      {
          IInspection.alreadyCome(systemControl.getUserID());
      }
      this.dispose();
  }
  public void changeContent(String content)
  {
      lbContent.setText(content);
  }
}