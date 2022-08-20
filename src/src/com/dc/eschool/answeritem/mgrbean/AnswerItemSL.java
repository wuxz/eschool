package com.dc.eschool.answeritem.mgrbean;

import java.util.Collection;
import java.util.Locale;
import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import com.dc.eschool.answeritem.exceptions.*;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface is the remote interface for the AnswerItemSL EJB.
 * It provides the ejb-tier implementation of the List functions
 */

public interface AnswerItemSL extends EJBObject
{
  public void deleteAnswerItem(Integer primKey) throws AnswerItemDeleteException, RemoteException;
  public com.dc.eschool.answeritem.model.AnswerItemModel getAnswerItem(String subjectId) throws RemoteException;
  public void insertAnswerItem(com.dc.eschool.answeritem.model.AnswerItemModel sm) throws AnswerItemCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchAnswerItem(String clause, int startIndex, int count) throws AnswerItemSearchException, RemoteException;
  public void updateAnswerItem(com.dc.eschool.answeritem.model.AnswerItemModel sm) throws AnswerItemCreateException, RemoteException;
}
