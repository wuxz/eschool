package com.dc.eschool.schoolresource.mgrbean;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import javax.ejb.FinderException;
import javax.ejb.EJBException;

import java.util.Collection;
import java.util.Locale;

import com.dc.eschool.schoolresource.dao.SchoolResourceMgrDAO;
import com.dc.eschool.schoolresource.model.SchoolResourceModel;

import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAOSysException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceCreateException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceSearchException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAOFinderException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDeleteException;
import com.dc.eschool.schoolresource.ejb.*;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.util.EJBUtil;
import com.dc.eschool.util.Calendar;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * Session Bean implementation of SchoolResource
 *
 */
public class SchoolResourceSLBean implements SessionBean
{
	protected SchoolResourceMgrDAO dao;
	private SessionContext sessionContext;

/**
 * the ejbCreate methods that does nothing
 */
	public void ejbCreate()
	{
	}

/**
 * the ejbRemove methods
 */
	public void ejbRemove() throws RemoteException
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
 * the ejbActivate methods that does nothing
 */
	public void ejbActivate() throws RemoteException
	{
		dao = null;
	}

/**
 * the ejbPassivate methods
 */
	public void ejbPassivate() throws RemoteException
	{
	  dao = null;
	}

/**
 * Insert an data to SchoolResource Table
 *
 * @param pm a SchoolResourceModel that represents the properties.
 * @return an <code>Integer</code> that has the SchoolResource information
 *         corresponding to a SchoolResource account.
 * @exception <code>SchoolResourcecreateException</code> for irrecoverable errors
 */
	public void insertSchoolResource(SchoolResourceModel sm) throws SchoolResourceCreateException
	{
		try
		{
		  SchoolResourceEBHome home = EJBUtil.getSchoolResourceEBHome();
		  SchoolResourceEB remote = home.create(sm);
		}
		catch(Exception e)
		{
		  Debug.print(e,"Exception while createSchoolResource in SCHOOLRESOURCE_MANAGER.");
		  throw new SchoolResourceCreateException(e);
		}
	}

/**
 * remove record from SchoolResource by SchoolResourceID
 * @param primKey int primaty key of record
 * @exception <code>SchoolResourceDeleteException</code>
 */
	public void deleteSchoolResource(Integer primKey) throws SchoolResourceDeleteException
	{
	  try
	  {
		SchoolResourceEBHome home = EJBUtil.getSchoolResourceEBHome();
		SchoolResourceEB remote = home.findByPrimaryKey(primKey);
		remote.remove();
	  }
	  catch(Exception e)
	  {
		Debug.print(e,"Exception while delete schoolresource from SchoolResource Table");
		throw new SchoolResourceDeleteException(e);
	  }
	}


	public void updateSchoolResource(SchoolResourceModel sm) throws SchoolResourceCreateException
	{
	  try
	  {
		SchoolResourceEBHome home = EJBUtil.getSchoolResourceEBHome();
		SchoolResourceEB remote = home.findByPrimaryKey(sm.getSchoolResourceID());
		remote.changeSchoolResource(sm);
	  }
	  catch(Exception e)
	  {
		Debug.print(e,"Exception while createClass in SCHOOLRESOURCE_MANAGER.");
		throw new SchoolResourceCreateException(e);
	  }
	}

/**
 * Gets the data from SchoolResource Table by aptly SQL
 * @param clause a string that represents the SQL
 * @return the <code>ListChunk</code> that have the SchoolResource information
 *         corresponding to schoolresource accounts.     *
 * @exception <code>SchoolResourceSearchException</code>
 */
	public ListChunk searchSchoolResource(String clause, int startindex, int count) throws SchoolResourceSearchException {
		try
		{
			getDAO();
			return dao.searchSchoolResource(clause, startindex, count);
		}
		catch(SchoolResourceDAOFinderException ade)
		{
		  throw new SchoolResourceSearchException(ade);
		}
	}

	public SchoolResourceModel getSchoolResource(String schoolresourceId)
	{
		  SchoolResourceModel sm=null;
		try
		{
		  getDAO();
		  sm=dao.getSchoolResource(schoolresourceId);
		} catch(Exception se)
		{
		  System.out.println("getSchoolResource():" + se.getMessage());
		}
		return sm;
	}

/**
 * Gets the data from SchoolResource Table by aptly SQL
 * @param keyword is a string that appears in the explain, name or subject name
 * @param subjectID is the subjectID of table Subject
 * @return the <code>ListChunk</code> that have the SchoolResource information
 *         corresponding to schoolresource accounts.     *
 * @exception <code>SchoolResourceSearchException</code>
 */
	public ListChunk searchSchoolResource(String keyword, String subjectID, String time, String role, int startindex, int count) throws SchoolResourceSearchException {
		try
		{
			getDAO();
			return dao.searchSchoolResource(keyword, subjectID, time, role, startindex, count);
		}
		catch(SchoolResourceDAOFinderException ade)
		{
		  throw new SchoolResourceSearchException(ade);
		}
	}

/**
 * Create an instance of SchoolResourceMgrDAO
 */
	private void getDAO()
	{
	  try
	  {
		dao = new SchoolResourceMgrDAO();
	  }
	  catch(Exception e)
	  {
		String str = "Exception while SchoolResourceMgrBean creating :" + e.getMessage();
		Debug.println(str);
		throw new EJBException(str);
	  }
	}
}
