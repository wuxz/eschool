package com.dc.eschool.DataModel;

/**
 * Title:         ������Ϣģ����
 * Description:   ��������ID,�������ƣ�����ʱ�䣬����˵��
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class ExamProjectInfoModle implements java.io.Serializable
{
  /****************************************************
   * ˽�г�Ա����
   ***************************************************/
  /**   ����ID   */
  private String projectID;
  /**   ��������  */
  private String projectName;
  /**   ��������  */
  private String projectDate;
  /**   ����˵��  */
  private String projectInfo;
  /**************************************************
   * ������
   **************************************************/
  public ExamProjectInfoModle()
  {
	  this.projectID=null;
	  this.projectName=null;
	  this.projectDate=null;
	  this.projectInfo=null;
  }
  /**************************************************
   * ��ȡ����
   **************************************************/
  /**   ȡ�ÿ���ID  */
  public String getProjectID()
  {
	  return projectID;
  }
  /**   ���ÿ���ID  */
  public void setProjectID(String projectID)
  {
	  this.projectID=projectID;
  }
  /**  ȡ�ÿ�������  */
  public String getProjectName()
  {
	  return projectName;
  }
  /**  ���ÿ�������  */
  public void setProjectName(String projectName)
  {
	  this.projectName=projectName;
  }
  /**   ȡ�ÿ�������  */
  public String getProjectDate()
  {
	  return projectDate;
  }
  /**   ���ÿ�������  */
  public void setProjectDate(String projectDate)
  {
	  this.projectDate=projectDate;
  }
  /**  ȡ�ÿ���˵��   */
  public String getProjectInfo()
  {
	  return projectInfo;
  }
  /**  ���ÿ���˵��  */
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