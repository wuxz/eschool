package com.dc.eschool.DataModel;

/**
 * Title:        用户数据对象
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */
public class GroupUserModel implements java.io.Serializable
{
	private String id;
	private String name;
	private String type;
	private String status;
	private String gender;
	private String ip;
	  private String handup;
	  private String examing;
	public GroupUserModel() {}
	public GroupUserModel(String newUserID,String newUserName,String newUserType,
		String newUserStatus,String newUserGender,String newUserIP,String newHandUp,String newExaming)
	{
		this.id=newUserID;
		this.name=newUserName;
		this.type=newUserType;
		this.status=newUserStatus;
		this.gender=newUserGender;
		this.ip=newUserIP;
			this.handup=newHandUp;
			this.examing=newExaming;
	}
   /**
	*  用户ID
	*/
	public void setUserID(String newUserID)
	{
	   this.id=newUserID;
	}
	public String getUserID()
	{
		return this.id;
	}
   /**
	*  用户名称
	*/
	public void setUserName(String newUserName)
	{
		this.name=newUserName;
	}
	public String getUserName()
	{
		return this.name;
	}
   /**
	*  用户类别
	*/
	public void setUserType(String newUserType)
	{
		this.type=newUserType;
	}
	public String getUserType()
	{
		return this.type;
	}
	/**
	 *  用户状态
	 */
	public void setUserStatus(String newUserStatus)
	{
		this.status=newUserStatus;
	}
	public String getUserStatus()
	{
		return this.status;
	}
	/**
	 *  用户性别
	 */
	public void setUserGender(String newUserGender)
	{
		this.gender=newUserGender;
	}
	public String getUserGender()
	{
		return this.gender;
	}
   /**
	*  用户IP
	*/
	public void setUserIP(String newUserIP)
	{
		this.ip=newUserIP;
	}
	public String getUserIP()
	{
		return this.ip;
	}

	public String toString()
	{
		return
			"{GroupUserModel:" +
			"/id:" + id +
			"/name:" + name +
			"/type:" + type +
			"/status:" + status +
			"/gender:" + gender +
			"/ip:" + ip +
			"}";
	}
	public String getHandUp()
	{
		return this.handup;
	}
	public void setHandUp( String newHandUp)
	{
		this.handup=newHandUp;
	}

	public String getExaming()
	{
		return this.examing;
	}

	public void setExaming( String newExaming)
	{
		this.examing=newExaming;
	}
}
