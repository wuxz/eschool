package com.dc.eschool.sessiondata;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.content.exceptions.*;
import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.util.ListChunk;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */


/**
 * The remote interface of SessionSLBean(Statelesee Session bean)
 */
public interface SessionSL extends EJBObject
{
  public void addProject(ContentModel cm,String type) throws RemoteException;
  public ListChunk getProject(Integer projectID,String type) throws RemoteException;
  public void deleteProject(ContentModel cm) throws RemoteException;
  public void addHomeWork(Integer homeWorkID,Integer homeWorkContentID) throws RemoteException;
  public ListChunk getHomeWork(Integer homeWorkID) throws RemoteException;
  public void deleteHomeWork(Integer homeWorkID,Integer homeWorkContentID) throws RemoteException;
  public void removeAll() throws RemoteException;
}