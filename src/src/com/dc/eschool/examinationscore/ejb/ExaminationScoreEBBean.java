package com.dc.eschool.examinationscore.ejb;

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

import com.dc.eschool.examinationscore.dao.ExaminationScoreDAO;
import com.dc.eschool.examinationscore.model.ExaminationScoreModel;

import com.dc.eschool.examinationscore.exceptions.ExaminationScoreAppException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAOAppException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAODBUpdateException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAODupKeyException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAOFinderException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAOSysException;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.InterpretSQL;
/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * ExaminationScoreEBBean is a class which represents the implementation of
 * a personal preferences profile as an Entity Bean using bean managed
 * persistance.
 */
public class ExaminationScoreEBBean extends ExaminationScoreModel implements EntityBean
{
  private ExaminationScoreModel examinationScoreDetails;
  private EntityContext entityContext;
  private transient ExaminationScoreDAO dao;

/**
 * Default class constructor with no arguments.
 */
  public ExaminationScoreEBBean() throws DAOException
  {
  }

/**
 * Creates a examinationscore for a particular examinationscore.
 * @returns         a integer that is the examinationscoreid of the examinationscore
 * @throws          <code>EJBException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this answeritem
 * @throws          <code>SchoolResourceAppException</code> if an wrong/missing
 *                  field was specified by the user
 */
  public Integer ejbCreate(ExaminationScoreModel em) throws CreateException, RemoteException
	{
	this.copy(em);
	try
	{
		getDAO();
		copyToDAO();
		dao.create();
		copyFromDAO();
		return (this.examinationID);
	}catch(Exception e)
	{
		String str = "Exception while create ExaminationScoreEB: " + e.getMessage();
		Debug.println(str);
		throw new CreateException(str);
	}
	}

/**
 * A post create method for this EJB object.
 * @throws          <code>RemoteException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this examinationscore
 */
  public void ejbPostCreate(ExaminationScoreModel em) throws CreateException, RemoteException
  {
  }

/**
 * Loads a examinationscore preferences profile from persistent store into memory.
 * @throws  <code>EJBException</code> is thrown if any error occurred
 */
  public void ejbLoad() throws RemoteException
  {
	  try
	  {
		  getDAO();
		  copyToDAO();
		  dao.load();
		  copyFromDAO();
	  } catch (ExaminationScoreDAOFinderException se)
	  {
		  throw new EJBException (se.getMessage());
	  } catch (ExaminationScoreDAOSysException acs)
	  {
		  throw new EJBException(acs.getMessage());
	  }
  }

/**
 * Stores a examinationscore preferences profile from memory into persistent store.
 * @throws  <code>EJBException</code> is thrown if any error occurs
 */
  public void ejbStore() throws RemoteException
  {
	  try
	  {
		  getDAO();
		  copyToDAO();
		  dao.store();
		  copyFromDAO();
	  } catch (ExaminationScoreDAOAppException se)
	  {
		  throw new EJBException (se.getMessage());
	  } catch (ExaminationScoreDAOSysException acs)
	  {
		  throw new EJBException(acs.getMessage());
	  }
  }

/**
 * Removes a examinationscore preferences profile from persistent store.
 * @throws  <code>RemoveException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>EJBException</code> is thrown if an
 *          irrecoverable error occurred while removing the profile
 */
  public void ejbRemove() throws RemoveException, RemoteException
  {
	  try
	  {
		  getDAO();
		  copyToDAO();
		  dao.remove();
	  } catch (ExaminationScoreDAODBUpdateException se)
	  {
		  entityContext.setRollbackOnly();
		  throw new RemoveException (se.getMessage());
	  } catch (ExaminationScoreDAOSysException acs)
	  {
		  throw new EJBException(acs.getMessage());
	  }
  }


/**
 * Activates the examinationscore preference profile for this EJB object.
 */
  public void ejbActivate() throws RemoteException
  {
  }

/**
 * Passivates the examinationscore preference profile for this EJB object.
 */
  public void ejbPassivate() throws RemoteException
  {
	this.dao = null;
  }

/**
 * Sets the <code>EntityContext</code> for this examinationscore preference profile
 * EJB object.
 * @param ec    the <code>EntityContext</code> for tis profile EJB object
 */
  public void setEntityContext(EntityContext entityContext) throws RemoteException
  {
	this.entityContext = entityContext;
  }

/**
 * Unsets the <code>EntityContext</code> for this examinationscore preference
 * profile EJB object.
 */
  public void unsetEntityContext() throws RemoteException
  {
	entityContext = null;
  }

/**
 * Finds a examinationscore preferences profile from persistent store.
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
		  Integer examinationID = dao.findByPrimaryKey(primKey);
		  dao.load();
		  copyFromDAO();
		  return(examinationID);
	  } catch (ExaminationScoreDAOFinderException se)
	  {
		  throw new FinderException (se.getMessage());
	  } catch (ExaminationScoreDAOSysException acs)
	  {
		  throw new EJBException(acs.getMessage());
	  }
  }

/**
 * Finds a examinationscore preferences profile from persistent store.
 * @returns a integer which represents the primary key for this profile
 * @throws  <code>FinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>EJBException</code> is thrown if an
 *          irrecoverable error occurred while accessing the database
 */
  public Integer ejbFindByProjectStudent(Integer projectID, Integer student) throws FinderException
  {
	  try
	  {
		  getDAO();
		  Integer examinationID = dao.findByProjectStudent(projectID, student);
		  dao.load();
		  copyFromDAO();
		  return(examinationID);
	  } catch (ExaminationScoreDAOFinderException se)
	  {
		  throw new FinderException (se.getMessage());
	  } catch (ExaminationScoreDAOSysException acs)
	  {
		  throw new EJBException(acs.getMessage());
	  }
  }

  /* business methods */

/**
 * Returns  the examinationscore preferences model for this user.
 * @return  the <code>ExaminationScoreModel</code> examinationscore prefrences data
 *          for this user
 */
  public void changeExaminationScore(ExaminationScoreModel other)
  {
	  com.dc.eschool.util.Calendar calendar=com.dc.eschool.util.Calendar.getInstance();
	  this.projectID = other.getProjectID();
	  this.student = other.getStudent();
	  this.score = other.getScore();
	  this.testResultItemID = other.getTestResultItemID();
	  this.allow = other.getAllow();
	  this.createDate = other.getCreateDate();
	  this.createTime = other.getCreateTime();
	  this.createBy = other.getCreateBy();
	  this.lastModifyDate = calendar.getESchoolDateString();
	  this.lastModifyTime = calendar.getESchoolTimeString();
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
  private void getDAO()
  {
	  try
	  {
		  if(dao == null)
			dao = new ExaminationScoreDAO();
	  }catch(Exception e)
	  {
		  String str = "Exception while create ExaminationScoreEB: " + e.getMessage();
		  System.out.println(str) ;
	  }
  }
	 /**
	 * Set all the properties to ExaminationScoreDAO
	 */
	private void copyToDAO()
	{
	dao.setExaminationID(this.examinationID);
	dao.setProjectID(this.projectID);
		System.out.println("&2="+this.projectID);
		dao.setStudent(this.student);
		System.out.println("&2="+this.student);
		dao.setScore(this.score);
		System.out.println("&2="+this.score);
	dao.setAllow(this.allow);
	dao.setCreateDate(this.createDate);
	dao.setCreateTime(this.createTime);
	dao.setCreateBy(this.createBy);
	dao.setLastModifyDate(this.lastModifyDate);
	dao.setLastModifyTime(this.lastModifyTime);
	dao.setLastModifyBy(this.lastModifyBy);
		dao.setTestResultItemID(this.testResultItemID);
		System.out.println("&2="+this.testResultItemID);
	 }

	/**
	 * get all the properties from ExaminationScoreDAO
	 */
	private void copyFromDAO()
	{
	this.examinationID = dao.getExaminationID();
	this.projectID=dao.getProjectID();
		this.student=dao.getStudent();
		this.score=dao.getScore();
	this.allow=dao.getAllow();
	this.createDate=dao.getCreateDate();
	this.createTime=dao.getCreateTime();
	this.createBy=dao.getCreateBy();
	this.lastModifyDate=dao.getLastModifyDate();
	this.lastModifyTime=dao.getLastModifyTime();
	this.lastModifyBy=dao.getLastModifyBy();
	}
}