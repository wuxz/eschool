package com.dc.eschool.homeworkcontent.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.homeworkcontent.model.HWContentModel;
import com.dc.eschool.homeworkcontent.exceptions.HCDAOAppException;
import com.dc.eschool.homeworkcontent.exceptions.HCDAODBUpException;
import com.dc.eschool.homeworkcontent.exceptions.HCDAODuKeyException;
import com.dc.eschool.homeworkcontent.exceptions.HCDAOFindException;
import com.dc.eschool.homeworkcontent.exceptions.HCDAOSysException;

import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.GeneralDAO;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

  /**
   * This class encapsulates all the JDBC calls made by the  HMContentEB.
   * Actual logic of inserting/fetching/updating/deleting  the data in
   * relational database tables to mirror the state of HMContentEB is
   * implemented here.
   */
    public class HWContentDAO extends GeneralDAO
    {
	private HWContentModel hcm=new HWContentModel();
        /**
        *  Constructor of HWContentDAO
        *
        *  @exception DAOException
        */
        public HWContentDAO() throws DAOException
        {
            super();
        }

        /**
        * The function of this method will insert a record into HomeWorkContent Table
        *
        * @exception HCDAOSysException
        * @exception HCDAODuKeyException
        * @exception HCDAOUpException
        * @exception HCDAOAppException
        */
        public void create() throws HCDAOSysException,HCDAODuKeyException,
                                    HCDAODBUpException,HCDAOAppException
        {
            insertHomeWorkContent();
        }

	/**
	 * The function of this method is loading a record
	 *
	 * @exception HCDAOSysException
	 * @exception HCDAOFindException
	 */
	public void load() throws HCDAOSysException,HCDAOFindException
        {
            selectHomeWorkContent();
	}

	/**
	 * The function of this method will modify the record
	 */
	public void store() throws HCDAODBUpException,HCDAOAppException,
                                    HCDAOSysException
        {
            updateHomeWorkContent();
	}

	/**
	 * The function of this method will delete a record from HomeWorkContent Table
	 *
	 * @exception HCDAODBUpException
	 * @exception HCDAOSysException
	 */
	public void remove() throws HCDAODBUpException,HCDAOSysException
        {
            deleteHomeWorkContent();
	}

	/**
	 * Search by primary key from HomeWorkContent table
	 *
	 * @param HomeWorkContentID the <code>Integer</code> for this instance
	 * @return Integer the primarykey
	 * @exception HCDAOFindException
	 * @exception HCDAOSysException
	 */
	public Integer findByPrimaryKey(Integer primKey) throws HCDAOFindException,HCDAOSysException
        {
            return homeworkContentExists(primKey);
	}

	/**
	 * Search data by HomeWorkContentID
	 *
	 * @return ture if the coure is in the HomeWorkContent Table
	 * @exception HCDAOSysException
	 */
	private Integer homeworkContentExists (Integer primKey) throws HCDAOSysException
	{
            Statement stmt = null;
            ResultSet result = null;
            Integer returnValue = new Integer(0);
            String queryStr ="SELECT "
			+InterpretSQL.encodeEndSign("HomeWorkContentID")
			+" FROM "
			+InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORKCONTENT_TABLE)
			+ " WHERE "
			+InterpretSQL.encodeEndSign("HomeWorkContentID")
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
            }catch(SQLException ae)
            {
                throw new HCDAOSysException("SQLException while getting " +
                "account; id = " + primKey + " :\n" + ae);
            }finally
            {
                closeResultSet(result);
                closeStatement(stmt);
                closeDBConnection();
            }
            return returnValue;
	}

	/**
	 * insert data to HomeWorkContent Table
	 *
	 * @exception HCDAOSysException
	 * @exception HCDAODuKeyException
	 * @exception HCDAOAppException
	 */
	private void insertHomeWorkContent() throws HCDAOSysException,
                                                    HCDAODuKeyException,
                                                    HCDAODBUpException,
                                                    HCDAOAppException
        {
            Statement stmt = null;
            ResultSet result=null;
            Calendar calendar=Calendar.getInstance();
            String queryStr = "INSERT INTO "
			+InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORKCONTENT_TABLE)
			+InterpretSQL.startInsert()
			+InterpretSQL.encodeSign("DocURL")
			+InterpretSQL.encodeSign("State")
			+InterpretSQL.encodeSign("SubmitDate")
                        +InterpretSQL.encodeSign("SubmitTime")
			+InterpretSQL.encodeSign("HomeWorkID")
			+InterpretSQL.encodeSign("Size")
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
			+InterpretSQL.encodeCol(hcm.getDocURL())
			+InterpretSQL.encodeCol(hcm.getState())
			+InterpretSQL.encodeCol(calendar.getESchoolDateString())
			+InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			+InterpretSQL.encodeCol(hcm.getHomeWorkID())
			+InterpretSQL.encodeCol(hcm.getSize())
			+InterpretSQL.encodeCol("ÔÊÐí")
			+InterpretSQL.encodeCol(calendar.getESchoolDateString())
			+InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			+InterpretSQL.encodeCol(hcm.getCreateBy())
			+InterpretSQL.encodeCol(calendar.getESchoolDateString())
			+InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			+InterpretSQL.encodeEndCol(hcm.getLastModifyBy())
			+InterpretSQL.endInsert();

            /**
             * inicialize the SQL
             */
            Debug.println("queryString is: "+ queryStr);

            try
            {
                /**
                 * Insert into HomeWorkContent Table except for HomeWorkContentID
                 */
                getDBConnection();
                stmt = dbConnection.createStatement();
                int resultCount = stmt.executeUpdate(queryStr);

                /**
                * if inserting is successful then return HomeWorkContentID
                * else throw the exception
                */
                if ( resultCount != 1 )
                {
                    throw new HCDAODBUpException(
                      "ERROR in HOMEWORKCONTENT_TABLE INSERT !! resultCount = " +
								   resultCount);
                }else
                {
                    /**
                    * Search the primary Key
                    */
                    queryStr = "SELECT last_value FROM \"HomeWorkConte_HomeWorkConte_seq\"";
                    result = stmt.executeQuery(queryStr);

                    /**
                    * get the primary key
                    */
                    if(!result.next())
                    {
                        Debug.println("CAN NOT CREATE HOMEWORKCONTENT.#2");
                          throw new HCDAODBUpException("CAN NOT CREATE HOMEWORKCONTENT.#2");
                    }else
                    {
                        if(result.getInt(1) < 1)
                        {
                            hcm.setHomeWorkContentID(new Integer(0));
                            Debug.println("CAN NOT CREATE HOMEWORKCONTENT.#3");
                            throw new HCDAODBUpException("CAN NOT CREATE HOMEWORKCONTENT.#3");
                        }else
                        {
                            hcm.setHomeWorkContentID(new Integer(result.getInt(1)));
                        }
                    }
                }
            }catch(SQLException ae)
            {
                throw new HCDAOSysException(
                    "SQLException while inserting homeworkcontent account;\n" + ae);
            }finally
            {
                closeStatement(stmt);
                closeDBConnection();
            }
	}

	/**
	 * Search data by HomeWorkContnetID
	 *
	 * @exception HCDAOSysException
	 * @exception HCDAOFindException
	 */
	private void selectHomeWorkContent() throws HCDAOSysException, HCDAOFindException
	{
            Statement stmt = null;
            ResultSet result = null;
            String queryStr ="SELECT "+
			InterpretSQL.encodeSign("HomeWorkContentID") +
			InterpretSQL.encodeSign("DocURL") +
			InterpretSQL.encodeSign("State") +
			InterpretSQL.encodeSign("SubmitDate") +
			InterpretSQL.encodeSign("SubmitTime") +
			InterpretSQL.encodeSign("PassDate") +
			InterpretSQL.encodeSign("PassTime") +
			InterpretSQL.encodeSign("HomeWorkID") +
                        InterpretSQL.encodeSign("Size") +
			InterpretSQL.encodeSign("Allow") +
			InterpretSQL.encodeSign("CreateDate") +
			InterpretSQL.encodeSign("CreateTime") +
			InterpretSQL.encodeSign("CreateBy") +
			InterpretSQL.encodeSign("LastModifyDate") +
			InterpretSQL.encodeSign("LastModifyTime") +
			InterpretSQL.encodeEndSign("LastModifyBy") +
			" FROM " +
			InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORKCONTENT_TABLE) +
			" WHERE " +
			InterpretSQL.encodeEndSign("HomeWorkContentID") +
			" = " +
			InterpretSQL.encodeEndCol(hcm.getHomeWorkContentID());

              Debug.println("queryString is: "+ queryStr);

              try
              {
                  getDBConnection();
                  stmt = dbConnection.createStatement();
                  result = stmt.executeQuery(queryStr);

                  if ( !result.next() )
                      throw new HCDAOFindException( "No record for primary key " + hcm.getHomeWorkContentID());

                  int i = 1;
                  hcm.setHomeWorkContentID(new Integer(result.getInt(i++)));
                  hcm.setDocURL(result.getString(i++));
                  hcm.setState(result.getString(i++));
                  hcm.setSubmitDate(result.getString(i++));
                  hcm.setSubmitTime(result.getString(i++));
                  hcm.setPassDate(result.getString(i++));
                  hcm.setPassTime(result.getString(i++));
                  hcm.setHomeWorkID(new Integer(result.getInt(i++)));
                  hcm.setSize(new Integer(result.getInt(i++)));
                  hcm.setAllow(result.getString(i++));
                  hcm.setCreateDate(result.getString(i++));
                  hcm.setCreateTime(result.getString(i++));
                  hcm.setCreateBy(new Integer(result.getInt(i++)));
                  hcm.setLastModifyDate(result.getString(i++));
                  hcm.setLastModifyTime(result.getString(i++));
                  hcm.setLastModifyBy(new Integer(result.getInt(i++)));
              }catch(SQLException ae)
              {
                  throw new HCDAOSysException("SQLException while getting " +
                        "homeworkcontent; id = " + hcm.getHomeWorkContentID() + " :\n" + ae);
              }finally
              {
                  closeResultSet(result);
                  closeStatement(stmt);
                  closeDBConnection();
              }
	}

	/**
	 * Remove data from HomeWorkContnet Table by HomeWorkContentID
	 *
	 * @exception HCDAODBUpException
	 * @exception HCDAOSysException
	 */
	private void deleteHomeWorkContent () throws HCDAODBUpException,HCDAOSysException
	{
            String queryStr ="DELETE FROM " +
		  InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORKCONTENT_TABLE) +
		  " WHERE " +
		  InterpretSQL.encodeEndSign("HomeWorkContentID") +
		  " = " +
		  InterpretSQL.encodeEndCol(hcm.getHomeWorkContentID());
            Statement stmt = null;
            Debug.println("queryString is: "+ queryStr);
            try
            {
                getDBConnection();
                stmt = dbConnection.createStatement();
                int resultCount = stmt.executeUpdate(queryStr);

                if (resultCount != 1)
                    throw new HCDAODBUpException
                      ("ERROR deleteing account from HOMEWORKCONTENT_TABLE!! resultCount = "+
                      resultCount);
            }catch(SQLException se)
            {
                throw new HCDAOSysException("SQLException while removing " +
                    "homeworkcontent; id = " + hcm.getHomeWorkContentID() + " :\n" + se);
            }finally
            {
                closeStatement(stmt);
                closeDBConnection();
            }
	}

	/**
	 * Modefy the current data
	 *
	 * @exception HCDAODBUpException
	 * @exception HCDAOAppException
	 * @exception HCDAOSysException
	 */
	private void updateHomeWorkContent() throws HCDAODBUpException,
                                                    HCDAOAppException,
                                                    HCDAOSysException
	{
            Calendar calendar=Calendar.getInstance();
            String queryStr =
		"UPDATE " +
		InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORKCONTENT_TABLE) +
		" SET " +
                InterpretSQL.encodeEndSign("DocURL") +
		" = " +
		InterpretSQL.encodeCol(hcm.getDocURL())+
		InterpretSQL.encodeEndSign("State") +
		" = " +
		InterpretSQL.encodeCol(hcm.getState())+

		InterpretSQL.encodeEndSign("PassDate") +
		" = " +
		InterpretSQL.encodeCol(calendar.getESchoolDateString())+

		InterpretSQL.encodeEndSign("PassTime") +
		" = " +
		InterpretSQL.encodeCol(calendar.getESchoolTimeString())+
                InterpretSQL.encodeEndSign("Size") +
		" = " +
		InterpretSQL.encodeCol(hcm.getSize())+

		InterpretSQL.encodeEndSign("LastModifyDate") +
		" = " +
		InterpretSQL.encodeCol(calendar.getESchoolDateString())+

		InterpretSQL.encodeEndSign("LastModifyTime") +
		" = " +
		InterpretSQL.encodeCol(calendar.getESchoolTimeString())+

		InterpretSQL.encodeEndSign("LastModifyBy") +
		" = " +
		InterpretSQL.encodeEndCol(hcm.getLastModifyBy()) +
		" WHERE " +
		InterpretSQL.encodeEndSign("HomeWorkContentID") +
		" = " +
		InterpretSQL.encodeEndCol(hcm.getHomeWorkContentID());

		Debug.println("queryString is: "+ queryStr);

            Statement stmt = null;

            try
            {
                getDBConnection();
                stmt = dbConnection.createStatement();
                int resultCount = stmt.executeUpdate(queryStr);
                if (resultCount != 1)
                    throw new HCDAODBUpException
                    ("ERROR updating account in HOMEWORKCONTENT_TABLE!! resultCount = " +
                    resultCount);
            }catch(SQLException se)
            {
                throw new HCDAOSysException("SQLException while updating " +
                    "Homework; id = "  + " :\n" + se);
            }finally
            {
                closeStatement(stmt);
                closeDBConnection();
            }
	}
	public void setHcm(HWContentModel hcm)
	{
	  this.hcm = hcm;
	}
	public HWContentModel getHcm()
	{
	  return hcm;
	}
  }