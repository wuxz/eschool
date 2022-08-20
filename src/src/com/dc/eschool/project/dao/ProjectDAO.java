package com.dc.eschool.project.dao;

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

import com.dc.eschool.project.exceptions.ProjectDAOAppException;
import com.dc.eschool.project.exceptions.ProjectDAODBUpException;
import com.dc.eschool.project.exceptions.ProjectDAODuKeyException;
import com.dc.eschool.project.exceptions.ProjectDAOFindException;
import com.dc.eschool.project.exceptions.ProjectDAOSysException;
import com.dc.eschool.project.model.ProjectModel;

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
 * This project encapsulates all the JDBC calls made by the ProjectEB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of EprojectEB is
 * implemented here.
 */
public class ProjectDAO extends GeneralDAO
{
    protected ProjectModel pm= new ProjectModel();

     /**
       *  Constructor of ProjectDAO
       *  @exception DAOException
       */
      public ProjectDAO() throws DAOException
      {
	  super();
      }

      /**
       * The function of this method will insert a record into Project Table
       * @exception ProjectDAOSysException
       * @exception ProjectDAODuKeyException
       * @exception ProjectDAOUpException
       * @exception ProjectDAOAppException
       */
      public void create() throws ProjectDAOSysException,
				  ProjectDAODuKeyException,
				  ProjectDAODBUpException,
				  ProjectDAOAppException
      {
	  insertProject();
      }

      /**
       * The function of this method is loading a record
       * @exception ProjectDAOSysException
       * @exception ProjectDAOFindException
       */
      public void load() throws ProjectDAOSysException,
				ProjectDAOFindException
      {
	  selectProject();
      }

      /**
       * The function of this method will modify the record
       */
      public void store() throws ProjectDAODBUpException,
				 ProjectDAOAppException,
				 ProjectDAOSysException
      {
	  updateProject();
      }

      /**
       * The function of this method will delete a record from Project Table
       * @exception ProjectDAODBUpException
       * @exception ProjectDAOSysException
       */
      public void remove() throws ProjectDAODBUpException,
				  ProjectDAOSysException
      {
	  deleteProject();
      }

      /**
       * Search by primary key from project table
       * @param projectID the <code>Integer</code> for this instance
       * @return Integer the primarykey
       * @exception ProjectDAOFindException
       * @exception ProjectDAOSysException
       */
      public Integer findByPrimaryKey(Integer primKey) throws ProjectDAOFindException,
							      ProjectDAOSysException
      {
	      return (projectExists(primKey));
      }


      /**
       * Search data by ProjectID
       * @return ture if the projects is in the project Table
       * @exception ProjectDAOSysException
       */
      private Integer projectExists (Integer primKey) throws ProjectDAOSysException
      {
	  Statement stmt = null;
	  ResultSet result = null;
	  Integer returnValue = new Integer(0);
	  String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("ProjectID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ProjectID")
			  + " = "
			  + InterpretSQL.encodeEndCol(primKey);

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);
	      if ( result.next() )
	      {
		returnValue = new Integer(result.getInt(1));
	      }
	  } catch(SQLException se)
	  {
	      throw new ProjectDAOSysException(
			     "SQLException while checking for an"
			     + " existing project - id -> " + pm.getProjectID() + " :\n" + se);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
	  return returnValue;
      }

      /**
       * insert data to Project Table
       * @exception ProjectDAOSysException
       * @exception ProjectDAODuKeyException
       * @exception ProjectDAOAppException
       */
      private void insertProject() throws
				   ProjectDAOSysException,
				   ProjectDAODuKeyException,
				   ProjectDAODBUpException,
				   ProjectDAOAppException
      {

	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "INSERT INTO "
	      + InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE)
	      + InterpretSQL.startInsert()
	      + InterpretSQL.encodeSign("Name")
              + InterpretSQL.encodeSign("CourseID")
              + InterpretSQL.encodeSign("Info")
              + InterpretSQL.encodeSign("State")
              + InterpretSQL.encodeSign("Type")
              + InterpretSQL.encodeSign("PublishResult")
              + InterpretSQL.encodeSign("StartDate")
	      + InterpretSQL.encodeSign("EndDate")
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
	      + InterpretSQL.encodeCol(pm.getName())
              + InterpretSQL.encodeCol(pm.getCourseID())
              + InterpretSQL.encodeCol(pm.getInfo())
              + InterpretSQL.encodeCol(pm.getState())
              + InterpretSQL.encodeCol(pm.getType())
              + InterpretSQL.encodeCol(pm.getPublishResult())
              + InterpretSQL.encodeCol(pm.getStartDate())
              + InterpretSQL.encodeCol(pm.getEndDate())
	      + InterpretSQL.encodeCol("ÔÊÐí")
	      + InterpretSQL.encodeCol(calendar.getESchoolDateString())
	      + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	      + InterpretSQL.encodeCol(pm.getCreateBy())
	      + InterpretSQL.encodeCol(calendar.getESchoolDateString())
	      + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	      + InterpretSQL.encodeEndCol(pm.getLastModifyBy())
	      + InterpretSQL.endInsert();

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      /**
	       * Insert into Users Table except for ProjectID
	       */
	        getDBConnection();
		stmt = dbConnection.createStatement();
		int resultCount = stmt.executeUpdate(queryStr);

		/**
		 * if inserting is successful then return ProjectID
		 * else throw the exception
		 */
		if ( resultCount!=1)
		{
		    throw new ProjectDAODBUpException(
			"ERROR in PROJECT_TABLE INSERT !! resultCount = " +
				       resultCount);
		}else
		{
		  queryStr = "SELECT last_value FROM \"Project_ProjectID_seq\"";
		  result = stmt.executeQuery(queryStr);
		  /**
		   * get the primary key
		   */
		    if(!result.next())
		    {
			Debug.println("CAN NOT CREATE PROJECT.#2");
			throw new ProjectDAODBUpException("CAN NOT CREATE PROJECT.#2");
		    }else
		    {
			if(result.getInt(1) < 1)
			{
			    pm.setProjectID(new Integer(0));
			    Debug.println("CAN NOT CREATE PROJECT.#3");
			    throw new ProjectDAODBUpException("CAN NOT CREATE PROJECT.#3");
			}else
			{
			    pm.setProjectID(new Integer(result.getInt(1)));
			}
		    }
		}

	  } catch(SQLException ae)
	  {
	      throw new ProjectDAOSysException(
			  "SQLException while inserting new " +
			  "projectID; id = " + pm.getProjectID() + " :\n" + ae);
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
       * @exception ProjectDAOSysException
       * @exception ProjectDAOFindException
       */
      private void selectProject() throws
					   ProjectDAOSysException,
					   ProjectDAOFindException
      {

	  Statement stmt = null;
	  ResultSet result = null;

	  String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ProjectID")
			  + " = "
			  + InterpretSQL.encodeEndCol(pm.getProjectID()) ;

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);

	      if ( !result.next() )
		  throw new ProjectDAOFindException(
				    "No record for primary key " + pm.getProjectID());

	      int i = 1;
	      pm.setProjectID(new Integer(result.getInt(i++)));
	      pm.setName(result.getString(i++));
              pm.setCourseID(new Integer(result.getInt(i++)));
              pm.setInfo(result.getString(i++));
              pm.setState(result.getString(i++));
              pm.setType(result.getString(i++));
              pm.setPublishResult(result.getString(i++));
              pm.setStartDate(result.getString(i++));
              pm.setEndDate(result.getString(i++));
	      pm.setAllow(result.getString(i++));
	      pm.setCreateDate(result.getString(i++));
	      pm.setCreateTime(result.getString(i++));
	      pm.setCreateBy(new Integer(result.getInt(i++)));
	      pm.setLastModifyDate(result.getString(i++));
	      pm.setLastModifyTime(result.getString(i++));
	      pm.setLastModifyBy(new Integer(result.getInt(i++)));

	  } catch(SQLException ae)
	  {
	      throw new ProjectDAOSysException("SQLException while getting " +
			"project; id = " + pm.getProjectID() + " :\n" + ae);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Remove data from Project Table by UserID
       * @exception ProjectDAODBUpException
       * @exception ProjectDAOSysException
       */
      private void deleteProject () throws ProjectDAODBUpException,
					   ProjectDAOSysException
      {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ProjectID")
			  + " = "
			  + InterpretSQL.encodeEndCol(pm.getProjectID());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1) throw new ProjectDAODBUpException
		  ("ERROR deleteing account from PROJECT_TABLE!! resultCount = "+
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new ProjectDAOSysException("SQLException while removing " +
			      "project; id = " + pm.getProjectID() + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Modefy the current data
       * @exception ProjectDAODBUpException
       * @exception ProjectDAOAppException
       * @exception ProjectDAOSysException
       */
      private void updateProject() throws
					   ProjectDAODBUpException,
					   ProjectDAOAppException,
					   ProjectDAOSysException
      {
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("Name")
			  + " = "
			  + InterpretSQL.encodeCol(pm.getName())
                          + InterpretSQL.encodeEndSign("CourseID")
			  + " = "
			  + InterpretSQL.encodeCol(pm.getCourseID())
                          + InterpretSQL.encodeEndSign("Info")
			  + " = "
			  + InterpretSQL.encodeCol(pm.getInfo())
                          + InterpretSQL.encodeEndSign("State")
			  + " = "
			  + InterpretSQL.encodeCol(pm.getState())
                          + InterpretSQL.encodeEndSign("Type")
			  + " = "
			  + InterpretSQL.encodeCol(pm.getType())
                          + InterpretSQL.encodeEndSign("PublishResult")
			  + " = "
			  + InterpretSQL.encodeCol(pm.getPublishResult())
                          + InterpretSQL.encodeEndSign("StartDate")
			  + " = "
			  + InterpretSQL.encodeCol(pm.getStartDate())
                          + InterpretSQL.encodeEndSign("EndDate")
			  + " = "
			  + InterpretSQL.encodeCol(pm.getEndDate())
                          + InterpretSQL.encodeEndSign("Allow")
			  + " = "
			  + InterpretSQL.encodeCol(pm.getAllow())
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  +  " = "
			  +InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  +InterpretSQL.encodeEndSign("LastModifyTime")
			  +" = "
			  +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  +InterpretSQL.encodeEndSign("LastModifyBy")
			  +" = "
			  +InterpretSQL.encodeEndCol(pm.getLastModifyBy())
			  +" WHERE "
			  +InterpretSQL.encodeEndSign("ProjectID")
			  +" = "
			  +InterpretSQL.encodeEndCol(pm.getProjectID());
	  //Debug.println("queryString is: "+ queryStr);

	  Statement stmt = null;
	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1)
		  throw new ProjectDAODBUpException
		  ("ERROR updating account in PROJECT_TABLE!! resultCount = " +
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new ProjectDAOSysException("SQLException while updating " +
		       "Project; id = "  + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

    // get and set methods for the instance variables
    public void setPm(ProjectModel pm)
    {
      this.pm = pm;
    }
    public ProjectModel getPm()
    {
      return pm;
    }
}