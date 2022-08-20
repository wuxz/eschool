package com.dc.eschool.testresultsitem.dao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.*;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.testresultsitem.model.TestResultsItemModel;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAOFinderException;

import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.ListChunk;


/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

public class TestResultsItemMgrDAO extends GeneralDAO
{

	public TestResultsItemMgrDAO() throws DAOException
	{
	  super();
	}

	/**
	 * Search data from Subject Table
	 * @param clause String "Indicate the SQL after 'WHERE'"
	 * @param startindex int the record number of first
	 * @param count int the number of showing on the HP
	 * @return ListChunk
	 * @exception SubjectDAOFindException
	 */
	public ListChunk searchTestResultsItem(String clause, int startindex, int count) throws TestResultsItemDAOFinderException
	{
	int listCount = 0;
	ArrayList al = new ArrayList();
		int countAll = 0;
		Statement stmt = null;
	ResultSet rs = null;
		String newclause=" WHERE \"ContentID\"="+clause;

		try
	{
			countAll = getCountAll(InterpretSQL.encodeEndSign(DatabaseNames.TESTRESULTSITEM_TABLE),newclause);
			if (count == 0) count = countAll;

			String sql =
				   "select "
				   + InterpretSQL.encodeEndSign("AnswerItem")
				   + "."
				   + InterpretSQL.encodeSign("AnswerNumber")
				   + InterpretSQL.encodeEndSign("AnswerItem")
				   + "."
				   + InterpretSQL.encodeSign("Answer")
				   + InterpretSQL.encodeEndSign("TestResultItem")
				   + "."
				   + InterpretSQL.encodeSign("Answer")
				   + InterpretSQL.encodeEndSign("TestResultItem")
				   + "."
				   + InterpretSQL.encodeSign("IsRight")
				   + InterpretSQL.encodeEndSign("TestResultItem")
				   + "."
				   + InterpretSQL.encodeSign("TestResultItemID")
				   + InterpretSQL.encodeEndSign("AnswerItem")
				   + "."
				   + InterpretSQL.encodeEndSign("ItemNumber")
				   + " from "
				   + InterpretSQL.encodeSign("AnswerItem")
				   + InterpretSQL.encodeEndSign("TestResultItem")
				   + " where "
				   + InterpretSQL.encodeEndSign("AnswerItem")
				   + "."
				   + InterpretSQL.encodeEndSign("ContentID")
				   + "="
				   + InterpretSQL.encodeEndSign("TestResultItem")
				   + "."
				   + InterpretSQL.encodeEndSign("ContentID")
				   + " and "
				   + InterpretSQL.encodeEndSign("AnswerItem")
				   + "."
				   + InterpretSQL.encodeEndSign("AnswerItemID")
				   + "="
				   + InterpretSQL.encodeEndSign("TestResultItem")
				   + "."
				   + InterpretSQL.encodeEndSign("AnswerItemID")
				   + " and "
				   + InterpretSQL.encodeEndSign("AnswerItem")
				   + "."
				   + InterpretSQL.encodeEndSign("ContentID")
				   + "="
				   + clause
				   + " order by "
				   + InterpretSQL.encodeEndSign("ItemNumber");

		getDBConnection();
		stmt = dbConnection.createStatement();
		rs = stmt.executeQuery(sql);
		while(startindex-- > 0 && rs.next()) ;
		while(count-- > 0 && rs.next())
		{
		int i = 1;
		TestResultsItemModel tm = new TestResultsItemModel();

				tm.setAnswerNumber(rs.getString(i++));
				tm.setRightAnswer(rs.getString(i++));
				tm.setAnswer(rs.getString(i++));
				tm.setRight(rs.getString(i++));
				tm.setTestResultItemID(new Integer(rs.getInt(i++)));
		al.add(tm);

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
	 * Search date by Primary Key TestResultsItemID
	 *@param TestResultsItemID String
	 *@return TestResultsItemModel
	 */
	public TestResultsItemModel getTestResultsItem(String testResultItemID)
	{
	  TestResultsItemModel tm = null;
	  Statement stmt = null;
	  ResultSet rs = null;
	  String qstr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.TESTRESULTSITEM_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("testResultItemID")
					  + " = "
			  + testResultItemID ;

	  try {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  rs = stmt.executeQuery(qstr);
		  while (rs.next()) {
		  int i = 0;
		  Integer student = new Integer(rs.getInt(i++));
				  String  right = rs.getString(i++);
				  String  answerMark = rs.getString(i++);
				  String  answer = rs.getString(i++);
				  Integer answerItemID = new Integer(rs.getInt(i++));
				  i = 13;
				  Integer contenID = new Integer(rs.getInt(i++));
				  String  answerNumber = rs.getString(i++);
		  tm = new TestResultsItemModel(student,right,answerMark,answer,answerItemID);
		  }
	  } catch(SQLException se)
	  {
		  System.out.println("getTestResultItem():SQLException while getting " +
			  "class " + testResultItemID + " : " + se.getMessage());
	  } finally {
		  closeResultSet(rs);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  return tm;
	}
	private int getCountAll(String clause) throws TestResultsItemDAOFinderException
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