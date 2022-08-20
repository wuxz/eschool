package com.dc.eschool.users.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

import com.dc.eschool.users.model.UsersModel;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * The home interface of the UsersEBBean.
 */

public interface UsersEBHome extends EJBHome
{
  public UsersEB create(UsersModel um) throws CreateException, RemoteException;
  public UsersEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}