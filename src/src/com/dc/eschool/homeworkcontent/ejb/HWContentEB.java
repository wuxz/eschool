package com.dc.eschool.homeworkcontent.ejb;

import java.rmi.*;
import javax.ejb.*;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public interface HWContentEB extends EJBObject
{
  public com.dc.eschool.homeworkcontent.model.HWContentModel getDetails() throws RemoteException;
  public void setAllow(java.lang.String p0) throws RemoteException;
  public void setDocURL(java.lang.String p0) throws RemoteException;
  public void setHomeWorkID(java.lang.Integer p0) throws RemoteException;
  public void setLastModifyBy(java.lang.Integer p0) throws RemoteException;
  public void setPassDate(java.lang.String p0) throws RemoteException;
  public void setPassTime(java.lang.String p0) throws RemoteException;
  public void setSize(java.lang.Integer p0) throws RemoteException;
  public void setState(java.lang.String p0) throws RemoteException;
  public void setStudent(java.lang.Integer p0) throws RemoteException;
  public void setSubmitDate(java.lang.String p0) throws RemoteException;
  public void setSubmitTime(java.lang.String p0) throws RemoteException;

}