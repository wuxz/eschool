package com.dc.eschool.system;

/**
 * Title:        Logout
 * Description:   退出，注销
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.group.EschoolUser;
import java.util.Hashtable;
import java.util.Enumeration;
public class Logout
{
   private SystemControl systemControl;
  public Logout(SystemControl sysControl)
  {
      this.systemControl=sysControl;
  }
  /**
   * METHOD: logout
   * DESC  : 退出服务。
   * CREATE: 1.0 Ardy 2001-11-28
   * MODIFY:
   */
  public boolean logout()
  {
      boolean b=true;
      try
      {
          String userType=this.systemControl.getUserType();
          if(userType==null) return false;
          if(userType.equals(EschoolUser.ESCHOOL_TEACHER))
          b=teacherFinishedServer();
          else
          b=studentFinishedServer();

      }
      catch(Exception e)
      {
          System.out.println("logout Exception:"+e.getMessage());
          e.printStackTrace();
          return false;
      }
      return b;
  }
  /**
   * METHOD: studentFinishedServer
   * DESC  : 学生退出服务。
   * CREATE: 1.0 Ardy 2001-11-28
   * MODIFY:
   */
  boolean studentFinishedServer()
  {
      boolean b=true;
      try
      {
          Teacher teacher=this.systemControl.getTeacherInterface();
          b=teacher.logout(this.systemControl.getUserID());
      }
      catch(Exception e)
      {
          System.out.println("studentFinishedServer Exception:"+e.getMessage());
          e.printStackTrace();
          return false;
      }
      return b;
  }
  /**
   * METHOD: teacherFinishedServer
   * DESC  : 老师退出服务。
   * CREATE: 1.0 Ardy 2001-11-28
   * MODIFY:
   */
  boolean teacherFinishedServer()
  {
      boolean b=true;
      try
      {
          b=this.systemControl.ejbAccess.logout(this.systemControl.getCourseID());
          Hashtable htUser=this.systemControl.getHtStudentIDInterface();
          for(Enumeration er=htUser.elements(); er.hasMoreElements();)
          {
              Student student=(Student)er.nextElement();
              b=student.logout();
          }
      }
      catch(Exception e)
      {
          System.out.println("teacherFinishedServer Exception:"+e.getMessage());
          e.printStackTrace();
          return false;
      }
      return b;
  }
}