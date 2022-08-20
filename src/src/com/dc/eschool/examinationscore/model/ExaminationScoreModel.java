package com.dc.eschool.examinationscore.model;

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
 * This class provides methods to view and modify examinationscore
 * information for a particular examinationscore.
 */
public class ExaminationScoreModel implements java.io.Serializable
{
  protected Integer examinationID;
  protected Integer projectID;
  protected Integer student;
  protected Integer score;
  protected Integer testResultItemID;
  protected String  allow;
  protected String  createDate;
  protected String  createTime;
  protected Integer createBy;
  protected String  lastModifyDate;
  protected String  lastModifyTime;
  protected Integer lastModifyBy;

  public ExaminationScoreModel(Integer examinationID,Integer projectID,Integer student,Integer score,
				   Integer testResultItemID,String allow, String createDate, String createTime, Integer createBy,
							   String lastModifyDate, String lastModifyTime, Integer lastModifyBy)
  {
	this.examinationID = examinationID;
	this.projectID = projectID;
	this.student = student;
	this.score = score;
	this.testResultItemID = testResultItemID;
	this.allow = allow;
	this.createDate = createDate;
	this.createTime = createTime;
	this.createBy = createBy;
	this.lastModifyDate = lastModifyDate;
	this.lastModifyTime = lastModifyTime;
	this.lastModifyBy = lastModifyBy;
  }

  public ExaminationScoreModel(Integer projectID,Integer student,Integer score,Integer testResultItemID,Integer user)
  {
	this.projectID = projectID;
	this.student = student;
	this.score = score;
	this.testResultItemID = testResultItemID;
	this.createBy = user;
	this.lastModifyBy = user;
   }

   public ExaminationScoreModel(Integer projectID,Integer student,Integer score,Integer testResultItemID)
  {
	this.projectID = projectID;
	this.student = student;
	this.score = score;
	this.testResultItemID = testResultItemID;
   }

  public void copy(ExaminationScoreModel other)
  {
	this.examinationID = other.examinationID;
	this.projectID = other.projectID;
	System.out.println("&1="+other.projectID);
	this.student = other.student;
	System.out.println("&1="+other.student);
	this.score = other.score;
	System.out.println("&1="+other.score);
	this.testResultItemID = other.testResultItemID;
	System.out.println("&1="+other.testResultItemID);
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
  public ExaminationScoreModel() {}

  /* get and set methods for the instance variables */
  public Integer getExaminationID()
  {
	return examinationID;
  }
  public Integer getProjectID()
  {
	return projectID;
  }
  public Integer getStudent()
  {
	return student;
  }
  public Integer getScore()
  {
	return score;
  }
  public Integer getTestResultItemID()
  {
	return testResultItemID;
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

  public void setExaminationID(Integer examinationID)
  {
	this.examinationID = examinationID;
  }
  public void setProjectID(Integer projectID)
  {
	this.projectID = projectID;
  }
  public void setStudent(Integer student)
  {
	this.student = student;
  }
  public void setScore(Integer score)
  {
	this.score = score;
  }
  public void setTestResultItemID(Integer testResultItemID)
  {
	this.testResultItemID = testResultItemID;
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
		"{ExaminationScoreModel:" +
		"/examinationID:" + examinationID +
		"/projectID:" + projectID +
		"/student:" + student +
		"/score:" + score +
		"/testResultItemID:" + testResultItemID +
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