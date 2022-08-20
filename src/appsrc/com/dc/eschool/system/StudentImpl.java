package com.dc.eschool.system;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.awt.*;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.ImageIcon;

import com.dc.eschool.system.EJBAccess;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.exam.ExamIF;
import com.dc.eschool.exam.Examination;
import com.dc.eschool.inspection.InspectionIF;
import com.dc.eschool.inspection.Inspection;
import com.dc.eschool.StartExamFrm;
import com.dc.eschool.AnswerExamFrm;
import com.dc.eschool.CheckOnWorkFrm;
import com.dc.eschool.ShowWarnDialog;
import com.dc.eschool.DarkFrm;
import com.dc.eschool.group.*;
import com.dc.eschool.communication.JDialogMessageBox;
/**
 * Title:         ѧ��Զ�̽ӿڵ�ʵ����
 * Description:   ʵ����ѧ��Զ�̽ӿڵ����з���
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class StudentImpl extends UnicastRemoteObject implements Student
{
   /**  ˽�г���  */
   private  static  final String   WALKCONTENT="��Ҫע��������";
   /**  ˽�б���  */
   private EJBAccess      ejbAccess;
   private Teacher        ITeacher=null;
   private SystemControl  systemControl;
   private ExamIF         IExam;
   private InspectionIF   IInspection;
   private String teacherIP;
   private String userIP;
   /**    ��ɫ����       */
   private DarkFrm            darkFrm;

   public StudentImpl(SystemControl systemControl,String teacherIP,String userIP)
           throws RemoteException
   {
       super();
       this.systemControl=systemControl;
       this.ejbAccess=systemControl.ejbAccess;
       this.teacherIP=teacherIP;
       this.userIP=userIP;
       init();
       if (systemControl.IExam==null)
       {
           IExam=new Examination(systemControl);
           systemControl.IExam=IExam;
       }
       else
       {
           IExam=systemControl.IExam;
       }
       if (systemControl.IInspection==null)
       {
           IInspection=new Inspection(systemControl);
           systemControl.IInspection=IInspection;
       }
       else
       {
           IInspection=systemControl.IInspection;
       }
   }

   /** ��������  */
   public void endExam() throws RemoteException
   {
       AnswerExamFrm  answerExamFrm;
       if (systemControl.answerExamFrm==null)
       {
           systemControl.writeLog("û���ҵ����ⴰ��,���ܸ�ѧ���Ѿ�����,Ҳ�����Ǹ�ѧ��������û�н��п���!");
           return;
       }
       else
       {
           answerExamFrm=systemControl.answerExamFrm;
           answerExamFrm.handin();
           answerExamFrm.dispose();
       }
   }

   /** ��ʼ����   */
   public void startExam() throws RemoteException
   {
       StartExamFrm   startExamFrm;
       if (systemControl.startExamFrm==null)
       {
           startExamFrm=new StartExamFrm(systemControl.mainFrm,systemControl);
           startExamFrm.setSize(300,130);
           startExamFrm.setVisible(true);
           systemControl.startExamFrm=startExamFrm;
       }
       else
       {
           startExamFrm=systemControl.startExamFrm;
           startExamFrm.setSize(300,130);
           startExamFrm.setVisible(true);
       }
   }

   /** ��������ȫ��Ļ�ĺ�ɫ����   */
   public void createDarkWindow() throws RemoteException
   {
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       if (systemControl.darkFrm==null)
       {
           darkFrm=new DarkFrm();
           darkFrm.setSize(screenSize);
           darkFrm.setLocation(0,0);
           darkFrm.show();
           systemControl.darkFrm=darkFrm;
       }
       else
       {
           darkFrm=systemControl.darkFrm;
           darkFrm.setSize(screenSize);
           darkFrm.setLocation(0,0);
           darkFrm.setVisible(true);
       }
       systemControl.mainFrm.setVisible(false);
   }

   /** ɾ����ɫ����   */
   public void deleteDarkWindow() throws RemoteException
   {
      if (systemControl.darkFrm==null)
      {
          return;
      }
      else
      {
         systemControl.darkFrm.closeWindow();
      }
      systemControl.mainFrm.setVisible(true);
   }

   /** ��ʾ��ʦ�ľ�����Ϣ   */
   public void showWarnMessage(String warnContent) throws RemoteException
   {
       ShowWarnDialog   showWarnDialog;
       if (systemControl.showWarnDialog==null)
       {
           showWarnDialog=new ShowWarnDialog(systemControl,warnContent);
           showWarnDialog.setSize(450,230);
           showWarnDialog.setVisible(true);
           systemControl.showWarnDialog=showWarnDialog;
       }
       else
       {
           showWarnDialog=systemControl.showWarnDialog;
           showWarnDialog.setContent(warnContent);
           showWarnDialog.setSize(450,230);
           showWarnDialog.setVisible(true);
       }
   }

   /** ��ʼץȡ��Ļ
    *  public void startCaptureScreen()
    *{
    *}
    */


   /** ȡ��ץ��
     *public void cancelCaptureScreen()
     *{
     *}
     */

   /** ��ʾ��ʦ��ʾ�Ĵ���   */
   public void hasCome() throws RemoteException
   {
       CheckOnWorkFrm   checkOnWorkFrm;
       if (systemControl.checkOnWorkFrm==null)
       {
          checkOnWorkFrm=new CheckOnWorkFrm(false,systemControl,WALKCONTENT);
          checkOnWorkFrm.setSize(320,120);
          checkOnWorkFrm.setVisible(true);
          systemControl.checkOnWorkFrm=checkOnWorkFrm;
       }
       else
       {
          checkOnWorkFrm=systemControl.checkOnWorkFrm;
          checkOnWorkFrm.setSize(320,120);
          checkOnWorkFrm.setVisible(true);
       }
   }

   /** ץȡ�Լ�����ĻͼƬ   */
   public ImageIcon CaptureScreen() throws RemoteException
   {
       return  IInspection.selfCaptureScreen();
   }

   /**  �˳�����     */
   public void exitClass() throws RemoteException
   {
       systemControl.mainFrm.exitClass();
   }

   /**  ��ʼ������ */
   public  void init() throws RemoteException
   {

       if (System.getSecurityManager() == null)
           System.setSecurityManager(new RMISecurityManager());
       try
       {
           ITeacher = (Teacher) Naming.lookup("//"+teacherIP+":1200/TeacherServer");
           System.out.println("I found Teacher");
       }
       catch (Exception e)
       {
           System.out.println("Student exception: " + e.getMessage());
           e.printStackTrace();
       }

       try
       {
	       UnicastRemoteObject.exportObject(this);
       }
       catch (java.rmi.server.ExportException e)
       {
		// use already exported object; remember exception
       }
       try
       {
           Vector vecTmp=ITeacher.getGroupInformation();
           this.systemControl.userGroup=new UserGroup(vecTmp,this.systemControl);
           ITeacher.login(this,systemControl.getUserID(),this.userIP);
        }
        catch(Exception ee)
        {
            System.out.println("Student exception2: " + ee.getMessage());
            ee.printStackTrace();
        }
       systemControl.setTeacherInterface(ITeacher);
    }

    /**
     * ���±�������Ardy_yan���
     */

  /**
   * METHOD: answerFromTeacher
   * DESC  : ��ʦӦ��
   * CREATE: 1.0 Ardy 2001-09-30
   * MODIFY:
   */
    public String answerFromTeacher(String port)
    {
        System.out.println("port="+port);
        String p=systemControl.communication.answerFromTeacher(port);
        return p;
    }
  /**
   * METHOD: sendText
   * DESC  : ��ʦѧ��������Ϣ��
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void sendText(String content)
   {
      systemControl.privateTalk.receiveText(content);
   }
  /**
   * METHOD: notifyStartOnlineTalk
   * DESC  : ����ѧ�����������۽��档
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void  notifyStartOnlineTalk(Hashtable userIDs,String port)
   {
      systemControl.communication.notifyStartOnlineTalk(userIDs,port);
   }
   /**
   * METHOD: notifyEndChat
   * DESC  : ����ѧ�����������۽��档
   * CREATE: 1.0 Ardy 2001-10-25
   * MODIFY:
   */
   public void  notifyEndOnlineTalk()
   {
      systemControl.communication.notifyEndOnlineTalk();
   }
  /**
   * METHOD: notifyStartDemonstrate
   * DESC  : ֪ͨ������ѧ������ʾ��
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void  notifyStartDemonstrate(String port)
   {
      systemControl.communication.notifyStartDemonstrate(port);
   }
  /**
   * METHOD: notifyStartDemonstrate
   * DESC  : ֪ͨ������ѧ������ʾ��
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void  notifyEndDemonstrate()
   {
      systemControl.communication.notifyEndDemonstrate();
   }
  /**
   * METHOD: notifyStartAnswerCompetition
   * DESC  : ֪ͨ������ѧ��������
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void  notifyStartAnswerCompetition(Vector userIDs,String port)
   {
      systemControl.communication.notifyStartAnswerCompetition(userIDs,port);
   }

   /**
    * METHOD: notifyBeginOrEndAnswerCompetition
    * DESC  : ��ʼ���������
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void notifyBeginOrEndAnswerCompetition(boolean b)
    {
        systemControl.communication.notifyBeginOrEndAnswerCompetition(b);
        if(b==true)
        {
            this.systemControl.answerCompetitionSequenceListModel.setData(new Vector());
        }
    }
    /**
    * METHOD: addAnswerCompetitionSequence
    * DESC  : ��������ѧ����
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addAnswerCompetitionSequence(String userID)
    {
        systemControl.communication.addAnswerCompetitionSequence(userID);
    }

   /**
    * METHOD: sendInformation
    * DESC  : ������Ϣ��
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public void sendInformation(String info)
    {
        if(info!=null)
            systemControl.mainFrm.sendInformation(info);
    }
   /**
    * METHOD: voiceReceive
    * DESC  : �������ա�
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void voiceReceive(String port)
    {
        systemControl.communication.startVoiceReceive(port);
    }
   /**
    * METHOD: endVoiceReceive
    * DESC  : �ر�������
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void endMediaReceive()
    {
        systemControl.communication.closeMedia();
    }
   /**
    * METHOD: notifyAllowAnswer
    * DESC  : ����ش�
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void notifyAllowAnswer(boolean b)
    {
        systemControl.communication.notifyAllowAnswer(b);
    }
    /**
    * METHOD: listenReceive
    * DESC  : �������ա�
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void listenReceive(String port)
    {
        systemControl.communication.startListenReceive(port);
    }
    /**
   * METHOD: updateOtherUser
   * DESC  : �����û������ݡ�
   * CREATE: 1.0 Ardy 2001-09-30
   * MODIFY:
   */
   public void updateOtherUser(String userID,String userIP,int userCount,Student a_student)
   {
        systemControl.userGroup.changeUserStatus(userID,EschoolUser.PRESENT);
        systemControl.userGroup.changeUserIP(userID,userIP);
        systemControl.setLoginStudentCount(String.valueOf(userCount));

        Hashtable HtStudent=systemControl.getHtStudentIDInterface();
        if(HtStudent.containsValue(a_student)==false)
        {
             HtStudent.put(userID,a_student);
             systemControl.setHtStudentIDInterface(HtStudent);
        }
    }
    /**
    * METHOD: moveUser
    * DESC  : �ƶ�һ���˴�һ���鵽��һ����(ָ��λ��)��
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void moveUser(String fromGroupName,String fromUserID,String toGroupName,String toUserID)
    {
        this.systemControl.userGroup.moveUser(fromGroupName,fromUserID,toGroupName,toUserID);
    }
     /**
   * METHOD: updateOtherUser
   * DESC  : �����û������ݡ�
   * CREATE: 1.0 Ardy 2001-09-30
   * MODIFY:
   */
   public void updateGroup()
   {
        try
        {
            Teacher teacher=this.systemControl.getTeacherInterface();
            Vector vecTmp=teacher.getGroupInformation();
            this.systemControl.userGroup=null;
            this.systemControl.userGroup=new UserGroup(vecTmp,this.systemControl);
            this.systemControl.mainFrm.init();
        }
        catch(Exception e)
        {
            System.out.println("updateGroup Exception :"+e.getMessage());
            e.printStackTrace();
        }
    }
  /**
   * METHOD: logout
   * DESC  : �˳���
   * CREATE: 1.0 Ardy 2001-11-28
   * MODIFY:
   */
    public boolean logout()
    {
        try
        {
            JDialogMessageBox jDialogMessageBox=new JDialogMessageBox("�¿Σ�",this.systemControl,"1");
            jDialogMessageBox.setSize(200,100);
            jDialogMessageBox.show();
        }
        catch(Exception e)
        {
            System.out.println("logout Exception:"+e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
    * METHOD: notifyAllowAnswer
    * DESC  : ����ش�
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public String startDemonstrate()
    {
        String port=systemControl.communication.studentDemonstrate(this.systemControl.mainFrm.SCREENURL);
        return port;
    }
    /**
    * METHOD: changeStatus
    * DESC  : ѧ���¿θı�����ѧ��״̬��
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void changeStatus(String userID)
    {
        this.systemControl.userGroup.changeUserStatus(userID,EschoolUser.ABESENT);
    }
    /**
   * METHOD: notifyEndCloseAnswerCompetition
   * DESC  : ����ѧ�����������۽��档
   * CREATE: 1.0 Ardy 2001-10-25
   * MODIFY:
   */
   public void  notifyCloseAnswerCompetition()
   {
      systemControl.communication.notifyCloseAnswerCompetition();
   }
     /**
   * METHOD: closeTalkWithTeacher
   * DESC  : �ر������Խ�
   * CREATE: 1.0 Ardy 2001-12-10
   * MODIFY:
   */
   public void  closeTalkWithTeacher()
   {
      JDialogMessageBox jDialogMessageBox=new JDialogMessageBox("�����Խ�������",this.systemControl);
      jDialogMessageBox.setSize(200,100);
      jDialogMessageBox.show();
      this.systemControl.jmfApi.stopJMFDevice();
      systemControl.jDialogTalkWithTeacher.dispose();
      systemControl.jDialogTalkWithTeacher=null;
   }
}
