package com.dc.eschool.coursestudent.ejb;

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

import com.dc.eschool.coursestudent.dao.CourseStudentDAO;
import com.dc.eschool.coursestudent.model.CourseStudentModel;

import com.dc.eschool.coursestudent.exceptions.CSAppException;
import com.dc.eschool.coursestudent.exceptions.CSDAOAppException;
import com.dc.eschool.coursestudent.exceptions.CSDAODBUpException;
import com.dc.eschool.coursestudent.exceptions.CSDAODuKeyException;
import com.dc.eschool.coursestudent.exceptions.CSDAOFindException;
import com.dc.eschool.coursestudent.exceptions.CSDAOSysException;

import com.dc.eschool.util.Debug;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * Implementation of class as an Entity Bean
 */
public class CourseStudentEBBean extends CourseStudentModel implements EntityBean
{
    private EntityContext entityContext;
    private transient CourseStudentDAO dao;

    /**
    * the ejbCreate methods
    */
    public Integer ejbCreate(CourseStudentModel cm) throws CreateException, RemoteException
    {
        try
        {
            getDAO();
            dao.setCsm(cm);
            dao.create();
	    this.copy(dao.getCsm());
            return (this.courseStudentID);
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
    public void ejbPostCreate(CourseStudentModel cm) throws CreateException, RemoteException
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
            dao.getCsm().setCourseStudentID(this.courseStudentID);
            dao.load();
            this.copy(dao.getCsm());
        } catch (CSDAOFindException se)
        {
            throw new EJBException (se.getMessage());
        } catch (CSDAOSysException acs)
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
            dao.getCsm().copy(this);
            dao.store();
            dao.getCsm();
        } catch (CSDAOAppException se)
        {
            throw new EJBException (se.getMessage());
        } catch (CSDAOSysException acs)
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
	    dao.getCsm().setCourseStudentID(this.courseStudentID);
            dao.remove();
        } catch (CSDAODBUpException se)
        {
            entityContext.setRollbackOnly();
            throw new RemoveException (se.getMessage());
        } catch (CSDAOSysException acs)
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
	    this.courseStudentID = dao.findByPrimaryKey(primKey);
	    dao.getCsm();
	    return(this.courseStudentID);
        } catch (CSDAOFindException se)
        {
            throw new FinderException (se.getMessage());
        } catch (CSDAOSysException acs)
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
                dao = new CourseStudentDAO();
        }catch(Exception e)
        {
            String str = "Exception while create ProjectEB: " + e.getMessage();
            System.out.println(str) ;
        }
    }

}