package com.dc.eschool.eclass.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.eclass.exceptions.*;
import com.dc.eschool.eclass.model.EClassModel;
import com.dc.eschool.util.ListChunk;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public interface EClassSL extends EJBObject
{
    public void deleteEClass(Integer primKey) throws EClassDeleteException, RemoteException;
    public void insertEClass(EClassModel pm) throws EClassCreateException, RemoteException;
    public ListChunk searchEClass(String clause, int startIndex, int count) throws EClassSearchException, RemoteException;
    public void updateEClass(EClassModel cm) throws EClassCreateException, RemoteException;
    public EClassModel getEClass(String classId) throws RemoteException;
}