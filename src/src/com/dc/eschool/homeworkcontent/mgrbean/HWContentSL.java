package com.dc.eschool.homeworkcontent.mgrbean;

import java.rmi.*;
import java.util.Vector;
import javax.ejb.*;
import com.dc.eschool.homeworkcontent.exceptions.*;
import com.dc.eschool.homeworkcontent.model.HWContentModel;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * The remote interface of HWContentSLBean(Statelesee Session bean)
 */
public interface HWContentSL extends EJBObject
{
  public void deleteHomeWorkContent(java.lang.Integer primKey) throws HCDeleteException, RemoteException;
  public void insertHomeWorkContent(com.dc.eschool.homeworkcontent.model.HWContentModel sm) throws HCCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchHomeWorkContent(java.lang.String clause, int startIndex, int count, String orderBy) throws HCSearchException, RemoteException;
  public void updateHomeWorkContent(com.dc.eschool.homeworkcontent.model.HWContentModel sm) throws HCCreateException, RemoteException;
  public Vector getHomeWorkID(Integer primaryKey) throws RemoteException;
  public Vector unHandInStudent(Integer projectID) throws RemoteException;
  public int getRestCount(Integer projectID) throws RemoteException;
  public Integer isHaveDocURL(String docURL) throws RemoteException;
  public boolean isHaveOther(String homeworkId) throws RemoteException;
  public HWContentModel getHomeWorkContent(String homeWorkContentId) throws RemoteException;
}