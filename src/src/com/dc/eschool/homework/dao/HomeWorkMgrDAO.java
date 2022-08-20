package com.dc.eschool.homework.dao;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.*;

import java.sql.SQLException;

import com.dc.eschool.homework.model.HomeWorkModel;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.project.dao.ProjectDAO;
import com.dc.eschool.users.dao.UsersMgrDAO;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.util.GeneralDAO;

import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.homework.exceptions.HWDAOFindException;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * This class encapsulates search method calls made by the HomeWorkSLBean.
 * Actual logic of fetching the data in
 * relational database tables to mirror the state of HomeWorkSLBean is
 * implemented here.
 */
public class HomeWorkMgrDAO extends GeneralDAO
{
  private transient UsersMgrDAO usersMgrDao;

  public HomeWorkMgrDAO() throws DAOException
  {
    super();
  }

  /**
     * Search data from HomeWork Table
     *
     * @param clause String "Indicate the SQL after 'WHERE'"
     * @param startindex int the record number of first
     * @param count int the number of showing on the HP
     * @return ListChunk
     * @exception HWDAOFindException
     */
  public ListChunk searchHomeWork(String clause, int startindex, int count) throws HWDAOFindException
  {

    int listCount = 0;
    ArrayList al = new ArrayList();
    int countAll=0;

    String sql = "SELECT * FROM "
	      +InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORK_TABLE)
	      +clause;
    Statement stmt = null;
    ResultSet rs = null;

    try
    {
      countAll = getCountAll(InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORK_TABLE),clause);
      if (count==0) count=countAll;
      getDBConnection();
      stmt = dbConnection.createStatement();
      rs = stmt.executeQuery(sql);

      while(startindex-- > 0 && rs.next()) ;
      while(count-- > 0 && rs.next())
      {
	int i = 1;
	HomeWorkModel cm = new HomeWorkModel();
	cm.setHomeWorkID(new Integer(rs.getInt(i++)));
	cm.setStudent(new Integer(rs.getInt(i++)));
	cm.setProjectID(new Integer(rs.getInt(i++)));
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

  public Integer searchUserProject(Integer student, Integer projectID)
  {
    Integer returnValue=new Integer(0);
    String sql = "SELECT * FROM "
	      +InterpretSQL.encodeEndSign(DatabaseNames.HOMEWORK_TABLE)
	      +" WHERE "
	      +InterpretSQL.encodeEndSign("Student")
	      +" = "
	      +InterpretSQL.encodeEndCol(student)
	      +" AND "
	      +InterpretSQL.encodeEndSign("ProjectID")
	      +" = "
	      +InterpretSQL.encodeEndCol(projectID);

    Statement stmt = null;
    ResultSet rs = null;

    try
    {
      getDBConnection();
      stmt = dbConnection.createStatement();
      rs = stmt.executeQuery(sql);

      while(rs.next())
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