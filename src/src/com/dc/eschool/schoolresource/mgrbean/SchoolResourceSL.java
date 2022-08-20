package com.dc.eschool.schoolresource.mgrbean;

import java.util.Collection;
import java.util.Locale;
import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;

import com.dc.eschool.schoolresource.exceptions.*;


/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface is the remote interface for the SchoolResourceSL EJB.
 * It provides the ejb-tier implementation of the List functions
 */

public interface SchoolResourceSL extends EJBObject
{
  public void insertSchoolResource(com.dc.eschool.schoolresource.model.SchoolResourceModel sm) throws SchoolResourceCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchSchoolResource(java.lang.String clause, int startindex, int count) throws SchoolResourceSearchException, RemoteException;
  public com.dc.eschool.util.ListChunk searchSchoolResource(java.lang.String keyword, java.lang.String subjectID, java.lang.String time, java.lang.String role, int startindex, int count) throws SchoolResourceSearchException, RemoteException;
  public com.dc.eschool.schoolresource.model.SchoolResourceModel getSchoolResource(java.lang.String schoolResourceId) throws RemoteException;
  public void deleteSchoolResource(Integer primKey) throws SchoolResourceDeleteException, RemoteException;
  public void updateSchoolResource(com.dc.eschool.schoolresource.model.SchoolResourceModel sm) throws SchoolResourceCreateException, RemoteException;
}
