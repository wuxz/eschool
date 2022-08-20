package com.dc.eschool.homework.model;

import java.rmi.RemoteException;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class HomeWorkModel implements java.io.Serializable
{
  protected Integer homeWorkID;
  protected Integer student;
  protected Integer projectID;
  protected String allow;
  protected String createDate;
  protected String createTime;
  protected Integer createBy;
  protected String lastModifyDate;
  protected String lastModifyTime;
  protected Integer lastModifyBy;
  protected String docURL;
  protected Integer size;
  protected Integer homeWorkContentID;

  /**
  * Class constructor with no arguments, used by the web tier.
  */
  public HomeWorkModel()
  {
  }

  // get and set methods for the instance variables
  public Integer getHomeWorkID()
  {
	return homeWorkID;
  }
  public Integer getStudent()
  {
	return student;
  }
  public Integer getProjectID()
  {
	return projectID;
  }
  public String getAllow()
  {
	return allow;
  }
  public String getCreateDate()
  {
	return createDate;
  }
  public String getCreateTime() {
	return createTime;
  }
  public Integer getCreateBy() {
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
  public void setHomeWorkID(Integer homeWorkID)
  {
	this.homeWorkID = homeWorkID;
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
  public void setStudent(Integer student)
  {
	this.student = student;
  }

  public void copy(HomeWorkModel other)
  {
	this.homeWorkID = other.homeWorkID;
	this.student = other.student;
	this.projectID = other.projectID;
	this.allow = other.allow;
	this.createDate = other.createDate;
	this.createTime = other.createTime;
	this.createBy = other.createBy;
	this.lastModifyDate = other.lastModifyDate;
	this.lastModifyTime = other.lastModifyTime;
	this.lastModifyBy = other.lastModifyBy;
  }
  public void setDocURL(String docURL)
  {
	this.docURL = docURL;
  }
  public String getDocURL()
  {
	return docURL;
  }
  public void setSize(Integer size)
  {
	this.size = size;
  }
  public Integer getSize()
  {
	return size;
  }
  public void setHomeWorkContentID(Integer homeWorkContentID)
  {
	this.homeWorkContentID = homeWorkContentID;
  }
  public Integer getHomeWorkContentID()
  {
	return homeWorkContentID;
  }

  public String toString()
  {
	return
		"{HomeWorkModel:" +
		"/homeWorkID:" + homeWorkID +
		"/student:" + student +
		"/projectID:" + projectID +
		"/allow:" + allow +
		"/createDate:" + createDate +
		"/createTime:" + createTime +
		"/createBy:" + createBy +
		"/lastModifyDate:" + lastModifyDate +
		"/lastModifyTime:" + lastModifyTime +
		"/lastModifyBy:" + lastModifyBy +
		"/docURL:" + docURL +
		"/size:" + size +
		"/homeWorkContentID:" + homeWorkContentID +
		"}";
  }
}