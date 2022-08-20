package com.dc.eschool.DataModel;

/**
 * Title:        ������ϰ����ģ��
 * Description:  ��ģ��������ȡ����
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class ListenExerciseModel implements java.io.Serializable
{
  /****************************************************
   * ˽�г�Ա����
   ***************************************************/
  /**   ������ϰID   */
  private String projectID;
  /**   ������ϰ����  */
  private String projectName;
  /**   ����  */
  private String projectDate;
  /**   ������ϰ˵��  */
  private String projectInfo;
  /**************************************************
   * ������
   **************************************************/
  public ListenExerciseModel()
  {
	  this.projectID=null;
	  this.projectName=null;
	  this.projectDate=null;
	  this.projectInfo=null;
  }
  /**************************************************
   * ��ȡ����
   **************************************************/
  /**   ȡ������ID  */
  public String getProjectID()
  {
	  return projectID;
  }
  /**   ��������ID  */
  public void setProjectID(String projectID)
  {
	  this.projectID=projectID;
  }
  /**  ȡ����������  */
  public String getProjectName()
  {
	  return projectName;
  }
  /**  ������������  */
  public void setProjectName(String projectName)
  {
	  this.projectName=projectName;
  }
  /**   ȡ������  */
  public String getProjectDate()
  {
	  return projectDate;
  }
  /**   ��������  */
  public void setProjectDate(String projectDate)
  {
	  this.projectDate=projectDate;
  }
  /**  ȡ������˵��   */
  public String getProjectInfo()
  {
	  return projectInfo;
  }
  /**  ��������˵��  */
  public void setProjectInfo(String projectInfo)
  {
	  this.projectInfo=projectInfo;
  }

  public String toString()
  {
	return
		"{ListenExerciseModel:" +
		"/projectID:" + projectID +
		"/projectName:" + projectName +
		"/projectDate:" + projectDate +
		"/projectInfo:" + projectInfo +
		"}";
  }
}