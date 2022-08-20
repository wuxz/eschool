package com.dc.eschool.DataModel;

/**
 * Title:        听力练习数据模型
 * Description:  该模型用来提取数据
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class ListenExerciseModel implements java.io.Serializable
{
  /****************************************************
   * 私有成员变量
   ***************************************************/
  /**   听力练习ID   */
  private String projectID;
  /**   听力练习名称  */
  private String projectName;
  /**   日期  */
  private String projectDate;
  /**   听力练习说明  */
  private String projectInfo;
  /**************************************************
   * 构造器
   **************************************************/
  public ListenExerciseModel()
  {
	  this.projectID=null;
	  this.projectName=null;
	  this.projectDate=null;
	  this.projectInfo=null;
  }
  /**************************************************
   * 存取方法
   **************************************************/
  /**   取得听力ID  */
  public String getProjectID()
  {
	  return projectID;
  }
  /**   设置听力ID  */
  public void setProjectID(String projectID)
  {
	  this.projectID=projectID;
  }
  /**  取得听力名称  */
  public String getProjectName()
  {
	  return projectName;
  }
  /**  设置听力名称  */
  public void setProjectName(String projectName)
  {
	  this.projectName=projectName;
  }
  /**   取得日期  */
  public String getProjectDate()
  {
	  return projectDate;
  }
  /**   设置日期  */
  public void setProjectDate(String projectDate)
  {
	  this.projectDate=projectDate;
  }
  /**  取得听力说明   */
  public String getProjectInfo()
  {
	  return projectInfo;
  }
  /**  设置听力说明  */
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