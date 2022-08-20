package com.dc.eschool.exam;

import java.util.*;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

/**
 * Title:         ���ԵĽ���ӿ�
 * Description:   �������������뿼���йصĽ��淽����
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public interface ExamIF
{

   /** ��ÿ��Ե������Ϣ  */
   public Collection getExamInfo(String courseID);

   /** ��ÿ��Զ�Ӧ�������Ϣ  */
   public Collection getContentInfo(String projectID);

   /** �޸Ŀ��Ե�״̬  */
   public void changeProjectState(String projectID,String state);

   /** �������� */
   public void saveContent(Vector vectProjectID);

   /** ֪ͨ���Ե�ѧ�����Կ�ʼ�� */
   public void notifyStartExam(Vector studentID) throws RemoteException;

   /** ��ʦȡ������  */
   public void cancelExam(String courseID);

   /** ��ʦȡ������  */
   public void finishExam(String courseID,String userID);

   /** ȡ�ÿ������б�   */
   public Collection getContentList(String courseID);

   /** ���ѧ���ķ�������  */
   public Vector getGroupsName(String fileName);

   /**  ���ĳһ�����ѧ��ID */
   public Vector getStudentbyGroupsName(String groupName,String fileName);

   /** �������������Ϣ  */
   public void SaveContentStudent(Vector userID, String contentID, String courseID, String teacherID);

   /** ɾ������  */
   public void deleteContent(String contentID);

   /** ����ѧ�����Զ�̷�����ǿ���վ�  */
   public void forceAllEnd(Vector vectStudentID);

   /** ����ѧ���˵�Զ�̷�����ǿ����ĳһѧ�����Ծ�  */
   public void forceOneEnd(String studentID);

   /** ��ʾ�Ѿ������ѧ��   */
   public Vector showFinishStudent();

   /** ��ʦ��ÿ�����  */
   public void getExamContent(String contentID);

   /** ѧ����ÿ�����  */
   public void getExamContent(String userID,String courseID);

   /** �õ��������Ŀ����������ѧ�����Խ���   */
   public Collection getQuestionInfo(String userID,String courseID);

   /** ���濼�Խ�� */
   public void saveExamAnsweerPaper(String userID, String courseID, Hashtable answeerItem);

   /** ����Զ�̷�����֪ͨ��ʦע�⵽�Լ��Ѿ�����  */
   public void notifyHandin(String userID) throws RemoteException;

   /**
    * ���⽻���÷���Ҫ����saveResult������
    * ���⻹Ҫ����notifyHandin������֪ͨ��ʦ��
    */
   public void handinExamAnsweerPaper(String userID,String courseID, Hashtable answeerItem)
               throws RemoteException;
   /**  ����Ƿ�ѡ��������  */
   public boolean selectExamOrNot(String courseID);
   /**  ����Ƿ�������������  */
   public boolean examGroupOrNot(String courseID);
   /**  ����Ƿ�ѡ��������     */
   public boolean selectListenOrNot(String courseID);
   /**  ��������word�ĵ�  */
   public void restartWordDoc();
   /**   �ر�word�ĵ�  */
   public void closeWordDoc();
}
