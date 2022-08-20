package com.dc.eschool.group;

/**
 * Title:        Group Test
 * Description:  描述一个人的全部信息
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public interface Person
{
  /**
   * 人类型: 教师
   */
  public static final int TEACHER = 0;

  /**
   * 人类型: 学生
   */
  public static final int STUDENT = 1;

  /**
   * 人类型: 旁听
   */
  public static final int AUDITOR = 2;

  /**
   * 性别: 男
   */
  public static final int MALE = 0;

  /**
   * 性别: 女
   */
  public static final int FEMALE = 1;

  /**
   * 返回人名
   */
  public String getName();

  /**
   * 返回人类型: TEACHER, STUDENT OR AUDITOR
   */
  public int getType();

  /**
   * 返回人的性别
   */
  public int getGender();

  /**
   * 返回LoginName
   */
  public String getLoginName();

  /**
   * 如果在教室,返回True
   */
  public boolean InClassroom();

  /**
   * 返回举手状态, 如果举手返回true
   */
  public boolean isHandUp();

  /**
   * 设置举手状态
   */
  public void setHandUp( boolean b);

  /**
   * 返回考试状态 如果在考试返回true
   */
  public boolean examing();

  /**
   * 设置考试状态
   */
  public void setExaming( boolean b);

  /**
   * 返回报到状态 如果报到返回true
   */
  public boolean calling();

  /**
   * 设置报到状态
   */
  public void setCalling( boolean b);

}