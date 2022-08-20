package com.dc.eschool.scorestatistic.dao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.util.*;

import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAOFinderException;
import com.dc.eschool.scorestatistic.model.ScoreStatisticModel;

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

public class ScoreStatisticMgrDAO extends GeneralDAO
{

  public ScoreStatisticMgrDAO() throws DAOException
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
	public ListChunk searchScoreStatistic(String clause, int startindex, int count) throws ScoreStatisticDAOFinderException
	{
	int listCount = 0;
	ArrayList al = new ArrayList();
		int countAll = 0;
		Statement stmt = null;
	ResultSet rs = null;

		try
	{
			countAll = getCountAll(InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE),clause);
			if (count == 0) count = countAll;

			String sql = "SELECT * FROM "
					  +InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE)
					  +clause;

		getDBConnection();
		stmt = dbConnection.createStatement();
		rs = stmt.executeQuery(sql);

		while(startindex-- > 0 && rs.next()) ;
		while(count-- > 0 && rs.next())
		{
		int i = 1;
		ScoreStatisticModel sm = new ScoreStatisticModel();
		sm.setScoreStatisticID(new Integer(rs.getInt(i++)));
				sm.setStudent(new Integer(rs.getInt(i++)));
				sm.setAnswerItemID(rs.getString(i++));
				sm.setProjectContentID(new Integer(rs.getInt(i++)));
		sm.setStatistic(rs.getString(i++));
				sm.setRightAnswer(new Integer(rs.getInt(i++)));
				sm.setWrongAnswer(new Integer(rs.getInt(i++)));

		sm.setAllow(rs.getString(i++));
		sm.setCreateDate(rs.getString(i++));
		sm.setCreateTime(rs.getString(i++));
		sm.setCreateBy(new Integer(rs.getInt(i++)));
		sm.setLastModifyDate(rs.getString(i++));
		sm.setLastModifyTime(rs.getString(i++));
		sm.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(sm);
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
	 * Search data from Subject Table
	 * @param clause String "Indicate the SQL after 'WHERE'"
	 * @param startindex int the record number of first
	 * @param count int the number of showing on the HP
	 * @return ListChunk
	 * @exception SubjectDAOFindException
	 */
	public ListChunk searchScoreStatisticProject(String projectID, int startindex, int count, boolean isAsc) throws ScoreStatisticDAOFinderException
	{
		String querySql =
		   ", "
		   + InterpretSQL.encodeSign("ProjectContent")
		   + InterpretSQL.encodeEndSign("AnswerItem")
		   + " where "
		   + InterpretSQL.encodeEndSign("AnswerItem")
		   + "."
		   +  InterpretSQL.encodeEndSign("AnswerItemID")
		   + " = "
		   +  InterpretSQL.encodeEndSign("ScoreStatistic")
		   + "."
		   +  InterpretSQL.encodeEndSign("AnswerItemID")
		   + " and "
		   +  InterpretSQL.encodeEndSign("ProjectContent")
		   + "."
		   +  InterpretSQL.encodeEndSign("ProjectID")
		   + " = "
		   + projectID
		   + " and "
		   +  InterpretSQL.encodeEndSign("ScoreStatistic")
		   + "."
		   +  InterpretSQL.encodeEndSign("ProjectContentID")
		   + " = "
		   +  InterpretSQL.encodeEndSign("ProjectContent")
		   + "."
		   +  InterpretSQL.encodeEndSign("ProjectContentID");

	int listCount = 0;
	ArrayList al = new ArrayList();
		int countAll = 0;
		Statement stmt = null;
	ResultSet rs = null;

		try
	{
			countAll = getCountAll(InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE),querySql);
			if (count == 0) count = countAll;

		   querySql += " order by "
		   +  InterpretSQL.encodeEndSign("ScoreStatistic")
		   + "."
		   +  InterpretSQL.encodeEndSign("Statistic");

			if (!isAsc)
				querySql += " desc";

			querySql += ", "
				+  InterpretSQL.encodeEndSign("AnswerItem")
				+ "."
				+  InterpretSQL.encodeEndSign("ItemNumber")
				+ " asc";

			String sql = "SELECT * FROM "
					  +InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE)
					  +querySql;


		getDBConnection();
		stmt = dbConnection.createStatement();
		rs = stmt.executeQuery(sql);

		while(startindex-- > 0 && rs.next()) ;
		while(count-- > 0 && rs.next())
		{
		int i = 1;
		ScoreStatisticModel sm = new ScoreStatisticModel();
		sm.setScoreStatisticID(new Integer(rs.getInt(i++)));
				sm.setStudent(new Integer(rs.getInt(i++)));
				sm.setAnswerItemID(rs.getString(i++));
				sm.setProjectContentID(new Integer(rs.getInt(i++)));
		sm.setStatistic(rs.getString(i++));
				sm.setRightAnswer(new Integer(rs.getInt(i++)));
				sm.setWrongAnswer(new Integer(rs.getInt(i++)));

		sm.setAllow(rs.getString(i++));
		sm.setCreateDate(rs.getString(i++));
		sm.setCreateTime(rs.getString(i++));
		sm.setCreateBy(new Integer(rs.getInt(i++)));
		sm.setLastModifyDate(rs.getString(i++));
		sm.setLastModifyTime(rs.getString(i++));
		sm.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(sm);
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
	 * Search date by Primary Key SubjectID
	 *@param subjectId String
	 *@return SubjectModel
	 */
	public ScoreStatisticModel getScoreStatistic(String scoreStatisticId)
	{
	  ScoreStatisticModel sm = null;
	  Statement stmt = null;
	  ResultSet rs = null;
	  String qstr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.SCORESTATISTIC_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ScoreStatisticID")
					  + " = "
			  + scoreStatisticId ;

	  try {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  rs = stmt.executeQuery(qstr);
		  while (rs.next()) {
		  int i = 1;

				  Integer student=new Integer(rs.getInt(i++));
				  String  answerNumber= rs.getString(i++);
				  Integer projectContentID=new Integer(rs.getInt(i++));
			  String  statistic=rs.getString(i++);
				  Integer rightAnswer=new Integer(rs.getInt(i++));
				  Integer wrongAnswer=new Integer(rs.getInt(i++));

		  sm = new ScoreStatisticModel(student,answerNumber,projectContentID,statistic,rightAnswer,wrongAnswer);
		  }
	  } catch(SQLException se)
	  {
		  System.out.println("getSubject():SQLException while getting " +
			  "class " + scoreStatisticId + " : " + se.getMessage());
	  } finally {
		  closeResultSet(rs);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  return sm;
	}
}