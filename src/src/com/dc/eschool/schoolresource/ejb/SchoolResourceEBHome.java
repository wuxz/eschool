package com.dc.eschool.schoolresource.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;

import com.dc.eschool.schoolresource.exceptions.SchoolResourceAppException;
import com.dc.eschool.schoolresource.model.SchoolResourceModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * SchoolResourceEBHome is an interface which provides methods to create or to find
 * schoolresource preferences for a particular user.
 */
public interface SchoolResourceEBHome extends EJBHome
{
/**
 * Creates a schoolresource preference profile for a particular user.
 * @returns         a reference to a <code>SchoolResourceEB</code> object created
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the schoolresource perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this user
 * @throws          <code>SchoolResourceAppException</code> if an wrong/missing
 *                  field was specified by the user
 */
/* 
  public SchoolResourceEB create(Integer schoolResourseID,String name, String docURL,Integer subjectID,
                            	 String startDate, String endDate, String explain,String allow, String createDate,String createTime,
                                 Integer createBy, String lastModifyDate, String lastModifyTime,
                                 Integer lastModifyBy) throws DuplicateKeyException,SchoolResourceAppException,CreateException, RemoteException ;
*/
  public SchoolResourceEB create(SchoolResourceModel other) throws DuplicateKeyException,
                                                                    SchoolResourceAppException,
                                                                    CreateException,
                                                                    RemoteException ;
/**
 * Finds a personal preferences profile for a particular user.
 * @param primKey    a Integer which represents the id of this user
 * @returns         a reference to a <code>SchoolResourceEB</code> object found
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>FinderException</code> is thrown if a profile
 *                  could not be found for this user
 */
  public SchoolResourceEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}