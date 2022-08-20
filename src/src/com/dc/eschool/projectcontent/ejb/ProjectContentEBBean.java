package com.dc.eschool.projectcontent.ejb;

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

import com.dc.eschool.projectcontent.dao.ProjectContentDAO;
import com.dc.eschool.projectcontent.exceptions.ProjectContentAppException;
import com.dc.eschool.projectcontent.exceptions.ProjectContentDAOAppException;
import com.dc.eschool.projectcontent.exceptions.ProjectContentDAODBUpException;
import com.dc.eschool.projectcontent.exceptions.ProjectContentDAODuKeyException;
import com.dc.eschool.projectcontent.exceptions.ProjectContentDAOFindException;
import com.dc.eschool.projectcontent.exceptions.ProjectContentDAOSysException;
import com.dc.eschool.projectcontent.model.ProjectContentModel;

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
public class ProjectContentEBBean extends ProjectContentModel implements EntityBean
{
	private ProjectContentModel classDetails;
	private EntityContext entityContext;
	private transient ProjectContentDAO dao;

	/**
	* the ejbCreate methods
	*/
	public Integer ejbCreate(ProjectContentModel pcm) throws CreateException, RemoteException
	{
	try
	{
		getDAO();
		dao.setPcm(pcm);
		dao.create();
		this.copy(dao.getPcm());
		return (this.projectContentID);
	}catch(Exception e)
	{
		String str = "Exception while create ProjectContentEB: " + e.getMessage();
		Debug.println(str);
		throw new CreateException(str);
	}
	}

	/**
	* the ejbPostCreate methods do nothing
	*/
	public void ejbPostCreate(ProjectContentModel pcm) throws CreateException, RemoteException
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
		dao.getPcm().setProjectContentID(this.projectContentID);
		dao.load();
		this.copy(dao.getPcm());
	} catch (ProjectContentDAOFindException se)
	{
		throw new EJBException (se.getMessage());
	} catch (ProjectContentDAOSysException acs)
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
		dao.getPcm().copy(this);
		dao.store();
		dao.getPcm();
	} catch (ProjectContentDAOAppException se)
	{
		throw new EJBException (se.getMessage());
	} catch (ProjectContentDAOSysException acs)
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
		dao.getPcm().setProjectContentID(this.projectContentID);
		dao.remove();
	} catch (ProjectContentDAODBUpException se)
	{
		entityContext.setRollbackOnly();
		throw new RemoveException (se.getMessage());
	} catch (ProjectContentDAOSysException acs)
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
		this.projectContentID = dao.findByPrimaryKey(primKey);
		dao.getPcm();
		return(this.projectContentID);
	} catch (ProjectContentDAOFindException se)
	{
		throw new FinderException (se.getMessage());
	} catch (ProjectContentDAOSysException acs)
	{
		throw new EJBException(acs.getMessage());
	}
	}

	public Integer ejbFindByProjectContent(Integer projectID, Integer contentID) throws FinderException
	{
		try
		{
			getDAO();
			this.projectContentID = dao.findByPrimaryKey(projectID, contentID);
			dao.getPcm();
			return(this.projectContentID);
		}
		catch (ProjectContentDAOFindException se)
		{
			throw new FinderException (se.getMessage());
		}
		catch (ProjectContentDAOSysException acs)
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
		  dao = new ProjectContentDAO();
	}catch(Exception e)
	{
		String str = "Exception while create ProjectContentEB: " + e.getMessage();
		System.out.println(str) ;
	}
	}

	public ProjectContentModel getDetails()
	{
	  getDAO();
	  return dao.getPcm();
	}
}