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

public class UsersModel implements java.io.Serializable
{
	protected String allow;
	protected String createDate;
	protected String createTime;
	protected Integer createBy;
	protected String lastModifyDate;
	protected String lastModifyTime;
	protected Integer lastModifyBy;
	protected String loginName;
	protected String password;
	protected String name;
	protected String gender;
	protected String birthday;
	protected String tel;
	protected String email;
	protected String address;
	protected String userType;
	protected String state;
	protected Integer userID;
	protected Integer classID;

	/**
	 * Class constructor with no arguments, used by the web tier.
	 */
	public UsersModel()
	{
	}

	/** shallow copy */
	public void copy(UsersModel other)
	{
	  this.userID = other.userID;
	  this.loginName = other.loginName;
	  this.password = other.password;
	  this.name= other.name;
	  this.gender=other.gender;
	  this.birthday=other.birthday;
	  this.tel=other.tel;
	  this.email=other.email;
	  this.address=other.address;
	  this.classID=other.classID;
	  this.userType=other.userType;
	  this.state=other.state;
	  this.allow = other.allow;
	  this.createDate = other.createDate;
	  this.createTime = other.createTime;
	  this.createBy = other.createBy;
	  this.lastModifyDate = other.lastModifyDate;
	  this.lastModifyTime = other.lastModifyTime;
	  this.lastModifyBy = other.lastModifyBy;
	}

	// get and set methods for the instance variables
	public String getAllow()
	{
	  return allow;
	}
	public String getCreateDate()
	{
	  return createDate;
	}
	public String getCreateTime()
	{
	  return createTime;
	}
	public Integer getCreateBy()
	{
	  return createBy;
	}
	public String getLastModifyDate()
	{
	  return lastModifyDate;
	}
	public String getLastModifyTime()
	{
	  return lastModifyTime;
	}
	public Integer getLastModifyBy()
	{
	  return lastModifyBy;
	}
	public void setAllow(String allow)
	{
	  this.allow = allow;
	}
	public void setCreateBy(Integer createBy)
	{
	  this.createBy = createBy;
	}
	public void setCreateDate(String createDate)
	{
	  this.createDate = createDate;
	}
	public void setCreateTime(String createTime)
	{
	  this.createTime = createTime;
	}
	public void setLastModifyBy(Integer lastModifyBy)
	{
	  this.lastModifyBy = lastModifyBy;
	}
	public void setLastModifyDate(String lastModifyDate)
	{
	  this.lastModifyDate = lastModifyDate;
	}
	public void setLastModifyTime(String lastModifyTime)
	{
	  this.lastModifyTime = lastModifyTime;
	}
	public void setLoginName(String loginName)
	{
	  this.loginName = loginName;
	}
	public String getLoginName()
	{
	  return loginName;
	}
	public void setPassword(String password)
	{
	  this.password = password;
	}
	public String getPassword()
	{
	  return password;
	}
	public void setName(String name)
	{
	  this.name = name;
	}
	public String getName()
	{
	  return name;
	}
	public void setGender(String gender)
	{
	  this.gender = gender;
	}
	public String getGender()
	{
	  return gender;
	}
	public void setBirthday(String birthday)
	{
	  this.birthday = birthday;
	}
	public String getBirthday()
	{
	  return birthday;
	}
	public void setTel(String tel)
	{
	  this.tel = tel;
	}
	public String getTel()
	{
	  return tel;
	}
	public void setEmail(String email)
	{
	this.email = email;
	}
	public String getEmail()
	{
	  return email;
	}
	public void setAddress(String address)
	{
	  this.address = address;
	}
	public String getAddress()
	{
	  return address;
	}
	public void setClassID(Integer classID)
	{
	  this.classID = classID;
	}
	public Integer getClassID()
	{
	  return classID;
	}
	public void setUserType(String userType)
	{
	  this.userType = userType;
	}
	public String getUserType()
	{
	  return userType;
	}
	public void setState(String state)
	{
	  this.state = state;
	}
	public String getState()
	{
	  return state;
	}
	public void setUserID(Integer userID)
	{
	  this.userID = userID;
	}
	public Integer getUserID()
	{
	  return userID;
	}

	public String toString()
	{
		return
			"{UserModel:" +
			"/allow:" + allow +
			"/createDate:" + createDate +
			"/createTime:" + createTime +
			"/createBy:" + createBy +
			"/lastModifyDate:" + lastModifyDate +
			"/lastModifyTime:" + lastModifyTime +
			"/lastModifyBy:" + lastModifyBy +
			"/loginName:" + loginName +
			"/password:" + password +
			"/name:" + name +
			"/gender:" + gender +
			"/birthday:" + birthday +
			"/tel:" + tel +
			"/email:" + email +
			"/address:" + address +
			"/userType:" + userType +
			"/state:" + state +
			"/userID:" + userID +
			"/classID:" + classID +
			"}";
	}
}