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
   * ����Group����ʦ
   */
  public Person getTeacher();

  /**
   * ����GroupNames
   */
  public Vector getGroupNames();

  /**
   * ����ѧ��һ���¼��
   */
  public Vector getStudentLoginNames( String groupName );

  /**
   * ����һ��ѧ��
   */
  public Person getStudent( String groupName, String loginName );

  /**
   * ������������¼��
   */
  public Vector getAuditorLoginNames();

  /**
   * ����������
   */
  public Person getAuditor( String loginName ) ;

  /**
   * �ƶ�fromLoginName��fromGroupName��toGroupName��toLoginNameǰ,
   */
  public void movePerson( String fromGroupName, String fromLoginName, String toGroupName, String toLoginName );

}