package com.dc.eschool.scorestatistic.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

import com.dc.eschool.scorestatistic.model.ScoreStatisticModel;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAOSysException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAOAppException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAODBUpdateException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAOFinderException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAODupKeyException;


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
 * This class implements ScoreStatisticDAO for Oracle, Sybase and cloudscape databases
 * This class encapsulates all the JDBC calls made by the ScoreStatisticEJB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of ScoreStatisticEJB is
 * implemented here.
 */

public class ScoreStatisticDAO extends GeneralDAO
{
	protected Integer scoreStatisticID;
	private Integer student;
	private String  answerItemID;
	private Integer projectContentID;
	private String  statistic;
	private Integer rightAnswer;
	private Integer wrongAnswer;
	private String  allow;
	private String  createDate;
	private String  createTime;
	private Integer createBy;
	private String  lastModifyDate;
	private String  lastModifyTime;
	private Integer lastModifyBy;
/**
 * ScoreStatistic constructor comment.
 */
   public ScoreStatisticDAO() throws DAOException
   {
	  super();
   }

/* get and set methods for the instance variables */
  public Integer getScoreStatisticID()
  {
	return scoreStatisticID;
  }
  public Integer getStudent()
  {
	return student;
  }
  public String getAnswerItemID()
  {
	return answerItemID;
  }
  public Integer getProjectContentID()
  {
	return projectContentID;
  }
  public String  getStatistic()
  {
	return statistic;
  }
  public Integer getRightAnswer()
  {
	return rightAnswer;
  }
  public Integer getWrongAnswer()
  {
	return wrongAnswer;
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

  public void setScoreStatisticID(Integer scoreStatisticID)
  {
	this.scoreStatisticID = scoreStatisticID;
  }
  public void setStudent(Integer student)
  {
	this.student = student;
  }
  public void setAnswerItemID(String answerItemID)
  {
	this.answerItemID = answerItemID;
  }
  public void setProjectContentID(Integer projectContentID)
  {
	this.projectContentID = projectContentID;
  }
  public void setStatistic(String statistic)
  {
	this.statistic = statistic;
  }
  public void setRightAnswer(Integer rightAnswer)
  {
	this.rightAnswer = rightAnswer;
  }
  public void setWrongAnswer(Integer wrongAnswer)
  {
	this.wrongAnswer = wrongAnswer;
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
 * Creates a ScoreStatistic  the data in persistent store.
 * Mirrors the ejbCreate method.
 * @param detail  the <code>ScoreStatisticModel</code>
 * @throws  <code>ScoreStatisticDAOAppException</code> is thrown if the profile
 *          could not be persisted because of an user error in inputs.
 * @throws  <code>ScoreStatisticDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>ScoreStatisticDAODupKeyException</code> is thrown if a
 *          with the same userid exists in the database
 * @throws  <code>ScoreStatisticDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while persisting the profile
 */
   public void create() throws ScoreStatisticDAOSysException,
							  ScoreStatisticDAODupKeyException,
							  ScoreStatisticDAODBUpdateException,
							  ScoreStatisticDAOAppException
   {
		insertScoreStatistic();
   }

/**
 * Loads a personal preferences profile from persistent store into memory.
 * Mirrors the ejbLoad method.
 * @param scoreStatisticID  A <code>Integer</code> that represents the scoreStatistic  id
 * @returns the profile of the scoreStatistic <code>ScoreStatisticModel</code>
 * @throws  <code>ScoreStatisticDAOFinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>ScoreStatisticDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while loading the profile
 */
	public void load() throws ScoreStatisticDAOSysException,
				  ScoreStatisticDAOFinderException
	{
		selectScoreStatistic();
	}

/**
 * Stores a personal preferences profile from memory into persistent store.
 * Mirrors the ejbStore method.
 * @param details the <code>ScoreStatisticModel</code> of the schoolresource
 * @throws  <code>ScoreStatisticDAOAppException</code> is thrown if the profile
 *          could not be persisted because of an user error in inputs.
 * @throws  <code>ScoreStatisticDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>ScoreStatisticDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while persisting the profile
 */
	public void store() throws ScoreStatisticDAODBUpdateException,
					   ScoreStatisticDAOAppException,
					   ScoreStatisticDAOSysException
	{
		updateScoreStatistic();
	}


/**
 * Removes a personal preferences profile from persistent store.
 * Mirrors the ejbRemove method.
 * @param scoreStatisticID    a Integer that represents the scoreStatisticID to be removed
 * @throws  <code>ScoreStatisticDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>ScoreStatisticDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while removing the profile
 */
	public void remove() throws ScoreStatisticDAODBUpdateException,
					ScoreStatisticDAOSysException
	{
		deleteScoreStatistic();
	}

/**
 * Finds a personal preferences profile from persistent store.
 * Mirrors the ejbFindByPrimaryKey method.
 * @returns a Integer which represents the primary key for this profile
 * @throws  <code>ScoreStatisticDAOFinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>ScoreStatisticDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while accessing the database
 */
	public Integer findByPrimaryKey(Integer scoreStatisticID) throws ScoreStatisticDAOFinderException,
											 ScoreStatisticDAOSysException
	{
		if (ScoreStatisticIDExists(scoreStatisticID))
			return (scoreStatisticID);
		throw new ScoreStatisticDAOFinderException("primary key not found :"+ scoreStatisticID);
	}

	public String findByAnswerItemID(String answerItemID) throws ScoreStatisticDAOFinderException,
											   ScoreStatisticDAOSysException
	{
		if (AnswerItemIDExists(answerItemID))
			return (answerItemID);
		else
			return  "";
		//throw new ScoreStatisticDAOFinderException("primary key not found :"+ answerItemID);
	}
/**
 * Check to see if this ID is a unique constraint
 * violation on our database table (ie. there is already a id with the
 * value of scoreStatisticID in the table).  If so, throw a
 * ScoreStatisticDAOSysException .
 */
	private boolean ScoreStatisticIDExists (Integer scoreStatisticID) throws ScoreStatisticDAOSysException
	{
		Statement stmt = null;
		ResultSet result = null;
		boolean returnValue = false;
		/* Create a new row in the database for this scorestatistic */
		String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("ScoreStatisticID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ScoreStatisticID")
			  + " = "
			  + InterpretSQL.encodeEndCol(scoreStatisticID);
/**
		String queryStr ="SELECT \"ScoreStatisticID \" FROM \"" +
						 DatabaseNames.SCORESTATISTIC_TABLE
						+ "\" WHERE \" ScoreStatisticID\" = " + "'" + scoreStatisticID.toString().trim()+ "'";
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
				scoreStatisticID = new Integer(result.getInt(1));
				returnValue = true;
			}
		}
		catch(SQLException se)
		{
			throw new ScoreStatisticDAOSysException(
						   "SQLException while checking for an"
						   + " existing ScoreStatistic - id -> " + scoreStatisticID + " :\n" + se);
		}
		finally
		{
			closeResultSet(result);
			closeStatement(stmt);
			closeDBConnection();
		}
		return returnValue;
	}


	private boolean AnswerItemIDExists (String answerItemID) throws ScoreStatisticDAOSysException
	{
		Statement stmt = null;
		ResultSet result = null;
		boolean returnValue = false;
		/* Create a new row in the database for this scorestatistic */
		String queryStr ="SELECT "
			  + InterpretSQL.encodeSign("AnswerItemID")
						  + InterpretSQL.encodeEndSign("ScoreStatisticID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("AnswerItemID")
			  + " = "
			  + InterpretSQL.encodeEndCol(answerItemID);

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
				answerItemID = result.getString(1);
				this.scoreStatisticID = new Integer(result.getInt(2));
				returnValue = true;
			}
		}
		catch(SQLException se)
		{
			throw new ScoreStatisticDAOSysException(
						   "SQLException while checking for an"
						   + " existing AnswerItemID -> " + answerItemID + " :\n" + se);
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
	private void insertScoreStatistic() throws ScoreStatisticDAOSysException,
							   ScoreStatisticDAODupKeyException,
							   ScoreStatisticDAODBUpdateException,
							   ScoreStatisticDAOAppException
	{
		Statement stmt = null;
		ResultSet result=null;
	Calendar calendar=Calendar.getInstance();

		/* Create a new row in the database for this ScoreStatistic  */
		String queryStr = "INSERT INTO "
		  + InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE)
		  + InterpretSQL.startInsert()
		  + InterpretSQL.encodeSign("Student")
			  + InterpretSQL.encodeSign("AnswerItemID")
			  + InterpretSQL.encodeSign("ProjectContentID")
			  + InterpretSQL.encodeSign("Statistic")
			  + InterpretSQL.encodeSign("RightAnswer")
			  + InterpretSQL.encodeSign("WrongAnswer")
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
		  + InterpretSQL.encodeCol(student)
			  + InterpretSQL.encodeCol(answerItemID)
			  + InterpretSQL.encodeCol(projectContentID)
			  + InterpretSQL.encodeCol(statistic)
			  + InterpretSQL.encodeCol(rightAnswer)
			  + InterpretSQL.encodeCol(wrongAnswer)
		  + InterpretSQL.encodeCol("ÔÊÐí")
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeCol(createBy)
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeEndCol(createBy)
		  + InterpretSQL.endInsert();

		/**
		String queryStr = "INSERT INTO \"" + DatabaseNames.SCORESTATISTIC_TABLE +
						  "\"(\"ScoreStatisticID\",\"Student\",\"AnswerItemID\",\"ProjectContentID\",\"Statistic\","
						+ "\"RightAnswer\",\"WrongAnswer\",\"Allow\",\"CreateDate\",\"CreateTime\",\"CreateBy\",\"LastmodifyDate\",\"LastmodifyTime\",\"LastmodifyBy\")"
						+ "VALUES ("
						+ details.getScoreStatisticID().toString().trim() + ","
						+ details.getStudent().toString().trim() + ","
						+ details.getAnswerItemID().toString().trim() + ","
						+ details.getProjectContentID().toString().trim() + ","
						+ "'" + details.getStatistic().trim() + "',"
						+ details.getRightAnswer().toString().trim() +","
						+ details.getWrongAnswer().toString().trim() +","
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
				throw new ScoreStatisticDAODBUpdateException(
					"ERROR in SCORESTATISTIC_TABLE INSERT !! resultCount = " +
								   resultCount);
			}else
			{
			  queryStr = "SELECT last_value FROM \"ScoreStatisti_ScoreStatisti_seq\"";
			  result = stmt.executeQuery(queryStr);

			  /**
			   * get the primary key
			   */
				if(!result.next())
				{
					Debug.println("CAN NOT CREATE SCORESTATISTIC.#2");
					throw new ScoreStatisticDAODBUpdateException("CAN NOT CREATE SCORESTATISTIC.#2");
				}else
				{
					if(result.getInt(1) < 1)
					{
						scoreStatisticID= new Integer(0);
						Debug.println("CAN NOT CREATE SCORESTATISTIC.#3");
						throw new ScoreStatisticDAODBUpdateException("CAN NOT CREATE SCORESTATISTIC.#3");
					}else
					{
						scoreStatisticID = new Integer(result.getInt(1));
					}
				}
			}
		}
		catch(SQLException ae)
		{
			throw new ScoreStatisticDAOSysException(
						"SQLException while inserting new " +
						"ScoreStatisticID; id = " + scoreStatisticID.toString() + " :\n" + ae);
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
	private void selectScoreStatistic() throws ScoreStatisticDAOSysException,
											   ScoreStatisticDAOFinderException
	{
		Statement stmt = null;
		ResultSet result = null;

		/* Create a new row in the database for this ScoreStatistic  */
		String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ScoreStatisticID")
			  + " = "
			  + InterpretSQL.encodeEndCol(this.scoreStatisticID) ;
/**
		String queryStr = "SELECT "+
					  "\"ScoreStatisticID\",\"Student\",\"AnswerItemID\",\"ProjectContentID\",\"Statistic\","
					+ "\"RightAnswer\",\"WrongAnswer\",\"Allow\",\"CreateDate\",\"CreateTime\","
					+ "\"Createby\",\"LastmodifyDate\",\"LastmodifyTime\",\"LastmodifyBy\""
					+ " FROM " + DatabaseNames.SCORESTATISTIC_TABLE +
						  "\" WHERE \"ScoreStatisticID\" = " + "'" + scoreStatisticID.toString().trim() + "'";
 */
		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			result = stmt.executeQuery(queryStr);


			if ( !result.next() )
				throw new ScoreStatisticDAOFinderException(
								  "No record for primary key " + scoreStatisticID);

			int i = 1;
			scoreStatisticID = new Integer(result.getInt(i++));
			student = new Integer(result.getInt(i++));
			answerItemID = result.getString(i++);
			projectContentID = new Integer(result.getInt(i++));
			statistic = result.getString(i++);
			rightAnswer = new Integer(result.getInt(i++));
			wrongAnswer = new Integer(result.getInt(i++));
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
			throw new ScoreStatisticDAOSysException("SQLException while getting " +
					  "scoreStatisticID; id = " + scoreStatisticID + " :\n" + ae);
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
	private void deleteScoreStatistic () throws  ScoreStatisticDAODBUpdateException,
											 ScoreStatisticDAOSysException
   {
		 Statement stmt = null;
		/* Create a new row in the database for this ScoreStatistic  */
		String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ScoreStatisticID")
			  + " = "
			  + InterpretSQL.encodeEndCol(scoreStatisticID);
	   /**
		String queryStr = "DELETE FROM \"" + DatabaseNames.SCORESTATISTIC_TABLE
						+ "\" WHERE \"ScoreStatisticID\" = " + scoreStatisticID.toString().trim();

		*/

		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			int resultCount = stmt.executeUpdate(queryStr);

			if (resultCount != 1)
				throw new ScoreStatisticDAODBUpdateException
				("ERROR deleteing ScoreStatistic from SCORESTATISTIC_TABLE!! resultCount = "+
				 resultCount);
		}
		catch(SQLException se)
		{
			throw new ScoreStatisticDAOSysException("SQLException while removing " +
							"scoreStatisticID; id = " + scoreStatisticID + " :\n" + se);
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
	private void updateScoreStatistic() throws ScoreStatisticDAODBUpdateException,
											   ScoreStatisticDAOAppException,
											   ScoreStatisticDAOSysException
	{
		Calendar calendar=Calendar.getInstance();
		/* Create a new row in the database for this ScoreStatistic  */
		String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("Student")
			  + " = "
			  + InterpretSQL.encodeCol(student)
						  + InterpretSQL.encodeEndSign("AnswerItemID")
			  + " = "
			  + InterpretSQL.encodeCol(answerItemID)
						  + InterpretSQL.encodeEndSign("ProjectContentID")
			  + " = "
			  + InterpretSQL.encodeCol(projectContentID)
						  + InterpretSQL.encodeEndSign("Statistic")
			  + " = "
			  + InterpretSQL.encodeCol(statistic)
						  + InterpretSQL.encodeEndSign("RightAnswer")
			  + " = "
			  + InterpretSQL.encodeCol(rightAnswer)
						  + InterpretSQL.encodeEndSign("WrongAnswer")
			  + " = "
			  + InterpretSQL.encodeCol(wrongAnswer)
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
			  +InterpretSQL.encodeEndSign("ScoreStatisticID")
			  +" = "
			  +InterpretSQL.encodeEndCol(scoreStatisticID);
		/**
		String queryStr = "UPDATE \"" + DatabaseNames.SCORESTATISTIC_TABLE + "\" SET "
						+ "\"Student\" = " + details.getStudent().toString().trim() + ","
						+ "\"AnswerItemID\" = " + details.getAnswerItemID().toString().trim() + ","
						+ "\"ProjectContentID\" = " + details.getProjectContentID().toString().trim() + ","
						+ "\"Statistic\" = " + "'" + details.getStatistic().trim() + "',"
						+ "\"RightAnswer\" = " + details.getRightAnswer().toString().trim() + ","
						+ "\"WrongAnswer\" = " + details.getWrongAnswer().toString().trim() + ","
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
				throw new ScoreStatisticDAODBUpdateException
				("ERROR updating ScoreStatistic in SCORESTATISTIC_TABLE!! resultCount = " +
				 resultCount);
		}
		catch(SQLException se)
		{
			throw new ScoreStatisticDAOSysException("SQLException while updating " +
					 "ScoreStatistic; id = " + scoreStatisticID + " :\n" + se);
		}
		finally
		{
			closeStatement(stmt);
			closeDBConnection();
		}
	}
}