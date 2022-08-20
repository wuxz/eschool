package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Title:        课堂影院
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class ClassCinemaFrm extends JDialog
{
  JPanel panel1 = new JPanel();
  JButton btnPause = new JButton();
  JButton btnPlay = new JButton();
  JButton btnStop = new JButton();
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenu1 = new JMenu();
  JMenu jMenu2 = new JMenu();
  JMenuItem jMenuItem2 = new JMenuItem();
  JMenuItem jMenuItem1 = new JMenuItem();
  JSlider jSlider1 = new JSlider(1,100,1);
  JPanel centerButtonGroupPanel = new JPanel();
  JPanel rightButtonGroupPanel = new JPanel();
  JPanel leftButtonGroupPanel = new JPanel();
  JPanel buttonPanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JButton btnPause1 = new JButton();
  JButton btnPlay1 = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JMenuItem jMenuItem3 = new JMenuItem();

  public ClassCinemaFrm(Frame frame, String title, boolean modal)
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

  public ClassCinemaFrm()
  {
    this(null, "", false);
  }
  void jbInit() throws Exception
  {
    ImageIcon imgPlay = new ImageIcon(com.dc.eschool.ClassCinemaFrm.class.getResource("play.gif"));
    ImageIcon imgPause = new ImageIcon(com.dc.eschool.ClassCinemaFrm.class.getResource("pause.gif"));
    ImageIcon imgStop = new ImageIcon(com.dc.eschool.ClassCinemaFrm.class.getResource("stopplay.gif"));
    panel1.setLayout(borderLayout1);
    btnPause.setToolTipText("");
    btnPause.setIcon(imgPause);
    jMenu1.setText("文件");
    jMenu2.setText("帮助");
    jMenuItem2.setText("打开...");
    jMenuItem1.setText("VOD...");
    this.setJMenuBar(jMenuBar1);
    this.setModal(true);
    this.setTitle("课堂影院");
    btnPlay.setIcon(imgPlay);
    btnStop.setIcon(imgStop);
    buttonPanel.setLayout(borderLayout2);
    btnPause1.setToolTipText("");
    jMenuItem3.setText("关于");
    jMenuItem3.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem3_actionPerformed(e);
      }
    });
    getContentPane().add(panel1,  BorderLayout.CENTER);
    jMenuBar1.add(jMenu1);
    jMenuBar1.add(jMenu2);
    jMenu1.add(jMenuItem2);
    jMenu1.add(jMenuItem1);
    panel1.add(jSlider1,  BorderLayout.CENTER);
    this.getContentPane().add(buttonPanel,  BorderLayout.SOUTH);
    buttonPanel.add(leftButtonGroupPanel, BorderLayout.WEST);
    leftButtonGroupPanel.add(btnPlay, null);
    leftButtonGroupPanel.add(btnPause, null);
    leftButtonGroupPanel.add(btnStop, null);
    buttonPanel.add(rightButtonGroupPanel, BorderLayout.EAST);
    buttonPanel.add(centerButtonGroupPanel, BorderLayout.CENTER);
    jMenu2.add(jMenuItem3);
  }

  void jMenuItem3_actionPerformed(ActionEvent e)
  {
      MainFrm_AboutBox dlg = new MainFrm_AboutBox(null);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = Toolkit.getDefaultToolkit().getScreenSize();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 , (frmSize.height - dlgSize.height) / 2 );
      dlg.setModal(true);
      dlg.show();
  }
}