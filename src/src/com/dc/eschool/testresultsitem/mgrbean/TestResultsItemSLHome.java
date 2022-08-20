package com.dc.eschool.testresultsitem.mgrbean;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/** The Home interface for TestResultsItem */

public interface TestResultsItemSLHome extends EJBHome
{
    public TestResultsItemSL create() throws RemoteException, CreateException;
}
