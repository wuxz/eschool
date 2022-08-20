package com.dc.eschool.studentexamcontent.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;

import com.dc.eschool.studentexamcontent.exceptions.StudentExamContentAppException;
import com.dc.eschool.studentexamcontent.model.StudentExamContentModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

/**
 * The home interface of the StudentExamContentEBBean.
 */
public interface StudentExamContentEBHome extends EJBHome
{
    public StudentExamContentEB create(StudentExamContentModel secm) throws CreateException, RemoteException ;
    public StudentExamContentEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}