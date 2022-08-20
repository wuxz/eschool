package com.dc.eschool.controller.event;

import java.io.Serializable;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:  DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This event is sent from the web tier to the EJB Controller to notify
 * the EJB Controller that a change needs to be made in the SchoolResource
 * and SchoolResourceMgr model data.
 */
public class SchoolResourceEvent extends MainEventSupport {
	public static final int CREATE_SCHOOLRESOURCE = 0;
	public static final int DELETE_SCHOOLRESOURCE = 1;
	public static final int UPDATE_SCHOOLRESOURCE = 2;
	public static final int REFRESH_SCHOOLRESOURCE = 3;

	private int actionType;
	private String name;
	private String docURL;
	private String allow;
	private Integer subjectID;
	private String explain;
	private Integer schoolResourceID;
	private String userID;

	public SchoolResourceEvent() {}

	public SchoolResourceEvent(int actionType)
	{
		this.actionType = actionType;
	}

	public void setInfo(String name,String docurl,String allow,Integer subjectid,String explain,String userID)
	{
		this.actionType = CREATE_SCHOOLRESOURCE;
		this.name = name;
		this.docURL = docurl;
		this.allow = allow;
		this.subjectID = subjectid;
		this.explain = explain;
		this.userID = userID;
	}

	public void setInfo(Integer schoolResourceID,String name,String docurl,String allow,Integer subjectid,String explain,String userID)
	{
		this.actionType = UPDATE_SCHOOLRESOURCE;
		this.schoolResourceID = schoolResourceID;
		this.name = name;
		this.docURL = docurl;
		this.allow = allow;
		this.subjectID = subjectid;
		this.explain = explain;
		this.userID = userID;
	}

		  public int getActionType()
		  {
			return actionType;
		  }
		  public void setActionType(int actionType)
		  {
			this.actionType = actionType;
		  }

		  public Integer getSchoolResourceID()
		  {
			return schoolResourceID;
		  }
		  public void setSchoolResourceID(Integer schoolResourceID)
		  {
			this.schoolResourceID = schoolResourceID;
		  }

	  public String  getName()
	  {
		return name;
	  }
	  public String  getUserID()
	  {
		return userID;
	  }
	  public String  getDocURL()
	  {
		return docURL;
	  }
	  public Integer getSubjectID()
	  {
		return subjectID;
	  }
	  public String getAllow()
	  {
		return allow;
	  }
	  public String getExplain()
	  {
		return explain;
	  }
	  public String getEventName()
	  {
				return "java:comp/env/event/SchoolResourceEvent";
		  }
}
