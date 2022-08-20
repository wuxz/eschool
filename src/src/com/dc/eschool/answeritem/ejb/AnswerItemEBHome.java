package com.dc.eschool.answeritem.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBHome;

import com.dc.eschool.answeritem.exceptions.AnswerItemAppException;
import com.dc.eschool.answeritem.model.AnswerItemModel;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * AnswerItemEBHome is an interface which provides methods to create or to find
 * answeritem preferences for a particular user.
 */
public interface AnswerItemEBHome extends EJBHome
{

    public AnswerItemEB create(AnswerItemModel sm) throws CreateException, RemoteException ;
    public AnswerItemEB findByPrimaryKey(Integer primKey) throws RemoteException, FinderException;
}