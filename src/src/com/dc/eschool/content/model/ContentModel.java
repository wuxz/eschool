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
public class ContentModel implements java.io.Serializable
{
	protected String allow;
	protected String createDate;
	protected String createTime;
	protected Integer createBy;
	protected String lastModifyDate;
	protected String lastModifyTime;
	protected Integer lastModifyBy;
	protected Integer contentID;
	protected String name;
	protected String docURL;
	protected String info;
	protected String state;
	protected String type;
	protected String hasAnswerItem;
	protected Integer fileSize;
	protected Integer projectID;
	protected Integer projectContentID;

	/**
	* Content constructor with no arguments, used by the web tier.
	*/
	public ContentModel()
	{
	}

	public void copy(ContentModel other)
	{
	this.contentID = other.contentID;
		this.fileSize=other.fileSize;
	this.name = other.name;
		this.docURL=other.docURL;
		this.info=other.info;
		this.state=other.state;
		this.type=other.type;
		this.hasAnswerItem=other.hasAnswerItem;
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
	public void setContentID(Integer contentID)
	{
	  this.contentID = contentID;
	}
	public Integer getContentID()
	{
	  return contentID;
	}
	public void setFileSize(Integer fileSize)
	{
	  this.fileSize = fileSize;
	}
	public Integer getFileSize()
	{
	  return fileSize;
	}
	public void setName(String name)
	{
	  this.name = name;
	}
	public String getName()
	{
	  return name;
	}
	public void setDocURL(String docURL)
	{
	  this.docURL = docURL;
	}
	public String getDocURL()
	{
	  return docURL;
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
	public void setHasAnswerItem(String hasAnswerItem)
	{
	  this.hasAnswerItem = hasAnswerItem;
	}
	public String getHasAnswerItem()
	{
	  return hasAnswerItem;
	}
	public void setProjectID(Integer projectID)
	{
	  this.projectID = projectID;
	}
	public Integer getProjectID()
	{
	  return projectID;
	}
	public void setProjectContentID(Integer projectContentID)
	{
	  this.projectContentID = projectContentID;
	}
	public Integer getProjectContentID()
	{
	  return projectContentID;
	}

	public String toString()
	{
		return
			"{ContentModel:" +
			"/allow:" + allow +
			"/createDate:" + createDate +
			"/createTime:" + createTime +
			"/createBy:" + createBy +
			"/lastModifyDate:" + lastModifyDate +
			"/lastModifyTime:" + lastModifyTime +
			"/lastModifyBy:" + lastModifyBy +
			"/contentID:" + contentID +
			"/name:" + name +
			"/docURL:" + docURL +
			"/info:" + info +
			"/state:" + state +
			"/type:" + type +
			"/hasAnswerItem:" + hasAnswerItem +
			"/fileSize:" + fileSize +
			"/projectID:" + projectID +
			"/projectContentID:" + projectContentID +
			"}";
	}
}