package com.dc.eschool.DataModel;

/**
 * Title:        考试信息内容类
 * Description:  包括试题ID,试题名称，答题纸，状态
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class ExamContentInfoModel implements java.io.Serializable
{
  /***************************************************
   * 私有成员变量
   ***************************************************/
  /**  试题ID   */
  private String contentID;
  /**  试题名称  */
  private String contentName;
  /**  答题纸   */
  private String contentAnswerPaper;
  /**  状态     */
  private String contentState;
  /****************************************************
   * 构造器
   ***************************************************/
  public ExamContentInfoModel()
  {
	  this.contentID=null;
	  this.contentName=null;
	  this.contentAnswerPaper=null;
	  this.contentState=null;
  }
  /***************************************************
   * 存取方法
   ***************************************************/
  /**  取得考试ID   */
  public String getContentID()
  {
	  return contentID;
  }
  /**  设置考试ID   */
  public void setContentID(String contentID)
  {
	  this.contentID=contentID;
  }
  /**  取得考试名称  */
  public String getContentName()
  {
	  return contentName;
  }
  /**  设置考试名称  */
  public void setContentName(String contentName)
  {
	  this.contentName=contentName;
  }
  /**  取得答题纸  */
  public String getContentAnswerPaper()
  {
	  return contentAnswerPaper;
  }
  /**  设置答题纸  */
  public void setContentAnswerPaper(String contentAnswerPaper)
  {
	  this.contentAnswerPaper=contentAnswerPaper;
  }
  /**  取得考试状态  */
  public String getContentState()
  {
	  return contentState;
  }
  /**  设置考试状态  */
  public void setContentState(String contentState)
  {
	  this.contentState=contentState;
  }

  public String toString()
  {
	return
		"{ExamContentInfoModel:" +
		"/contentID:" + contentID +
		"/contentName:" + contentName +
		"/contentAnswerPaper:" + contentAnswerPaper +
		"/contentState:" + contentState +
		"}";
  }
}