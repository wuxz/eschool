package com.dc.eschool.studentexamcontent.dao;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.*;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentDAOFindException;
import com.dc.eschool.studentexamcontent.model.StudentExamContentModel;

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
 * This class encapsulates search method calls made by the StudentExamContentSLBean.
 * Actual logic of fetching the data in
 * relational database tables to mirror the state of StudentExamContentSLBean is
 * implemented here.
 */
public class StudentExamContentMgrDAO extends GeneralDAO
{
    public StudentExamContentMgrDAO() throws DAOException
    {
        super();
    }

    /**
     * Search data from StudentExamContent Table
     * @param clause String "Indicate the SQL after 'WHERE'"
     * @param startindex int the record number of first
     * @param count int the number of showing on the HP
     * @return ListChunk
     * @exception StudentExamContentDAOFindException
     */
    public ListChunk searchStudentExamContent(String clause, int startindex, int count) throws StudentExamContentDAOFindException
    {
	int listCount = 0;
	ArrayList al = new ArrayList();
        int countAll = 0;
        Statement stmt = null;
	ResultSet rs = null;

        try
	{
            countAll = getCountAll(InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE),clause);
            if (count == 0) count = countAll;

            String sql = "SELECT * FROM "
                      +InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE)
                      +clause;

	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    rs = stmt.executeQuery(sql);

	    while(startindex-- > 0 && rs.next()) ;
	    while(count-- > 0 && rs.next())
	    {
		int i = 1;
		StudentExamContentModel secm = new StudentExamContentModel();
		secm.setStudentExamContentID(new Integer(rs.getInt(i++)));
		secm.setUserID(new Integer(rs.getInt(i++)));
                secm.setCourseID(new Integer(rs.getInt(i++)));
                secm.setContentID(new Integer(rs.getInt(i++)));
		secm.setAllow(rs.getString(i++));
		secm.setCreateDate(rs.getString(i++));
		secm.setCreateTime(rs.getString(i++));
		secm.setCreateBy(new Integer(rs.getInt(i++)));
		secm.setLastModifyDate(rs.getString(i++));
		secm.setLastModifyTime(rs.getString(i++));
		secm.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(secm);
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

    public boolean isGrouped(String courseID)
    {
        Statement stmt = null;
	ResultSet rs = null;
        boolean returnValue=false;

        try
	{

            String sql = "SELECT * FROM "
                      +InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE)
                      +" WHERE "
                      +InterpretSQL.encodeEndSign("CourseID")
                      +" = "
                      +courseID;

	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    rs = stmt.executeQuery(sql);

	    if(rs.next())
	    {
		returnValue=true;
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
      return returnValue;
    }

      public void deleteStudentExamContentByApp (String courseID)
      {
	  Statement stmt = null;
	  String queryStr = "DELETE FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("CourseID")
			  + " = "
			  + courseID;



	  try
	  {
	      getDBConnection();
	      stmt = dbConnection.createStatement();
	      int resultCount = stmt.executeUpdate(queryStr);
	  } catch(Exception se)
	  {
	      System.out.println(se);
	  } finally
	  {
	      closeStatement(stmt);
	      closeDBConnection();
	  }
      }

    public Integer getContentIDByApp(String userID,String courseID)
    {
        Statement stmt = null;
	ResultSet rs = null;
        Integer returnValue=null;

        try
	{

            String sql = "SELECT "
                      +InterpretSQL.encodeEndSign("ContentID")
                      +" FROM "
                      +InterpretSQL.encodeEndSign(DatabaseNames.STUDENTEXAMCONTENT_TABLE)
                      +" WHERE "
                      +InterpretSQL.encodeEndSign("CourseID")
                      +" = "
                      +courseID
                      +" AND "
                      +InterpretSQL.encodeEndSign("UserID")
                      +" = "
                      +userID;

	    //System.out.println(sql);
            getDBConnection();
	    stmt = dbConnection.createStatement();
	    rs = stmt.executeQuery(sql);


	    if(rs.next())
	    {
		returnValue=new Integer(rs.getInt(1));
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
      return returnValue;
    }
}