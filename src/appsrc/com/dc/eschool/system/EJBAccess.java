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
 * Title:         EJB�ӿ���
 * Description:   ����EJB���õ��û������������Ϣ���޸����ݿ��е�������Ϣ
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class EJBAccess
{
   /**   EJBԶ�̽ӿ�    */
   private RMIInterfaceSL     eschoolRMI=null;
   /**   ������         */
   public EJBAccess()
   {
       try
       {
           //Hashtable ht = new Hashtable();
           //ht.put(javax.naming.InitialContext.PROVIDER_URL,"IIOP://192.168.0.38:1050/");
           //ht.put(javax.naming.InitialContext.INITIAL_CONTEXT_FACTORY,"javax.naming.spi.InitialContextFactory");

           Context jndiContext=new InitialContext();
           System.out.println("Context�Ѵ�����");
           Object ref=jndiContext.lookup("eschool/RMIInterfaceManager");
           RMIInterfaceSLHome home=(RMIInterfaceSLHome)PortableRemoteObject.narrow(ref,
                                                         RMIInterfaceSLHome.class);
           if (home==null)
           {
               System.out.println("HOME�ӿ�Ϊ�գ�");
           }
           eschoolRMI=home.create();

           if (eschoolRMI==null)
           {
               System.out.println("REMOTE�ӿ�Ϊ�գ�");
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }

   /**
    * ����û���������Ϣ����Ҫ�������û�ID���û��������û��Ա�
    * �û��������ڡ��û��绰���롢�û������͵ȡ�
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
    * ͨ���û�������û�����Ϣ����Ҫ�������û�ID���û�������
    * �û��Ա��û��������ڡ��û��绰���롢�û������͵ȡ�
    */
   public Collection getUserInfoByName(String userName)
   {
       /**
        * �����ں��ڸĶ��У�ȥ����������ܡ�
        */
       return null;

   }

   /** �����޸ĺ��û�����Ϣ  */
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

   /** ������еĿγ���Ϣ����Ҫ�������γ�ID���γ����ơ�
    *  �γ̵Ŀ�ʼ���ںͽ������ڡ��γ̵���Ϣ���γ̵�״̬��
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

   /** ��ö�Ӧ�γ̵Ŀ��⡣����������ID���������ơ�ʱ�䡢˵���� */
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

   /** ȡ����Ӧ���Ե��������⡣����������ID������ֽ��״̬�ȡ� */
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

   /** ���濼�Ե����� */
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

   /** �ı����ݿ��п��Ե�״̬  */
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

   /** ���������б� */
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

   /** ɾ������  */
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

   /** ����ѧ�����������Ϣ  */
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
   /** ��ʦ��������URL�� */
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

   /** ѧ����������URL�� */
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
   /** ȡ�ÿ�����ľ�����Ϣ���������ɴ��  */
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

   /** �����Ծ����ݿ� */
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

   /**  ȡ������ */
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

    /**  �������� */
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

   /** ���������ϰ�������Ϣ  */
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
   /** �������Ƭ�ϵ������Ϣ  */
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
   /** ����������ϰ   */
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
   /**  ȡ��������ϰ  */
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
   /**  ȡ������Ƭ���б�  */
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
   /**  ȡ������Ƭ�ϵ�URL */
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
   /**  ����Ƿ�ѡ��������  */
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
   /**  ����Ƿ�������������  */
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
   /**   ����Ƿ�ѡ��������ϰ  */
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
   /** ��֤����  */
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
   /**  ���õ�¼ */
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
   /**��ʦ�˳�*/
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
