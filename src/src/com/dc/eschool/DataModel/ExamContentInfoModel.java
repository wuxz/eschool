package com.dc.eschool.DataModel;

/**
 * Title:        ������Ϣ������
 * Description:  ��������ID,�������ƣ�����ֽ��״̬
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class ExamContentInfoModel implements java.io.Serializable
{
  /***************************************************
   * ˽�г�Ա����
   ***************************************************/
  /**  ����ID   */
  private String contentID;
  /**  ��������  */
  private String contentName;
  /**  ����ֽ   */
  private String contentAnswerPaper;
  /**  ״̬     */
  private String contentState;
  /****************************************************
   * ������
   ***************************************************/
  public ExamContentInfoModel()
  {
	  this.contentID=null;
	  this.contentName=null;
	  this.contentAnswerPaper=null;
	  this.contentState=null;
  }
  /***************************************************
   * ��ȡ����
   ***************************************************/
  /**  ȡ�ÿ���ID   */
  public String getContentID()
  {
	  return contentID;
  }
  /**  ���ÿ���ID   */
  public void setContentID(String contentID)
  {
	  this.contentID=contentID;
  }
  /**  ȡ�ÿ�������  */
  public String getContentName()
  {
	  return contentName;
  }
  /**  ���ÿ�������  */
  public void setContentName(String contentName)
  {
	  this.contentName=contentName;
  }
  /**  ȡ�ô���ֽ  */
  public String getContentAnswerPaper()
  {
	  return contentAnswerPaper;
  }
  /**  ���ô���ֽ  */
  public void setContentAnswerPaper(String contentAnswerPaper)
  {
	  this.contentAnswerPaper=contentAnswerPaper;
  }
  /**  ȡ�ÿ���״̬  */
  public String getContentState()
  {
	  return contentState;
  }
  /**  ���ÿ���״̬  */
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