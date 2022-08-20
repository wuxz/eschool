package com.dc.eschool.eclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.eclass.model.EClassModel;
import com.dc.eschool.eclass.exceptions.EClassDAOAppException;
import com.dc.eschool.eclass.exceptions.EClassDAODBUpException;
import com.dc.eschool.eclass.exceptions.EClassDAODuKeyException;
import com.dc.eschool.eclass.exceptions.EClassDAOFindException;
import com.dc.eschool.eclass.exceptions.EClassDAOSysException;

import com.dc.eschool.util.Calendar;
import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.InterpretSQL;

/**
 * Title:        ESchool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * This class encapsulates all the JDBC calls made by the EClassEB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of EclassEB is
 * implemented here.
 */
public class EClassDAO extends GeneralDAO
{
    protected EClassModel ecm=new EClassModel();
     /**
       *  Constructor of EClassDAO
       *
       *  @exception DAOException
       */
      public EClassDAO() throws DAOException
      {
	  super();
      }

      /**
       * The function of this method will insert a record into EClass Table
       * @exception EClassDAOSysException
       * @exception EClassDAODuKeyException
       * @exception EClassDAOUpException
       * @exception EClassDAOAppException
       */
      public void create() throws EClassDAOSysException,
				  EClassDAODuKeyException,
				  EClassDAODBUpException,
				  EClassDAOAppException
      {
	  insertEClass();
      }

      /**
       * The function of this method is loading a record
       * @exception EClassDAOSysException
       * @exception EClassDAOFindException
       */
      public void load() throws EClassDAOSysException,
				EClassDAOFindException
      {
	  selectEClass();
      }

      /**
       * The function of this method will modify the record
       */
      public void store() throws EClassDAODBUpException,
				 EClassDAOAppException,
				 EClassDAOSysException
      {
	  updateEClass();
      }

      /**
       * The function of this method will delete a record from EClass Table
       * @exception EClassDAODBUpException
       * @exception EClassDAOSysException
       */
      public void remove() throws EClassDAODBUpException,
				  EClassDAOSysException
      {
	  deleteEClass();
      }

      /**
       * Search by primary key from class table
       * @param classID the <code>Integer</code> for this instance
       * @return Integer the primarykey
       * @exception EClassDAOFindException
       * @exception EClassDAOSysException
       */
      public Integer findByPrimaryKey(Integer primKey) throws
					      EClassDAOFindException,
					      EClassDAOSysException
      {
	  return classExists(primKey);
      }

      /**
       * Search the Class Name
       * @return boolean
       */

      /**
       * Search data by ClassID
       * @return ture if the classs is in the class Table
       * @exception EClassDAOSysException
       */
      private Integer classExists (Integer primKey) throws EClassDAOSysException
      {
	  Statement stmt = null;
	  ResultSet result = null;
	  Integer returnValue = new Integer(0);
	  String queryStr = "SELECT "
			  + InterpretSQL.encodeEndSign("ClassID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.CLASS_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ClassID")
			  + " = "
			  + InterpretSQL.encodeEndCol(primKey);

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);
	      if (result.next() )
		  returnValue = new Integer(result.getInt(1));
	  } catch(SQLException se)
	  {
	      throw new EClassDAOSysException(
			     "SQLException while checking for an"
			     + " existing class - id -> " + primKey + " :\n" + se);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
	  return returnValue;
      }

      /**
       * insert data to EClass Table
       * @exception EClassDAOSysException
       * @exception EClassDAODuKeyException
       * @exception EClassDAOAppException
       */
      private void insertEClass() throws
				   EClassDAOSysException,
				   EClassDAODuKeyException,
				   EClassDAODBUpException,
				   EClassDAOAppException
      {

	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "INSERT INTO "
			  + InterpretSQL.encodeEndSign(DatabaseNames.CLASS_TABLE)
			  + InterpretSQL.startInsert()
			  + InterpretSQL.encodeSign("Name")
			  + InterpretSQL.encodeSign("Info")
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
			  + InterpretSQL.encodeCol(ecm.getName())
			  + InterpretSQL.encodeCol(ecm.getInfo())
			  + InterpretSQL.encodeCol("ÔÊÐí")
			  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  + InterpretSQL.encodeCol(ecm.getCreateBy())
			  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  + InterpretSQL.encodeEndCol(ecm.getLastModifyBy())
			  + InterpretSQL.endInsert();

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      /**
	       * Insert into Users Table except for ClassID
	       */
		getDBConnection();
		stmt = dbConnection.createStatement();
		int resultCount = stmt.executeUpdate(queryStr);

		/**
		 * if inserting is successful then return ClassID
		 * else throw the exception
		 */
		if ( resultCount!=1)
		{
		    throw new EClassDAODBUpException(
			"ERROR in CLASS_TABLE INSERT !! resultCount = " +
				       resultCount);
		}else
		{
		    queryStr = "SELECT last_value FROM \"Class_ClassID_seq\"";
		    result = stmt.executeQuery(queryStr);

		    if(!result.next())
		    {
			//Debug.println("CAN NOT CREATE CLASS.#2");
			throw new EClassDAODBUpException("CAN NOT CREATE CLASS.#2");
		    }else
		    {
			if(result.getInt(1) < 1)
			{
			    //Debug.println("CAN NOT CREATE CLASS.#3");
			    throw new EClassDAODBUpException("CAN NOT CREATE CLASS.#3");
			}else
			{
			    ecm.setClassID(new Integer(result.getInt(1)));
			}
		    }
		}

	  } catch(SQLException ae)
	  {
	      throw new EClassDAOSysException(
			  "SQLException while inserting new " +
			  "classID; id = " + ecm.getClassID() + " :\n" + ae);
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
       * @exception EClassDAOSysException
       * @exception EClassDAOFindException
       */
      private void selectEClass() throws
					   EClassDAOSysException,
					   EClassDAOFindException
      {

	  Statement stmt = null;
	  ResultSet result = null;

	  String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.CLASS_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ClassID")
			  + " = "
			  + InterpretSQL.encodeEndCol(ecm.getClassID()) ;
	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);

	      if ( !result.next() )
		  throw new EClassDAOFindException(
				    "No record for primary key " + ecm.getClassID());

	      int i = 1;
	      ecm.setClassID(new Integer(result.getInt(i++)));
	      ecm.setName(result.getString(i++));
	      ecm.setInfo(result.getString(i++));
	      ecm.setAllow(result.getString(i++));
	      ecm.setCreateDate(result.getString(i++));
	      ecm.setCreateTime(result.getString(i++));
	      ecm.setCreateBy(new Integer(result.getInt(i++)));
	      ecm.setLastModifyDate(result.getString(i++));
	      ecm.setLastModifyTime(result.getString(i++));
	      ecm.setLastModifyBy(new Integer(result.getInt(i++)));

	  } catch(SQLException ae)
	  {
	      throw new EClassDAOSysException("SQLException while getting " +
			"class; id = " + ecm.getClassID() + " :\n" + ae);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Remove data from EClass Table by UserID
       *
       * @exception EClassDAODBUpException
       * @exception EClassDAOSysException
       */
      private void deleteEClass () throws
					   EClassDAODBUpException,
					   EClassDAOSysException
      {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.CLASS_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ClassID")
			  + " = "
			  + InterpretSQL.encodeEndCol(ecm.getClassID());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1)
		  throw new EClassDAODBUpException
		  ("ERROR deleteing account from CLASS_TABLE!! resultCount = "+
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new EClassDAOSysException("SQLException while removing " +
			      "class; id = " + ecm.getClassID() + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Modefy the current data
       * @exception EClassDAODBUpException
       * @exception EClassDAOAppException
       * @exception EClassDAOSysException
       */
      private void updateEClass() throws   EClassDAODBUpException,
					   EClassDAOAppException,
					   EClassDAOSysException
      {
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.CLASS_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("Name")
			  + " = "
			  + InterpretSQL.encodeCol(ecm.getName())
			  + InterpretSQL.encodeEndSign("Info")
			  + " = "
			  + InterpretSQL.encodeCol(ecm.getInfo())
			  + InterpretSQL.encodeEndSign("Allow")
			  + " = "
			  + InterpretSQL.encodeCol(ecm.getAllow())
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  + " = "
			  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  + InterpretSQL.encodeEndSign("LastModifyTime")
			  + " = "
			  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  + InterpretSQL.encodeEndSign("LastModifyBy")
			  + " = "
			  + InterpretSQL.encodeEndCol(ecm.getLastModifyBy())
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ClassID")
			  + " = "
			  + InterpretSQL.encodeEndCol(ecm.getClassID());

	  //Debug.println("queryString is: "+ queryStr);

	  Statement stmt = null;
	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1)
		  throw new EClassDAODBUpException
		    ("ERROR updating account in CLASS_TABLE!! resultCount = " +
		      resultCount);
	  } catch(SQLException se)
	  {
	      throw new EClassDAOSysException("SQLException while updating " +
		       "EClass; id = "  + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      // get and set methods for the instance variables
      public void setEcm(EClassModel ecm)
      {
	this.ecm = ecm;
      }
      public EClassModel getEcm()
      {
	return ecm;
      }
}