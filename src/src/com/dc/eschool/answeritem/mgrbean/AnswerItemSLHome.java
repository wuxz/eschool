package com.dc.eschool.answeritem.mgrbean;

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

/** The Home interface for AnswerItem */

public interface AnswerItemSLHome extends EJBHome
{
    public AnswerItemSL create() throws RemoteException, CreateException;
}
