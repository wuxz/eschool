package com.dc.eschool.bbs;

import java.sql.*;
import java.sql.ResultSet;

import java.sql.SQLException;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.util.GeneralDAO;


/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

/**
 * This Class is a general Database Access Object include looking up
 * datasource, getting connection and setting free the resource methods.
 * */

public class BbsDAO extends GeneralDAO
{
	PreparedStatement stmt = null;
	ResultSet rst = null;

	public BbsDAO() throws DAOException
	{
		super();
	}

	public ResultSet executeQuery(String sql)
	{
		close();

		try
		{
			getDBConnection();
			stmt = dbConnection.prepareStatement(sql);
			rst = stmt.executeQuery(sql);
		}
		catch(Throwable ex)
		{
			System.err.println("aq.executeQuery: " + ex.getMessage());
			System.err.println("aq.executeQuerystrSQL: " + sql);

			rst = null;

			close();
		}

		return rst;
	}

	public void executeUpdate(String sql)
	{
		close();

		try
		{
			getDBConnection();
			stmt = dbConnection.prepareStatement(sql);
			stmt.executeUpdate(sql);
		}
		catch(Throwable ex)
		{
			System.err.println("aq.executeQuery: " + ex.getMessage());
			System.err.println("aq.executeQuerystrSQL: " + sql);

			rst = null;

			close();
		}
	}

	public void close()
	{
		try
		{
			if (rst != null)
			   closeResultSet(rst);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		try
		{
			if (stmt != null)
			   closeStatement(stmt);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		try
		{
			closeDBConnection();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		rst = null;
		stmt = null;
	}

	public ResultSet listBoard(String clause, String orderBy)
	{
		String sql =
			   "select * from " +
			   InterpretSQL.encodeEndSign("Board") +
			   (clause == null ? "" : clause) +
			   " order by " +
			   InterpretSQL.encodeEndSign(orderBy);

		return executeQuery(sql);
	}

	public ResultSet listBbs(String clause, String orderBy)
	{
		String sql =
			   "select * from " +
			   InterpretSQL.encodeEndSign("Bbs") +
			   (clause == null ? "" : clause) +
			   " order by " +
			   InterpretSQL.encodeEndSign(orderBy);

		return executeQuery(sql);
	}

	public ResultSet listBbsUser(String clause, String orderBy)
	{
		String sql =
			   "select distinct " +
			   InterpretSQL.encodeEndSign("UserName") +
			   " from " +
			   InterpretSQL.encodeEndSign("Bbs") +
			   (clause == null ? "" : clause) +
			   " order by " +
			   InterpretSQL.encodeEndSign(orderBy);

		return executeQuery(sql);
	}

	public void updateBoardHits(int boardID, int nNum)
	{
		String sql =
			   "update " +
			   InterpretSQL.encodeEndSign("Board") +
			   "set " +
			   InterpretSQL.encodeEndSign("BoardHits") +
			   " = " +
			   InterpretSQL.encodeEndSign("BoardHits") +
			   " + " +
			   nNum +
			   " where " +
			   InterpretSQL.encodeEndSign("BoardID") +
			   " = " +
			   boardID;

		executeUpdate(sql);

		close();
	}

	public void updateBoardTopics(int boardID, int nNum)
	{
		String sql =
			   "update " +
			   InterpretSQL.encodeEndSign("Board") +
			   "set " +
			   InterpretSQL.encodeEndSign("BoardTopics") +
			   " = " +
			   InterpretSQL.encodeEndSign("BoardTopics") +
			   " + " +
			   nNum +
			   " where " +
			   InterpretSQL.encodeEndSign("BoardID") +
			   " = " +
			   boardID;

		executeUpdate(sql);

		close();
	}

	public int getBbsCount(int method, int boardid, String par)
	{
		String clause = null;
		if (par == null)
		   par = "";

		switch (method)
		{
			case 1:
				 clause = InterpretSQL.encodeEndSign("BbsTopic") +
				 " like '%" + par + "%'";
				 break;

			case 2:
				 clause = InterpretSQL.encodeEndSign("UserName") +
				 " = '" + par + "'";
				 break;

			case 3:
				 clause = InterpretSQL.encodeEndSign("DateAndTime") +
				 " >= " +
				 "now() - " + par;
				 break;

			default:
				 clause = InterpretSQL.encodeEndSign("ParentID") +
				 " = 0 ";
				 break;
		}

		String sql =
			   "select count(*) " +
			   " from " +
			   InterpretSQL.encodeEndSign("Bbs") +
			   " where " +
			   InterpretSQL.encodeEndSign("BoardID") +
			   " = " +
			   boardid;

		if (clause != null)
			   sql += " and " + clause;

		executeQuery(sql);

		int nRet = 0;
		try
		{
			rst.next();
			nRet = rst.getInt(1);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		close();

		return nRet;
	}

	public boolean checkBoardName(String boardname)
	{
		String sql =
			   "select " +
			   InterpretSQL.encodeEndSign("BoardID") +
			   " from " +
			   InterpretSQL.encodeEndSign("Board") +
			   " where " +
			   InterpretSQL.encodeEndSign("BoardName") +
			   " = " +
			   InterpretSQL.encodeEndCol(boardname);

		boolean bRet = false;
		try
		{
			executeQuery(sql);

			if (rst.next())
				bRet = true;
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		close();

		return bRet;
	}

	public void addNewBoard(String boardname)
	{
		try
		{
			String sql =
				   "insert into " +
				   InterpretSQL.encodeEndSign("Board") +
				   "(" +
				   InterpretSQL.encodeEndSign("BoardName") +
				   ") values(?)";

			getDBConnection();
			stmt = dbConnection.prepareStatement(sql);
			stmt.setString(1, boardname);

			stmt.executeUpdate();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		close();
	}

	public void delBoard(int boardid)
	{
		try
		{
			String sql =
				   "delete from " +
				   InterpretSQL.encodeEndSign("Board") +
				   " where " +
				   InterpretSQL.encodeEndSign("BoardID") +
				   "= ?";

			getDBConnection();
			stmt = dbConnection.prepareStatement(sql);
			stmt.setInt(1, boardid);

			stmt.executeUpdate();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		close();
	}

	public void delBbs(int bbsid)
	{
		try
		{
			String sql =
				   "delete from " +
				   InterpretSQL.encodeEndSign("Bbs") +
				   " where " +
				   InterpretSQL.encodeEndSign("BbsID") +
				   "= ?";

			getDBConnection();
			stmt = dbConnection.prepareStatement(sql);
			stmt.setInt(1, bbsid);

			stmt.executeUpdate();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		close();
	}

	public ResultSet listBbs(int method, int boardid, String par)
	{
		String clause =
			   " where " +
			   InterpretSQL.encodeEndSign("BoardID") +
			   " = " +
			   boardid +
			   " and " +
			   InterpretSQL.encodeEndSign("ParentID") +
			   " = 0 ";

		if (par == null)
		   par = "";

		switch (method)
		{
			case 1:
				 clause = clause + " and " + InterpretSQL.encodeEndSign("BbsTopic") +
				 " like '%" + par + "%'";
				 break;

			case 2:
				 clause = clause + " and " + InterpretSQL.encodeEndSign("UserName") +
				 " = '" + par + "'";
				 break;

			case 3:
				 clause = clause + " and " + InterpretSQL.encodeEndSign("DateAndTime") +
				 " >= " +
				 "now() - " + par;
				 break;
		}

		return listBbs(clause, "BbsID");
	}

	public ResultSet listBbsRe(int bbsid)
	{
		String clause =
			   "where " +
			   InterpretSQL.encodeEndSign("ParentID") +
			   " = " +
			   bbsid;

		return listBbs(clause, "BbsID");
	}

	public void addNewBbs(int parentid, int boardid, int child,
		   String username,
		   String expression, String usersign, String bbstopic,
		   String bbscontent, int bbshits, int length)
	{
		try
		{
			String sql =
				   "insert into " +
				   InterpretSQL.encodeEndSign("Bbs") +
				   "(" +
				   InterpretSQL.encodeSign("DateAndTime") +
				   InterpretSQL.encodeSign("ParentID") +
				   InterpretSQL.encodeSign("BoardID") +
				   InterpretSQL.encodeSign("Child") +
				   InterpretSQL.encodeSign("UserName") +
				   InterpretSQL.encodeSign("Expression") +
				   InterpretSQL.encodeSign("UserSign") +
				   InterpretSQL.encodeSign("BbsTopic") +
				   InterpretSQL.encodeSign("BbsContent") +
				   InterpretSQL.encodeSign("BbsHits") +
				   InterpretSQL.encodeEndSign("Length") +
				   ") values(now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			getDBConnection();
			stmt = dbConnection.prepareStatement(sql);
			stmt.setInt(1, parentid);
			stmt.setInt(2, boardid);
			stmt.setInt(3, child);
			stmt.setString(4, username);
			stmt.setString(5, expression);
			stmt.setString(6, usersign);
			stmt.setString(7, bbstopic);
			stmt.setString(8, bbscontent);
			stmt.setInt(9, bbshits);
			stmt.setInt(10, length);

			stmt.executeUpdate();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		close();
	}

	public void changeBbsChild(int parentid, int num)
	{
		try
		{
			String sql =
				   "update  " +
				   InterpretSQL.encodeEndSign("Bbs") +
				   "set " +
				   InterpretSQL.encodeEndSign("Child") +
				   " = " +
				   InterpretSQL.encodeEndSign("Child") +
				   " + " +
				   num +
				   " where " +
				   InterpretSQL.encodeEndSign("BbsID") +
				   " = " +
				   parentid;

			executeUpdate(sql);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	public void changeBbsHits(int bbsID, int nNum)
	{
		String sql =
			   "update " +
			   InterpretSQL.encodeEndSign("Bbs") +
			   "set " +
			   InterpretSQL.encodeEndSign("BbsHits") +
			   " = " +
			   InterpretSQL.encodeEndSign("BbsHits") +
			   " + " +
			   nNum +
			   " where " +
			   InterpretSQL.encodeEndSign("BbsID") +
			   " = " +
			   bbsID;

		executeUpdate(sql);

		close();
	}
}
