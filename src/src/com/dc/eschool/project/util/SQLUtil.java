package com.dc.eschool.project.util;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public final class SQLUtil
{
  public static String encodeCol(String columnVal)
  {
    if(columnVal == null) return "'',";
    return "'"+columnVal+"',";
  }
  public static String encodeCol(int columnVal)
  {
    return ""+columnVal+",";
  }

  public static String encodeEndCol(String columnVal)
  {
    if(columnVal == null) return "''";
    return "'"+columnVal+"'";
  }
  public static String encodeEndCol(int columnVal)
  {
    return ""+columnVal;
  }
  public static String encodeCol(boolean columnVal)
  {
    if(!columnVal) return "'������',";
    return "'����',";
  }

  public static String encodeEndCol(boolean columnVal)
  {
    if(!columnVal) return "'������'";
    return "'����'";
  }
  public static String encodeCol(boolean columnVal,int flag)
  {
    switch(flag)
    {
      case 1:
      {
        if(!columnVal)
          return "'��ֹ',";
        else
          return "'����',";
      }

      case 2:
      {
        if(!columnVal)
          return "'������',";
        else return "'����',";
      }

    }

    return "'����ȷ',";
  }

  public static String encodeEndCol(boolean columnVal, int flag)
  {
    switch(flag)
    {
      case 1:
      {
        if(!columnVal)
          return "'��ֹ'";
        else
          return "'����'";
      }

      case 2:
      {
        if(!columnVal)
          return "'������'";
        else return "'����'";
      }

    }

    return "'����ȷ'";
  }

  public static String endInsert()
  {
    return ")";
  }
}