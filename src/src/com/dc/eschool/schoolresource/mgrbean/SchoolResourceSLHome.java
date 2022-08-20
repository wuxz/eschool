package com.dc.eschool.schoolresource.mgrbean;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import com.dc.eschool.schoolresource.exceptions.*;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/** The Home interface for SchoolResource */

public interface SchoolResourceSLHome extends EJBHome
{
    public SchoolResourceSL create() throws RemoteException, CreateException;
}
