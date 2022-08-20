package com.dc.eschool.system;

import java.util.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.util.Vector;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.*;
import com.dc.eschool.group.*;

/**
 * Title:         老师远程接口的实现类
 * Description:   实现了老师所有的远程方法
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public class TeacherImpl extends UnicastRemoteObject implements Teacher
{
   private static  final String    TALKCONTENT="正在认真听讲！";
   /**   SystemControl实例  */
   private SystemControl systemControl;

   public TeacherImpl(SystemControl systemControl) throws RemoteException
   {
      super();
      this.systemControl=systemControl;
      init();
   }

   /** 学生调用该方法，告诉老师已经交卷   */
   public synchronized void handinExam(String userID) throws RemoteException
   {
       Vector vectHandin=systemControl.getHandinStudent();
       if (vectHandin==null)
       {
           vectHandin=new Vector();
       }
       vectHandin.addElement(userID);
       systemControl.setHandinStudent(vectHandin);
       systemControl.userGroup.changeExaming(userID,false);
   }

   /**  学生调用该方法，告诉老师在认真的听讲   */
   public synchronized void alreadyCome(String userID) throws RemoteException
   {
       CheckOnWorkFrm   checkOnWorkFrm;
       Hashtable        htStudentIDName;
       String           userName;
       htStudentIDName=systemControl.getHtStudentIDName();
       systemControl.userGroup.changeCalling(userID,true);
       /**
       userName=(String)htStudentIDName.get(userID);
       if (systemControl.checkOnWorkFrm==null)
       {
          checkOnWorkFrm=new CheckOnWorkFrm(true,systemControl,userName+TALKCONTENT);
          checkOnWorkFrm.setSize(320,120);
          checkOnWorkFrm.setVisible(true);
          systemControl.checkOnWorkFrm=checkOnWorkFrm;
       }
       else
       {
          checkOnWorkFrm=systemControl.checkOnWorkFrm;
          checkOnWorkFrm.changeContent(userName+TALKCONTENT);
          checkOnWorkFrm.setSize(320,120);
          checkOnWorkFrm.setVisible(true);
       }
       */
   }


   /**  初始化函数 */
   public  void init() throws RemoteException
   {

      // Create and install a security manager
      if (System.getSecurityManager() == null)
      {
        System.setSecurityManager(new RMISecurityManager());
      }
      try
      {
          String name = "//"+":1200/TeacherServer";
	      LocateRegistry.createRegistry(1200);
	      Naming.rebind(name, this);
		  System.out.println("TeacherServer bound in registry");;
      }
      catch (Exception e)
      {
          System.out.println("TeacherImpl err: " + e.getMessage());
          e.printStackTrace();
      }
    }
    /**
     * 以下部分由Ardy_yan开发
     */

   /**
   * METHOD: login
   * DESC  : 登录。
   * CREATE: 1.0 Ardy 2001-09-30
   * MODIFY:
   */
   public void login( Student a_student,String userID,String userIP)
   {
     try
     {
        Hashtable HtStudent=systemControl.getHtStudentIDInterface();
        systemControl.userGroup.changeUserStatus(userID,EschoolUser.PRESENT);
        systemControl.userGroup.changeUserIP(userID,userIP);
        if(HtStudent.containsValue(a_student)==false)
        {
             HtStudent.put(userID,a_student);
             systemControl.setHtStudentIDInterface(HtStudent);
        }
        int userCount=systemControl.getStudentCountOnClass();
        updateOtherUser(HtStudent,userID,userIP,userCount,a_student);
      }
      catch (Exception e)
      {
        System.out.println("Student exception: " + e.getMessage());
        e.printStackTrace();
      }
    }
  /**
   * METHOD: updateOtherUser
   * DESC  : 更新用户组数据。
   * CREATE: 1.0 Ardy 2001-09-30
   * MODIFY:
   */
   void updateOtherUser(Hashtable htStudent,String userID,String userIP,int userCount,Student a_student)
   {
        try
        {
            for(Enumeration er=htStudent.elements(); er.hasMoreElements();)
            {
                Student student=(Student)er.nextElement();
                student.updateOtherUser(userID,userIP,userCount,a_student);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
   /**
   * METHOD: getGroupInformation
   * DESC  : 登录。
   * CREATE: 1.0 Ardy 2001-09-30
   * MODIFY:
   */
   public Vector getGroupInformation()
   {
       return this.systemControl.userGroup.getAllUser();

    }
   /**
    * METHOD: askFromStudent
    * DESC  : 老师获得请求。
    * CREATE: 1.0 Ardy 2001-10-18
    * MODIFY:
    */
    public void askFromStudent(String userID)
    {
        systemControl.communication.askFromStudent(userID);
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
    * METHOD: studentAnswerCompetition
    * DESC  : 学生抢答。
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public void studentAnswerCompetition(String userID)
    {
        systemControl.communication.answerCompetitionSequence(userID);
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
    * CREATE: 1.0 Ardy 2001-11-09
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
   * METHOD: logout
   * DESC  : 退出。
   * CREATE: 1.0 Ardy 2001-11-28
   * MODIFY:
   */
    public boolean logout(String userID)
    {
        try
        {
            Hashtable htUser=this.systemControl.getHtStudentIDInterface();
            htUser.remove(userID);
            this.systemControl.userGroup.changeUserStatus(userID,EschoolUser.ABESENT);
            for(Enumeration er=htUser.elements(); er.hasMoreElements();)
            {
                Student student=(Student)er.nextElement();
                if(student==null) continue;
                student.changeStatus(userID);
            }

            //String userCount=systemControl.getLoginStudentCount();
            //systemControl.setLoginStudentCount(String.valueOf((new Integer(userCount)).intValue() - 1));
        }
        catch(Exception e)
        {
            System.out.println("finishedServer Exception:"+e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
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
}
