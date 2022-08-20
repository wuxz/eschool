package com.dc.eschool.subject.ejb;

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

import com.dc.eschool.subject.dao.SubjectDAO;
import com.dc.eschool.subject.exceptions.SubjectAppException;
import com.dc.eschool.subject.exceptions.SubjectDAOAppException;
import com.dc.eschool.subject.exceptions.SubjectDAODBUpException;
import com.dc.eschool.subject.exceptions.SubjectDAODuKeyException;
import com.dc.eschool.subject.exceptions.SubjectDAOFindException;
import com.dc.eschool.subject.exceptions.SubjectDAOSysException;
import com.dc.eschool.subject.model.SubjectModel;

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
public class SubjectEBBean extends SubjectModel implements EntityBean
{
    private EntityContext entityContext;
    private transient SubjectDAO dao;

    /**
    * the ejbCreate methods
    */
    public Integer ejbCreate(SubjectModel sm) throws CreateException, RemoteException
    {
	try
	{
	    getDAO();
	    dao.setSm(sm);
	    dao.create();
	    this.copy(dao.getSm());
	    return (this.subjectID);
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
    public void ejbPostCreate(SubjectModel sm) throws CreateException, RemoteException
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
	    dao.getSm().setSubjectID(this.subjectID);
	    dao.load();
	    this.copy(dao.getSm());
	} catch (SubjectDAOFindException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (SubjectDAOSysException acs)
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
	    dao.getSm().copy(this);
	    dao.store();
	    dao.getSm();
	} catch (SubjectDAOAppException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (SubjectDAOSysException acs)
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
	    dao.getSm().setSubjectID(this.subjectID);
	    dao.remove();
	} catch (SubjectDAODBUpException se)
	{
	    entityContext.setRollbackOnly();
	    throw new RemoveException (se.getMessage());
	} catch (SubjectDAOSysException acs)
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
	    this.subjectID = dao.findByPrimaryKey(primKey);
	    dao.getSm();
	    return(this.subjectID);
	} catch (SubjectDAOFindException se)
	{
	    throw new FinderException (se.getMessage());
	} catch (SubjectDAOSysException acs)
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
	      dao = new SubjectDAO();
	}catch(Exception e)
	{
	    String str = "Exception while create ProjectEB: " + e.getMessage();
	    System.out.println(str) ;
	}
    }

    public SubjectModel getDetails()
    {
	getDAO();
	return dao.getSm();
    }
}