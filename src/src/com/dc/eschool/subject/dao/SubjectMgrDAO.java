package com.dc.eschool.subject.dao;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.*;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.subject.exceptions.SubjectDAOFindException;
import com.dc.eschool.subject.model.SubjectModel;

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
 * This class encapsulates search method calls made by the SubjectSLBean.
 * Actual logic of fetching the data in
 * relational database tables to mirror the state of SubjectSLBean is
 * implemented here.
 */
public class SubjectMgrDAO extends GeneralDAO
{
    public SubjectMgrDAO() throws DAOException
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
    public ListChunk searchSubject(String clause, int startindex, int count) throws SubjectDAOFindException
    {
	int listCount = 0;
	ArrayList al = new ArrayList();
        int countAll = 0;
        Statement stmt = null;
	ResultSet rs = null;

        try
	{
            countAll = getCountAll(InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE),clause);
            if (count == 0) count = countAll;

            String sql = "SELECT * FROM "
                      +InterpretSQL.encodeEndSign(DatabaseNames.SUBJECT_TABLE)
                      +clause;

	    getDBConnection();
	    stmt = dbConnection.createStatement();
	    rs = stmt.executeQuery(sql);

	    while(startindex-- > 0 && rs.next()) ;
	    while(count-- > 0 && rs.next())
	    {
		int i = 1;
		SubjectModel cm = new SubjectModel();
		cm.setSubjectID(new Integer(rs.getInt(i++)));
		cm.setName(rs.getString(i++));
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
}