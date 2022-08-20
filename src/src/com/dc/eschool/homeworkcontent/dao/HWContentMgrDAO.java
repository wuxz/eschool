package com.dc.eschool.homeworkcontent.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

import com.dc.eschool.course.dao.CourseDAO;
import com.dc.eschool.course.dao.CourseDAO;
import com.dc.eschool.content.dao.ContentDAO;
import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.homework.model.HomeWorkModel;
import com.dc.eschool.homeworkcontent.exceptions.HCDAOFindException;
import com.dc.eschool.homeworkcontent.model.HWContentModel;
import com.dc.eschool.project.dao.ProjectDAO;
import com.dc.eschool.users.dao.UsersMgrDAO;
import com.dc.eschool.users.model.UsersModel;

import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.ListChunk;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

  /**
   * This class encapsulates search method calls made by the HWContentSLBean.
   * Actual logic of fetching the data in
   * relational database tables to mirror the state of HWContentSLBean is
   * implemented here.
   */
  public class HWContentMgrDAO extends GeneralDAO
  {

	  public HWContentMgrDAO() throws DAOException
	  {
		  super();
	  }

	  /**
	   * Search data from HomeWorkContent Table
	   *
	   * @param clause String "Indicate the SQL after 'WHERE'"
	   * @param startindex int the record number of first
	   * @param count int the number of showing on the HP
	   * @return ListChunk
	   * @exception HCDAOFindException
	   */
	  public ListChunk searchHomeWorkContent(String clause, int startindex, int count, String value) throws HCDAOFindException
	  {
		  int listCount = 0;
		  ArrayList al = new ArrayList();
		  Statement stmt = null;
		  ResultSet rs = null;
		  int countAll = 0;
		  UsersModel usersModel;
		  String orderString= "  ORDER BY "
						+ InterpretSQL.encodeEndSign("HomeWorkContent")
						+ "."
						+ InterpretSQL.encodeEndSign("LastModifyDate")
						+" DESC , "
						+ InterpretSQL.encodeEndSign("HomeWorkContent")
						+ "."
						+ InterpretSQL.encodeEndSign("LastModifyTime")
						+ "DESC";

		  try
		  {
			  Debug.println("get count all :" + clause);
			  String whereStr="";

			  if(clause.equals("homework"))
			  {
				  whereStr=getHomeWork(value);
			  }
			  else if(clause.equals("session"))
			  {
				  whereStr=getSFHW(value);
			  }else
			  {
				  whereStr=getUploaded(clause, value);
			  }
			  whereStr=getFromString()+whereStr;
			  countAll = getCountAll(whereStr);
			  if (count==0) count=countAll;
			  String queryStr= getQueryString()+ whereStr+orderString;

			  System.out.println(queryStr);
			  getDBConnection();
			  stmt = dbConnection.createStatement();
			  rs = stmt.executeQuery(queryStr);
			  while(startindex-- > 0 && rs.next()) ;
			  while(count-- > 0 && rs.next())
			  {
				  int i = 1;
				  HWContentModel hcm = new HWContentModel();
				  Integer studentID=new Integer(rs.getInt(i++));
				  hcm.setStudent(studentID);
				  hcm.setHomeWorkContentID(new Integer(rs.getInt(i++)));
				  hcm.setDocURL(rs.getString(i++));
				  hcm.setState(rs.getString(i++));
				  hcm.setCreateDate(rs.getString(i++));
				  hcm.setSubmitDate(rs.getString(i++));
				  hcm.setSubmitTime(rs.getString(i++));
				  hcm.setPassDate(rs.getString(i++));
				  hcm.setPassTime(rs.getString(i++));
				  hcm.setSize(new Integer(rs.getInt(i++)));
				  al.add(hcm);
				  listCount++;
			  }
		  }catch(Exception se)
		  {
			  System.out.println(se) ;
		  }finally
		  {
			  closeResultSet(rs);
			  closeStatement(stmt);
			  closeDBConnection();
		  }
		  return new ListChunk(countAll,al,startindex,listCount);
	  }


	  /**
	   * the Query string for upload homeworks and upload approved homeworks
	   */
	  private String getSFHW(String value)
	  {
		  return  InterpretSQL.encodeEndSign("HomeWorkContent")
				  +"."
				  + InterpretSQL.encodeEndSign("LastModifyBy")
				  +" ="
				  +value;

	  }

	  /**
	   * get the query String; all the unapproved Homeworks
	   */
	  private String getHomeWork(String value)
	  {
		  return  InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeEndSign("State")
				  + " = "
				  + InterpretSQL.encodeEndCol("Î´ÅúÔÄ")
				  + " AND "
				  + InterpretSQL.encodeEndSign("HomeWork")
				  + "."
				  + InterpretSQL.encodeEndSign("ProjectID")
				  + " ="
				  + value;
	  }

	  /**
	   * get the query String; all the unapproved Homeworks
	   */
	  private String getUploaded(String clause, String value)
	  {
		  return  InterpretSQL.encodeEndSign("HomeWork")
				  + "."
				  + InterpretSQL.encodeEndSign("ProjectID")
				  + " ="
				  + value
				  + " AND "
				  + InterpretSQL.encodeEndSign("HomeWork")
				  + "."
				  + InterpretSQL.encodeEndSign("Student")
				  + " ="
				  + clause;
	  }

	  /**
	   * Get the from "From" SQL clause
	   */
	  private String getFromString()
	  {
		  return  " FROM "
				  + InterpretSQL.encodeSign("HomeWork")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + " WHERE "
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeEndSign("HomeWorkID")
				  +" = "
				  + InterpretSQL.encodeEndSign("HomeWork")
				  + "."
				  + InterpretSQL.encodeEndSign("HomeWorkID")
				  + " AND ";
	  }

	  /**
	   * get the List clause
	   */
	  private String getQueryString()
	  {
		  return "SELECT "
				  + InterpretSQL.encodeEndSign("HomeWork")
				  + "."
				  + InterpretSQL.encodeSign("Student")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeSign("HomeWorkContentID")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeSign("DocURL")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeSign("State")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeSign("CreateDate")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeSign("SubmitDate")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeSign("SubmitTime")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeSign("PassDate")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeSign("PassTime")
				  + InterpretSQL.encodeEndSign("HomeWorkContent")
				  + "."
				  + InterpretSQL.encodeEndSign("Size");

	  }

	  /**
	   * Get the count number of the users Table for the propriate SQL
	   * @param tableName String "Table Name"
	   * @param clause  String SQL
	   * @return int the number of the count
	   * @exception UDAOFindException
	   */
	  private int getCountAll(String clause)
	  {
		  int retVal = 0;
		  Statement stmt = null;
		  ResultSet rs = null;
		  clause= "SELECT COUNT (*)  " + clause;
		  try
		  {
			  getDBConnection();
			  stmt = dbConnection.createStatement();
			  rs = stmt.executeQuery(clause);
			  while(rs.next())
			  {
				  retVal = rs.getInt(1);
			  }
		  }
		  catch(Exception se)
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
	   * Get the homeworkID from the homeworkContent table
	   */
	  public Vector getHomeWorkID(Integer primaryKey)
	  {
		  Vector returnValue=new Vector();
		  Statement stmt = null;
		  ResultSet rs = null;
		  int homeworkId=0;
		  try
		  {
			  String queryStr="SELECT "
							  + InterpretSQL.encodeEndSign("HomeWorkID")
							  + " FROM "
							  + InterpretSQL.encodeEndSign("HomeWorkContent")
							  + " WHERE "
							  + InterpretSQL.encodeEndSign("HomeWorkContentID")
							  + " = "
							  + String.valueOf(primaryKey);
			  getDBConnection();
			  stmt = dbConnection.createStatement();
			  rs = stmt.executeQuery(queryStr);
			  while(rs.next())
			  {
				  homeworkId = rs.getInt(1);
			  }
			  if(homeworkId!=0)
			  {
				  String countStr=" FROM "
								  + InterpretSQL.encodeEndSign("HomeWorkContent")
								  + " WHERE "
								  + InterpretSQL.encodeEndSign("HomeWorkID")
								  + " = "
								  + String.valueOf(homeworkId);
				  int count=getCountAll(countStr);
				  returnValue.addElement(String.valueOf(count));
				  returnValue.addElement(String.valueOf(homeworkId));
			  }
		  }catch(Exception e)
		  {
			  System.out.println(e);
		  }finally
		  {
			  closeResultSet(rs);
			  closeStatement(stmt);
			  closeDBConnection();
		  }
		  return returnValue;
	  }

	  /**
	   * get the unhandin students' names and userID from users table
	   *
	   */
	  public Vector unHandInStudent(Integer projectID)
	  {
		  Vector returnValue=new Vector();
		  Statement stmt = null;
		  ResultSet rs = null;
		  int homeworkId=0;
		  Vector allStudents=new Vector();
		  try
		  {
			  String queryStr="SELECT "
							  + InterpretSQL.encodeEndSign("Users")
							  + "."
							  + InterpretSQL.encodeSign("UserID")
							  + InterpretSQL.encodeEndSign("Users")
							  + "."
							  + InterpretSQL.encodeEndSign("Name")
							  +" FROM "
							  + InterpretSQL.encodeSign("Users")
							  + InterpretSQL.encodeSign("CourseStudent")
							  + InterpretSQL.encodeSign("Course")
							  + InterpretSQL.encodeEndSign("Project")
							  + " WHERE "
							  + InterpretSQL.encodeEndSign("Users")
							  + "."
							  + InterpretSQL.encodeEndSign("UserID")
							  + " = "
							  + InterpretSQL.encodeEndSign("CourseStudent")
							  + "."
							  + InterpretSQL.encodeEndSign("Student")
							  + " AND "
							  + InterpretSQL.encodeEndSign("CourseStudent")
							  + "."
							  + InterpretSQL.encodeEndSign("CourseID")
							  + " = "
							  + InterpretSQL.encodeEndSign("Course")
							  + "."
							  + InterpretSQL.encodeEndSign("CourseID")
							  + " AND "
							  + InterpretSQL.encodeEndSign("Course")
							  + "."
							  + InterpretSQL.encodeEndSign("CourseID")
							  + " = "
							  + InterpretSQL.encodeEndSign("Project")
							  + "."
							  + InterpretSQL.encodeEndSign("CourseID")
							  + " AND "
							  + InterpretSQL.encodeEndSign("Project")
							  + "."
							  + InterpretSQL.encodeEndSign("ProjectID")
							  + " = "
							  + projectID.toString();
							  System.out.println(queryStr);
			  getDBConnection();
			  stmt = dbConnection.createStatement();
			  rs = stmt.executeQuery(queryStr);
			  while(rs.next())
			  {
				  Vector student=new Vector();
				  student.addElement(new Integer(rs.getInt(1)));
				  student.addElement(InterpretSQL.transformChinese(rs.getString(2)));
				  allStudents.addElement(student);
			  }
			  if(allStudents.size()>0)
			  {
				  queryStr=" SELECT  "
								  + InterpretSQL.encodeEndSign("Student")
								  + " FROM "
								  + InterpretSQL.encodeEndSign("HomeWork")
								  + " WHERE "
								  + InterpretSQL.encodeEndSign("ProjectID")
								  + " = "
								  + projectID.toString();

				  rs = stmt.executeQuery(queryStr);
				  while(rs.next())
				  {
					  for(int i=0;i<allStudents.size();i++)
					  {
						  returnValue=(Vector)allStudents.elementAt(i);
						  if(((Integer)returnValue.elementAt(0)).equals(new Integer(rs.getInt(1))))
						  {
							  allStudents.removeElementAt(i);
							  break;
						  }
					  }
				  }
			  }
		  }catch(Exception e)
		  {
			  System.out.println(e);
		  }finally
		  {
			  closeResultSet(rs);
			  closeStatement(stmt);
			  closeDBConnection();
		  }
		  return allStudents;
	  }

	  /**
	   * Get the unapproved homework count by projectID
	   */
	  public int getRestCount(Integer projectID)
	  {
		  Statement stmt = null;
		  ResultSet rs = null;
		  int returnValue=0;
		  try
		  {
			  String queryStr="SELECT COUNT (*) FROM "
							  + InterpretSQL.encodeSign("HomeWorkContent")
							  + InterpretSQL.encodeEndSign("HomeWork")
							  + " WHERE "
							  + InterpretSQL.encodeEndSign("HomeWorkContent")
							  + "."
							  + InterpretSQL.encodeEndSign("HomeWorkID")
							  + " = "
							  + InterpretSQL.encodeEndSign("HomeWork")
							  + "."
							  + InterpretSQL.encodeEndSign("HomeWorkID")
							  + " AND "
							  + InterpretSQL.encodeEndSign("HomeWork")
							  + "."
							  + InterpretSQL.encodeEndSign("ProjectID")
							  + " = "
							  + projectID.toString()
							  + " AND "
							  + InterpretSQL.encodeEndSign("HomeWorkContent")
							  + "."
							  + InterpretSQL.encodeEndSign("State")
							  + " = "
							  + InterpretSQL.encodeEndCol("Î´ÅúÔÄ");
			  System.out.println(queryStr);
			  getDBConnection();
			  stmt = dbConnection.createStatement();
			  rs = stmt.executeQuery(queryStr);
			  while(rs.next())
			  {
				  returnValue = rs.getInt(1);
			  }
		  }catch(Exception e)
		  {
			  System.out.println(e);
		  }finally
		  {
			  closeResultSet(rs);
			  closeStatement(stmt);
			  closeDBConnection();
		  }
		  return returnValue;
	  }

	  public Integer isHaveDocURL(String docURL)
	  {
		  Statement stmt = null;
		  ResultSet rs = null;
		  Integer returnValue=new Integer(0);
		  try
		  {
			  String queryStr="SELECT * FROM "
							  + InterpretSQL.encodeEndSign("HomeWorkContent")
							  + " WHERE "
							  + InterpretSQL.encodeEndSign("DocURL")
							  + " = "
							  + InterpretSQL.encodeEndCol(docURL);

			  System.out.println(queryStr);
			  getDBConnection();
			  stmt = dbConnection.createStatement();
			  rs = stmt.executeQuery(queryStr);
			  while(rs.next())
			  {
				  returnValue = new Integer(rs.getInt(1));
			  }
		  }catch(Exception e)
		  {
			  System.out.println(e);
		  }finally
		  {
			  closeResultSet(rs);
			  closeStatement(stmt);
			  closeDBConnection();
		  }
		  return returnValue;
	  }

	  public boolean isHaveOther(String homeworkId)
	  {
		  Statement stmt = null;
		  ResultSet rs = null;
		  boolean returnValue=false;
		  try
		  {
			  String queryStr="SELECT * FROM "
							  + InterpretSQL.encodeEndSign("HomeWorkContent")
							  + " WHERE "
							  + InterpretSQL.encodeEndSign("HomeWorkID")
							  + " = "
							  + homeworkId;

			  getDBConnection();
			  stmt = dbConnection.createStatement();
			  rs = stmt.executeQuery(queryStr);
			  while(rs.next())
			  {
				  returnValue = true;
			  }
		  }catch(Exception e)
		  {
			  System.out.println(e);
		  }finally
		  {
			  closeResultSet(rs);
			  closeStatement(stmt);
			  closeDBConnection();
		  }
		  return returnValue;
	  }
  }