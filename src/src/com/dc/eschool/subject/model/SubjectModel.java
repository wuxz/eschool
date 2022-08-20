package com.dc.eschool.subject.model;

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
 * This class provides methods to view and modify Subject
 * information for a particular Subject.
 */
public class SubjectModel implements java.io.Serializable
{
	protected String allow;
	protected String createDate;
	protected String createTime;
	protected Integer createBy;
	protected String lastModifyDate;
	protected String lastModifyTime;
	protected Integer lastModifyBy;
	protected Integer subjectID;
	protected String name;

	/**
	* Subject constructor with no arguments, used by the web tier.
	*/
	public SubjectModel()
	{
	}

	public void copy(SubjectModel other)
	{
	this.subjectID = other.subjectID;
	this.name = other.name;
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
	public void setSubjectID(Integer subjectID)
	{
	  this.subjectID = subjectID;
	}
	public Integer getSubjectID()
	{
	  return subjectID;
	}
	public void setName(String name)
	{
	  this.name = name;
	}
	public String getName()
	{
	  return name;
	}

	public String toString()
	{
		return
			"{SubjectModel:" +
			"/allow:" + allow +
			"/createDate:" + createDate +
			"/createTime:" + createTime +
			"/createBy:" + createBy +
			"/lastModifyDate:" + lastModifyDate +
			"/lastModifyTime:" + lastModifyTime +
			"/subjectID:" + subjectID +
			"/name:" + name +
			"}";
	}
}