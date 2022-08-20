package com.dc.eschool.DataModel;

/**
 * Title:         考试信息模板类
 * Description:   包括考试ID,考试名称，考试时间，考试说明
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class ExamProjectInfoModle implements java.io.Serializable
{
  /****************************************************
   * 私有成员变量
   ***************************************************/
  /**   考试ID   */
  private String projectID;
  /**   考试名称  */
  private String projectName;
  /**   考试日期  */
  private String projectDate;
  /**   考试说明  */
  private String projectInfo;
  /**************************************************
   * 构造器
   **************************************************/
  public ExamProjectInfoModle()
  {
	  this.projectID=null;
	  this.projectName=null;
	  this.projectDate=null;
	  this.projectInfo=null;
  }
  /**************************************************
   * 存取方法
   **************************************************/
  /**   取得考试ID  */
  public String getProjectID()
  {
	  return projectID;
  }
  /**   设置考试ID  */
  public void setProjectID(String projectID)
  {
	  this.projectID=projectID;
  }
  /**  取得考试名称  */
  public String getProjectName()
  {
	  return projectName;
  }
  /**  设置考试名称  */
  public void setProjectName(String projectName)
  {
	  this.projectName=projectName;
  }
  /**   取得考试日期  */
  public String getProjectDate()
  {
	  return projectDate;
  }
  /**   设置考试日期  */
  public void setProjectDate(String projectDate)
  {
	  this.projectDate=projectDate;
  }
  /**  取得考试说明   */
  public String getProjectInfo()
  {
	  return projectInfo;
  }
  /**  设置考试说明  */
  public void setProjectInfo(String projectInfo)
  {
	  this.projectInfo=projectInfo;
  }

  public String toString()
  {
	return
		"{ExamProjectInfoModel:" +
		"/projectID:" + projectID +
		"/projectName:" + projectName +
		"/projectDate:" + projectDate +
		"/projectInfo:" + projectInfo +
		"}";
  }
}