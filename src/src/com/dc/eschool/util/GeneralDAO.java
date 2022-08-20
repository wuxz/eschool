package com.dc.eschool.util;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.naming.*;

import java.sql.SQLException;
import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.taglib.list.*;
import com.dc.eschool.util.InterpretSQL;


/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * This Class is a general Database Access Object include looking up
 * datasource, getting connection and setting free the resource methods.
 * */

public class GeneralDAO
{
   protected Connection dbConnection = null;
   protected DataSource datasource = null;

   public GeneralDAO() throws DAOException
   {
        try
        {
            InitialContext ic = new InitialContext();
            datasource = (DataSource) ic.lookup(JNDINames.ESCHOOL_DATASOURCE);
        }
        catch (NamingException ne)
        {
            throw new DAOException("NamingException while looking " +
                            "up DB context  : " + ne.getMessage());
        }
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
   }

   /**
   Get connection from Datasource.
   */
   protected void getDBConnection() throws SQLException
   {
      try
      {
        dbConnection =  datasource.getConnection("eschool","eschool");
      }
      catch (SQLException se)
      {
        Debug.println("SQLException while getDBConnection: "+ se.getMessage());
        throw se;
      }
   }

   /**
   return connection to datasource pool.
   */
   protected void closeDBConnection()
   {
      try
      {
        if (dbConnection != null && !dbConnection.isClosed())
        {
          dbConnection.close();
        }
      }
      catch(SQLException se)
      {
          Debug.println("SQLException while closeConnection: "+ se.getMessage());
      }
   }

   /**
   release Statement.
   */
   protected void closeStatement(java.sql.Statement stmt)
   {
      try
      {
          if (stmt != null)
          {
              stmt.close();
          }
      }
      catch(SQLException se)
      {
          Debug.println("SQLException while closeStatement:" + se.getMessage());
      }
   }

   /**
   set ResultSet free.
   */
   protected void closeResultSet(java.sql.ResultSet rs)
   {
      try
      {
          if (rs != null)
          {
              rs.close();
          }
      }
      catch(SQLException se)
      {
          Debug.println("SQLException while closeResultSet: "+se.getMessage());
      }
   }
  private void jbInit() throws Exception
  {
  }
  protected int getCountAll(String tablename, String clause) throws SQLException
  {
    String sql = "SELECT COUNT(*) FROM ";
    int ret = 0;
    if(tablename == null) throw new SQLException("must given tablename.");
    sql +=tablename;
    if(clause != null)
      sql += clause;
    Statement stmt = null;
    ResultSet rs = null;

    //System.out.println(sql);

    try
    {
      getDBConnection();
      stmt = dbConnection.createStatement();
      rs = stmt.executeQuery(sql);
      if(!rs.next())
        throw new SQLException("getCountAll: no result.");
      ret = rs.getInt(1);
    }
    catch(SQLException se)
    {
      throw se;
    }
    finally
    {
      closeResultSet(rs);
      closeStatement(stmt);
      closeDBConnection();
    }
    return ret;
  }
}
