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
 * Title:         学生远程接口的实现类
 * Description:   实现了学生远程接口的所有方法
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class StudentImpl extends UnicastRemoteObject implements Student
{
   /**  私有常量  */
   private  static  final String   WALKCONTENT="你要注意听讲！";
   /**  私有变量  */
   private EJBAccess      ejbAccess;
   private Teacher        ITeacher=null;
   private SystemControl  systemControl;
   private ExamIF         IExam;
   private InspectionIF   IInspection;
   private String teacherIP;
   private String userIP;
   /**    黑色窗口       */
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

   /** 结束考试  */
   public void endExam() throws RemoteException
   {
       AnswerExamFrm  answerExamFrm;
       if (systemControl.answerExamFrm==null)
       {
           systemControl.writeLog("没有找到答题窗口,可能该学生已经交卷,也可能是该学生根本就没有进行考试!");
           return;
       }
       else
       {
           answerExamFrm=systemControl.answerExamFrm;
           answerExamFrm.handin();
           answerExamFrm.dispose();
       }
   }

   /** 开始考试   */
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

   /** 创建覆盖全屏幕的黑色窗口   */
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

   /** 删除黑色窗口   */
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

   /** 显示老师的警告信息   */
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

   /** 开始抓取屏幕
    *  public void startCaptureScreen()
    *{
    *}
    */


   /** 取消抓屏
     *public void cancelCaptureScreen()
     *{
     *}
     */

   /** 显示老师提示的窗口   */
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

   /** 抓取自己的屏幕图片   */
   public ImageIcon CaptureScreen() throws RemoteException
   {
       return  IInspection.selfCaptureScreen();
   }

   /**  退出课堂     */
   public void exitClass() throws RemoteException
   {
       systemControl.mainFrm.exitClass();
   }

   /**  初始化函数 */
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
     * 以下编码是由Ardy_yan完成
     */

  /**
   * METHOD: answerFromTeacher
   * DESC  : 老师应答。
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
   * DESC  : 老师学生传送信息。
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void sendText(String content)
   {
      systemControl.privateTalk.receiveText(content);
   }
  /**
   * METHOD: notifyStartOnlineTalk
   * DESC  : 启动学生端联机讨论界面。
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void  notifyStartOnlineTalk(Hashtable userIDs,String port)
   {
      systemControl.communication.notifyStartOnlineTalk(userIDs,port);
   }
   /**
   * METHOD: notifyEndChat
   * DESC  : 结束学生端联机讨论界面。
   * CREATE: 1.0 Ardy 2001-10-25
   * MODIFY:
   */
   public void  notifyEndOnlineTalk()
   {
      systemControl.communication.notifyEndOnlineTalk();
   }
  /**
   * METHOD: notifyStartDemonstrate
   * DESC  : 通知并启动学生端演示。
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void  notifyStartDemonstrate(String port)
   {
      systemControl.communication.notifyStartDemonstrate(port);
   }
  /**
   * METHOD: notifyStartDemonstrate
   * DESC  : 通知并启动学生端演示。
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void  notifyEndDemonstrate()
   {
      systemControl.communication.notifyEndDemonstrate();
   }
  /**
   * METHOD: notifyStartAnswerCompetition
   * DESC  : 通知并启动学生端抢答。
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public void  notifyStartAnswerCompetition(Vector userIDs,String port)
   {
      systemControl.communication.notifyStartAnswerCompetition(userIDs,port);
   }

   /**
    * METHOD: notifyBeginOrEndAnswerCompetition
    * DESC  : 开始或结束抢答。
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
    * DESC  : 加入抢答学生。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addAnswerCompetitionSequence(String userID)
    {
        systemControl.communication.addAnswerCompetitionSequence(userID);
    }

   /**
    * METHOD: sendInformation
    * DESC  : 传递信息。
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
    * DESC  : 语音接收。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void voiceReceive(String port)
    {
        systemControl.communication.startVoiceReceive(port);
    }
   /**
    * METHOD: endVoiceReceive
    * DESC  : 关闭语音。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void endMediaReceive()
    {
        systemControl.communication.closeMedia();
    }
   /**
    * METHOD: notifyAllowAnswer
    * DESC  : 允许回答。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void notifyAllowAnswer(boolean b)
    {
        systemControl.communication.notifyAllowAnswer(b);
    }
    /**
    * METHOD: listenReceive
    * DESC  : 听力接收。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void listenReceive(String port)
    {
        systemControl.communication.startListenReceive(port);
    }
    /**
   * METHOD: updateOtherUser
   * DESC  : 更新用户组数据。
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
    * DESC  : 移动一个人从一个组到另一个组(指定位置)。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void moveUser(String fromGroupName,String fromUserID,String toGroupName,String toUserID)
    {
        this.systemControl.userGroup.moveUser(fromGroupName,fromUserID,toGroupName,toUserID);
    }
     /**
   * METHOD: updateOtherUser
   * DESC  : 更新用户组数据。
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
   * DESC  : 退出。
   * CREATE: 1.0 Ardy 2001-11-28
   * MODIFY:
   */
    public boolean logout()
    {
        try
        {
            JDialogMessageBox jDialogMessageBox=new JDialogMessageBox("下课！",this.systemControl,"1");
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
    * DESC  : 允许回答。
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
    * DESC  : 学生下课改变其他学生状态。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void changeStatus(String userID)
    {
        this.systemControl.userGroup.changeUserStatus(userID,EschoolUser.ABESENT);
    }
    /**
   * METHOD: notifyEndCloseAnswerCompetition
   * DESC  : 结束学生端联机讨论界面。
   * CREATE: 1.0 Ardy 2001-10-25
   * MODIFY:
   */
   public void  notifyCloseAnswerCompetition()
   {
      systemControl.communication.notifyCloseAnswerCompetition();
   }
     /**
   * METHOD: closeTalkWithTeacher
   * DESC  : 关闭语音对讲
   * CREATE: 1.0 Ardy 2001-12-10
   * MODIFY:
   */
   public void  closeTalkWithTeacher()
   {
      JDialogMessageBox jDialogMessageBox=new JDialogMessageBox("语音对讲结束！",this.systemControl);
      jDialogMessageBox.setSize(200,100);
      jDialogMessageBox.show();
      this.systemControl.jmfApi.stopJMFDevice();
      systemControl.jDialogTalkWithTeacher.dispose();
      systemControl.jDialogTalkWithTeacher=null;
   }
}
