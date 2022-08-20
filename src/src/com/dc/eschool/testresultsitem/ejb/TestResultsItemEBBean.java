package com.dc.eschool.testresultsitem.ejb;

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

import com.dc.eschool.testresultsitem.dao.TestResultsItemDAO;
import com.dc.eschool.testresultsitem.model.TestResultsItemModel;

import com.dc.eschool.testresultsitem.exceptions.TestResultsItemAppException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAOAppException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAODBUpdateException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAODupKeyException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAOFinderException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAOSysException;

import com.dc.eschool.util.Debug;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * TestResultsItemEBBean is a class which represents the implementation of
 * a personal preferences profile as an Entity Bean using bean managed
 * persistance.
 */
public class TestResultsItemEBBean extends TestResultsItemModel implements EntityBean
{
  private TestResultsItemModel testResultsItemDetails;
  private EntityContext entityContext;
  private transient TestResultsItemDAO dao;

/**
 * Default class constructor with no arguments.
 */
  public TestResultsItemEBBean() throws DAOException
  {
  }

/**
 * Creates a testresultsitem for a particular testresultsitem.
 * @returns         a integer that is the testResultItemID of the testresultsitem
 * @throws          <code>EJBException</code> if an irrecoverable error
 *                  occurred while creating the personal perference
 * @throws          <code>CreateException</code> is thrown if there a
 *                  recoverable error happened while creating the profile
 * @throws          <code>DuplicateKeyException</code> is thrown if the
 *                  profile already exists for this answeritem
 * @throws          <code>TestResultsItemAppException</code> if an wrong/missing
 *                  field was specified by the user
 */
/**
  public Integer ejbCreate(Integer testResultItemID,Integer student,String right,String answerMark, Integer examinationID,
						   String answer,Integer answerItemID,String allow,String createDate, String createTime,
						   Integer createBy, String lastModifyDate, String lastModifyTime,
						   Integer lastModifyBy) throws DuplicateKeyException,TestResultsItemAppException,CreateException, RemoteException
  {

	this.testResultsItemDetails= new TestResultsItemModel(testResultItemID, student, right,
					answerMark, examinationID, answer, answerItemID,allow, createDate, createTime, createBy, lastModifyDate, lastModifyTime, lastModifyBy);
	try
	{
	  dao.create(this.testResultsItemDetails);
	  return (testResultItemID);
	}
	catch(TestResultsItemDAODBUpdateException se)
	{
	  entityContext.setRollbackOnly();
	  throw new CreateException (se.getMessage());
	}
	catch (TestResultsItemDAODupKeyException acd)
	{
	  throw new DuplicateKeyException(acd.getMessage());
	}
	catch (TestResultsItemDAOAppException aca)
	{
	  throw new TestResultsItemAppException(aca.getMessage());
	}
	catch (TestResultsItemDAOSysException acs)
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
/**
  public void ejbPostCreate(Integer testResultItemID,Integer student,String right,String answerMark, Integer examinationID,
							String answer,Integer answerItemID,String allow,String createDate, String createTime,
							Integer createBy, String lastModifyDate, String lastModifyTime,
							Integer lastModifyBy) throws DuplicateKeyException,CreateException, RemoteException
  {
  }
*/

 /**
  * the ejbCreate methods
  */
   public Integer ejbCreate(TestResultsItemModel tm) throws CreateException, RemoteException
   {
	this.copy(tm);
	try
	{
		getDAO();
		copyToDAO();
		dao.create();
		copyFromDAO();
		return (this.testResultItemID);
	}catch(Exception e)
	{
		String str = "Exception while create TestResultsItemEB: " + e.getMessage();
		Debug.println(str);
		throw new CreateException(str);
	}
	}
 /**
  * the ejbPostCreate methods do nothing
  */
  public void ejbPostCreate(TestResultsItemModel tm) throws CreateException, RemoteException
  {
  }

/**
 * Loads a testresultsitem preferences profile from persistent store into memory.
 * @throws  <code>EJBException</code> is thrown if any error occurred
 */
/**
  public void ejbLoad() throws RemoteException
  {
	try
	{
	  this.testResultsItemDetails = dao.load((Integer)entityContext.getPrimaryKey());
	}
	catch (TestResultsItemDAOFinderException se)
	{
	  throw new EJBException (se.getMessage());
	}
	catch (TestResultsItemDAOSysException acs)
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
	} catch (TestResultsItemDAOFinderException se)
	{
		throw new EJBException (se.getMessage());
	} catch (TestResultsItemDAOSysException acs)
	{
		throw new EJBException(acs.getMessage());
	}
	}
/**
 * Stores a testresultsitem preferences profile from memory into persistent store.
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
	} catch (TestResultsItemDAOAppException se)
	{
		throw new EJBException (se.getMessage());
	} catch (TestResultsItemDAOSysException acs)
	{
		throw new EJBException(acs.getMessage());
	}
	}

/**
 * Removes a testresultsitem preferences profile from persistent store.
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
	}
		catch (TestResultsItemDAODBUpdateException se)
	{
		entityContext.setRollbackOnly();
		throw new RemoveException (se.getMessage());
	}
		catch (TestResultsItemDAOSysException acs)
	{
		throw new EJBException(acs.getMessage());
	}
	}

/**
 * Activates the testresultsitem preference profile for this EJB object.
 */
  public void ejbActivate() throws RemoteException
  {
  }

/**
 * Passivates the testresultsitem preference profile for this EJB object.
 */
  public void ejbPassivate() throws RemoteException
  {
	this.dao = null;
  }

/**
 * Sets the <code>EntityContext</code> for this testresultsitem preference profile
 * EJB object.
 * @param ec    the <code>EntityContext</code> for tis profile EJB object
 */
  public void setEntityContext(EntityContext entityContext) throws RemoteException
  {
	this.entityContext = entityContext;
  }

/**
 * Unsets the <code>EntityContext</code> for this testresultsitem preference
 * profile EJB object.
 */
  public void unsetEntityContext() throws RemoteException
  {
	entityContext = null;
  }

/**
 * Finds a testresultsitem preferences profile from persistent store.
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
		Integer testResultItemID = dao.findByPrimaryKey(primKey);
		dao.load();
		copyFromDAO();
		return(testResultItemID);
	} catch (TestResultsItemDAOFinderException se)
	{
		throw new FinderException (se.getMessage());
	} catch (TestResultsItemDAOSysException acs)
	{
		throw new EJBException(acs.getMessage());
	}
	}

   public Integer ejbFindByAnswerItemIDContentIDStudent(String answerItemID, String contentID, String student) throws FinderException
	{
		try
		{
			getDAO();
			dao.findByAnswerItemIDContentIDStudent(answerItemID, contentID, student);
			dao.load();
			copyFromDAO();
			return(dao.getTestResultItemID());
		}
		catch (TestResultsItemDAOFinderException se)
		{
			throw new FinderException (se.getMessage());
		}
		catch (TestResultsItemDAOSysException acs)
		{
			throw new EJBException(acs.getMessage());
		}
	}

  /* business methods */

/**
 * Returns  the testresultsitem preferences model for this user.
 * @return  the <code>TestResultsItemModel</code> testresultsitem prefrences data
 *          for this user
 */
  public void changeTestResultsItem(TestResultsItemModel other)
	{
	com.dc.eschool.util.Calendar calendar=com.dc.eschool.util.Calendar.getInstance();
	this.student = other.getStudent();

		this.right = other.getRight();

		this.answerMark = other.getAnswerMark();

		this.answer = other.getAnswer();

		this.answerItemID = other.getAnswerItemID();

		this.allow = other.getAllow();

		this.contentID = other.getContentID();

		this.answerNumber = other.getAnswerNumber();

	this.lastModifyDate = calendar.getESchoolDateString();

	this.lastModifyTime = calendar.getESchoolTimeString();

	this.lastModifyBy = other.getLastModifyBy();

	try
	{
		getDAO();
		copyToDAO();
		dao.store();
		dao.load();
		copyFromDAO();
	}catch(Exception se)
	{
		System.out.println(se);
	}
	}
   /**
   * create instance of TestResultsItemDAO
   */
	private void getDAO()
	{
	  try
	  {
		if(dao == null)
		{
				dao = new TestResultsItemDAO();
			}
	  }catch(Exception e)
	  {
		String str = "Exception while create TestResultsItemEB(getDao): " + e.getMessage();
		System.out.println(str) ;
	  }
	}
	/**
	 * Set all the properties to TestResultsItemDAO
	 */
	private void copyToDAO()
	{
	dao.setTestResultItemID(this.testResultItemID);
	dao.setStudent(this.student) ;
		dao.setAnswer(this.answer);
		dao.setAnswerItemID(this.answerItemID);
		dao.setAnswerMark(this.answerMark);
		dao.setRight(this.right);
		dao.setAllow(this.allow);
	dao.setCreateDate(this.createDate);
	dao.setCreateTime(this.createTime);
	dao.setCreateBy(this.createBy);
	dao.setLastModifyDate(this.lastModifyDate);
	dao.setLastModifyTime(this.lastModifyTime);
	dao.setLastModifyBy(this.lastModifyBy);
		dao.setContentID(this.contentID);
		dao.setAnswerNumber(this.answerNumber);
	}

	/**
	 * get all the properties from TestResultsItemDAO
	 */
	private void copyFromDAO()
	{
	this.testResultItemID = dao.getTestResultItemID();
	this.student=dao.getStudent();
		this.answer=dao.getAnswer();
		this.answerItemID=dao.getAnswerItemID();
		this.answerMark=dao.getAnswerMark();
		this.right=dao.getRight();
	this.allow=dao.getAllow();
	this.createDate=dao.getCreateDate();
	this.createTime=dao.getCreateTime();
	this.createBy=dao.getCreateBy();
	this.lastModifyDate=dao.getLastModifyDate();
	this.lastModifyTime=dao.getLastModifyTime();
	this.lastModifyBy=dao.getLastModifyBy();
		this.contentID=dao.getContentID();
		this.answerNumber=dao.getAnswerNumber();
	}

}