package com.dc.eschool.schoolresource.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

import com.dc.eschool.schoolresource.model.SchoolResourceModel;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAOSysException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAOAppException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAODBUpdateException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAOFinderException;
import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAODupKeyException;


import com.dc.eschool.util.Calendar;
import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.GeneralDAO;
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
 * This class implements SchoolResourceDAO for Oracle, Sybase and cloudscape databases
 * This class encapsulates all the JDBC calls made by the SchoolResourceEJB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of SchoolResourceEJB is
 * implemented here.
 */

public class SchoolResourceDAO extends GeneralDAO
{
	protected Integer schoolResourceID;
	private String  name;
	private String  docURL;
	private Integer subjectID;
	private String  startDate;
	private String  endDate;
	private String  explain;
	private String  allow;
	private String  createDate;
	private String  createTime;
	private Integer createBy;
	private String  lastModifyDate;
	private String  lastModifyTime;
	private Integer lastModifyBy;



/**
 * SchoolResource constructor comment.
 */
   public SchoolResourceDAO() throws DAOException
   {
	  super();
   }

   /* get and set methods for the instance variables */
   public Integer getSchoolResourceID()
   {
	return schoolResourceID;
   }
   public String  getName()
   {
	return name;
   }
   public String  getDocURL()
   {
	return docURL;
   }
   public Integer getSubjectID()
   {
	return subjectID;
   }
   public String getStartDate()
   {
	return startDate;
   }
   public String getEndDate()
   {
	return endDate;
   }
   public String getExplain()
   {
	return explain;
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
   public void setSchoolResourceID(Integer schoolResourceID)
   {
	this.schoolResourceID = schoolResourceID;
   }
   public void setName(String name)
   {
	this.name = name;
   }
   public void setDocURL(String docURL)
   {
	this.docURL = docURL;
   }
   public void setSubjectID(Integer subjectID)
   {
	this.subjectID = subjectID;
   }
   public void setStartDate(String startDate)
   {
	this.startDate = startDate;
   }
   public void setEndDate(String endDate)
   {
	this.endDate = endDate;
   }
   public void setExplain(String explain)
   {
	this.explain = explain;
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
 * Creates a schoolresource  the data in persistent store.
 * Mirrors the ejbCreate method.
 * @param detail  the <code>SchoolResourceModel</code>
 * @throws  <code>SchoolResourceDAOAppException</code> is thrown if the profile
 *          could not be persisted because of an user error in inputs.
 * @throws  <code>SchoolResourceDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>SchoolResourceDAODupKeyException</code> is thrown if a
 *          with the same userid exists in the database
 * @throws  <code>SchoolResourceDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while persisting the profile
 */
   public void create() throws SchoolResourceDAOSysException,
														  SchoolResourceDAODupKeyException,
														  SchoolResourceDAODBUpdateException,
														  SchoolResourceDAOAppException
   {
	   insertSchoolResource();
   }

/**
 * Loads a personal preferences profile from persistent store into memory.
 * Mirrors the ejbLoad method.
 * @param answerItemID  A <code>Integer</code> that represents the schoolresource id
 * @returns the profile of the schoolresource <code>SchoolResourceModel</code>
 * @throws  <code>SchoolResourceDAOFinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>SchoolResourceDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while loading the profile
 */
	public void load() throws SchoolResourceDAOSysException,
										 SchoolResourceDAOFinderException
	{
		selectSchoolResource();
	}

/**
 * Stores a personal preferences profile from memory into persistent store.
 * Mirrors the ejbStore method.
 * @param details the <code>SchoolResourceModel</code> of the schoolresource
 * @throws  <code>SchoolResourceDAOAppException</code> is thrown if the profile
 *          could not be persisted because of an user error in inputs.
 * @throws  <code>SchoolResourceDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>SchoolResourceDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while persisting the profile
 */
	public void store() throws SchoolResourceDAODBUpdateException,
								  SchoolResourceDAOAppException,
								  SchoolResourceDAOSysException
	{
		updateSchoolResource();
	}

/**
 * Removes a personal preferences profile from persistent store.
 * Mirrors the ejbRemove method.
 * @param schoolResourceID    a Integer that represents the schoolResourceID to be removed
 * @throws  <code>SchoolResourceDAODBUpdateException</code> is thrown if a
 *          recoverable error occurred while updating the database.
 * @throws  <code>SchoolResourceDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while removing the profile
 */
	public void remove() throws SchoolResourceDAODBUpdateException,
								SchoolResourceDAOSysException
	{
		deleteSchoolResource();
	}

/**
 * Finds a personal preferences profile from persistent store.
 * Mirrors the ejbFindByPrimaryKey method.
 * @returns a Integer which represents the primary key for this profile
 * @throws  <code>SchoolResourceDAOFinderException</code> is thrown if a
 *          profile was not found for the given user
 * @throws  <code>SchoolResourceDAOSysException</code> is thrown if an
 *          irrecoverable error occurred while accessing the database
 */
	public Integer findByPrimaryKey(Integer schoolResourceID) throws SchoolResourceDAOFinderException,
											 SchoolResourceDAOSysException
	{
		if (SchoolResourceIDExists(schoolResourceID))
			return (schoolResourceID);
		throw new SchoolResourceDAOFinderException("primary key not found :"+schoolResourceID);
	}

/**
 * Check to see if this ID is a unique constraint
 * violation on our database table (ie. there is already a id with the
 * value of schoolResourceID in the table).  If so, throw a
 * SchoolResourceDAOSysException .
 */
	private boolean SchoolResourceIDExists (Integer schoolResourceID) throws SchoolResourceDAOSysException
	{
		Statement stmt = null;
		ResultSet result = null;
		boolean returnValue = false;
		/* Create a new row in the database for this answeritem */
		String queryStr = "SELECT "
				+ InterpretSQL.encodeEndSign("SchoolResourseID")
				+ " FROM "
				+ InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
				+ " WHERE "
				+ InterpretSQL.encodeEndSign("SchoolResourseID")
				+ " = "
				+ InterpretSQL.encodeEndCol(schoolResourceID);

/*
		String queryStr ="SELECT \"SchoolResourceID \" FROM \"" +
						  DatabaseNames.SCHOOLRESOURCE_TABLE +
						 "\" WHERE \" SchoolResourceID\" = " + "'" + schoolResourceID.toString().trim()+ "'";
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
				this.schoolResourceID = new Integer(result.getInt(1));
				returnValue = true;
			}
		}
		catch(SQLException se)
		{
			throw new SchoolResourceDAOSysException(
						   "SQLException while checking for an"
						   + " existing SchoolResource - id -> " + schoolResourceID + " :\n" + se);
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
	private void insertSchoolResource() throws SchoolResourceDAOSysException,
							   SchoolResourceDAODupKeyException,
							   SchoolResourceDAODBUpdateException,
							   SchoolResourceDAOAppException
	{
	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  try{
		  /* Create a new row in the database for this answeritem */
		  String queryStr = "INSERT INTO "
				+ InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
				+ InterpretSQL.startInsert()
				+ InterpretSQL.encodeSign("Name")
				+ InterpretSQL.encodeSign("DocURL")
				+ InterpretSQL.encodeSign("SubjectID")
				+ InterpretSQL.encodeSign("StartDate")
				+ InterpretSQL.encodeSign("EndDate")
				+ InterpretSQL.encodeSign("Explain")
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
				+ InterpretSQL.encodeCol(name)
				+ InterpretSQL.encodeCol(docURL)
				+ InterpretSQL.encodeCol(subjectID)
				+ InterpretSQL.encodeCol(calendar.getESchoolDateString())
				+ InterpretSQL.encodeCol(calendar.getESchoolDateString())
				+ InterpretSQL.encodeCol(explain)
				+ InterpretSQL.encodeCol(allow)
				+ InterpretSQL.encodeCol(calendar.getESchoolDateString())
				+ InterpretSQL.encodeCol(calendar.getESchoolTimeString())
				+ InterpretSQL.encodeCol(createBy)
				+ InterpretSQL.encodeCol(calendar.getESchoolDateString())
				+ InterpretSQL.encodeCol(calendar.getESchoolTimeString())
				+ InterpretSQL.encodeEndCol(createBy)
				+ InterpretSQL.endInsert();

/*
		String queryStr = "INSERT INTO \"" + DatabaseNames.SCHOOLRESOURCE_TABLE +
						  "\"(\"SchoolResourceID\",\"Name\",\"DocURL\",\"SubjectID\",\"StartDate\","
						+ "\"EndDate\",\"Allow\",\"CreateDate\",\"CreateTime\",\"CreateBy\",\"LastmodifyDate\",\"LastmodifyTime\",\"LastmodifyBy\")"
						+ "VALUES ("
						+ details.getSchoolResourceID().toString().trim() + ","
						+ "'" + details.getName().trim() + "',"
						+ "'" + details.getDocURL().trim() + ","
						+ details.getSubjectID().toString().trim() + ","
						+ "'" + details.getStartDate().trim() + "',"
						+ "'" + details.getEndDate().trim() +"',"
						+ "'" + details.getAllow().trim() +"',"
						+ "'" + details.getCreateDate().trim() +"',"
						+ "'" + details.getCreateTime().trim() +"',"
						+ details.getCreateBy().toString().trim() +","
						+ "'" + details.getLastModifyDate().trim() +"',"
						+ "'" + details.getLastModifyTime().trim() +"',"
						+ details.getLastModifyBy().toString().trim() +")";
*/

		getDBConnection();
		stmt = dbConnection.createStatement();
		int resultCount = stmt.executeUpdate(queryStr);

		if ( resultCount != 1 )
		{
			throw new SchoolResourceDAODBUpdateException(
				"ERROR in SCHOOLRESOURCE_TABLE INSERT !! resultCount = " + resultCount);
		}else
		{
		  /**
		   * Search the primary Key
		   */
		  queryStr = "SELECT last_value FROM \"SchoolResours_SchoolResours_seq\"";
		  result = stmt.executeQuery(queryStr);

		  if(!result.next())
		  {
			  Debug.println("CAN NOT CREATE SchoolResource.#2");
			  throw new SchoolResourceDAODBUpdateException("CAN NOT CREATE SchoolResource.#2");
		  }else
		  {
			if(result.getInt(1) < 1)
			{
				this.schoolResourceID = new Integer(0);
				Debug.println("CAN NOT CREATE SchoolResource.#3");
				throw new SchoolResourceDAODBUpdateException("CAN NOT CREATE SchoolResource.#3");
			}else
			{
				this.schoolResourceID = new Integer(result.getInt(1));
			}
		  }
		}
	  }catch(SQLException ae)
	  {
	   Debug.println("SQLException while inserting new " + "schoolresourceID; id = " + schoolResourceID.toString() + " :\n" + ae);
	   throw new SchoolResourceDAOSysException( "SQLException while inserting new " + "schoolresourceID; id = " + schoolResourceID.toString() + " :\n" + ae);
	  }
	  finally
	  {
			closeResultSet(result);
			closeStatement(stmt);
			closeDBConnection();
	  }
	}

/**
 * select the method's description here.
 */
	private void selectSchoolResource() throws SchoolResourceDAOSysException,
																  SchoolResourceDAOFinderException
	{
		Statement stmt = null;
		ResultSet result = null;

		/* Create a new row in the database for this answeritem */
		String queryStr = "SELECT * FROM "
				+InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
				+ " WHERE "
				+InterpretSQL.encodeEndSign("SchoolResourseID")
				+" = "
				+InterpretSQL.encodeEndCol(schoolResourceID);

/*
		String queryStr = "SELECT "
					+ "\"SchoolResourceID\",\"Name\",\"DocURL\",\"SubjectID\",\"StartDate\","
						+ "\"EndDate\",\"Explain\",\"Allow\",\"CreateDate\",\"CreateTime\","
						+ "\"Createby\",\"LastmodifyDate\",\"LastmodifyTime\",\"LastmodifyBy\""
						+ " FROM " + DatabaseNames.SCHOOLRESOURCE_TABLE +
						  "\" WHERE \"SchoolResourceID\" = " + "'" + schoolResourceID.toString().trim() + "'";
*/
		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			result = stmt.executeQuery(queryStr);

			if ( !result.next() )
				throw new SchoolResourceDAOFinderException( "No record for primary key " + schoolResourceID);

			int i = 1;
			schoolResourceID = new Integer(result.getInt(i++));
			name = result.getString(i++);
			docURL = result.getString(i++);
			subjectID = new Integer(result.getInt(i++));
			startDate = result.getString(i++);
			endDate = result.getString(i++);
			allow = result.getString(i++);
			createDate = result.getString(i++);
			createTime = result.getString(i++);
			createBy = new Integer(result.getInt(i++));
			lastModifyDate = result.getString(i++);
			lastModifyTime = result.getString(i++);
			lastModifyBy = new Integer(result.getInt(i++));
			explain = result.getString(i++);
		}
		catch(SQLException ae)
		{
			ae.printStackTrace();
			throw new SchoolResourceDAOSysException("SQLException while getting " +
										 "schoolreourceID; id = " + schoolResourceID + " :\n" + ae);
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
	private void deleteSchoolResource () throws SchoolResourceDAODBUpdateException,
												SchoolResourceDAOSysException
	{
		/* Create a new row in the database for this answeritem */
		String queryStr = "DELETE FROM "
				+ InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
				+ " WHERE "
				+ InterpretSQL.encodeEndSign("SchoolResourseID")
				+" = "
				+ InterpretSQL.encodeEndCol(schoolResourceID);
/*
		String queryStr = "DELETE FROM \"" + DatabaseNames.SCHOOLRESOURCE_TABLE
						+ "\" WHERE \"SchoolResourceID\" = " + schoolResourceID.toString().trim();
*/
		Statement stmt = null;

		try
		{
			getDBConnection();
			stmt = dbConnection.createStatement();
			int resultCount = stmt.executeUpdate(queryStr);

			if (resultCount != 1)
				throw new SchoolResourceDAODBUpdateException
				("ERROR deleteing schoolresource from SCHOOLRESOURCE_TABLE!! resultCount = "+
				 resultCount);
		}
		catch(SQLException se)
		{
			throw new SchoolResourceDAOSysException("SQLException while removing " +
							"schoolresourceID; id = " + schoolResourceID + " :\n" + se);
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
	private void updateSchoolResource() throws SchoolResourceDAODBUpdateException,
														  SchoolResourceDAOAppException,
														  SchoolResourceDAOSysException
	{
		 Calendar calendar=Calendar.getInstance();
		/* Create a new row in the database for this answeritem */
		if(name==null) name="ws";
		if(docURL==null) docURL="ws";
		if(subjectID==null) subjectID=new Integer(1);
		if(endDate==null) endDate="ws";
		if(explain==null) explain="ws";

		String queryStr = "UPDATE "
		  + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
		  + " SET "
		  + InterpretSQL.encodeEndSign("Name")+"="
		  + InterpretSQL.encodeCol(name)
			  + InterpretSQL.encodeEndSign("DocURL")+"="
		  + InterpretSQL.encodeCol(docURL)
		  + InterpretSQL.encodeEndSign("SubjectID")+"="
		  + InterpretSQL.encodeCol(subjectID)
		  + InterpretSQL.encodeEndSign("Allow")+"="
		  + InterpretSQL.encodeCol(allow)
		  + InterpretSQL.encodeEndSign("Explain")+"="
		  + InterpretSQL.encodeCol(explain)
		  + InterpretSQL.encodeEndSign("LastModifyDate")+"="
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeEndSign("LastModifyTime")+"="
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeEndSign("LastModifyBy")+"="
		  + InterpretSQL.encodeEndCol(lastModifyBy)
		  + " WHERE "
		  + InterpretSQL.encodeEndSign("SchoolResourseID")
		  +" = "
		  + InterpretSQL.encodeEndCol(schoolResourceID);

/*
		String queryStr = "UPDATE \"" + DatabaseNames.SCHOOLRESOURCE_TABLE + "\" SET "
						  + "\"Name\" = " + "'" + details.getName().trim() + "',"
						  + "\"DocURL\" = " + "'" + details.getDocURL().trim() + "',"
						  + "\"SubjectID\" = " + details.getSubjectID().toString().trim() + ","
						  + "\"Startdate\" = " + "'" + details.getStartDate().trim() + "',"
						  + "\"EndDate\" = " + "'" + details.getEndDate().trim() + "',"
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
				throw new SchoolResourceDAODBUpdateException
				("ERROR updating schoolresource in SCHOOLRESOURCE_TABLE!! resultCount = " +
				 resultCount);
		}
		catch(SQLException se)
		{
			throw new SchoolResourceDAOSysException("SQLException while updating " +
					 "schoolresource; id = " +  schoolResourceID + " :\n" + se);
		}
		finally
		{
			closeStatement(stmt);
			closeDBConnection();
		}
	}
}