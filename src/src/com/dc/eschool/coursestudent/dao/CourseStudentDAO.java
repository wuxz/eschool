package com.dc.eschool.coursestudent.dao;

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

import com.dc.eschool.coursestudent.model.CourseStudentModel;
import com.dc.eschool.coursestudent.exceptions.CSDAOAppException;
import com.dc.eschool.coursestudent.exceptions.CSDAODBUpException;
import com.dc.eschool.coursestudent.exceptions.CSDAODuKeyException;
import com.dc.eschool.coursestudent.exceptions.CSDAOFindException;
import com.dc.eschool.coursestudent.exceptions.CSDAOSysException;

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
 * This class encapsulates all the JDBC calls made by the CourseStudentEB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of EcourseStudentEB is
 * implemented here.
 */
public class CourseStudentDAO extends GeneralDAO
{
      private CourseStudentModel csm=new CourseStudentModel();

     /**
       *  Constructor of CourseStudentDAO
       *  @exception DAOException
       */
      public CourseStudentDAO() throws DAOException
      {
	  super();
      }

      /**
       * The function of this method will insert a record into CourseStudent Table
       * @exception CSDAOSysException
       * @exception CSDAODuKeyException
       * @exception CourseStudentDAOUpException
       * @exception CSDAOAppException
       */
      public void create() throws CSDAOSysException,
				  CSDAODuKeyException,
				  CSDAODBUpException,
				  CSDAOAppException
      {
	  insertCourseStudent();
      }

      /**
       * The function of this method is loading a record
       *
       * @exception CSDAOSysException
       * @exception CSDAOFindException
       */
      public void load() throws CSDAOSysException,
				CSDAOFindException
      {
	  selectCourseStudent();
      }

      /**
       * The function of this method will modify the record
       */
      public void store() throws CSDAODBUpException,
				 CSDAOAppException,
				 CSDAOSysException
      {
	  updateCourseStudent();
      }

      /**
       * The function of this method will delete a record from CourseStudent Table
       * @exception CSDAODBUpException
       * @exception CSDAOSysException
       */
      public void remove() throws CSDAODBUpException,
				  CSDAOSysException
      {
	  deleteCourseStudent();
      }

      /**
       * Search by primary key from coursestudent table
       * @param courseStudentID the <code>Integer</code> for this instance
       * @return Integer the primarykey
       * @exception CSDAOFindException
       * @exception CSDAOSysException
       */
      public Integer findByPrimaryKey(Integer primKey) throws
					      CSDAOFindException,
					      CSDAOSysException
      {
	  return courseStudentExists(primKey);
      }

      /**
       * Search the CourseID and Student
       * @return boolean
       */
      public boolean isInsert()
      {

          Statement stmt = null;
          ResultSet result = null;
	  boolean returnValue = false;
          String queryStr = "SELECT * FROM "
                          + InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE)
                          + " WHERE "
                          + InterpretSQL.encodeEndSign("CourseID")
                          + " = "
                          + InterpretSQL.encodeEndCol(csm.getCourseID())
			  + " AND "
			  + InterpretSQL.encodeEndSign("Student")
			  + " = "
			  + InterpretSQL.encodeEndCol(csm.getStudent());

          Debug.println("queryString is: "+ queryStr);

          try
          {
              getDBConnection();
              stmt = dbConnection.createStatement();
              result = stmt.executeQuery(queryStr);

              if ( result.next()) returnValue=true;

          } catch(SQLException ae)
          {
              System.out.println(ae);
          } finally
          {
              closeResultSet(result);
              closeStatement(stmt);
              closeDBConnection();
          }
	  return returnValue;
      }

      /**
       * Search data by CourseStudentID
       * @return ture if the coursestudents is in the coursestudent Table
       * @exception CSDAOSysException
       */
      private Integer courseStudentExists (Integer primKey) throws CSDAOSysException
      {
	  Statement stmt = null;
	  ResultSet result = null;
	  Integer returnValue = new Integer(0);
	  String queryStr = "SELECT "
			  + InterpretSQL.encodeEndSign("CourseStudentID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("CourseStudentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(primKey);

	  Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);
	      if (result.next() )
		  returnValue = new Integer(result.getInt(1));
	  } catch(SQLException se)
	  {
	      throw new CSDAOSysException(
			     "SQLException while checking for an"
			     + " existing coursestudent - id -> " + primKey + " :\n" + se);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
	  return returnValue;
      }

      /**
       * insert data to CourseStudent Table
       * @exception CSDAOSysException
       * @exception CSDAODuKeyException
       * @exception CSDAOAppException
       */
      private void insertCourseStudent() throws
				   CSDAOSysException,
				   CSDAODuKeyException,
				   CSDAODBUpException,
				   CSDAOAppException
      {
	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "INSERT INTO "
	      + InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE)
	      + InterpretSQL.startInsert()
	      + InterpretSQL.encodeSign("Student")
	      + InterpretSQL.encodeSign("CourseID")
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
	      + InterpretSQL.encodeCol(csm.getStudent())
	      + InterpretSQL.encodeCol(csm.getCourseID())
	      + InterpretSQL.encodeCol("ÔÊÐí")
	      + InterpretSQL.encodeCol(calendar.getESchoolDateString())
	      +  InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	      + InterpretSQL.encodeCol(csm.getCreateBy())
	      + InterpretSQL.encodeCol(calendar.getESchoolDateString())
	      + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	      + InterpretSQL.encodeEndCol(csm.getLastModifyBy())
	      + InterpretSQL.endInsert();

	  Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      /**
	       * Insert into Users Table except for CourseStudentID
	       */
	      if (!isInsert())
	      {
		getDBConnection();
		stmt = dbConnection.createStatement();
		int resultCount = stmt.executeUpdate(queryStr);

		/**
		 * if inserting is successful then return CourseStudentID
		 * else throw the exception
		 */
		if ( resultCount!=1)
		{
		    throw new CSDAODBUpException(
			"ERROR in COURSESTUDENT_TABLE INSERT !! resultCount = " +
				       resultCount);
		}else
		{
		  queryStr = "SELECT last_value FROM \"CourseStudent_CourseStudent_seq\"";
		  result = stmt.executeQuery(queryStr);

		  if(!result.next())
		  {
		    Debug.println("CAN NOT CREATE COURSESTUDENT.#2");
		    throw new CSDAODBUpException("CAN NOT CREATE COURSESTUDENT.#2");
		  }else
		  {
		    if(result.getInt(1) < 1)
		    {
		      csm.setCourseStudentID(new Integer(0));
		      Debug.println("CAN NOT CREATE COURSESTUDENT.#3");
		      throw new CSDAODBUpException("CAN NOT CREATE COURSESTUDENT.#3");
		    }else
		    {
		      csm.setCourseStudentID(new Integer(result.getInt(1)));
		    }
		  }
		}
	      }
	  } catch(SQLException ae)
	  {
	      throw new CSDAOSysException(
			  "SQLException while inserting new " +
			  "courseStudentID; id = " + csm.getCourseStudentID() + " :\n" + ae);
	  }
	  finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Search data by UserID
       * @exception CSDAOSysException
       * @exception CSDAOFindException
       */
      private void selectCourseStudent() throws CSDAOSysException,
						CSDAOFindException
      {

	  Statement stmt = null;
	  ResultSet result = null;
	  String queryStr = "SELECT * FROM "
			  +InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE)
			  +" WHERE "
			  +InterpretSQL.encodeEndSign("CourseStudentID")
			  +" = "
			  +InterpretSQL.encodeEndCol(csm.getCourseStudentID()) ;

	  Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);
	      if ( !result.next() )
		  throw new CSDAOFindException(
		    "No record for primary key " + csm.getCourseStudentID());

	      int i = 1;
	      csm.setCourseStudentID(new Integer(result.getInt(i++)));
	      csm.setStudent(new Integer(result.getInt(i++)));
	      csm.setCourseID(new Integer(result.getInt(i++)));
	      csm.setAllow(result.getString(i++));
	      csm.setCreateDate(result.getString(i++));
	      csm.setCreateTime(result.getString(i++));
	      csm.setCreateBy(new Integer(result.getInt(i++)));
	      csm.setLastModifyDate(result.getString(i++));
	      csm.setLastModifyTime(result.getString(i++));
	      csm.setLastModifyBy(new Integer(result.getInt(i++)));

	  } catch(SQLException ae)
	  {
	      throw new CSDAOSysException("SQLException while getting " +
			"coursestudent; id = " + csm.getCourseStudentID() + " :\n" + ae);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Remove data from CourseStudent Table by UserID
       * @exception CSDAODBUpException
       * @exception CSDAOSysException
       */
      private void deleteCourseStudent () throws CSDAODBUpException,
						 CSDAOSysException
      {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("CourseStudentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(csm.getCourseStudentID());

	  Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1)
		  throw new CSDAODBUpException
		  ("ERROR deleteing account from COURSESTUDENT_TABLE!! resultCount = "+
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new CSDAOSysException("SQLException while removing " +
			      "coursestudent; id = " + csm.getCourseStudentID() + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Modefy the current data
       * @exception CSDAODBUpException
       * @exception CSDAOAppException
       * @exception CSDAOSysException
       */
      private void updateCourseStudent() throws
					   CSDAODBUpException,
					   CSDAOAppException,
					   CSDAOSysException
      {
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("Student")
			  + " = "
			  + InterpretSQL.encodeCol(csm.getStudent())
			  + InterpretSQL.encodeEndSign("CourseID")
			  + " = "
			  + InterpretSQL.encodeCol(csm.getCourseID())
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  + " = "
			  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  + InterpretSQL.encodeEndSign("LastModifyTime")
			  + " = "
			  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  + InterpretSQL.encodeEndSign("LastModifyBy")
			  + " = "
			  + InterpretSQL.encodeEndCol(csm.getLastModifyBy())
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("CourseStudentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(csm.getCourseStudentID());

	  Debug.println("queryString is: "+ queryStr);

	  Statement stmt = null;
	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1)
		  throw new CSDAODBUpException
		  ("ERROR updating account in COURSESTUDENT_TABLE!! resultCount = " +
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new CSDAOSysException("SQLException while updating " +
		       "CourseStudent; id = "  + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

    // get and set methods for the instance variables
    public void setCsm(CourseStudentModel csm)
    {
      this.csm = csm;
    }
    public CourseStudentModel getCsm()
    {
      return csm;
    }

}