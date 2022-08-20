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
   private static final String ASKFOR="������";
   private SystemControl systemControl;
   private Student student=null;
   private Teacher teacher=null;
   private String studentIP;
   //���캯�����
   public Communication(SystemControl sysControl)
   {
      this.systemControl=sysControl;
      if(this.systemControl.jmfApi==null)
      this.systemControl.jmfApi=new JMFAPI(sysControl);
   }
  /**
   * METHOD: askForTeacher
   * DESC  : ѧ��������ʦ��
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
   * DESC  : ��ʦ�������
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
    * DESC  : Ӧ��ѧ������
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
   * DESC  : �����ʦӦ��
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
    * DESC  : ��ȫ��ѧ������
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
    * DESC  : ���鵽�������ۡ�
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addGroupToOnlineTalk(String userID)//�˴�userID,��������Ҳ�����������޸ģ�
    {
        if(this.systemControl.chatServer==null)
            this.systemControl.chatServer=new ChatServer(this.systemControl);
        this.systemControl.chatServer.addGroupToOnlineTalk(userID);
    }
   /**
    * METHOD: addStudentToOnlineTalk
    * DESC  : �ӵ���ѧ�����������ۡ�
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
    * DESC  : ����ѧ�����������۽��档
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
    * DESC  : �����������ۡ�
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void endOnlineTalk()
    {
        this.systemControl.chatServer.endOnlineTalk();
    }
   /**
    * METHOD: notifyEndOnlineTalk
    * DESC  : ����ѧ�����������۽��档
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void notifyEndOnlineTalk()
    {
        JDialogMessageBox jDialogMessageBox=new JDialogMessageBox("�������۽�����",this.systemControl);
        jDialogMessageBox.setSize(200,100);
        jDialogMessageBox.show();
        systemControl.jDialogOnlineTalkStudent.dispose();
        systemControl.jmfApi.stopJMFDevice();
        systemControl.jDialogOnlineTalkStudent=null;
    }
    /**
    * METHOD: closeAnswerCompetition
    * DESC  : ��ʦ�ر�����
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void closeAnswerCompetition(Vector userIDs)
    {
        this.systemControl.answerCompetition.closeAnswerCompetition(userIDs);
    }
   /**
    * METHOD: notifyCloseAnswerCompetition
    * DESC  : ����ѧ�����������۽��档
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void notifyCloseAnswerCompetition()
    {
        JDialogMessageBox jDialogMessageBox=new JDialogMessageBox("���������",this.systemControl);
        jDialogMessageBox.setSize(200,100);
        jDialogMessageBox.show();
        systemControl.jDialogAnswerCompetitionStudent.dispose();
        systemControl.jmfApi.stopJMFDevice();
        systemControl.jDialogAnswerCompetitionStudent=null;
    }
   /**
    * METHOD: startDemonstrate
    * DESC  : ��Ļ��ʾ��������ѧ������
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
    * DESC  : ��Ļ��ʾ(��һ��ѧ��)��
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
    * DESC  : ѧ����ʾ��
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
    * DESC  : ѧ����Ļ��ʾ��
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
    * DESC  : ֪ͨ������ѧ����Ļ��ʾ��
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
    * DESC  : ����ʦ��������Ļ��ʾ��
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
    * DESC  : ֪ͨ������ѧ����Ļ��ʾ��
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void notifyEndDemonstrate()
    {
        closeMedia();

    }
   /**
    * METHOD: addGroupToAnswerCompetition
    * DESC  : ����ѧ������
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addGroupToAnswerCompetition(String userID)//�˴�userID,��������Ҳ�����������޸ģ�
    {
        if(this.systemControl.answerCompetition==null)
            this.systemControl.answerCompetition=new AnswerCompetition(this.systemControl);
        this.systemControl.answerCompetition.addGroupToAnswerCompetition(userID);
    }
    /**
    * METHOD: addAllToAnswerCompetition
    * DESC  : ��ȫ��ѧ������
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
    * DESC  : �ӵ���ѧ��������
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
    * DESC  : ֪ͨ����������ѧ������
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
    * DESC  : ��ʼ����(��ʦ)��
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void beginAnswerCompetition(Vector userIDs)
    {
        this.systemControl.answerCompetition.beginAnswerCompetition(userIDs);
    }
   /**
    * METHOD: notifyBeginOrEndAnswerCompetition
    * DESC  : ��ʼ���������
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void notifyBeginOrEndAnswerCompetition(boolean b)
    {
        this.systemControl.answerCompetition.notifyBeginOrEndAnswerCompetition(b);
    }
    /**
    * METHOD: endAnswerCompetition
    * DESC  : ��������(��ʦ)��
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void endAnswerCompetition(Vector userIDs)
    {
        this.systemControl.answerCompetition.endAnswerCompetition(userIDs);
    }
   /**
    * METHOD: sendInformationToStudent
    * DESC  : ����Ϣ��ĳѧ����
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
    * DESC  : ����Ϣ����ʦ��
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
    * DESC  : �����㲥
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
    * DESC  : �����㲥�������ա�
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void startVoiceReceive(String port)
    {
        boolean b=this.systemControl.jmfApi.audioRecieve(BROADCASTIP,port);
    }
   /**
    * METHOD: stopVoiceBroadCast
    * DESC  : ֹͣ�����㲥��
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
    * DESC  : �ر�����ͼ�񲥷š�
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
    * DESC  : �Ƿ�����ش���ʦ����
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void allowAnswer(String userID,boolean b)
    {
        this.systemControl.answerCompetition.allowAnswer(userID,b);
    }
   /**
    * METHOD: notifyAllowAnswer
    * DESC  : ֪ͨѧ���Ƿ�����ش�
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void notifyAllowAnswer(boolean b)
    {
        this.systemControl.jDialogAnswerCompetitionStudent.setBeginAnswer(b);
    }

   /**
    * METHOD:  studentAnswerCompetition
    * DESC  :  ѧ������
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public void studentAnswerCompetition(String userID)
    {
        this.systemControl.answerCompetition.studentAnswerCompetition(userID);
    }
   /**
    * METHOD:  answerCompetitionSequence
    * DESC  :  ����ʦ����������˳��
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public void answerCompetitionSequence(String userID)
    {
        this.systemControl.answerCompetition.answerCompetitionSequence(userID);
    }
    /**
    * METHOD: addAnswerCompetitionSequence
    * DESC  : ��������ѧ����
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addAnswerCompetitionSequence(String userID)
    {
        this.systemControl.answerCompetition.addAnswerCompetitionSequence(userID);
    }
    /**
    * METHOD: listenPlay
    * DESC  : ��������
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
    * DESC  : �����������ա�
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void startListenReceive(String port)
    {
        boolean b=this.systemControl.jmfApi.audioRecieve(BROADCASTIP,port);
    }
   /**
    * METHOD: stopListenPlay
    * DESC  : ֹͣ�������š�
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
    * DESC  : ��ʾ��Ϣ��Ϣ��
    * CREATE: 1.0 Ardy 2001-09-29
    * MODIFY:
    */
    public void showInfoMsg(Component parentComponent,String infoMsg)
    {
        JOptionPane pane = new javax.swing.JOptionPane();

        parentComponent.setEnabled(false);
        try
        {
            pane.showMessageDialog(systemControl.mainFrm, "Զ����Ϣ��" + infoMsg,"���ý���",JOptionPane.INFORMATION_MESSAGE);
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
}
