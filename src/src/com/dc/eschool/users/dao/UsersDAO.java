package com.dc.eschool.users.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.users.exceptions.UDAOAppException;
import com.dc.eschool.users.exceptions.UDAODBUpException;
import com.dc.eschool.users.exceptions.UDAODuKeyException;
import com.dc.eschool.users.exceptions.UDAOFindException;
import com.dc.eschool.users.exceptions.UDAOSysException;

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
   * This class encapsulates all the JDBC calls made by the UsersEB.
   * Actual logic of inserting/fetching/updating/deleting  the data in
   * relational database tables to mirror the state of UsersEB is
   * implemented here.
   */
public class UsersDAO extends GeneralDAO
{
	  protected UsersModel um= new UsersModel();
	  /**
	   *  Constructor of UsersDAO
	   *  @exception DAOException
	   */
	  public UsersDAO() throws DAOException
	  {
	  super();
	  }

	  /**
	   * The function of this method will insert a record into Users Table
	   * @exception UDAOSysException
	   * @exception UDAODuKeyException
	   * @exception UDAOUpException
	   * @exception UDAOAppException
	   */
	  public void create() throws UDAOSysException,
				  UDAODuKeyException,
				  UDAODBUpException,
				  UDAOAppException
	  {
	  insertUsers();
	  }

	  /**
	   * The function of this method is loading a record
	   * @exception UDAOSysException
	   * @exception UDAOFindException
	   */
	  public void load() throws UDAOSysException,
				UDAOFindException
	  {
	  selectUsers();
	  }

	  /**
	   * The function of this method will modify the record
	   */
	  public void store() throws UDAODBUpException,
				 UDAOAppException,
				 UDAOSysException
	  {
	  updateUsers();
	  }

	  /**
	   * The function of this method will delete a record from Users Table
	   * @exception UDAODBUpException
	   * @exception UDAOSysException
	   */
	  public void remove() throws UDAODBUpException,
				  UDAOSysException
	  {
	  deleteUsers();
	  }

	  /**
	   * Search by primary key from users table
	   * @param userID the <code>Integer</code> for this instance
	   * @return Integer the primarykey
	   * @exception UDAOFindException
	   * @exception UDAOSysException
	   */
	  public Integer findByPrimaryKey(Integer key) throws
						  UDAOFindException,
						  UDAOSysException
	  {
		Integer ret = userExists(key);
		//Debug.println("found user, key:" + ret);
	  return ret;
	  }

	  /**
	   * Search data by UserID
	   * @return Integer if the users is in the user Table
	   * @exception UDAOSysException
	   */
	  private Integer userExists (Integer key) throws UDAOSysException
	  {
	  Statement stmt = null;
	  ResultSet result = null;
	  Integer returnValue = new Integer(0);
	  String queryStr = "SELECT "
				+ InterpretSQL.encodeEndSign("UserID")
				+ " FROM "
				+ InterpretSQL.encodeEndSign(DatabaseNames.USERS_TABLE)
				+ " WHERE "
				+ InterpretSQL.encodeEndSign("UserID")
				+ " = "
				+ InterpretSQL.encodeEndCol(key);

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
		  throw new UDAOSysException(
				 "SQLException while checking for an"
				 + " existing user - id -> " + um.getUserID() + " :\n" + se);
	  } finally
	  {
		  closeResultSet(result);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  return returnValue;
	  }

	  /**
	   * insert data to Users Table
	   * @exception UDAOSysException
	   * @exception UDAODuKeyException
	   * @exception UDAOAppException
	   */
	  private void insertUsers() throws
				   UDAOSysException,
				   UDAODuKeyException,
				   UDAODBUpException,
				   UDAOAppException
	 {

	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  /**
	   * inicialize the SQL
	   */

	  try
	  {
		String queryStr = "INSERT INTO "
				+ InterpretSQL.encodeEndSign(DatabaseNames.USERS_TABLE)
				+ InterpretSQL.startInsert()
				+ InterpretSQL.encodeSign("LoginName")
				+ InterpretSQL.encodeSign("Name")
				+ InterpretSQL.encodeSign("Gender")
				+ InterpretSQL.encodeSign("Birthday")
				+ InterpretSQL.encodeSign("Tel")
				+ InterpretSQL.encodeSign("Email")
				+ InterpretSQL.encodeSign("Address")
				+ InterpretSQL.encodeSign("ClassID")
				+ InterpretSQL.encodeSign("UserType")
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
				+ InterpretSQL.encodeCol(um.getLoginName())
				+ InterpretSQL.encodeCol(um.getName())
				+ InterpretSQL.encodeCol(um.getGender())
				+ InterpretSQL.encodeCol(um.getBirthday())
				+ InterpretSQL.encodeCol(um.getTel())
				+ InterpretSQL.encodeCol(um.getEmail())
				+ InterpretSQL.encodeCol(um.getAddress())
				+ InterpretSQL.encodeCol(um.getClassID())
				+ InterpretSQL.encodeCol(um.getUserType())
				+ InterpretSQL.encodeCol(um.getState())
				+ InterpretSQL.getTrueValue()+","
				+ InterpretSQL.encodeCol(calendar.getESchoolDateString())
				+ InterpretSQL.encodeCol(calendar.getESchoolTimeString())
				+ InterpretSQL.encodeCol(um.getCreateBy())
				+ InterpretSQL.encodeCol(calendar.getESchoolDateString())
				+ InterpretSQL.encodeCol(calendar.getESchoolTimeString())
				+ InterpretSQL.encodeEndCol(um.getLastModifyBy())
				+ InterpretSQL.endInsert();

		  //Debug.println("queryString is: "+ queryStr);

		  if ( !isLoginNameUsed() )
		  {
		  /**
		   * Insert into Users Table except for UserID
		   */
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  int resultCount = stmt.executeUpdate(queryStr);

		  /**
		   * if inserting is successful then return UserID
		   * else throw the exception
		   */
		  if ( resultCount!=1)
		  {
			  throw new UDAODBUpException(
			  "ERROR in USERS_TABLE INSERT !! resultCount = " +
					 resultCount);
		  }else
		  {
			  /**
			   * Search the primary Key
			   */
			  queryStr = "SELECT last_value FROM \"Users_UserID_seq\"";
			  result = stmt.executeQuery(queryStr);

			  if(!result.next())
			  {
			  Debug.println("CAN NOT CREATE USERS.#2");
			  throw new UDAODBUpException("CAN NOT CREATE USERS.#2");
			  }else
			  {
			  if(result.getInt(1) < 1)
			  {
				  Debug.println("CAN NOT CREATE USERS.#3");
				  throw new UDAODBUpException("CAN NOT CREATE USERS.#3");
			  }else
			  {
				  um.setUserID(new Integer(result.getInt(1)));
			  }
			  }
		  }
		  }
	  } catch(SQLException ae)
	  {
		  throw new UDAOSysException(
			  "SQLException while inserting new " +
			  "userID; id = " + um.getUserID() + " :\n" + ae);
	  }
	  finally
	  {
		  closeResultSet(result);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }

	  /**
	   * Search data by UserID
	   * @exception UDAOSysException
	   * @exception UDAOFindException
	   */
	  private void selectUsers() throws UDAOSysException,UDAOFindException
	  {
	  Statement stmt = null;
	  ResultSet result = null;
	  String queryStr = "SELECT * FROM "
				+InterpretSQL.encodeEndSign(DatabaseNames.USERS_TABLE)
				+ " WHERE "
				+InterpretSQL.encodeEndSign("UserID")
				+" = "
				+InterpretSQL.encodeEndCol(um.getUserID());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  result = stmt.executeQuery(queryStr);

		  if ( !result.next() )
		  throw new UDAOFindException(
					"No record for primary key " + um.getUserID());

		  int i = 1;
		  um.setUserID(new Integer(result.getInt(i++)));
		  um.setLoginName(result.getString(i++));
		  um.setPassword(result.getString(i++));
		  um.setName(result.getString(i++));
		  um.setGender(result.getString(i++));
		  um.setBirthday(result.getString(i++));
		  um.setTel(result.getString(i++));
		  um.setEmail(result.getString(i++));
		  um.setAddress(result.getString(i++));
		  um.setClassID(new Integer(result.getInt(i++)));
		  um.setUserType(result.getString(i++));
		  um.setState(result.getString(i++));
		  um.setAllow(result.getString(i++));
		  um.setCreateDate(result.getString(i++));
		  um.setCreateTime(result.getString(i++));
		  um.setCreateBy(new Integer(result.getInt(i++)));
		  um.setLastModifyDate(result.getString(i++));
		  um.setLastModifyTime(result.getString(i++));
		  um.setLastModifyBy(new Integer(result.getInt(i++)));

	  } catch(SQLException ae)
	  {
		  throw new UDAOSysException("SQLException while getting " +
			"users; id = " + um.getUserID() + " :\n" + ae);
	  } finally
	  {
		  closeResultSet(result);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }

	  /**
	   * Remove data from Users Table by UserID
	   * @exception UDAODBUpException
	   * @exception UDAOSysException
	   */
	  private void deleteUsers () throws
					   UDAODBUpException,
					   UDAOSysException
	  {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
				+ InterpretSQL.encodeEndSign(DatabaseNames.USERS_TABLE)
				+ " WHERE "
				+ InterpretSQL.encodeEndSign("UserID")
				+" = "
				+ InterpretSQL.encodeEndCol(um.getUserID());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  int resultCount = stmt.executeUpdate(queryStr);

		  if (resultCount != 1)
		  throw new UDAODBUpException
		  ("ERROR deleteing account from USERS_TABLE!! resultCount = "+
		   resultCount);
	  } catch(SQLException se)
	  {
		  throw new UDAOSysException("SQLException while removing " +
				  "users; id = " + um.getUserID() + " :\n" + se);
	  } finally
	  {
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  }

	  /**
	   * Modefy the current data
	   *
	   * @exception UDAODBUpException
	   * @exception UDAOAppException
	   * @exception UDAOSysException
	   */
	  private void updateUsers() throws
					   UDAODBUpException,
					   UDAOAppException,
					   UDAOSysException
	  {
	  Calendar calendar=Calendar.getInstance();
	  Statement stmt = null;
	  String queryStr = "UPDATE "
		  + InterpretSQL.encodeEndSign(DatabaseNames.USERS_TABLE)
		  + " SET "
		  + InterpretSQL.encodeEndSign("LoginName")+"="
		  + InterpretSQL.encodeCol(um.getLoginName())
		  + InterpretSQL.encodeEndSign("Password")+"="
		  + InterpretSQL.encodeCol(um.getPassword())
		  + InterpretSQL.encodeEndSign("Name")+"="
		  + InterpretSQL.encodeCol(um.getName())
		  + InterpretSQL.encodeEndSign("Gender")+"="
		  + InterpretSQL.encodeCol(um.getGender())
		  + InterpretSQL.encodeEndSign("Birthday")+"="
		  + InterpretSQL.encodeCol(um.getBirthday())
		  + InterpretSQL.encodeEndSign("Tel")+"="
		  + InterpretSQL.encodeCol(um.getTel())
		  + InterpretSQL.encodeEndSign("Email")+"="
		  + InterpretSQL.encodeCol(um.getEmail())
		  + InterpretSQL.encodeEndSign("Address")+"="
		  + InterpretSQL.encodeCol(um.getAddress())
		  + InterpretSQL.encodeEndSign("ClassID")+"="
		  + InterpretSQL.encodeCol(um.getClassID())
		  + InterpretSQL.encodeEndSign("UserType")+"="
		  + InterpretSQL.encodeCol(um.getUserType())
		  + InterpretSQL.encodeEndSign("State")+"="
		  + InterpretSQL.encodeCol(um.getState())
		  + InterpretSQL.encodeEndSign("Allow")+"="
		  + InterpretSQL.encodeCol(um.getAllow())
		  + InterpretSQL.encodeEndSign("LastModifyDate")+"="
		  + InterpretSQL.encodeCol(calendar.getESchoolDateString())
		  + InterpretSQL.encodeEndSign("LastModifyTime")+"="
		  + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
		  + InterpretSQL.encodeEndSign("LastModifyBy")+"="
		  + InterpretSQL.encodeEndCol(um.getLastModifyBy())
		  + " WHERE "
		  + InterpretSQL.encodeEndSign("UserID")
		  +" = "
		  + InterpretSQL.encodeEndCol(um.getUserID());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  int resultCount = stmt.executeUpdate(queryStr);
		  if (resultCount != 1)
		  throw new UDAODBUpException
		  ("ERROR updating account in USERS_TABLE!! resultCount = " +
		   resultCount);
	  } catch(SQLException se)
	  {
		  throw new UDAOSysException("SQLException while updating " +
			   "Homework; id = "  + " :\n" + se);
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
	  private boolean isLoginNameUsed()
	  {

	  Statement stmt = null;
	  ResultSet result = null;
	  boolean returnValue=false;

	  String queryStr ="SELECT * FROM "
				+ InterpretSQL.encodeEndSign(DatabaseNames.USERS_TABLE)
				+ " WHERE "
				+ InterpretSQL.encodeEndSign("LoginName")
				+ " = "
				+ InterpretSQL.encodeEndCol(um.getLoginName());

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
	public void setUm(UsersModel um)
	{
		this.um = um;
	}
	public UsersModel getUm()
	{
		return um;
	}
}