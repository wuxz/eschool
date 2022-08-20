package com.dc.eschool.DataModel;

/**
 * Title:        用户信息的DataModel
 * Description:  用来存放用户的信息。
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class UserInfoModel implements java.io.Serializable
{
  /********************************************
   * 私有变量
   ********************************************/
  /**  用户ID     */
  private String userID;
  /**  用户姓名    */
  private String userName;
  /**  用户性别    */
  private String userGender;
  /**  用户出生日期 */
  private String userBirthday;
  /**  用户电话    */
  private String userTelephone;
  /**  用户类型    */
  private String userType;

  /**  构造器      */
  public UserInfoModel()
  {
	  this.userID=null;
	  this.userName=null;
	  this.userGender=null;
	  this.userBirthday=null;
	  this.userTelephone=null;
	  this.userType=null;
  }
  /********************************************
   * 各种存取方法
   ********************************************/
  /**  提取用户ID  */
  public String getUserID()
  {
	  return userID;
  }
  /**  设置用户ID  */
  public void setUserID(String userID)
  {
	  this.userID=userID;
  }
  /**   提取用户姓名  */
  public String getUserName()
  {
	  return userName;
  }
  /**   设置用户姓名  */
  public void setUserName(String userName)
  {
	  this.userName=userName;
  }
  /**   提取用户性别  */
  public String getUserGender()
  {
	  return userGender;
  }
  /**   设置用户性别  */
  public void setUserGender(String userGender)
  {
	  this.userGender=userGender;
  }
  /**   提取用户出生日期  */
  public String getUserBirthday()
  {
	  return userBirthday;
  }
  /**   设置用户出生日期  */
  public void setUserBirthday(String userBirthday)
  {
	  this.userBirthday=userBirthday;
  }
  /**   提取用户电话 */
  public String getUserTelephone()
  {
	  return userTelephone;
  }
  /**   设置用户电话  */
  public void setUserTelephone(String userTelephone)
  {
	  this.userTelephone=userTelephone;
  }
  /**   提取用户身份 */
  public String getUserType()
  {
	  return userType;
  }
  /**   设置用户身份  */
  public void setUserType(String userType)
  {
	  this.userType=userType;
  }

  public String toString()
  {
	return
		"{UserInfoModel:" +
		"/userID:" + userID +
		"/userName:" + userName +
		"/userGender:" + userGender +
		"/userBirthday:" + userBirthday +
		"/userTelephone:" + userTelephone +
		"/userType:" + userType +
		"}";
  }
}