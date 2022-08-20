package com.dc.eschool.studentexamcontent.model;

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
 * This class provides methods to view and modify StudentExamContent
 * information for a particular StudentExamContent.
 */
public class StudentExamContentModel implements java.io.Serializable
{
	protected String allow;
	protected String createDate;
	protected String createTime;
	protected Integer createBy;
	protected String lastModifyDate;
	protected String lastModifyTime;
	protected Integer lastModifyBy;
	protected Integer userID;
	protected Integer studentExamContentID;
	protected Integer courseID;
	protected Integer contentID;

	/**
	* StudentExamContent constructor with no arguments, used by the web tier.
	*/
	public StudentExamContentModel()
	{
	}

	public void copy(StudentExamContentModel other)
	{
		this.studentExamContentID=other.studentExamContentID;
	this.userID=other.userID;
		this.courseID=other.courseID;
		this.contentID=other.contentID;
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
	public void setUserID(Integer userID) {
	  this.userID = userID;
	}
	public Integer getUserID() {
	  return userID;
	}
	public void setStudentExamContentID(Integer studentExamContentID) {
	  this.studentExamContentID = studentExamContentID;
	}
	public Integer getStudentExamContentID() {
	  return studentExamContentID;
	}
	public void setCourseID(Integer courseID) {
	  this.courseID = courseID;
	}
	public Integer getCourseID() {
	  return courseID;
	}
	public void setContentID(Integer contentID) {
	  this.contentID = contentID;
	}
	public Integer getContentID() {
	  return contentID;
	}

	public String toString()
	{
		return
			"{StudentExamContentModel:" +
			"/allow:" + allow +
			"/createDate:" + createDate +
			"/createTime:" + createTime +
			"/createBy:" + createBy +
			"/lastModifyDate:" + lastModifyDate +
			"/lastModifyTime:" + lastModifyTime +
			"/lastModifyBy:" + lastModifyBy +
			"/userID:" + userID +
			"/studentExamContentID:" + studentExamContentID +
			"/courseID:" + courseID +
			"/contentID" + contentID +
			"}";
	}
}