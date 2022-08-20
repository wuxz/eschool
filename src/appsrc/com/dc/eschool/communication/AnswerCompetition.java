package com.dc.eschool.communication;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import com.dc.eschool.group.*;
import com.dc.eschool.system.*;
import java.util.Random;
import com.dc.eschool.systemControl.SystemControl;
public class AnswerCompetition
{

    private String port=null;
    private SystemControl systemControl=null;
    private Student student=null;
    private Teacher teacher=null;
    private Random randomInt=new Random(9500);
    public AnswerCompetition(SystemControl sysControl)
    {
        this.systemControl=sysControl;
        //port=randomPort();
        if(this.systemControl.jmfApi!=null)
            port=this.systemControl.jmfApi.randomPort();
        else
            port="5555";
    }
    /**
    * METHOD: addGroupToAnswerCompetition
    * DESC  : 加组学生抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addGroupToAnswerCompetition(String userID)//此处userID,换作组名也成立（稍作修改）
    {
        try
        {
            if(systemControl.jDialogAnswerCompetition==null)
            {
                systemControl.jDialogAnswerCompetition=new JDialogAnswerCompetition(systemControl,port);
                systemControl.jDialogAnswerCompetition.setSize(730,550);
                systemControl.jDialogAnswerCompetition.show();
            }
            systemControl.jDialogAnswerCompetition.show();
            Vector userIDs=systemControl.userGroup.getUserIDsByUserID(userID);
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                String id=(String)userIDs.elementAt(i);
                student=(Student)htUser.get(id);
                if(student==null) continue;
                boolean b=addStudentToAnswerCompetition(id);
                if(b==false) continue;
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    /**
    * METHOD: addAllToAnswerCompetition
    * DESC  : 加全部学生抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addAllToAnswerCompetition()
    {
        try
        {
            if(systemControl.jDialogAnswerCompetition==null)
            {
                systemControl.jDialogAnswerCompetition=new JDialogAnswerCompetition(systemControl,port);
                systemControl.jDialogAnswerCompetition.setSize(730,600);
                systemControl.jDialogAnswerCompetition.show();
            }
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(Enumeration er=htUser.keys(); er.hasMoreElements();)
            {
                String id=(String)er.nextElement();
                boolean b=addStudentToAnswerCompetition(id);
                if(b==false) continue;
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
     /**
    * METHOD: addStudentToAnswerCompetition
    * DESC  : 加单个学生到抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public boolean addStudentToAnswerCompetition(String userID)
    {
        try
        {
            if(systemControl.jDialogAnswerCompetition==null)
            {
                systemControl.jDialogAnswerCompetition=new JDialogAnswerCompetition(systemControl,port);
                systemControl.jDialogAnswerCompetition.setSize(730,650);
                systemControl.jDialogAnswerCompetition.show();

            }
            boolean b=systemControl.answerCompetitionListModelStudent.addItem(userID);
            if(b==false) return b;
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            Vector userIDs=systemControl.answerCompetitionListModelStudent.getUserIDData();
            for(int i=0;i<userIDs.size();i++)
            {
                student=(Student)htUser.get((String)userIDs.elementAt(i));
                if(student==null) continue;
                student.notifyStartAnswerCompetition(userIDs,port);
            }
        }
        catch(Exception e)
        {
            System.out.println("AnswerCompetition Exception:"+e.getMessage());
            e.printStackTrace();
        }
        return true;
    }
    /**
    * METHOD: notifyStartAnswerCompetition
    * DESC  : 通知并启动抢答（学生）。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void notifyStartAnswerCompetition(Vector userIDs,String port)
    {
        try
        {
            if(systemControl.jDialogAnswerCompetitionStudent==null)
            {
                systemControl.jDialogAnswerCompetitionStudent=new JDialogAnswerCompetitionStudent(systemControl,port,userIDs);
                systemControl.jDialogAnswerCompetitionStudent.setSize(730,550);
                systemControl.jDialogAnswerCompetitionStudent.show();

            }
            else
            systemControl.answerCompetitionListModelStudent.setData(userIDs);

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    /**
    * METHOD: beginAnswerCompetition
    * DESC  : 开始抢答(老师)。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void beginAnswerCompetition(Vector userIDs)
    {
        try
        {
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                String id=(String)userIDs.elementAt(i);
                String type=this.systemControl.userGroup.getUserTypeByUserID(id);
                student=(Student)htUser.get(id);
                if(student==null||type.equals(EschoolUser.ESCHOOL_AUDITOR)) continue;
                student.notifyBeginOrEndAnswerCompetition(true);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   /**
    * METHOD: notifyBeginOrEndAnswerCompetition
    * DESC  : 开始或结束抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void notifyBeginOrEndAnswerCompetition(boolean b)
    {
        systemControl.jDialogAnswerCompetitionStudent.setBeginAnswerCompetition(b);
    }
    /**
    * METHOD: endAnswerCompetition
    * DESC  : 结束抢答(老师)。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void endAnswerCompetition(Vector userIDs)
    {
        try
        {
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                String id=(String)userIDs.elementAt(i);
                String type=this.systemControl.userGroup.getUserTypeByUserID(id);
                student=(Student)htUser.get(id);
                if(student==null||type.equals(EschoolUser.ESCHOOL_AUDITOR)) continue;
                student.notifyBeginOrEndAnswerCompetition(false);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    /**
    * METHOD:  studentAnswerCompetition
    * DESC  :  学生抢答
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public void studentAnswerCompetition(String userID)
    {
        try
        {
            teacher=systemControl.getTeacherInterface();
            teacher.studentAnswerCompetition(userID);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    /**
    * METHOD:  answerCompetitionSequence
    * DESC  :  （老师）处理抢答顺序
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public void answerCompetitionSequence(String userID)
    {
        try
        {
            this.systemControl.answerCompetitionSequenceListModel.addItem(userID);
            Vector userIDs=this.systemControl.answerCompetitionListModelStudent.getUserIDData();
            Hashtable HtUser=this.systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                String id=(String)userIDs.elementAt(i);
                student=(Student)HtUser.get(id);
                if(student==null) continue;
                student.addAnswerCompetitionSequence(userID);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
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
        this.systemControl.answerCompetitionSequenceListModel.addItem(userID);
    }
    /**
    * METHOD: allowAnswer
    * DESC  : 是否允许回答（老师）。
    * CREATE: 1.0 Ardy 2001-11-07
    * MODIFY:
    */
    public void allowAnswer(String userID,boolean b)
    {
        try
        {
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            String type=this.systemControl.userGroup.getUserTypeByUserID(userID);
            if(type.equals(EschoolUser.ESCHOOL_AUDITOR)) return;
            student=(Student)htUser.get(userID);
            student.notifyAllowAnswer(b);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    /**
    * METHOD: randomPort
    * DESC  :  生成随机端口
    * CREATE: 1.0 Ardy 2001-11-08
    * MODIFY:
    */
    public String randomPort()
    {
        int intPort=randomInt.nextInt(1000);
        intPort = intPort +1024;
        return new Integer(intPort).toString();
    }
   /**
    * METHOD: closeAnswerCompetition
    * DESC  : 关闭抢答。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void closeAnswerCompetition(Vector userIDs)
    {
        try
        {
            Hashtable HtUser=this.systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                String userID=(String)userIDs.elementAt(i);
                student=(Student)HtUser.get(userID);
                if(student==null) continue;
                student.notifyCloseAnswerCompetition();
            }
            systemControl.jDialogAnswerCompetition.dispose();
            systemControl.jDialogAnswerCompetition=null;
            systemControl.jmfApi.stopJMFDevice();
            systemControl.answerCompetition=null;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            systemControl.answerCompetition=null;
            systemControl.jDialogAnswerCompetition=null;
        }
    }

}