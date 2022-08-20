package com.dc.eschool.examinationscore.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.dc.eschool.examinationscore.model.ExaminationScoreModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface provides methods to view and modify ExaminationScore
 * information for a particular ExaminationScore.
*/

public interface ExaminationScoreEB extends EJBObject
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

    public Integer getExaminationID() throws RemoteException;
    public Integer getProjectID() throws RemoteException;
    public Integer getStudent() throws RemoteException;
    public Integer getScore() throws RemoteException;
    public Integer getTestResultItemID() throws RemoteException;


    public void setProjectID(Integer ProjectID) throws RemoteException;
    public void setStudent(Integer Student) throws RemoteException;
    public void setScore(Integer Score) throws RemoteException;
    public void setTestResultItemID(Integer TestResultItemID) throws RemoteException;

    public void changeExaminationScore(ExaminationScoreModel other) throws RemoteException;
}