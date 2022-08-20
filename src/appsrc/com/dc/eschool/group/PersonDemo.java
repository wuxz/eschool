package com.dc.eschool.group;

/**
 * Title:        Group Test
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class PersonDemo implements Person
{
  public String name;
  public String loginName;
  public int type;
  public int gender;
  public boolean inClassroom;
  public boolean examing;

  public boolean handUp;
  public boolean calling;

  public PersonDemo( String name, String loginName, int type, int gender, boolean inClassroom )
  {
    this.name = name;
    this.loginName = loginName;
    this.type = type;
    this.gender = gender;
    this.inClassroom = inClassroom;
    this.handUp = false;
    this.examing=false;
  }
  public String getName()
  {
    return this.name;
    /**@todo: Implement this com.dc.eschool.group.Person method*/
    //throw new java.lang.UnsupportedOperationException("Method getName() not yet implemented.");
  }
  public int getType()
  {
    return this.type;
    /**@todo: Implement this com.dc.eschool.group.Person method*/
    //throw new java.lang.UnsupportedOperationException("Method getType() not yet implemented.");
  }
  public int getGender()
  {
    return this.gender;
    /**@todo: Implement this com.dc.eschool.group.Person method*/
    //throw new java.lang.UnsupportedOperationException("Method getGender() not yet implemented.");
  }
  public String getLoginName()
  {
    return this.loginName;
    /**@todo: Implement this com.dc.eschool.group.Person method*/
    //t/hrow new java.lang.UnsupportedOperationException("Method getLoginName() not yet implemented.");
  }
  public boolean InClassroom()
  {
    return this.inClassroom;
    /**@todo: Implement this com.dc.eschool.group.Person method*/
    //throw new java.lang.UnsupportedOperationException("Method InClassroom() not yet implemented.");
  }

  public boolean isHandUp()
  {
    return this.handUp;
  }

  public void setHandUp( boolean b)
  {
    this.handUp=b;
  }

  public boolean examing()
  {
    return examing;
  }

  public void setExaming( boolean b)
  {
    this.examing = b;
  }


  public String toString()
  {
    return "name:" + this.name + " loginName:" + this.loginName;
  }

  /**
   * 返回报到状态 如果报到返回true
   */
  public boolean calling()
  {
    return this.calling;
  };

  /**
   * 设置报到状态
   */
  public void setCalling( boolean b)
  {
    this.calling = b;
  };

}