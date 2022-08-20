package com.dc.eschool.answeritem.ejb;

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

import com.dc.eschool.answeritem.dao.AnswerItemDAO;
import com.dc.eschool.answeritem.exceptions.AnswerItemAppException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAOAppException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAODBUpdateException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAODupKeyException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAOFinderException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAOSysException;
import com.dc.eschool.answeritem.model.AnswerItemModel;

import com.dc.eschool.util.Debug;

/**
 * Title:        ESchool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author wangshui
 * @version 1.0
 */

/**
 * Implementation of class as an Entity Bean
 */
public class AnswerItemEBBean extends AnswerItemModel implements EntityBean
{
    private AnswerItemModel classDetails;
    private EntityContext entityContext;
    private transient AnswerItemDAO dao;

    /**
    * the ejbCreate methods
    */
    public Integer ejbCreate(AnswerItemModel am) throws CreateException, RemoteException
    {
	try
	{
	    getDAO();
	    dao.setAm(am);
	    dao.create();
	    this.copy(dao.getAm());
	    return (this.answerItemID);
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
    public void ejbPostCreate(AnswerItemModel am) throws CreateException, RemoteException
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
	    dao.getAm().setAnswerItemID(this.answerItemID);
	    dao.load();
	    this.copy(dao.getAm());
	} catch (AnswerItemDAOFinderException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (AnswerItemDAOSysException acs)
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
	    dao.getAm().copy(this);
	    dao.store();
	    dao.getAm();
	} catch (AnswerItemDAOAppException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (AnswerItemDAOSysException acs)
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
	    dao.getAm().setAnswerItemID(this.answerItemID);
	    dao.remove();
	} catch (AnswerItemDAODBUpdateException se)
	{
	    entityContext.setRollbackOnly();
	    throw new RemoveException (se.getMessage());
	} catch (AnswerItemDAOSysException acs)
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
	    this.answerItemID = dao.findByPrimaryKey(primKey);
	    dao.getAm();
	    return(this.answerItemID);
	} catch (AnswerItemDAOFinderException se)
	{
	    throw new FinderException (se.getMessage());
	} catch (AnswerItemDAOSysException acs)
	{
	    throw new EJBException(acs.getMessage());
	}
    }

    public AnswerItemModel getDetails()
    {
	getDAO();
	return dao.getAm();
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
	      dao = new AnswerItemDAO();
	}catch(Exception e)
	{
	    String str = "Exception while create ProjectEB: " + e.getMessage();
	    System.out.println(str) ;
	}
    }

}