package com.dc.eschool.eclass.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.eclass.dao.EClassMgrDAO;
import com.dc.eschool.eclass.ejb.*;
import com.dc.eschool.eclass.exceptions.*;
import com.dc.eschool.eclass.model.EClassModel;
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
 * Stateless session Bean implementation for EClassEJB EJB.
 */
public class EClassSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private EClassMgrDAO dao = null;

    /**
     * the ejbCreate methods that does nothing
     */
    public void ejbCreate()
    {
	try
	{
	    dao = new EClassMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while EClassMgrBean creating :" + e.getMessage();
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
       * @param ecm a EClassModel that represents the properties.
       * @return an <code>Integer</code> that has the Class information
       *         corresponding to a Class item.
       * @exception <code>EClassCreateException</code> for irrecoverable errors
       */
    public void insertEClass(EClassModel ecm) throws EClassCreateException
    {
	try
	{
	    EClassEBHome home = EJBUtil.getEClassEBHome();
	    EClassEB remote = home.create(ecm);
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new EClassCreateException(e);
	}
    }

    /**
       * remove record from Class by ClassID
       * @param primKey int primaty key of record
       * @exception <code>EClassDeleteException</code>
       */
    public void deleteEClass(Integer primKey) throws EClassDeleteException
    {
	try
	{
	    EClassEBHome home = EJBUtil.getEClassEBHome();
	    EClassEB remote = home.findByPrimaryKey(primKey);
	    remote.remove();
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while delete Class from Class Table");
	    throw new EClassDeleteException(e);
	}
    }

    public void updateEClass(EClassModel ecm) throws EClassCreateException
    {
	try
	{
	    EClassEBHome home = EJBUtil.getEClassEBHome();
	    EClassEB remote = home.findByPrimaryKey(ecm.getClassID());
	    remote.setName(ecm.getName());
	    remote.setInfo(ecm.getInfo());
	    remote.setLastModifyBy(ecm.getLastModifyBy());
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new EClassCreateException(e);
	}
    }

    /**
       * Gets the data from Class Table by aptly SQL
       * @param clause a string that represents the SQL
       * @return the <code>ListChunk</code> that have the EClass information
       *         corresponding to class items.
       * @exception <code>EClassSearchException</code>
       */
    public ListChunk searchEClass(String clause, int startIndex, int count) throws EClassSearchException
    {
	try
	{
	    getDAO();
	    return dao.searchEClass(clause, startIndex, count);
	}
	catch(EClassDAOFindException pde)
	{
	    throw new EClassSearchException(pde);
	}
    }

    /**
     * Find record by primary key ClassId
     * @param classId String
     * @return EClassModel
     */
    public EClassModel getEClass(String classId)
    {
	EClassModel ecm=new EClassModel();
	try
	{
	    EClassEBHome home = EJBUtil.getEClassEBHome();
	    ecm.setClassID(new Integer(classId));
	    EClassEB remote = home.findByPrimaryKey(new Integer(classId));
	    ecm=remote.getDetails();
	} catch(Exception se)
	{
	    System.out.println("getEClass():" + se.getMessage());
	}
	return ecm;
    }

    /**
     * Create an instance of EClassMgrDAO
     */
    private void getDAO()
    {
	try
	{
	    dao = new EClassMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while EClassMgrBean creating :" + e.getMessage();
	    Debug.println(str);
	    throw new EJBException(str);
	}
    }
}