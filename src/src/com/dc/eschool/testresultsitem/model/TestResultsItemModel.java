package com.dc.eschool.testresultsitem.model;

import java.rmi.RemoteException;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:p
 * @author shuiwang
 * @version 1.0
 */

public class TestResultsItemModel implements java.io.Serializable {

  protected Integer testResultItemID;
  protected Integer student;
  protected String  right;
  protected String  answerMark;
  protected String  answer;
  protected Integer answerItemID;
  protected String  allow;
  protected String  createDate;
  protected String  createTime;
  protected Integer createBy;
  protected String  lastModifyDate;
  protected String  lastModifyTime;
  protected Integer lastModifyBy;
  protected Integer contentID;
  protected String  answerNumber;
  protected String  rightAnswer;
  protected Integer projectID;

  /**
   * This class provides methods to view and modify TestResultItem
   * information for a particular testresultitem.
   */
  public TestResultsItemModel(Integer testResultItemID, Integer student, String right, String answerMark, String answer, Integer answerItemID, String allow, String createDate, String createTime, Integer createBy, String lastModifyDate, String lastModifyTime, Integer lastModifyBy,Integer contentID,String answerNumber)
  {
	this.testResultItemID = testResultItemID;
	this.student = student;
	this.right = right;
	this.answerMark = answerMark;
	this.answer = answer;
	this.answerItemID = answerItemID;
	this.allow = allow;
	this.createDate = createDate;
	this.createTime = createTime;
	this.createBy = createBy;
	this.lastModifyDate = lastModifyDate;
	this.lastModifyTime = lastModifyTime;
	this.lastModifyBy = lastModifyBy;
	this.contentID = contentID;
	this.answerNumber = answerNumber;
  }

   public TestResultsItemModel(String right, Integer testResultItemID,Integer lastModifyBy)
  {
	  this.right = right;
	  this.testResultItemID = testResultItemID;
	  this.lastModifyBy = lastModifyBy;
  }
   public TestResultsItemModel(Integer testResultItemID,String right,String answerNumber,Integer lastModifyBy)
  {
	  this.testResultItemID = testResultItemID;
	  this.right = right;
	  this.answerNumber = answerNumber;
	  this.lastModifyBy = lastModifyBy;
  }

  public TestResultsItemModel(Integer student, String right, String answerMark,Integer answerItemID,Integer lastModifyBy)
  {
	this.student = student;
	this.right = right;
	this.answerMark = answerMark;
	this.answerItemID = answerItemID;
	this.lastModifyBy = lastModifyBy;
  }

  public TestResultsItemModel(Integer student, String right, String answerMark, String answer,Integer answerItemID)
  {
	this.student = student;
	this.right = right;
	this.answerMark = answerMark;
	this.answer = answer;
	this.answerItemID = answerItemID;
  }

  protected void copy(TestResultsItemModel other)
  {
	  this.testResultItemID = other.testResultItemID;
	  this.student = other.student;
	  this.right = other.right;
	  this.answerMark = other.answerMark;
	  this.answer = other.answer;
	  this.answerItemID = other.answerItemID;
	  this.allow = other.allow;
	  this.createDate = other.createDate;
	  this.createTime = other.createTime;
	  this.createBy = other.createBy;
	  this.lastModifyDate = other.lastModifyDate;
	  this.lastModifyTime = other.lastModifyTime;
	  this.lastModifyBy = other.lastModifyBy;
	  this.contentID = other.contentID;
	  this.answerNumber = other.answerNumber;
	  this.projectID = other.projectID;
  }
  /**
  * Class constructor with no arguments, used by the web tier.
  */
  public TestResultsItemModel()
  {
  }

  // get and set methods for the instance variables
  public Integer getTestResultItemID()
  {
	 return testResultItemID;
  }
  public Integer getStudent()
  {
	return student;
  }
  public String getRight()
  {
	return right;
  }
  public String getAnswerMark()
  {
	return answerMark;
  }
  public String getAnswer()
  {
	return answer;
  }
  public Integer getAnswerItemID()
  {
	return answerItemID;
  }
  public String getAllow()
  {
	return allow;
  }
  public String getCreateDate()
  {
	return createDate;
  }
  public String getCreateTime()
  {
	return createTime;
  }
  public Integer getCreateBy()
  {
	return createBy;
  }
  public String getLastModifyDate()
  {
	return lastModifyDate;
  }
  public String getLastModifyTime()
  {
	return lastModifyTime;
  }
  public Integer getLastModifyBy()
  {
	return lastModifyBy;
  }
  public Integer getContentID()
  {
	return contentID;
  }
  public Integer getProjectID()
  {
	return projectID;
  }
  public String getAnswerNumber()
  {
	return answerNumber;
  }
  public String getRightAnswer()
  {
	return rightAnswer;
  }

  public void setTestResultItemID(Integer testResultItemID)
  {
   this.testResultItemID = testResultItemID;
  }
  public void setStudent(Integer student)
  {
   this.student = student;
  }
  public void setRight(String right)
  {
	this.right = right;
  }
  public void setAnswerMark(String answerMark)
  {
	this.answerMark = answerMark;
  }
  public void setAnswer(String answer)
  {
	this.answer = answer;
  }
  public void setAnswerItemID(Integer answerItemID)
  {
	this.answerItemID = answerItemID;
  }
  public void setAllow(String allow)
  {
	this.allow = allow;
  }
  public void setCreateBy(Integer createBy)
  {
	this.createBy = createBy;
  }
  public void setCreateDate(String createDate)
  {
	this.createDate = createDate;
  }
  public void setCreateTime(String createTime)
  {
	this.createTime = createTime;
  }
  public void setLastModifyBy(Integer lastModifyBy)
  {
	this.lastModifyBy = lastModifyBy;
  }
  public void setLastModifyDate(String lastModifyDate)
  {
	this.lastModifyDate = lastModifyDate;
  }
  public void setLastModifyTime(String lastModifyTime)
  {
	this.lastModifyTime = lastModifyTime;
  }
  public void setContentID(Integer contentID)
  {
	this.contentID = contentID;
  }
  public void setProjectID(Integer projectID)
  {
	this.projectID = projectID;
  }
  public void setAnswerNumber(String answerNumber)
  {
	this.answerNumber = answerNumber;
  }
  public void setRightAnswer(String rightAnswer)
  {
	this.rightAnswer = rightAnswer;
  }

  public String toString()
  {
	return
		"{TestResultItemModel:" +
		"/testResultItemID:" + testResultItemID +
		"/student:" + student +
		"/right:" + right +
		"/answerMark:" + answerMark +
		"/answer:" + answer +
		"/answerItemID:" + answerItemID +
		"/allow:" + allow +
		"/createDate:" + createDate +
		"/createTime:" + createTime +
		"/createBy:" + createBy +
		"/lastModifyDate:" + lastModifyDate +
		"/lastModifyTime:" + lastModifyTime +
		"/lastModifyBy:" + lastModifyBy +
		"/contentID:" + contentID +
		"/answerNumber:" + answerNumber +
		"/rightAnswer:" + rightAnswer +
		"/projectID:" + projectID +
		"}";
  }
}