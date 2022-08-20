package com.dc.eschool.schoolresource.ejb;

import java.rmi.RemoteException;

import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.ejb.DuplicateKeyException;
import javax.naming.NamingException;
import javax.naming.InitialContext;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.schoolresource.dao.SchoolResourceDAO;
import com.dc.eschool.schoolresource.model.SchoolResourceModel;

import com.dc.eschool.schoolresource.exceptions.SchoolResourceAppException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAOAppException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAODBUpdateException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAODupKeyException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAOFinderException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAOSysException;

import com.dc.eschool.util.*;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * SchoolResourceEBBean is a class which represents the implementation of
 * a personal preferences profile as an Entity Bean using bean managed
 * persistance.
 */
public class SchoolResourceEBBean extends SchoolResourceModel implements EntityBean
{
  private SchoolResourceModel schoolResourceDetails;
  private EntityContext entityContext;
  private transient SchoolResourceDAO dao;
/**
 * Default class constructor with no arguments.
 */
  public SchoolResourceEBBean() throws DAOException
  {
  }

/**
 * Creates a schoolresource for a particular schoolresource.
 * @returns         a integer that is the schoolresourceid of the schoolresource
 * @throws          <code>EJBException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this answeritem
 * @throws          <code>SchoolResourceAppException</code> if an wrong/missing
 *                  field was specified by the user
 */
  public Integer ejbCreate(SchoolResourceModel other) throws DuplicateKeyException,SchoolResourceAppException,CreateException, RemoteException
  {
    System.out.println("in SchoolResourceEBBean");
    this.copy(other);
      try
      {
	  System.out.println("this SchoolResourceEBBean");
          getDAO();
	  copyToDAO();
	  dao.create();
	  copyFromDAO();
	  return (this.schoolResourceID);
      }catch(Exception e)
      {
	  String str = "Exception while create SchoolResourceEB(ejbcreate): " + e.getMessage();
	  Debug.println(str);
	  throw new CreateException(str);
      }
  }
  public void ejbPostCreate(SchoolResourceModel other) throws DuplicateKeyException,CreateException, RemoteException
  {
  }
/*
  public Integer ejbCreate(Integer schoolResourceID,String name,String docURL, Integer subjectID,
                           String startDate,String endDate,String explain,String allow,String createDate, String createTime,
                           Integer createBy, String lastModifyDate, String lastModifyTime,
                           Integer lastModifyBy) throws DuplicateKeyException,SchoolResourceAppException,CreateException, RemoteException
 {

    this.schoolResourceDetails= new SchoolResourceModel(schoolResourceID, name, docURL,subjectID, startDate, endDate, explain,allow, createDate, createTime, createBy, lastModifyDate, lastModifyTime, lastModifyBy);
    try
    {
      dao.create(this.schoolResourceDetails);
      return (schoolResourceID);
    }
    catch(SchoolResourceDAODBUpdateException se)
    {
      entityContext.setRollbackOnly();
      throw new CreateException (se.getMessage());
    }
    catch (SchoolResourceDAODupKeyException acd)
    {
      throw new DuplicateKeyException(acd.getMessage());
    }
    catch (SchoolResourceDAOAppException aca)
    {
      throw new SchoolResourceAppException(aca.getMessage());
    }
    catch (SchoolResourceDAOSysException acs)
    {
      throw new EJBException(acs.getMessage());
    }
  }
*/


/**
 * A post create method for this EJB object.
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this answeritem
 */
/*
  public void ejbPostCreate(Integer schoolResourseID,String name,String docURL, Integer subjectID,
                            String startDate,String endDate,String explain,String allow,String createDate, String createTime,
                            Integer createBy, String lastModifyDate, String lastModifyTime,
                            Integer lastModifyBy) throws DuplicateKeyException,CreateException, RemoteException
 {
 }
*/
/**
 * Loads a schoolresource preferences profile from persistent store into memory.
 * @throws  <code>EJBException</code> is thrown if any error occurred
 */
/*
  public void ejbLoad() throws RemoteException
  {
    try
    {
      this.schoolResourceDetails = dao.load((Integer)entityContext.getPrimaryKey());
    }
    catch (SchoolResourceDAOFinderException se)
    {
      throw new EJBException (se.getMessage());
    }
    catch (SchoolResourceDAOSysException acs)
    {
      throw new EJBException(acs.getMessage());
    }
  }
*/
  public void ejbLoad() throws RemoteException
    {
      try
      {
	  getDAO();
	  copyToDAO();
	  dao.load();
	  copyFromDAO();
      } catch (SchoolResourceDAOFinderException se)
      {
	  throw new EJBException (se.getMessage());
      } catch (SchoolResourceDAOSysException acs)
      {
	  throw new EJBException(acs.getMessage());
      }
    }

/**
 * Stores a schoolresource preferences profile from memory into persistent store.
 * @throws  <code>EJBException</code> is thrown if any error occurs
 */
/*
  public void ejbStore() throws RemoteException
  {
    try
    {
      dao.store(this.schoolResourceDetails);
    }
    catch (SchoolResourceDAOAppException se)
    {
      throw new EJBException (se.getMessage());
    }
    catch (SchoolResourceDAOSysException acs)
    {
      throw new EJBException(acs.getMessage());
    }
  }
*/
  public void ejbStore() throws RemoteException
    {
	try
	{
	    getDAO();
	    copyToDAO();
	    dao.store();
	    copyFromDAO();
	} catch (SchoolResourceDAOAppException se)
	{
	    throw new EJBException (se.getMessage());
	} catch (SchoolResourceDAOSysException acs)
	{
	    throw new EJBException(acs.getMessage());
	}
    }

/**
 * Removes a schoolresource preferences profile from persistent store.
 * @throws  <code>RemoveException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>EJBException</code> is thrown if an
 *          irrecoverable error occurred while removing the profile
 */
/*
  public void ejbRemove() throws RemoveException, RemoteException
  {
    try
    {
      dao.remove((Integer)entityContext.getPrimaryKey());
    }
    catch (SchoolResourceDAODBUpdateException se)
    {
      entityContext.setRollbackOnly();
      throw new RemoveException (se.getMessage());
    }
    catch (SchoolResourceDAOSysException acs)
    {
      throw new EJBException(acs.getMessage());
    }
  }
*/
  public void ejbRemove() throws RemoveException, RemoteException
    {
	try
	{
	    getDAO();
	    copyToDAO();
	    dao.remove();
	} catch (SchoolResourceDAODBUpdateException se) {
	    entityContext.setRollbackOnly();
	    throw new RemoveException (se.getMessage());
	} catch (SchoolResourceDAOSysException acs) {
	    throw new EJBException(acs.getMessage());
	}
    }
/**
 * Activates the schoolresource preference profile for this EJB object.
 */
  public void ejbActivate() throws RemoteException
  {
  }
/**
 * Passivates the schoolresource preference profile for this EJB object.
 */
  public void ejbPassivate() throws RemoteException
  {
    this.dao = null;
  }
/**
 * Sets the <code>EntityContext</code> for this schoolresource preference profile
 * EJB object.
 * @param ec    the <code>EntityContext</code> for tis profile EJB object
 */
  public void setEntityContext(EntityContext entityContext) throws RemoteException
  {
    this.entityContext = entityContext;
  }
/**
 * Unsets the <code>EntityContext</code> for this schoolresource preference
 * profile EJB object.
 */
  public void unsetEntityContext() throws RemoteException
  {
    entityContext = null;
  }
/**
 * Finds a schoolresource preferences profile from persistent store.
 * @returns a integer which represents the primary key for this profile
 * @throws  <code>FinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>EJBException</code> is thrown if an
 *          irrecoverable error occurred while accessing the database
 */
  public Integer ejbFindByPrimaryKey(Integer primKey) throws FinderException
  {
    try
    {
      getDAO();
      Integer schoolResourceID = dao.findByPrimaryKey(primKey);
      dao.load();
      copyFromDAO();
      //this.schoolResourceDetails = dao.load(schoolResourceID);
      return(schoolResourceID);
    }
    catch (SchoolResourceDAOFinderException se)
    {
      throw new FinderException (se.getMessage());
    }
    catch (SchoolResourceDAOSysException acs)
    {
      throw new EJBException(acs.getMessage());
    }
  }

  public void changeSchoolResource(SchoolResourceModel other)
  {
    com.dc.eschool.util.Calendar calendar=com.dc.eschool.util.Calendar.getInstance();
    this.name = other.getName();
    this.docURL = other.getDocURL();
    this.subjectID = other.getSubjectID();
    this.startDate = other.getStartDate();
    this.endDate = other.getEndDate();
    this.explain = other.getExplain();
    this.allow = other.getAllow();
    this.createDate = other.getCreateDate();
    this.createTime = other.getCreateTime();
    this.createBy = other.getCreateBy();
    this.lastModifyDate = other.getLastModifyDate();
    this.lastModifyTime = other.getLastModifyTime();
    this.lastModifyBy = other.getLastModifyBy();
	try
	{
	    getDAO();
	    copyToDAO();
	    dao.store();
	    copyFromDAO();
	}catch(Exception se)
	{
	    System.out.println(se);
	}
    }

  /* business methods */

  /**
   * create instance of SchoolResourceDAO
   */
    private void getDAO()
    {
	  try
	  {
	    if(dao == null)
	    {
               System.out.println("in");
                dao = new SchoolResourceDAO();
            }
	  }catch(Exception e)
	  {
	    String str = "Exception while create SchoolResourceEB(getDao): " + e.getMessage();
	    System.out.println(str) ;
	  }
    }

    /**
     * Set all the properties to SchoolResourceDAO
     */
    private void copyToDAO()
    {
		dao.setSchoolResourceID(this.schoolResourceID);
		dao.setName(this.name) ;
		dao.setDocURL(this.docURL);
		dao.setSubjectID(this.subjectID);
		dao.setStartDate(this.startDate);
		dao.setEndDate(this.endDate);
		dao.setExplain(this.explain);
		dao.setAllow(this.allow);
		dao.setCreateDate(this.createDate);
		dao.setCreateTime(this.createTime);
		dao.setCreateBy(this.createBy);
		dao.setLastModifyDate(this.lastModifyDate);
		dao.setLastModifyTime(this.lastModifyTime);
		dao.setLastModifyBy(this.lastModifyBy);
    }

    /**
     * get all the properties from SchoolResourceDAO
     */
    private void copyFromDAO()
    {
		this.schoolResourceID = dao.getSchoolResourceID();
		this.name=dao.getName();
		this.docURL=dao.getDocURL();
		this.subjectID=dao.getSubjectID();
		this.startDate=dao.getStartDate();
		this.endDate=dao.getEndDate();
		this.explain=dao.getExplain();
		this.allow=dao.getAllow();
		this.createDate=dao.getCreateDate();
		this.createTime=dao.getCreateTime();
		this.createBy=dao.getCreateBy();
		this.lastModifyDate=dao.getLastModifyDate();
		this.lastModifyTime=dao.getLastModifyTime();
		this.lastModifyBy=dao.getLastModifyBy();
    }
}