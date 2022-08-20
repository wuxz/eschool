package com.dc.eschool.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.Hashtable;
import com.dc.eschool.systemControl.*;
//import com.borland.jbcl.layout.*;

/**
 * Title:        JDialogLogin
 * Description:  �û���������֤
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class JDialogLogin extends JDialog
{
  public boolean verifyPass=true;
  public Hashtable htCourse;
  private LoginIF login;
  private SystemControl systemControl;
  BorderLayout borderLayout1 = new BorderLayout();
  JButton cancelButton = new JButton();
  JButton okButton = new JButton();
  JLabel logoLabel = new JLabel(new ImageIcon( JDialogLogin.class.getResource("title.gif") ));
  JPanel inputPanel = new JPanel();
  JTextField jTextFieldUserName = new JTextField();
  JPasswordField jPasswordFieldPassword = new JPasswordField();
  JLabel jLabel2 = new JLabel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JLabel jLabel1 = new JLabel();
  JPanel rightButtonGroupPanel = new JPanel();
  JPanel leftButtonGroupPanel = new JPanel();
  JPanel buttonPanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel centerButtonGroupPanel = new JPanel();//�洢�γ�

  public JDialogLogin(Frame frame, String title, boolean modal)
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

  public JDialogLogin(SystemControl sysControl,Login login)
  {
    this(sysControl.mainFrm, "�����֤", true);
    this.systemControl=sysControl;
    this.login=login;
    jTextFieldUserName.requestFocus();
    centerScreen();
  }
  void jbInit() throws Exception
  {
    //this.setSize(400,300);
    this.getContentPane().setLayout(borderLayout1);
    this.setDefaultCloseOperation(3);
    this.setResizable(false);
    this.setTitle("");
    this.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        this_windowClosing(e);
      }
    });
    cancelButton.setText("ȡ��");
    cancelButton.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        cancelButton_actionPerformed(e);
      }
    });
    okButton.setText("ȷ��");
    okButton.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        okButton_actionPerformed(e);
      }
    });
    //panel1.setMaximumSize(new Dimension(368, 172));
    //panel1.setMinimumSize(new Dimension(368, 172));
    //panel1.setPreferredSize(new Dimension(368, 172));

    inputPanel.setLayout(gridBagLayout1);
    jTextFieldUserName.setNextFocusableComponent(jPasswordFieldPassword);
    jLabel2.setText("���룺");
    jLabel1.setText("��¼����");
    buttonPanel.setLayout(borderLayout2);
    this.getContentPane().add(logoLabel,  BorderLayout.NORTH);

    inputPanel.add(jTextFieldUserName, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(17, 0, 7, 60), 159, 3));
    inputPanel.add(jPasswordFieldPassword, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(11, 0, 0, 60), 159, 3));
    inputPanel.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(17, 33, 0, 0), 6, 10));
    inputPanel.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(9, 33, 0, 0), 6, 10));
    this.getContentPane().add(inputPanel, BorderLayout.CENTER);
    this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    buttonPanel.add(leftButtonGroupPanel, BorderLayout.WEST);
    buttonPanel.add(rightButtonGroupPanel, BorderLayout.EAST);
    buttonPanel.add(centerButtonGroupPanel,  BorderLayout.CENTER);
    centerButtonGroupPanel.add(okButton, null);
    centerButtonGroupPanel.add(cancelButton, null);
  }
   /**
    * MOTHOD: jButton1_actionPerformed
    * DESC  : ����������֤��
    * CREATE: 1.0 Ardy 2001-09-29
    * MODIFY:
    */
  void okButton_actionPerformed(ActionEvent e)
  {
    char[] password=jPasswordFieldPassword.getPassword();
    String userName=jTextFieldUserName.getText();
    htCourse=login.verifyPassword(userName,password);
    if (htCourse==null||htCourse.size()==0)
    {
      showInfoMsg(this,"�û�������������!");
      verifyPass=false;
    }
    else
    {
      verifyPass=true;
      dispose();
      this.logoLabel = null;
    }
  }

  /**
  * MOTHOD: showInfoMsg
  * DESC  : ��ʾ��Ϣ��Ϣ��
  * CREATE: 1.0 Ardy 2001-09-29
  * MODIFY:
  */
  void showInfoMsg(Component parentComponent,String infoMsg)
  {
    JOptionPane pane = new javax.swing.JOptionPane();

    parentComponent.setEnabled(false);
    try
    {
      pane.showMessageDialog(this, "������Ϣ��" + infoMsg,"���ý���->�����֤",JOptionPane.ERROR_MESSAGE);
    }
    catch (Exception e)
    {
      System.out.println("��Ϣ�Ի���->" + e.getMessage());
    }
    finally
    {
      parentComponent.setEnabled(true);
    }

  }

  void cancelButton_actionPerformed(ActionEvent e)
  {
    verifyPass=false;
    dispose();
    System.exit(0);
  }

  void this_windowClosing(WindowEvent e)
  {
    verifyPass=false;
    System.exit(0);
  }
  public void centerScreen()
  {
    Dimension dim = getToolkit().getScreenSize();
    Rectangle abounds = getBounds();
    setLocation((dim.width - abounds.width) / 2,(dim.height - abounds.height) / 2);
  }
}