package com.dc.eschool.content.ejb;

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

import com.dc.eschool.content.dao.ContentDAO;
import com.dc.eschool.content.exceptions.ContentAppException;
import com.dc.eschool.content.exceptions.ContentDAOAppException;
import com.dc.eschool.content.exceptions.ContentDAODBUpException;
import com.dc.eschool.content.exceptions.ContentDAODuKeyException;
import com.dc.eschool.content.exceptions.ContentDAOFindException;
import com.dc.eschool.content.exceptions.ContentDAOSysException;
import com.dc.eschool.content.model.ContentModel;

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
public class ContentEBBean extends ContentModel implements EntityBean
{
    private ContentModel classDetails;
    private EntityContext entityContext;
    private transient ContentDAO dao;

    /**
    * the ejbCreate methods
    */
    public Integer ejbCreate(ContentModel cm) throws CreateException, RemoteException
    {
	try
	{
	    getDAO();
	    dao.setCm(cm);
	    dao.create();
	    this.copy(dao.getCm());
	    return (this.contentID);
	}catch(Exception e)
	{
	    String str = "Exception while create ContentEB: " + e.getMessage();
	    Debug.println(str);
	    throw new CreateException(str);
	}
    }

    /**
    * the ejbPostCreate methods do nothing
    */
    public void ejbPostCreate(ContentModel cm) throws CreateException, RemoteException
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
	    dao.getCm().setContentID(this.contentID);
	    dao.load();
	    this.copy(dao.getCm());
	} catch (ContentDAOFindException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (ContentDAOSysException acs)
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
	    dao.getCm().copy(this);
	    dao.store();
	    dao.getCm();
	} catch (ContentDAOAppException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (ContentDAOSysException acs)
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
	    dao.getCm().setContentID(this.contentID);
	    dao.remove();
	} catch (ContentDAODBUpException se)
	{
	    entityContext.setRollbackOnly();
	    throw new RemoveException (se.getMessage());
	} catch (ContentDAOSysException acs)
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
	    this.contentID = dao.findByPrimaryKey(primKey);
	    dao.getCm();
	    return(this.contentID);
	} catch (ContentDAOFindException se)
	{
	    throw new FinderException (se.getMessage());
	} catch (ContentDAOSysException acs)
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
	      dao = new ContentDAO();
	}catch(Exception e)
	{
	    String str = "Exception while create ContentEB: " + e.getMessage();
	    System.out.println(str) ;
	}
    }

    public ContentModel getDetails()
    {
      getDAO();
      return dao.getCm();
    }
}