package com.dc.eschool.util;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class DatabaseSetup
{
  //the sign of Database defer from others
  public static final String DATABASE_SIGN   = "\"";
  public static final String STRING_FIELD_SIGN   = "'";
  public static final String INTEGER_FIELD_SIDN   ="";
  public static final String SEPERATE_SIDN   =",";
  public static final String START_INSERT_SIGN   ="(";
  public static final String END_INSERT_SIGN   =")";

  //Boolean column value
  public static final String TRUE_VALUE   ="'公开'";
  public static final String FALSE_VALUE   ="'不公开'";
  public static final String END_TRUE_VALUE   ="'公开'";
  public static final String END_FALSE_VALUE   ="'不公开'";
  public static final String FLAG_1_TRUE_VALUE   ="'允许'";
  public static final String FLAG_1_FALSE_VALUE   ="'禁止'";
  public static final String FLAG_2_TRUE_VALUE   ="'公开'";
  public static final String FLAG_2_FALSE_VALUE   ="'不公开'";
  public static final String FLAG_OTHERS_VALUE   ="'不明确'";
  public static final String FLAG_1_END_TRUE_VALUE   ="'允许'";
  public static final String FLAG_1_END_FALSE_VALUE   ="'禁止'";
  public static final String FLAG_2_END_TRUE_VALUE   ="'公开'";
  public static final String FLAG_2_END_FALSE_VALUE   ="'不公开'";
  public static final String FLAG_END_OTHERS_VALUE   ="'不明确'";

  //the iendity of the user
  public static final String ADMIN_TYPE_VALUE   ="管理员";
  public static final String TEACHER_TYPE_VALUE   ="教师";
  public static final String STUDENT_TYPE_VALUE   ="学生";
}