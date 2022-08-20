package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import com.dc.eschool.systemControl.SystemControl;
/**
 * Title:        显示警告信息
 * Description:  显示老师的警告信息
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class ShowWarnDialog extends JDialog
{
  /**    SystemControl实例  */
  SystemControl     systemControl;

  JPanel panel1 = new JPanel();
  JTextArea taContent = new JTextArea();
  JButton btnConfirm = new JButton();
  Border border1;
  JScrollPane jScrollPane1 = new JScrollPane();
  GridBagLayout gridBagLayout1 = new GridBagLayout();

  public ShowWarnDialog(Frame frame, String title, boolean modal)
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

  public ShowWarnDialog()
  {
      this(null, "", false);
  }
  public ShowWarnDialog(SystemControl systemControl,String talkContent)
  {
      this(null, "", false);
      this.systemControl=systemControl;
      taContent.setText(talkContent);
  }
  void jbInit() throws Exception
  {
    border1 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,new Color(178, 178, 178),new Color(124, 124, 124));
    panel1.setLayout(gridBagLayout1);
    taContent.setBorder(BorderFactory.createLoweredBevelBorder());
    taContent.setEditable(false);
    taContent.setLineWrap(true);
    taContent.setRows(10);
    btnConfirm.setText("确定");
    btnConfirm.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnConfirm_actionPerformed(e);
      }
    });
    this.setResizable(false);
    this.setTitle("警告");
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(btnConfirm,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 152, 10, 154), 0, 0));
    panel1.add(jScrollPane1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(12, 12, 0, 11), 15, -71));
    jScrollPane1.getViewport().add(taContent, null);
  }

  void btnConfirm_actionPerformed(ActionEvent e)
  {
       this.dispose();
  }

  public void setContent(String content)
  {
      taContent.setText(content);
  }
}