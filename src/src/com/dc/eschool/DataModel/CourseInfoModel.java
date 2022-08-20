package com.dc.eschool.DataModel;

/**
 * Title:         课程信息的DataModel
 * Description:   主要用来存取课程信息
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class CourseInfoModel implements java.io.Serializable
{
  /*****************************************************
   * 私有变量
   *****************************************************/
  /**  课程ID   */
  private String courseID;
  /**  课程名   */
  private String courseName;
  /**  课程信息  */
  private String courseInfo;
  /**  起始日期  */
  private String startDate;
  /**  结束日期  */
  private String endDate;
  /**  状态     */
  private String state;
  /*****************************************************
   *构造器
   *****************************************************/
  public CourseInfoModel()
  {
	  this.courseID=null;
	  this.courseInfo=null;
	  this.courseName=null;
	  this.startDate=null;
	  this.endDate=null;
	  this.state=null;
  }
  /****************************************************
   * 各种存取方法
   ****************************************************/
  /**   取得课程ID  */
  public String getCourseID()
  {
	  return courseID;
  }
  /**   保存课程ID  */
  public void setCourseID(String courseID)
  {
	  this.courseID=courseID;
  }
  /**   取得课程名称  */
  public String getCourseName()
  {
	  return courseName;
  }
  /**   保存课程名称 */
  public void setCourseName(String courseName)
  {
	  this.courseName=courseName;
  }
  /**   取得课程信息  */
  public String getCourseInfo()
  {
	  return courseInfo;
  }
  /**   保存课程信息 */
  public void setCourseInfo(String courseInfo)
  {
	  this.courseInfo=courseInfo;
  }
  /**   取得课程状态  */
  public String getState()
  {
	  return state;
  }
  /**   保存课程状态 */
  public void setState(String state)
  {
	  this.state=state;
  }
  /**   取得起始日期  */
  public String getStartDate()
  {
	  return startDate;
  }
  /**   保存起始日期 */
  public void setStartDate(String startDate)
  {
	  this.startDate=startDate;
  }
  /**   取得结束日期  */
  public String getEndDate()
  {
	  return endDate;
  }
  /**   保存结束日期 */
  public void setEndDate(String endDate)
  {
	  this.endDate=endDate;
  }

  public String toString()
  {
	return
		"{CourseInfoModel" +
		"/courseID:" + courseID +
		"/courseName:" + courseName +
		"/courseInfo:" + courseInfo +
		"/startDate:" + startDate +
		"/endDate:" + endDate +
		"/state:" + state +
		"}";
  }
}