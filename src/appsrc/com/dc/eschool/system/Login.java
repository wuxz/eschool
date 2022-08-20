//Source file: D:\\yan_work\\eschool\\src\\com\\dc\\eschool\\System\\Login.java
package com.dc.eschool.system;
import java.util.Vector;
import java.util.Hashtable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.net.InetAddress;
import java.awt.Frame;
import com.dc.eschool.group.*;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.communication.*;
import com.dc.eschool.DataModel.GroupUserModel;
/**
 * Title:        Login
 * Description:  系统登录
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
public class Login implements LoginIF
{
    private SystemControl systemControl;
    private Vector vecUsers;//存储数据库返回的用户
    private String userID;
    private boolean verifyPass=true;
    private String userType;
    public Login(){}
    public Login(SystemControl sysControl)
    {
        try
        {
            this.systemControl=sysControl;
            JDialogLogin jDialogLogin=new JDialogLogin(this.systemControl,this);
            //jDialogLogin.setSize(300,172);
            jDialogLogin.show();
            verifyPass=jDialogLogin.verifyPass;
            Hashtable htCourse=jDialogLogin.htCourse;
            if (verifyPass==true)
            {
                userType=(String)htCourse.get("type");
                userID=(String)htCourse.get("userid");
                JDialogCourse jDialogCourse=new JDialogCourse(this.systemControl,this,htCourse);
                jDialogCourse.show();
                vecUsers=jDialogCourse.vecUsers;
            }
            System.out.println("login ok");
            startRemoteServer();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

   /**
    * METHOD: verifyPassword
    * DESC  : 验证用户名，密码
    * CREATE: 1.0, Ardy, 2001-09-29
    * MODIFY:
    */
    public Hashtable verifyPassword(String userName,char[] password)
    {
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<password.length;i++)
        {
           sb.append(password[i]);
        }
        String psd=sb.toString();
        Hashtable htCourse;
        htCourse=this.systemControl.ejbAccess.verifyPassword(userName,psd);
        return htCourse;
    }

   /**
    * METHOD: login
    * DESC  : 选择课程登录
    * CREATE: 1.0, Ardy, 2001-09-29
    * MODIFY:
    */
    public Vector login(String courseid,String ip,String userID)
    {
        Vector vecUser;
        vecUser=this.systemControl.ejbAccess.login(courseid,ip,userID);
        return vecUser;
    }
   /**
    * METHOD: getLocalIP
    * DESC  :获取本地IP
    * CREATE: 1.0, Ardy, 2001-09-29
    * MODIFY:
    */
    public String getLocalIP() throws UnknownHostException
    {
        String ip=null;
        try
        {
            InetAddress inetAddress=InetAddress.getLocalHost();
            ip=inetAddress.getHostAddress();
        }
        catch(UnknownHostException ex)
        {
            System.out.println(ex.getMessage());
        }
        return ip;
    }
    /**
    * METHOD: EjbAccess_verifyPassword
    * DESC  : ejbAccess's Method
    * CREATE: 1.0, Ardy, 2001-09-29
    * NOTE  : 测试用
    * MODIFY:
    */
    Vector EjbAccess_verifyPassword(String username,String psd)
    {
        Vector vec=new Vector();
        if (username.equals("1")&& psd.equals("1"))
        {
            vec.addElement("0");
            vec.addElement("Mr.Lee's English");
            vec.addElement("Mr.Lee's Math");
            vec.addElement("Mr.Lee's Physics");
        }
        else if (username.equals("2")&& psd.equals("2"))
        {
            vec.addElement("1");
            vec.addElement("Mr.Lee's English");
            vec.addElement("Mr.Lee's Math");
            vec.addElement("Mr.Lee's Physics");
        }
        return vec;
    }

    /**
    * METHOD: startRemoteServer
    * DESC  : 老师或学生创建连接远程服务。
    * CREATE: 1.0 Ardy 2001-10-08
    * MODIFY:
    */
    void startRemoteServer()
    {
        try
        {
            String teacherIP;
            GroupUserModel teacherUser;
            EschoolUser tUser;
            if(vecUsers.size()==0)
            {
                verifyPass=false;
                return;
            }
            String userIP=getLocalIP();
            if (userType.equals(EschoolUser.ESCHOOL_TEACHER))
            {
                teacherUser=(GroupUserModel)vecUsers.elementAt(0);
                String id=teacherUser.getUserID();
                String name=teacherUser.getUserName();
                String gender=teacherUser.getUserGender();
                gender=EschoolUser.turnGenderIntoID(gender);
                String ip=teacherUser.getUserIP();
                //String status=teacherUser.getUserStatus();
                String status=EschoolUser.ABESENT;
                String type=teacherUser.getUserType();
                String handup=EschoolUser.ABESENT;
                String examing=EschoolUser.ABESENT;
                String calling=EschoolUser.ABESENT;
                tUser=new EschoolUser(id,name,type,status,gender,ip,handup,examing,calling);
                Vector users=new Vector();
                Hashtable htUser=new Hashtable();
                for(int i=1;i<vecUsers.size();i++)
                {
                    try
                    {
                        //users.addElement(vecUsers.elementAt(i));
                        GroupUserModel eschoolUser=(GroupUserModel)vecUsers.elementAt(i);
                        if(eschoolUser==null) return;
                        String userid=eschoolUser.getUserID();
                        String usernm=eschoolUser.getUserName();
                        String userGender=eschoolUser.getUserGender();
                        userGender=EschoolUser.turnGenderIntoID(userGender);
                        String IP=eschoolUser.getUserIP();
                        if(IP==null) IP="";
                        String userStatus=EschoolUser.ABESENT;
                        String userType=eschoolUser.getUserType();
                        EschoolUser eUser=new EschoolUser(userid,usernm,userType,userStatus,userGender,IP,EschoolUser.ABESENT,EschoolUser.ABESENT,EschoolUser.ABESENT);
                        users.addElement(eUser);
                        htUser.put(userid,usernm);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                systemControl.teacherImpl=new TeacherImpl(systemControl);
                systemControl.userGroup= new UserGroup(users,tUser,this.systemControl);
                //EschoolUser eschoolUser=systemControl.userGroup.getTheTeacher();//
                systemControl.setUserIP(userIP);
                systemControl.setUserType(tUser.getUserType());
                systemControl.setUserID(tUser.getUserID());
                systemControl.setUserName(tUser.getUserName());
                systemControl.setTeacherIP(userIP);
                systemControl.setTeacherName(tUser.getUserName());
                systemControl.setHtStudentIDName(htUser);

            }
            else
            {
                //teacherIP=(String)vecUsers.elementAt(0);
                teacherUser=(GroupUserModel)vecUsers.elementAt(0);
                teacherIP=teacherUser.getUserIP();
                Vector users=new Vector();
                Hashtable htUser=new Hashtable();
                for(int i=1;i<vecUsers.size();i++)
                {
                    GroupUserModel eschoolUser=(GroupUserModel)vecUsers.elementAt(i);
                    String id=eschoolUser.getUserID();
                    String nm=eschoolUser.getUserName();
                    String gender=eschoolUser.getUserGender();
                    gender=EschoolUser.turnGenderIntoID(gender);
                    String ip=eschoolUser.getUserIP();
                    if(ip==null) ip="";
                    String status=eschoolUser.getUserStatus();
                    String type=eschoolUser.getUserType();
                    EschoolUser eUser=new EschoolUser(id,nm,type,status,gender,ip,EschoolUser.ABESENT,EschoolUser.ABESENT,EschoolUser.ABESENT);
                    users.addElement(eUser);
                    htUser.put(id,nm);
                }
                systemControl.setUserID(userID);
                systemControl.setUserIP(userIP);
                systemControl.studentImpl=new StudentImpl(systemControl,teacherIP,userIP);
                systemControl.setUserName(systemControl.userGroup.getUserNameByUserID(userID));
                systemControl.setUserType(systemControl.userGroup.getUserTypeByUserID(userID));
                systemControl.setTeacherIP(teacherIP);
                systemControl.setTeacherName(teacherUser.getUserName());
                systemControl.setHtStudentIDName(htUser);

            }
            systemControl.communication=new Communication(systemControl);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   /**
    * METHOD: getVerifyResult
    * DESC  : 获取验证结果。
    * CREATE: 1.0 Ardy 2001-10-08
    * MODIFY:
    */
    public boolean getVerifyResult()
    {
        return verifyPass;
    }
}
