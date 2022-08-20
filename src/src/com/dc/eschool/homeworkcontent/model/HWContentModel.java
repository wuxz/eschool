package com.dc.eschool.homeworkcontent.model;

import java.rmi.RemoteException;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class HWContentModel implements java.io.Serializable {

  protected Integer homeWorkContentID;
  protected String docURL;
  protected String state;
  protected String submitDate;
  protected String submitTime;
  protected String passDate;
  protected String passTime;
  protected Integer homeWorkID;
  protected String allow;
  protected String createDate;
  protected String createTime;
  protected Integer createBy;
  protected String lastModifyDate;
  protected String lastModifyTime;
  protected Integer lastModifyBy;
  protected Integer student;
  protected Integer size;

  /**
   * This class provides methods to view and modify homeWorkContent
   * information for a particular homeWorkContent.
   */

  /**
  * Class constructor with no arguments, used by the web tier.
  */
  public HWContentModel() {}

  // get and set methods for the instance variables
  public Integer getHomeWorkContentID() {
	return homeWorkContentID;
  }
  public String getDocURL() {
	return docURL;
  }
  public String getState() {
	return state;
  }
  public String getSubmitDate() {
	return submitDate;
  }
  public String getSubmitTime() {
	return submitTime;
  }
  public String getPassDate() {
	return passDate;
  }
  public String getPassTime() {
	return passTime;
  }
  public Integer getHomeWorkID() {
	return homeWorkID;
  }
  public String getAllow() {
	return allow;
  }
  public String getCreateDate() {
	return createDate;
  }
  public String getCreateTime() {
	return createTime;
  }
  public Integer getCreateBy() {
	return createBy;
  }
  public String getLastModifyDate() {
	return lastModifyDate;
  }
  public String getLastModifyTime() {
	return lastModifyTime;
  }
  public Integer getStudent() {
	return student;
  }
  public Integer getLastModifyBy() {
	return lastModifyBy;
  }
  public void setAllow(String allow) {
	this.allow = allow;
  }
  public void setCreateBy(Integer createBy) {
	this.createBy = createBy;
  }
  public void setCreateDate(String createDate) {
	this.createDate = createDate;
  }
  public void setCreateTime(String createTime) {
	this.createTime = createTime;
  }
  public void setDocURL(String docURL) {
	this.docURL = docURL;
  }
  public void setHomeWorkContentID(Integer homeWorkContentID) {
	this.homeWorkContentID = homeWorkContentID;
  }
  public void setHomeWorkID(Integer homeWorkID) {
	this.homeWorkID = homeWorkID;
  }
  public void setLastModifyBy(Integer lastModifyBy) {
	this.lastModifyBy = lastModifyBy;
  }
  public void setLastModifyDate(String lastModifyDate) {
	this.lastModifyDate = lastModifyDate;
  }
  public void setLastModifyTime(String lastModifyTime) {
	this.lastModifyTime = lastModifyTime;
  }
  public void setPassDate(String passDate) {
	this.passDate = passDate;
  }
  public void setPassTime(String passTime) {
	this.passTime = passTime;
  }
  public void setState(String state) {
	this.state = state;
  }
  public void setSubmitDate(String submitDate) {
	this.submitDate = submitDate;
  }
  public void setSubmitTime(String submitTime) {
	this.submitTime = submitTime;
  }
  public void setStudent(Integer student) {
	this.student = student;
  }

  public void copy(HWContentModel other)
  {
	this.homeWorkContentID = other.homeWorkContentID;
	this.docURL  = other.docURL;
	this.state  = other.state;
	this.submitDate  = other.submitDate;
	this.submitTime  = other.submitTime;
	this.passDate  = other.passDate;
	this.passTime  = other.passTime;
	this.homeWorkID  = other.homeWorkID;
	this.size=other.size;
	this.allow  = other.allow;
	this.createDate  = other.createDate;
	this.createTime = other.createTime;
	this.createBy = other.createBy;
	this.lastModifyDate = other.lastModifyDate;
	this.lastModifyTime = other.lastModifyTime;
	this.lastModifyBy = other.lastModifyBy;
  }
  public void setSize(Integer size)
  {
	this.size = size;
  }
  public Integer getSize()
  {
	return size;
  }

  public String toString()
  {
	return
		"{HWContentModel:" +
		"/homeWorkContentID:" + homeWorkContentID +
		"/docURL:" + docURL +
		"/state:" + state +
		"/submitDate:" + submitDate +
		"/submitTime:" + submitTime +
		"/passDate:" + passDate +
		"/passTime:" + passTime +
		"/homeWorkID:" + homeWorkID +
		"/allow:" + allow +
		"/createDate:" + createDate +
		"/createTime:" + createTime +
		"/createBy:" + createBy +
		"/lastModifyDate:" + lastModifyDate +
		"/lastModifyTime:" + lastModifyTime +
		"/lastModifyBy:" + lastModifyBy +
		"/student:" + student +
		"/size:" + size +
		"}";
  }
}