package com.dc.eschool.project.dao;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.*;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.project.exceptions.ProjectDAOFindException;
import com.dc.eschool.project.model.ProjectModel;

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
 * This class encapsulates search method calls made by the ProjectSLBean.
 * Actual logic of fetching the data in
 * relational database tables to mirror the state of ProjectSLBean is
 * implemented here.
 */
public class ProjectMgrDAO extends GeneralDAO
{
    public ProjectMgrDAO() throws DAOException
    {
        super();
    }

    /**
     * Search data from Project Table
     * @param clause String "Indicate the SQL after 'WHERE'"
     * @param startindex int the record number of first
     * @param count int the number of showing on the HP
     * @return ListChunk
     * @exception ProjectDAOFindException
     */
  public ListChunk searchProjects(String clause, int startindex, int count,String course,String value,String userType) throws ProjectDAOFindException
  {
    int listCount = 0;
    ArrayList al = new ArrayList();
    Statement stmt = null;
    ResultSet rs = null;
    String queryStr="";
    int countAll = 0;
    try
    {


      if(userType.equals("学生"))
      {
          queryStr=queryStr+getStudentString(clause,value);
          queryStr=queryStr+" AND "
                  +InterpretSQL.encodeEndSign("Project")
                  +"."
                  + InterpretSQL.encodeEndSign("State")
                  +" = "
                  +InterpretSQL.encodeEndCol("公布");
      }else if(userType.equals("教师"))
      {
         queryStr=queryStr+getTeacherString(clause,value);
      }else
      {
          queryStr=queryStr+getAdminString(clause,value);
      }

      if(course!=null)
      {
          queryStr=queryStr+getCourseString(clause,course);
      }

      if(userType.equals("学生"))
      {
        String tableName=InterpretSQL.encodeSign(DatabaseNames.PROJECT_TABLE)
                          +InterpretSQL.encodeSign(DatabaseNames.COURSE_TABLE)
                          +InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE);
        countAll = getCountAll(tableName,queryStr);
      }else
      {
        String tableName=InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE);
        countAll = getCountAll(tableName,queryStr);
      }


      String sql = "SELECT "
                    +InterpretSQL.encodeEndSign("Project")
                    +"."
                    +"* FROM ";
      if(userType.equals("学生"))
      {
        sql=sql+InterpretSQL.encodeSign(DatabaseNames.PROJECT_TABLE)
                +InterpretSQL.encodeSign(DatabaseNames.COURSE_TABLE)
                +InterpretSQL.encodeEndSign(DatabaseNames.COURSESTUDENT_TABLE);
      }else
      {
          sql=sql+InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE);
      }

		    sql=sql+" "+queryStr +" " +getOrderString();

      getDBConnection();
      stmt = dbConnection.createStatement();
      rs = stmt.executeQuery(sql);

      while(startindex-- > 0 && rs.next()) ;
      while(count-- > 0 && rs.next())
      {
        int i = 1;
        ProjectModel pm = new ProjectModel();
        pm.setProjectID(new Integer(rs.getInt(i++)));
        pm.setName(rs.getString(i++));
        pm.setCourseID(new Integer(rs.getInt(i++)));
        pm.setInfo(rs.getString(i++));
        pm.setState(rs.getString(i++));
        pm.setType(rs.getString(i++));
        pm.setPublishResult(rs.getString(i++));
        pm.setStartDate(rs.getString(i++));
        pm.setEndDate(rs.getString(i++));
        pm.setAllow(rs.getString(i++));
        pm.setCreateDate(rs.getString(i++));
        pm.setCreateTime(rs.getString(i++));
        pm.setCreateBy(new Integer(rs.getInt(i++)));
        pm.setLastModifyDate(rs.getString(i++));
        pm.setLastModifyTime(rs.getString(i++));
        pm.setLastModifyBy(new Integer(rs.getInt(i++)));

        al.add(pm);
        listCount++;
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
    return new ListChunk(countAll,al,startindex,listCount);
  }

  public Collection getProjectInfoByApp(String courseID,String type)
  {
    Statement stmt = null;
    ResultSet rs = null;
    ArrayList al=new ArrayList();
    String sql=" SELECT * FROM"
              +InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE)
              + " WHERE "
              +InterpretSQL.encodeEndSign("CourseID")
              + " = "
              + courseID;
    if(!type.equals("")) sql=sql+" AND "
                                +InterpretSQL.encodeEndSign("Type")
                                +" = "
                                +InterpretSQL.encodeEndCol(type);
    try
    {
      getDBConnection();
      stmt = dbConnection.createStatement();
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {
        int i = 1;
        ProjectModel pm = new ProjectModel();
        pm.setProjectID(new Integer(rs.getInt(i++)));
        pm.setName(rs.getString(i++));
        pm.setCourseID(new Integer(rs.getInt(i++)));
        pm.setInfo(rs.getString(i++));
        pm.setState(rs.getString(i++));
        pm.setType(rs.getString(i++));
        pm.setPublishResult(rs.getString(i++));
        pm.setStartDate(rs.getString(i++));
        pm.setEndDate(rs.getString(i++));
        pm.setAllow(rs.getString(i++));
        pm.setCreateDate(rs.getString(i++));
        pm.setCreateTime(rs.getString(i++));
        pm.setCreateBy(new Integer(rs.getInt(i++)));
        pm.setLastModifyDate(rs.getString(i++));
        pm.setLastModifyTime(rs.getString(i++));
        pm.setLastModifyBy(new Integer(rs.getInt(i++)));

        al.add(pm);
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
    return al;
  }
  private String getCourseString(String clause,String course)
  {
    return " AND "
            +InterpretSQL.encodeEndSign("Project")
            +"."
            +InterpretSQL.encodeEndSign("CourseID")
            +" = "
            +course;
  }
  private String getTeacherString(String clause,String value)
  {

      return " WHERE "
              +InterpretSQL.encodeEndSign("Project")
              +"."
              +InterpretSQL.encodeEndSign("Type")
              + " = "
              +InterpretSQL.encodeEndCol(clause)
              +" AND "
              +InterpretSQL.encodeEndSign("Project")
              +"."
              +InterpretSQL.encodeEndSign("CreateBy")
              +" = "
              +value;
  }

  private String getAdminString(String clause,String value)
  {
      return " WHERE "
              +InterpretSQL.encodeEndSign("Project")
              +"."
              +InterpretSQL.encodeEndSign("Type")
              +" = "
              +InterpretSQL.encodeEndCol(clause);

  }

  private String getStudentString(String clause,String value)
  {
      return " WHERE "
              +InterpretSQL.encodeEndSign("Project")
              +"."
              +InterpretSQL.encodeEndSign("Type")
              +" = "
              +InterpretSQL.encodeEndCol(clause)
              + " AND "
              +InterpretSQL.encodeEndSign("Project")
              +"."
              +InterpretSQL.encodeEndSign("CourseID")
              +" = "
              +InterpretSQL.encodeEndSign("Course")
              +"."
              +InterpretSQL.encodeEndSign("CourseID")
              +" AND "
              +InterpretSQL.encodeEndSign("CourseStudent")
              +"."
              +InterpretSQL.encodeEndSign("CourseID")
              +" = "
              +InterpretSQL.encodeEndSign("Course")
              +"."
              +InterpretSQL.encodeEndSign("CourseID")
              +" AND "
              +InterpretSQL.encodeEndSign("CourseStudent")
              +"."
              +InterpretSQL.encodeEndSign("Student")
              +" = "
              +value;
  }

  private String getOrderString()
  {
      return " ORDER BY "
              +InterpretSQL.encodeEndSign("Project")
              +"."
              +InterpretSQL.encodeEndSign("ProjectID")
              +" DESC ";
  }
  public void updateByapp(String courseID,String type,String state)
  {
    Statement stmt = null;
    String sql=" UPDATE "
              +InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE)
              +" SET "
              + InterpretSQL.encodeEndSign("State")
              +" = "
              + InterpretSQL.encodeEndCol(state)
              + " WHERE "
              +InterpretSQL.encodeEndSign("CourseID")
              + " = "
              + courseID;

    if(type.equals("testclosed"))
    {
        sql=sql+" AND "
            +InterpretSQL.encodeEndSign("Type")
            +" = "
            +InterpretSQL.encodeEndCol("test")
            +" AND "
            +InterpretSQL.encodeEndSign("State")
            +" = "
            +InterpretSQL.encodeEndCol("预备");
    }else if(!type.equals(""))
    {
        sql=sql+" AND "
            +InterpretSQL.encodeEndSign("Type")
            +" = "
            +InterpretSQL.encodeEndCol(type);
    }
    try
    {
      getDBConnection();
      stmt = dbConnection.createStatement();
      stmt.executeUpdate(sql);
    }
    catch(SQLException se)
    {
      System.out.println(se);
    }
    finally
    {
      closeStatement(stmt);
      closeDBConnection();
    }
  }

  public boolean selectExamOrNotForApp(String courseID,String type)
  {
    boolean returnValue=false;
    ResultSet rs = null;
    Statement stmt = null;
    String sql=" SELECT * FROM "
              +InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE)
              +" WHERE "
              + InterpretSQL.encodeEndSign("State")
              +" = "
              + InterpretSQL.encodeEndCol("预备")
              + " AND "
              +InterpretSQL.encodeEndSign("CourseID")
              + " = "
              + courseID
              + " AND "
              +InterpretSQL.encodeEndSign("Type")
              + " = "
              + InterpretSQL.encodeEndCol(type);
    try
    {
      getDBConnection();
      stmt = dbConnection.createStatement();
      rs = stmt.executeQuery(sql);
      while(rs.next()){
        returnValue=true;
      }
    }
    catch(SQLException se)
    {
      System.out.println(se);
      return false;
    }
    finally
    {
      closeStatement(stmt);
      closeDBConnection();
    }
    return returnValue;
  }
}