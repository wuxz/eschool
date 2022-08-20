package com.dc.eschool.studentexamcontent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAOAppException;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAODBUpException;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAODuKeyException;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAOFindException;
import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAOSysException;
import com.dc.eschool.studentexamcontent.model.StudentExamContentModel;

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
 * @author Eric
 * @version 1.0
 */

/**
 * This studentexamcontent encapsulates all the JDBC calls made by the StudentExamContentEB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of EstudentexamcontentEB is
 * implemented here.
 */
public class StudentExamContentDAO extends GeneralDAO
{
	  private StudentExamContentModel secm= new StudentExamContentModel();

	 /**
	   *  Constructor of StudentExamContentDAO
	   *  @exception DAOException
	   */
	  public StudentExamContentDAO() throws DAOException
	  {
	  super();
	  }

	  /**
	   * The function of this method will insert a record into StudentExamContent Table
	   * @exception StudentExamContentDAOSysException
	   * @exception StudentExamContentDAODuKeyException
	   * @exception StudentExamContentDAOUpException
	   * @exception StudentExamContentDAOAppException
	   */
	  public void create() throws StudentExamContentDAOSysException,
				  StudentExamContentDAODuKeyException,
				  StudentExamContentDAODBUpException,
				  StudentExamContentDAOAppException
	  {
	  insertStudentExamContent();
	  }

	  /**
	   * The function of this method is loading a record
	   * @exception StudentExamContentDAOSysException
	   * @exception StudentExamContentDAOFindException
	   */
	  public void load() throws StudentExamContentDAOSysException,
				StudentExamContentDAOFindException
	  {
	  selectStudentExamContent();
	  }

	  /**
	   * The function of this method will modify the record
	   */
	  public void store() throws StudentExamContentDAODBUpException,
				 StudentExamContentDAOAppException,
				 StudentExamContentDAOSysException
	  {
	  updateStudentExamContent();
	  }

	  /**
	   * The function of this method will delete a record from StudentExamContent Table
	   * @exception StudentExamContentDAODBUpException
	   * @exception StudentExamContentDAOSysException
	   */
	  public void remove() throws StudentExamContentDAODBUpException,
				  StudentExamContentDAOSysException
	  {
	  deleteStudentExamContent();
	  }

	  /**
	   * Search by primary key from studentexamcontent table
	   * @param studentexamcontentID the <code>Integer</code> for this instance
	   * @return Integer the primarykey
	   * @exception StudentExamContentDAOFindException
	   * @exception StudentExamContentDAOSysException
	   */
	  public Integer findByPrimaryKey(Integer primKey) throws StudentExamContentDAOFindException,
								  StudentExamContentDAOSysException
	  {
	  return studentexamcontentExists(primKey);
	  }

	  /**
	   * Search data by StudentExamContentID
	   * @return ture if the studentexamcontents is in the studentexamcontent Table
	   * @exception StudentExamContentDAOSysException
	   */
	  private Integer studentexamcontentExists (Integer primKey) throws StudentExamContentDAOSysException
	  {
	  Statement stmt = null;
	  ResultSet result = null;
	  Integer returnValue = new Integer(0);
	  String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("StudentExamContentID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("StudentExamContentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(primKey);

	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  result = stmt.executeQuery(queryStr);
		  if ( result.next() )
		  returnValue = new Integer(result.getInt(1));
	  } catch(SQLException se)
	  {
		  throw new StudentExamContentDAOSysException(
				 "SQLException while checking for an"
				 + " existing studentexamcontent - id -> " + primKey + " :\n" + se);
	  } finally
	  {
		  closeResultSet(result);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  return returnValue;
	  }

	  /**
	   * insert data to StudentExamContent Table
	   * @exception StudentExamContentDAOSysException
	   * @exception StudentExamContentDAODuKeyException
	   * @exception StudentExamContentDAOAppException
	   */
	  private void insertStudentExamContent() throws
				   StudentExamContentDAOSysException,
				   StudentExamContentDAODuKeyException,
				   StudentExamContentDAODBUpException,
				   StudentExamContentDAOAppException
	  {

	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "INSERT INTO "
		  + InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE)
		  + InterpretSQL.startInsert()
		  + InterpretSQL.encodeSign("UserID")
			  + InterpretSQL.encodeSign("CourseID")
			  + InterpretSQL.encodeSign("ContentID")
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
		  + InterpretSQL.encodeCol(secm.getUserID())
			  + InterpretSQL.encodeCol(secm.getCourseID())
			  + InterpretSQL.encodeCol(secm.getContentID())
		  + InterpretSQL.encodeCol("ÔÊÐí")
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeCol(secm.getCreateBy())
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeEndCol(secm.getLastModifyBy())
		  + InterpretSQL.endInsert();

	  try
	  {
		  /**
		   * Insert into Users Table except for StudentExamContentID
		   */

		getDBConnection();
		stmt = dbConnection.createStatement();
		int resultCount = stmt.executeUpdate(queryStr);

		/**
		 * if inserting is successful then return StudentExamContentID
		 * else throw the exception
		 */
		if ( resultCount!=1)
		{
			throw new StudentExamContentDAODBUpException(
			"ERROR in STUDENTEXAMCONTENT_TABLE INSERT !! resultCount = " +
					   resultCount);
		}else
		{
		  queryStr = "SELECT last_value FROM \"StudentExamCo_StudentExamCo_seq\"";
		  result = stmt.executeQuery(queryStr);

		  /**
		   * get the primary key
		   */
			if(!result.next())
			{
			Debug.println("CAN NOT CREATE STUDENTEXAMCONTENT.#2");
			throw new StudentExamContentDAODBUpException("CAN NOT CREATE STUDENTEXAMCONTENT.#2");
			}else
			{
			if(result.getInt(1) < 1)
			{
				Debug.println("CAN NOT CREATE STUDENTEXAMCONTENT.#3");
				throw new StudentExamContentDAODBUpException("CAN NOT CREATE STUDENTEXAMCONTENT.#3");
			}else
			{
				secm.setStudentExamContentID(new Integer(result.getInt(1)));
			}
			}
		}

	  } catch(SQLException ae)
	  {
		  throw new StudentExamContentDAOSysException(
			  "SQLException while inserting new " +
			  "studentexamcontentID; id = " + secm.getStudentExamContentID() + " :\n" + ae);
	  }
	  finally
	  {
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }

	  /**
	   * Search data by UserID
	   *
	   * @exception StudentExamContentDAOSysException
	   * @exception StudentExamContentDAOFindException
	   */
	  private void selectStudentExamContent() throws
					   StudentExamContentDAOSysException,
					   StudentExamContentDAOFindException
	  {

	  Statement stmt = null;
	  ResultSet result = null;

	  String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("StudentExamContentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(secm.getStudentExamContentID()) ;

	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  result = stmt.executeQuery(queryStr);

		  if ( !result.next() )
		  throw new StudentExamContentDAOFindException(
					"No record for primary key " + secm.getStudentExamContentID() );

		  int i = 1;
		  secm.setStudentExamContentID(new Integer(result.getInt(i++)));
			  secm.setUserID(new Integer(result.getInt(i++)));
		  secm.setCourseID(new Integer(result.getInt(i++)));
			  secm.setContentID(new Integer(result.getInt(i++)));
		  secm.setAllow(result.getString(i++));
		  secm.setCreateDate(result.getString(i++));
		  secm.setCreateTime(result.getString(i++));
		  secm.setCreateBy(new Integer(result.getInt(i++)));
		  secm.setLastModifyDate(result.getString(i++));
		  secm.setLastModifyTime(result.getString(i++));
		  secm.setLastModifyBy(new Integer(result.getInt(i++)));

	  } catch(SQLException ae)
	  {
		  throw new StudentExamContentDAOSysException("SQLException while getting " +
			"studentexamcontent; id = " + secm.getStudentExamContentID()  + " :\n" + ae);
	  } finally
	  {
		  closeResultSet(result);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }

	  /**
	   * Remove data from StudentExamContent Table by UserID
	   * @exception StudentExamContentDAODBUpException
	   * @exception StudentExamContentDAOSysException
	   */
	  private void deleteStudentExamContent () throws StudentExamContentDAODBUpException,
					   StudentExamContentDAOSysException
	  {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("StudentExamContentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(secm.getStudentExamContentID());

	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  int resultCount = stmt.executeUpdate(queryStr);
		  if (resultCount != 1) throw new StudentExamContentDAODBUpException
		  ("ERROR deleteing account from STUDENTEXAMCONTENT_TABLE!! resultCount = "+
		   resultCount);
	  } catch(SQLException se)
	  {
		  throw new StudentExamContentDAOSysException("SQLException while removing " +
				  "studentexamcontent; id = " + secm.getStudentExamContentID()  + " :\n" + se);
	  } finally
	  {
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }



	  /**
	   * Modefy the current data
	   * @exception StudentExamContentDAODBUpException
	   * @exception StudentExamContentDAOAppException
	   * @exception StudentExamContentDAOSysException
	   */
	  private void updateStudentExamContent() throws
					   StudentExamContentDAODBUpException,
					   StudentExamContentDAOAppException,
					   StudentExamContentDAOSysException
	  {
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("UserID")
			  + " = "
			  + InterpretSQL.encodeCol(secm.getUserID())
						  + InterpretSQL.encodeEndSign("CourseID")
			  + " = "
			  + InterpretSQL.encodeCol(secm.getCourseID())
						  + InterpretSQL.encodeEndSign("ContentID")
			  + " = "
			  + InterpretSQL.encodeCol(secm.getContentID())
			  + InterpretSQL.encodeEndSign("Allow")
			  + " = "
			  + InterpretSQL.encodeCol(secm.getAllow())
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  +  " = "
			  +InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  +InterpretSQL.encodeEndSign("LastModifyTime")
			  +" = "
			  +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  +InterpretSQL.encodeEndSign("LastModifyBy")
			  +" = "
			  +InterpretSQL.encodeEndCol(secm.getLastModifyBy())
			  +" WHERE "
			  +InterpretSQL.encodeEndSign("StudentExamContentID")
			  +" = "
			  +InterpretSQL.encodeEndCol(secm.getStudentExamContentID());

	  Statement stmt = null;
	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  int resultCount = stmt.executeUpdate(queryStr);
		  if (resultCount != 1)
		  throw new StudentExamContentDAODBUpException
		  ("ERROR updating account in STUDENTEXAMCONTENT_TABLE!! resultCount = " +
		   resultCount);
	  } catch(SQLException se)
	  {
		  throw new StudentExamContentDAOSysException("SQLException while updating " +
			   "StudentExamContent; id = "  + " :\n" + se);
	  } finally
	  {
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }

	// get and set methods for the instance variables
	public void setSecm(StudentExamContentModel secm)
	{
	  this.secm = secm;
	}
	public StudentExamContentModel getSecm()
	{
	  return secm;
	}
}