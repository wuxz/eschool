package com.dc.eschool.course.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

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
 * This interface provides methods to view and modify course
 * information for a particular course.
*/

public interface CourseEB extends EJBObject
{
  public CourseModel getDetails() throws RemoteException;
  public void setAllow(String p0) throws RemoteException;
  public void setCourseName(String p0) throws RemoteException;
  public void setEndDate(String p0) throws RemoteException;
  public void setInfo(String p0) throws RemoteException;
  public void setLastModifyBy(Integer p0) throws RemoteException;
  public void setStartDate(String p0) throws RemoteException;
  public void setState(String p0) throws RemoteException;
  public void setTeacher(Integer p0) throws RemoteException;

}