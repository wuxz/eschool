package com.dc.eschool.eclass.ejb;

import java.rmi.*;
import javax.ejb.*;
import com.dc.eschool.eclass.model.EClassModel;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public interface EClassEB extends EJBObject
{
    public void setAllow(String p0) throws RemoteException;
    public void setName(String p0) throws RemoteException;
    public void setInfo(String p0) throws RemoteException;
    public void setLastModifyBy(java.lang.Integer p0) throws RemoteException;
    public EClassModel getDetails() throws RemoteException;
}