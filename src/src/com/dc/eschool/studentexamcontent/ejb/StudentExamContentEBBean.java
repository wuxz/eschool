package com.dc.eschool.studentexamcontent.ejb;

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

import com.dc.eschool.studentexamcontent.dao.StudentExamContentDAO;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentAppException;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAOAppException;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAODBUpException;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAODuKeyException;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAOFindException;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAOSysException;
import com.dc.eschool.studentexamcontent.model.StudentExamContentModel;

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
public class StudentExamContentEBBean extends StudentExamContentModel implements EntityBean
{
    private EntityContext entityContext;
    private transient StudentExamContentDAO dao;

    /**
    * the ejbCreate methods
    */
    public Integer ejbCreate(StudentExamContentModel secm) throws CreateException, RemoteException
    {
	try
	{
	    getDAO();
	    dao.setSecm(secm);
	    dao.create();
	    this.copy(dao.getSecm());
	    return (this.studentExamContentID);
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
    public void ejbPostCreate(StudentExamContentModel secm) throws CreateException, RemoteException
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
	    dao.getSecm().setStudentExamContentID(this.studentExamContentID);
	    dao.load();
	    this.copy(dao.getSecm());
	} catch (StudentExamContentDAOFindException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (StudentExamContentDAOSysException acs)
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
	    dao.getSecm().copy(this);
	    dao.store();
	    dao.getSecm();
	} catch (StudentExamContentDAOAppException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (StudentExamContentDAOSysException acs)
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
	    dao.getSecm().setStudentExamContentID(this.studentExamContentID);
	    dao.remove();
	} catch (StudentExamContentDAODBUpException se)
	{
	    entityContext.setRollbackOnly();
	    throw new RemoveException (se.getMessage());
	} catch (StudentExamContentDAOSysException acs)
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
	    this.studentExamContentID = dao.findByPrimaryKey(primKey);
	    dao.getSecm();
	    return(this.studentExamContentID);
	} catch (StudentExamContentDAOFindException se)
	{
	    throw new FinderException (se.getMessage());
	} catch (StudentExamContentDAOSysException acs)
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
	      dao = new StudentExamContentDAO();
	}catch(Exception e)
	{
	    String str = "Exception while create ProjectEB: " + e.getMessage();
	    System.out.println(str) ;
	}
    }

    public StudentExamContentModel getDetails()
    {
	getDAO();
	return dao.getSecm();
    }
}