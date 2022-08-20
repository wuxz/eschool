package com.dc.eschool.DataModel;

/**
 * Title:        ����Ƭ������ģ��
 * Description:  ��ģ��������ȡ����Ƭ��
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public class ListenSnippetModel implements java.io.Serializable
{
  /****************************************************
   * ˽�г�Ա����
   ***************************************************/
  /**  ����Ƭ��ID   */
  private String snippetID;
  /**  ����Ƭ������  */
  private String snippetName;
  /**  ����Ƭ��˵��  */
  private String snippetInfo;
  /**************************************************
   * ������
   **************************************************/
  public ListenSnippetModel()
  {
	  this.snippetID=null;
	  this.snippetInfo=null;
	  this.snippetName=null;
  }
  /**************************************************
   * ��ȡ����
   **************************************************/
  /**  ���Ƭ��ID  */
  public String getSnippetID()
  {
	  return this.snippetID;
  }
  /**  ����Ƭ��ID  */
  public void setSinppetID(String snippetID)
  {
	  this.snippetID=snippetID;
  }
  /**  ���Ƭ������ */
  public String getSnippetName()
  {
	  return this.snippetName;
  }
  /**  ����Ƭ������ */
  public void setSinppetName(String snippetName)
  {
	  this.snippetName=snippetName;
  }
  /**  ���Ƭ��˵�� */
  public String getSnippetInfo()
  {
	  return this.snippetInfo;
  }
  /**  ����Ƭ��˵�� */
  public void setSnippetInfo(String snippetInfo)
  {
	  this.snippetInfo=snippetInfo;
  }

  public String toString()
  {
	return
		"{ListenSnippetModel:" +
		"/snippetID:" + snippetID +
		"/snippetName:" + snippetName +
		"/snippetInfo:" + snippetInfo +
		"}";
  }
}