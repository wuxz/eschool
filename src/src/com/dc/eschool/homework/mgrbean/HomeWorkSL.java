package com.dc.eschool.homework.mgrbean;

import java.rmi.*;
import javax.ejb.*;
import com.dc.eschool.homework.exceptions.HWDAOAppException;
import com.dc.eschool.homework.exceptions.HWDAODBUpException;
import com.dc.eschool.homework.exceptions.HWDAODuKeyException;
import com.dc.eschool.homework.exceptions.HWDAOSysException;
import com.dc.eschool.homework.model.HomeWorkModel;
import com.dc.eschool.homework.exceptions.*;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */


/**
 * The remote interface of HomeWorkSLBean(Statelesee Session bean)
 */

public interface HomeWorkSL extends EJBObject
{
  public void deleteHomeWork(HomeWorkModel hm) throws HWDeleteException, RemoteException;
  public void insertHomeWork(com.dc.eschool.homework.model.HomeWorkModel sm) throws HWCreateException, RemoteException;
  public HomeWorkModel getHomeWork(String homeWorkId) throws RemoteException;
}