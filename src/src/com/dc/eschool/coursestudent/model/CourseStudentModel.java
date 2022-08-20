package com.dc.eschool.coursestudent.model;

import java.rmi.RemoteException;

/**
 * Title:        ESchool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * This class provides methods to view and modify CourseStudent
 * information for a particular CourseStudent.
 */
public class CourseStudentModel implements java.io.Serializable
{
	protected String allow;
	protected String createDate;
	protected String createTime;
	protected Integer createBy;
	protected String lastModifyDate;
	protected String lastModifyTime;
	protected Integer lastModifyBy;
	protected Integer courseStudentID;
	protected Integer student;
	protected Integer courseID;

	/**
	* CourseStudent constructor with no arguments, used by the web tier.
	*/
	public CourseStudentModel()
	{
	}

	public void copy(CourseStudentModel other)
	{
		this.courseStudentID = other.courseStudentID;
		this.student = other.student;
		this.courseID = other.courseID;
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
	public void setCourseStudentID(Integer courseStudentID)
	{
		this.courseStudentID = courseStudentID;
	}
	public Integer getCourseStudentID()
	{
		return courseStudentID;
	}
	public void setStudent(Integer student)
	{
		this.student = student;
	}
	public Integer getStudent()
	{
		return student;
	}
	public void setCourseID(Integer courseID)
	{
		this.courseID = courseID;
	}
	public Integer getCourseID()
	{
		return courseID;
	}

	public String toString()
	{
		return
			"{CourseStudentModel:" +
			"/allow:" + allow +
			"/createDate:" + createDate +
			"/createTime:" + createTime +
			"/createBy:" + createBy +
			"/lastModifyDate:" + lastModifyDate +
			"/lastModifyTime:" + lastModifyTime +
			"/lastModifyBy:" + lastModifyBy +
			"/courseStudentID:" + courseStudentID +
			"/student:" + student +
			"/courseID:" + courseID +
			"}";
	}
}