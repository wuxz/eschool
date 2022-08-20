package com.dc.eschool;

import java.awt.*;
import javax.swing.*;

/**
 * Title:        �ȴ��Ի���
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public class WaitFrm extends JWindow implements Runnable
{

  public WaitFrm()
  {
      super();
  }


  public void paint(Graphics g)
  {
      super.paint(g);
      g.setColor(Color.black);
      g.fillRect(0,0,this.getWidth(),this.getHeight());
      Font font=new Font("����",Font.PLAIN,14);
      g.setColor(Color.white);
      g.setFont(font);
      g.drawString("ϵͳæ,��ȴ�...",5,5);
  }

  public void run()
  {
    this.pack();
    this.setSize(139, 26);
    //Center the window
    Dimension dlgSize = getSize();
    Dimension frmSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation((frmSize.width - dlgSize.width) / 2 , (frmSize.height - dlgSize.height) / 2 );
    show();
  }

  public void stop()
  {
    setVisible(false);
  }
}