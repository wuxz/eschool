package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.dc.eschool.system.CourseIF;
import com.dc.eschool.system.CourseInfo;
import com.dc.eschool.systemControl.SystemControl;


/**
 * Title:        课程信息
 * Description:  这是课程信息的浏览界面
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public class CourseDialog extends JDialog {
  JPanel panel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JTextField tfName = new JTextField();
  JTextField tfInfo = new JTextField();
  JTextField tfStartDate = new JTextField();
  JTextField tfEndDate = new JTextField();
  JTextField tfState = new JTextField();
  JButton btnClose = new JButton();
  GridBagLayout gridBagLayout1 = new GridBagLayout();

  /**  课程信息接口  */
  private CourseIF        ICourse;
  /**   SystemControl */
  private SystemControl   systemControl;

  public CourseDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public CourseDialog()
  {
    this(null, "", false);
  }
  /**   构造器  */
  public CourseDialog(Frame frame,SystemControl systemControl)
  {
     this(frame, "", false);
     this.systemControl=systemControl;
     if (systemControl.ICourse==null)
     {
        this.ICourse=new CourseInfo(systemControl);
        systemControl.ICourse=this.ICourse;
     }
     else
     {
         ICourse=systemControl.ICourse;
     }
     init();
  }
  void jbInit() throws Exception {
    panel1.setLayout(gridBagLayout1);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("课程信息");
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 19));
    jLabel1.setText("课程信息");
    jLabel2.setText("名称");
    jLabel3.setText("说明");
    jLabel4.setText("开始时间");
    jLabel5.setText("结束时间");
    jLabel6.setText("状态");
    btnClose.setText("退出");
    btnClose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnClose_actionPerformed(e);
      }
    });
    tfName.setEnabled(false);
    tfInfo.setEnabled(false);
    tfStartDate.setEnabled(false);
    tfEndDate.setEnabled(false);
    tfState.setEnabled(false);
    getContentPane().add(panel1);
    panel1.add(jLabel1,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(24, 63, 0, 148), 0, 0));
    panel1.add(jLabel3,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(30, 22, 0, 25), 0, 0));
    panel1.add(jLabel4,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(30, 22, 0, 0), 0, 0));
    panel1.add(jLabel5,  new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(29, 22, 0, 0), 0, 0));
    panel1.add(jLabel6,  new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(30, 22, 0, 25), 0, 0));
    panel1.add(jLabel2,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(24, 22, 0, 25), 0, 0));
    panel1.add(tfInfo,  new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(27, 24, 0, 29), 173, 0));
    panel1.add(tfName,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(21, 24, 0, 29), 173, 0));
    panel1.add(tfStartDate,  new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(27, 24, 0, 29), 173, 0));
    panel1.add(tfEndDate,  new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(26, 24, 0, 29), 173, 0));
    panel1.add(tfState,  new GridBagConstraints(1, 5, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(27, 24, 0, 29), 173, 0));
    panel1.add(btnClose,  new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 73, 22, 135), 0, 0));
  }

  void btnClose_actionPerformed(ActionEvent e)
  {
      this.dispose();
  }

  private void init()
  {
      String temp;
      String courseID=null;
      courseID=systemControl.getCourseID();
      if (courseID==null)
      {
         systemControl.writeLog("在取课程信息时,课程ID为空,故没有取到任何信息！");
         return;
      }
      temp=ICourse.getCourseName(courseID);
      tfName.setText(temp);
      temp=ICourse.getStartDate(courseID);
      tfStartDate.setText(temp);
      temp=ICourse.getEndDate(courseID);
      tfEndDate.setText(temp);
      temp=ICourse.getCourseInfo(courseID);
      tfInfo.setText(temp);
      temp=ICourse.getCourseState(courseID);
      tfState.setText(temp);
  }
}