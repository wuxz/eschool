package com.dc.eschool;

import java.awt.*;
import javax.swing.*;

/**
 * Title:         黑色的窗体
 * Description:   用来阻止学生的各种操作
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class DarkFrm extends JWindow
{
  BorderLayout borderLayout1 = new BorderLayout();



  public DarkFrm()
  {
      super();
  }

  void jbInit() throws Exception
  {
    this.getContentPane().setBackground(Color.black);
    this.getContentPane().setLayout(borderLayout1);
  }
  /**   关闭窗口  */
  public void closeWindow()
  {
      this.dispose();
  }

  public void paint(Graphics g)
  {
      super.paint(g);
      g.setColor(Color.black);
      g.fillRect(0,0,this.getWidth(),this.getHeight());
  }
}