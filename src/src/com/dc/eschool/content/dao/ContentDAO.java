package com.dc.eschool.content.dao;

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

import com.dc.eschool.content.exceptions.ContentDAOAppException;
import com.dc.eschool.content.exceptions.ContentDAODBUpException;
import com.dc.eschool.content.exceptions.ContentDAODuKeyException;
import com.dc.eschool.content.exceptions.ContentDAOFindException;
import com.dc.eschool.content.exceptions.ContentDAOSysException;
import com.dc.eschool.content.model.ContentModel;

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
 * This content encapsulates all the JDBC calls made by the ContentEB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of EcontentEB is
 * implemented here.
 */
public class ContentDAO extends GeneralDAO
{
    protected ContentModel cm= new ContentModel();

     /**
       *  Constructor of ContentDAO
       *  @exception DAOException
       */
      public ContentDAO() throws DAOException
      {
	  super();
      }

      /**
       * The function of this method will insert a record into Content Table
       * @exception ContentDAOSysException
       * @exception ContentDAODuKeyException
       * @exception ContentDAOUpException
       * @exception ContentDAOAppException
       */
      public void create() throws ContentDAOSysException,
				  ContentDAODuKeyException,
				  ContentDAODBUpException,
				  ContentDAOAppException
      {
	  insertContent();
      }

      /**
       * The function of this method is loading a record
       * @exception ContentDAOSysException
       * @exception ContentDAOFindException
       */
      public void load() throws ContentDAOSysException,
				ContentDAOFindException
      {
	  selectContent();
      }

      /**
       * The function of this method will modify the record
       */
      public void store() throws ContentDAODBUpException,
				 ContentDAOAppException,
				 ContentDAOSysException
      {
	  updateContent();
      }

      /**
       * The function of this method will delete a record from Content Table
       * @exception ContentDAODBUpException
       * @exception ContentDAOSysException
       */
      public void remove() throws ContentDAODBUpException,
				  ContentDAOSysException
      {
	  deleteContent();
      }

      /**
       * Search by primary key from content table
       * @param contentID the <code>Integer</code> for this instance
       * @return Integer the primarykey
       * @exception ContentDAOFindException
       * @exception ContentDAOSysException
       */
      public Integer findByPrimaryKey(Integer primKey) throws ContentDAOFindException,
							      ContentDAOSysException
      {
	      return (contentExists(primKey));
      }


      /**
       * Search data by ContentID
       * @return ture if the contents is in the content Table
       * @exception ContentDAOSysException
       */
      private Integer contentExists (Integer primKey) throws ContentDAOSysException
      {
	  Statement stmt = null;
	  ResultSet result = null;
	  Integer returnValue = new Integer(0);
	  String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("ContentID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.CONTENT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ContentID")
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
	      throw new ContentDAOSysException(
			     "SQLException while checking for an"
			     + " existing content - id -> " + cm.getContentID() + " :\n" + se);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
	  return returnValue;
      }

      /**
       * insert data to Content Table
       * @exception ContentDAOSysException
       * @exception ContentDAODuKeyException
       * @exception ContentDAOAppException
       */
      private void insertContent() throws
				   ContentDAOSysException,
				   ContentDAODuKeyException,
				   ContentDAODBUpException,
				   ContentDAOAppException
      {

	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "INSERT INTO "
	      + InterpretSQL.encodeEndSign(DatabaseNames.CONTENT_TABLE)
	      + InterpretSQL.startInsert()
	      + InterpretSQL.encodeSign("FileSize")
              + InterpretSQL.encodeSign("Name")
              + InterpretSQL.encodeSign("DocURL")
              + InterpretSQL.encodeSign("Info")
              + InterpretSQL.encodeSign("State")
              + InterpretSQL.encodeSign("Type")
              + InterpretSQL.encodeSign("HasAnswerItem")
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
              + InterpretSQL.encodeCol(cm.getFileSize())
	      + InterpretSQL.encodeCol(cm.getName())
              + InterpretSQL.encodeCol(cm.getDocURL())
              + InterpretSQL.encodeCol(cm.getInfo())
              + InterpretSQL.encodeCol(cm.getState())
              + InterpretSQL.encodeCol(cm.getType())
              + InterpretSQL.encodeCol(cm.getHasAnswerItem())
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
	       * Insert into Users Table except for ContentID
	       */
	        getDBConnection();
		stmt = dbConnection.createStatement();
		int resultCount = stmt.executeUpdate(queryStr);

		/**
		 * if inserting is successful then return ContentID
		 * else throw the exception
		 */
		if ( resultCount!=1)
		{
		    throw new ContentDAODBUpException(
			"ERROR in CONTENT_TABLE INSERT !! resultCount = " +
				       resultCount);
		}else
		{
		  queryStr = "SELECT last_value FROM \"Content_ContentID_seq\"";
		  stmt = dbConnection.createStatement();
		  result = stmt.executeQuery(queryStr);

		  /**
		   * get the primary key
		   */
		    if(!result.next())
		    {
			Debug.println("CAN NOT CREATE CONTENT.#2");
			throw new ContentDAODBUpException("CAN NOT CREATE CONTENT.#2");
		    }else
		    {
			if(result.getInt(1) < 1)
			{
			    cm.setContentID(new Integer(0));
			    Debug.println("CAN NOT CREATE CONTENT.#3");
			    throw new ContentDAODBUpException("CAN NOT CREATE CONTENT.#3");
			}else
			{
			    cm.setContentID(new Integer(result.getInt(1)));
			}
		    }
		}

	  } catch(SQLException ae)
	  {
	      throw new ContentDAOSysException(
			  "SQLException while inserting new " +
			  "contentID; id = " + cm.getContentID() + " :\n" + ae);
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
       * @exception ContentDAOSysException
       * @exception ContentDAOFindException
       */
      private void selectContent() throws
					   ContentDAOSysException,
					   ContentDAOFindException
      {

	  Statement stmt = null;
	  ResultSet result = null;

	  String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.CONTENT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ContentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(cm.getContentID()) ;

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);

	      if ( !result.next() )
		  throw new ContentDAOFindException(
				    "No record for primary key " + cm.getContentID());

	      int i = 1;
	      cm.setContentID(new Integer(result.getInt(i++)));
	      cm.setFileSize(new Integer(result.getInt(i++)));
	      cm.setName(result.getString(i++));
	      cm.setDocURL(result.getString(i++));
	      cm.setInfo(result.getString(i++));
	      cm.setState(result.getString(i++));
	      cm.setType(result.getString(i++));
	      cm.setHasAnswerItem(result.getString(i++));
	      cm.setAllow(result.getString(i++));
	      cm.setCreateDate(result.getString(i++));
	      cm.setCreateTime(result.getString(i++));
	      cm.setCreateBy(new Integer(result.getInt(i++)));
	      cm.setLastModifyDate(result.getString(i++));
	      cm.setLastModifyTime(result.getString(i++));
	      cm.setLastModifyBy(new Integer(result.getInt(i++)));

	  } catch(SQLException ae)
	  {
	      throw new ContentDAOSysException("SQLException while getting " +
			"content; id = " + cm.getContentID() + " :\n" + ae);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Remove data from Content Table by UserID
       * @exception ContentDAODBUpException
       * @exception ContentDAOSysException
       */
      private void deleteContent () throws ContentDAODBUpException,
					   ContentDAOSysException
      {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.CONTENT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ContentID")
			  + " = "
			  + InterpretSQL.encodeEndCol(cm.getContentID());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1) throw new ContentDAODBUpException
		  ("ERROR deleteing account from CONTENT_TABLE!! resultCount = "+
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new ContentDAOSysException("SQLException while removing " +
			      "content; id = " + cm.getContentID() + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Modefy the current data
       * @exception ContentDAODBUpException
       * @exception ContentDAOAppException
       * @exception ContentDAOSysException
       */
      private void updateContent() throws
					   ContentDAODBUpException,
					   ContentDAOAppException,
					   ContentDAOSysException
      {
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.CONTENT_TABLE)
			  + " SET "
                          + InterpretSQL.encodeEndSign("FileSize")
			  + " = "
			  + InterpretSQL.encodeCol(cm.getFileSize())
			  + InterpretSQL.encodeEndSign("Name")
			  + " = "
			  + InterpretSQL.encodeCol(cm.getName())
                          + InterpretSQL.encodeEndSign("DocURL")
			  + " = "
			  + InterpretSQL.encodeCol(cm.getDocURL())
                          + InterpretSQL.encodeEndSign("Info")
			  + " = "
			  + InterpretSQL.encodeCol(cm.getInfo())
                          + InterpretSQL.encodeEndSign("State")
			  + " = "
			  + InterpretSQL.encodeCol(cm.getState())
                          + InterpretSQL.encodeEndSign("Type")
			  + " = "
			  + InterpretSQL.encodeCol(cm.getType())
                          + InterpretSQL.encodeEndSign("HasAnswerItem")
			  + " = "
			  + InterpretSQL.encodeCol(cm.getHasAnswerItem())
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  +  " = "
			  +InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  +InterpretSQL.encodeEndSign("LastModifyTime")
			  +" = "
			  +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  +InterpretSQL.encodeEndSign("LastModifyBy")
			  +" = "
			  +InterpretSQL.encodeEndCol(cm.getLastModifyBy())
			  +" WHERE "
			  +InterpretSQL.encodeEndSign("ContentID")
			  +" = "
			  +InterpretSQL.encodeEndCol(cm.getContentID());
	  //Debug.println("queryString is: "+ queryStr);

	  Statement stmt = null;
	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1)
		  throw new ContentDAODBUpException
		  ("ERROR updating account in CONTENT_TABLE!! resultCount = " +
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new ContentDAOSysException("SQLException while updating " +
		       "Content; id = "  + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

    // get and set methods for the instance variables
    public void setCm(ContentModel cm)
    {
      this.cm = cm;
    }
    public ContentModel getCm()
    {
      return cm;
    }
}