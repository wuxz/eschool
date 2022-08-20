package com.dc.eschool.DataModel;

/**
 * Title:         �γ���Ϣ��DataModel
 * Description:   ��Ҫ������ȡ�γ���Ϣ
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class CourseInfoModel implements java.io.Serializable
{
  /*****************************************************
   * ˽�б���
   *****************************************************/
  /**  �γ�ID   */
  private String courseID;
  /**  �γ���   */
  private String courseName;
  /**  �γ���Ϣ  */
  private String courseInfo;
  /**  ��ʼ����  */
  private String startDate;
  /**  ��������  */
  private String endDate;
  /**  ״̬     */
  private String state;
  /*****************************************************
   *������
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
   * ���ִ�ȡ����
   ****************************************************/
  /**   ȡ�ÿγ�ID  */
  public String getCourseID()
  {
	  return courseID;
  }
  /**   ����γ�ID  */
  public void setCourseID(String courseID)
  {
	  this.courseID=courseID;
  }
  /**   ȡ�ÿγ�����  */
  public String getCourseName()
  {
	  return courseName;
  }
  /**   ����γ����� */
  public void setCourseName(String courseName)
  {
	  this.courseName=courseName;
  }
  /**   ȡ�ÿγ���Ϣ  */
  public String getCourseInfo()
  {
	  return courseInfo;
  }
  /**   ����γ���Ϣ */
  public void setCourseInfo(String courseInfo)
  {
	  this.courseInfo=courseInfo;
  }
  /**   ȡ�ÿγ�״̬  */
  public String getState()
  {
	  return state;
  }
  /**   ����γ�״̬ */
  public void setState(String state)
  {
	  this.state=state;
  }
  /**   ȡ����ʼ����  */
  public String getStartDate()
  {
	  return startDate;
  }
  /**   ������ʼ���� */
  public void setStartDate(String startDate)
  {
	  this.startDate=startDate;
  }
  /**   ȡ�ý�������  */
  public String getEndDate()
  {
	  return endDate;
  }
  /**   ����������� */
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