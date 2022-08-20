package com.dc.eschool.answeritem.dao;

import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.util.GeneralDAO;
import com.dc.eschool.util.DatabaseNames;
import com.dc.eschool.util.InterpretSQL;


import com.dc.eschool.answeritem.model.AnswerItemModel;

import com.dc.eschool.answeritem.exceptions.AnswerItemDAOFinderException;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

/**
 * This class encapsulates search method calls made by the AnswerItemSLBean.
 * Actual logic of fetching the data in
 * relational database tables to mirror the state of AnswerItemSLBean is
 * implemented here.
 */
public class AnswerItemMgrDAO extends GeneralDAO
{

  public AnswerItemMgrDAO() throws DAOException
  {
    super();
  }

/**
 * Search data from AnswerItem Table
 *
 * @param clause String "Indicate the SQL after 'WHERE'"
 * @param startindex int the record number of first
 * @param count int the number of showing on the HP
 * @return ListChunk
 * @exception AnswerItemDAOFindException
 */
  public ListChunk searchAnswerItem(String clause, int startIndex, int count) throws AnswerItemDAOFinderException
  {
    int listCount = 0;
    ArrayList al = new ArrayList();
    Statement stmt = null;
    ResultSet rs = null;
    int countAll = 0;
    clause=" WHERE "+InterpretSQL.encodeEndSign("ContentID")+" = "+clause;
    try
    {
      countAll = getCountAll(InterpretSQL.encodeEndSign(DatabaseNames.ANSWERITEM_TABLE),clause);
      if(count==0) count = countAll;
      String sql = "select * from "
		    +InterpretSQL.encodeEndSign(DatabaseNames.ANSWERITEM_TABLE)
		    + clause;
      //System.out.println(sql);
      getDBConnection();
      stmt = dbConnection.createStatement();
      rs = stmt.executeQuery(sql);

      while(startIndex-- > 0 && rs.next()) ;
      while(count-- > 0 && rs.next())
      {
        int i = 1;
        AnswerItemModel am = new AnswerItemModel();
        am.setAnswerItemID(new Integer(rs.getInt(i++)));
        am.setType(rs.getString(i++));
        am.setItemNumber(new Integer(rs.getString(i++)));
        am.setAnswerNumber(rs.getString(i++));
        am.setAnswer(rs.getString(i++));
        am.setMemo(rs.getString(i++));
        am.setContentID(new Integer(rs.getString(i++)));
        am.setAllow(rs.getString(i++));
        am.setCreateDate(rs.getString(i++));
        am.setCreateTime(rs.getString(i++));
        am.setCreateBy(new Integer(rs.getInt(i++)));
        am.setLastModifyDate(rs.getString(i++));
        am.setLastModifyTime(rs.getString(i++));
        am.setLastModifyBy(new Integer(rs.getInt(i++)));

        al.add(am);
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
    return new ListChunk(countAll,al,startIndex,listCount);
  }

  public boolean isHasContent(String contentID)
      {

          Statement stmt = null;
          ResultSet result = null;
	      boolean returnValue = false;
          String queryStr = "SELECT "
			  + InterpretSQL.encodeEndSign("ContentID")
			  + " FROM "
                          + InterpretSQL.encodeEndSign(DatabaseNames.ANSWERITEM_TABLE)
                          + " WHERE "
                          + InterpretSQL.encodeEndSign("ContentID")
                          + " = "
                          + contentID ;

          //System.out.println("queryString is: "+ queryStr);

          try
          {
              getDBConnection();
              stmt = dbConnection.createStatement();
              result = stmt.executeQuery(queryStr);

              if ( result.next()) returnValue=true;

          } catch(SQLException ae)
          {
              System.out.println(ae);
          } finally
          {
              closeResultSet(result);
              closeStatement(stmt);
              closeDBConnection();
          }
	  return returnValue;
      }
}