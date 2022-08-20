package com.dc.eschool.util;

import java.io.UnsupportedEncodingException;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class InterpretSQL
{
  /**
   * 给“字符”字段加标示符(SQL语句中间的字段)
   */
  public static String encodeCol(String columnVal)
  {
	if(columnVal == null){
	  return DatabaseSetup.STRING_FIELD_SIGN
			+DatabaseSetup.STRING_FIELD_SIGN
			+DatabaseSetup.SEPERATE_SIDN;
	}else{
	  return DatabaseSetup.STRING_FIELD_SIGN
			+columnVal
			+DatabaseSetup.STRING_FIELD_SIGN
			+DatabaseSetup.SEPERATE_SIDN;
	}
  }

  /**
   * 给“int”字段加标示符(SQL语句中间的字段)
   */
  public static String encodeCol(int columnVal)
  {
	return DatabaseSetup.INTEGER_FIELD_SIDN
		  +columnVal
		  +DatabaseSetup.INTEGER_FIELD_SIDN
		  +DatabaseSetup.SEPERATE_SIDN;
  }

  public static String encodeCol(Integer columnVal)
  {
	return DatabaseSetup.INTEGER_FIELD_SIDN
		  +columnVal
		  +DatabaseSetup.INTEGER_FIELD_SIDN
		  +DatabaseSetup.SEPERATE_SIDN;
  }
  /**
   * 给“String”字段加标示符(SQL语句最后字段)
   */
  public static String encodeEndCol(String columnVal)
  {
	if(columnVal == null)
	{
	  return DatabaseSetup.STRING_FIELD_SIGN
			+DatabaseSetup.STRING_FIELD_SIGN;
	}else
	{
	  return DatabaseSetup.STRING_FIELD_SIGN
			+columnVal
			+DatabaseSetup.STRING_FIELD_SIGN;
	}
  }

  /**
   * 给“int”字段加标示符(SQL语句最后字段)
   */
  public static String encodeEndCol(int columnVal)
  {
	return DatabaseSetup.INTEGER_FIELD_SIDN
		  +columnVal
		  +DatabaseSetup.INTEGER_FIELD_SIDN;
  }

  public static String encodeEndCol(Integer columnVal)
  {
	return DatabaseSetup.INTEGER_FIELD_SIDN
		  +columnVal
		  +DatabaseSetup.INTEGER_FIELD_SIDN;
  }

  public static String encodeCol(boolean columnVal)
  {
	if(!columnVal)
	{
	  return DatabaseSetup.FALSE_VALUE
			+DatabaseSetup.SEPERATE_SIDN;
	}else
	{
	  return DatabaseSetup.TRUE_VALUE
			+DatabaseSetup.SEPERATE_SIDN;
	}
  }

  public static String encodeEndCol(boolean columnVal)
  {
	if(!columnVal)
	{
	  return DatabaseSetup.END_FALSE_VALUE
			+DatabaseSetup.SEPERATE_SIDN;
	}else
	{
	  return DatabaseSetup.END_TRUE_VALUE
			+DatabaseSetup.SEPERATE_SIDN;
	}
  }
  public static String encodeCol(boolean columnVal,int flag)
  {
	switch(flag)
	{
	  case 1:
	  {
		if(!columnVal)
		{
		  return DatabaseSetup.FLAG_1_FALSE_VALUE
				+DatabaseSetup.SEPERATE_SIDN;
		}
		else
		{
		  return DatabaseSetup.FLAG_1_TRUE_VALUE
				+DatabaseSetup.SEPERATE_SIDN;
		}
	  }

	  case 2:
	  {
		if(!columnVal)
		{
		  return DatabaseSetup.FLAG_2_FALSE_VALUE
				+DatabaseSetup.SEPERATE_SIDN;
		}
		else
		{
		  return DatabaseSetup.FLAG_2_TRUE_VALUE
				+DatabaseSetup.SEPERATE_SIDN;
		}
	  }

	}

	return DatabaseSetup.FLAG_OTHERS_VALUE
		  +DatabaseSetup.SEPERATE_SIDN;
  }

  public static String encodeEndCol(boolean columnVal, int flag)
  {
	switch(flag)
	{
	  case 1:
	  {
		if(!columnVal)
		{
		  return DatabaseSetup.FLAG_1_END_FALSE_VALUE
				+DatabaseSetup.SEPERATE_SIDN;
		}
		else
		{
		  return DatabaseSetup.FLAG_1_END_TRUE_VALUE
				+DatabaseSetup.SEPERATE_SIDN;
		}
	  }

	  case 2:
	  {
		if(!columnVal)
		{
		  return DatabaseSetup.FLAG_2_END_FALSE_VALUE
				+DatabaseSetup.SEPERATE_SIDN;
		}
		else
		{
		  return DatabaseSetup.FLAG_2_END_TRUE_VALUE
				+DatabaseSetup.SEPERATE_SIDN;
		}
	  }

	}

	return DatabaseSetup.FLAG_END_OTHERS_VALUE
		  +DatabaseSetup.SEPERATE_SIDN;
  }

  public static String endInsert()
  {
	return DatabaseSetup.END_INSERT_SIGN;
  }

  public static String startInsert()
  {
	return DatabaseSetup.START_INSERT_SIGN;
  }

  public static String encodeSign(String crudeValue)
  {
	if(crudeValue == null)
	{
	  return DatabaseSetup.DATABASE_SIGN
			+DatabaseSetup.DATABASE_SIGN
			+DatabaseSetup.SEPERATE_SIDN
			+" ";
	}else
	{
	  return DatabaseSetup.DATABASE_SIGN
			+crudeValue
			+DatabaseSetup.DATABASE_SIGN
			+DatabaseSetup.SEPERATE_SIDN
			+" ";
	}
  }

  public static String encodeEndSign(String crudeValue)
  {
	if(crudeValue == null)
	{
	  return DatabaseSetup.DATABASE_SIGN
			+DatabaseSetup.DATABASE_SIGN
			+" ";
	}else
	{
	  return DatabaseSetup.DATABASE_SIGN
			+crudeValue
			+DatabaseSetup.DATABASE_SIGN
			+" ";
	}
  }

  public static String getTrueValue()
  {
	return DatabaseSetup.FLAG_1_TRUE_VALUE;
  }

  /**转换所有的中文字体。
  */
  public static String transform(String newString)
  {
	  String submit=newString;
	  try
	  {
            if(submit!=null)
            {
		submit=submit.replace('\'','`').replace('"','`');
                submit=new String(submit.getBytes("ISO8859_1"),"GBK");
            }else
            {
                submit="";
            }
	  }
	  catch( UnsupportedEncodingException e)
	  {
		e.printStackTrace();
	  }
	  return submit;
  }

/**转换所有的中文字体。
  */
  public static String transformChinese(String newString)
  {
	  String submit=null;
	  submit=newString;
	  try
	  {
		if(submit!=null)
		{
		  submit=new String(submit.getBytes("GBK"),"ISO8859_1");
		}else
		{
		  submit="";
		}
	  }
	  catch( UnsupportedEncodingException e)
	  {
		e.printStackTrace();
	  }
	  return submit;
  }

  public static String updatePair(String col, String val)
  {
	return col+"="+val;
  }
  /** the stream comes from web page form is encoded in ISO8859_1.(if the form
   *  is sybmited by POST method.the GET method encode data by urlencode.) we should encode
   *  them in GBK or GB2312, then put them into database.when output to web page
   *  what we should only do just set page charset to gb2312 or gbk and output
   *  string in database directly.of course this is my own case,this maybe cause
   *  more problems.another case,i am sure i can insert the iso8859 stream in db
   *  directly,only encode it in gbk when output,but i think it is painful that
   *  we can not display chinese charaters normally while debugging.
   *
   */
   public static String fromGBEncode(String iso)
   {
	  String gbk = "";
	  try
	  {
		gbk = new String(iso.getBytes("ISO8859_1"),"GBK");
	  }
	  catch(Exception e)
	  {
		//do nothing,let method return empty string.
	  }
	  return gbk;
   }

   public static String genSql1(String strField, int nValue, String strOrderBy)
   {
	return "\"" + strField + " = " + nValue + " order by \"" + strOrderBy + "\"";
   }
}