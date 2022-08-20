package com.dc.eschool.course.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import javax.naming.InitialContext;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.course.dao.CourseDAO;
import com.dc.eschool.course.model.CourseModel;

import com.dc.eschool.course.exceptions.CourseAppException;
import com.dc.eschool.course.exceptions.CourseDAOAppException;
import com.dc.eschool.course.exceptions.CourseDAODBUpException;
import com.dc.eschool.course.exceptions.CourseDAODuKeyException;
import com.dc.eschool.course.exceptions.CourseDAOFindException;
import com.dc.eschool.course.exceptions.CourseDAOSysException;

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
public class CourseEBBean extends CourseModel implements EntityBean
{
    private EntityContext entityContext;
    private transient CourseDAO dao;

    /**
    * the ejbCreate methods
    */
    public Integer ejbCreate(CourseModel cm) throws CreateException, RemoteException
    {
        try
        {
            getDAO();
            dao.setCm(cm);
            dao.create();
            this.copy(dao.getCm());
            return (this.courseID);
        }catch(Exception e)
        {
            String str = "Exception while create CourseEB: " + e.getMessage();
            Debug.println(str);
            throw new CreateException(str);
        }
    }

    /**
    * the ejbPostCreate methods do nothing
    */
    public void ejbPostCreate(CourseModel cm) throws CreateException, RemoteException
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
            dao.getCm().setCourseID(this.courseID);
            dao.load();
            this.copy(dao.getCm());
        } catch (CourseDAOFindException se)
        {
            throw new EJBException (se.getMessage());
        } catch (CourseDAOSysException acs)
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
            dao.getCm().copy(this);
            dao.store();
            dao.getCm();
        } catch (CourseDAOAppException se)
        {
            throw new EJBException (se.getMessage());
        } catch (CourseDAOSysException acs)
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
	    dao.getCm().setCourseID(this.courseID);
	    dao.remove();
	} catch (CourseDAODBUpException se)
	{
	    entityContext.setRollbackOnly();
	    throw new RemoveException (se.getMessage());
	} catch (CourseDAOSysException acs)
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
	    this.courseID = dao.findByPrimaryKey(primKey);
	    dao.getCm();
	    return(this.courseID);
	} catch (CourseDAOFindException se)
	{
	    throw new FinderException (se.getMessage());
	} catch (CourseDAOSysException acs)
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
	        dao = new CourseDAO();
	    }
	}catch(Exception e)
	{
	    String str = "Exception while create CourseEB: " + e.getMessage();
	    System.out.println(str) ;
	}
    }

    public CourseModel getDetails()
    {
	getDAO();
	return dao.getCm();
    }
}