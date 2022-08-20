package com.dc.eschool.subject.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.subject.dao.SubjectMgrDAO;
import com.dc.eschool.subject.ejb.*;
import com.dc.eschool.subject.exceptions.*;
import com.dc.eschool.subject.model.SubjectModel;

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
 * Stateless session Bean implementation for SubjectEJB EJB.
 */
public class SubjectSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private SubjectMgrDAO dao = null;

    /**
     * the ejbCreate methods that does nothing
     */
    public void ejbCreate()
    {
	try
	{
	    dao = new SubjectMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while SubjectMgrBean creating :" + e.getMessage();
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
     * @param sm a SubjectModel that represents the properties.
     * @return an <code>Integer</code> that has the Class information
     *         corresponding to a Class item.
     * @exception <code>SubjectCreateException</code> for irrecoverable errors
     */
    public void insertSubject(SubjectModel sm) throws SubjectCreateException
    {

	try
	{
	    SubjectEBHome home = EJBUtil.getSubjectEBHome();
	    SubjectEB remote = home.create(sm);
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new SubjectCreateException(e);
	}

    }

    /**
     * remove record from Class by ClassID
     * @param primKey int primaty key of record
     * @exception <code>SubjectDeleteException</code>
     */
    public void deleteSubject(Integer primKey) throws SubjectDeleteException
    {
	try
	{
	    SubjectEBHome home = EJBUtil.getSubjectEBHome();
	    SubjectEB remote = home.findByPrimaryKey(primKey);
	    remote.remove();
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while delete Class from Class Table");
	    throw new SubjectDeleteException(e);
	}
    }

    /**
     * update subject record
     * @param sm SubjectModel
     */
    public void updateSubject(SubjectModel sm) throws SubjectCreateException
    {
	try
	{
	    SubjectEBHome home = EJBUtil.getSubjectEBHome();
	    SubjectEB remote = home.findByPrimaryKey(sm.getSubjectID());
	    remote.setName(sm.getName());
	    remote.setLastModifyBy(sm.getLastModifyBy());
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new SubjectCreateException(e);
	}
    }

    /**
     * Gets the data from Class Table by aptly SQL
     * @param clause a string that represents the SQL
     * @return the <code>ListChunk</code> that have the Subject information
     *         corresponding to class items.
     * @exception <code>SubjectSearchException</code>
     */
    public ListChunk searchSubject(String clause, int startIndex, int count) throws SubjectSearchException
    {
	try
	{
	    getDAO();
	    return dao.searchSubject(clause, startIndex, count);
	}
	catch(SubjectDAOFindException pde)
	{
	    throw new SubjectSearchException(pde);
	}
    }

    /**
     * search record by primKey SubjectID
     * @param subjectId String the primarykey of Subject table
     * return SubjectModel
     */
    public SubjectModel getSubject(String subjectId)
    {
	SubjectModel sm = new SubjectModel();
	try
	{
	    SubjectEBHome home=EJBUtil.getSubjectEBHome();
	    sm.setSubjectID(new Integer(subjectId));
	    SubjectEB remote = home.findByPrimaryKey(new Integer(subjectId));
	    sm=remote.getDetails();
	} catch(Exception se)
	{
	    System.out.println("getSubject():" + se.getMessage());
	}
	return sm;
    }

    /**
     * Create an instance of SubjectMgrDAO
     */
    private void getDAO()
    {
	try
	{
	    dao = new SubjectMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while SubjectMgrBean creating :" + e.getMessage();
	    Debug.println(str);
	    throw new EJBException(str);
	}
    }
}