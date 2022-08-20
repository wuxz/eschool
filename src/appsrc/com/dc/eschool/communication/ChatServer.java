package com.dc.eschool.communication;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Random;
import com.dc.eschool.systemControl.*;
import com.dc.eschool.system.*;
public class ChatServer
{
    private Hashtable groupNamePort=new Hashtable();
    private SystemControl systemControl=null;
    private Student student=null;
    public ChatServer(SystemControl sysControl)
    {
        this.systemControl=sysControl;
        Vector groups=systemControl.userGroup.getGroupNames();
        String port="";
        for(int i=0;i<groups.size();i++)
        {

            String groupName=(String)groups.elementAt(i);
            do
            {
                port = this.systemControl.jmfApi.randomPort();
            }
            while(groupNamePort.containsValue(port)==true);
            groupNamePort.put(groupName,port);
        }
    }

   /**
    * METHOD: getPortByGroupName
    * DESC  : 获取端口号。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public String  getPortByGroupName(String groupName)
    {
        System.out.println("groupNamePort.size="+groupNamePort.size());
        String port=(String)groupNamePort.get(groupName);
        return port;
    }
    /**
    * METHOD: addAllToOnlineTalk
    * DESC  : 加全部学生抢答。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addAllToOnlineTalk()
    {
        try
        {
            if(systemControl.jDialogOnlineTalk==null)
            {
                systemControl.jDialogOnlineTalk=new JDialogOnlineTalk(systemControl);
                systemControl.jDialogOnlineTalk.setSize(730,550);
                systemControl.jDialogOnlineTalk.show();
            }
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(Enumeration er=htUser.keys(); er.hasMoreElements();)
            {
                String id=(String)er.nextElement();
                boolean b=addStudentToOnlineTalk(id);
                if(b==false) continue;
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   /**
    * METHOD: addGroupToOnlineTalk
    * DESC  : 加组到联机讨论。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public void addGroupToOnlineTalk(String userID)//此处userID,换作组名也成立（稍作修改）
    {
        try
        {
            if(systemControl.jDialogOnlineTalk==null)
            {
                systemControl.jDialogOnlineTalk=new JDialogOnlineTalk(systemControl);
                systemControl.jDialogOnlineTalk.setSize(730,550);
                systemControl.jDialogOnlineTalk.show();
            }
            Vector userIDs=systemControl.userGroup.getUserIDsByUserID(userID);
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            for(int i=0;i<userIDs.size();i++)
            {
                String id=(String)userIDs.elementAt(i);
                student=(Student)htUser.get(id);
                if(student==null) continue;
                boolean b=addStudentToOnlineTalk(id);
                if(b==false) continue;
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
     /**
    * METHOD: addStudentToOnlineTalk
    * DESC  : 加单个学生到联机讨论。
    * CREATE: 1.0 Ardy 2001-11-06
    * MODIFY:
    */
    public boolean addStudentToOnlineTalk(String userID)
    {
        try
        {
            if(systemControl.jDialogOnlineTalk==null)
            {
                systemControl.jDialogOnlineTalk=new JDialogOnlineTalk(systemControl);
                systemControl.jDialogOnlineTalk.setSize(730,550);
                systemControl.jDialogOnlineTalk.show();
            }
            boolean b=systemControl.onlineTalkStudentListModel.addItem(userID);
            if(b==false) return b;
            systemControl.onlineTalkGroupLIstModel.updateGroup();
            Hashtable htUser=systemControl.getHtStudentIDInterface();
            Hashtable userIDs=systemControl.onlineTalkStudentListModel.getUserIDData();
            System.out.println("userIDs size="+userIDs.size());
            for(Enumeration er=userIDs.keys(); er.hasMoreElements();)
            {
                String groupName=(String)er.nextElement();
                String port=(String)groupNamePort.get(groupName);
                Vector ids=(Vector)userIDs.get(groupName);
                System.out.println("ids size:"+ids.size());
                System.out.println("groupName:"+groupName);
                for(int i=0;i<ids.size();i++)
                {
                    String id=(String)ids.elementAt(i);
                    student=(Student)htUser.get(id);
                    if(student==null) continue;
                    student.notifyStartOnlineTalk(userIDs,port);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    /**
    * METHOD: notifyStartOnlineTalk
    * DESC  : 启动学生端联机讨论界面。
    * CREATE: 1.0 Ardy 2001-10-19
    * MODIFY:
    */
    public void  notifyStartOnlineTalk(Hashtable userIDs,String port)
    {
        try
        {
            System.out.println("ChatServer's notifyStartOnlineTalk begin..");
            if(systemControl.jDialogOnlineTalkStudent==null)
            {
                systemControl.jDialogOnlineTalkStudent=new JDialogOnlineTalkStudent(systemControl,port,userIDs);
                systemControl.jDialogOnlineTalkStudent.setSize(730,550);
                systemControl.jDialogOnlineTalkStudent.show();
            }
            else
            {
                systemControl.onlineTalkStudentListModel.setData(userIDs);
                systemControl.onlineTalkGroupLIstModel.updateGroup();
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    /**
    * METHOD: endOnlineTalk
    * DESC  : 结束联机讨论。
    * CREATE: 1.0 Ardy 2001-10-25
    * MODIFY:
    */
    public void endOnlineTalk()
    {
        try
        {
            Hashtable HtUser=this.systemControl.onlineTalkStudentListModel.getUserIDData();
            Hashtable HtInterface=this.systemControl.getHtStudentIDInterface();
            for(Enumeration er=HtUser.elements(); er.hasMoreElements();)
            {
                Vector userIDs=(Vector)er.nextElement();
                for(int i=0;i<userIDs.size();i++)
                {
                    String userID=(String)userIDs.elementAt(i);
                    student=(Student)HtInterface.get(userID);
                    if(student==null) continue;
                    student.notifyEndOnlineTalk();
                }
            }
            systemControl.chatServer=null;
            systemControl.jDialogOnlineTalk.dispose();
            systemControl.jmfApi.stopJMFDevice();
            systemControl.jDialogOnlineTalk=null;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            systemControl.chatServer=null;
            systemControl.jDialogOnlineTalk=null;
        }
    }
}
