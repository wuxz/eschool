package com.dc.eschool.project.model;

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
 * This class provides methods to view and modify Project
 * information for a particular Project.
 */
public class ProjectModel implements java.io.Serializable
{
	protected String allow;
	protected String createDate;
	protected String createTime;
	protected Integer createBy;
	protected String lastModifyDate;
	protected String lastModifyTime;
	protected Integer lastModifyBy;
	protected Integer projectID;
	protected String name;
	protected Integer courseID;
	protected String info;
	protected String state;
	protected String type;
	protected String publishResult;
	protected String startDate;
	protected String endDate;


	/**
	* Project constructor with no arguments, used by the web tier.
	*/
	public ProjectModel()
	{
	}

	public void copy(ProjectModel other)
	{
	this.projectID = other.projectID;
	this.name = other.name;
		this.courseID = other.courseID;
		this.info = other.info;
		this.state = other.state;
		this.type = other.type;
		this.publishResult = other.publishResult;
		this.startDate = other.startDate;
		this.endDate = other.endDate;
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
	public void setProjectID(Integer projectID)
	{
	  this.projectID = projectID;
	}
	public Integer getProjectID()
	{
	  return projectID;
	}
	public void setName(String name)
	{
	  this.name = name;
	}
	public String getName()
	{
	  return name;
	}
	public void setCourseID(Integer courseID)
	{
	  this.courseID = courseID;
	}
	public Integer getCourseID()
	{
	  return courseID;
	}
	public void setInfo(String info)
	{
	  this.info = info;
	}
	public String getInfo()
	{
	  return info;
	}
	public void setState(String state)
	{
	  this.state = state;
	}
	public String getState()
	{
	  return state;
	}
	public void setType(String type)
	{
	  this.type = type;
	}
	public String getType()
	{
	  return type;
	}
	public void setPublishResult(String publishResult)
	{
	  this.publishResult = publishResult;
	}
	public String getPublishResult()
	{
	  return publishResult;
	}
	public void setStartDate(String startDate)
	{
	  this.startDate = startDate;
	}
	public String getStartDate()
	{
	  return startDate;
	}
	public void setEndDate(String endDate)
	{
	  this.endDate = endDate;
	}
	public String getEndDate()
	{
	  return endDate;
	}

	public String toString()
	{
		return
			"{ProjectModel:" +
			"/allow:" + allow +
			"/createDate:" + createDate +
			"/createTime:" + createTime +
			"/createBy:" + createBy +
			"/lastModifyDate:" + lastModifyDate +
			"/lastModifyTime:" + lastModifyTime +
			"/lastModifyBy:" + lastModifyBy +
			"/projectID:" + projectID +
			"/name:" + name +
			"/courseID:" + courseID +
			"/info:" + info +
			"/state:" + state +
			"/type:" + type +
			"/publishResult:" + publishResult +
			"/startDate:" + startDate +
			"/endDate:" + endDate +
			"}";
	}
}