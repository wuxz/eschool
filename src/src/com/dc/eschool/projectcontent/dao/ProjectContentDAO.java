package com.dc.eschool.projectcontent.dao;

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

import com.dc.eschool.projectcontent.exceptions.ProjectContentDAOAppException;
import com.dc.eschool.projectcontent.exceptions.ProjectContentDAODBUpException;
import com.dc.eschool.projectcontent.exceptions.ProjectContentDAODuKeyException;
import com.dc.eschool.projectcontent.exceptions.ProjectContentDAOFindException;
import com.dc.eschool.projectcontent.exceptions.ProjectContentDAOSysException;
import com.dc.eschool.projectcontent.model.ProjectContentModel;

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
 * This projectcontent encapsulates all the JDBC calls made by the ProjectContentEB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of EprojectcontentEB is
 * implemented here.
 */
public class ProjectContentDAO extends GeneralDAO
{
	protected ProjectContentModel pcm=new ProjectContentModel();

	 /**
	   *  Constructor of ProjectContentDAO
	   *  @exception DAOException
	   */
	  public ProjectContentDAO() throws DAOException
	  {
	  super();
	  }

	  /**
	   * The function of this method will insert a record into ProjectContent Table
	   * @exception ProjectContentDAOSysException
	   * @exception ProjectContentDAODuKeyException
	   * @exception ProjectContentDAOUpException
	   * @exception ProjectContentDAOAppException
	   */
	  public void create() throws ProjectContentDAOSysException,
				  ProjectContentDAODuKeyException,
				  ProjectContentDAODBUpException,
				  ProjectContentDAOAppException
	  {
	  insertProjectContent();
	  }

	  /**
	   * The function of this method is loading a record
	   * @exception ProjectContentDAOSysException
	   * @exception ProjectContentDAOFindException
	   */
	  public void load() throws ProjectContentDAOSysException,
				ProjectContentDAOFindException
	  {
	  selectProjectContent();
	  }

	  /**
	   * The function of this method will modify the record
	   */
	  public void store() throws ProjectContentDAODBUpException,
				 ProjectContentDAOAppException,
				 ProjectContentDAOSysException
	  {
	  updateProjectContent();
	  }

	  /**
	   * The function of this method will delete a record from ProjectContent Table
	   * @exception ProjectContentDAODBUpException
	   * @exception ProjectContentDAOSysException
	   */
	  public void remove() throws ProjectContentDAODBUpException,
				  ProjectContentDAOSysException
	  {
	  deleteProjectContent();
	  }

	  /**
	   * Search by primary key from projectcontent table
	   * @param projectcontentID the <code>Integer</code> for this instance
	   * @return Integer the primarykey
	   * @exception ProjectContentDAOFindException
	   * @exception ProjectContentDAOSysException
	   */
	  public Integer findByPrimaryKey(Integer primKey) throws ProjectContentDAOFindException,
								  ProjectContentDAOSysException
	  {
		  return (projectcontentExists(primKey));
	  }


	  /**
	   * Search by projectId and contentID key from projectcontent table
	   * @param projectID the <code>projectID</code> for this instance
	   * @param contentID the <code>contentID</code> for this instance
	   * @return Integer the primarykey
	   * @exception ProjectContentDAOFindException
	   * @exception ProjectContentDAOSysException
	   */
	  public Integer findByPrimaryKey(Integer projectID, Integer contentID) throws ProjectContentDAOFindException,
								  ProjectContentDAOSysException
	  {
		  return (projectcontentExists(projectID, contentID));
	  }

	  /**
	   * Search data by ProjectContentID
	   * @return ture if the projectcontents is in the projectcontent Table
	   * @exception ProjectContentDAOSysException
	   */
	  private Integer projectcontentExists (Integer primKey) throws ProjectContentDAOSysException
	  {
	  Statement stmt = null;
	  ResultSet result = null;
	  Integer returnValue = new Integer(0);
	  String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("ProjectContentID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.PROJ_CONT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ProjectContentID")
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
		  throw new ProjectContentDAOSysException(
				 "SQLException while checking for an"
				 + " existing projectcontent - id -> " + pcm.getProjectContentID() + " :\n" + se);
	  } finally
	  {
		  closeResultSet(result);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  return returnValue;
	  }

	  /**
	   * Search data by ProjectID, ContentID
	   * @return ture if the projectcontents is in the projectcontent Table
	   * @exception ProjectContentDAOSysException
	   */
	  private Integer projectcontentExists (Integer projectID, Integer contentID) throws ProjectContentDAOSysException
	  {
		  Statement stmt = null;
		  ResultSet result = null;
		  Integer returnValue = new Integer(0);
		  String queryStr ="SELECT "
				  + InterpretSQL.encodeEndSign("ProjectContentID")
				  + " FROM "
				  + InterpretSQL.encodeEndSign(DatabaseNames.PROJ_CONT_TABLE)
				  + " WHERE "
				  + InterpretSQL.encodeEndSign("ProjectID")
				  + " = "
				  + InterpretSQL.encodeEndCol(projectID)
				  + " AND "
				  + InterpretSQL.encodeEndSign("ContentID")
				  + " = "
				  + InterpretSQL.encodeEndCol(contentID);

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
		  }
		  catch(SQLException se)
		  {
			  throw new ProjectContentDAOSysException(
					 "SQLException while checking for an"
					 + " existing projectcontent - id -> " + projectID + "/" + contentID + " :\n" + se);
		  } finally
		  {
			  closeResultSet(result);
			  closeStatement(stmt);
			  closeDBConnection();
		  }
		  return returnValue;
	  }

	  /**
	   * insert data to ProjectContent Table
	   * @exception ProjectContentDAOSysException
	   * @exception ProjectContentDAODuKeyException
	   * @exception ProjectContentDAOAppException
	   */
	  private void insertProjectContent() throws
				   ProjectContentDAOSysException,
				   ProjectContentDAODuKeyException,
				   ProjectContentDAODBUpException,
				   ProjectContentDAOAppException
	  {

	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "INSERT INTO "
		  + InterpretSQL.encodeEndSign(DatabaseNames.PROJ_CONT_TABLE)
		  + InterpretSQL.startInsert()
		  + InterpretSQL.encodeSign("ContentID")
		  + InterpretSQL.encodeSign("ProjectID")
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
			  + InterpretSQL.encodeCol(pcm.getContentID())
			  + InterpretSQL.encodeCol(pcm.getProjectID())
		  + InterpretSQL.encodeCol("ÔÊÐí")
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeCol(pcm.getCreateBy())
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeEndCol(pcm.getLastModifyBy())
		  + InterpretSQL.endInsert();

	  //Debug.println("queryString is: "+ queryStr);

	  if(!isHave())
	  {
		try
		{
		/**
		 * Insert into Users Table except for ProjectContentID
		 */
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  int resultCount = stmt.executeUpdate(queryStr);

		  /**
		   * if inserting is successful then return ProjectContentID
		   * else throw the exception
		   */
		  if ( resultCount!=1)
		  {
			  throw new ProjectContentDAODBUpException(
			  "ERROR in PROJ_CONT_TABLE INSERT !! resultCount = " +
					 resultCount);
		  }else
		  {
			queryStr = "SELECT last_value FROM \"ProjectConten_ProjectConten_seq\"";
			result = stmt.executeQuery(queryStr);

			/**
			 * get the primary key
			 */
			  if(!result.next())
			  {
			  Debug.println("CAN NOT CREATE PROJ_CONT.#2");
			  throw new ProjectContentDAODBUpException("CAN NOT CREATE PROJ_CONT.#2");
			  }else
			  {
			  if(result.getInt(1) < 1)
			  {
				  pcm.setProjectContentID(new Integer(0));
				  Debug.println("CAN NOT CREATE PROJ_CONT.#3");
				  throw new ProjectContentDAODBUpException("CAN NOT CREATE PROJ_CONT.#3");
			  }else
			  {
				  pcm.setProjectContentID(new Integer(result.getInt(1)));
			  }
			  }
		  }

		} catch(SQLException ae)
		{
		throw new ProjectContentDAOSysException(
				"SQLException while inserting new " +
				"projectcontentID; id = " + pcm.getProjectContentID() + " :\n" + ae);
		}
		finally
		{
		closeStatement(stmt);
		closeDBConnection();
		}
	  }
	  }

	  /**
	   * Search data by UserID
	   *
	   * @exception ProjectContentDAOSysException
	   * @exception ProjectContentDAOFindException
	   */
	  private void selectProjectContent() throws
					   ProjectContentDAOSysException,
					   ProjectContentDAOFindException
	  {

	  Statement stmt = null;
	  ResultSet result = null;

	  String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.PROJ_CONT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ProjectContentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(pcm.getProjectContentID()) ;

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  result = stmt.executeQuery(queryStr);

		  if ( !result.next() )
		  throw new ProjectContentDAOFindException(
					"No record for primary key " + pcm.getProjectContentID());

		  int i = 1;
		  pcm.setProjectContentID(new Integer(result.getInt(i++)));
		  pcm.setContentID(new Integer(result.getInt(i++)));
			  pcm.setProjectID(new Integer(result.getInt(i++)));
		  pcm.setAllow(result.getString(i++));
		  pcm.setCreateDate(result.getString(i++));
		  pcm.setCreateTime(result.getString(i++));
		  pcm.setCreateBy(new Integer(result.getInt(i++)));
		  pcm.setLastModifyDate(result.getString(i++));
		  pcm.setLastModifyTime(result.getString(i++));
		  pcm.setLastModifyBy(new Integer(result.getInt(i++)));

	  } catch(SQLException ae)
	  {
		  throw new ProjectContentDAOSysException("SQLException while getting " +
			"projectcontent; id = " + pcm.getProjectContentID() + " :\n" + ae);
	  } finally
	  {
		  closeResultSet(result);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }

	  /**
	   * Remove data from ProjectContent Table by UserID
	   * @exception ProjectContentDAODBUpException
	   * @exception ProjectContentDAOSysException
	   */
	  private void deleteProjectContent () throws ProjectContentDAODBUpException,
					   ProjectContentDAOSysException
	  {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.PROJ_CONT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ProjectContentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(pcm.getProjectContentID());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  int resultCount = stmt.executeUpdate(queryStr);
		  if (resultCount != 1) throw new ProjectContentDAODBUpException
		  ("ERROR deleteing account from PROJ_CONT_TABLE!! resultCount = "+
		   resultCount);
	  } catch(SQLException se)
	  {
		  throw new ProjectContentDAOSysException("SQLException while removing " +
				  "projectcontent; id = " + pcm.getProjectContentID() + " :\n" + se);
	  } finally
	  {
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }

	  /**
	   * Modefy the current data
	   * @exception ProjectContentDAODBUpException
	   * @exception ProjectContentDAOAppException
	   * @exception ProjectContentDAOSysException
	   */
	  private void updateProjectContent() throws
					   ProjectContentDAODBUpException,
					   ProjectContentDAOAppException,
					   ProjectContentDAOSysException
	  {
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.PROJ_CONT_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("ContentID")
			  + " = "
			  + InterpretSQL.encodeCol(pcm.getContentID())
						  + InterpretSQL.encodeEndSign("ProjectID")
			  + " = "
			  + InterpretSQL.encodeCol(pcm.getProjectID())
						  + InterpretSQL.encodeEndSign("Allow")
			  + " = "
			  + InterpretSQL.encodeCol(pcm.getAllow())
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  +  " = "
			  +InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  +InterpretSQL.encodeEndSign("LastModifyTime")
			  +" = "
			  +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  +InterpretSQL.encodeEndSign("LastModifyBy")
			  +" = "
			  +InterpretSQL.encodeEndCol(pcm.getLastModifyBy())
			  +" WHERE "
			  +InterpretSQL.encodeEndSign("ProjectContentID")
			  +" = "
			  +InterpretSQL.encodeEndCol(pcm.getProjectContentID());
	  //Debug.println("queryString is: "+ queryStr);

	  Statement stmt = null;
	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  int resultCount = stmt.executeUpdate(queryStr);
		  if (resultCount != 1)
		  throw new ProjectContentDAODBUpException
		  ("ERROR updating account in PROJ_CONT_TABLE!! resultCount = " +
		   resultCount);
	  } catch(SQLException se)
	  {
		  throw new ProjectContentDAOSysException("SQLException while updating " +
			   "ProjectContent; id = "  + " :\n" + se);
	  } finally
	  {
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }

	  private boolean isHave()
	  {

	  Statement stmt = null;
	  ResultSet result = null;
	  boolean returnValue=false;

	  String queryStr ="SELECT * FROM "
				+ InterpretSQL.encodeEndSign(DatabaseNames.PROJ_CONT_TABLE)
				+ " WHERE "
				+ InterpretSQL.encodeEndSign("ProjectID")
				+ " = "
				+ InterpretSQL.encodeEndCol(pcm.getProjectID())
				+ " AND "
				+ InterpretSQL.encodeEndSign("ContentID")
				+ " = "
				+ InterpretSQL.encodeEndCol(pcm.getContentID());

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
	public void setPcm(ProjectContentModel pcm)
	{
	  this.pcm = pcm;
	}
	public ProjectContentModel getPcm()
	{
	  return pcm;
	}
}