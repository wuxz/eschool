package com.dc.eschool.scorestatistic.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.dc.eschool.scorestatistic.model.ScoreStatisticModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface provides methods to view and modify ScoreStatistic
 * information for a particular ScoreStatistic.
*/

public interface ScoreStatisticEB extends EJBObject
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

    public Integer getScoreStatisticID() throws RemoteException;
    public Integer getStudent() throws RemoteException;
    public String  getAnswerItemID() throws RemoteException;
    public Integer getProjectContentID() throws RemoteException;
    public String  getStatistic() throws RemoteException;
    public Integer getRightAnswer() throws RemoteException;
    public Integer getWrongAnswer() throws RemoteException;


    public void setStudent(Integer Student) throws RemoteException;
    public void setAnswerItemID(String AnswerItemID) throws RemoteException;
    public void setProjectContentID(Integer ProjectContentID) throws RemoteException;
    public void setStatistic(String Statistic) throws RemoteException;
    public void setRightAnswer(Integer RightAnswer) throws RemoteException;
    public void setWrongAnswer(Integer WrongAnswer) throws RemoteException;

    public void changeScoreStatistic(ScoreStatisticModel other) throws RemoteException;
}