package com.dc.eschool.exam;

import java.util.*;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

/**
 * Title:         考试的界面接口
 * Description:   它定义了所有与考试有关的界面方法。
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public interface ExamIF
{

   /** 获得考试的相关信息  */
   public Collection getExamInfo(String courseID);

   /** 获得考试对应试题的信息  */
   public Collection getContentInfo(String projectID);

   /** 修改考试的状态  */
   public void changeProjectState(String projectID,String state);

   /** 保存试题 */
   public void saveContent(Vector vectProjectID);

   /** 通知考试的学生考试开始。 */
   public void notifyStartExam(Vector studentID) throws RemoteException;

   /** 老师取消考试  */
   public void cancelExam(String courseID);

   /** 老师取消考试  */
   public void finishExam(String courseID,String userID);

   /** 取得考试题列表   */
   public Collection getContentList(String courseID);

   /** 获得学生的分组名称  */
   public Vector getGroupsName(String fileName);

   /**  获得某一分组的学生ID */
   public Vector getStudentbyGroupsName(String groupName,String fileName);

   /** 保存试题分组信息  */
   public void SaveContentStudent(Vector userID, String contentID, String courseID, String teacherID);

   /** 删除试题  */
   public void deleteContent(String contentID);

   /** 调用学生类的远程方法，强行收卷  */
   public void forceAllEnd(Vector vectStudentID);

   /** 调用学生端的远程方法，强行收某一学生的试卷  */
   public void forceOneEnd(String studentID);

   /** 显示已经交卷的学生   */
   public Vector showFinishStudent();

   /** 老师获得考试题  */
   public void getExamContent(String contentID);

   /** 学生获得考试题  */
   public void getExamContent(String userID,String courseID);

   /** 得到试题的数目，用来生成学生考试界面   */
   public Collection getQuestionInfo(String userID,String courseID);

   /** 保存考试结果 */
   public void saveExamAnsweerPaper(String userID, String courseID, Hashtable answeerItem);

   /** 调用远程方法，通知老师注意到自己已经交卷。  */
   public void notifyHandin(String userID) throws RemoteException;

   /**
    * 试题交卷，该方法要调用saveResult方法，
    * 另外还要调用notifyHandin方法，通知老师。
    */
   public void handinExamAnsweerPaper(String userID,String courseID, Hashtable answeerItem)
               throws RemoteException;
   /**  检查是否选择了试题  */
   public boolean selectExamOrNot(String courseID);
   /**  检查是否进行了试题分组  */
   public boolean examGroupOrNot(String courseID);
   /**  检查是否选择了听力     */
   public boolean selectListenOrNot(String courseID);
   /**  重新启动word文档  */
   public void restartWordDoc();
   /**   关闭word文档  */
   public void closeWordDoc();
}
