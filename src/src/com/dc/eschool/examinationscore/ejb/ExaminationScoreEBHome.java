package com.dc.eschool.examinationscore.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;

import com.dc.eschool.examinationscore.exceptions.ExaminationScoreAppException;
import com.dc.eschool.examinationscore.model.ExaminationScoreModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * ExaminationScoreEBHome is an interface which provides methods to create or to find
 * examinationscore preferences for a particular user.
 */
public interface ExaminationScoreEBHome extends EJBHome
{

/**
 * Creates a examinationscore preference profile for a particular user.
 * @returns         a reference to a <code>ExaminationScoreEB</code> object created
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the examinationscore perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this user
 * @throws          <code>ExaminationScoreAppException</code> if an wrong/missing
 *                  field was specified by the user
 */
/**
  public ExaminationScoreEB create(Integer examinationID,Integer projectID, Integer student,Integer score,
								   String allow, String createDate,String createTime,
								   Integer createBy, String lastModifyDate, String lastModifyTime,
								   Integer lastModifyBy) throws DuplicateKeyException,ExaminationScoreAppException,CreateException, RemoteException ;
*/
  public ExaminationScoreEB create(ExaminationScoreModel em) throws  DuplicateKeyException,
																	 ExaminationScoreAppException,
																	 CreateException,
																	 RemoteException ;
/**
 * Finds a personal preferences profile for a particular user.
 * @param primKey    a Integer which represents the id of this user
 * @returns         a reference to a <code>ExaminationScoreEB</code> object found
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>FinderException</code> is thrown if a profile
 *                  could not be found for this user
 */
  public ExaminationScoreEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
  public ExaminationScoreEB findByProjectStudent(Integer projectID, Integer student) throws RemoteException, FinderException;
}