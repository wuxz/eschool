package com.dc.eschool.homework.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.homework.exceptions.HWDAOAppException;
import com.dc.eschool.homework.exceptions.HWDAODBUpException;
import com.dc.eschool.homework.exceptions.HWDAODuKeyException;
import com.dc.eschool.homework.exceptions.HWDAOFindException;
import com.dc.eschool.homework.exceptions.HWDAOSysException;
import com.dc.eschool.homework.model.HomeWorkModel;

import com.dc.eschool.util.Calendar;
import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.InterpretSQL;


/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

  /**
   * This class encapsulates all the JDBC calls made by the  HomeWorkEB.
   * Actual logic of inserting/fetching/updating/deleting  the data in
   * relational database tables to mirror the state of HomeWorkEB is
   * implemented here.
   */
public class HomeWorkDAO extends GeneralDAO
{
  private HomeWorkModel hm=new HomeWorkModel();

    /**
     *  Constructor of HomeWorkDAO
     *
     *  @exception DAOException
     */
  public HomeWorkDAO() throws DAOException
  {
      super();
  }

    /**
     * The function of this method will insert a record into HomeWork Table
     *
     * @exception HWDAOSysException
     * @exception HWDAODuKeyException
     * @exception HWDAOUpException
     * @exception HWDAOAppException
     */
    public void create() throws HWDAOSysException,
				HWDAODuKeyException,
				HWDAODBUpException,
				HWDAOAppException
    {
	insertHomeWork();
    }

    /**
     * The function of this method is loading a record
     *
     * @exception HWDAOSysException
     * @exception HWDAOFindException
     */
    public void load() throws HWDAOSysException,
			      HWDAOFindException
    {
	selectHomeWork();
    }

    /**
     * The function of this method will modify the record
     */
    public void store() throws HWDAODBUpException,
			       HWDAOAppException,
			       HWDAOSysException
    {
	updateHomeWork();
    }

    /**
     * The function of this method will delete a record from HomeWork Table
     *
     * @exception HWDAODBUpException
     * @exception HWDAOSysException
     */
    public void remove() throws HWDAODBUpException,
				HWDAOSysException
    {
	deleteHomeWork();
    }

    /**
     * Search by primary key from HomeWork table
     *
     * @param HomeWorkID the <code>Integer</code> for this instance
     * @return Integer the primarykey
     * @exception HWDAOFindException
     * @exception HWDAOSysException
     */
    public Integer findByPrimaryKey(Integer primKey) throws
					    HWDAOFindException,
					    HWDAOSysException
    {
	return homeworkExists(primKey);
    }

    /**
     * Search data by HomeWorkID
     *
     * @return ture if the coure is in the HomeWork Table
     * @exception HWDAOSysException
     */
    private Integer homeworkExists (Integer primKey) throws HWDAOSysException
    {
	Statement stmt = null;
	ResultSet result = null;
	Integer returnValue = new Integer(0);
	String queryStr ="SELECT "
			+InterpretSQL.encodeEndSign("HomeWorkID")
			+" FROM "
			+InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORK_TABLE)
			+ " WHERE "
			+InterpretSQL.encodeEndSign("HomeWorkID")
			+" = "
			+InterpretSQL.encodeEndCol(primKey);
	Debug.println("queryString is: "+ queryStr);

	try
	{
	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    result = stmt.executeQuery(queryStr);
	    if ( result.next() )
	        returnValue= new Integer(result.getInt(1));
	} catch(SQLException se)
	{
	    throw new HWDAOSysException(
			   "SQLException while checking for an"
			   + " existing homework - id -> " + hm.getHomeWorkID() + " :\n" + se);
	} finally
	{
	    closeResultSet(result);
	    closeStatement(stmt);
	    closeDBConnection();
	}
	return returnValue;
    }

    /**
     * insert data to HomeWork Table
     *
     * @exception HWDAOSysException
     * @exception HWDAODuKeyException
     * @exception HWDAOAppException
     */
    private void insertHomeWork() throws
				 HWDAOSysException,
				 HWDAODuKeyException,
				 HWDAODBUpException,
				 HWDAOAppException
    {

	Statement stmt = null;
	ResultSet result=null;
	Calendar calendar=Calendar.getInstance();
	String queryStr = "INSERT INTO "
	    +InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORK_TABLE)
	    +InterpretSQL.startInsert()
	    +InterpretSQL.encodeSign("Student")
	    +InterpretSQL.encodeSign("ProjectID")
	    +InterpretSQL.encodeSign("Allow")
	    +InterpretSQL.encodeSign("CreateDate")
	    +InterpretSQL.encodeSign("CreateTime")
	    +InterpretSQL.encodeSign("CreateBy")
	    +InterpretSQL.encodeSign("LastModifyDate")
	    +InterpretSQL.encodeSign("LastModifyTime")
	    +InterpretSQL.encodeEndSign("LastModifyBy")
	    +InterpretSQL.endInsert()
	    + " VALUES "
	    +InterpretSQL.startInsert()
	    +InterpretSQL.encodeCol(hm.getStudent())
            +InterpretSQL.encodeCol(hm.getProjectID())
	    +InterpretSQL.encodeCol("ÔÊÐí")
	    +InterpretSQL.encodeCol(calendar.getESchoolDateString())
	    +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	    +InterpretSQL.encodeCol(hm.getCreateBy())
	    +InterpretSQL.encodeCol(calendar.getESchoolDateString())
	    +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	    +InterpretSQL.encodeEndCol(hm.getLastModifyBy())
	    +InterpretSQL.endInsert();

	Debug.println("queryString is: "+ queryStr);

	try
	{
	    /**
	     * Insert into Users Table except for HomeWorkID
	     */
	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    int resultCount = stmt.executeUpdate(queryStr);

	    /**
	     * if inserting is successful then return HomeWorkID
	     * else throw the exception
	     */
	    if ( resultCount!=1)
	    {
		throw new HWDAODBUpException(
		    "ERROR in HOMEWORK_TABLE INSERT !! resultCount = " +
				   resultCount);
	    }else
	    {
	      /**
	       * Search the primary Key
	       */
	      queryStr = "SELECT last_value FROM \"HomeWork_HomeWorkID_seq\"";
	      result = stmt.executeQuery(queryStr);

	      /**
	       * get the primary key
	       */
	      if(!result.next())
	      {
		Debug.println("CAN NOT CREATE HOMEWORK.#2");
		throw new HWDAODBUpException("CAN NOT CREATE HOMEWORK.#2");
	      }else
	      {
		if(result.getInt(1) < 1)
		{
		  hm.setHomeWorkID(new Integer(0));
		  Debug.println("CAN NOT CREATE HOMEWORK.#3");
		  throw new HWDAODBUpException("CAN NOT CREATE HOMEWORK.#3");
		}else
		{
		  hm.setHomeWorkID(new Integer(result.getInt(1)));
		}
	      }
	    }

	} catch(SQLException ae)
	{
	    throw new HWDAOSysException(
			"SQLException while inserting new " +
			"homework; id = " + hm.getHomeWorkID() + " :\n" + ae);
	}
	finally
	{
	    closeStatement(stmt);
	    closeDBConnection();
	}
    }

    /**
     * Search data by HomeWorkID
     *
     * @exception HWDAOSysException
     * @exception HWDAOFindException
     */
    private void selectHomeWork() throws
					 HWDAOSysException,
					 HWDAOFindException
    {
	Statement stmt = null;
	ResultSet result = null;

	String queryStr = "SELECT * FROM "
			+InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORK_TABLE)
			+" WHERE "
			+InterpretSQL.encodeEndSign("HomeWorkID")
			+" = "
			+InterpretSQL.encodeEndCol(hm.getHomeWorkID()) ;
	Debug.println("queryString is: "+ queryStr);

	try
	{
	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    result = stmt.executeQuery(queryStr);

	    if ( !result.next() )
		throw new HWDAOFindException(
				  "No record for primary key " + hm.getHomeWorkID());

	    int i = 1;
	    hm.setHomeWorkID(new Integer(result.getInt(i++)));
	    hm.setStudent(new Integer(result.getInt(i++)));
	    hm.setProjectID(new Integer(result.getInt(i++)));
	    hm.setAllow(result.getString(i++));
	    hm.setCreateDate(result.getString(i++));
	    hm.setCreateTime(result.getString(i++));
	    hm.setCreateBy(new Integer(result.getInt(i++)));
	    hm.setLastModifyDate(result.getString(i++));
	    hm.setLastModifyTime(result.getString(i++));
	    hm.setLastModifyBy(new Integer(result.getInt(i++)));

	} catch(SQLException ae)
	{
	    throw new HWDAOSysException("SQLException while getting " +
		      "account; id = " + hm.getHomeWorkID() + " :\n" + ae);
	} finally
	{
	    closeResultSet(result);
	    closeStatement(stmt);
	    closeDBConnection();
	}
    }

    /**
     * Remove data from HomeWork Table by HomeWorkID
     *
     * @exception HWDAODBUpException
     * @exception HWDAOSysException
     */
    private void deleteHomeWork () throws
					 HWDAODBUpException,
					 HWDAOSysException
    {
	String queryStr = "DELETE FROM "
			+InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORK_TABLE)
			+" WHERE "
			+InterpretSQL.encodeEndSign("HomeWorkID")
			+" = "
			+InterpretSQL.encodeEndCol(hm.getHomeWorkID());
	Statement stmt = null;
	Debug.println("queryString is: "+ queryStr);

	try
	{
	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    int resultCount = stmt.executeUpdate(queryStr);

	    if (resultCount != 1)
		throw new HWDAODBUpException
		("ERROR deleteing account from HOMEWORK_TABLE!! resultCount = "+
		 resultCount);
	} catch(SQLException se)
	{
	    throw new HWDAOSysException("SQLException while removing " +
			    "homework; id = " + hm.getHomeWorkID() + " :\n" + se);
	} finally
	{
	    closeStatement(stmt);
	    closeDBConnection();
	}
    }

    /**
     * Modefy the current data
     *
     * @exception HWDAODBUpException
     * @exception HWDAOAppException
     * @exception HWDAOSysException
     */
    private void updateHomeWork() throws
					 HWDAODBUpException,
					 HWDAOAppException,
					 HWDAOSysException
    {
	Calendar calendar=Calendar.getInstance();
	String queryStr = "UPDATE "
			+InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORK_TABLE)
			+ " SET "
			+InterpretSQL.encodeEndSign("Student")
			+" = "
			+InterpretSQL.encodeCol(hm.getStudent())
			+InterpretSQL.encodeEndSign("ProjectID")
			+" = "
			+InterpretSQL.encodeCol(hm.getProjectID())
			+InterpretSQL.encodeEndSign("LastModifyDate")
			+" = "
			+InterpretSQL.encodeCol(calendar.getESchoolDateString())
			+InterpretSQL.encodeEndSign("LastModifyTime")
			+" = "
			+InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			+InterpretSQL.encodeEndSign("LastModifyBy")
			+" = "
			+InterpretSQL.encodeEndCol(hm.getLastModifyBy())
			+" WHERE "
			+InterpretSQL.encodeEndSign("HomeWorkID")
			+" = "
			+InterpretSQL.encodeEndCol(hm.getHomeWorkID());
	Debug.println("queryString is: "+ queryStr);

	PreparedStatement stmt = null;
	try
        {
	    getDBConnection();
	    stmt = dbConnection.prepareStatement(queryStr);
	    int resultCount = stmt.executeUpdate();
	    if (resultCount != 1)
		throw new HWDAODBUpException
		("ERROR updating account in HOMEWORK_TABLE!! resultCount = " +
		 resultCount);
	} catch(SQLException se)
        {
	    throw new HWDAOSysException("SQLException while updating " +
		     "Homework; id = "  + " :\n" + se);
	} finally
        {
	    closeStatement(stmt);
	    closeDBConnection();
	}
    }

    public void setHm(HomeWorkModel hm)
    {
      this.hm = hm;
    }
    public HomeWorkModel getHm()
    {
      return hm;
    }

}