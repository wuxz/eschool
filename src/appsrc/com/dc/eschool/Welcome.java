package com.dc.eschool;

import javax.swing.*;
import java.awt.*;


/**
 * Title:        课堂交流
 * Description:  “欢迎”快速闪动画面
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author : Robin Liu
 * @version 1.0
 */

public class Welcome extends JWindow implements Runnable
{
  JLabel jLabel1 = new JLabel( new ImageIcon( Welcome.class.getResource("about.gif") ));

  public Welcome()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception
  {
    this.setSize(500,346);
    jLabel1.setBorder(BorderFactory.createLineBorder(Color.black));
    this.getContentPane().add(jLabel1, BorderLayout.CENTER);
  }

  public void run()
  {
    this.pack();
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize();
    if (frameSize.height > screenSize.height)
    {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width)
    {
      frameSize.width = screenSize.width;
    }
    setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    setVisible(true);
  }

  public void stop()
  {
    setVisible(false);
    jLabel1 = null;
  }
}