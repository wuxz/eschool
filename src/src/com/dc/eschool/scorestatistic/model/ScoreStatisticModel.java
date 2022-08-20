package com.dc.eschool.scorestatistic.model;

import java.rmi.RemoteException;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author shuiwang
 * @version 1.0
 */

/**
 * This class provides methods to view and modify scorestatistic
 * information for a particular scorestatistic.
 */
public class ScoreStatisticModel implements java.io.Serializable
{
  protected Integer scoreStatisticID;
  protected Integer student;
  protected String  answerItemID;
  protected Integer projectContentID;
  protected String  statistic;
  protected Integer rightAnswer;
  protected Integer wrongAnswer;
  protected String  allow;
  protected String  createDate;
  protected String  createTime;
  protected Integer createBy;
  protected String  lastModifyDate;
  protected String  lastModifyTime;
  protected Integer lastModifyBy;

  public ScoreStatisticModel(Integer scoreStatisticID,Integer student,String answerNumber,Integer projectContentID,String  statistic, Integer rightAnswer,Integer wrongAnswer, String allow, String createDate, String createTime, Integer createBy, String lastModifyDate, String lastModifyTime, Integer lastModifyBy)
  {
	this.scoreStatisticID = scoreStatisticID;
	this.student = student;
	this.answerItemID = answerNumber;
	this.projectContentID = projectContentID;
	this.statistic = statistic;
	this.rightAnswer = rightAnswer;
	this.wrongAnswer = wrongAnswer;
	this.allow = allow;
	this.createDate = createDate;
	this.createTime = createTime;
	this.createBy = createBy;
	this.lastModifyDate = lastModifyDate;
	this.lastModifyTime = lastModifyTime;
	this.lastModifyBy = lastModifyBy;
  }
  public ScoreStatisticModel(Integer student,String answerNumber,Integer projectContentID,String  statistic, Integer rightAnswer,Integer wrongAnswer)
  {
	this.student = student;
	this.answerItemID = answerNumber;
	this.projectContentID = projectContentID;
	this.statistic = statistic;
	this.rightAnswer = rightAnswer;
	this.wrongAnswer = wrongAnswer;
  }

  public void copy(ScoreStatisticModel other)
  {
	this.scoreStatisticID = other.scoreStatisticID;
	this.student = other.student;
	this.answerItemID = other.answerItemID;
	this.projectContentID = other.projectContentID;
	this.statistic = other.statistic;
	this.rightAnswer = other.rightAnswer;
	this.wrongAnswer = other.wrongAnswer;
	this.allow = other.allow;
	this.createDate = other.createDate;
	this.createTime = other.createTime;
	this.createBy = other.createBy;
	this.lastModifyDate = other.lastModifyDate;
	this.lastModifyTime = other.lastModifyTime;
	this.lastModifyBy = other.lastModifyBy;
  }
  /**
  * Class constructor with no arguments, used by the web tier.
  */
  public ScoreStatisticModel() {}

  /* get and set methods for the instance variables */
  public Integer getScoreStatisticID()
  {
	return scoreStatisticID;
  }
  public Integer getStudent()
  {
	return student;
  }
  public String getAnswerItemID()
  {
	return answerItemID;
  }
  public Integer getProjectContentID()
  {
	return projectContentID;
  }
  public String  getStatistic()
  {
	return statistic;
  }
  public Integer getRightAnswer()
  {
	return rightAnswer;
  }
  public Integer getWrongAnswer()
  {
	return wrongAnswer;
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

  public void setScoreStatisticID(Integer scoreStatisticID)
  {
	this.scoreStatisticID = scoreStatisticID;
  }
  public void setStudent(Integer student)
  {
	this.student = student;
  }
  public void setAnswerItemID(String answerItemID)
  {
	this.answerItemID = answerItemID;
  }
  public void setProjectContentID(Integer projectContentID)
  {
	this.projectContentID = projectContentID;
  }
  public void setStatistic(String statistic)
  {
	this.statistic = statistic;
  }
  public void setRightAnswer(Integer rightAnswer)
  {
	this.rightAnswer = rightAnswer;
  }
  public void setWrongAnswer(Integer wrongAnswer)
  {
	this.wrongAnswer = wrongAnswer;
  }
  public void setAllow(String allow)
  {
	this.allow = allow;
  }
  public void setCreateDate(String createDate)
  {
	this.createDate = createDate;
  }
  public void setCreateTime(String createTime)
  {
	this.createTime = createTime;
  }
  public void setCreateBy(Integer createBy)
  {
	this.createBy = createBy;
  }
  public void setLastModifyDate(String lastModifyDate)
  {
	this.lastModifyDate = lastModifyDate;
  }
  public void setLastModifyTime(String lastModifyTime)
  {
	this.lastModifyTime = lastModifyTime;
  }
  public void setLastModifyBy(Integer lastModifyBy)
  {
	this.lastModifyBy = lastModifyBy;
  }

  public String toString()
  {
	return
		"{ScoreStatisticModel:" +
		"/scoreStatisticID:" + scoreStatisticID +
		"/student:" + student +
		"/answerItemID:" + answerItemID +
		"/projectContentID:" + projectContentID +
		"/statistic:" + statistic +
		"/rightAnswer:" + rightAnswer +
		"/wrongAnswer:" + wrongAnswer +
		"/allow:" + allow +
		"/createDate:" + createDate +
		"/createTime:" + createTime +
		"/createBy:" + createBy +
		"/lastModifyDate:" + lastModifyDate +
		"/lastModifyTime:" + lastModifyTime +
		"/lastModifyBy:" + lastModifyBy +
		"}";
  }
}