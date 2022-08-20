package com.dc.eschool.testresultsitem.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.dc.eschool.testresultsitem.model.TestResultsItemModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface provides methods to view and modify TestResultsItem
 * information for a particular TestResultsItem.
*/

public interface TestResultsItemEB extends EJBObject
{
    public String getAllow() throws RemoteException;
    public String getCreateDate() throws RemoteException;
    public String getCreateTime() throws RemoteException;
    public Integer getCreateBy() throws RemoteException;
    public String getLastModifyDate() throws RemoteException;
    public String getLastModifyTime() throws RemoteException;
    public Integer getLastModifyBy() throws RemoteException;
    public void setAllow(String Allow) throws RemoteException;
    public void setCreateBy(Integer CreateBy) throws RemoteException;
    public void setCreateDate(String CreateDate) throws RemoteException;
    public void setCreateTime(String CreateTime) throws RemoteException;
    public void setLastModifyBy(Integer LastModifyBy) throws RemoteException;
    public void setLastModifyDate(String LastModifyDate) throws RemoteException;
    public void setLastModifyTime(String LastModifyTime) throws RemoteException;


    public Integer getTestResultItemID() throws RemoteException;
    public void setStudent(Integer Student) throws RemoteException;
    public Integer getStudent() throws RemoteException;
    public void setRight(String Right) throws RemoteException;
    public String getRight() throws RemoteException;
    public void setAnswerMark(String AnswerMark) throws RemoteException;
    public String getAnswerMark() throws RemoteException;
    public void setAnswer(String Answer) throws RemoteException;
    public String getAnswer() throws RemoteException;
    public void setAnswerItemID(Integer AnswerItemID) throws RemoteException;
    public Integer getAnswerItemID() throws RemoteException;
    public void setContentID(Integer ContentID) throws RemoteException;
    public Integer getContentID() throws RemoteException;
    public void setAnswerNumber(String AnswerNumber) throws RemoteException;
    public String  getAnswerNumber() throws RemoteException;


    public void changeTestResultsItem(TestResultsItemModel other) throws RemoteException;
}