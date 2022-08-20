package com.dc.eschool.DataModel;


/**
 * Title:          答题项类
 * Description:    该类存储答题纸中一项的内容。
 * Copyright:      Copyright (c) 2001
 * Company:        DC
 * @author         lau_hz
 * @version        1.0
 */

public class AnswerExamItemModel implements java.io.Serializable
{
  /*****************************************************
   * 私有变量
   *****************************************************/
  /**   标记  */
  private Boolean mark;
  /**   题号  */
  private String  examCode;
  /**   答案  */
  private String  answerKey;
  /**   类型  */
  private String  answerType;
  /**   题目数量  */
  private int     itemCount;
  /*****************************************************
   * 构造器
   ******************************************************/
  public AnswerExamItemModel()
  {
	  this.mark=new Boolean(false);
	  this.examCode=null;
	  this.answerKey=null;
	  this.answerType=null;
	  this.itemCount=0;
  }
  /********************************************************
   * 实现方法
   *******************************************************/
  /**   取得标记    */
  public Boolean getMark()
  {
	  return mark;
  }
  public void setMark(Boolean mark)
  {
	  this.mark=mark;
  }
  /**   取得考试题号  */
  public String getExamCode()
  {
	  return examCode;
  }
  /**   设置考试题号  */
  public void  setExamCode(String examCode)
  {
	  this.examCode=examCode;
  }
  /**   取得考试答案  */
  public String getAnswerKey()
  {
	  return answerKey;
  }
  /**   设置考试答案  */
  public void setAnswerKey(String answerKey)
  {
	  this.answerKey=answerKey;
  }
  /**  获得试题类型 */
  public String getAnswerType()
  {
	  return answerType;
  }
  /**  设置考试类型  */
  public void setAnswerType(String answerType)
  {
	  this.answerType=answerType;
  }
  /**   获得答案数量  */
  public int getItemCount()
  {
	 return itemCount;
  }
  /**   设置答案数量  */
  public void setItemCount(int itemCount)
  {
	  this.itemCount=itemCount;
  }

	public String toString()
	{
		return
			"{AnswerExamItemModel:" +
			"/mark:" + mark +
			"/examCode:" + examCode +
			"/answerKey:" + answerKey +
			"/itemCount:" + itemCount +
			"}";
	}
}