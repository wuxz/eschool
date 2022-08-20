package com.dc.eschool.testresultsitem.mgrbean;

import java.util.Collection;
import java.util.Locale;
import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;

import com.dc.eschool.testresultsitem.exceptions.*;
import com.dc.eschool.testresultsitem.model.*;
import java.util.Vector;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface is the remote interface for the TestResultsItemSL EJB.
 * It provides the ejb-tier implementation of the List functions
 */

public interface TestResultsItemSL extends EJBObject
{
  public void deleteTestResultsItem(Integer primKey) throws TestResultsItemDeleteException, RemoteException;
  public TestResultsItemModel getTestResultsItem(String testResultItemID) throws RemoteException;
  public void insertTestResultsItem(Vector models) throws TestResultsItemCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchTestResultsItem(String clause, int startIndex, int count) throws TestResultsItemSearchException, RemoteException;
  public void updateTestResultsItem(Vector models) throws TestResultsItemCreateException, RemoteException;
}
