package com.dc.eschool.DataModel;

/**
 * Title:        听力片断数据模型
 * Description:  该模型用来提取听力片断
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public class ListenSnippetModel implements java.io.Serializable
{
  /****************************************************
   * 私有成员变量
   ***************************************************/
  /**  听力片断ID   */
  private String snippetID;
  /**  听力片断名称  */
  private String snippetName;
  /**  听力片断说明  */
  private String snippetInfo;
  /**************************************************
   * 构造器
   **************************************************/
  public ListenSnippetModel()
  {
	  this.snippetID=null;
	  this.snippetInfo=null;
	  this.snippetName=null;
  }
  /**************************************************
   * 存取方法
   **************************************************/
  /**  获得片断ID  */
  public String getSnippetID()
  {
	  return this.snippetID;
  }
  /**  设置片断ID  */
  public void setSinppetID(String snippetID)
  {
	  this.snippetID=snippetID;
  }
  /**  获得片断名称 */
  public String getSnippetName()
  {
	  return this.snippetName;
  }
  /**  设置片断名称 */
  public void setSinppetName(String snippetName)
  {
	  this.snippetName=snippetName;
  }
  /**  获得片断说明 */
  public String getSnippetInfo()
  {
	  return this.snippetInfo;
  }
  /**  设置片断说明 */
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