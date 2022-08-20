package com.dc.eschool.group;

import java.io.Serializable;
import java.util.Vector;

/**
 * Title:        Group Test
 * Description:  Data Model for GroupComponent
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public interface GroupModel extends Serializable
{
  /**
   * Adds a listener to the list that is notified each time a change to the data model occurs.
   */
  public void addGroupModelListener( GroupModelListener l );

  /**
   * Removes a listener from the list that is notified each time a change to the data model occurs.
   */
  public void removeGroupModelListener( GroupModelListener l );

  /**
   * 返回Group的老师
   */
  public Person getTeacher();

  /**
   * 返回GroupNames
   */
  public Vector getGroupNames();

  /**
   * 返回学生一组登录名
   */
  public Vector getStudentLoginNames( String groupName );

  /**
   * 返回一个学生
   */
  public Person getStudent( String groupName, String loginName );

  /**
   * 返回旁听生登录名
   */
  public Vector getAuditorLoginNames();

  /**
   * 返回旁听生
   */
  public Person getAuditor( String loginName ) ;

  /**
   * 移动fromLoginName从fromGroupName到toGroupName的toLoginName前,
   */
  public void movePerson( String fromGroupName, String fromLoginName, String toGroupName, String toLoginName );

}