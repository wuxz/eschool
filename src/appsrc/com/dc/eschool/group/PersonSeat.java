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
 * Description:  person seat 是Group中的一个位子
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class PersonSeat
{
  /**
   * 组名
   */
  String groupName;

  /**
   * 坐在位子中的人
   */
  Person person = null;

  /**
   * 构造方法
   */
  public PersonSeat()
  {
    this( null, null);
  }

  /**
   * 构造方法
   */
  public PersonSeat( String groupName )
  {
    this( groupName, null );
  }

  /**
   * 构造方法
   */
  public PersonSeat(  String groupName, Person p )
  {
    super();
    this.groupName = groupName;
    this.person = p;
  }

  /**
   * 返回组名
   */
  public String getGroupName()
  {
    return this.groupName;
  }

  /**
   * 设置组名
   */
  public void setGroupName( String groupName )
  {
    this.groupName = groupName;
  }

  /**
   * 返回人
   */
  public Person getPerson()
  {
    return this.person;
  }

  /**
   * 放入人
   */
  public void setPerson( Person p )
  {
    this.person = p;
  }

}