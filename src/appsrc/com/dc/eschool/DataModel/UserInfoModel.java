package com.dc.eschool.DataModel;

/**
 * Title:        �û���Ϣ��DataModel
 * Description:  ��������û�����Ϣ��
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class UserInfoModel implements java.io.Serializable
{
  /********************************************
   * ˽�б���
   ********************************************/
  /**  �û�ID     */
  private String userID;
  /**  �û�����    */
  private String userName;
  /**  �û��Ա�    */
  private String userGender;
  /**  �û��������� */
  private String userBirthday;
  /**  �û��绰    */
  private String userTelephone;
  /**  �û�����    */
  private String userType;

  /**  ������      */
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
   * ���ִ�ȡ����
   ********************************************/
  /**  ��ȡ�û�ID  */
  public String getUserID()
  {
	  return userID;
  }
  /**  �����û�ID  */
  public void setUserID(String userID)
  {
	  this.userID=userID;
  }
  /**   ��ȡ�û�����  */
  public String getUserName()
  {
	  return userName;
  }
  /**   �����û�����  */
  public void setUserName(String userName)
  {
	  this.userName=userName;
  }
  /**   ��ȡ�û��Ա�  */
  public String getUserGender()
  {
	  return userGender;
  }
  /**   �����û��Ա�  */
  public void setUserGender(String userGender)
  {
	  this.userGender=userGender;
  }
  /**   ��ȡ�û���������  */
  public String getUserBirthday()
  {
	  return userBirthday;
  }
  /**   �����û���������  */
  public void setUserBirthday(String userBirthday)
  {
	  this.userBirthday=userBirthday;
  }
  /**   ��ȡ�û��绰 */
  public String getUserTelephone()
  {
	  return userTelephone;
  }
  /**   �����û��绰  */
  public void setUserTelephone(String userTelephone)
  {
	  this.userTelephone=userTelephone;
  }
  /**   ��ȡ�û���� */
  public String getUserType()
  {
	  return userType;
  }
  /**   �����û����  */
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