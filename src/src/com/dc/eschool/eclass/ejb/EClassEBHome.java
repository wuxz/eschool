package com.dc.eschool.eclass.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

import com.dc.eschool.eclass.model.EClassModel;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public interface EClassEBHome extends EJBHome
{
    public EClassEB create(EClassModel cm) throws CreateException, RemoteException;
    public EClassEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}