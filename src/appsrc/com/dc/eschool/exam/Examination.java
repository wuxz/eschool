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
  * Title:         ����ʵ����
  * Description:   ����ʵ����ExamIF�ӿڣ����ҵ���EJBAccess�����ݿ�ͨ�ţ�
  *                ����StudentGroup�����ļ�ͨѶ��
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

   /**������*/
   public Examination(SystemControl systemControl)
   {
      this.systemControl=systemControl;
      if (this.systemControl.ejbAccess==null)
          this.systemControl.ejbAccess=new EJBAccess();
      ejbAccess=this.systemControl.ejbAccess;
   }

   /** ����ѧ�������ļ�    */
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
            systemControl.writeLog("�����ؿ�������ʱ,�����������,�����������Ա��ϵ��");
            e.printStackTrace();
			return null;
		}
		return localFile;
   }


   /** ȡ�Ŀ��Ե���Ϣ  */
   public Collection getExamInfo(String courseID)
   {
       Collection tempSet;
       tempSet=ejbAccess.getExamInfo(courseID);
       return tempSet;
   }

   /**   ȡ����Ӧ�����������Ϣ   */
   public Collection getContentInfo(String projectID)
   {
       Collection tempSet;
       tempSet=ejbAccess.getContentInfo(projectID);
       return tempSet;
   }

   /** �޸Ŀ��Ե�״̬  */
   public void changeProjectState(String projectID,String state)
   {
       ejbAccess.changeProjectState(projectID,state);
   }

   /** ���濼����   */
   public void saveContent(Vector vectProjectID)
   {
       ejbAccess.saveExameContent(vectProjectID);
   }

   /** ��ʦ֪ͨѧ�����Կ�ʼ   */
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

   /** ȡ������   */
   public void cancelExam(String courseID)
   {
       ejbAccess.cancelExam(courseID);
   }

   /** ��ʦȡ������  */
   public void finishExam(String courseID,String userID)
   {
       ejbAccess.finishExam(courseID,userID);
   }

   /** ��ÿ��������б�   */
   public Collection getContentList(String courseID)
   {
       Collection tempSet;
       tempSet=ejbAccess.getContentList(courseID);
       return tempSet;
   }


   /**  ͨ��С�������С��ѧ������ */
   public Vector getStudentbyGroupsName(String groupName,String fileName)
   {
       Vector studentID=null;
       UserGroup IGroup=new UserGroup(fileName);
       IGroup.matchGroup(systemControl.userGroup);
       studentID=IGroup.getUserIDsByGroupName(groupName);
       IGroup=null;
       return studentID;
   }

   /**  ��ȡѧ��������Ϣ   */
   public Vector getGroupsName(String fileName)
   {
       Vector groupName;
       UserGroup IGroup=new UserGroup(fileName);
       IGroup.matchGroup(systemControl.userGroup);
       groupName=IGroup.getGroupNames();
       IGroup=null;
       return groupName;
   }

   /**  ����ѧ��������Ϣ   */
   public void SaveContentStudent(Vector userID, String contentID,String courseID, String teacherID)
   {
       try
       {
           ejbAccess.saveContentStudent(userID,contentID,courseID,teacherID);
       }
       catch(Exception e)
       {
           systemControl.writeLog("�ڱ���ѧ���������ʱ,�������ݿ����");
       }
   }

   /** ɾ������   */
   public void deleteContent(String contentID)
   {
       ejbAccess.deleteContent(contentID);
   }

   /** ǿ������ѧ������   */
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
            systemControl.writeLog("�ڽ�������ѧ������ʱ,Զ�̵���ʧ�ܣ�");
        }
   }

   /** ǿ��ĳһѧ������   */
   public void forceOneEnd(String studentID)
   {
        try
        {
            Hashtable  htStudentIDInterface=systemControl.getHtStudentIDInterface();
            if (htStudentIDInterface==null)
            {
                systemControl.writeLog("û��ȡ��ѧ���Ľӿ�,ǿ��ѧ������ʧ�ܣ�");
                return;
            }
            IStudent=(Student)htStudentIDInterface.get(studentID);
            IStudent.endExam();
        }
        catch(Exception e)
        {
            systemControl.writeLog("�ڽ���ĳһѧ������ʱ,Զ�̵���ʧ�ܣ�");
        }
   }

   /** ��ʾ�Ѿ������ѧ��   */
   public Vector showFinishStudent()
   {
       return systemControl.getHandinStudent();
   }
   /** ��ÿ������URL   */
   public void getExamContent(String contentID)
   {
       URL     examURL;
       String  fileName=null;
       examURL=ejbAccess.getExamContent(contentID);
       if (examURL==null)
       {
           systemControl.writeLog("û�д����ݿ��еõ������Ŀ¼,�޷���������!");
           return;
       }
       try
       {
           fileName=downloadExamContent(examURL);
       }
       catch (Exception e)
       {
           systemControl.writeLog("���������ع����г��ִ���,û�����ص����⣡");
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
               systemControl.writeLog("�ڿ��Թ���������Word�ĵ�ʧ��,�쳣��"+s);
           }
       }
       catch(Exception e)
       {
           systemControl.writeLog("�ڿ��Թ�����,��������Word���߳�ʧ�ܣ�");
       }
   }
   /** ��ÿ������URL   */
   public void getExamContent(String userID,String courseID)
   {
       URL     examURL=null;
       examURL=ejbAccess.getExamContent(userID,courseID);
       if (examURL==null)
       {
           systemControl.writeLog("û�д����ݿ��еõ������Ŀ¼,�޷���������!");
           return;
       }
       try
       {
           fileName=downloadExamContent(examURL);
       }
       catch (Exception e)
       {
           systemControl.writeLog("�����ع��̳��ִ���,û�����ص����⣡");
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
               systemControl.writeLog("�ڿ��Թ���������Word�ĵ�ʧ��,�쳣��"+s);
           }
       }
       catch(Exception e)
       {
           systemControl.writeLog("�ڿ��Թ�����,��������Word���߳�ʧ�ܣ�");
       }
   }


   /** ��ÿ��Ե���Ϣ   */
   public Collection getQuestionInfo(String userID,String courseID)
   {
       Collection questionInfo;
       questionInfo=ejbAccess.getQuestionInfo(userID,courseID);
       return questionInfo;
   }

   /** �����Ծ�   */
   public void saveExamAnsweerPaper(String userID, String courseID, Hashtable answeerItem)
   {
       ejbAccess.saveExamAnsweerPaper(userID,courseID,answeerItem);
   }

   /** ѧ��������ʦ�Ѿ�����   */
   public void notifyHandin(String userID) throws RemoteException
   {
       ITeacher=systemControl.getTeacherInterface();
       if (ITeacher==null)
       {
           systemControl.writeLog("û�з�����ʦ��Զ�̽ӿ�,֪ͨ��ʦ�ѽ���ʧ�ܣ�");
           return;
       }
       try
       {
           ITeacher.handinExam(userID);
       }
       catch (Exception e)
       {
           systemControl.writeLog("�ڿ��Խ���ʱ,֪ͨ��ʦ��Զ�̵���ʧ�ܣ�");
       }
   }

   /** ѧ������   */
   public void handinExamAnsweerPaper(String userID,String courseID,Hashtable answeerItem)
               throws RemoteException
   {
       this.saveExamAnsweerPaper(userID,courseID,answeerItem);
       this.notifyHandin(userID);
   }
   /**  ����Ƿ�ѡ��������  */
   public boolean selectExamOrNot(String courseID)
   {
       return ejbAccess.selectExamOrNot(courseID);
   }
   /**  ����Ƿ�������������  */
   public boolean examGroupOrNot(String courseID)
   {
       return ejbAccess.examGroupOrNot(courseID);
   }
   /**  ����Ƿ�ѡ��������     */
   public boolean selectListenOrNot(String courseID)
   {
       return ejbAccess.selectListenOrNot(courseID);
   }
   /**  ��������word�ĵ�  */
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
               systemControl.writeLog("�ڿ��Թ���������Word�ĵ�ʧ��,�쳣��"+s);
           }
       }
       catch(Exception e)
       {
           systemControl.writeLog("�ڿ��Թ�����,��������Word���߳�ʧ�ܣ�");
       }
   }
   /**   �ر�word�ĵ�  */
   public void closeWordDoc()
   {
       if (myProcess!=null)
       {
           myProcess.destroy();
       }
   }
}
