package com.dc.eschool.homeworkcontent.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;

import com.dc.eschool.homeworkcontent.model.HWContentModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */
public interface HWContentEBHome extends EJBHome
{
  public HWContentEB create(HWContentModel hwcm) throws CreateException, RemoteException ;
  public HWContentEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}