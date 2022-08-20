package com.dc.eschool.users.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Collection;

import javax.naming.*;
import javax.sql.DataSource;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.eclass.dao.EClassMgrDAO;
import com.dc.eschool.eclass.model.EClassModel;
import com.dc.eschool.users.exceptions.UDAOFindException;
import com.dc.eschool.users.model.UsersModel;

import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.InterpretSQL;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * This class encapsulates search method calls made by the UsersSLBean.
 * Actual logic of fetching the data in
 * relational database tables to mirror the state of UsersSLBean is
 * implemented here.
 */
public class UsersMgrDAO extends GeneralDAO
{

    public UsersMgrDAO() throws DAOException
    {
        super();
    }

    /**
     * Search data from Users Table
     * @param clause String Search type
     * @param value Dtring search value
     * @param startindex int the record number of first
     * @param count int the number of showing on the HP
     * @return ListChunk
     * @exception UDAOFindException
     */
    public ListChunk searchUsers(String clause, String value, int startIndex, int count) throws UDAOFindException
    {
	int listCount = 0;
	Statement stmt = null;
	ResultSet rs = null;
	Integer classID;
	ArrayList al = new ArrayList();
	String queryStr="";

	/**
	 * if clause equal "usertype"  seaching the data by usertype such as teacher,student and admin
	 * else if clause equal search  fetching data by LoginName/Name/ClassName
	 * else fetching the data by course
	 * The default value of clause is usertype
	 * The default value of value is student
	 */
	if(clause == null|| clause.equals(""))
	{
	    clause = "usertype";
	}

        if (value==null) value="";

	queryStr = " FROM "+InterpretSQL.encodeEndSign(DatabaseNames.USERS_TABLE);

	if (clause.equals("search"))
	{
	    queryStr = queryStr+getSearchQuery(value);
	}else if(clause.equals("usertype"))
	{
	    if(value.equals(""))
	    {
	        value = "student";
	    }
	    queryStr = queryStr+getUsersQuery(value);
	}else if(clause.equals("course"))
	{
	    queryStr = queryStr+getCourseStudentQuery(value);
	}

	int countAll = getCountAll(queryStr);
        if (count==0) count=countAll;

	queryStr=queryStr+" ORDER BY "
		+InterpretSQL.encodeEndSign("Users")
		+"."
		+InterpretSQL.encodeEndSign("UserID");

	String sql = "select "
		    +InterpretSQL.encodeEndSign("Users")
		    +".* "
		    +queryStr;

                    System.out.println(sql);
	try
	{
	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    rs = stmt.executeQuery(sql);

	    while(startIndex-- > 0 && rs.next()) ;
	    while(count-- > 0 && rs.next())
	    {
		int i = 1;
		UsersModel um = new UsersModel();
		um.setUserID(new Integer(rs.getInt(i++)));
		um.setLoginName(rs.getString(i++));
		um.setPassword(rs.getString(i++));
		um.setName(rs.getString(i++));
		um.setGender(rs.getString(i++));
		um.setBirthday(rs.getString(i++));
		um.setTel(rs.getString(i++));
		um.setEmail(rs.getString(i++));
		um.setAddress(rs.getString(i++));
		classID = new Integer(rs.getInt(i++));
		um.setClassID(classID);
		um.setUserType(rs.getString(i++));
		um.setState(rs.getString(i++));
		um.setAllow(rs.getString(i++));
		um.setCreateDate(rs.getString(i++));
		um.setCreateTime(rs.getString(i++));
		um.setCreateBy(new Integer(rs.getInt(i++)));
		um.setLastModifyDate(rs.getString(i++));
		um.setLastModifyTime(rs.getString(i++));
		um.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(um);
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
	return new ListChunk(countAll,al,startIndex,listCount);
    }

    /**
     * islogin and return value
     * @param loginName String  the login name of user
     * @param password String the password
     * @return Vector the first value is the userID
     *                the second value is the userType
     */
    public UsersModel isLogin(String loginName,String password)
    {
	  UsersModel userModel=new UsersModel();
	  Statement stmt = null;
	  ResultSet rs = null;
	  String qstr = "SELECT * FROM "
		      + InterpretSQL.encodeEndSign(DatabaseNames.USERS_TABLE)
		      + " WHERE "
		      + InterpretSQL.encodeEndSign("LoginName")
		      + " = "
		      + InterpretSQL.encodeEndCol(loginName)
		      + " AND "
		      + InterpretSQL.encodeEndSign("Password")
		      + " = "
		      + InterpretSQL.encodeEndCol(password)
		      + " AND "
		      + InterpretSQL.encodeEndSign("State")
		      + " = "
		      + InterpretSQL.encodeEndCol("正式")
		      + " AND "
		      + InterpretSQL.encodeEndSign("Allow")
		      + " = "
		      + InterpretSQL.encodeEndCol("允许");
	  //System.out.println(qstr);
	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      rs = stmt.executeQuery(qstr);
	      while (rs.next()) {
		  int i = 1;
                  userModel.setUserID(new Integer(rs.getInt(i++)));
                  userModel.setLoginName(rs.getString(i++));
                  userModel.setPassword(rs.getString(i++));
                  userModel.setName(rs.getString(i++));
                  userModel.setGender(rs.getString(i++));
                  userModel.setBirthday(rs.getString(i++));
                  userModel.setTel(rs.getString(i++));
                  userModel.setEmail(rs.getString(i++));
                  userModel.setAddress(rs.getString(i++));
                  userModel.setClassID(new Integer(rs.getInt(i++)));
                  userModel.setUserType(rs.getString(i++));
                  userModel.setState(rs.getString(i++));
                  userModel.setAllow(rs.getString(i++));
                  userModel.setCreateDate(rs.getString(i++));
                  userModel.setCreateTime(rs.getString(i++));
                  userModel.setCreateBy(new Integer(rs.getInt(i++)));
                  userModel.setLastModifyDate(rs.getString(i++));
                  userModel.setLastModifyTime(rs.getString(i++));
                  userModel.setLastModifyBy(new Integer(rs.getInt(i++)));
	      }
	  } catch(SQLException se)
	  {
	      System.out.println("getUser():SQLException while getting " +
			  "User " + loginName + " : " + se.getMessage());
	  } finally {
	      closeResultSet(rs);
	      closeStatement(stmt);
	      closeDBConnection();
	  }
	  return userModel;
      }

    public Collection searchUsersForApp(String courseID)
    {
	int listCount = 0;
	Statement stmt = null;
	ResultSet rs = null;
	Integer classID;
	ArrayList al = new ArrayList();

	String sql = "select "
		    +InterpretSQL.encodeEndSign("Users")
		    +".* "
		    +" FROM "
		    + InterpretSQL.encodeSign("Users")
		    + InterpretSQL.encodeSign("Course")
		    + InterpretSQL.encodeEndSign("CourseStudent")
		    + " WHERE "
		    +InterpretSQL.encodeEndSign("CourseStudent")
		    +"."
		    +InterpretSQL.encodeEndSign("Student")
		    +" = "
		    +InterpretSQL.encodeEndSign("Users")
		    +"."
		    +InterpretSQL.encodeEndSign("UserID")
		    + " AND "
		    +InterpretSQL.encodeEndSign("CourseStudent")
		    +"."
		    +InterpretSQL.encodeEndSign("CourseID")
		    +" = "
		    +InterpretSQL.encodeEndSign("Course")
		    +"."
		    +InterpretSQL.encodeEndSign("CourseID")
		    + " AND "
		    +InterpretSQL.encodeEndSign("Course")
		    +"."
		    +InterpretSQL.encodeEndSign("CourseID")
		    +" = "
		    +courseID;

	try
	{
	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    rs = stmt.executeQuery(sql);

	    while(rs.next())
	    {
		int i = 1;
		UsersModel um = new UsersModel();
		um.setUserID(new Integer(rs.getInt(i++)));
		um.setLoginName(rs.getString(i++));
		um.setPassword(rs.getString(i++));
		um.setName(rs.getString(i++));
		um.setGender(rs.getString(i++));
		um.setBirthday(rs.getString(i++));
		um.setTel(rs.getString(i++));
		um.setEmail(rs.getString(i++));
		um.setAddress(rs.getString(i++));
		classID = new Integer(rs.getInt(i++));
		um.setClassID(classID);
		um.setUserType(rs.getString(i++));
		um.setState(rs.getString(i++));
		um.setAllow(rs.getString(i++));
		um.setCreateDate(rs.getString(i++));
		um.setCreateTime(rs.getString(i++));
		um.setCreateBy(new Integer(rs.getInt(i++)));
		um.setLastModifyDate(rs.getString(i++));
		um.setLastModifyTime(rs.getString(i++));
		um.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(um);
		listCount++;
	    }
	}
	catch(Exception se)
	{
	    System.out.println(se) ;
	}
	finally
	{
	    closeResultSet(rs);
	    closeStatement(stmt);
	    closeDBConnection();
	}
	return al;
    }

    /**
       * Get the count number of the users Table for the propriate SQL
       * @param tableName String "Table Name"
       * @param clause  String SQL
       * @return int the number of the count
       * @exception UDAOFindException
       */
    private int getCountAll(String clause) throws UDAOFindException
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
	      +InterpretSQL.encodeEndSign("Class")
	      +" WHERE "
	      +InterpretSQL.encodeEndSign("Users")
	      +"."
	      +InterpretSQL.encodeEndSign("ClassID")
	      +" = "
	      +InterpretSQL.encodeEndSign("Class")
	      +"."
	      +InterpretSQL.encodeEndSign("ClassID")
	      +" AND ("
	      +InterpretSQL.encodeEndSign("Users")
	      +"."
	      +InterpretSQL.encodeEndSign("LoginName")
	      +" LIKE '%"
	      +value
	      +"%' OR "
	      +InterpretSQL.encodeEndSign("Users")
	      +"."
	      +InterpretSQL.encodeEndSign("Name")
	      +" LIKE '%"
	      +value
	      +"%' OR "
	      +InterpretSQL.encodeEndSign("Class")
	      +"."
	      +InterpretSQL.encodeEndSign("Name")
	      +" LIKE '%"
	      +value
	      +"%' )";
    }

    /**
     * Integreat the usersType SQL
     */
    private String getUsersQuery(String value)
    {
      if(value.equals("admin"))
      {
	value=InterpretSQL.encodeEndCol("管理员");
      }else if(value.equals("teacher"))
      {
	value=InterpretSQL.encodeEndCol("教师");
      }else if(value.equals("student"))
      {
	value=InterpretSQL.encodeEndCol("学生")
              + " OR "
              + InterpretSQL.encodeEndSign("Users")
	      + "."
	      + InterpretSQL.encodeEndSign("UserType")
	      + "="
	      + InterpretSQL.encodeEndCol("旁听生");
      }
      return " WHERE "
	      +InterpretSQL.encodeEndSign("Users")
	      +"."
	      +InterpretSQL.encodeEndSign("UserType")
	      +"="
	      +value;
    }

    /**
     * Integreat the course SQL
     */
    private String getCourseStudentQuery(String value)
    {
      return ","
	      +InterpretSQL.encodeEndSign("CourseStudent")
	      +" WHERE "
	      +InterpretSQL.encodeEndSign("Users")
	      +"."
	      +InterpretSQL.encodeEndSign("UserID")
	      +"="
	      +InterpretSQL.encodeEndSign("CourseStudent")
	      +"."
	      +InterpretSQL.encodeEndSign("Student")
	      +" AND "
	      +InterpretSQL.encodeEndSign("CourseStudent")
	      +"."
	      +InterpretSQL.encodeEndSign("CourseID")
	      +"="
	      +value;
    }
}