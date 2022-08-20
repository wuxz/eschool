package com.dc.eschool.users.ejb;

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

import com.dc.eschool.users.dao.UsersDAO;
import com.dc.eschool.users.exceptions.UAppException;
import com.dc.eschool.users.exceptions.UDAOAppException;
import com.dc.eschool.users.exceptions.UDAODBUpException;
import com.dc.eschool.users.exceptions.UDAODuKeyException;
import com.dc.eschool.users.exceptions.UDAOFindException;
import com.dc.eschool.users.exceptions.UDAOSysException;
import com.dc.eschool.users.model.UsersModel;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.Calendar;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

 /**
 * Implementation of Users as an Entity Bean
 */
public class UsersEBBean extends UsersModel implements EntityBean
{
	private EntityContext entityContext;
	private transient UsersDAO dao;

	/**
	 * the ejbCreate methods
	 */
	public Integer ejbCreate(UsersModel um) throws CreateException, RemoteException
	{
	  try
	  {
	  getDAO();
	  dao.setUm(um);
	  dao.create();
	  this.copy(dao.getUm());
	  return (this.userID);
	  }catch(Exception e)
	  {
	  String str = "Exception while create UsersEB(ejbcreate): " + e.getMessage();
	  Debug.println(str);
	  throw new CreateException(str);
	  }
	}

	/**
	 * the ejbPostCreate methods do nothing
	 */
	public void ejbPostCreate(UsersModel um) throws CreateException, RemoteException
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
	  dao.getUm().setUserID(this.userID);
	  dao.load();
	  this.copy(dao.getUm());
	  } catch (UDAOFindException se)
	  {
	  throw new EJBException (se.getMessage());
	  } catch (UDAOSysException acs)
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
		dao.getUm().copy(this);
		dao.store();
		dao.getUm();
	} catch (UDAOAppException se)
	{
		throw new EJBException (se.getMessage());
	} catch (UDAOSysException acs)
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
		dao.getUm().setUserID(this.userID);
		dao.remove();
	} catch (UDAODBUpException se) {
		entityContext.setRollbackOnly();
		throw new RemoveException (se.getMessage());
	} catch (UDAOSysException acs) {
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

	/**
	   * release the  Context
	   */
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
		this.userID = dao.findByPrimaryKey(primKey);
		dao.getUm();
		return(this.userID);
	} catch (UDAOFindException se)
	{
		throw new FinderException (se.getMessage());
	} catch (UDAOSysException acs)
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
			dao = new UsersDAO();
		}
	}catch(Exception e)
	{
		String str = "Exception while create UsersEB(getDao): " + e.getMessage();
		System.out.println(str) ;
	}
	}
	public UsersModel getDetails()
	{
	getDAO();
	return dao.getUm();
	}
}