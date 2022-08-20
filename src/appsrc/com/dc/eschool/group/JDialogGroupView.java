package com.dc.eschool.group;

import java.awt.*;
import javax.swing.*;
import com.dc.eschool.systemControl.*;
import java.awt.event.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class JDialogGroupView extends JDialog
{
  private SystemControl systemControl;
  private UserGroup userGroup;

  GroupComponent groupView = null;
  JPanel panel1 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JButton jButton1 = new JButton();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  public JDialogGroupView(Frame frame, String title, boolean modal)
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

  public JDialogGroupView(SystemControl sysControl,UserGroup userGroup)
  {
    this(sysControl.mainFrm, "·Ö×éÔ¤ÀÀ", true);
    this.systemControl=sysControl;
    this.userGroup=userGroup;
    groupView = new GroupComponent(this.userGroup);
    jScrollPane1.getViewport().add(groupView);
  }
  void jbInit() throws Exception
  {
    panel1.setLayout(gridBagLayout2);
    this.getContentPane().setLayout(gridBagLayout1);
    jButton1.setText("¹Ø±Õ");
    jButton1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton1_actionPerformed(e);
      }
    });
    this.getContentPane().add(panel1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 2, 0, 0), 554, 400));
    panel1.add(jScrollPane1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 0, 0, 5), 545, 342));
    panel1.add(jButton1,           new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 455, 14, 32), 13, -6));
  }

  void jButton1_actionPerformed(ActionEvent e)
  {
      this.dispose();
  }
}