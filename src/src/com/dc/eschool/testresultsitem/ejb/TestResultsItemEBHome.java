package com.dc.eschool.testresultsitem.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;

import com.dc.eschool.testresultsitem.exceptions.TestResultsItemAppException;
import com.dc.eschool.testresultsitem.model.TestResultsItemModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * TestResultsItemEBHome is an interface which provides methods to create or to find
 * testresultsitem preferences for a particular user.
 */
public interface TestResultsItemEBHome extends EJBHome
{
/**
 * Creates a testresultsitem preference profile for a particular user.
 * @returns         a reference to a <code>TestResultsItemEB</code> object created
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the answeritem perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this user
 * @throws          <code>TestResultsItemAppException</code> if an wrong/missing
 *                  field was specified by the user
 */
/**
  public TestResultsItemEB create(Integer TestResultItemID,Integer Student,String Right, String AnswerMark,Integer ExaminationID,
								  String Answer, Integer AnswerItemID, String allow, String createDate,String createTime,
								  Integer createBy, String lastModifyDate, String lastModifyTime,
								  Integer lastModifyBy) throws DuplicateKeyException,TestResultsItemAppException,CreateException, RemoteException ;
*/
  public TestResultsItemEB create(TestResultsItemModel other) throws DuplicateKeyException,
																	 TestResultsItemAppException,
																	 CreateException,
																	 RemoteException ;
/**
 * Finds a personal preferences profile for a particular user.
 * @param primKey    a Integer which represents the id of this user
 * @returns         a reference to a <code>TestResultsItemEB</code> object found
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>FinderException</code> is thrown if a profile
 *                  could not be found for this user
 */
  public TestResultsItemEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
  public TestResultsItemEB findByAnswerItemIDContentIDStudent(String answerItemID, String contentID, String student) throws RemoteException, FinderException;
}