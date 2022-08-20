package com.dc.eschool.course.model;

import java.io.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author eric
 * @version 1.0
 */

public class CourseModel implements Serializable
{
	protected String allow;
	protected String createTime;
	protected Integer createBy;
	protected String lastModifyDate;
	protected String lastModifyTime;
	protected Integer lastModifyBy;
	protected String createDate;
	protected Integer teacher;
	protected String startDate;
	protected String endDate;
	protected String info;
	protected String state;
	protected Integer courseID;
	protected String courseName;


	/**
	* Class constructor with no arguments, used by the web tier.
	*/
	public CourseModel()
	{
	}

	// get and set methods for the instance variables
	public String getAllow()
	{
	  return allow;
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
	public void setCreateDate(String createDate)
	{
	  this.createDate = createDate;
	}
	public String getCreateDate()
	{
	  return createDate;
	}
	public void setTeacher(Integer teacher)
	{
	  this.teacher = teacher;
	}
	public Integer getTeacher()
	{
	  return teacher;
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
	public void setCourseID(Integer courseID)
	{
	  this.courseID = courseID;
	}
	public Integer getCourseID()
	{
	  return courseID;
	}
	public void setCourseName(String courseName)
	{
	  this.courseName = courseName;
	}
	public String getCourseName()
	{
	  return courseName;
	}

	public void copy(CourseModel other)
	{
	  this.courseID = other.courseID;
	  this.courseName = other.courseName;
	  this.teacher=other.teacher;
	  this.startDate=other.startDate;
	  this.endDate=other.endDate;
	  this.info=other.info;
	  this.state=other.state;
	  this.allow = other.allow;
	  this.createDate = other.createDate;
	  this.createTime = other.createTime;
	  this.createBy = other.createBy;
	  this.lastModifyDate = other.lastModifyDate;
	  this.lastModifyTime = other.lastModifyTime;
	  this.lastModifyBy = other.lastModifyBy;
	}

	public String toString()
	{
		return
			"{CourseModel:" +
			"/allow:" + allow +
			"/createTime:" + createTime +
			"/createBy:" + createBy +
			"/lastModifyDate:" + lastModifyDate +
			"/lastModifyTime:" + lastModifyTime +
			"/lastModifyBy:" + lastModifyBy +
			"/createDate:" + createDate +
			"/teacher:" + teacher +
			"/startDate:" + startDate +
			"/endDate:" + endDate +
			"/info:" + info +
			"/state:" + state +
			"/courseID:" + courseID +
			"/courseName:" + courseName +
			"}";
	}
}