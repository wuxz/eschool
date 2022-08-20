package com.dc.eschool.answeritem.model;

import java.rmi.RemoteException;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author WangShui
 * @version 1.0
 */

/**
 * This class provides methods to view and modify AnswerItem
 * information for a particular AnswerItem.
 */
public class AnswerItemModel implements java.io.Serializable
{
	protected String allow;
	protected String createDate;
	protected String createTime;
	protected Integer createBy;
	protected String lastModifyDate;
	protected String lastModifyTime;
	protected Integer lastModifyBy;
	protected String type;
	protected Integer itemNumber;
	protected String answerNumber;
	protected String answer;
	protected String memo;
	protected Integer contentID;
	protected Integer answerItemID;

	/**
	* AnswerItem constructor with no arguments, used by the web tier.
	*/
	public AnswerItemModel()
	{
	}

	public void copy(AnswerItemModel other)
	{
	this.answerItemID = other.answerItemID;
	this.type = other.type;
		this.itemNumber = other.itemNumber;
		this.answerNumber = other.answerNumber;
		this.answer = other.answer;
		this.memo= other.memo;
		this.contentID = other.contentID;
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
	public void setAnswerItemID(Integer answerItemID)
	{
	  this.answerItemID = answerItemID;
	}
	public Integer getAnswerItemID()
	{
	  return answerItemID;
	}
	public void setType(String type)
	{
	  this.type = type;
	}
	public String getType()
	{
	  return type;
	}
	public void setItemNumber(Integer itemNumber)
	{
	  this.itemNumber = itemNumber;
	}
	public Integer getItemNumber()
	{
	  return itemNumber;
	}
	public void setAnswerNumber(String answerNumber)
	{
	  this.answerNumber = answerNumber;
	}
	public String getAnswerNumber()
	{
	  return answerNumber;
	}
	public void setAnswer(String answer)
	{
	  this.answer = answer;
	}
	public String getAnswer()
	{
	  return answer;
	}
	public void setMemo(String memo)
	{
	  this.memo = memo;
	}
	public String getMemo()
	{
	  return memo;
	}
	public void setContentID(Integer contentID)
	{
	  this.contentID = contentID;
	}
	public Integer getContentID()
	{
	  return contentID;
	}

	public String toString()
	{
		return
			"{AnshwerItemModel: allow:" + allow +
			"/createDate:" + createDate +
			"/createTime:" + createTime +
			"/createBy:" + createBy +
			"/lastModifyDate:" + lastModifyDate +
			"/lastModifyTime:" + lastModifyTime +
			"/lastModifyBy:" + lastModifyBy +
			"/type:" + type +
			"/itemNumber:" + itemNumber +
			"/answerNumber:" + answerNumber +
			"/answer:" + answer +
			"/memo:" + memo +
			"/contentID:" + contentID +
			"/answerItemID:" + answerItemID +
			"}";
	}
}