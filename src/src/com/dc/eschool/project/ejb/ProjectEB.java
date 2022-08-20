package com.dc.eschool.project.ejb;

import java.rmi.*;
import javax.ejb.*;
import java.util.Collection;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public interface ProjectEB extends EJBObject
{
  public com.dc.eschool.project.model.ProjectModel getDetails() throws RemoteException;
  public void setAllow(String p0) throws RemoteException;
  public void setCourseID(Integer p0) throws RemoteException;
  public void setEndDate(String p0) throws RemoteException;
  public void setInfo(String p0) throws RemoteException;
  public void setLastModifyBy(Integer p0) throws RemoteException;
  public void setName(String p0) throws RemoteException;
  public void setPublishResult(String p0) throws RemoteException;
  public void setStartDate(String p0) throws RemoteException;
  public void setState(String p0) throws RemoteException;
  public void setType(String p0) throws RemoteException;
}