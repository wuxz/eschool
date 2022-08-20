package com.dc.eschool.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dc.eschool.course.exceptions.CourseDAOAppException;
import com.dc.eschool.course.exceptions.CourseDAODBUpException;
import com.dc.eschool.course.exceptions.CourseDAODuKeyException;
import com.dc.eschool.course.exceptions.CourseDAOFindException;
import com.dc.eschool.course.exceptions.CourseDAOSysException;
import com.dc.eschool.course.model.CourseModel;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.util.Calendar;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.DatabaseNames;
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
 * This class encapsulates all the JDBC calls made by the CourseEB.
 * Actual logic of inserting/fetching/updating/deleting the data in
 * relational database tables to mirror the state of EcourseEB is
 * implemented here.
 */
public class CourseDAO extends GeneralDAO
{
      private CourseModel cm= new CourseModel();

     /**
       *  Constructor of CourseDAO
       *  @exception DAOException
       */
      public CourseDAO() throws DAOException
      {
          super();
      }

      /**
       * The function of this method will insert a record into Course Table     *
       * @exception CourseDAOSysException
       * @exception CourseDAODuKeyException
       * @exception CourseDAOUpException
       * @exception CourseDAOAppException
       */
      public void create() throws CourseDAOSysException,
                                  CourseDAODuKeyException,
                                  CourseDAODBUpException,
                                  CourseDAOAppException
      {
          insertCourse();
      }

      /**
       * The function of this method is loading a record     *
       * @exception CourseDAOSysException
       * @exception CourseDAOFindException
       */
      public void load() throws CourseDAOSysException,
                                CourseDAOFindException
      {
          selectCourse();
      }

      /**
       * The function of this method will modify the record
       * @exception CourseDAODBUpException
       * @exception CourseDAOAppException
       * @exception CourseDAOSysException
       */
      public void store() throws CourseDAODBUpException,
                                 CourseDAOAppException,
                                 CourseDAOSysException
      {
          updateCourse();
      }

      /**
       * The function of this method will delete a record from Course Table
       * @exception CourseDAODBUpException
       * @exception CourseDAOSysException
       */
      public void remove() throws CourseDAODBUpException,
                                  CourseDAOSysException
      {
          deleteCourse();
      }

      /**
       * Search by primary key from course table
       * @param courseID the <code>Integer</code> for this instance
       * @return Integer the primarykey
       * @exception CourseDAOFindException
       * @exception CourseDAOSysException
       */
      public Integer findByPrimaryKey(Integer primKey) throws
                                              CourseDAOFindException,
                                              CourseDAOSysException
      {
          return courseExists(primKey);
      }

      /**
       * Search data by CourseID
       * @return Integer if the courses is in the course Table
       * @exception CourseDAOSysException
       */
      private Integer courseExists (Integer primKey) throws CourseDAOSysException
      {
          Statement stmt = null;
          ResultSet result = null;
          Integer returnValue = new Integer(0);
          String queryStr = "SELECT "
                          + InterpretSQL.encodeEndSign("CourseID")
                          + " FROM "
                          + InterpretSQL.encodeEndSign(DatabaseNames.COURSE_TABLE)
                          + " WHERE "
                          + InterpretSQL.encodeEndSign("CourseID")
                          + " = "
                          + InterpretSQL.encodeEndCol(primKey);
          //Debug.println("queryString is: "+ queryStr);

          try
          {
              getDBConnection();
              stmt = dbConnection.createStatement();
              result = stmt.executeQuery(queryStr);
              if (result.next() )
		  returnValue= new Integer(result.getInt(1));
          } catch(SQLException se)
          {
              throw new CourseDAOSysException("SQLException while checking for an"
                                                + " existing course - id -> " + primKey + " :\n" + se);
          } finally
          {
              closeResultSet(result);
              closeStatement(stmt);
              closeDBConnection();
          }
          return returnValue;
      }

      /**
       * insert data into Course Table     *
       * @exception CourseDAOSysException
       * @exception CourseDAODuKeyException
       * @exception CourseDAOAppException
       */
      private void insertCourse() throws
                                   CourseDAOSysException,
                                   CourseDAODuKeyException,
                                   CourseDAODBUpException,
                                   CourseDAOAppException
      {

          Statement stmt = null;
          ResultSet result = null;
          Calendar calendar = Calendar.getInstance();

          String queryStr = "INSERT INTO "
              + InterpretSQL.encodeEndSign(DatabaseNames.COURSE_TABLE)
              + InterpretSQL.startInsert()
              + InterpretSQL.encodeSign("CourseName")
              + InterpretSQL.encodeSign("Teacher")
              + InterpretSQL.encodeSign("StartDate")
              + InterpretSQL.encodeSign("EndDate")
              + InterpretSQL.encodeSign("Info")
              + InterpretSQL.encodeSign("State")
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
              + InterpretSQL.encodeCol(cm.getCourseName())
              + InterpretSQL.encodeCol(cm.getTeacher())
              + InterpretSQL.encodeCol(cm.getStartDate())
              + InterpretSQL.encodeCol(cm.getEndDate())
              + InterpretSQL.encodeCol(cm.getInfo())
              + InterpretSQL.encodeCol(cm.getState())
              + InterpretSQL.encodeCol("ÔÊÐí")
              + InterpretSQL.encodeCol(calendar.getESchoolDateString())
              + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
              + InterpretSQL.encodeCol(cm.getCreateBy())
              + InterpretSQL.encodeCol(calendar.getESchoolDateString())
              + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
              + InterpretSQL.encodeEndCol(cm.getLastModifyBy())
              + InterpretSQL.endInsert();

          //Debug.println("queryString is: "+ queryStr);

          try
          {
              /**
               * Insert into Users Table except for CourseID
               */
              getDBConnection();
              stmt = dbConnection.createStatement();

              if(isCourseNameUsed()){
		int resultCount = stmt.executeUpdate(queryStr);

		/**
		 * if inserting is successful then return CourseID
		 * else throw the exception
		 */
		if ( resultCount!=1)
		{
		    throw new CourseDAODBUpException("ERROR in COURSE_TABLE INSERT !! resultCount = " + resultCount);
		}else
		{
		    queryStr = "SELECT last_value FROM \"Course_CourseID_seq\"";
		    result = stmt.executeQuery(queryStr);
		    if(!result.next())
		    {
			Debug.println("CAN NOT CREATE COURSE.#2");
			throw new CourseDAODBUpException("CAN NOT CREATE COURSE.#2");
		    }else
		    {
			if(result.getInt(1) < 1)
			{
			    Debug.println("CAN NOT CREATE COURSE.#3");
			    throw new CourseDAODBUpException("CAN NOT CREATE COURSE.#3");
			}else
			{
			    cm.setCourseID(new Integer(result.getInt(1)));
			}
		    }
		}
              }
          } catch(SQLException ae)
          {
              throw new CourseDAOSysException("SQLException while inserting new " +
                          "courseID; id = " + cm.getCourseID() + " :\n" + ae);
          }
          finally
          {
              closeStatement(stmt);
              closeDBConnection();
          }
      }

      /**
       * Search the CourseName
       * @return boolean
       */

      /**
       * Search data by UserID     *
       * @exception CourseDAOSysException
       * @exception CourseDAOFindException
       */
      private void selectCourse() throws
                                           CourseDAOSysException,
                                           CourseDAOFindException
      {

          Statement stmt = null;
          ResultSet result = null;
          String queryStr = "SELECT * FROM "
                          + InterpretSQL.encodeEndSign(DatabaseNames.COURSE_TABLE)
                          + " WHERE "
                          + InterpretSQL.encodeEndSign("CourseID")
                          + " = "
                          + InterpretSQL.encodeEndCol(cm.getCourseID()) ;

          //Debug.println("queryString is: "+ queryStr);

          try
          {
              getDBConnection();
              stmt = dbConnection.createStatement();
              result = stmt.executeQuery(queryStr);

              if ( !result.next()) throw new CourseDAOFindException(
                                    "No record for primary key " + cm.getCourseID());

              int i = 1;
              cm.setCourseID(new Integer(result.getInt(i++)));
              cm.setCourseName(result.getString(i++));
              cm.setTeacher(new Integer(result.getInt(i++)));
              cm.setStartDate(result.getString(i++));
              cm.setEndDate(result.getString(i++));
              cm.setInfo(result.getString(i++));
              cm.setState(result.getString(i++));
              cm.setAllow(result.getString(i++));
              cm.setCreateDate(result.getString(i++));
              cm.setCreateTime(result.getString(i++));
              cm.setCreateBy(new Integer(result.getInt(i++)));
              cm.setLastModifyDate(result.getString(i++));
              cm.setLastModifyTime(result.getString(i++));
              cm.setLastModifyBy(new Integer(result.getInt(i++)));

          } catch(SQLException ae)
          {
              throw new CourseDAOSysException("SQLException while getting " +
                        "course; id = " + cm.getCourseID() + " :\n" + ae);
          } finally
          {
              closeResultSet(result);
              closeStatement(stmt);
              closeDBConnection();
          }
      }

      /**
       * Remove data from Course Table by UserID
       * @exception CourseDAODBUpException
       * @exception CourseDAOSysException
       */
      private void deleteCourse () throws  CourseDAODBUpException,
                                           CourseDAOSysException
      {
          Statement stmt = null;
          String queryStr = "DELETE FROM "
                          + InterpretSQL.encodeEndSign(DatabaseNames.COURSE_TABLE)
                          + " WHERE "
                          + InterpretSQL.encodeEndSign("CourseID")
                          + " = "
                          + InterpretSQL.encodeEndCol(cm.getCourseID());

          //Debug.println("queryString is: "+ queryStr);

          try
          {
              getDBConnection();
              stmt = dbConnection.createStatement();
              int resultCount = stmt.executeUpdate(queryStr);
              if (resultCount != 1)
              {
                  throw new CourseDAODBUpException("ERROR deleteing account from COURSE_TABLE!! resultCount = "
                                                     + resultCount);
              }
          } catch(SQLException se)
          {
              throw new CourseDAOSysException("SQLException while removing " +
                              "course; id = " + cm.getCourseID() + " :\n" + se);
          } finally
          {
              closeStatement(stmt);
              closeDBConnection();
          }
      }

      /**
       * Modify the current data
       * @exception CourseDAODBUpException
       * @exception CourseDAOAppException
       * @exception CourseDAOSysException
       */
      private void updateCourse() throws   CourseDAODBUpException,
                                           CourseDAOAppException,
                                           CourseDAOSysException
      {
          Calendar calendar=Calendar.getInstance();
          String queryStr = "UPDATE "
                          + InterpretSQL.encodeEndSign(DatabaseNames.COURSE_TABLE)
                          + " SET "
                          + InterpretSQL.encodeEndSign("CourseName")
                          + " = "
                          + InterpretSQL.encodeCol(cm.getCourseName())
                          + InterpretSQL.encodeEndSign("Teacher")
                          + " = "
                          + InterpretSQL.encodeCol(cm.getTeacher())
                          + InterpretSQL.encodeEndSign("StartDate")
                          + " = "
                          + InterpretSQL.encodeCol(cm.getStartDate())
                          + InterpretSQL.encodeEndSign("EndDate")
                          + " = "
                          + InterpretSQL.encodeCol(cm.getEndDate())
                          + InterpretSQL.encodeEndSign("Info")
                          + " = "
                          + InterpretSQL.encodeCol(cm.getInfo())
                          + InterpretSQL.encodeEndSign("State")
                          + " = "
                          + InterpretSQL.encodeCol(cm.getState())
                          + InterpretSQL.encodeEndSign("LastModifyDate")
                          + " = "
                          + InterpretSQL.encodeCol(calendar.getESchoolDateString())
                          + InterpretSQL.encodeEndSign("LastModifyTime")
                          + " = "
                          + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
                          + InterpretSQL.encodeEndSign("LastModifyBy")
                          + " = "
                          + InterpretSQL.encodeEndCol(cm.getLastModifyBy())
                          + " WHERE "
                          + InterpretSQL.encodeEndSign("CourseID")
                          + " = "
                          + InterpretSQL.encodeEndCol(cm.getCourseID());

          //Debug.println("queryString is: "+ queryStr);

          Statement stmt = null;
          try
          {
              getDBConnection();
              stmt = dbConnection.createStatement();
              int resultCount = stmt.executeUpdate(queryStr);
              if (resultCount != 1)
              {
                  throw new CourseDAODBUpException
                  ("ERROR updating account in COURSE_TABLE!! resultCount = " +
                   resultCount);
              }
          } catch(SQLException se)
          {
              throw new CourseDAOSysException("SQLException while updating " +
                       "Course; id = "  + " :\n" + se);
          } finally
          {
              closeStatement(stmt);
              closeDBConnection();
          }
      }

      /**
	   * verify whthere the loginname is used
	   * @exception UDAOSysException
	   * @exception UDAOFindException
	   */
	  private boolean isCourseNameUsed()
	  {

	  Statement stmt = null;
	  ResultSet result = null;
	  boolean returnValue=false;

	  String queryStr ="SELECT * FROM "
				+ InterpretSQL.encodeEndSign(DatabaseNames.COURSE_TABLE)
				+ " WHERE "
				+ InterpretSQL.encodeEndSign("CourseName")
				+ " = "
				+ InterpretSQL.encodeEndCol(cm.getCourseName());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  result = stmt.executeQuery(queryStr);
		  if ( result.next() ){
		returnValue=true;
		  }
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

    // get and set methods for the instance variables
      public void setCm(CourseModel cm)
      {
	this.cm = cm;
      }
      public CourseModel getCm()
      {
	return cm;
      }
}