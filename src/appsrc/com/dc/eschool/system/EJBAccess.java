package com.dc.eschool.system;

import java.util.*;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import com.dc.eschool.DataModel.GroupUserModel;
import com.dc.eschool.rmi.ejb.RMIInterfaceSL;
import com.dc.eschool.rmi.ejb.RMIInterfaceSLHome;

/**
 * Title:         EJB接口类
 * Description:   连接EJB来得到用户所需的数据信息和修改数据库中的数据信息
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class EJBAccess
{
   /**   EJB远程接口    */
   private RMIInterfaceSL     eschoolRMI=null;
   /**   构造器         */
   public EJBAccess()
   {
       try
       {
           //Hashtable ht = new Hashtable();
           //ht.put(javax.naming.InitialContext.PROVIDER_URL,"IIOP://192.168.0.38:1050/");
           //ht.put(javax.naming.InitialContext.INITIAL_CONTEXT_FACTORY,"javax.naming.spi.InitialContextFactory");

           Context jndiContext=new InitialContext();
           System.out.println("Context已创建！");
           Object ref=jndiContext.lookup("eschool/RMIInterfaceManager");
           RMIInterfaceSLHome home=(RMIInterfaceSLHome)PortableRemoteObject.narrow(ref,
                                                         RMIInterfaceSLHome.class);
           if (home==null)
           {
               System.out.println("HOME接口为空！");
           }
           eschoolRMI=home.create();

           if (eschoolRMI==null)
           {
               System.out.println("REMOTE接口为空！");
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }

   /**
    * 获得用户的所有信息。主要包括：用户ID、用户姓名、用户性别、
    * 用户出生日期、用户电话号码、用户的类型等。
    */
   public Collection getUserInfo(String userID)
   {
       try
       {
           Collection colTemp=eschoolRMI.getUserInfo(userID);
           return colTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }

   /**
    * 通过用户名获得用户的信息，主要包括：用户ID、用户姓名、
    * 用户性别、用户出生日期、用户电话号码、用户的类型等。
    */
   public Collection getUserInfoByName(String userName)
   {
       /**
        * 程序在后期改动中，去掉了这个功能。
        */
       return null;

   }

   /** 保存修改后用户的信息  */
   public void saveUserInfo(String userID,String name, String gender, String birthday, String userType, String telephone)
   {
       try
       {
           eschoolRMI.saveUserInfo(userID, name, gender, birthday, userType, telephone);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }

   /** 获得所有的课程信息。主要包括：课程ID、课程名称、
    *  课程的开始日期和结束日期、课程的信息、课程的状态。
    */
   public Collection getCourseAllInfo(String courseID)
   {
       try
       {
           Collection colTemp=eschoolRMI.getCourseAllInfo(courseID);
           return colTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }

   /** 获得对应课程的考题。包括：考试ID、试题名称、时间、说明等 */
   public Collection getExamInfo(String courseID)
   {
       try
       {
           Collection colTemp=eschoolRMI.getExamInfo(courseID);
           return colTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }

   /** 取得相应考试的所有试题。包括：试题ID、答题纸、状态等。 */
   public Collection getContentInfo(String projectID)
   {
       try
       {
           Collection colTemp=eschoolRMI.getContentInfo(projectID);
           return colTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }

   /** 保存考试的试题 */
   public void saveExameContent(Vector projectID)
   {
       try
       {
           eschoolRMI.saveExameContent(projectID);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }

   /** 改变数据库中考试的状态  */
   public void changeProjectState(String projectID,String state)
   {
       try
       {
           eschoolRMI.changeProjectState(projectID,state);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }

   /** 获得试题的列表。 */
   public Collection getContentList(String courseID)
   {
       try
       {
           Collection colTemp=eschoolRMI.getContentList(courseID);
           return colTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }

   /** 删除试题  */
   public void deleteContent(String ContentID)
   {
       try
       {
           eschoolRMI.deleteContent(ContentID);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }

   /** 保存学生试题分组信息  */
   public void saveContentStudent(Vector StudentName,
                                  String contentID,
                                  String courseID,
                                  String teacherID)
   {
       try
       {
           eschoolRMI.saveContentStudent(StudentName,contentID,courseID,teacherID);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }
   /** 老师获得试题的URL。 */
   public URL getExamContent(String contentID)
   {
       try
       {
           URL URLTemp=eschoolRMI.getExamContent(contentID);
           return URLTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }

   /** 学生获得试题的URL。 */
   public URL getExamContent(String userID, String courseID)
   {
       try
       {
           URL URLTemp=eschoolRMI.getExamContent(userID,courseID);
           return URLTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }
   /** 取得考试题的具体信息，用来生成答卷  */
   public Collection getQuestionInfo(String userID, String courseID)
   {
       try
       {
           Collection colTemp=eschoolRMI.getQuestionInfo(userID,courseID);
           return colTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }

   /** 保存试卷到数据库 */
   public void saveExamAnsweerPaper(String userID,String courseID, Hashtable answeerItem)
   {
       try
       {
           eschoolRMI.saveExamAnsweerPaper(userID,courseID,answeerItem);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }

   /**  取消考试 */
   public void cancelExam(String courseID)
   {
       try
       {
           eschoolRMI.cancelExam(courseID);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }

    /**  结束考试 */
   public void finishExam(String courseID,String userID)
   {
       try
       {
           eschoolRMI.finishExam(courseID,userID);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }

   /** 获得听力练习的相关信息  */
   public Collection getListenExerciseInfo(String courseID)
   {
       try
       {
           Collection colTemp=eschoolRMI.getListenExerciseInfo(courseID);
           return colTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }
   /** 获得听力片断的相关信息  */
   public Collection getListenSnippetInfo(String exerciseID)
   {
       try
       {
           Collection colTemp=eschoolRMI.getListenSnippetInfo(exerciseID);
           return colTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }
   /** 保存听力练习   */
   public void saveListenExerciseInfo(Vector listenExerciseID)
   {
       try
       {
           eschoolRMI.saveListenExerciseInfo(listenExerciseID);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }
   /**  取消听力练习  */
   public void cancelListenExercise(String courseID)
   {
       try
       {
           eschoolRMI.cancelListenExercise(courseID);
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
   }
   /**  取得听力片断列表  */
   public Collection getListenSnippetList(String courseID)
   {
       try
       {
           Collection colTemp=eschoolRMI.getListenSnippetList(courseID);
           return colTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }
   /**  取得听力片断的URL */
   public String   getListenSnippetURL(String sinppetID)
   {
       try
       {
           String strTemp=eschoolRMI.getListenSnippetURL(sinppetID);
           return strTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }
   /**  检查是否选择了试题  */
   public boolean selectExamOrNot(String courseID)
   {
       try
       {
           boolean boolTemp=eschoolRMI.selectExamOrNot(courseID);
           return boolTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return false;
   }
   /**  检查是否进行了试题分组  */
   public boolean examGroupOrNot(String courseID)
   {
       try
       {
           boolean boolTemp=eschoolRMI.examGroupOrNot(courseID);
           return boolTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return false;
   }
   /**   检查是否选择听力练习  */
   public boolean selectListenOrNot(String courseID)
   {
       try
       {
           boolean boolTemp=eschoolRMI.selectListenOrNot(courseID);
           return boolTemp;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return false;
   }

   //--->>Ardy
   /** 验证密码  */
   public Hashtable verifyPassword(String userName,String password)
   {
       try
       {
           Hashtable htCourse=eschoolRMI.verifyPassword(userName,password);
           return htCourse;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }
   /**  课堂登录 */
   public Vector login(String courseID,String IP,String userID)
   {
       try
       {
           Vector vecUser=eschoolRMI.login(courseID,IP,userID);
           return vecUser;
       }
       catch(Exception re)
       {
           re.printStackTrace();
       }
       return null;
   }
   /**老师退出*/
   public boolean logout(String courseID)
   {
       boolean b=true;
       try
       {
           b=eschoolRMI.logout(courseID);
       }
       catch(Exception re)
       {
           re.printStackTrace();
           return false;
       }
       return b;
   }

}
