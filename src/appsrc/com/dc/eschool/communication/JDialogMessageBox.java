package com.dc.eschool.communication;

import java.awt.*;
import javax.swing.*;
import com.dc.eschool.systemControl.SystemControl;
import java.awt.event.*;
import com.borland.jbcl.layout.*;

/**
 * Title:        课堂交流
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public class JDialogMessageBox extends JDialog
{
  JPanel panel1 = new JPanel();
  JLabel jLabelMessage = new JLabel();
  JButton jButton1 = new JButton();
  public String result="0";
  public JDialogMessageBox(Frame frame, String title, boolean modal)
  {
    super(frame, title, modal);
    try
    {
      jbInit();
      pack();
      centerScreen();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public JDialogMessageBox(String message,SystemControl sysControl)
  {
    this(sysControl.mainFrm, "提示信息：", false);
    this.jLabelMessage.setText("       "+message);
  }
  public JDialogMessageBox(String message,SystemControl sysControl,String result)
  {
    this(sysControl.mainFrm, "提示信息：", false);
    this.jLabelMessage.setText("         "+message);
    this.result=result;
  }
  void jbInit() throws Exception
  {
    panel1.setLayout(null);
    jButton1.setText("确定");
    jButton1.setBounds(new Rectangle(59, 44, 76, 21));
    jButton1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton1_actionPerformed(e);
      }
    });
    jLabelMessage.setToolTipText("");
    jLabelMessage.setBounds(new Rectangle(19, 4, 159, 34));
    this.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        this_windowClosing(e);
      }
    });
    getContentPane().add(panel1);
    panel1.add(jButton1, null);
    panel1.add(jLabelMessage, null);
  }

  void jButton1_actionPerformed(ActionEvent e)
  {
      if(this.result.equals("1"))
          System.exit(0);
      else
          this.dispose();

  }
    public void centerScreen()
    {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - abounds.width) / 2,(dim.height - abounds.height) / 2);
    }

  void this_windowClosing(WindowEvent e)
  {
      if(this.result.equals("1"))
          System.exit(0);
      else
          this.dispose();
  }
}