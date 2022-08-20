package com.dc.eschool.homework.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;

import com.dc.eschool.homework.model.HomeWorkModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * The home interface of the HomeWorkEBBean.
 */
public interface HomeWorkEBHome extends EJBHome
{
  public HomeWorkEB create(HomeWorkModel homeWorkModel) throws CreateException, RemoteException ;
  public HomeWorkEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}