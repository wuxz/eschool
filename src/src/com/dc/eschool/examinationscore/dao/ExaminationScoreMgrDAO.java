package com.dc.eschool.examinationscore.dao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAOFinderException;
import com.dc.eschool.examinationscore.model.ExaminationScoreModel;

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

public class ExaminationScoreMgrDAO extends GeneralDAO
{

  public ExaminationScoreMgrDAO() throws DAOException
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
	public ListChunk searchExaminationScore(String clause, int startindex, int count) throws ExaminationScoreDAOFinderException
	{
	int listCount = 0;
	ArrayList al = new ArrayList();
		int countAll = 0;
		Statement stmt = null;
	ResultSet rs = null;

		try
	{
			countAll = getCountAll(InterpretSQL.encodeEndSign(DatabaseNames.EXAMINATIONSCORE_TABLE),clause);
			if (count == 0) count = countAll;

			String sql = "SELECT * FROM "
					  + InterpretSQL.encodeEndSign(DatabaseNames.EXAMINATIONSCORE_TABLE)
					  + clause
					  + " order by "
					  + InterpretSQL.encodeEndSign("Score")
					  + " desc";

		getDBConnection();
		stmt = dbConnection.createStatement();
		rs = stmt.executeQuery(sql);

		while(startindex-- > 0 && rs.next()) ;
		while(count-- > 0 && rs.next())
		{
		int i = 1;
		ExaminationScoreModel em = new ExaminationScoreModel();
		em.setExaminationID(new Integer(rs.getInt(i++)));
				em.setProjectID(new Integer(rs.getInt(i++)));
				em.setStudent(new Integer(rs.getInt(i++)));
		em.setScore(new Integer(rs.getString(i++)));
		em.setAllow(rs.getString(i++));
		em.setCreateDate(rs.getString(i++));
		em.setCreateTime(rs.getString(i++));
		em.setCreateBy(new Integer(rs.getInt(i++)));
		em.setLastModifyDate(rs.getString(i++));
		em.setLastModifyTime(rs.getString(i++));
		em.setLastModifyBy(new Integer(rs.getInt(i++)));

		al.add(em);
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
	public ExaminationScoreModel getExaminationScore(String examinationId)
	{
	  ExaminationScoreModel em = null;
	  Statement stmt = null;
	  ResultSet rs = null;
	  String qstr = "SELECT * FROM "
			  + InterpretSQL.encodeEndSign(DatabaseNames.EXAMINATIONSCORE_TABLE)
			  + " WHERE "
			  + InterpretSQL.encodeEndSign("ExaminationID")
					  + " = "
			  + examinationId ;

	  try {
		  getDBConnection();
		  stmt = dbConnection.createStatement();
		  rs = stmt.executeQuery(qstr);
		  while (rs.next()) {
		  int i = 2;
		  Integer projectID = new Integer(rs.getString(i++));
				  Integer student = new Integer(rs.getString(i++));
				  Integer score = new Integer(rs.getString(i++));
				  i=12;
				  Integer testResultItemID = new Integer(rs.getString(i));

		  em = new ExaminationScoreModel(projectID,student,score,testResultItemID);
		  }
	  } catch(SQLException se)
	  {
		  System.out.println("getSubject():SQLException while getting " +
			  "class " + examinationId + " : " + se.getMessage());
	  } finally {
		  closeResultSet(rs);
		  closeStatement(stmt);
		  closeDBConnection();
	  }
	  return em;
	}
}