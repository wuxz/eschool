package com.dc.eschool.answeritem.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.dc.eschool.answeritem.model.AnswerItemModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface provides methods to view and modify AnswerItem
 * information for a particular answeritem.
*/

public interface AnswerItemEB extends EJBObject
{
  public void setType(java.lang.String p0) throws RemoteException;
  public void setMemo(java.lang.String p0) throws RemoteException;
  public void setLastModifyBy(java.lang.Integer p0) throws RemoteException;
  public void setItemNumber(java.lang.Integer p0) throws RemoteException;
  public void setContentID(java.lang.Integer p0) throws RemoteException;
  public void setAnswerNumber(java.lang.String p0) throws RemoteException;
  public void setAnswer(java.lang.String p0) throws RemoteException;
  public void setAllow(java.lang.String p0) throws RemoteException;
  public com.dc.eschool.answeritem.model.AnswerItemModel getDetails() throws RemoteException;
}