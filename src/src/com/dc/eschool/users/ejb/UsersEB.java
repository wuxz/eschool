package com.dc.eschool.users.ejb;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.users.exceptions.UDAOAppException;
import com.dc.eschool.users.exceptions.UDAODBUpException;
import com.dc.eschool.users.exceptions.UDAODuKeyException;
import com.dc.eschool.users.exceptions.UDAOSysException;
import com.dc.eschool.users.exceptions.UDAOFindException;

import com.dc.eschool.users.model.UsersModel;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public interface UsersEB extends EJBObject
{
  public UsersModel getDetails() throws RemoteException;
  public void setAddress(String p0) throws RemoteException;
  public void setAllow(String p0) throws RemoteException;
  public void setBirthday(String p0) throws RemoteException;
  public void setClassID(Integer p0) throws RemoteException;
  public void setEmail(String p0) throws RemoteException;
  public void setGender(String p0) throws RemoteException;
  public void setLastModifyBy(Integer p0) throws RemoteException;
  public void setLoginName(String p0) throws RemoteException;
  public void setName(String p0) throws RemoteException;
  public void setPassword(String p0) throws RemoteException;
  public void setState(String p0) throws RemoteException;
  public void setTel(String p0) throws RemoteException;
  public void setUserType(String p0) throws RemoteException;

}