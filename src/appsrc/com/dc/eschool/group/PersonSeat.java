package com.dc.eschool.group;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableCellRenderer;
import java.io.Serializable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Hashtable;
import java.awt.event.*;
import java.util.Vector;

/**
 * Title:        Group Test
 * Description:  person seat ��Group�е�һ��λ��
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class PersonSeat
{
  /**
   * ����
   */
  String groupName;

  /**
   * ����λ���е���
   */
  Person person = null;

  /**
   * ���췽��
   */
  public PersonSeat()
  {
    this( null, null);
  }

  /**
   * ���췽��
   */
  public PersonSeat( String groupName )
  {
    this( groupName, null );
  }

  /**
   * ���췽��
   */
  public PersonSeat(  String groupName, Person p )
  {
    super();
    this.groupName = groupName;
    this.person = p;
  }

  /**
   * ��������
   */
  public String getGroupName()
  {
    return this.groupName;
  }

  /**
   * ��������
   */
  public void setGroupName( String groupName )
  {
    this.groupName = groupName;
  }

  /**
   * ������
   */
  public Person getPerson()
  {
    return this.person;
  }

  /**
   * ������
   */
  public void setPerson( Person p )
  {
    this.person = p;
  }

}