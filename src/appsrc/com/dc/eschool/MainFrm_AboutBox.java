package com.dc.eschool;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * Title:        关于的对话框。
 * Description:  老师利用该界面给学生分配考题。
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */


public class MainFrm_AboutBox extends JDialog implements ActionListener
{
  //String product = "\u8bfe\u5802\u4ea4\u6d41";
  //String version = "1.0";
  //String copyright = "Copyright (c) 2001";
  //String comments = "";
  JButton aboutBtn = new JButton();
  ImageIcon aboutIcon = new ImageIcon( MainFrm_AboutBox.class.getResource("about.gif") );
  public MainFrm_AboutBox(Frame parent)
  {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    pack();
  }
  /**Component initialization*/
  private void jbInit() throws Exception
  {
    aboutBtn.addActionListener(this);
    //button1.setText("关闭");
    //aboutBtn.setPressedIcon( aboutIcon);
    aboutBtn.setBounds(0,0,0,0);
        aboutBtn.setBorder(null);
    aboutBtn.setIcon(aboutIcon);
    //imageLabel.setIcon(new ImageIcon(MainFrm_AboutBox.class.getResource("[Your Image]")));
    this.setTitle("关于 \u8bfe\u5802\u4ea4\u6d41");
    this.setSize(500,346);
    setResizable(false);
    this.getContentPane().add(aboutBtn, BorderLayout.CENTER);
  }
  /**Overridden so we can exit when window is closed*/
  protected void processWindowEvent(WindowEvent e)
  {
    if (e.getID() == WindowEvent.WINDOW_CLOSING)
    {
      cancel();
    }
    super.processWindowEvent(e);
  }
  /**Close the dialog*/
  void cancel()
  {
    dispose();
  }
  /**Close the dialog on a button event*/
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == aboutBtn)
    {
      cancel();
    }
  }
}