package com.dc.eschool.homework.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

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
 * This interface provides methods to view and modify homework
 * information for a particular homework.
*/

public interface HomeWorkEB extends EJBObject
{
  public com.dc.eschool.homework.model.HomeWorkModel getDetails() throws RemoteException;
  public void setAllow(java.lang.String p0) throws RemoteException;
  public void setLastModifyBy(java.lang.Integer p0) throws RemoteException;
  public void setStudent(java.lang.Integer p0) throws RemoteException;
  public void setProjectID(java.lang.Integer p0) throws RemoteException;
}