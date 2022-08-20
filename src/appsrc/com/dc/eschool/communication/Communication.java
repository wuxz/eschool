package com.dc.eschool.communication;
import com.dc.eschool.system.*;
import java.util.Hashtable;
import java.rmi.RemoteException;
import java.util.Vector;
import com.dc.eschool.util.JMFAPI;
import javax.media.*;
import java.util.Enumeration;
import com.dc.eschool.group.*;
import java.awt.*;
import javax.swing.JOptionPane;
import com.dc.eschool.systemControl.SystemControl;
public class Communication implements CommunicationIF
{

   private static final String AUDIOURL = "javasound://44100.0";    //"224.1.1.1"
   private static final String BROADCASTIP = "224.1.1.1";
   private static final String LOCALIP = "127.0.0.1";
   private static final String ASKFOR="。。。";
   private SystemControl systemControl;
   private Student student=null;
   private Teacher teacher=null;
   private String studentIP;
   //构造函数入口
   public Communication(SystemControl sysControl)
   {
      this.systemControl=sysControl;
      if(this.systemControl.jmfApi==null)
      this.systemControl.jmfApi=new JMFAPI(sysControl);
   }
  /**
   * METHOD: askForTeacher
   * DESC  : 学生请求老师。
   * CREATE: 1.0 Ardy 2001-10-18
   * MODIFY:
   */
    public void askForTeacher()
    {
        try
        {
            teacher=systemControl.getTeacherInterface();
            teacher.askFromStudent(systemControl.getUserID());
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
            System.out.println("Remote Exception"+e.getMessage());
        }
    }

  /**
   * METHOD: askFromStudent
   * DESC  : 老师获得请求。
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
    public void askFromStudent(String userID)
   {
        String userName=systemControl.userGroup.getUserNameByUserID(userID);
        systemControl.userGroup.changeHandUp(userID,true);
   }

   /**
    * METHOD: answerForStudent
    * DESC  : 应答学生请求。
    * CREATE: 1.0 Ardy 2001-10-19
    * MODIFY:
    */
    public void answerForStudent(EschoolUser user)
    {
        try
        {
            if(systemControl.jDialogTalkWithTeacher==null)
                systemControl.jDialogTalkWithTeacher=new JDialogTalkWithTeacher(systemControl,user);
            systemControl.jDialogTalkWithTeacher.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Remote Exception"+e.getMessage());
        }
    }
  /**
   * METHOD: answerFromTeacher
   * DESC  : 获得老师应答。
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
   public String answerFromTeacher(String port)
   {
      if(systemControl.jDialogTalkWithTeacher==null)
      {
          systemControl.jDialogTalkWithTeacher=new JDialogTalkWithTeacher(systemControl,port);
          systemControl.jDialogTalkWithTeacher.show();
      }
      return systemControl.jDialogTalkWithTeacher.sendPort;
   }

  /**
    * METHOD: addAllToOnlineTalk
    * DESC  : 加全部学生抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
   public void addAllToOnlineTalk()
   {
      if(this.systemControl.chatServer==null)
          this.systemControl.chatServer=new ChatServer(this.systemControl);
      this.systemControl.chatServer.addAllToOnlineTalk();
   }
    /**
    * METHOD: addGroupToOnlineTalk
    * DESC  : 加组到联机讨论。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addGroupToOnlineTalk(String userID)//此处userID,换作组名也成立（稍作修改）
    {
        if(this.systemControl.chatServer==null)
            this.systemControl.chatServer=new ChatServer(this.systemControl);
        this.systemControl.chatServer.addGroupToOnlineTalk(userID);
    }
   /**
    * METHOD: addStudentToOnlineTalk
    * DESC  : 加单个学生到联机讨论。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addStudentToOnlineTalk(String userID)
    {
        if(this.systemControl.chatServer==null)
            this.systemControl.chatServer=new ChatServer(this.systemControl);
        this.systemControl.chatServer.addStudentToOnlineTalk(userID);
    }
   /**
    * METHOD: notifyStartOnlineTalk
    * DESC  : 启动学生端联机讨论界面。
    * CREATE: 1.0 Ardy 2001-10-19
    * MODIFY:
    */
    public void  notifyStartOnlineTalk(Hashtable userIDs,String port)
    {
        System.out.println("Communication's notifyStartOnlineTalk begin..");
        if(this.systemControl.chatServer==null)
            this.systemControl.chatServer=new ChatServer(this.systemControl);
        this.systemControl.chatServer.notifyStartOnlineTalk(userIDs,port);
    }
   /**
    * METHOD: endOnlineTalk
    * DESC  : 结束联机讨论。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void endOnlineTalk()
    {
        this.systemControl.chatServer.endOnlineTalk();
    }
   /**
    * METHOD: notifyEndOnlineTalk
    * DESC  : 结束学生端联机讨论界面。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void notifyEndOnlineTalk()
    {
        JDialogMessageBox jDialogMessageBox=new JDialogMessageBox("联机讨论结束！",this.systemControl);
        jDialogMessageBox.setSize(200,100);
        jDialogMessageBox.show();
        systemControl.jDialogOnlineTalkStudent.dispose();
        systemControl.jmfApi.stopJMFDevice();
        systemControl.jDialogOnlineTalkStudent=null;
    }
    /**
    * METHOD: closeAnswerCompetition
    * DESC  : 老师关闭抢答。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void closeAnswerCompetition(Vector userIDs)
    {
        this.systemControl.answerCompetition.closeAnswerCompetition(userIDs);
    }
   /**
    * METHOD: notifyCloseAnswerCompetition
    * DESC  : 结束学生端联机讨论界面。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void notifyCloseAnswerCompetition()
    {
        JDialogMessageBox jDialogMessageBox=new JDialogMessageBox("抢答结束！",this.systemControl);
        jDialogMessageBox.setSize(200,100);
        jDialogMessageBox.show();
        systemControl.jDialogAnswerCompetitionStudent.dispose();
        systemControl.jmfApi.stopJMFDevice();
        systemControl.jDialogAnswerCompetitionStudent=null;
    }
   /**
    * METHOD: startDemonstrate
    * DESC  : 屏幕演示（对所有学生）。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void startDemonstrate(String url)
    {
         try
         {
             String port=this.systemControl.jmfApi.screenTransmit(url,null,true);
             System.out.println("Teacher transmit port :"+port);
             if(port==null) return;
             Hashtable HtUser=systemControl.getHtStudentIDInterface();
            for(Enumeration er=HtUser.elements(); er.hasMoreElements();)
            {
                student=(Student)er.nextElement();
                if(student==null) continue;
                student.notifyStartDemonstrate(port);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
    * METHOD: startDemonstrate
    * DESC  : 屏幕演示(对一个学生)。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void startDemonstrate(String url,String userID)
    {
         try
         {
             if(userID==null) return;
             String port=this.systemControl.jmfApi.screenTransmit(url,null,true);
             System.out.println("Teacher transmit port :"+port);
             if(port==null) return;
             Hashtable HtUser=systemControl.getHtStudentIDInterface();
             student=(Student)HtUser.get(userID);
             student.notifyStartDemonstrate(port);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    /**
    * METHOD: startDemonstrate
    * DESC  : 学生演示。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void startStudentDemonstrate(String userID)
    {
         try
         {
             //String port=this.systemControl.jmfApi.screenTransmit(url,null,true);
            Hashtable HtUser=systemControl.getHtStudentIDInterface();
            student=(Student)HtUser.get(userID);
            String port=student.startDemonstrate();
            boolean b=this.systemControl.jmfApi.screenRecieve(BROADCASTIP,port);
            if(b==false) System.out.println("Student videoRecieve failed");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    /**
    * METHOD: studentDemonstrate
    * DESC  : 学生屏幕演示。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public String studentDemonstrate(String url)
    {
         String port=null;
         try
         {
             port=this.systemControl.jmfApi.screenTransmit(url,null,true);
             System.out.println("Teacher transmit port :"+port);
             if(port==null) return null;
             String userID=this.systemControl.getUserID();
             Hashtable HtUser=systemControl.getHtStudentIDInterface();
            for(Enumeration er=HtUser.keys(); er.hasMoreElements();)
            {
                String id=(String)er.nextElement();
                if(id.equals(userID)) continue;
                student=(Student)HtUser.get(id);
                student.notifyStartDemonstrate(port);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return port;
    }
   /**
    * METHOD: notifyStartDemonstrate
    * DESC  : 通知并启动学生屏幕演示。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void notifyStartDemonstrate(String port)
    {
        try
        {
            boolean b=this.systemControl.jmfApi.screenRecieve(BROADCASTIP,port);
            if(b==false) System.out.println("Student videoRecieve failed");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
   /**
    * METHOD: endDemonstrate
    * DESC  : （老师）结束屏幕演示。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void endDemonstrate()
    {
        try
        {
             Hashtable HtUser=systemControl.getHtStudentIDInterface();
            for(Enumeration er=HtUser.elements(); er.hasMoreElements();)
            {
                student=(Student)er.nextElement();
                if(student==null) continue;
                student.notifyEndDemonstrate();
            }
            this.systemControl.jmfApi.stopJMFDevice();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   /**
    * METHOD: notifyEndDemonstrate
    * DESC  : 通知并结束学生屏幕演示。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void notifyEndDemonstrate()
    {
        closeMedia();

    }
   /**
    * METHOD: addGroupToAnswerCompetition
    * DESC  : 加组学生抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addGroupToAnswerCompetition(String userID)//此处userID,换作组名也成立（稍作修改）
    {
        if(this.systemControl.answerCompetition==null)
            this.systemControl.answerCompetition=new AnswerCompetition(this.systemControl);
        this.systemControl.answerCompetition.addGroupToAnswerCompetition(userID);
    }
    /**
    * METHOD: addAllToAnswerCompetition
    * DESC  : 加全部学生抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addAllToAnswerCompetition()
    {
        if(this.systemControl.answerCompetition==null)
            this.systemControl.answerCompetition=new AnswerCompetition(this.systemControl);
        this.systemControl.answerCompetition.addAllToAnswerCompetition();
    }
     /**
    * METHOD: addStudentToAnswerCompetition
    * DESC  : 加单个学生到抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addStudentToAnswerCompetition(String userID)
    {
        if(this.systemControl.answerCompetition==null)
            this.systemControl.answerCompetition=new AnswerCompetition(this.systemControl);
        this.systemControl.answerCompetition.addStudentToAnswerCompetition(userID);
    }

   /**
    * METHOD: notifyStartAnswerCompetition
    * DESC  : 通知并启动抢答（学生）。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void notifyStartAnswerCompetition(Vector userIDs,String port)
    {
        if(this.systemControl.answerCompetition==null)
            this.systemControl.answerCompetition=new AnswerCompetition(this.systemControl);
        this.systemControl.answerCompetition.notifyStartAnswerCompetition(userIDs,port);
    }
   /**
    * METHOD: beginAnswerCompetition
    * DESC  : 开始抢答(老师)。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void beginAnswerCompetition(Vector userIDs)
    {
        this.systemControl.answerCompetition.beginAnswerCompetition(userIDs);
    }
   /**
    * METHOD: notifyBeginOrEndAnswerCompetition
    * DESC  : 开始或结束抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void notifyBeginOrEndAnswerCompetition(boolean b)
    {
        this.systemControl.answerCompetition.notifyBeginOrEndAnswerCompetition(b);
    }
    /**
    * METHOD: endAnswerCompetition
    * DESC  : 结束抢答(老师)。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void endAnswerCompetition(Vector userIDs)
    {
        this.systemControl.answerCompetition.endAnswerCompetition(userIDs);
    }
   /**
    * METHOD: sendInformationToStudent
    * DESC  : 送消息给某学生。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void sendInformationToStudent(String userID,String info)
    {
        try
        {
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            student=(Student)htUser.get(userID);
            if(student==null) return;
            student.sendInformation(info);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   /**
    * METHOD: sendInformationToTeacher
    * DESC  : 送消息给老师。
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public void sendInformationToTeacher(String info)
    {
        try
        {
            teacher=systemControl.getTeacherInterface();
            if(teacher==null) return;
            teacher.sendInformation(info);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   /**
    * METHOD: voiceBroadCast
    * DESC  : 语音广播
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void voiceBroadCast(Vector userIDs,Teacher teacher)
    {
        try
        {
            String port=this.systemControl.jmfApi.audioTransmit(AUDIOURL,null,true);
            if(port==null) return;
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                student=(Student)htUser.get((String)userIDs.elementAt(i));
                if(student==null) continue;
                student.voiceReceive(port);
            }
            if(teacher!=null)
            {
                teacher.voiceReceive(port);
            }
        }
        catch(Exception e)
        {
            System.out.println("Communication voiceBroadCast Exception:"+e.getMessage());
            e.printStackTrace();
        }
    }

   /**
    * METHOD: startVoiceReceive
    * DESC  : 启动广播语音接收。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void startVoiceReceive(String port)
    {
        boolean b=this.systemControl.jmfApi.audioRecieve(BROADCASTIP,port);
    }
   /**
    * METHOD: stopVoiceBroadCast
    * DESC  : 停止语音广播。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void stopVoiceBroadCast(Vector userIDs,Teacher teacher)
    {
        try
        {
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                student=(Student)htUser.get((String)userIDs.elementAt(i));
                if(student==null) continue;
                student.endMediaReceive();
            }
            if(teacher!=null)
            {
                teacher.endMediaReceive();
            }
            closeMedia();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   /**
    * METHOD: closeMedia
    * DESC  : 关闭声音图像播放。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void closeMedia()
    {
        try
        {
            this.systemControl.jmfApi.stopJMFDevice();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   /**
    * METHOD: allowAnswer
    * DESC  : 是否允许回答（老师）。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void allowAnswer(String userID,boolean b)
    {
        this.systemControl.answerCompetition.allowAnswer(userID,b);
    }
   /**
    * METHOD: notifyAllowAnswer
    * DESC  : 通知学生是否允许回答。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void notifyAllowAnswer(boolean b)
    {
        this.systemControl.jDialogAnswerCompetitionStudent.setBeginAnswer(b);
    }

   /**
    * METHOD:  studentAnswerCompetition
    * DESC  :  学生抢答
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public void studentAnswerCompetition(String userID)
    {
        this.systemControl.answerCompetition.studentAnswerCompetition(userID);
    }
   /**
    * METHOD:  answerCompetitionSequence
    * DESC  :  （老师）处理抢答顺序
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public void answerCompetitionSequence(String userID)
    {
        this.systemControl.answerCompetition.answerCompetitionSequence(userID);
    }
    /**
    * METHOD: addAnswerCompetitionSequence
    * DESC  : 加入抢答学生。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addAnswerCompetitionSequence(String userID)
    {
        this.systemControl.answerCompetition.addAnswerCompetitionSequence(userID);
    }
    /**
    * METHOD: listenPlay
    * DESC  : 听力播放
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void listenPlay(Vector userIDs,Teacher teacher,String url,String port)
    {
        try
        {
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                student=(Student)htUser.get((String)userIDs.elementAt(i));
                if(student==null) return;
                student.listenReceive(port);
            }
            if(teacher!=null)
            {
                teacher.listenReceive(port);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

   /**
    * METHOD: startListenReceive
    * DESC  : 启动听力接收。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void startListenReceive(String port)
    {
        boolean b=this.systemControl.jmfApi.audioRecieve(BROADCASTIP,port);
    }
   /**
    * METHOD: stopListenPlay
    * DESC  : 停止听力播放。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void stopListenPlay(Vector userIDs,Teacher teacher)
    {
        try
        {
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                student=(Student)htUser.get((String)userIDs.elementAt(i));
                if(student==null) return;
                student.endMediaReceive();
            }
            if(teacher!=null)
            {
                teacher.endMediaReceive();
            }
            closeMedia();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   /**
    * MOTHOD: showInfoMsg
    * DESC  : 显示信息消息。
    * CREATE: 1.0 Ardy 2001-09-29
    * MODIFY:
    */
    public void showInfoMsg(Component parentComponent,String infoMsg)
    {
        JOptionPane pane = new javax.swing.JOptionPane();

        parentComponent.setEnabled(false);
        try
        {
            pane.showMessageDialog(systemControl.mainFrm, "远程信息：" + infoMsg,"课堂交流",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            System.out.println("信息对话框->" + e.getMessage());
        }
        finally
        {
              parentComponent.setEnabled(true);
        }

    }
}
