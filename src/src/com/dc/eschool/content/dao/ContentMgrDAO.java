package com.dc.eschool.content.dao;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.naming.*;
import java.util.*;

import java.sql.SQLException;

import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.content.model.DataModel;

import com.dc.eschool.util.*;
import com.dc.eschool.content.exceptions.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 *          last Modify by Eric Chen
 * @version 1.0
 */

public class ContentMgrDAO extends GeneralDAO
{
  String projectTable = InterpretSQL.encodeEndSign(DatabaseNames.PROJECT_TABLE);
  String pcTable=InterpretSQL.encodeEndSign(DatabaseNames.PROJ_CONT_TABLE);
  String contentTable=InterpretSQL.encodeEndSign(DatabaseNames.CONTENT_TABLE);

  public ContentMgrDAO() throws DAOException
  {
	super();
  }

  public ListChunk searchContent(String clause, int startindex, int count, String value) throws ContentSearchException
  {
	int listCount = 0;
	ArrayList al = new ArrayList();
	int countAll = 0;
	Statement stmt = null;
	ResultSet rs = null;
	clause=" WHERE "
				+ contentTable
				+"."
				+InterpretSQL.encodeEndSign("ContentID")
				+" = "
				+pcTable
				+"."
				+ InterpretSQL.encodeEndSign("ContentID")
				+" AND "
				+pcTable
				+"."
				+ InterpretSQL.encodeEndSign("ProjectID")
				+" = "
				+clause;
		if ("student".equals(value)) clause=clause+" AND "
											+contentTable
							+"."
							+ InterpretSQL.encodeEndSign("State")
							+" = "
							+InterpretSQL.encodeEndCol("公布");
	String sql = "SELECT * FROM "+contentTable +", "+pcTable
				+clause;

	try
	{

	  countAll=getCountAll((contentTable+","+pcTable),clause);
	  if (count==0) count=countAll;
	  getDBConnection();
	  stmt = dbConnection.createStatement();
	  rs = stmt.executeQuery(sql);

	  while(startindex-- > 0 && rs.next()) ;
	  while(count-- > 0 && rs.next())
	  {
		int i = 1;
		ContentModel cm = new ContentModel();
		cm.setContentID(new Integer(rs.getInt(i++)));
		cm.setFileSize(new Integer(rs.getInt(i++)));
		cm.setName(rs.getString(i++));
		cm.setDocURL(rs.getString(i++));
		cm.setInfo(rs.getString(i++));
		cm.setState(rs.getString(i++));
		cm.setType(rs.getString(i++));
		cm.setHasAnswerItem(rs.getString(i++));
		cm.setAllow(rs.getString(i++));
		cm.setCreateTime(rs.getString(i++));
		cm.setCreateDate(rs.getString(i++));
		cm.setCreateBy(new Integer(rs.getInt(i++)));
		cm.setLastModifyDate(rs.getString(i++));
		cm.setLastModifyTime(rs.getString(i++));
		cm.setLastModifyBy(new Integer(rs.getInt(i++)));
	cm.setProjectContentID(new Integer(rs.getInt(i++)));

		al.add(cm);
		listCount++;
	  }


	}
	catch(SQLException se)
	{
	  String ex = "SQLException while search content: "+se.getMessage();
	  Debug.println(ex);
	  throw new ContentSearchException(ex);
	}
	finally
	{
	  closeResultSet(rs);
	  closeStatement(stmt);
	  closeDBConnection();
	}
	return new ListChunk(countAll, al ,startindex,listCount);
  }
  public boolean getProjectContentID(Integer contentID)
  {
	Statement stmt = null;
	ResultSet rs = null;
	String sql="";
	boolean returnValue=false;
	try
	{
	  sql="SELECT "
		  +InterpretSQL.encodeEndSign("ProjectContentID")
		  +" FROM "
		  +InterpretSQL.encodeEndSign(DatabaseNames.PROJ_CONT_TABLE)
		  + " WHERE "
		  +InterpretSQL.encodeEndSign("ContentID")
		  +" = "
		  +InterpretSQL.encodeEndCol(contentID);
		  //System.out.println(sql);
	  getDBConnection();
	  stmt = dbConnection.createStatement();
	  rs = stmt.executeQuery(sql);
	  if(rs.next())
	  {
		returnValue=true;
	  }
	}
	catch(Exception se)
	{
	  String ex = "SQLException while search content: "+se.getMessage();
	  Debug.println(ex);

	}
	finally
	{
	  closeResultSet(rs);
	  closeStatement(stmt);
	  closeDBConnection();
	}
	return returnValue;
  }

  public ListChunk searchPC(String clause, int startindex, int count,String value,String info,String time) throws ContentSearchException
  {
	int listCount = 0;
	ArrayList al = new ArrayList();
	int countAll = 0;
	Statement stmt = null;
	ResultSet rs = null;

	clause=" WHERE "
				+ projectTable
				+"."
				+InterpretSQL.encodeEndSign("State")
				+" = "
				+InterpretSQL.encodeEndCol("公布")
								+" AND "
				+ contentTable
				+"."
				+InterpretSQL.encodeEndSign("State")
				+" = "
				+InterpretSQL.encodeEndCol("公布")
				+" AND "
				+ projectTable
				+"."
				+InterpretSQL.encodeEndSign("ProjectID")
				+" = "
				+pcTable
				+"."
				+ InterpretSQL.encodeEndSign("ProjectID")
				+" AND "
				+pcTable
				+"."
				+ InterpretSQL.encodeEndSign("ContentID")
				+" = "
				+contentTable
				+"."
				+InterpretSQL.encodeEndSign("ContentID")
				+" AND "
				+ InterpretSQL.encodeEndSign("Users")
				+"."
				+ InterpretSQL.encodeEndSign("UserID")
				+ " = "
				+ InterpretSQL.encodeEndSign("Project")
				+"."
				+ InterpretSQL.encodeEndSign("CreateBy")
				+ getClauseQuery(clause)
				+ getInfoQuery(info)
				+ getValueQuery(value)
				+ getTimeQuery(time);

	try
	{
	  String tableName=projectTable+","+contentTable+","+pcTable+","+InterpretSQL.encodeEndSign("Users");

	  countAll=getCountAll(tableName,clause);

	  String sql=getSelectQuery()+clause
				+" ORDER BY "
				+projectTable
				+"."
				+InterpretSQL.encodeEndSign("LastModifyDate")
				+" DESC ,"
				+projectTable
				+"."
				+InterpretSQL.encodeEndSign("LastModifyTime")
				+" DESC ";
	  //System.out.println(sql);
	  if (count==0) count=countAll;
	  getDBConnection();
	  stmt = dbConnection.createStatement();
	  rs = stmt.executeQuery(sql);

	  while(startindex-- > 0 && rs.next()) ;
	  while(count-- > 0 && rs.next())
	  {
		int i = 1;
		DataModel dm = new DataModel();
		dm.setProjectID(new Integer(rs.getInt(i++)));
		dm.setProjectName(rs.getString(i++));
		dm.setCourseID(new Integer(rs.getInt(i++)));
		dm.setProjectInfo(rs.getString(i++));
		dm.setProjectStartDate(rs.getString(i++));
		dm.setProjectEndDate(rs.getString(i++));
		dm.setProjectCreateBy(new Integer(rs.getInt(i++)));
		dm.setProjectLastModifyDate(rs.getString(i++));
		dm.setProjectLastModifyTime(rs.getString(i++));
		dm.setContentID(new Integer(rs.getInt(i++)));
		dm.setDocURL(rs.getString(i++));
		dm.setContentName(rs.getString(i++));

		al.add(dm);
		listCount++;
	  }


	}
	catch(SQLException se)
	{
	  String ex = "SQLException while search content: "+se.getMessage();
	  Debug.println(ex);
	  throw new ContentSearchException(ex);
	}
	finally
	{
	  closeResultSet(rs);
	  closeStatement(stmt);
	  closeDBConnection();
	}
	return new ListChunk(countAll, al ,startindex,listCount);
  }

  public Collection getContentByApp(String projectID)
  {
	ArrayList al = new ArrayList();
	Statement stmt = null;
	ResultSet rs = null;
	String sql = "SELECT "
				+ contentTable
				+"."
				+"* FROM "+contentTable +", "+pcTable+ ","+projectTable
				+" WHERE "
				+ projectTable
				+"."
				+InterpretSQL.encodeEndSign("ProjectID")
				+" = "
				+pcTable
				+"."
				+ InterpretSQL.encodeEndSign("ProjectID")
				+" AND "
				+pcTable
				+"."
				+ InterpretSQL.encodeEndSign("ContentID")
				+" = "
				+contentTable
				+"."
				+InterpretSQL.encodeEndSign("ContentID")
				+" AND "
				+ projectTable
				+"."
				+InterpretSQL.encodeEndSign("ProjectID")
				+" = "
				+projectID;
System.out.println(sql);
	try
	{
	  getDBConnection();
	  stmt = dbConnection.createStatement();
	  rs = stmt.executeQuery(sql);

	  while(rs.next())
	  {
		int i = 1;
		ContentModel cm = new ContentModel();
		cm.setContentID(new Integer(rs.getInt(i++)));
		cm.setFileSize(new Integer(rs.getInt(i++)));
		cm.setName(rs.getString(i++));
		cm.setDocURL(rs.getString(i++));
		cm.setInfo(rs.getString(i++));
		cm.setState(rs.getString(i++));
		cm.setType(rs.getString(i++));
		cm.setHasAnswerItem(rs.getString(i++));
		cm.setAllow(rs.getString(i++));
		cm.setCreateDate(rs.getString(i++));
		cm.setCreateTime(rs.getString(i++));
		cm.setCreateBy(new Integer(rs.getInt(i++)));
		cm.setLastModifyDate(rs.getString(i++));
		cm.setLastModifyTime(rs.getString(i++));
		cm.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(cm);
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
	return al;
  }

  public Collection getCheckedContentByApp(String courseID,String type)
  {
	ArrayList al = new ArrayList();
	Statement stmt = null;
	ResultSet rs = null;
	String sql = "SELECT "
				+ contentTable
				+"."
				+"* FROM "+contentTable +", "+pcTable+ ","+projectTable
				+" WHERE "
				+ projectTable
				+"."
				+InterpretSQL.encodeEndSign("ProjectID")
				+" = "
				+pcTable
				+"."
				+ InterpretSQL.encodeEndSign("ProjectID")
				+" AND "
				+pcTable
				+"."
				+ InterpretSQL.encodeEndSign("ContentID")
				+" = "
				+contentTable
				+"."
				+InterpretSQL.encodeEndSign("ContentID")
				+" AND "
				+projectTable
				+"."
				+ InterpretSQL.encodeEndSign("State")
				+" = "
				+InterpretSQL.encodeEndCol("预备")
				+" AND "
				+projectTable
				+"."
				+ InterpretSQL.encodeEndSign("CourseID")
				+" = "
				+courseID;

	if(!"".equals(type)) sql=sql+" AND "
								+projectTable
								+"."
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
		ContentModel cm = new ContentModel();
		cm.setContentID(new Integer(rs.getInt(i++)));
		cm.setFileSize(new Integer(rs.getInt(i++)));
		cm.setName(rs.getString(i++));
		cm.setDocURL(rs.getString(i++));
		cm.setInfo(rs.getString(i++));
		cm.setState(rs.getString(i++));
		cm.setType(rs.getString(i++));
		cm.setHasAnswerItem(rs.getString(i++));
		cm.setAllow(rs.getString(i++));
		cm.setCreateDate(rs.getString(i++));
		cm.setCreateTime(rs.getString(i++));
		cm.setCreateBy(new Integer(rs.getInt(i++)));
		cm.setLastModifyDate(rs.getString(i++));
		cm.setLastModifyTime(rs.getString(i++));
		cm.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(cm);
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
	return al;
  }

  private String getSelectQuery()
  {
	  return "SELECT "
			  + projectTable
			  +"."
			  + InterpretSQL.encodeSign("ProjectID")
			  + projectTable
			  +"."
			  + InterpretSQL.encodeSign("Name")
			  + projectTable
			  +"."
			  + InterpretSQL.encodeSign("CourseID")
			  + projectTable
			  +"."
			  + InterpretSQL.encodeSign("Info")
			  + projectTable
			  +"."
			  + InterpretSQL.encodeSign("StartDate")
			  + projectTable
			  +"."
			  + InterpretSQL.encodeSign("EndDate")
			  + projectTable
			  +"."
			  + InterpretSQL.encodeSign("CreateBy")
			  + projectTable
			  +"."
			  + InterpretSQL.encodeSign("LastModifyDate")
			  + projectTable
			  +"."
			  + InterpretSQL.encodeSign("LastModifyTime")
			  + contentTable
			  +"."
			  + InterpretSQL.encodeSign("ContentID")
			  + contentTable
			  +"."
			  + InterpretSQL.encodeSign("DocURL")
			  + contentTable
			  +"."
			  + InterpretSQL.encodeEndSign("Name")
			  + " FROM "
			  + projectTable+","
			  + pcTable+","
			  + contentTable+","
			  + InterpretSQL.encodeEndSign("Users");
  }

  private String getValueQuery(String value)
  {
	if (!"".equals(value))
	{
		return " AND "
			  + InterpretSQL.encodeEndSign("Users")
			  +"."
			  + InterpretSQL.encodeEndSign("Name")
			  + " LIKE '%"
			  + InterpretSQL.transform(value)
			  + "%'";
	}else
	{
	  return " ";
	}
  }

  private String getInfoQuery(String info)
  {
	  if(!"".equals(info))
	  {
		return  " AND "
				+ InterpretSQL.encodeEndSign("Project")
				+"."
				+ InterpretSQL.encodeEndSign("Info")
				+ " LIKE '%"
				+ InterpretSQL.transform(info)
				+ "%'";
	  }else
	  {
		return " ";
	  }
  }

  private String getTimeQuery(String time)
  {
	  if(!"".equals(time))
	  {
		return  " AND "
				+ InterpretSQL.encodeEndSign("Project")
				+"."
				+ InterpretSQL.encodeEndSign("StartDate")
				+ " <="
				+ InterpretSQL.encodeEndCol(time)
				+ " AND "
				+ InterpretSQL.encodeEndSign("Project")
				+"."
				+ InterpretSQL.encodeEndSign("EndDate")
				+ " >="
				+ InterpretSQL.encodeEndCol(time);
	  }else
	  {
		return "";
	  }
  }

  private String getClauseQuery(String clause)
  {
	  if(!"".equals(clause))
	  {
		return  " AND "
				+projectTable
				+"."
				+ InterpretSQL.encodeEndSign("Type")
				+" = "
				+ InterpretSQL.encodeEndCol(clause);
	  }else
	  {
		return "";
	  }
  }
}