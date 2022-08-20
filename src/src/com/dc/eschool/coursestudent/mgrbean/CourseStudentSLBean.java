package com.dc.eschool.coursestudent.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.coursestudent.dao.CourseStudentMgrDAO;
import com.dc.eschool.coursestudent.ejb.*;
import com.dc.eschool.coursestudent.model.CourseStudentModel;

import com.dc.eschool.coursestudent.exceptions.*;
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
 * Stateless session Bean implementation for CourseStudentEJB EJB.
 */
public class CourseStudentSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private CourseStudentMgrDAO dao = null;

    /**
     * the ejbCreate methods that does nothing
     */
    public void ejbCreate()
    {
        try
        {
            dao = new CourseStudentMgrDAO();
        }
        catch(Exception e)
        {
            String str = "Exception while CourseStudentMgrBean creating :" + e.getMessage();
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
       * @param sm a CourseStudentModel that represents the properties.
       * @return an <code>Integer</code> that has the Class information
       *         corresponding to a Class item.
       * @exception <code>CourseStudentCreateException</code> for irrecoverable errors
       */
    public void insertCourseStudent(CourseStudentModel sm) throws CSCreateException
    {
        try
        {
            CourseStudentEBHome home = EJBUtil.getCourseStudentEBHome();
            CourseStudentEB remote = home.create(sm);
        }
        catch(Exception e)
        {
            Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
            throw new CSCreateException(e);
        }
    }

    /**
       * remove record from Class by ClassID
       * @param primKey int primaty key of record
       * @exception <code>CourseStudentDeleteException</code>
       */
    public void deleteCourseStudent(Integer student,Integer courseID) throws CSDeleteException
    {
        try
        {
            getDAO();
            Integer primKey=dao.searchPKBySC(student,courseID);
            CourseStudentEBHome home = EJBUtil.getCourseStudentEBHome();
            CourseStudentEB remote = home.findByPrimaryKey(primKey);
            remote.remove();
        }
        catch(Exception e)
        {
            Debug.print(e,"Exception while delete Class from Class Table");
            throw new CSDeleteException(e);
        }
    }

    /**
       * Gets the data from Class Table by aptly SQL
       * @param clause a string that represents the SQL
       * @return the <code>ListChunk</code> that have the CourseStudent information
       *         corresponding to class items.
       * @exception <code>CourseStudentSearchException</code>
       */
    public ListChunk searchCourseStudent(String clause, int startIndex, int count) throws CSSearchException
    {
        try
        {
            getDAO();
            return dao.searchCourseStudent(clause, startIndex, count);
        }
        catch(CSDAOFindException pde)
        {
            throw new CSSearchException(pde);
        }
    }

    /**
     * Create an instance of CourseStudentMgrDAO
     */
    private void getDAO()
    {
        try
        {
            dao = new CourseStudentMgrDAO();
        }
        catch(Exception e)
        {
            String str = "Exception while CourseStudentMgrBean creating :" + e.getMessage();
            Debug.println(str);
            throw new EJBException(str);
        }
    }
}