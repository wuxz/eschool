package com.dc.eschool.studentexamcontent.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.studentexamcontent.exceptions.*;
import com.dc.eschool.studentexamcontent.model.StudentExamContentModel;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */


/**
 * The remote interface of StudentExamContentSLBean(Statelesee Session bean)
 */
public interface StudentExamContentSL extends EJBObject
{
  public void deleteStudentExamContent(Integer primKey) throws StudentExamContentDeleteException, RemoteException;
  public StudentExamContentModel getStudentExamContent(String studentExamContentID) throws RemoteException;
  public void insertStudentExamContent(StudentExamContentModel secm) throws StudentExamContentCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchStudentExamContent(String clause, int startIndex, int count) throws StudentExamContentSearchException, RemoteException;
  public void updateStudentExamContent(StudentExamContentModel secm) throws StudentExamContentCreateException, RemoteException;
  public boolean isGrouped(String courseID) throws RemoteException;
  public Integer getContentIDByApp(String userID,String courseID) throws RemoteException;
  public void deleteByApp(String courseID) throws RemoteException;
}