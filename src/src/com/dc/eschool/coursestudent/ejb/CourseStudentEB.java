package com.dc.eschool.coursestudent.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.dc.eschool.coursestudent.model.CourseStudentModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author eric
 * @version 1.0
 */

/**
 * This interface provides methods to view and modify coursestudent
 * information for a particular coursestudent.
*/

public interface CourseStudentEB extends EJBObject
{
  public void setAllow(String p0) throws RemoteException;
  public void setCourseID(Integer p0) throws RemoteException;
  public void setLastModifyBy(Integer p0) throws RemoteException;
  public void setStudent(Integer p0) throws RemoteException;

}