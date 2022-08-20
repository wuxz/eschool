package com.dc.eschool.course.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

import com.dc.eschool.course.exceptions.CourseAppException;
import com.dc.eschool.course.model.CourseModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

/**
 * The home interface of the CourseEBBean.
 */
public interface CourseEBHome extends EJBHome {
    public CourseEB create(CourseModel sm) throws CreateException, RemoteException ;
    public CourseEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}