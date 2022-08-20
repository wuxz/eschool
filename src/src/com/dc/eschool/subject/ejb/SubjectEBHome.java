package com.dc.eschool.subject.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;

import com.dc.eschool.subject.exceptions.SubjectAppException;
import com.dc.eschool.subject.model.SubjectModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

/**
 * The home interface of the SubjectEBBean.
 */
public interface SubjectEBHome extends EJBHome {
    public SubjectEB create(SubjectModel sm) throws CreateException, RemoteException ;
    public SubjectEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}