package com.dc.eschool.project.mgrbean;

import java.rmi.*;
import java.util.Collection;
import javax.ejb.*;

import com.dc.eschool.project.dao.ProjectMgrDAO;
import com.dc.eschool.project.ejb.*;
import com.dc.eschool.project.exceptions.*;
import com.dc.eschool.project.model.ProjectModel;

import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * Stateless session Bean implementation for ProjectEJB EJB.
 */
public class ProjectMgrSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private ProjectMgrDAO dao = null;

    /**
     * the ejbCreate methods that does nothing
     */
    public void ejbCreate()
    {
	try
	{
	    dao = new ProjectMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while ProjectMgrBean creating :" + e.getMessage();
	    Debug.println(str);
	    throw new EJBException(str);
	}
    }

    /**
     * the ejbRemove methods
     */
    public void ejbRemove() throws RemoteException
    {
        dao = null;
    }

    /**
     * the ejbActivate methods that does nothing
     */
    public void ejbActivate() throws RemoteException
    {
    }

    /**
     * the ejbPassivate methods
     */
    public void ejbPassivate() throws RemoteException
    {
        dao = null;
    }

    /**
     * Sets the session context
     * @param sc the <code>SessionContext</code> for this instance
     */
    public void setSessionContext(SessionContext sessionContext) throws RemoteException
    {
        this.sessionContext = sessionContext;
    }

    /**
     * Insert an data to Class Table
     * @param pm a ProjectModel that represents the properties.
     * @return an <code>Integer</code> that has the Class information
     *         corresponding to a Class item.
     * @exception <code>ProjectCreateException</code> for irrecoverable errors
     */
    public Integer insertProject(ProjectModel pm) throws ProjectCreateException
    {
        Integer returnValue=new Integer(0);
	try
	{
	    ProjectEBHome home = EJBUtil.getProjectEBHome();
	    ProjectEB remote = home.create(pm);
	    returnValue=remote.getDetails().getProjectID();
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new ProjectCreateException(e);
	}
	return returnValue;
    }

    /**
     * remove record from Class by ClassID
     * @param primKey int primaty key of record
     * @exception <code>ProjectDeleteException</code>
     */
    public void deleteProject(Integer primKey) throws ProjectDeleteException
    {
	try
	{
	    ProjectEBHome home = EJBUtil.getProjectEBHome();
	    ProjectEB remote = home.findByPrimaryKey(primKey);
	    remote.remove();
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while delete Class from Class Table");
	    throw new ProjectDeleteException(e);
	}
    }

    /**
     * update project record
     * @param pm ProjectModel
     */
    public void updateProject(ProjectModel pm) throws ProjectCreateException
    {
	try
	{
	    ProjectEBHome home = EJBUtil.getProjectEBHome();
	    ProjectEB remote = home.findByPrimaryKey(pm.getProjectID());
	    remote.setName(pm.getName());
	    remote.setCourseID(pm.getCourseID());
	    remote.setInfo(pm.getInfo());
	    remote.setState(pm.getState());
	    remote.setPublishResult(pm.getPublishResult());
	    remote.setStartDate(pm.getStartDate());
	    remote.setEndDate(pm.getEndDate());
	    //remote.setAllow(pm.getAllow());
            remote.setLastModifyBy(pm.getLastModifyBy());
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new ProjectCreateException(e);
	}
    }

    public void updateState(String projectID,String state) throws ProjectCreateException
    {
      try
      {
          ProjectEBHome home = EJBUtil.getProjectEBHome();
          ProjectEB remote = home.findByPrimaryKey(new Integer(projectID));
          remote.setState(state);
      }
      catch(Exception e)
      {
          Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
          throw new ProjectCreateException(e);
      }
    }

    public void updateByapp(String courseID,String type,String state)
    {
      try
      {
        getDAO();
        dao.updateByapp(courseID,type,state);
      }catch(Exception e)
      {
        System.out.println(e);
      }
    }
    /**
     * Gets the data from Class Table by aptly SQL
     * @param clause a string that represents the SQL
     * @return the <code>ListChunk</code> that have the Project information
     *         corresponding to class items.
     * @exception <code>ProjectSearchException</code>
     */
    public ListChunk searchProjects(String clause, int startIndex, int count,String course,String value,String userType) throws ProjectSearchException
    {
	try
	{
	    getDAO();
	    return dao.searchProjects(clause, startIndex, count,course,value,userType);
	}
	catch(ProjectDAOFindException pde)
	{
	    throw new ProjectSearchException(pde);
	}
    }

    /**
     * search record by primKey ProjectID
     * @param projectId String the primarykey of Project table
     * return ProjectModel
     */
    public ProjectModel getProject(String projectId)
    {
	ProjectModel pm = new ProjectModel();
	try
	{
	    ProjectEBHome home = EJBUtil.getProjectEBHome();
	    pm.setProjectID(new Integer(projectId));
	    ProjectEB remote = home.findByPrimaryKey(new Integer(projectId));
	    pm=remote.getDetails();
	} catch(Exception se)
	{
	    System.out.println("getProject():" + se.getMessage());
	}
	return pm;
    }

    public Collection getProjectInfoByApp(String courseID,String type)
    {
      try
      {
          getDAO();
          return dao.getProjectInfoByApp(courseID,type);
      }
      catch(Exception pde)
      {
          System.out.println(pde);
          return null;
      }
    }

    public boolean selectExamOrNotForApp(String courseID,String type)
    {
      try
      {
          getDAO();
          return dao.selectExamOrNotForApp(courseID,type);
      }
      catch(Exception pde)
      {
          System.out.println(pde);
          return false;
      }
    }

    /**
     * Create an instance of ProjectMgrDAO
     */
    private void getDAO()
    {
	try
	{
	    dao = new ProjectMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while ProjectMgrBean creating :" + e.getMessage();
	    Debug.println(str);
	    throw new EJBException(str);
	}
    }
}