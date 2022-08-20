package com.dc.eschool.users.model;

import java.rmi.RemoteException;


/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * This class provides methods to view and modify users
 * information for a particular user.
 */

public class LoginInfoModel{
	protected String loginName;
	protected String userType;
	protected String name;
	protected String ip;
	protected String courseID;
	protected String courseName;
	protected String userID;
	protected String gender;

	/**
	 * Class constructor with no arguments, used by the web tier.
	 */
	public LoginInfoModel()
	{
	}

	// get and set methods for the instance variables
	public void setLoginName(String loginName)
	{
	  this.loginName = loginName;
	}
	public String getLoginName()
	{
	  return loginName;
	}
	public void setUserType(String userType)
	{
	  this.userType = userType;
	}
	public String getUserType()
	{
	  return userType;
	}
	public void setUserID(String userID)
	{
	  this.userID = userID;
	}
	public String getUserID()
	{
	  return userID;
	}
	public void setName(String name) {
	  this.name = name;
	}
	public String getName() {
	  return name;
	}
	public void setIp(String ip) {
	  this.ip = ip;
	}
	public String getIp() {
	  return ip;
	}
  public void setCourseID(String courseID) {
	this.courseID = courseID;
  }
  public String getCourseID() {
	return courseID;
  }
  public void setCourseName(String courseName) {
	this.courseName = courseName;
  }
  public String getCourseName() {
	return courseName;
  }
  public void setGender(String gender) {
	this.gender = gender;
  }
  public String getGender() {
	return gender;
  }

  public String toString()
  {
	return
		"{LoginInfoModel:" +
		"/loginName:" + loginName +
		"/userType:" + userType +
		"/name:" + name +
		"/ip:" + ip +
		"/courseID:" + courseID +
		"/courseName:" + courseName +
		"/userID:" + userID +
		"/gender:" + gender +
		"}";
  }
}