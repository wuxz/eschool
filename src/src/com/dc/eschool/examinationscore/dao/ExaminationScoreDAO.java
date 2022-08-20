package com.dc.eschool.examinationscore.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

import com.dc.eschool.examinationscore.model.ExaminationScoreModel;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAOSysException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAOAppException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAODBUpdateException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAOFinderException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAODupKeyException;


import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.Calendar;
import com.dc.eschool.util.DatabaseSetup;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * This class implements ExaminationScoreDAO for Oracle, Sybase and cloudscape databases
 * This class encapsulates all the JDBC calls made by the ExaminationScoreEJB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of ExaminationScoreEJB is
 * implemented here.
 */

public class ExaminationScoreDAO extends GeneralDAO
{
   protected Integer examinationID;
   private Integer projectID;
   private Integer student;
   private Integer score;
   private Integer testResultItemID;
   private String  allow;
   private String  createDate;
   private String  createTime;
   private Integer createBy;
   private String  lastModifyDate;
   private String  lastModifyTime;
   private Integer lastModifyBy;
/**
 * ExaminationScore  constructor comment.
 */
   public ExaminationScoreDAO() throws DAOException
   {
	  super();
   }
/* get and set methods for the instance variables */
  public Integer getExaminationID()
  {
	return examinationID;
  }
  public Integer getProjectID()
  {
	return projectID;
  }
  public Integer getStudent()
  {
	return student;
  }
  public Integer getScore()
  {
	return score;
  }
  public Integer getTestResultItemID()
  {
	return testResultItemID;
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

  public void setExaminationID(Integer examinationID)
  {
	this.examinationID = examinationID;
  }
  public void setProjectID(Integer projectID)
  {
	this.projectID = projectID;
  }
  public void setStudent(Integer student)
  {
	this.student = student;
  }
  public void setScore(Integer score)
  {
	this.score = score;
  }
  public void setTestResultItemID(Integer testResultItemID)
  {
	this.testResultItemID = testResultItemID;
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
 * Creates a ExaminationScore the data in persistent store.
 * Mirrors the ejbCreate method.
 * @param detail  the <code>ExaminationScoreModel</code>
 * @throws  <code>ExaminationScoreDAOAppException</code> is thrown if the profile
 *          could not be persisted because of an user error in inputs.
 * @throws  <code>ExaminationScoreDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>ExaminationScoreDAODupKeyException</code> is thrown if a
 *          with the same userid exists in the database
 * @throws  <code>ExaminationScoreDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while persisting the profile
 */
   public void create() throws ExaminationScoreDAOSysException,
				   ExaminationScoreDAODupKeyException,
				   ExaminationScoreDAODBUpdateException,
				   ExaminationScoreDAOAppException
   {
		insertExaminationScore();
   }

/**
 * Loads a personal preferences profile from persistent store into memory.
 * Mirrors the ejbLoad method.
 * @param examinationID  A <code>Integer</code> that represents the examination  id
 * @returns the profile of the examination <code>ExaminationScoreModel</code>
 * @throws  <code>ExaminationScoreDAOFinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>ExaminationScoreDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while loading the profile
 */
	public void load() throws ExaminationScoreDAOSysException,
					  ExaminationScoreDAOFinderException
	{
		selectExaminationScore();
	}

/**
 * Stores a personal preferences profile from memory into persistent store.
 * Mirrors the ejbStore method.
 * @param details the <code>ExaminationScoreModel</code> of the examination
 * @throws  <code>ExaminationScoreDAOAppException</code> is thrown if the profile
 *          could not be persisted because of an user error in inputs.
 * @throws  <code>ExaminationScoreDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>ExaminationScoreDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while persisting the profile
 */
	public void store() throws  ExaminationScoreDAODBUpdateException,
					ExaminationScoreDAOAppException,
					ExaminationScoreDAOSysException
	{
		updateExaminationScore();
	}

/**
 * Removes a personal preferences profile from persistent store.
 * Mirrors the ejbRemove method.
 * @param examinationID    a Integer that represents the examinationID to be removed
 * @throws  <code>ExaminationScoreDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>ExaminationScoreDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while removing the profile
 */
	public void remove() throws ExaminationScoreDAODBUpdateException,
								 ExaminationScoreDAOSysException
	{
		deleteExaminationScore();
	}

/**
 * Finds a personal preferences profile from persistent store.
 * Mirrors the ejbFindByPrimaryKey method.
 * @returns a Integer which represents the primary key for this profile
 * @throws  <code>ExaminationScoreDAOFinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>ExaminationScoreDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while accessing the database
 */
	public Integer findByPrimaryKey(Integer examinationID) throws ExaminationScoreDAOFinderException,
									  ExaminationScoreDAOSysException
	{
		if (ExaminationIDExists(examinationID))
			return (examinationID);
		throw new ExaminationScoreDAOFinderException("primary key not found :"+ examinationID);
	}

/**
 * Finds a personal preferences profile from persistent store.
 * Mirrors the ejbFindByProjectStudent method.
 * @returns a Integer which represents the primary key for this profile
 * @throws  <code>ExaminationScoreDAOFinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>ExaminationScoreDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while accessing the database
 */
	public Integer findByProjectStudent(Integer projectID, Integer student) throws ExaminationScoreDAOFinderException,
									  ExaminationScoreDAOSysException
	{
		if (ExaminationIDExists(projectID, student))
			return (examinationID);
		throw new ExaminationScoreDAOFinderException("primary key not found :"+ examinationID);
	}

/**
 * Check to see if this ID is a unique constraint
 * violation on our database table (ie. there is already a id with the
 * value of examinationID in the table).  If so, throw a
 * ExaminationScoreDAOSysException .
 */
	private boolean ExaminationIDExists (Integer examinationID) throws ExaminationScoreDAOSysException
	{
		Statement stmt = null;
		ResultSet result = null;
		boolean returnValue = false;

		/* Create a new row in the database for this examination  */
		String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("ExaminationID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.EXAMINATIONSCORE_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ExaminationID")
			  + " = "
			  + InterpretSQL.encodeEndCol(examinationID);
/**
		String queryStr ="SELECT \"ExaminationID \" FROM \"" +
							DatabaseNames.EXAMINATIONSCORE_TABLE
						+ "\" WHERE \" ExaminationID\" = " + "'" + examinationID.toString().trim()+ "'";
*/

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
				examinationID = new Integer(result.getInt(1));
				returnValue = true;
			}
		}
		catch(SQLException se)
		{
			throw new ExaminationScoreDAOSysException(
						   "SQLException while checking for an"
						   + " existing ExaminationScore - id -> " + examinationID + " :\n" + se);
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
 * Check to see if these two IDs is a unique constraint
 * violation on our database table (ie. there is already a id with the
 * value of projectID and student in the table).  If so, throw a
 * ExaminationScoreDAOSysException .
 */
	private boolean ExaminationIDExists (Integer projectID, Integer student) throws ExaminationScoreDAOSysException
	{
		Statement stmt = null;
		ResultSet result = null;
		boolean returnValue = false;

		/* Create a new row in the database for this examination  */
		String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("ExaminationID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.EXAMINATIONSCORE_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ProjectID")
			  + " = "
			  + InterpretSQL.encodeEndCol(projectID)
			  + " AND "
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
				examinationID = new Integer(result.getInt(1));
				returnValue = true;
			}
		}
		catch(SQLException se)
		{
			throw new ExaminationScoreDAOSysException(
						   "SQLException while checking for an"
						   + " existing ExaminationScore - id -> " + examinationID + " :\n" + se);
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
	private void insertExaminationScore() throws ExaminationScoreDAOSysException,
							 ExaminationScoreDAODupKeyException,
								 ExaminationScoreDAODBUpdateException,
								 ExaminationScoreDAOAppException
	{

		Statement stmt = null;
		ResultSet result=null;
		Calendar calendar=Calendar.getInstance();
		/* Create a new row in the database for this examination  */
		String queryStr = "INSERT INTO "
		  + InterpretSQL.encodeEndSign(DatabaseNames.EXAMINATIONSCORE_TABLE)
		  + InterpretSQL.startInsert()
		  + InterpretSQL.encodeSign("ProjectID")
			  + InterpretSQL.encodeSign("Student")
			  + InterpretSQL.encodeSign("Score")
			  + InterpretSQL.encodeSign("TestResultItemID")
		  + InterpretSQL.encodeSign("Allow")
		  + InterpretSQL.encodeSign("CreateDate")
		  + InterpretSQL.encodeSign("CreateTime")
		  + InterpretSQL.encodeSign("CreateBy")
		  + InterpretSQL.encodeSign("LastModifyDate")
		  + InterpretSQL.encodeSign("LastModifyTime")
		  + InterpretSQL.encodeEndSign("LastModifyBy")
		  + InterpretSQL.endInsert()
		  + " VALUES "
		  + InterpretSQL.startInsert()
		  + InterpretSQL.encodeCol(projectID)
			  + InterpretSQL.encodeCol(student)
			  + InterpretSQL.encodeCol(score)
			  + InterpretSQL.encodeCol(testResultItemID)
		  + InterpretSQL.encodeCol("ÔÊÐí")
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeCol(createBy)
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeEndCol(createBy)
		  + InterpretSQL.endInsert();
/**
		String queryStr = "INSERT INTO \"" + DatabaseNames.EXAMINATIONSCORE_TABLE +
						"\"(\"ExaminationID\",\"ProjectID\",\"Student\",\"Score\","
						+ "\"Allow\",\"CreateDate\",\"CreateTime\",\"CreateBy\",\"LastmodifyDate\",\"LastmodifyTime\",\"LastmodifyBy\")"
						+ "VALUES ("
						+ details.getExaminationID().toString().trim() + ","
						+ details.getProjectID().toString().trim() + ","
						+ details.getStudent().toString().trim() + ","
						+ details.getScore().toString().trim() + ","
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
				throw new ExaminationScoreDAODBUpdateException(
					"ERROR in EXAMINATIONSCORE_TABLE INSERT !! resultCount = " +
								   resultCount);
			}else
			{
			  queryStr = "SELECT last_value FROM \"ExaminationSc_ExaminationID_seq\"";
			  result = stmt.executeQuery(queryStr);

			  /**
			   * get the primary key
			   */
				if(!result.next())
				{
					Debug.println("CAN NOT CREATE EXAMINATIONSCORE.#2");
					throw new ExaminationScoreDAODBUpdateException("CAN NOT CREATE EXAMINATIONSCORE.#2");
				}else
				{
					if(result.getInt(1) < 1)
					{
						examinationID= new Integer(0);
						Debug.println("CAN NOT CREATE EXAMINATIONSCORE.#3");
						throw new ExaminationScoreDAODBUpdateException("CAN NOT CREATE EXAMINATIONSCORE.#3");
					}else
					{
						examinationID = new Integer(result.getInt(1));
					}
				}
			}
		}
		catch(SQLException ae)
		{
			throw new ExaminationScoreDAOSysException(
						"SQLException while inserting new " +
						"ExaminationID; id = " + examinationID.toString() + " :\n" + ae);
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
	private void selectExaminationScore() throws ExaminationScoreDAOSysException,
											 ExaminationScoreDAOFinderException
	{
		Statement stmt = null;
		ResultSet result = null;

		/* Create a new row in the database for this examination  */
		String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.EXAMINATIONSCORE_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ExaminationID")
			  + " = "
			  + InterpretSQL.encodeEndCol(examinationID) ;

		/**
		String queryStr = "SELECT "+
						  "\"ExaminationID\",\"ProjectID\",\"Student\",\"Score\","
						+ "\"Allow\",\"CreateDate\",\"CreateTime\","
						+ "\"Createby\",\"LastmodifyDate\",\"LastmodifyTime\",\"LastmodifyBy\""
						+ " FROM " + DatabaseNames.EXAMINATIONSCORE_TABLE +
						  "\" WHERE \"ExaminationID\" = " + "'" + examinationID.toString().trim() + "'";
		*/

		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			result = stmt.executeQuery(queryStr);

			if ( !result.next() ) throw new ExaminationScoreDAOFinderException(
								  "No record for primary key " + examinationID);

			int i = 1;
			examinationID = new Integer(result.getInt(i++));
			projectID = new Integer(result.getInt(i++));
			student = new Integer(result.getInt(i++));
			score = new Integer(result.getInt(i++));
			allow = result.getString(i++);
			createDate = result.getString(i++);
			createTime = result.getString(i++);
			createBy = new Integer(result.getInt(i++));
			lastModifyDate = result.getString(i++);
			lastModifyTime = result.getString(i++);
			lastModifyBy = new Integer(result.getInt(i++));

		}
		catch(SQLException ae)
		{
			throw new ExaminationScoreDAOSysException("SQLException while getting " +
					  "ExaminationID; id = " + examinationID + " :\n" + ae);
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
	private void deleteExaminationScore () throws  ExaminationScoreDAODBUpdateException,
												   ExaminationScoreDAOSysException
	{
		Statement stmt = null;
		/* Create a new row in the database for this examination */
		String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.EXAMINATIONSCORE_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ExaminationID")
			  + " = "
			  + InterpretSQL.encodeEndCol(examinationID);
		/**
		String queryStr = "DELETE FROM \"" + DatabaseNames.EXAMINATIONSCORE_TABLE
						+ "\" WHERE \"ExaminationID\" = " + examinationID.toString().trim();
		*/

		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			int resultCount = stmt.executeUpdate(queryStr);

			if (resultCount != 1)
				throw new ExaminationScoreDAODBUpdateException
				("ERROR deleteing ExaminationScore from EXAMINATIONSCORE_TABLE!! resultCount = "+
				 resultCount);
		}
		catch(SQLException se)
		{
			throw new ExaminationScoreDAOSysException("SQLException while removing " +
							"ExaminationID; id = " + examinationID + " :\n" + se);
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
	private void updateExaminationScore() throws ExaminationScoreDAODBUpdateException,
											 ExaminationScoreDAOAppException,
											 ExaminationScoreDAOSysException
	{
		 Calendar calendar=Calendar.getInstance();
		/* Create a new row in the database for this examination */
		String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.EXAMINATIONSCORE_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("ProjectID")
			  + " = "
			  + InterpretSQL.encodeCol(projectID)
						  + InterpretSQL.encodeEndSign("Student")
			  + " = "
			  + InterpretSQL.encodeCol(student)
						  + InterpretSQL.encodeEndSign("Score")
			  + " = "
			  + InterpretSQL.encodeCol(score)
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  +  " = "
			  +InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  +InterpretSQL.encodeEndSign("LastModifyTime")
			  +" = "
			  +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  +InterpretSQL.encodeEndSign("LastModifyBy")
			  +" = "
			  +InterpretSQL.encodeEndCol(lastModifyBy)
			  +" WHERE "
			  +InterpretSQL.encodeEndSign("ExaminationID")
			  +" = "
			  +InterpretSQL.encodeEndCol(examinationID);

		/**
		String queryStr = "UPDATE \"" + DatabaseNames.EXAMINATIONSCORE_TABLE + "\" SET "
						+ "\"ProjectID\" = " + details.getProjectID().toString().trim() + ","
						+ "\"Student\" = " + details.getStudent().toString().trim() + ","
						+ "\"Score\" = " + details.getScore().toString().trim() + ","
						+ "\"Allow\" = " + "'" + details.getAllow().trim() + "',"
						+ "\"CreateDate\" = " + "'" + details.getCreateDate().trim() + "',"
						+ "\"CreateTime\" = " + "'" + details.getCreateTime().trim() + "',"
						+ "\"CreateBy\" = " + details.getCreateBy().toString().trim() + ","
						+ "\"LastmodifyDate\" = " + "'" + details.getLastModifyDate().trim() + "',"
						+ "\"LastmodifyTime\" = " + "'" + details.getLastModifyTime().trim() + "',"
						+ "\"LastmodifyBy\" = " + details.getLastModifyBy().toString().trim() + ",";
		*/

		Statement stmt = null;
		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			int resultCount = stmt.executeUpdate(queryStr);
			if (resultCount != 1)
				throw new ExaminationScoreDAODBUpdateException
				("ERROR updating ExaminationScore in EXAMINATIONSCORE_TABLE!! resultCount = " +
				 resultCount);
		}
		catch(SQLException se)
		{
			throw new ExaminationScoreDAOSysException("SQLException while updating " +
					 "ExaminationScore; id = " + examinationID + " :\n" + se);
		}
		finally
		{
			closeStatement(stmt);
			closeDBConnection();
		}
	}
}