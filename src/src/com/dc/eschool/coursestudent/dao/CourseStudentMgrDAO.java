package com.dc.eschool.coursestudent.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.course.dao.CourseMgrDAO;
import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.coursestudent.exceptions.CSDAOFindException;
import com.dc.eschool.coursestudent.model.CourseStudentModel;
import com.dc.eschool.users.dao.UsersMgrDAO;
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
 * This class encapsulates search method calls made by the CourseStudentSLBean.
 * Actual logic of fetching the data in
 * relational database tables to mirror the state of CourseStudentSLBean is
 * implemented here.
 */
public class CourseStudentMgrDAO extends GeneralDAO
{
    public CourseStudentMgrDAO() throws DAOException
    {
        super();
    }

    /**
       * Search data from CourseStudent Table
       * @param clause String "Indicate the SQL after 'WHERE'"
       * @param startindex int the record number of first
       * @param count int the number of showing on the HP
       * @return ListChunk
       * @exception CourseStudentDAOFindException
       */
    public ListChunk searchCourseStudent(String clause, int startindex, int count) throws CSDAOFindException
    {
	Statement stmt = null;
	ResultSet rs = null;
        int countAll = 0;
	int listCount = 0;
	ArrayList al = new ArrayList();

	try
	{
            countAll = getCountAll(InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE),clause);
            if (count==0) count = countAll;

            String sql = "SELECT * FROM "
                        + InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE)
                        + clause
                        + "ORDER BY "
                        + InterpretSQL.encodeEndSign("CourseStudentID");

	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    rs = stmt.executeQuery(sql);

	    while(startindex-- > 0 && rs.next()) ;
	    while(count-- > 0 && rs.next())
	    {
		int i = 1;
		CourseStudentModel cm = new CourseStudentModel();
		cm.setCourseStudentID(new Integer(rs.getInt(i++)));
		cm.setStudent(new Integer(rs.getInt(i++)));
		cm.setCourseID(new Integer(rs.getInt(i++)));
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
	  System.out.println("CourseStudentMgrDAO>seatchCourseStudent:" + se) ;
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
     * search primKey by student and courseID
     * @param student Integer the Student ID
     * @param courseID Integer  the Course ID
     * @return Integer the CourseStuentID
     */
    public Integer searchPKBySC(Integer student,Integer courseID)
    {
	Statement stmt = null;
	ResultSet rs = null;
	Integer retVal = new Integer(0);
	String sql = "SELECT "
		    + InterpretSQL.encodeEndSign("CourseStudentID")
		    + " FROM "
		    + InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE)
		    + " WHERE "
		    + InterpretSQL.encodeEndSign("Student")
		    + " = "
		    + InterpretSQL.encodeEndCol(student)
		    + " AND "
		    + InterpretSQL.encodeEndSign("CourseID")
		    + " = "
		    + InterpretSQL.encodeEndCol(courseID);

	try
	{
	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    rs = stmt.executeQuery(sql);

	    while(rs.next())
	    {
	        retVal = new Integer(rs.getInt(1));
	    }
	}
	catch(SQLException se)
	{
	    System.out.println("CourseStudentMgrDAO>searchPKBySC" + se) ;
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