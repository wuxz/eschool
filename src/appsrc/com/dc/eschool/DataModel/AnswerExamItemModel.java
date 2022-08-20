package com.dc.eschool.DataModel;


/**
 * Title:          ��������
 * Description:    ����洢����ֽ��һ������ݡ�
 * Copyright:      Copyright (c) 2001
 * Company:        DC
 * @author         lau_hz
 * @version        1.0
 */

public class AnswerExamItemModel implements java.io.Serializable
{
  /*****************************************************
   * ˽�б���
   *****************************************************/
  /**   ���  */
  private Boolean mark;
  /**   ���  */
  private String  examCode;
  /**   ��  */
  private String  answerKey;
  /**   ����  */
  private String  answerType;
  /**   ��Ŀ����  */
  private int     itemCount;
  /*****************************************************
   * ������
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
   * ʵ�ַ���
   *******************************************************/
  /**   ȡ�ñ��    */
  public Boolean getMark()
  {
	  return mark;
  }
  public void setMark(Boolean mark)
  {
	  this.mark=mark;
  }
  /**   ȡ�ÿ������  */
  public String getExamCode()
  {
	  return examCode;
  }
  /**   ���ÿ������  */
  public void  setExamCode(String examCode)
  {
	  this.examCode=examCode;
  }
  /**   ȡ�ÿ��Դ�  */
  public String getAnswerKey()
  {
	  return answerKey;
  }
  /**   ���ÿ��Դ�  */
  public void setAnswerKey(String answerKey)
  {
	  this.answerKey=answerKey;
  }
  /**  ����������� */
  public String getAnswerType()
  {
	  return answerType;
  }
  /**  ���ÿ�������  */
  public void setAnswerType(String answerType)
  {
	  this.answerType=answerType;
  }
  /**   ��ô�����  */
  public int getItemCount()
  {
	 return itemCount;
  }
  /**   ���ô�����  */
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