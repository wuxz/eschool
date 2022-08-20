package com.dc.eschool.scorestatistic.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;

import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticAppException;
import com.dc.eschool.scorestatistic.model.ScoreStatisticModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * ScoreStatisticEBHome is an interface which provides methods to create or to find
 * ScoreStatistic  preferences for a particular user.
 */
public interface ScoreStatisticEBHome extends EJBHome
{
/**
 * Creates a ScoreStatistic preference profile for a particular user.
 * @returns         a reference to a <code>ScoreStatisticEB</code> object created
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the ScoreStatistic perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this user
 * @throws          <code>ScoreStatisticAppException</code> if an wrong/missing
 *                  field was specified by the user
 */
/**
  public ScoreStatisticEB create(Integer scoreStatisticID,Integer student, Integer answerItemID,Integer projectContentID,
                            	 String statistic, Integer rightAnswer, Integer wrongAnswer,String allow, String createDate,String createTime,
                              	 Integer createBy, String lastModifyDate, String lastModifyTime,
                                 Integer lastModifyBy) throws DuplicateKeyException,ScoreStatisticAppException,CreateException, RemoteException ;
*/
  public ScoreStatisticEB create(ScoreStatisticModel other) throws DuplicateKeyException,
                                                                   ScoreStatisticAppException,
                                                                   CreateException,
                                                                   RemoteException ;
/**
 * Finds a personal preferences profile for a particular user.
 * @param primKey    a Integer which represents the id of this user
 * @returns         a reference to a <code>ScoreStatisticEB</code> object found
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>FinderException</code> is thrown if a profile
 *                  could not be found for this user
 */
  public ScoreStatisticEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
  public ScoreStatisticEB findByAnswerItemID(String answerItemID) throws RemoteException, FinderException;
}