package com.dc.eschool.schoolresource.model;

import java.rmi.RemoteException;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This class provides methods to view and modify schoolresource
 * information for a particular schoolresource.
 */
public class SchoolResourceModel implements java.io.Serializable
{
  protected Integer schoolResourceID;
  protected String  name;
  protected String  docURL;
  protected Integer subjectID;
  protected String  subjectName;
  protected String  startDate;
  protected String  endDate;
  protected String  explain;
  protected String  allow;
  protected String  createDate;
  protected String  createTime;
  protected Integer createBy;
  protected String  lastModifyDate;
  protected String  lastModifyTime;
  protected Integer lastModifyBy;

  public SchoolResourceModel(Integer schoolResourceID, String name, String docURL,
							 Integer subjectID, String startDate, String endDate,
														 String explain,
							 String allow, String createDate, String createTime,
							 Integer createBy, String lastModifyDate, String lastModifyTime,
							 Integer lastModifyBy)
  {
	this.schoolResourceID = schoolResourceID;
	this.name = name;
	this.docURL = docURL;
	this.subjectID = subjectID;
	this.startDate = startDate;
	this.endDate = endDate;
	this.explain = explain;
	this.allow = allow;
	this.createDate = createDate;
	this.createTime = createTime;
	this.createBy = createBy;
	this.lastModifyDate = lastModifyDate;
	this.lastModifyTime = lastModifyTime;
	this.lastModifyBy = lastModifyBy;
  }

  public SchoolResourceModel(Integer schoolResourceID, String name, String docURL,
				 Integer subjectID, String startDate, String endDate,String explain)
  {
	this.schoolResourceID = schoolResourceID;
	this.name = name;
	this.docURL = docURL;
	this.subjectID = subjectID;
	this.startDate = startDate;
	this.endDate = endDate;
	this.explain = explain;
  }

  public SchoolResourceModel(String name, String docURL, Integer subjectID, String allow, String explain,Integer createBy)
  {
	this.name = name;
	System.out.println(name);
	this.docURL = docURL;
	System.out.println(docURL);
	this.subjectID = subjectID;
	System.out.println(subjectID);
	this.allow = allow;
	System.out.println(allow);
	this.explain = explain;
	System.out.println(explain);
	this.createBy = createBy;
	System.out.println(createBy);
  }

  public SchoolResourceModel(Integer schoolResourceID,String name, String docURL, Integer subjectID, String allow, String explain,Integer lastModifyBy)
  {
	this.schoolResourceID = schoolResourceID;
	this.name = name;
	this.docURL = docURL;
	this.subjectID = subjectID;
	this.allow = allow;
	this.explain = explain;
	this.lastModifyBy = lastModifyBy;
  }

  public SchoolResourceModel(Integer schoolResourceID,String name,String docURL,Integer subjectID,String startDate,String endDate)
  {
	this.schoolResourceID = schoolResourceID;
	this.name = name;
	this.docURL = docURL;
	this.subjectID = subjectID;
	this.startDate = startDate;
	this.endDate = endDate;
  }
  public void copy(SchoolResourceModel other)
  {
	this.schoolResourceID = other.schoolResourceID;
	System.out.println("schoolResourceID =" + schoolResourceID);
	this.name = other.name;
	System.out.println("name =" + name);
	this.docURL = other.docURL;
	System.out.println("docURL =" + docURL);
	this.subjectID = other.subjectID;
	System.out.println("subjectID =" + subjectID);
	this.startDate = other.startDate;
	System.out.println("startDate =" + startDate);
	this.endDate = other.endDate;
	System.out.println("endDate =" + endDate);
	this.explain = other.explain;
	System.out.println("explain =" + explain);
	this.allow = other.allow;
	System.out.println("allow =" + allow);
	this.createDate = other.createDate;
	System.out.println("createDate =" + createDate);
	this.createTime = other.createTime;
	System.out.println("createTime =" + createTime);
	this.createBy = other.createBy;
	System.out.println("createBy =" + createBy);
	this.lastModifyDate = other.lastModifyDate;
	System.out.println("lastModifyDate =" + lastModifyDate);
	this.lastModifyTime = other.lastModifyTime;
	System.out.println("lastModifyTime =" + lastModifyTime);
	this.lastModifyBy = other.lastModifyBy;
	System.out.println("lastModifyBy =" + lastModifyBy);
  }

  /**
  * Class constructor with no arguments, used by the web tier.
  */
  public SchoolResourceModel() {}

  /* get and set methods for the instance variables */
  public Integer getSchoolResourceID()
  {
	return schoolResourceID;
  }
  public String  getName()
  {
	return name;
  }
  public String  getDocURL()
  {
	return docURL;
  }
  public Integer getSubjectID()
  {
	return subjectID;
  }
  public String  getSubjectName()
  {
	return subjectName;
  }
  public String getStartDate()
  {
	return startDate;
  }
  public String getEndDate()
  {
	return endDate;
  }
  public String getExplain()
  {
	return explain;
  }
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
  public void setSchoolResourceID(Integer schoolResourceID)
  {
	this.schoolResourceID = schoolResourceID;
  }
  public void setName(String name)
  {
	this.name = name;
  }
  public void setDocURL(String docURL)
  {
	this.docURL = docURL;
  }
  public void setSubjectID(Integer subjectID)
  {
	this.subjectID = subjectID;
  }
  public void setSubjectName(String subjectName)
  {
	this.subjectName = subjectName;
  }
  public void setStartDate(String startDate)
  {
	this.startDate = startDate;
  }
  public void setEndDate(String endDate)
  {
	this.endDate = endDate;
  }
  public void setExplain(String explain)
  {
	this.explain = explain;
  }
  public void setAllow(String allow)
  {
	this.allow = allow;
  }
  public void setCreateDate(String createDate)
  {
	this.createDate = createDate;
  }
  public void setCreateTime(String createTime)
  {
	this.createTime = createTime;
  }
  public void setCreateBy(Integer createBy)
  {
	this.createBy = createBy;
  }
  public void setLastModifyDate(String lastModifyDate)
  {
	this.lastModifyDate = lastModifyDate;
  }
  public void setLastModifyTime(String lastModifyTime)
  {
	this.lastModifyTime = lastModifyTime;
  }
  public void setLastModifyBy(Integer lastModifyBy)
  {
	this.lastModifyBy = lastModifyBy;
  }

  public String toString()
  {
	return
		"{SchoolResourceModel:" +
		"/schoolResourceID:" + schoolResourceID +
		"/name:" + name +
		"/docURL:" + docURL +
		"/subjectID:" + subjectID +
		"/subjectName:" + subjectName +
		"/startDate:" + startDate +
		"/endDate:" + endDate +
		"/explain:" + explain +
		"/allow:" + allow +
		"/createDate:" + createDate +
		"/createTime:" + createTime +
		"/createBy:" + createBy +
		"/lastModifyDate:" + lastModifyDate +
		"/lastModifyTime:" + lastModifyTime +
		"/lastModifyBy:" + lastModifyBy +
		"}";
  }
}