package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Title:         显示学生屏幕的窗口
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public class DrawImageFrm extends JDialog
{
  /**    Image  */
  ImageIcon   image=null;

  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JButton btnClose = new JButton();
  JScrollPane jScrollPane1 = new JScrollPane();
  JLabel lbImage = new JLabel();

  public DrawImageFrm(Frame frame, String title, boolean modal)
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

  public DrawImageFrm()
  {
    this(null, "", false);
  }
  /**   构造器  */
  public DrawImageFrm(ImageIcon image)
  {
      this(null, "", false);
      this.image=image;
      lbImage.setIcon(image);
  }

  void jbInit() throws Exception
  {
    panel1.setLayout(borderLayout1);
    btnClose.setText("退出");
    btnClose.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnClose_actionPerformed(e);
      }
    });
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("学生桌面");
    getContentPane().add(panel1);
    panel1.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(btnClose, null);
    panel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(lbImage, null);
  }

  void btnClose_actionPerformed(ActionEvent e)
  {
      this.dispose();
  }
}