package com.dc.eschool.rmi.ejb;

import java.rmi.*;
import javax.ejb.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public interface RMIInterfaceSL extends EJBObject
{
  public java.util.Collection getUserInfo(java.lang.String userID) throws RemoteException;
  public void cancelExam(java.lang.String courseID) throws RemoteException;
  public void changeProjectState(java.lang.String projectID, java.lang.String state) throws RemoteException;
  public void deleteContent(java.lang.String ContentID) throws RemoteException;
  public boolean examGroupOrNot(java.lang.String courseID) throws RemoteException;
  public java.util.Collection getContentInfo(java.lang.String projectID) throws RemoteException;
  public java.util.Collection getContentList(java.lang.String courseID) throws RemoteException;
  public java.util.Collection getCourseAllInfo(java.lang.String courseID) throws RemoteException;
  public java.net.URL getExamContent(java.lang.String contentID) throws RemoteException;
  public java.util.Collection getExamInfo(java.lang.String courseID) throws RemoteException;
  public java.util.Collection getQuestionInfo(java.lang.String userID, java.lang.String courseID) throws RemoteException;
  public java.util.Collection getUserInfoByName(java.lang.String userName) throws RemoteException;
  public java.util.Vector login(java.lang.String courseid, java.lang.String ip,String userID) throws RemoteException;
  public void saveContentStudent(java.util.Vector StudentName, java.lang.String contentID, java.lang.String courseID, java.lang.String teacherID) throws RemoteException;
  public void saveExamAnsweerPaper(java.lang.String userID, java.lang.String courseID, java.util.Hashtable answeerItem) throws RemoteException;
  public void saveExameContent(java.util.Vector projectID) throws RemoteException;
  public void saveUserInfo(java.lang.String userID, java.lang.String name, java.lang.String gender, java.lang.String birthday, java.lang.String userType, java.lang.String telephone) throws RemoteException;
  public boolean selectExamOrNot(java.lang.String courseID) throws RemoteException;
  public java.util.Hashtable verifyPassword(java.lang.String userName, java.lang.String password) throws RemoteException;
  public void cancelListenExercise(java.lang.String courseID) throws RemoteException;
  public java.net.URL getExamContent(java.lang.String userID, java.lang.String courseID) throws RemoteException;
  public java.util.Collection getListenExerciseInfo(java.lang.String courseID) throws RemoteException;
  public java.util.Collection getListenSnippetInfo(java.lang.String exerciseID) throws RemoteException;
  public java.util.Collection getListenSnippetList(java.lang.String courseID) throws RemoteException;
  public java.lang.String getListenSnippetURL(java.lang.String sinppetID) throws RemoteException;
  public void saveListenExerciseInfo(java.util.Vector listenExerciseID) throws RemoteException;
  public boolean logout(String courseID) throws RemoteException;
  public boolean selectListenOrNot(String courseID) throws RemoteException;
  public void finishExam(String courseID,String userID) throws RemoteException;
}