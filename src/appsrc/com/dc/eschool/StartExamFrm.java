package com.dc.eschool;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.exam.Examination;
import com.dc.eschool.exam.ExamIF;

/**
 * Title:        ��ʼ����
 * Description:  ����ʦ�´￼������ʱ���ô��ڵ���
 *               ѧ��������Կ�ʼ��ť���������⿪
 *               ʼ���ԡ�
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class StartExamFrm extends JDialog
{
  /**   SystemControl */
  private   SystemControl  systemControl;
  /**   ���ⴰ��       */
  private   AnswerExamFrm  answerExamFrm;

  JPanel panel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JButton btnStartExam = new JButton();
  GridBagLayout gridBagLayout1 = new GridBagLayout();

  public StartExamFrm(Frame frame, String title, boolean modal)
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

  public StartExamFrm()
  {
    this(null, "", false);
  }

  /**  ������  */
  public StartExamFrm(Frame frame,SystemControl systemControl)
  {
      this(frame, "", false);
      this.systemControl=systemControl;
  }

  void jbInit() throws Exception
  {
    panel1.setLayout(gridBagLayout1);
    jLabel1.setText("������ť��ʼ����");
    btnStartExam.setText("��ʼ����");
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.setTitle("��ʼ����");
    btnStartExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnStartExam_actionPerformed(e);
      }
    });
    getContentPane().add(panel1);
    panel1.add(btnStartExam,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(13, 69, 15, 80), 63, 18));
    panel1.add(jLabel1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(14, 80, 0, 80), 22, 20));
  }

  void btnStartExam_actionPerformed(ActionEvent e)
  {
      this.hide();
      DownloadFile downloadFile=new DownloadFile(systemControl);
      downloadFile.start();
      /**   �û�ID   */
      answerExamFrm=new  AnswerExamFrm(systemControl);
      answerExamFrm.setSize(480,550);
      answerExamFrm.setVisible(true);
      systemControl.answerExamFrm=answerExamFrm;

      this.dispose();
  }
  /**  ���������߳�  */
  class DownloadFile extends Thread
  {
      /**   ���Խӿ�       */
      private   ExamIF         IExam;
      /**   SystemControl */
      private   SystemControl  systemControl;

      public DownloadFile(SystemControl systemControl)
      {
          this.systemControl=systemControl;
          if (systemControl.IExam==null)
          {
              IExam=new Examination(systemControl);
              systemControl.IExam=IExam;
          }
          else
          {
              IExam=systemControl.IExam;
          }
      }
      public void run()
      {
          String         userID;
          userID=systemControl.getUserID();
          String courseID=systemControl.getCourseID();
          try
          {
              IExam.getExamContent(userID,courseID);
          }
          catch (Exception e)
          {
              JOptionPane.showMessageDialog(null,
                                            "û�����ص����⣡",
                                            "��ʼ����",
                                            JOptionPane.INFORMATION_MESSAGE);
          }
      }
  }
}