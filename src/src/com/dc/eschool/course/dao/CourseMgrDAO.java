package com.dc.eschool.course.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.course.exceptions.CourseDAOFindException;
import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.users.dao.UsersMgrDAO;
import com.dc.eschool.users.model.UsersModel;

import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.ListChunk;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * This class encapsulates search method calls made by the CourseSLBean.
 * Actual logic of fetching the data in
 * relational database tables to mirror the state of CourseSLBean is
 * implemented here.
 */
public class CourseMgrDAO extends GeneralDAO
{

	public CourseMgrDAO() throws DAOException
	{
		super();
	}

	/**
	   * Search data from Course Table
	   * @param clause String "Indicate the SQL after 'WHERE'"
	   * @param startindex int the record number of first
	   * @param count int the number of showing on the HP
	   * @return ListChunk
	   * @exception CourseDAOFindException
	   */
	public ListChunk searchCourse(String clause, int startindex, int count, String value) throws CourseDAOFindException
	{
		Statement stmt = null;
		ResultSet rs = null;
		int listCount = 0;
		int countAll = 0;
		ArrayList al = new ArrayList();
		String queryStr="";

		if(clause == null)
		clause = "";

		if (value==null) value="";

	queryStr = " FROM "+InterpretSQL.encodeEndSign(DatabaseNames.COURSE_TABLE);


	if (clause.equals("searchProjectID"))
	{
		queryStr = queryStr+getSearchProjectIDQuery(value);
	}
	else if (clause.equals("search"))
	{
		queryStr = queryStr+getSearchQuery(value);
	}else if (clause.equals("teacher"))
		{
			if(!value.equals("eschool_admin"))
                            queryStr = queryStr+getTeacherQuery(value);
		}else if (clause.equals("student"))
		{
			queryStr = queryStr+getStudentQuery(value);
		}

	countAll = getCountAll(queryStr);
		if (count==0) count=countAll;

	queryStr=queryStr+" ORDER BY "
		+InterpretSQL.encodeEndSign("Course")
		+"."
		+InterpretSQL.encodeEndSign("CourseID")
				+" DESC ";

	String sql = "select "
			+InterpretSQL.encodeEndSign("Course")
			+".* "
			+queryStr;

		//System.out.println(sql);
		try
		{
			/**
			 * if count=0 indicate that show all the record
			 * so count=countAll
			 */
			if (count==0)
			{
				count = countAll;
			}

			getDBConnection();
			stmt = dbConnection.createStatement();
			rs = stmt.executeQuery(sql);

			while(startindex-- > 0 && rs.next()) ;
			while(count-- > 0 && rs.next())
			{
				int i = 1;
				CourseModel cm = new CourseModel();
				cm.setCourseID(new Integer(rs.getInt(i++)));
				cm.setCourseName(rs.getString(i++));
				cm.setTeacher(new Integer(rs.getInt(i++)));
				cm.setStartDate(rs.getString(i++));
				cm.setEndDate(rs.getString(i++));
				cm.setInfo(rs.getString(i++));
				cm.setState(rs.getString(i++));
				cm.setAllow(rs.getString(i++));
				cm.setCreateDate(rs.getString(i++));
				cm.setCreateTime(rs.getString(i++));
				cm.setCreateBy(new Integer(rs.getInt(i++)));
				cm.setLastModifyDate(rs.getString(i++));
				cm.setLastModifyTime(rs.getString(i++));
				cm.setLastModifyBy(new Integer(rs.getInt(i++)));

				al.add(cm);
				listCount++;
			}
		}
		catch(SQLException se)
		{
			System.out.println(se) ;
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(stmt);
			closeDBConnection();
		}
		return new ListChunk(countAll,al,startindex,listCount);
	}

	  /**
	   * Get the count number of the users Table for the propriate SQL
	   * @param tableName String "Table Name"
	   * @param clause  String SQL
	   * @return int the number of the count
	   * @exception UDAOFindException
	   */
	private int getCountAll(String clause) throws CourseDAOFindException
	{
	int retVal = 0;
	String sql = "select count(*) "+ clause;
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

	/**
	 * Integreat the search SQL
	 */
	private String getSearchQuery(String value)
	{
	  value=InterpretSQL.transform(value);
	  return ","
		  +InterpretSQL.encodeEndSign("Users")
		  +" WHERE "
		  +InterpretSQL.encodeEndSign("Course")
		  +"."
		  +InterpretSQL.encodeEndSign("Teacher")
		  +" = "
		  +InterpretSQL.encodeEndSign("Users")
		  +"."
		  +InterpretSQL.encodeEndSign("UserID")
		  +" AND ("
		  +InterpretSQL.encodeEndSign("Course")
		  +"."
		  +InterpretSQL.encodeEndSign("CourseName")
		  +" LIKE '%"
		  +value
		  +"%' OR "
		  +InterpretSQL.encodeEndSign("Course")
		  +"."
		  +InterpretSQL.encodeEndSign("Info")
		  +" LIKE '%"
		  +value
		  +"%' OR "
		  +InterpretSQL.encodeEndSign("Users")
		  +"."
		  +InterpretSQL.encodeEndSign("Name")
		  +" LIKE '%"
		  +value
		  +"%' )";
	}


	/**
	 * Integreat the search SQL
	 */
	private String getSearchProjectIDQuery(String value)
	{
	  return ","
		  +InterpretSQL.encodeEndSign("Project")
		  +" WHERE "
		  +InterpretSQL.encodeEndSign("Course")
		  +"."
		  +InterpretSQL.encodeEndSign("CourseID")
		  +" = "
		  +InterpretSQL.encodeEndSign("Project")
		  +"."
		  +InterpretSQL.encodeEndSign("CourseID")
		  +" AND "
		  +InterpretSQL.encodeEndSign("Project")
		  +"."
		  +InterpretSQL.encodeEndSign("ProjectID")
		  +" = "
		  +value;
	}
	private String getTeacherQuery(String value)
	{
	  return  " WHERE "
		  +InterpretSQL.encodeEndSign("Course")
		  +"."
		  +InterpretSQL.encodeEndSign("Teacher")
		  +" = "
		  +value;
	}
	private String getStudentQuery(String value)
	{
	  return  ","
			  +InterpretSQL.encodeEndSign("CourseStudent")
			  +" WHERE "
		  +InterpretSQL.encodeEndSign("Course")
		  +"."
		  +InterpretSQL.encodeEndSign("CourseID")
		  +" = "
		  +InterpretSQL.encodeEndSign("CourseStudent")
			  +"."
		  +InterpretSQL.encodeEndSign("CourseID")
			  +" AND "
		  +InterpretSQL.encodeEndSign("CourseStudent")
		  +"."
		  +InterpretSQL.encodeEndSign("Student")
		  +" = "
		  +value;
	}
}