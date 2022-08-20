package com.dc.eschool.subject.dao;

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

import com.dc.eschool.subject.exceptions.SubjectDAOAppException;
import com.dc.eschool.subject.exceptions.SubjectDAODBUpException;
import com.dc.eschool.subject.exceptions.SubjectDAODuKeyException;
import com.dc.eschool.subject.exceptions.SubjectDAOFindException;
import com.dc.eschool.subject.exceptions.SubjectDAOSysException;
import com.dc.eschool.subject.model.SubjectModel;

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
 * This subject encapsulates all the JDBC calls made by the SubjectEB.
 * Actual logic of inserting/fetching/updating/deleting  the data in
 * relational database tables to mirror the state of EsubjectEB is
 * implemented here.
 */
public class SubjectDAO extends GeneralDAO
{
      private SubjectModel sm= new SubjectModel();

     /**
       *  Constructor of SubjectDAO
       *  @exception DAOException
       */
      public SubjectDAO() throws DAOException
      {
	  super();
      }

      /**
       * The function of this method will insert a record into Subject Table
       * @exception SubjectDAOSysException
       * @exception SubjectDAODuKeyException
       * @exception SubjectDAOUpException
       * @exception SubjectDAOAppException
       */
      public void create() throws SubjectDAOSysException,
				  SubjectDAODuKeyException,
				  SubjectDAODBUpException,
				  SubjectDAOAppException
      {
	  insertSubject();
      }

      /**
       * The function of this method is loading a record
       * @exception SubjectDAOSysException
       * @exception SubjectDAOFindException
       */
      public void load() throws SubjectDAOSysException,
				SubjectDAOFindException
      {
	  selectSubject();
      }

      /**
       * The function of this method will modify the record
       */
      public void store() throws SubjectDAODBUpException,
				 SubjectDAOAppException,
				 SubjectDAOSysException
      {
	  updateSubject();
      }

      /**
       * The function of this method will delete a record from Subject Table
       * @exception SubjectDAODBUpException
       * @exception SubjectDAOSysException
       */
      public void remove() throws SubjectDAODBUpException,
				  SubjectDAOSysException
      {
	  deleteSubject();
      }

      /**
       * Search by primary key from subject table
       * @param subjectID the <code>Integer</code> for this instance
       * @return Integer the primarykey
       * @exception SubjectDAOFindException
       * @exception SubjectDAOSysException
       */
      public Integer findByPrimaryKey(Integer primKey) throws SubjectDAOFindException,
							      SubjectDAOSysException
      {
	  return subjectExists(primKey);
      }

      /**
       * Search the Subject Name
       * @return boolean
       */
      public boolean isInsert()
      {

          Statement stmt = null;
          ResultSet result = null;
	  boolean returnValue = false;
          String queryStr = "SELECT "
			  + InterpretSQL.encodeEndSign("Name")
			  + " FROM "
                          + InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
                          + " WHERE "
                          + InterpretSQL.encodeEndSign("Name")
                          + " = "
                          + InterpretSQL.encodeEndCol(sm.getName()) ;

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
      /**
       * Search data by SubjectID
       * @return ture if the subjects is in the subject Table
       * @exception SubjectDAOSysException
       */
      private Integer subjectExists (Integer primKey) throws SubjectDAOSysException
      {
	  Statement stmt = null;
	  ResultSet result = null;
	  Integer returnValue = new Integer(0);
	  String queryStr ="SELECT "
			  + InterpretSQL.encodeEndSign("SubjectID")
			  + " FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("SubjectID")
			  + " = "
			  + InterpretSQL.encodeEndCol(primKey);

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);
	      if ( result.next() )
		  returnValue = new Integer(result.getInt(1));
	  } catch(SQLException se)
	  {
	      throw new SubjectDAOSysException(
			     "SQLException while checking for an"
			     + " existing subject - id -> " + primKey + " :\n" + se);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
	  return returnValue;
      }

      /**
       * insert data to Subject Table
       * @exception SubjectDAOSysException
       * @exception SubjectDAODuKeyException
       * @exception SubjectDAOAppException
       */
      private void insertSubject() throws
				   SubjectDAOSysException,
				   SubjectDAODuKeyException,
				   SubjectDAODBUpException,
				   SubjectDAOAppException
      {

	  Statement stmt = null;
	  ResultSet result=null;
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "INSERT INTO "
	      + InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
	      + InterpretSQL.startInsert()
	      + InterpretSQL.encodeSign("Name")
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
	      + InterpretSQL.encodeCol(sm.getName())
	      + InterpretSQL.encodeCol("ÔÊÐí")
	      + InterpretSQL.encodeCol(calendar.getESchoolDateString())
	      + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	      + InterpretSQL.encodeCol(sm.getCreateBy())
	      + InterpretSQL.encodeCol(calendar.getESchoolDateString())
	      + InterpretSQL.encodeCol(calendar.getESchoolTimeString())
	      + InterpretSQL.encodeEndCol(sm.getLastModifyBy())
	      + InterpretSQL.endInsert();

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      /**
	       * Insert into Users Table except for SubjectID
	       */
	      if(!isInsert())
	      {
		getDBConnection();
		stmt = dbConnection.createStatement();
		int resultCount = stmt.executeUpdate(queryStr);

		/**
		 * if inserting is successful then return SubjectID
		 * else throw the exception
		 */
		if ( resultCount!=1)
		{
		    throw new SubjectDAODBUpException(
			"ERROR in SUBJECT_TABLE INSERT !! resultCount = " +
				       resultCount);
		}else
		{
		  queryStr = "SELECT last_value FROM \"Subject_SubjectID_seq\"";
		  result = stmt.executeQuery(queryStr);

		  /**
		   * get the primary key
		   */
		    if(!result.next())
		    {
			Debug.println("CAN NOT CREATE SUBJECT.#2");
			throw new SubjectDAODBUpException("CAN NOT CREATE SUBJECT.#2");
		    }else
		    {
			if(result.getInt(1) < 1)
			{
			    Debug.println("CAN NOT CREATE SUBJECT.#3");
			    throw new SubjectDAODBUpException("CAN NOT CREATE SUBJECT.#3");
			}else
			{
			    sm.setSubjectID(new Integer(result.getInt(1)));
			}
		    }
		}
	      }
	  } catch(SQLException ae)
	  {
	      throw new SubjectDAOSysException(
			  "SQLException while inserting new " +
			  "subjectID; id = " + sm.getSubjectID() + " :\n" + ae);
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
       * @exception SubjectDAOSysException
       * @exception SubjectDAOFindException
       */
      private void selectSubject() throws
					   SubjectDAOSysException,
					   SubjectDAOFindException
      {

	  Statement stmt = null;
	  ResultSet result = null;

	  String queryStr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("SubjectID")
			  + " = "
			  + InterpretSQL.encodeEndCol(sm.getSubjectID()) ;

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      result = stmt.executeQuery(queryStr);

	      if ( !result.next() )
		  throw new SubjectDAOFindException(
				    "No record for primary key " + sm.getSubjectID() );

	      int i = 1;
	      sm.setSubjectID(new Integer(result.getInt(i++)));
	      sm.setName(result.getString(i++));
	      sm.setAllow(result.getString(i++));
	      sm.setCreateDate(result.getString(i++));
	      sm.setCreateTime(result.getString(i++));
	      sm.setCreateBy(new Integer(result.getInt(i++)));
	      sm.setLastModifyDate(result.getString(i++));
	      sm.setLastModifyTime(result.getString(i++));
	      sm.setLastModifyBy(new Integer(result.getInt(i++)));

	  } catch(SQLException ae)
	  {
	      throw new SubjectDAOSysException("SQLException while getting " +
			"subject; id = " + sm.getSubjectID()  + " :\n" + ae);
	  } finally
	  {
	      closeResultSet(result);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Remove data from Subject Table by UserID
       * @exception SubjectDAODBUpException
       * @exception SubjectDAOSysException
       */
      private void deleteSubject () throws SubjectDAODBUpException,
					   SubjectDAOSysException
      {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("SubjectID")
			  + " = "
			  + InterpretSQL.encodeEndCol(sm.getSubjectID());

	  //Debug.println("queryString is: "+ queryStr);

	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1) throw new SubjectDAODBUpException
		  ("ERROR deleteing account from SUBJECT_TABLE!! resultCount = "+
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new SubjectDAOSysException("SQLException while removing " +
			      "subject; id = " + sm.getSubjectID()  + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

      /**
       * Modefy the current data
       * @exception SubjectDAODBUpException
       * @exception SubjectDAOAppException
       * @exception SubjectDAOSysException
       */
      private void updateSubject() throws
					   SubjectDAODBUpException,
					   SubjectDAOAppException,
					   SubjectDAOSysException
      {
	  Calendar calendar=Calendar.getInstance();
	  String queryStr = "UPDATE "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
			  + " SET "
			  + InterpretSQL.encodeEndSign("Name")
			  + " = "
			  + InterpretSQL.encodeCol(sm.getName())
			  + InterpretSQL.encodeEndSign("Allow")
			  + " = "
			  + InterpretSQL.encodeCol(sm.getAllow())
			  + InterpretSQL.encodeEndSign("LastModifyDate")
			  +  " = "
			  +InterpretSQL.encodeCol(calendar.getESchoolDateString())
			  +InterpretSQL.encodeEndSign("LastModifyTime")
			  +" = "
			  +InterpretSQL.encodeCol(calendar.getESchoolTimeString())
			  +InterpretSQL.encodeEndSign("LastModifyBy")
			  +" = "
			  +InterpretSQL.encodeEndCol(sm.getLastModifyBy())
			  +" WHERE "
			  +InterpretSQL.encodeEndSign("SubjectID")
			  +" = "
			  +InterpretSQL.encodeEndCol(sm.getSubjectID());
	  //Debug.println("queryString is: "+ queryStr);

	  Statement stmt = null;
	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	      if (resultCount != 1)
		  throw new SubjectDAODBUpException
		  ("ERROR updating account in SUBJECT_TABLE!! resultCount = " +
		   resultCount);
	  } catch(SQLException se)
	  {
	      throw new SubjectDAOSysException("SQLException while updating " +
		       "Subject; id = "  + " :\n" + se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

    // get and set methods for the instance variables
    public void setSm(SubjectModel sm)
    {
      this.sm = sm;
    }
    public SubjectModel getSm()
    {
      return sm;
    }
}