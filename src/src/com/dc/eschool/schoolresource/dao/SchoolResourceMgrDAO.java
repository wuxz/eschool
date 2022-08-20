package com.dc.eschool.schoolresource.dao;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import javax.naming.*;

import java.sql.SQLException;

import com.dc.eschool.schoolresource.model.SchoolResourceModel;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.InterpretSQL;

import com.dc.eschool.schoolresource.exceptions.SchoolResourceDAOFinderException;



/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This class encapsulates search method calls made by the SchoolResourceSLBean.
 * Actual logic of fetching the data in
 * relational database tables to mirror the state of SchoolResourceSLBean is
 * implemented here.
 */
public class SchoolResourceMgrDAO extends GeneralDAO
{

  public SchoolResourceMgrDAO() throws DAOException
  {
	super();
  }

/**
 * Search data from SchoolResource Table
 *
 * @param clause String "Indicate the SQL after 'WHERE'"
 * @param startindex int the record number of first
 * @param count int the nsmber of showing on the HP
 * @return ListChunk
 * @exception SchoolResourceDAOFindException
 */
  public ListChunk searchSchoolResource(String clause, int startIndex, int count) throws SchoolResourceDAOFinderException
  {
	Statement stmt = null;
	ResultSet rs = null;
	int listCount = 0;
	ArrayList al = new ArrayList();
	int countAll = 0;

	clause = DatabaseNames.SCHOOLRESOURCE_TABLE;

	String sql =
		"select * from "
		+ InterpretSQL.encodeSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
		+ clause;

	try
	{
	  getDBConnection();
	  stmt = dbConnection.createStatement();
	  rs = stmt.executeQuery(sql);
	  while(startIndex-- > 0 && rs.next())
		countAll ++;
	  while(count-- > 0 && rs.next())
	  {
		int i = 1;
		SchoolResourceModel sm = new SchoolResourceModel();
		sm.setSchoolResourceID(new Integer(rs.getInt(i++)));
		sm.setName(rs.getString(i++));
		sm.setDocURL(rs.getString(i++));
		sm.setSubjectID(new Integer(rs.getInt(i++)));
		sm.setStartDate(rs.getString(i++));
		sm.setEndDate(rs.getString(i++));
		sm.setAllow(rs.getString(i++));
		sm.setCreateDate(rs.getString(i++));
		sm.setCreateTime(rs.getString(i++));
		sm.setCreateBy(new Integer(rs.getInt(i++)));
		sm.setLastModifyDate(rs.getString(i++));
		sm.setLastModifyTime(rs.getString(i++));
		sm.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(sm);
		listCount++;
		countAll ++;

	  }

	  while (rs.next())
		countAll ++;
	}
	catch(SQLException se)
	{
	  System.out.println(se.getMessage()) ;
	}
	finally
	{
	  closeResultSet(rs);
	  closeStatement(stmt);
	  closeDBConnection();
	}
	return new ListChunk(countAll,al,startIndex,listCount);
  }

/**
 * Search data from SchoolResource Table
 *
 * @param keyword is a string that appears in the explain, name or subject name
 * @param subjectID is the subjectID of table Subject
 * @param startindex int the record number of first
 * @param count int the nsmber of showing on the HP
 * @return ListChunk
 * @exception SchoolResourceDAOFindException
 */
  public ListChunk searchSchoolResource(String keyword, String subjectID, String time, String role, int startIndex, int count) throws SchoolResourceDAOFinderException
  {
	keyword = "'%" + keyword + "%'";

	Statement stmt = null;
	ResultSet rs = null;
	int listCount = 0;
	ArrayList al = new ArrayList();
	int countAll = 0;

	String sql =
		  "select * from "
		  + InterpretSQL.encodeSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
		  + InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
		   + " where "
		   + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
		   + "."
		   + InterpretSQL.encodeEndSign("SubjectID")
		   + " = "
		   + InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
		   + "."
		   + InterpretSQL.encodeEndSign("SubjectID");

	if (subjectID != null)
	   sql +=
		   " and "
		   + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
		   + "."
		   + InterpretSQL.encodeEndSign("SubjectID")
		   + " = "
		   + subjectID;

	if (time != null)
	   sql +=
		   " and "
		   + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
		   + "."
		   + InterpretSQL.encodeEndSign("StartDate")
		   + " <= "
		   + InterpretSQL.encodeEndCol(time)
		   + " and "
		   + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
		   + "."
		   + InterpretSQL.encodeEndSign("EndDate")
		   + " >= "
		   + InterpretSQL.encodeEndCol(time);

	if (!"admin".equals(role))
	   sql +=
		   " and "
		   + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
		   + "."
		   + InterpretSQL.encodeEndSign("Allow")
		   + " = "
		   + InterpretSQL.encodeEndCol("¹«²¼");

	sql +=
	   " and ("
	   + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
	   + "."
	   + InterpretSQL.encodeEndSign("Name")
	   + " like "
	   + keyword
	   +" or "
	   + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
	   + "."
	   + InterpretSQL.encodeEndSign("Explain")
	   + " like "
	   + keyword
	   +" or "
	   + InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
	   + "."
	   + InterpretSQL.encodeEndSign("Name")
	   + " like "
	   + keyword
	   + ") order by "
	   + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
	   + "."
	   + InterpretSQL.encodeEndSign("LastModifyDate")
	   + " desc";

	try
	{
	  getDBConnection();
	  stmt = dbConnection.createStatement();
	  rs = stmt.executeQuery(sql);

	  while(startIndex-- > 0 && rs.next())
		countAll ++;

	  while(count-- > 0 && rs.next())
	  {
		int i = 1;
		SchoolResourceModel sm = new SchoolResourceModel();
		sm.setSchoolResourceID(new Integer(rs.getInt(i++)));
		sm.setName(rs.getString(i++));
		sm.setDocURL(rs.getString(i++));
		sm.setSubjectID(new Integer(rs.getInt(i++)));
		sm.setStartDate(rs.getString(i++));
		sm.setEndDate(rs.getString(i++));
		sm.setAllow(rs.getString(i++));
		sm.setCreateDate(rs.getString(i++));
		sm.setCreateTime(rs.getString(i++));
		sm.setCreateBy(new Integer(rs.getInt(i++)));
		sm.setLastModifyDate(rs.getString(i++));
		sm.setLastModifyTime(rs.getString(i++));
		sm.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(sm);
		listCount++;
		countAll ++;

	  }

	  while (rs.next())
		countAll ++;
	}
	catch(SQLException se)
	{
	  System.out.println(se.getMessage()) ;
	}
	finally
	{
	  closeResultSet(rs);
	  closeStatement(stmt);
	  closeDBConnection();
	}
	return new ListChunk(countAll,al,startIndex,listCount);
  }

  public SchoolResourceModel getSchoolResource(String schoolResourceId)
  {
		String qstr = "SELECT * "
			  + "FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SCHOOLRESOURCE_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("SchoolResourseID")
			  + "="
			  + schoolResourceId;
/*
		String qstr = "select * " + "from \"" +  DatabaseNames.SCHOOLRESOURCE_TABLE +
					  "\" where " +
					  "\"SchoolResourceID\"="+ schoolResourceId ;
*/
		SchoolResourceModel sm = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
		   getDBConnection();
		   stmt = dbConnection.createStatement();
		   rs = stmt.executeQuery(qstr);
		   while (rs.next())
		   {
			  int i = 1;
			  Integer schoolResourceID = new Integer(rs.getInt(i++));
			  String name=rs.getString(i++);
			  String docURL = rs.getString(i++);
			  Integer subjectID = new Integer(rs.getString(i++));
			  String startDate=rs.getString(i++);
			  String endDate=rs.getString(i++);
			  sm = new SchoolResourceModel(schoolResourceID,name,docURL,subjectID,startDate,endDate);
			  sm.setAllow(rs.getString(i++));
			  sm.setCreateDate(rs.getString(i++));
			  sm.setCreateTime(rs.getString(i++));
			  sm.setCreateBy(new Integer(rs.getInt(i++)));
			  sm.setLastModifyDate(rs.getString(i++));
			  sm.setLastModifyTime(rs.getString(i++));
			  sm.setLastModifyBy(new Integer(rs.getString(i++)));
			  sm.setExplain(rs.getString(i++));

			}
		} catch(SQLException se)
		{
			System.out.println("getSchoolResource():SQLException while getting " +
						"schoolresource " + schoolResourceId + " : " + se.getMessage()+"\n"
						+ se.getSQLState()
						+ se.getErrorCode());

		} finally {

			closeResultSet(rs);
			closeStatement(stmt);
			closeDBConnection();
		}
		return sm;
	}

/**
 * Get the count number of the schoolresource Table for the propriate SQL
 *
 * @param tableName String "Table Name"
 * @param clause  String SQL
 * @return int the number of the count
 * @exception SchoolResourceDAOFinderException
 */
  private int getCountAll(String clause) throws SchoolResourceDAOFinderException
  {
	int retVal = 0;
	String sql = "select count(*) from \"" +  clause + "\"";

	Statement stmt = null;
	ResultSet rs = null;
	try
	{
	  getDBConnection();
	  stmt = dbConnection.createStatement();
	  rs = stmt.executeQuery(sql);

	  while(rs.next())
	  {
		retVal = rs.getInt(1);
	  }
	}
	catch(SQLException se)
	{
	  System.out.println(se);
	}
	finally
	{
	  closeResultSet(rs);
	  closeStatement(stmt);
	  closeDBConnection();
	}

	return retVal;
  }
}