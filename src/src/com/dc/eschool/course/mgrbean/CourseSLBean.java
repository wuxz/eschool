package com.dc.eschool.course.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.course.dao.CourseMgrDAO;
import com.dc.eschool.course.ejb.*;
import com.dc.eschool.course.exceptions.*;
import com.dc.eschool.course.model.CourseModel;
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
 * Stateless session Bean implementation for CourseEJB EJB.
 */
public class CourseSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private CourseMgrDAO dao = null;

    /**
     * the ejbCreate methods that does nothing
     */
    public void ejbCreate()
    {
	try
	{
	    dao = new CourseMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while CourseMgrBean creating :" + e.getMessage();
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
       * Insert an data to Class Table     *
       * @param cm a CourseModel that represents the properties.
       * @return an <code>Integer</code> that has the Class information
       *         corresponding to a Class item.
       * @exception <code>CourseCreateException</code> for irrecoverable errors
       */
    public void insertCourse(CourseModel cm) throws CourseCreateException
    {
	try
	{
	    CourseEBHome home = EJBUtil.getCourseEBHome();
	    CourseEB remote = home.create(cm);
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new CourseCreateException(e);
	}
    }

    /**
       * remove record from Class by ClassID
       * @param primKey int primaty key of record
       * @exception <code>CourseDeleteException</code>
       */
    public void deleteCourse(Integer primKey) throws CourseDeleteException
    {
	try
	{
	    CourseEBHome home = EJBUtil.getCourseEBHome();
	    CourseEB remote = home.findByPrimaryKey(primKey);
	    remote.remove();
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while delete Class from Class Table");
	    throw new CourseDeleteException(e);
	}
    }

    /**
     * modify course record
     * @param cm CourseModel
     * @exception CourseCreateException
     */
    public void updateCourse(CourseModel cm) throws CourseCreateException
    {
	try
	{
	    CourseEBHome home = EJBUtil.getCourseEBHome();
	    CourseEB remote = home.findByPrimaryKey(cm.getCourseID());
	    remote.setCourseName(cm.getCourseName());
	    remote.setTeacher(cm.getTeacher());
	    remote.setStartDate(cm.getStartDate());
	    remote.setEndDate(cm.getEndDate());
	    remote.setInfo(cm.getInfo());
	    remote.setState(cm.getState());
	    remote.setLastModifyBy(cm.getLastModifyBy());
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new CourseCreateException(e);
	}
    }

    /**
       * Gets the data from Class Table by aptly SQL
       * @param clause a string that represents the SQL
       * @return the <code>ListChunk</code> that have the Course information
       *         corresponding to class items.
       * @exception <code>CourseSearchException</code>
       */
    public ListChunk searchCourse(String clause, int startIndex, int count, String value) throws CourseSearchException
    {
	try
	{
	    getDAO();
	    return dao.searchCourse(clause, startIndex, count, value);
	}
	catch(CourseDAOFindException pde)
	{
	    throw new CourseSearchException(pde);
	}
    }

    /**
     * find course record by primary key courseID
     * @param courseId primary key
     * @return CourseModel
     */
    public CourseModel getCourse(String courseId)
    {
	CourseModel cm=new CourseModel();
	try
	{
	    CourseEBHome home= EJBUtil.getCourseEBHome();
	    cm.setCourseID(new Integer(courseId));
	    CourseEB remote=home.findByPrimaryKey(new Integer(courseId));
	    cm=remote.getDetails();
	} catch(Exception se)
	{
	    System.out.println("getCourse():" + se.getMessage());
	}
	return cm;
    }

    /**
     * Create an instance of CourseMgrDAO
     */
    private void getDAO()
    {
	try
	{
	    dao = new CourseMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while CourseMgrBean creating :" + e.getMessage();
	    Debug.println(str);
	    throw new EJBException(str);
	}
    }
}