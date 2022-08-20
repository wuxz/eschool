package com.dc.eschool.scorestatistic.mgrbean;

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

/** The Home interface for ScoreStatistic */

public interface ScoreStatisticSLHome extends EJBHome
{
    public ScoreStatisticSL create() throws RemoteException, CreateException;
}
