package com.dc.eschool.exam;

import java.util.*;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.net.*;
import java.io.*;

import com.dc.eschool.system.EJBAccess;
import com.dc.eschool.group.UserGroup;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.system.Student;
import com.dc.eschool.system.Teacher;


/**
  * Title:         考试实现类
  * Description:   该类实现了ExamIF接口，并且调用EJBAccess与数据库通信，
  *                调用StudentGroup本地文件通讯。
  * Copyright:     Copyright (c) 2001
  * Company:       DC
  * @author        lau_hz
  * @version       1.0
  */

public class Examination implements ExamIF
{

   private SystemControl  systemControl;
   private EJBAccess      ejbAccess=null;
   private Student        IStudent;
   private Teacher        ITeacher;
   private Process        myProcess=null;
   private String         fileName=null;

   /**构造器*/
   public Examination(SystemControl systemControl)
   {
      this.systemControl=systemControl;
      if (this.systemControl.ejbAccess==null)
          this.systemControl.ejbAccess=new EJBAccess();
      ejbAccess=this.systemControl.ejbAccess;
   }

   /** 下载学生试题文件    */
   private String downloadExamContent(URL inURL)
            throws RemoteException,IOException,MalformedURLException
   {
		//String parentdir;

        //make time stamp as the local file name
		//long currentTime = System.currentTimeMillis();
		//int high = (int)(currentTime >>> 32);
		//int low = (int)(( currentTime << 32 ) >>> 32);
        URL examContentURL=new URL(inURL.toString());
		new File("../data").mkdir();
		String  localFile = "../data/temp.doc";
        FileOutputStream  savefile = null;

		try
        {
 			InputStream input;
			URLConnection hpCon = examContentURL.openConnection();
            input = hpCon.getInputStream();
			byte buffer[] = new byte[1024];
			int count=0;
			savefile = new FileOutputStream(localFile);
            while((count = input.read(buffer))>0 )
                savefile.write(buffer,0,count);
            input.close();
			savefile.close();
		}
		catch(Exception e)
        {
			if (savefile != null)
            {
				savefile.close();
			}
            systemControl.writeLog("在下载考试试题时,出现网络故障,请与网络管理员联系！");
            e.printStackTrace();
			return null;
		}
		return localFile;
   }


   /** 取的拷试的信息  */
   public Collection getExamInfo(String courseID)
   {
       Collection tempSet;
       tempSet=ejbAccess.getExamInfo(courseID);
       return tempSet;
   }

   /**   取得相应考试试题的信息   */
   public Collection getContentInfo(String projectID)
   {
       Collection tempSet;
       tempSet=ejbAccess.getContentInfo(projectID);
       return tempSet;
   }

   /** 修改考试的状态  */
   public void changeProjectState(String projectID,String state)
   {
       ejbAccess.changeProjectState(projectID,state);
   }

   /** 保存考试题   */
   public void saveContent(Vector vectProjectID)
   {
       ejbAccess.saveExameContent(vectProjectID);
   }

   /** 老师通知学生考试开始   */
   public void notifyStartExam(Vector studentID) throws RemoteException
   {
       String     tempID;
       Hashtable  htStudentIDInterface;
       String     userType;
       for (int i=0;i<studentID.size();i++)
       {
           tempID=(String)studentID.get(i);
           if (systemControl.userGroup.getUserTypeByUserID(tempID).equals("2"))
           {
           }
           else
           {
               systemControl.userGroup.changeExaming(tempID,true);
               htStudentIDInterface=systemControl.getHtStudentIDInterface();
               IStudent=(Student)htStudentIDInterface.get(tempID);
               IStudent.startExam();
           }
       }
   }

   /** 取消考试   */
   public void cancelExam(String courseID)
   {
       ejbAccess.cancelExam(courseID);
   }

   /** 老师取消考试  */
   public void finishExam(String courseID,String userID)
   {
       ejbAccess.finishExam(courseID,userID);
   }

   /** 获得考试内容列表   */
   public Collection getContentList(String courseID)
   {
       Collection tempSet;
       tempSet=ejbAccess.getContentList(courseID);
       return tempSet;
   }


   /**  通过小组名获得小组学生名单 */
   public Vector getStudentbyGroupsName(String groupName,String fileName)
   {
       Vector studentID=null;
       UserGroup IGroup=new UserGroup(fileName);
       IGroup.matchGroup(systemControl.userGroup);
       studentID=IGroup.getUserIDsByGroupName(groupName);
       IGroup=null;
       return studentID;
   }

   /**  读取学生分组信息   */
   public Vector getGroupsName(String fileName)
   {
       Vector groupName;
       UserGroup IGroup=new UserGroup(fileName);
       IGroup.matchGroup(systemControl.userGroup);
       groupName=IGroup.getGroupNames();
       IGroup=null;
       return groupName;
   }

   /**  保存学生试题信息   */
   public void SaveContentStudent(Vector userID, String contentID,String courseID, String teacherID)
   {
       try
       {
           ejbAccess.saveContentStudent(userID,contentID,courseID,teacherID);
       }
       catch(Exception e)
       {
           systemControl.writeLog("在保存学生试题分组时,调用数据库出错！");
       }
   }

   /** 删除考试   */
   public void deleteContent(String contentID)
   {
       ejbAccess.deleteContent(contentID);
   }

   /** 强迫所有学生交卷   */
   public void forceAllEnd(Vector vectStudentID)
   {
       String studentID;
       try
       {
           for (int i=0;i<vectStudentID.size();i++)
           {
              studentID=(String)vectStudentID.get(i);
              forceOneEnd(studentID);
           }
        }
        catch (Exception e)
        {
            systemControl.writeLog("在结束所有学生考试时,远程调用失败！");
        }
   }

   /** 强迫某一学生交卷   */
   public void forceOneEnd(String studentID)
   {
        try
        {
            Hashtable  htStudentIDInterface=systemControl.getHtStudentIDInterface();
            if (htStudentIDInterface==null)
            {
                systemControl.writeLog("没有取到学生的接口,强迫学生交卷失败！");
                return;
            }
            IStudent=(Student)htStudentIDInterface.get(studentID);
            IStudent.endExam();
        }
        catch(Exception e)
        {
            systemControl.writeLog("在结束某一学生考试时,远程调用失败！");
        }
   }

   /** 显示已经交卷的学生   */
   public Vector showFinishStudent()
   {
       return systemControl.getHandinStudent();
   }
   /** 获得考试题的URL   */
   public void getExamContent(String contentID)
   {
       URL     examURL;
       String  fileName=null;
       examURL=ejbAccess.getExamContent(contentID);
       if (examURL==null)
       {
           systemControl.writeLog("没有从数据库中得到试题的目录,无法下载试题!");
           return;
       }
       try
       {
           fileName=downloadExamContent(examURL);
       }
       catch (Exception e)
       {
           systemControl.writeLog("在试题下载过程中出现错误,没有下载到试题！");
       }

       try
       {
           String s;
           Process myProcess = null;
           Properties p=new Properties();
           p.load(new FileInputStream("systemconf.properties"));
           myProcess = Runtime.getRuntime().exec (p.getProperty("word")+" "+fileName);
           System.out.print(myProcess.toString());
           BufferedReader in = new BufferedReader(new  InputStreamReader(myProcess.getErrorStream()));
           while ((s = in.readLine()) != null)
           {
               systemControl.writeLog("在考试过程中启动Word文档失败,异常："+s);
           }
       }
       catch(Exception e)
       {
           systemControl.writeLog("在考试过程中,创建启动Word的线程失败！");
       }
   }
   /** 获得考试题的URL   */
   public void getExamContent(String userID,String courseID)
   {
       URL     examURL=null;
       examURL=ejbAccess.getExamContent(userID,courseID);
       if (examURL==null)
       {
           systemControl.writeLog("没有从数据库中得到试题的目录,无法下载试题!");
           return;
       }
       try
       {
           fileName=downloadExamContent(examURL);
       }
       catch (Exception e)
       {
           systemControl.writeLog("在下载过程出现错误,没有下载到试题！");
       }

       try
       {
           String s;
           //Process myProcess = null;
           Properties p=new Properties();
           p.load(new FileInputStream("systemconf.properties"));
           myProcess = Runtime.getRuntime().exec (p.getProperty("word")+" "+fileName);
           BufferedReader in = new BufferedReader(new  InputStreamReader(myProcess.getErrorStream()));
           while ((s = in.readLine()) != null)
           {
               systemControl.writeLog("在考试过程中启动Word文档失败,异常："+s);
           }
       }
       catch(Exception e)
       {
           systemControl.writeLog("在考试过程中,创建启动Word的线程失败！");
       }
   }


   /** 获得考试的信息   */
   public Collection getQuestionInfo(String userID,String courseID)
   {
       Collection questionInfo;
       questionInfo=ejbAccess.getQuestionInfo(userID,courseID);
       return questionInfo;
   }

   /** 保存试卷   */
   public void saveExamAnsweerPaper(String userID, String courseID, Hashtable answeerItem)
   {
       ejbAccess.saveExamAnsweerPaper(userID,courseID,answeerItem);
   }

   /** 学生告诉老师已经交卷   */
   public void notifyHandin(String userID) throws RemoteException
   {
       ITeacher=systemControl.getTeacherInterface();
       if (ITeacher==null)
       {
           systemControl.writeLog("没有发现老师的远程接口,通知老师已交卷失败！");
           return;
       }
       try
       {
           ITeacher.handinExam(userID);
       }
       catch (Exception e)
       {
           systemControl.writeLog("在考试结束时,通知老师的远程调用失败！");
       }
   }

   /** 学生交卷   */
   public void handinExamAnsweerPaper(String userID,String courseID,Hashtable answeerItem)
               throws RemoteException
   {
       this.saveExamAnsweerPaper(userID,courseID,answeerItem);
       this.notifyHandin(userID);
   }
   /**  检查是否选择了试题  */
   public boolean selectExamOrNot(String courseID)
   {
       return ejbAccess.selectExamOrNot(courseID);
   }
   /**  检查是否进行了试题分组  */
   public boolean examGroupOrNot(String courseID)
   {
       return ejbAccess.examGroupOrNot(courseID);
   }
   /**  检查是否选择了听力     */
   public boolean selectListenOrNot(String courseID)
   {
       return ejbAccess.selectListenOrNot(courseID);
   }
   /**  重新启动word文档  */
   public void restartWordDoc()
   {
       if (myProcess!=null)
       {
           myProcess.destroy();
       }
       try
       {
           String s;
           //Process myProcess = null;
           Properties p=new Properties();
           p.load(new FileInputStream("systemconf.properties"));
           myProcess = Runtime.getRuntime().exec (p.getProperty("word")+" "+fileName);
           BufferedReader in = new BufferedReader(new  InputStreamReader(myProcess.getErrorStream()));
           while ((s = in.readLine()) != null)
           {
               systemControl.writeLog("在考试过程中启动Word文档失败,异常："+s);
           }
       }
       catch(Exception e)
       {
           systemControl.writeLog("在考试过程中,创建启动Word的线程失败！");
       }
   }
   /**   关闭word文档  */
   public void closeWordDoc()
   {
       if (myProcess!=null)
       {
           myProcess.destroy();
       }
   }
}
