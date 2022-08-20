package com.dc.eschool.answeritem.mgrbean;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import javax.ejb.FinderException;
import javax.ejb.EJBException;

import java.util.Collection;
import java.util.Locale;

import com.dc.eschool.answeritem.dao.AnswerItemMgrDAO;
import com.dc.eschool.answeritem.model.AnswerItemModel;

import com.dc.eschool.answeritem.exceptions.AnswerItemDAOSysException;
import com.dc.eschool.answeritem.exceptions.AnswerItemCreateException;
import com.dc.eschool.answeritem.exceptions.AnswerItemSearchException;
import com.dc.eschool.answeritem.exceptions.AnswerItemDAOFinderException;

import com.dc.eschool.answeritem.ejb.* ;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.util.EJBUtil;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * Session Bean implementation of AnswerItem
 *
 */
public class AnswerItemSLEJB implements SessionBean
{
    protected AnswerItemMgrDAO dao;

    public void ejbCreate()
    {
        try
        {
            dao = new AnswerItemMgrDAO();
        }
        catch(Exception e)
        {
          String str = "Exception while AnswerItemMgrBean creating :" + e.getMessage();
          Debug.println(str);
          throw new EJBException(str);
        }
    }

    public void setSessionContext(SessionContext sc) throws RemoteException {}

    public void ejbRemove() throws RemoteException
    {
      dao = null;
    }

    public void ejbActivate() throws RemoteException
    {
    	dao = null;
    }

    public void ejbPassivate() throws RemoteException
    {
      dao = null;
    }

    public void destroy() throws RemoteException
    {
      dao = null;
    }

    public Integer createAnswerItem(AnswerItemModel am) throws AnswerItemCreateException
	{
	    try
	    {
	      AnswerItemEBHome home = EJBUtil.getAnswerItemEBHome();
	      AnswerItemEB remote = home.create(am);
	      return (Integer)remote.getPrimaryKey();
	    }
	    catch(Exception e)
	    {
	      Debug.print(e,"Exception while createAnswerItem in ANSWERITEM_MANAGER.");
	      throw new AnswerItemCreateException(e);
	    }
	  }

    public ListChunk searchAnswerItem(String clause, int startindex, int count) throws AnswerItemSearchException
    {
        try
        {
            return dao.searchAnswerItem(clause, startindex, count);
        }
        catch(AnswerItemDAOFinderException ade)
        {
          throw new AnswerItemSearchException(ade);
        }
    }
}
