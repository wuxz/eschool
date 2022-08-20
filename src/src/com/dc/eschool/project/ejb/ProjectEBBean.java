package com.dc.eschool.project.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.project.dao.ProjectDAO;
import com.dc.eschool.project.exceptions.ProjectAppException;
import com.dc.eschool.project.exceptions.ProjectDAOAppException;
import com.dc.eschool.project.exceptions.ProjectDAODBUpException;
import com.dc.eschool.project.exceptions.ProjectDAODuKeyException;
import com.dc.eschool.project.exceptions.ProjectDAOFindException;
import com.dc.eschool.project.exceptions.ProjectDAOSysException;
import com.dc.eschool.project.model.ProjectModel;

import com.dc.eschool.util.Debug;

/**
 * Title:        ESchool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * Implementation of class as an Entity Bean
 */
public class ProjectEBBean extends ProjectModel implements EntityBean
{
    private EntityContext entityContext;
    private transient ProjectDAO dao;

    /**
    * the ejbCreate methods
    */
    public Integer ejbCreate(ProjectModel pm) throws CreateException, RemoteException
    {
	try
	{
	    getDAO();
	    dao.setPm(pm);
	    dao.create();
	    this.copy(dao.getPm());
	    return (this.projectID);
	}catch(Exception e)
	{
	    String str = "Exception while create ProjectEB: " + e.getMessage();
	    Debug.println(str);
	    throw new CreateException(str);
	}
    }

    /**
    * the ejbPostCreate methods do nothing
    */
    public void ejbPostCreate(ProjectModel pm) throws CreateException, RemoteException
    {
    }

    /**
    * the ejbLoad methods
    */
    public void ejbLoad() throws RemoteException
    {
	try
	{
	    getDAO();
	    dao.getPm().setProjectID(this.projectID);
	    dao.load();
	    this.copy(dao.getPm());
	} catch (ProjectDAOFindException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (ProjectDAOSysException acs)
	{
	    throw new EJBException(acs.getMessage());
	}
    }

    /**
    * the ejbStore methods
    */
    public void ejbStore() throws RemoteException
    {
	try
	{
	    getDAO();
	    dao.getPm().copy(this);
	    dao.store();
	    dao.getPm();
	} catch (ProjectDAOAppException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (ProjectDAOSysException acs)
	{
	    throw new EJBException(acs.getMessage());
	}
    }

    /**
    * the ejbRemove methods
    */
    public void ejbRemove() throws RemoveException, RemoteException
    {
	try
	{
	    getDAO();
	    dao.getPm().setProjectID(this.projectID);
	    dao.remove();
	} catch (ProjectDAODBUpException se)
	{
	    entityContext.setRollbackOnly();
	    throw new RemoveException (se.getMessage());
	} catch (ProjectDAOSysException acs)
	{
	    throw new EJBException(acs.getMessage());
	}
    }

    /**
    * the ejbActive methods do nothing
    */
    public void ejbActivate() throws RemoteException
    {
    }

    /**
    * the ejbPassivate methods
    */
    public void ejbPassivate() throws RemoteException
    {
        this.dao = null;
    }

    /**
     * Sets the session context
     * @param sc the <code>SessionContext</code> for this instance
     */
    public void setEntityContext(EntityContext entityContext) throws RemoteException
    {
        this.entityContext = entityContext;
    }

    public void unsetEntityContext() throws RemoteException
    {
        entityContext = null;
    }

    /**
     * ejbFindByPrimaryKey
     * @param primkKey the <code>Integer</code> for this instance
     * @return Integer if the primKey is exist
     */
    public Integer ejbFindByPrimaryKey(Integer primKey) throws FinderException
    {
	try
	{
	    getDAO();
	    this.projectID = dao.findByPrimaryKey(primKey);
	    dao.getPm();
	    return(this.projectID);
	} catch (ProjectDAOFindException se)
	{
	    throw new FinderException (se.getMessage());
	} catch (ProjectDAOSysException acs)
	{
	    throw new EJBException(acs.getMessage());
	}
    }

    //inner methods

    /**
     * create instance of UsersDAO
     */
    private void getDAO()
    {
	try
	{
	    if(dao == null)
	      dao = new ProjectDAO();
	}catch(Exception e)
	{
	    String str = "Exception while create ProjectEB: " + e.getMessage();
	    System.out.println(str) ;
	}
    }

    public ProjectModel getDetails()
    {
      getDAO();
      return dao.getPm();;
    }
}