package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.dc.eschool.system.UserIF;
import com.dc.eschool.system.UserInfo;
import com.dc.eschool.systemControl.SystemControl;



/**
 * Title:        �û���Ϣ
 * Description:  �Ľ���������ʾ���޸��û�����Ϣ
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class UserInfoDialog extends JDialog
{
  static final String NAME = "����";
  static final String GENDER = "�Ա�";
  static final String BIRTHDAY = "��������";
  static final String TELEPHONE = "�绰����";
  static final String TYPE = "���";

  /**  �û���Ϣ�ӿ�  */
  private UserIF         IUser;
  /**   SystemControl */
  private SystemControl  systemControl;
  /**  �û�ID */
  private String         userID;
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton btnSave = new JButton();
  JButton btnClose = new JButton();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable infoTable = new JTable();
  InfoTableModel infoModel ;
  BorderLayout borderLayout1 = new BorderLayout();

  public UserInfoDialog(Frame frame, String title, boolean modal)
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

  public UserInfoDialog()
  {
    this(null, "", false);
  }

  /**�Լ���д�Ĺ�������������ʼ������*/
  public UserInfoDialog(Frame frame,SystemControl systemControl,boolean modifyOrNot,String userID)
  {
    super(frame,"",false);
    this.systemControl=systemControl;
    this.userID=userID;
    infoModel = new InfoTableModel(modifyOrNot);
    init();
    if (modifyOrNot == false )
    {
       btnSave.setEnabled(false);
    }
    else
    {
        btnSave.setEnabled(true);
    }
    this.infoTable.setModel(this.infoModel);
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

  void jbInit() throws Exception
  {
    this.setModal(true);
    this.setTitle("�û���Ϣ");
    btnSave.setText("����");
    btnSave.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnSave_actionPerformed(e);
      }
    });
    btnClose.setText("�˳�");
    btnClose.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnClose_actionPerformed(e);
      }
    });
    jPanel1.setLayout(borderLayout1);
    jScrollPane1.setPreferredSize(new Dimension(300, 200));
    this.getContentPane().add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(infoTable, null);

    this.getContentPane().add(jPanel2,  BorderLayout.SOUTH);
    jPanel2.add(btnSave, null);
    jPanel2.add(btnClose, null);
  }

  void btnClose_actionPerformed(ActionEvent e)
  {
      dispose();
  }

  private void init()
  {
      /** ��ʱ���� */
      String temp;
      if (userID==null)
      {
         systemControl.writeLog("��ȡ�û���Ϣʱ,�û�IDΪ��,��û��ȡ���κ���Ϣ��");
         return;
      }
      if (systemControl.IUser==null)
      {
          IUser=new UserInfo(systemControl);
          systemControl.IUser=IUser;
      }
      else
      {
          IUser=systemControl.IUser;
      }
//      temp=IUser.getName(userID);
//      tfName.setText(temp);
//      temp=IUser.getBirthday(userID);
//      tfBirthday.setText(temp);
//      temp=IUser.getGender(userID);
//      tfGender.setText(temp);
//      temp=IUser.getTelephone(userID);
//      tfTelephone.setText(temp);
//      temp=IUser.getUserType(userID);
//      tfType.setText(temp);

      this.infoModel.addInfo(this.NAME, IUser.getName(userID) );
      this.infoModel.addInfo(this.GENDER, IUser.getGender(userID) );
      this.infoModel.addInfo(this.BIRTHDAY, IUser.getBirthday(userID) );
      this.infoModel.addInfo(this.TELEPHONE, IUser.getTelephone(userID) );
      this.infoModel.addInfo(this.TYPE, IUser.getUserType(userID) );
  }

  void btnSave_actionPerformed(ActionEvent e)
  {
      String name = this.infoModel.getValueByName(this.NAME);
      String gender = this.infoModel.getValueByName(this.GENDER);
      String birthday = this.infoModel.getValueByName(this.BIRTHDAY);
      String telephone = this.infoModel.getValueByName(this.TELEPHONE);
      String type = this.infoModel.getValueByName(this.TYPE);

      IUser.setUserInfo(userID,
                        name.trim(),
                        gender.trim(),
                        birthday.trim(),
                        telephone.trim(),
                        type.trim());
      this.dispose();
  }
}