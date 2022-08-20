package com.dc.eschool.answeritem.dao;

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

import com.dc.eschool.answeritem.exceptions.AnswerItemDAOAppException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAODBUpdateException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAODupKeyException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAOFinderException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAOSysException;
import com.dc.eschool.answeritem.model.AnswerItemModel;

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
 * @author Wangshui
 * @version 1.0
 */

/**
 * This answerItem encapsulates all the JDBC calls made by the AnswerItemEB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of EanswerItemEB is
 * implemented here.
 */
public class AnswerItemDAO extends GeneralDAO
{
    protected AnswerItemModel am=new AnswerItemModel();
     /**
       *  Constructor of AnswerItemDAO
       *  @exception DAOException
       */
      public AnswerItemDAO() throws DAOException
      {
	  super();
      }

      /**
       * The function of this method will insert a record into AnswerItem Table
       * @exception AnswerItemDAOSysException
       * @exception AnswerItemDAODupKeyException
       * @exception AnswerItemDAOUpException
       * @exception AnswerItemDAOAppException
       */
      public void create() throws AnswerItemDAOSysException,
				  AnswerItemDAODupKeyException,
				  AnswerItemDAODBUpdateException,
				  AnswerItemDAOAppException
      {
	  insertAnswerItem();
      }

      /**
       * The function of this method is loading a record
       * @exception AnswerItemDAOSysException
       * @exception AnswerItemDAOFinderException
       */
      public void load() throws AnswerItemDAOSysException,
				AnswerItemDAOFinderException
      {
	  selectAnswerItem();
      }

      /**
       * The function of this method will modify the record
       */
      public void store() throws AnswerItemDAODBUpdateException,
				 AnswerItemDAOAppException,
				 AnswerItemDAOSysException
      {
	  updateAnswerItem();
      }

      /**
       * The function of this method will delete a record from AnswerItem Table
       * @exception AnswerItemDAODBUpdateException
       * @exception AnswerItemDAOSysException
       */
      public void remove() throws AnswerItemDAODBUpdateException,
				  AnswerItemDAOSysException
      {
	  deleteAnswerItem();
      }

      /**
       * Search by primary key from answerItem table
       * @param answerItemID the <code>Integer</code> for this instance
       * @return Integer the primarykey
       * @exception AnswerItemDAOFinderException
       * @exception AnswerItemDAOSysException
       */
      public Integer findByPrimaryKey(Integer primKey) throws AnswerItemDAOFinderException,
							      AnswerItemDAOSysException
      {
	  return answerItemExists(primKey);
      }

      /**
       * Search the AnswerItem Name
       * @return boolean
       */
      /**
       * Search data by AnswerItemID
       * @return ture if the answerItems is in the answerItem Table
       * @exception AnswerItemDAOSysException
       */
      private Integer answerItemExists (Integer primKey) throws AnswerItemDAOSysException
      {
	  Statement stmt = null;
	  ResultSet result = null;
	  Integer returnValue = new Integer(0);
	  String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("AnswerItemID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.ANSWERITEM_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("AnswerItemID")
			  + " = "
			  + InterpretSQL.encodeEndCol(primKey);

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);
	      if ( result.next() )
		  returnValue= new Integer(result.getInt(1));
	  } catch(SQLException se)
	  {
	      throw new AnswerItemDAOSysException(
			     "SQLException while checking for an"
			     + " existing answerItem - id -> " + primKey + " :\n" + se);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
	  return returnValue;
      }

      /**
       * insert data to AnswerItem Table
       * @exception AnswerItemDAOSysException
       * @exception AnswerItemDAODupKeyException
       * @exception AnswerItemDAOAppException
       */
      private void insertAnswerItem() throws
				   AnswerItemDAOSysException,
				   AnswerItemDAODupKeyException,
				   AnswerItemDAODBUpdateException,
				   AnswerItemDAOAppException
      {

	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "INSERT INTO "
	      + InterpretSQL.encodeEndSign(DatabaseNames.ANSWERITEM_TABLE)
	      + InterpretSQL.startInsert()
	      + InterpretSQL.encodeSign("Type")
              + InterpretSQL.encodeSign("ItemNumber")
              + InterpretSQL.encodeSign("AnswerNumber")
              + InterpretSQL.encodeSign("Answer")
              + InterpretSQL.encodeSign("Memo")
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
	      + InterpretSQL.encodeCol(am.getType())
              + InterpretSQL.encodeCol(am.getItemNumber())
              + InterpretSQL.encodeCol(am.getAnswerNumber())
              + InterpretSQL.encodeCol(am.getAnswer())
              + InterpretSQL.encodeCol(am.getMemo())
              + InterpretSQL.encodeCol(am.getContentID())
	      + InterpretSQL.encodeCol("ÔÊÐí")
	      + InterpretSQL.encodeCol(calendar.getESchoolDateString())
	      + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	      + InterpretSQL.encodeCol(am.getCreateBy())
	      + InterpretSQL.encodeCol(calendar.getESchoolDateString())
	      + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	      + InterpretSQL.encodeEndCol(am.getLastModifyBy())
	      + InterpretSQL.endInsert();

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      /**
	       * Insert into Users Table except for AnswerItemID
	       */
	      if(!isInsert())
	      {
		getDBConnection();
		stmt = dbConnection.createStatement();
		int resultCount = stmt.executeUpdate(queryStr);

		/**
		 * if inserting is successful then return AnswerItemID
		 * else throw the exception
		 */
		if ( resultCount!=1)
		{
		    throw new AnswerItemDAODBUpdateException(
			"ERROR in ANSWERITEM_TABLE INSERT !! resultCount = " +
				       resultCount);
		}else
		{
		  queryStr = "SELECT last_value FROM \"AnswerItem_AnswerItemID_seq\"";
		  result = stmt.executeQuery(queryStr);

		  /**
		   * get the primary key
		   */
		    if(!result.next())
		    {
			Debug.println("CAN NOT CREATE ANSWERITEM.#2");
			throw new AnswerItemDAODBUpdateException("CAN NOT CREATE ANSWERITEM.#2");
		    }else
		    {
			if(result.getInt(1) < 1)
			{
			    am.setAnswerItemID(new Integer(0));
			    Debug.println("CAN NOT CREATE ANSWERITEM.#3");
			    throw new AnswerItemDAODBUpdateException("CAN NOT CREATE ANSWERITEM.#3");
			}else
			{
			    am.setAnswerItemID(new Integer(result.getInt(1)));
			}
		    }
		}
	      }
	  } catch(SQLException ae)
	  {
	      throw new AnswerItemDAOSysException(
			  "SQLException while inserting new " +
			  "answerItemID; id = " + am.getAnswerItemID() + " :\n" + ae);
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
       * @exception AnswerItemDAOSysException
       * @exception AnswerItemDAOFinderException
       */
      private void selectAnswerItem() throws
					   AnswerItemDAOSysException,
					   AnswerItemDAOFinderException
      {

	  Statement stmt = null;
	  ResultSet result = null;

	  String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.ANSWERITEM_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("AnswerItemID")
			  + " = "
			  + InterpretSQL.encodeEndCol(am.getAnswerItemID()) ;

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);

	      if ( !result.next() )
		  throw new AnswerItemDAOFinderException(
				    "No record for primary key " + am.getAnswerItemID());

	      int i = 1;
	      am.setAnswerItemID(new Integer(result.getInt(i++)));
	      am.setType(result.getString(i++));
              am.setItemNumber(new Integer(result.getInt(i++)));
              am.setAnswerNumber(result.getString(i++));
              am.setAnswer(result.getString(i++));
              am.setMemo(result.getString(i++));
              am.setContentID(new Integer(result.getInt(i++)));
	      am.setAllow(result.getString(i++));
	      am.setCreateDate(result.getString(i++));
	      am.setCreateTime(result.getString(i++));
	      am.setCreateBy(new Integer(result.getInt(i++)));
	      am.setLastModifyDate(result.getString(i++));
	      am.setLastModifyTime(result.getString(i++));
	      am.setLastModifyBy(new Integer(result.getInt(i++)));

	  } catch(SQLException ae)
	  {
	      throw new AnswerItemDAOSysException("SQLException while getting " +
			"answerItem; id = " + am.getAnswerItemID() + " :\n" + ae);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Remove data from AnswerItem Table by UserID
       * @exception AnswerItemDAODBUpdateException
       * @exception AnswerItemDAOSysException
       */
      private void deleteAnswerItem () throws AnswerItemDAODBUpdateException,
					   AnswerItemDAOSysException
      {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.ANSWERITEM_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("AnswerItemID")
			  + " = "
			  + InterpretSQL.encodeEndCol(am.getAnswerItemID());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1) throw new AnswerItemDAODBUpdateException
		  ("ERROR deleteing account from ANSWERITEM_TABLE!! resultCount = "+
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new AnswerItemDAOSysException("SQLException while removing " +
			      "answerItem; id = " + am.getAnswerItemID() + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Modefy the current data
       * @exception AnswerItemDAODBUpdateException
       * @exception AnswerItemDAOAppException
       * @exception AnswerItemDAOSysException
       */
      private void updateAnswerItem() throws
					   AnswerItemDAODBUpdateException,
					   AnswerItemDAOAppException,
					   AnswerItemDAOSysException
      {
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.ANSWERITEM_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("Type")
			  + " = "
			  + InterpretSQL.encodeCol(am.getType())
                          + InterpretSQL.encodeEndSign("ItemNumber")
			  + " = "
			  + InterpretSQL.encodeCol(am.getItemNumber())
                          + InterpretSQL.encodeEndSign("AnswerNumber")
			  + " = "
			  + InterpretSQL.encodeCol(am.getAnswerNumber())
                          + InterpretSQL.encodeEndSign("Answer")
			  + " = "
			  + InterpretSQL.encodeCol(am.getAnswer())
                          + InterpretSQL.encodeEndSign("Memo")
			  + " = "
			  + InterpretSQL.encodeCol(am.getMemo())
                          + InterpretSQL.encodeEndSign("ContentID")
			  + " = "
			  + InterpretSQL.encodeCol(am.getContentID())
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  +  " = "
			  +InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  +InterpretSQL.encodeEndSign("LastModifyTime")
			  +" = "
			  +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  +InterpretSQL.encodeEndSign("LastModifyBy")
			  +" = "
			  +InterpretSQL.encodeEndCol(am.getLastModifyBy())
			  +" WHERE "
			  +InterpretSQL.encodeEndSign("AnswerItemID")
			  +" = "
			  +InterpretSQL.encodeEndCol(am.getAnswerItemID());
	  //Debug.println("queryString is: "+ queryStr);

	  Statement stmt = null;
	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1)
		  throw new AnswerItemDAODBUpdateException
		  ("ERROR updating account in ANSWERITEM_TABLE!! resultCount = " +
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new AnswerItemDAOSysException("SQLException while updating " +
		       "AnswerItem; id = "  + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Search the ContentID
       * @return boolean
       */
      public boolean isInsert()
      {

          Statement stmt = null;
          ResultSet result = null;
	  boolean returnValue = false;
          String queryStr = "SELECT "
			  + InterpretSQL.encodeEndSign("AnswerItemID")
			  + " FROM "
                          + InterpretSQL.encodeEndSign(DatabaseNames.ANSWERITEM_TABLE)
                          + " WHERE "
                          + InterpretSQL.encodeEndSign("ContentID")
                          + " = "
                          + InterpretSQL.encodeEndCol(am.getContentID())
                          +" AND  "
                          + InterpretSQL.encodeEndSign("ItemNumber")
                          + " = "
                          + InterpretSQL.encodeEndCol(am.getItemNumber()) ;

          //Debug.println("queryString is: "+ queryStr);

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


      // get and set methods for the instance variables
      public void setAm(AnswerItemModel am)
      {
	this.am = am;
      }
      public AnswerItemModel getAm()
      {
	return am;
      }
}