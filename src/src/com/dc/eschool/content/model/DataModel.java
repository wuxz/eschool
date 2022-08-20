package com.dc.eschool.content.model;

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
 * This class provides methods to view and modify Content
 * information for a particular Content.
 */
public class DataModel implements java.io.Serializable
{
	private Integer projectID;
	private Integer contentID;
	private String docURL;
	private String projectName;
	private String contentName;
	private String projectLastModifyDate;
	private String projectLastModifyTime;
	private String projectInfo;
	private String projectStartDate;
	private String projectEndDate;
	private Integer courseID;
	private Integer projectCreateBy;

	/**
	* Content constructor with no arguments, used by the web tier.
	*/
	public DataModel()
	{
	}

	public void setProjectID(Integer projectID)
	{
	  this.projectID = projectID;
	}
	public Integer getProjectID()
	{
	  return projectID;
	}
	public void setContentID(Integer contentID)
	{
	  this.contentID = contentID;
	}
	public Integer getContentID()
	{
	  return contentID;
	}
	public void setProjectName(String projectName)
	{
	  this.projectName = projectName;
	}
	public String getProjectName()
	{
	  return projectName;
	}
	public void setDocURL(String docURL)
	{
	  this.docURL = docURL;
	}
	public String getDocURL()
	{
	  return docURL;
	}
	public void setContentName(String contentName)
	{
	  this.contentName = contentName;
	}
	public String getContentName()
	{
	  return contentName;
	}
	public void setProjectCreateBy(Integer projectCreateBy)
	{
	  this.projectCreateBy = projectCreateBy;
	}
	public Integer getProjectCreateBy()
	{
	  return projectCreateBy;
	}
	public void setProjectLastModifyDate(String projectLastModifyDate)
	{
	  this.projectLastModifyDate = projectLastModifyDate;
	}
	public String getProjectLastModifyDate()
	{
	  return projectLastModifyDate;
	}
	public void setProjectLastModifyTime(String projectLastModifyTime)
	{
	  this.projectLastModifyTime = projectLastModifyTime;
	}
	public String getProjectLastModifyTime()
	{
	  return projectLastModifyTime;
	}
	public void setCourseID(Integer courseID)
	{
	  this.courseID = courseID;
	}
	public Integer getCourseID()
	{
	  return courseID;
	}
	public void setProjectInfo(String projectInfo)
	{
	  this.projectInfo = projectInfo;
	}
	public String getProjectInfo()
	{
	  return projectInfo;
	}
	public void setProjectStartDate(String projectStartDate)
	{
	  this.projectStartDate = projectStartDate;
	}
	public String getProjectStartDate()
	{
	  return projectStartDate;
	}
	public void setProjectEndDate(String projectEndDate)
	{
	  this.projectEndDate = projectEndDate;
	}
	public String getProjectEndDate()
	{
	  return projectEndDate;
	}

	public String toString()
	{
		return
			"{DataModel:" +
			"/projectID:" + projectID +
			"/contentID:" + contentID +
			"/projectName:" + projectName +
			"/contentName:" + contentName +
			"/projectLastModifyDate:" + projectLastModifyDate +
			"/projectLastModifyTime:" + projectLastModifyTime +
			"/projectInfo:" + projectInfo +
			"/projectStartDate:" + projectStartDate +
			"/projectEndDate:" + projectEndDate +
			"/courseID:" + courseID +
			"/projectCreateBy:" + projectCreateBy +
			"}";
	}

	// get and set methods for the instance variables
}