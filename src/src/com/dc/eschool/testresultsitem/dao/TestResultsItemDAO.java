package com.dc.eschool.testresultsitem.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

import com.dc.eschool.testresultsitem.model.TestResultsItemModel;
import com.dc.eschool.controller.event.TestResultItemEvent;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAOSysException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAOAppException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAODBUpdateException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAOFinderException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAODupKeyException;


import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.Calendar;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * This class implements TestResultsItemDAO for Oracle, Sybase and cloudscape databases
 * This class encapsulates all the JDBC calls made by the TestResultsItemEJB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of TestResultsItemEJB is
 * implemented here.
 */

public class TestResultsItemDAO extends GeneralDAO
{
  protected Integer testResultItemID;
  private Integer student;
  private String  right;
  private String  answerMark;
  private String  answer;
  private Integer answerItemID;
  private String  allow;
  private String  createDate;
  private String  createTime;
  private Integer createBy;
  private String  lastModifyDate;
  private String  lastModifyTime;
  private Integer lastModifyBy;
  private Integer contentID;
  private String  answerNumber;


/**
 * TestResultsItem constructor comment.
 */
  public TestResultsItemDAO() throws DAOException
  {
	  super();
  }
  /* get and set methods for the instance variables */
  public Integer getTestResultItemID()
  {
	return testResultItemID;
  }
  public Integer getStudent()
  {
	return student;
  }
  public String getRight()
  {
	return right;
  }
  public String getAnswerMark()
  {
	return answerMark;
  }
  public String getAnswer()
  {
	return answer;
  }
  public Integer getAnswerItemID()
  {
	return answerItemID;
  }
  public Integer getContentID()
  {
	return contentID;
  }
  public String getAnswerNumber()
  {
	return answerNumber;
  }
  public String getAllow()
  {
	return allow;
  }
  public String getCreateDate()
  {
	return createDate;
  }
  public String getCreateTime()
  {
	return createTime;
  }
  public Integer getCreateBy()
  {
	return createBy;
  }
  public String getLastModifyDate()
  {
	return lastModifyDate;
  }
  public String getLastModifyTime()
  {
	return lastModifyTime;
  }
  public Integer getLastModifyBy()
  {
	return lastModifyBy;
  }
  public void setTestResultItemID(Integer testResultItemID)
  {
	this.testResultItemID = testResultItemID;
  }
  public void setStudent(Integer student)
  {
	this.student = student;
  }
  public void setRight(String right)
  {
	this.right = right;
  }
  public void setAnswerMark(String answerMark)
  {
	this.answerMark = answerMark;
  }
  public void setAnswer(String answer)
  {
	this.answer = answer;
  }
  public void setAnswerItemID(Integer answerItemID)
  {
	this.answerItemID = answerItemID;
  }
  public void setContentID(Integer contentID)
  {
	this.contentID = contentID;
  }
  public void setAnswerNumber(String answerNumber)
  {
	this.answerNumber = answerNumber;
  }
  public void setAllow(String allow)
  {
	this.allow = allow;
  }
  public void setCreateDate(String createDate)
  {
	this.createDate = createDate;
  }
  public void setCreateTime(String createTime)
  {
	this.createTime = createTime;
  }
  public void setCreateBy(Integer createBy)
  {
	this.createBy = createBy;
  }
  public void setLastModifyDate(String lastModifyDate)
  {
	this.lastModifyDate = lastModifyDate;
  }
  public void setLastModifyTime(String lastModifyTime)
  {
	this.lastModifyTime = lastModifyTime;
  }
  public void setLastModifyBy(Integer lastModifyBy)
  {
	this.lastModifyBy = lastModifyBy;
  }

/**
 * Creates a testresultsitem  the data in persistent store.
 * Mirrors the ejbCreate method.
 * @param detail  the <code>TestResultsItemModel</code>
 * @throws  <code>TestResultsItemDAOAppException</code> is thrown if the profile
 *          could not be persisted because of an user error in inputs.
 * @throws  <code>TestResultsItemDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>TestResultsItemDAODupKeyException</code> is thrown if a
 *          with the same userid exists in the database
 * @throws  <code>TestResultsItemDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while persisting the profile
 */
  public void create() throws TestResultsItemDAOSysException,
				  TestResultsItemDAODupKeyException,
				  TestResultsItemDAODBUpdateException,
				  TestResultsItemDAOAppException
  {
	 insertTestResultsItem();
  }

/**
 * Loads a personal preferences profile from persistent store into memory.
 * Mirrors the ejbLoad method.
 * @param testResultsItemID  A <code>Integer</code> that represents the testResultsItem id
 * @returns the profile of the answeritem <code>TestResultsItemModel</code>
 * @throws  <code>TestResultsItemDAOFinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>TestResultsItemDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while loading the profile
 */
  public void load() throws TestResultsItemDAOSysException,
					TestResultsItemDAOFinderException
  {
		selectTestResultsItem();
  }


/**
 * Stores a personal preferences profile from memory into persistent store.
 * Mirrors the ejbStore method.
 * @param details the <code>TestResultsItemModel</code> of the TestResultsItem
 * @throws  <code>TestResultsItemDAOAppException</code> is thrown if the profile
 *          could not be persisted because of an user error in inputs.
 * @throws  <code>TestResultsItemDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>TestResultsItemDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while persisting the profile
 */
   public void store() throws TestResultsItemDAODBUpdateException,
					  TestResultsItemDAOAppException,
					  TestResultsItemDAOSysException
   {
		updateTestResultsItem();
   }

/**
 * Removes a personal preferences profile from persistent store.
 * Mirrors the ejbRemove method.
 * @param testResultsItemID    a Integer that represents the testResultsItemID to be removed
 * @throws  <code>TestResultsItemDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>TestResultsItemDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while removing the profile
 */
   public void remove() throws TestResultsItemDAODBUpdateException,
								TestResultsItemDAOSysException
   {
		deleteTestResultsItem();
   }

/**
 * Finds a personal preferences profile from persistent store.
 * Mirrors the ejbFindByPrimaryKey method.
 * @returns a Integer which represents the primary key for this profile
 * @throws  <code>TestResultsItemDAOFinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>TestResultsItemDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while accessing the database
 */
	public Integer findByPrimaryKey(Integer testResultItemID) throws TestResultsItemDAOFinderException,
																	  TestResultsItemDAOSysException
	{
		if (TestResultsItemIDExists(testResultItemID))
			return (testResultItemID);
		throw new TestResultsItemDAOFinderException("primary key not found :"+testResultItemID);
	}

	public Integer findByAnswerItemIDContentIDStudent(String answerItemID, String contentID, String student) throws TestResultsItemDAOFinderException,
																	  TestResultsItemDAOSysException
	{
		if (AnswerItemIDContentIDStudentExists(answerItemID, contentID, student))
			return (testResultItemID);
		throw new TestResultsItemDAOFinderException("answeritemidcontentstudent not found");
	}

/**
 * Check to see if this ID is a unique constraint
 * violation on our database table (ie. there is already a id with the
 * value of testResultsItemID in the table).  If so, throw a
 * TestResultsItemDAOSysException .
 */
	private boolean TestResultsItemIDExists (Integer testResultItemID) throws TestResultsItemDAOSysException
	{
		Statement stmt = null;
		ResultSet result = null;
		boolean returnValue = false;
		this.testResultItemID = testResultItemID;
		/* Create a new row in the database for this testresultsitem */
		 String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("TestResultItemID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.TESTRESULTSITEM_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("TestResultItemID")
			  + " = "
			  + InterpretSQL.encodeEndCol(testResultItemID);

		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			result = stmt.executeQuery(queryStr);
			if ( !result.next() )
			{
				returnValue = false;
			}
			else
			{
				testResultItemID = new Integer(result.getInt(1));
				returnValue = true;
			}
		}
		catch(SQLException se)
		{
			throw new TestResultsItemDAOSysException( "SQLException while checking for an"
													+ " existing TestResultsItem - id -> " + testResultItemID + " :\n" + se);
		}
		finally
		{
			closeResultSet(result);
			closeStatement(stmt);
			closeDBConnection();
		}
		return returnValue;
	}


	private boolean AnswerItemIDContentIDStudentExists(String answerItemID, String contentID, String student) throws TestResultsItemDAOSysException
	{
		Statement stmt = null;
		ResultSet result = null;
		boolean returnValue = false;
		/* Create a new row in the database for this testresultsitem */
		 String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("TestResultItemID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.TESTRESULTSITEM_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("AnswerItemID")
			  + " = "
			  + InterpretSQL.encodeEndCol(answerItemID)
			  + " and "
			  + InterpretSQL.encodeEndSign("ContentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(contentID)
			  + " and "
			  + InterpretSQL.encodeEndSign("Student")
			  + " = "
			  + InterpretSQL.encodeEndCol(student);

		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			result = stmt.executeQuery(queryStr);
			if ( !result.next() )
			{
				returnValue = false;
			}
			else
			{
				this.testResultItemID = new Integer(result.getInt(1));
				returnValue = true;
			}
		}
		catch(SQLException se)
		{
			throw new TestResultsItemDAOSysException( "SQLException while checking for an"
													+ " existing TestResultsItem - id -> " + answerNumber + " :\n" + se);
		}
		finally
		{
			closeResultSet(result);
			closeStatement(stmt);
			closeDBConnection();
		}
		return returnValue;
	}
/**
 * Insert the method's description here.
 */
	private void insertTestResultsItem() throws TestResultsItemDAOSysException,
											TestResultsItemDAODupKeyException,
											TestResultsItemDAODBUpdateException,
											TestResultsItemDAOAppException
	{
		Statement stmt = null;
		ResultSet result=null;
		Calendar calendar=Calendar.getInstance();

		/* Create a new row in the database for this testresultsitem */
		String queryStr = "INSERT INTO "
		  + InterpretSQL.encodeEndSign(DatabaseNames.TESTRESULTSITEM_TABLE)
		  + InterpretSQL.startInsert()
		  + InterpretSQL.encodeSign("Student")
			  + InterpretSQL.encodeSign("IsRight")
			  + InterpretSQL.encodeSign("AnswerMark")
			  + InterpretSQL.encodeSign("Answer")
			  + InterpretSQL.encodeSign("AnswerItemID")
		  + InterpretSQL.encodeSign("Allow")
		  + InterpretSQL.encodeSign("CreateDate")
		  + InterpretSQL.encodeSign("CreateTime")
		  + InterpretSQL.encodeSign("CreateBy")
		  + InterpretSQL.encodeSign("LastModifyDate")
		  + InterpretSQL.encodeSign("LastModifyTime")
		  + InterpretSQL.encodeSign("LastModifyBy")
			  + InterpretSQL.encodeSign("ContentID")
			  + InterpretSQL.encodeEndSign("AnswerNumber")
		  + InterpretSQL.endInsert()
		  + " VALUES "
		  + InterpretSQL.startInsert()
		  + InterpretSQL.encodeCol(student)
			  + InterpretSQL.encodeCol(right)
			  + InterpretSQL.encodeCol(answerMark)
			  + InterpretSQL.encodeCol(answer)
			  + InterpretSQL.encodeCol(answerItemID)
		  + InterpretSQL.encodeCol("ÔÊÐí")
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeCol(createBy)
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeCol(createBy)
			  + InterpretSQL.encodeCol(contentID)
			  + InterpretSQL.encodeEndCol(answerNumber)
		  + InterpretSQL.endInsert();
/**
		String queryStr = "INSERT INTO \"" + DatabaseNames.TESTRESULTSITEM_TABLE +
						  "\"(\"TestResultItemID\",\"Student\",\"Right\",\"AnswerMark\",\"ExaminationID\","
						+ "\"Answer\",\"AnswerItemID\",\"Allow\",\"CreateDate\",\"CreateTime\",\"CreateBy\",\"LastmodifyDate\",\"LastmodifyTime\",\"LastmodifyBy\")"
						+ "VALUES ("
						+ details.getTestResultItemID().toString().trim() + ","
						+ details.getStudent().toString().trim() + ","
						+ "'" + details.getRight().trim() + ","
						+ "'" + details.getAnswerMark().trim() + ","
						+ details.getExaminationID().toString().trim() + ","
						+ "'" + details.getAnswer().trim() + "',"
						+ details.getAnswerItemID().toString().trim() +","
						+ "'" + details.getAllow().trim() +"',"
						+ "'" + details.getCreateDate().trim() +"',"
						+ "'" + details.getCreateTime().trim() +"',"
						+ details.getCreateBy().toString().trim() +","
						+ "'" + details.getLastModifyDate().trim() +"',"
						+ "'" + details.getLastModifyTime().trim() +"',"
						+ details.getLastModifyBy().toString().trim() +")";
*/
		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			int resultCount = stmt.executeUpdate(queryStr);

			if ( resultCount!=1)
			{
				throw new TestResultsItemDAODBUpdateException("ERROR in TESTRESULTSITEM_TABLE INSERT !! resultCount = " + resultCount);
			}
			else
			{
				queryStr = "SELECT last_value FROM \"TestResultIte_TestResultIte_seq\"";
				result = stmt.executeQuery(queryStr);

				/**
				 * get the primary key
				 */
				if(!result.next())
				{
					Debug.println("CAN NOT CREATE TESTRESULTSITEM.#2");
					throw new TestResultsItemDAODBUpdateException("CAN NOT CREATE TESTRESULTSITEM.#2");
				}
				else
				{
					if(result.getInt(1) < 1)
					{
						testResultItemID= new Integer(0);
						Debug.println("CAN NOT CREATE TESTRESULTSITEM.#3");
						throw new TestResultsItemDAODBUpdateException("CAN NOT CREATE TESTRESULTSITEM.#3");
					}else
					{
						testResultItemID = new Integer(result.getInt(1));
					}
				}
			}
		}
		catch(SQLException ae)
		{
			throw new TestResultsItemDAOSysException( "SQLException while inserting new " +
						"TestResultsItemID; id = " + testResultItemID.toString().trim() + " :\n" + ae);
		}
		finally
		{
			closeStatement(stmt);
			closeDBConnection();
		}
	}

/**
 * select the method's description here.
 */
	private void selectTestResultsItem() throws TestResultsItemDAOSysException,
																						 TestResultsItemDAOFinderException
	{
		Statement stmt = null;
		ResultSet result = null;

		/* Create a new row in the database for this testresultsitem */

		String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.TESTRESULTSITEM_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("TestResultItemID")
			  + " = "
			  + InterpretSQL.encodeEndCol(this.testResultItemID) ;

		/**
		String queryStr = "SELECT "+
						  "\"TestResultsItemID\",\"Student\",\"Right\",\"AnswerMark\",\"ExaminationID\","
						+ "\"Answer\",\"AnswerItemID\",\"Allow\",\"CreateDate\",\"CreateTime\","
						+ "\"Createby\",\"LastmodifyDate\",\"LastmodifyTime\",\"LastmodifyBy\""
						+ " FROM " + DatabaseNames.TESTRESULTSITEM_TABLE +
						  "\" WHERE \"TestResultsItemID\" = " + "'" + testResultsItemID.toString().trim() + "'";
		*/
		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			result = stmt.executeQuery(queryStr);

			if ( !result.next() )
				throw new TestResultsItemDAOFinderException( "No record for primary key " + testResultItemID);

			int i = 1;
			testResultItemID = new Integer(result.getInt(i++));

			student = new Integer(result.getInt(i++));

			right = result.getString(i++);

			answerMark = result.getString(i++);

			answer = result.getString(i++);

			answerItemID = new Integer(result.getInt(i++));

			allow = result.getString(i++);

			createDate = result.getString(i++);

			createTime = result.getString(i++);

			createBy = new Integer(result.getInt(i++));

			lastModifyDate = result.getString(i++);

			lastModifyTime = result.getString(i++);

			lastModifyBy = new Integer(result.getInt(i++));

			contentID = new Integer(result.getInt(i++));

			answerNumber = result.getString(i++);

		}
		catch(SQLException ae)
		{
			throw new TestResultsItemDAOSysException("SQLException while getting " +
					  "schoolreourceID; id = " + testResultItemID + " :\n" + ae);
		}
		finally
		{
			closeResultSet(result);
			closeStatement(stmt);
			closeDBConnection();
		}
	}
/**
 * Delete the method's description here.
 */
	private void deleteTestResultsItem () throws  TestResultsItemDAODBUpdateException,
											  TestResultsItemDAOSysException
	{
		/* Create a new row in the database for this testresultsitem */
		String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.TESTRESULTSITEM_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("TestResultItemID")
			  + " = "
			  + InterpretSQL.encodeEndCol(testResultItemID);

		/**
		String queryStr = "DELETE FROM \"" + DatabaseNames.TESTRESULTSITEM_TABLE
						+ "\" WHERE \"TestResultsItemID\" = " + testResultItemID.toString().trim();
		*/
		Statement stmt = null;

		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			int resultCount = stmt.executeUpdate(queryStr);

			if (resultCount != 1)
				throw new TestResultsItemDAODBUpdateException
				("ERROR deleteing TestResultsItem from TESTRESULTSITEM_TABLE!! resultCount = "+
				 resultCount);
		}
		catch(SQLException se)
		{
			throw new TestResultsItemDAOSysException("SQLException while removing " +
							"TestResultItemID; id = " + testResultItemID + " :\n" + se);
		}
		finally
		{
			closeStatement(stmt);
			closeDBConnection();
		}
	}
/**
 * Update the method's description here.
 */
	private void updateTestResultsItem() throws TestResultsItemDAODBUpdateException,
											TestResultsItemDAOAppException,
											TestResultsItemDAOSysException
	{
		Calendar calendar=Calendar.getInstance();
		/* Create a new row in the database for this testresultsitem */
		String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.TESTRESULTSITEM_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("Answer")
			  + " = "
			  + InterpretSQL.encodeCol(answer)
			  + InterpretSQL.encodeEndSign("IsRight")
			  + " = "
			  + InterpretSQL.encodeCol(right)
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  +  " = "
			  +InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  +InterpretSQL.encodeEndSign("LastModifyTime")
			  +" = "
			  +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  + InterpretSQL.encodeEndSign("LastModifyBy")
			  +" = "
			  + InterpretSQL.encodeEndCol(lastModifyBy)
			  +" WHERE "
			  + InterpretSQL.encodeEndSign("TestResultItemID")
			  +" = "
			  + InterpretSQL.encodeEndCol(testResultItemID);

		Statement stmt = null;
		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			int resultCount = stmt.executeUpdate(queryStr);
			if (resultCount != 1)
				throw new TestResultsItemDAODBUpdateException
				("ERROR updating TestResultsItem in TESTRESULTSITEM_TABLE!! resultCount = " +
				 resultCount);
		}
		catch(SQLException se)
		{
			throw new TestResultsItemDAOSysException("SQLException while updating " +
					 "TestResultItem; id = " +  testResultItemID + " :\n" + se);
		}
		finally
		{
			closeStatement(stmt);
			closeDBConnection();
		}
	}

/**
 * Sum the number of items are wrong or right.
 */
	public int[] calcScoreStatistics (Integer answerItemID) throws TestResultsItemDAOSysException
	{
		Statement stmt = null;
		ResultSet result = null;
		boolean returnValue = false;

		int rightAnswer = 0;
		int wrongAnswer = 0;

		/* Create a new row in the database for this testresultsitem */
		 String queryStr ="SELECT count(*), "
				+ InterpretSQL.encodeEndSign("IsRight")
				+ " from "
				+ InterpretSQL.encodeEndSign(DatabaseNames.TESTRESULTSITEM_TABLE)
				+ " where "
				+ InterpretSQL.encodeEndSign("AnswerItemID")
				+ " = "
				+ answerItemID
				+ " group by "
				+ InterpretSQL.encodeEndSign("IsRight")
				+ " order by "
				+ InterpretSQL.encodeEndSign("IsRight");

		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			result = stmt.executeQuery(queryStr);

			int arRet[] = new int[2];
			arRet[0] = arRet[1] = 0;

			while (result.next())
			{
				if ("right".equals(result.getString(2)))
				   arRet[0] = result.getInt(1);
				else
				   arRet[1] += result.getInt(1);
			}

			return arRet;
		}
		catch(Exception se)
		{
			return null;
		}
		finally
		{
			closeResultSet(result);
			closeStatement(stmt);
			closeDBConnection();
		}
	}
}