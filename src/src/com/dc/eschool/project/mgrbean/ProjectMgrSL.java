package com.dc.eschool.project.mgrbean;

import java.rmi.*;
import java.util.Collection;
import javax.ejb.*;

import com.dc.eschool.project.exceptions.*;
import com.dc.eschool.project.model.ProjectModel;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */


/**
 * The remote interface of ProjectMgrSLBean(Statelesee Session bean)
 */
public interface ProjectMgrSL extends EJBObject
{
  public void deleteProject(Integer primKey) throws ProjectDeleteException, RemoteException;
  public ProjectModel getProject(String projectId) throws RemoteException;
  public Integer insertProject(ProjectModel sm) throws ProjectCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchProjects(String clause, int startIndex, int count, String course, String value,String userType) throws ProjectSearchException, RemoteException;
  public void updateProject(ProjectModel sm) throws ProjectCreateException, RemoteException;
  public void updateState(String projectID,String state) throws ProjectCreateException,RemoteException;
  public Collection getProjectInfoByApp(String courseID,String type) throws RemoteException;
  public void updateByapp(String courseID,String type,String state) throws RemoteException;
  public boolean selectExamOrNotForApp(String courseID,String type) throws RemoteException;
}