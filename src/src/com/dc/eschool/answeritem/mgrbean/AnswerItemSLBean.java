package com.dc.eschool.answeritem.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.answeritem.dao.AnswerItemMgrDAO;
import com.dc.eschool.answeritem.ejb.*;
import com.dc.eschool.answeritem.exceptions.*;
import com.dc.eschool.answeritem.model.AnswerItemModel;
import com.dc.eschool.content.ejb.*;

import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author wangshui
 * @version 1.0
 */

/**
 * Stateless session Bean implementation for AnswerItemEJB EJB.
 */
public class AnswerItemSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private AnswerItemMgrDAO dao = null;

    /**
     * the ejbCreate methods that does nothing
     */
    public void ejbCreate()
    {
	try
	{
            System.out.println("SLBean");
	    dao = new AnswerItemMgrDAO();
            System.out.println("SLBean");
	}
	catch(Exception e)
	{
	    String str = "Exception while AnswerItemMgrBean creating :" + e.getMessage();
	    Debug.println(str);
	    throw new EJBException(str);
	}
    }

    /**
     * the ejbRemove methods
     */
    public void ejbRemove() throws RemoteException
    {
        dao = null;
    }

    /**
     * the ejbActivate methods that does nothing
     */
    public void ejbActivate() throws RemoteException
    {
    }

    /**
     * the ejbPassivate methods
     */
    public void ejbPassivate() throws RemoteException
    {
        dao = null;
    }

    /**
     * Sets the session context
     * @param sc the <code>SessionContext</code> for this instance
     */
    public void setSessionContext(SessionContext sessionContext) throws RemoteException
    {
        this.sessionContext = sessionContext;
    }

    /**
     * Insert an data to Class Table
     * @param am a AnswerItemModel that represents the properties.
     * @return an <code>Integer</code> that has the Class information
     *         corresponding to a Class item.
     * @exception <code>AnswerItemCreateException</code> for irrecoverable errors
     */
    public void insertAnswerItem(AnswerItemModel am) throws AnswerItemCreateException
    {
	try
	{
	    AnswerItemEBHome home = EJBUtil.getAnswerItemEBHome();
	    AnswerItemEB remote = home.create(am);
            EJBUtil.getContentHome().findByPrimaryKey(am.getContentID()).setHasAnswerItem("Y");
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new AnswerItemCreateException(e);
	}
    }

    /**
     * remove record from Class by ClassID
     * @param primKey int primaty key of record
     * @exception <code>AnswerItemDeleteException</code>
     */
    public void deleteAnswerItem(Integer primKey) throws AnswerItemDeleteException
    {
	try
	{
	    AnswerItemEBHome home = EJBUtil.getAnswerItemEBHome();
	    AnswerItemEB remote = home.findByPrimaryKey(primKey);
            Integer contentID=remote.getDetails().getContentID();
	    remote.remove();
        if(!dao.isHasContent(primKey.toString()))
        {
          EJBUtil.getContentHome().findByPrimaryKey(contentID).setHasAnswerItem("N");
        }
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while delete Class from Class Table");
	    throw new AnswerItemDeleteException(e);
	}
    }

    /**
     * update answeritem record
     * @param am AnswerItemModel
     */
    public void updateAnswerItem(AnswerItemModel am) throws AnswerItemCreateException
    {
	try
	{
	    AnswerItemEBHome home = EJBUtil.getAnswerItemEBHome();
	    AnswerItemEB remote = home.findByPrimaryKey(am.getAnswerItemID());
	    remote.setType(am.getType());
	    remote.setItemNumber(am.getItemNumber());
	    remote.setAnswerNumber(am.getAnswerNumber());
	    remote.setAnswer(am.getAnswer());
	    remote.setMemo(am.getMemo());
	    remote.setContentID(am.getContentID());
	    remote.setLastModifyBy(am.getLastModifyBy());
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new AnswerItemCreateException(e);
	}
    }

    /**
     * Gets the data from Class Table by aptly SQL
     * @param clause a string that represents the SQL
     * @return the <code>ListChunk</code> that have the AnswerItem information
     *         corresponding to class items.
     * @exception <code>AnswerItemSearchException</code>
     */
    public ListChunk searchAnswerItem(String clause, int startIndex, int count) throws AnswerItemSearchException
    {
	try
	{
	    getDAO();
	    return dao.searchAnswerItem(clause, startIndex, count);
	}
	catch(AnswerItemDAOFinderException pde)
	{
	    throw new AnswerItemSearchException(pde);
	}
    }

    /**
     * search record by primKey AnswerItemID
     * @param answeritemId String the primarykey of AnswerItem table
     * return AnswerItemModel
     */
    public AnswerItemModel getAnswerItem(String subjectId)
    {
	AnswerItemModel um = new AnswerItemModel();
	try
	{
	    AnswerItemEBHome home=EJBUtil.getAnswerItemEBHome();
	    AnswerItemEB remote=home.findByPrimaryKey(new Integer(subjectId));
	    um=remote.getDetails();
	} catch(Exception se)
	{
	    System.out.println("getUser():" + se.getMessage());
	}
	return um;
    }

    /**
     * Create an instance of AnswerItemMgrDAO
     */
    private void getDAO()
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
}