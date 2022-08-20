package com.dc.eschool.content.mgrbean;

import java.rmi.*;
import java.util.Collection;
import javax.ejb.*;

import com.dc.eschool.content.exceptions.*;
import com.dc.eschool.content.model.ContentModel;
/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */


/**
 * The remote interface of ContentMgrSLBean(Statelesee Session bean)
 */
public interface ContentMgrSL extends EJBObject
{
  public ContentModel getContent(String contentId) throws RemoteException;
  public void insertContent(ContentModel cm) throws ContentCreateException, RemoteException;
  public void insertProjectContent(ContentModel cm) throws ContentCreateException, RemoteException;
  public void updateContent(ContentModel cm) throws ContentCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchContents(java.lang.String clause, int startIndex, int count,String value) throws ContentSearchException, RemoteException;
  public com.dc.eschool.util.ListChunk searchPC(String clause, int startIndex, int count,String value,String info,String time) throws ContentSearchException, RemoteException;
  public Collection getContentByApp(String projectID) throws RemoteException;
  public Collection getCheckedContentByApp(String courseID,String type) throws RemoteException;
  public void changeInfo(java.lang.Integer contentID, java.lang.String info, java.lang.String state) throws ContentCreateException, RemoteException;
  public void deleteContent(com.dc.eschool.content.model.ContentModel cm) throws ContentDeleteException, RemoteException;
}