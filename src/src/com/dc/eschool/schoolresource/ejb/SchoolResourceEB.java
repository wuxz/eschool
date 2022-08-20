package com.dc.eschool.schoolresource.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.dc.eschool.schoolresource.model.SchoolResourceModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface provides methods to view and modify schoolresource
 * information for a particular schoolresource.
*/

public interface SchoolResourceEB extends EJBObject
{
/*
    public String getAllow() throws RemoteException;
    public String getCreateDate() throws RemoteException;
    public String getCreateTime() throws RemoteException;
    public Integer getCreateBy() throws RemoteException;
    public String getLastModifyDate() throws RemoteException;
    public String getLastModifyTime() throws RemoteException;
    public Integer getLastModifyBy() throws RemoteException;
    public void setAllow(String allow) throws RemoteException;
    public void setCreateBy(Integer createBy) throws RemoteException;
    public void setCreateDate(String createDate) throws RemoteException;
    public void setCreateTime(String createTime) throws RemoteException;
    public void setLastModifyBy(Integer lastModifyBy) throws RemoteException;
    public void setLastModifyDate(String lastModifyDate) throws RemoteException;
    public void setLastModifyTime(String lastModifyTime) throws RemoteException;

    public Integer getSchoolResourceID() throws RemoteException;
    public String getName() throws RemoteException;
    public String getDocURL() throws RemoteException;
    public Integer getSubjectID() throws RemoteException;
    public String getStartDate() throws RemoteException;
    public String getEndDate() throws RemoteException;

   public void setSchoolResourceID(Integer schoolResourceID) throws RemoteException;
   public void setName(String name) throws RemoteException;
   public void setDocURL(String docURL) throws RemoteException;
   public void setSubjectID(Integer subjectID) throws RemoteException;
   public void setStartDate(String startDate) throws RemoteException;
   public void setEndDate(String endDate) throws RemoteException;
*/
   public void changeSchoolResource(com.dc.eschool.schoolresource.model.SchoolResourceModel sm) throws RemoteException;

}