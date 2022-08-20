package com.dc.eschool.eclass.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import javax.naming.NamingException;
import javax.naming.InitialContext;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.eclass.dao.EClassDAO;
import com.dc.eschool.eclass.exceptions.EClassAppException;
import com.dc.eschool.eclass.exceptions.EClassDAOAppException;
import com.dc.eschool.eclass.exceptions.EClassDAODBUpException;
import com.dc.eschool.eclass.exceptions.EClassDAODuKeyException;
import com.dc.eschool.eclass.exceptions.EClassDAOFindException;
import com.dc.eschool.eclass.exceptions.EClassDAOSysException;
import com.dc.eschool.eclass.model.EClassModel;

import com.dc.eschool.util.Debug;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * Implementation of class as an Entity Bean
 */
public class EClassEBBean extends EClassModel implements EntityBean
{
    private EntityContext entityContext;
    private transient EClassDAO dao;

    /**
    * the ejbCreate methods
    */
    public Integer ejbCreate(EClassModel ecm) throws CreateException, RemoteException
    {
	try
	{
	    getDAO();
	    dao.setEcm(ecm);
	    dao.create();
	    this.copy(dao.getEcm());
	    return (this.classID);
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
    public void ejbPostCreate(EClassModel cm) throws CreateException, RemoteException
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
	    dao.getEcm().setClassID(this.classID);
	    dao.load();
	    this.copy(dao.getEcm());
	} catch (EClassDAOFindException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (EClassDAOSysException acs)
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
	    dao.getEcm().copy(this);
	    dao.store();
	    dao.getEcm();
	} catch (EClassDAOAppException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (EClassDAOSysException acs)
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
	    dao.getEcm().setClassID(this.classID);
	    dao.remove();
	} catch (EClassDAODBUpException se)
	{
	    entityContext.setRollbackOnly();
	    throw new RemoveException (se.getMessage());
	} catch (EClassDAOSysException acs)
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
	    this.classID = dao.findByPrimaryKey(primKey);
	    dao.getEcm();
	    return(this.classID);
	} catch (EClassDAOFindException se)
	{
	    throw new FinderException (se.getMessage());
	} catch (EClassDAOSysException acs)
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
	    {
	      dao = new EClassDAO();
	    }
	}catch(Exception e)
	{
	    String str = "Exception while create ProjectEB: " + e.getMessage();
	    System.out.println(str) ;
	}
    }

    public EClassModel getDetails()
    {
	getDAO();
	return dao.getEcm();
    }
}