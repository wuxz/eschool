package com.dc.eschool.coursestudent.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

import com.dc.eschool.coursestudent.model.CourseStudentModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

/**
 * The home interface of the CourseStudentEBBean.
 */
public interface CourseStudentEBHome extends EJBHome {
    public CourseStudentEB create(CourseStudentModel sm) throws CreateException, RemoteException ;
    public CourseStudentEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}