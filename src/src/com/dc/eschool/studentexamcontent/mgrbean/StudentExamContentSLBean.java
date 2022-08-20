package com.dc.eschool.studentexamcontent.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.studentexamcontent.dao.StudentExamContentMgrDAO;
import com.dc.eschool.studentexamcontent.ejb.*;
import com.dc.eschool.studentexamcontent.exceptions.*;
import com.dc.eschool.studentexamcontent.model.StudentExamContentModel;

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
 * Stateless session Bean implementation for StudentExamContentEJB EJB.
 */
public class StudentExamContentSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private StudentExamContentMgrDAO dao = null;

    /**
     * the ejbCreate methods that does nothing
     */
    public void ejbCreate()
    {
	try
	{
	    dao = new StudentExamContentMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while StudentExamContentMgrBean creating :" + e.getMessage();
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
     * @param secm a StudentExamContentModel that represents the properties.
     * @return an <code>Integer</code> that has the Class information
     *         corresponding to a Class item.
     * @exception <code>StudentExamContentCreateException</code> for irrecoverable errors
     */
    public void insertStudentExamContent(StudentExamContentModel secm) throws StudentExamContentCreateException
    {

	try
	{
	    StudentExamContentEBHome home = EJBUtil.getStudentExamContentEBHome();
	    StudentExamContentEB remote = home.create(secm);
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new StudentExamContentCreateException(e);
	}

    }

    /**
     * remove record from Class by ClassID
     * @param primKey int primaty key of record
     * @exception <code>StudentExamContentDeleteException</code>
     */
    public void deleteStudentExamContent(Integer primKey) throws StudentExamContentDeleteException
    {
	try
	{
	    StudentExamContentEBHome home = EJBUtil.getStudentExamContentEBHome();
	    StudentExamContentEB remote = home.findByPrimaryKey(primKey);
	    remote.remove();
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while delete Class from Class Table");
	    throw new StudentExamContentDeleteException(e);
	}
    }

    public void deleteByApp(String courseID)
    {
	try
	{
	    getDAO();
	    dao.deleteStudentExamContentByApp(courseID);
	} catch (Exception se)
	{
	    System.out.println(se);
	}
    }

    /**
     * update studentexamcontent record
     * @param secm StudentExamContentModel
     */
    public void updateStudentExamContent(StudentExamContentModel secm) throws StudentExamContentCreateException
    {
	try
	{
	    StudentExamContentEBHome home = EJBUtil.getStudentExamContentEBHome();
	    StudentExamContentEB remote = home.findByPrimaryKey(secm.getStudentExamContentID());
	    //remote.setName(secm.getName());
	    //remote.setLastModifyBy(secm.getLastModifyBy());
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new StudentExamContentCreateException(e);
	}
    }

    public boolean isGrouped(String courseID)
    {
        boolean returnValue=false;
        try
	{
	    getDAO();
            returnValue=dao.isGrouped(courseID);
	}
	catch(Exception e)
	{
	    System.out.println(e);
	}
        return returnValue;
    }

    public Integer getContentIDByApp(String userID,String courseID)
    {
        Integer returnValue=null;
        try
	{
	    getDAO();
            returnValue=dao.getContentIDByApp(userID,courseID);
	}
	catch(Exception e)
	{
	    System.out.println(e);
	}
        return returnValue;
    }

    /**
     * Gets the data from Class Table by aptly SQL
     * @param clause a string that represents the SQL
     * @return the <code>ListChunk</code> that have the StudentExamContent information
     *         corresponding to class items.
     * @exception <code>StudentExamContentSearchException</code>
     */
    public ListChunk searchStudentExamContent(String clause, int startIndex, int count) throws StudentExamContentSearchException
    {
	try
	{
	    getDAO();
	    return dao.searchStudentExamContent(clause, startIndex, count);
	}
	catch(StudentExamContentDAOFindException pde)
	{
	    throw new StudentExamContentSearchException(pde);
	}
    }

    /**
     * search record by primKey StudentExamContentID
     * @param studentexamcontentId String the primarykey of StudentExamContent table
     * return StudentExamContentModel
     */
    public StudentExamContentModel getStudentExamContent(String studentexamcontentId)
    {
	StudentExamContentModel secm = new StudentExamContentModel();
	try
	{
	    StudentExamContentEBHome home=EJBUtil.getStudentExamContentEBHome();
	    secm.setStudentExamContentID(new Integer(studentexamcontentId));
	    StudentExamContentEB remote = home.findByPrimaryKey(new Integer(studentexamcontentId));
	    secm=remote.getDetails();
	} catch(Exception se)
	{
	    System.out.println("getStudentExamContent():" + se.getMessage());
	}
	return secm;
    }

    /**
     * Create an instance of StudentExamContentMgrDAO
     */
    private void getDAO()
    {
	try
	{
	    dao = new StudentExamContentMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while StudentExamContentMgrBean creating :" + e.getMessage();
	    Debug.println(str);
	    throw new EJBException(str);
	}
    }
}