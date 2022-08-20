package com.dc.eschool.scorestatistic.ejb;

import java.rmi.RemoteException;

import javax.ejb.*;
import javax.naming.NamingException;
import javax.naming.InitialContext;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.scorestatistic.dao.ScoreStatisticDAO;
import com.dc.eschool.scorestatistic.model.ScoreStatisticModel;

import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticAppException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAOAppException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAODBUpdateException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAODupKeyException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAOFinderException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAOSysException;

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
 * ScoreStatisticEBBean is a class which represents the implementation of
 * a personal preferences profile as an Entity Bean using bean managed
 * persistance.
 */
public class ScoreStatisticEBBean extends ScoreStatisticModel implements EntityBean
{
  private ScoreStatisticModel scoreStatisticDetails;
  private EntityContext entityContext;
  private transient ScoreStatisticDAO dao;

/**
 * Default class constructor with no arguments.
 */
  public ScoreStatisticEBBean() throws DAOException
  {
  }

/**
 * Creates a scoreStatistic  for a particular scoreStatistic.
 * @returns         a integer that is the scoreStatisticID of the scoreStatistic
 * @throws          <code>EJBException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this answeritem
 * @throws          <code>ScoreStatisticAppException</code> if an wrong/missing
 *                  field was specified by the user
 */
  public Integer ejbCreate(ScoreStatisticModel sm) throws CreateException, RemoteException
  {
	  this.copy(sm);
	  try
	  {
		  getDAO();
		  copyToDAO();
		  dao.create();
		  copyFromDAO();
		  return (this.scoreStatisticID);
	  }catch(Exception e)
	  {
		  String str = "Exception while create ScoreStatisticEB: " + e.getMessage();
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
 *                  profile already exists for this answeritem
 */
  public void ejbPostCreate(ScoreStatisticModel sm) throws CreateException, RemoteException
  {
  }

/**
 * Loads a scoreStatistic preferences profile from persistent store into memory.
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
	} catch (ScoreStatisticDAOFinderException se)
	{
		throw new EJBException (se.getMessage());
	} catch (ScoreStatisticDAOSysException acs)
	{
		throw new EJBException(acs.getMessage());
	}
	}

/**
 * Stores a scoreStatistic preferences profile from memory into persistent store.
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
	  } catch (ScoreStatisticDAOAppException se)
	  {
		  throw new EJBException (se.getMessage());
	  } catch (ScoreStatisticDAOSysException acs)
	  {
		  throw new EJBException(acs.getMessage());
	  }
  }

/**
 * Removes a scoreStatistic preferences profile from persistent store.
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
	} catch (ScoreStatisticDAODBUpdateException se)
	{
		entityContext.setRollbackOnly();
		throw new RemoveException (se.getMessage());
	} catch (ScoreStatisticDAOSysException acs)
	{
		throw new EJBException(acs.getMessage());
	}
	}

/**
 * Activates the scoreStatistic preference profile for this EJB object.
 */
  public void ejbActivate() throws RemoteException
  {
  }

/**
 * Passivates the scoreStatistic preference profile for this EJB object.
 */
  public void ejbPassivate() throws RemoteException
  {
	this.dao = null;
  }

/**
 * Sets the <code>EntityContext</code> for this scoreStatistic preference profile
 * EJB object.
 * @param ec    the <code>EntityContext</code> for tis profile EJB object
 */
  public void setEntityContext(EntityContext entityContext) throws RemoteException
  {
	this.entityContext = entityContext;
  }

/**
 * Unsets the <code>EntityContext</code> for this scoreStatistic preference
 * profile EJB object.
 */
  public void unsetEntityContext() throws RemoteException
  {
	entityContext = null;
  }

/**
 * Finds a scoreStatistic preferences profile from persistent store.
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
		  Integer scoreStatistic = dao.findByPrimaryKey(primKey);
		  dao.load();
		  copyFromDAO();
		  return(scoreStatisticID);
	  } catch (ScoreStatisticDAOFinderException se)
	  {
		  throw new FinderException (se.getMessage());
	  } catch (ScoreStatisticDAOSysException acs)
	  {
		  throw new EJBException(acs.getMessage());
	  }
  }

  public Integer ejbFindByAnswerItemID(String  answerItemID) throws RemoteException,FinderException
  {
	  try
	  {
		  getDAO();
		  answerItemID = dao.findByAnswerItemID(answerItemID);
		  if(!answerItemID.equals(""))
		  {
			dao.load();
			copyFromDAO();
			return(scoreStatisticID);
		   }
		   else
		   {
			throw new ObjectNotFoundException();
		   }
	  } catch (ScoreStatisticDAOFinderException se)
	  {
		  throw new FinderException (se.getMessage());
	  } catch (ScoreStatisticDAOSysException acs)
	  {
		  throw new EJBException(acs.getMessage());
	  }
  }
 /* business methods */

/**
 * Returns  the scoreStatistic preferences model for this user.
 * @return  the <code>ScoreStatisticModel</code> scoreStatistic prefrences data
 *          for this user
 */
	  public void changeScoreStatistic(ScoreStatisticModel other)
	{
	com.dc.eschool.util.Calendar calendar=com.dc.eschool.util.Calendar.getInstance();
		this.student = other.getStudent();
		this.answerItemID = other.getAnswerItemID();
		this.projectContentID = other.getProjectContentID();
		this.statistic = other.getStatistic();
		this.rightAnswer = other.getRightAnswer();
		this.wrongAnswer = other.getWrongAnswer();
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

	//inner methods

	/**
	 * create instance of UsersDAO
	 */
	private void getDAO()
	{
	try
	{
		if(dao == null)
		  dao = new ScoreStatisticDAO();
	}catch(Exception e)
	{
		String str = "Exception while create ScoreStatisticEB: " + e.getMessage();
		System.out.println(str) ;
	}
	}

	/**
	 * Set all the properties to UsersDAO
	 */
	private void copyToDAO()
	{
	dao.setScoreStatisticID(this.scoreStatisticID);
	dao.setStudent(this.student) ;
		dao.setAnswerItemID(this.answerItemID) ;
		dao.setProjectContentID(this.projectContentID) ;
		dao.setStatistic(this.statistic) ;
		dao.setRightAnswer(this.rightAnswer) ;
		dao.setWrongAnswer(this.wrongAnswer) ;
		dao.setAllow(this.allow);
	dao.setCreateDate(this.createDate);
	dao.setCreateTime(this.createTime);
	dao.setCreateBy(this.createBy);
	dao.setLastModifyDate(this.lastModifyDate);
	dao.setLastModifyTime(this.lastModifyTime);
	dao.setLastModifyBy(this.lastModifyBy);
	}

	/**
	 * get all the properties from UsersDAO
	 */
	private void copyFromDAO()
	{
	this.scoreStatisticID = dao.getScoreStatisticID();
	this.student=dao.getStudent();
		this.answerItemID=dao.getAnswerItemID();
		this.projectContentID=dao.getProjectContentID();
		this.statistic=dao.getStatistic();
		this.rightAnswer=dao.getRightAnswer();
		this.wrongAnswer=dao.getWrongAnswer();

		this.allow=dao.getAllow();
	this.createDate=dao.getCreateDate();
	this.createTime=dao.getCreateTime();
	this.createBy=dao.getCreateBy();
	this.lastModifyDate=dao.getLastModifyDate();
	this.lastModifyTime=dao.getLastModifyTime();
	this.lastModifyBy=dao.getLastModifyBy();
	}
}