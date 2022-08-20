package com.dc.eschool.homeworkcontent.mgrbean;

import java.rmi.*;
import java.util.Vector;
import javax.ejb.*;

import com.dc.eschool.homeworkcontent.dao.HWContentMgrDAO;
import com.dc.eschool.util.*;
import com.dc.eschool.homeworkcontent.ejb.*;
import com.dc.eschool.homeworkcontent.model.HWContentModel;
import com.dc.eschool.homework.mgrbean.*;

import com.dc.eschool.homeworkcontent.exceptions.*;
import com.dc.eschool.sessiondata.*;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * Stateless session Bean implementation for HWContentEJB EJB.
 */

public class HWContentSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private HWContentMgrDAO dao = null;

    /**
           * the ejbCreate methods that does nothing
           */
    public void ejbCreate()
    {
	try
	{
	  dao = new HWContentMgrDAO();
	}
	catch(Exception e)
	{
	  String str = "Exception while HWContentMgrBean creating :" + e.getMessage();
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
    * Insert an data to HomeWorkContent Table
    *
    * @param pm a HWContentModel that represents the properties.
    * @return an <code>Integer</code> that has the HomeWorkContent information
    *         corresponding to a HomeWorkContent account.
    * @exception <code>HCCreateException</code> for irrecoverable errors
    */
    public void insertHomeWorkContent(HWContentModel pm) throws HCCreateException
    {
	try
	{
            HWContentEBHome home = EJBUtil.getHWContentEBHome();
            HWContentEB remote = home.create(pm);
	}
	catch(Exception e)
	{
            Debug.print(e,"Exception while createProjct in PROJECT_MANAGER.");
            throw new HCCreateException(e);
	}
    }

    /**
     * Delecte record by primary key
     */
    public void deleteHomeWorkContent(Integer primKey) throws HCDeleteException
    {
	try
	{
            HWContentEBHome home = EJBUtil.getHWContentEBHome();
            HWContentEB remote = home.findByPrimaryKey(primKey);
            remote.remove();
	}
	catch(Exception e)
	{
            Debug.print(e,"Exception while delete Class from Class Table");
            throw new HCDeleteException(e);
	}

    }

    /**
     * update record
     */
    public void updateHomeWorkContent(HWContentModel sm) throws HCCreateException
    {
	try
	{
            getDAO();
	    Integer key=dao.isHaveDocURL(sm.getDocURL());
	    HWContentEBHome home = EJBUtil.getHWContentEBHome();
            HWContentEB remote = home.findByPrimaryKey(key);
            Integer homeworkID=remote.getDetails().getHomeWorkID();
            Integer projectID=EJBUtil.getHomeWorkSLHome().create().getHomeWork(homeworkID.toString()).getProjectID();
	    remote.setDocURL(sm.getDocURL());
            remote.setSize(sm.getSize());
            remote.setState("ÒÑÅúÔÄ");
	    remote.setLastModifyBy(sm.getLastModifyBy());
            if(homeworkID.intValue()>0)
            {
              SessionSLHome sessionSLHome=EJBUtil.getSessionSLHome();
              SessionSL sessionSL=sessionSLHome.create();
              sessionSL.addHomeWork(projectID,key);
            }
	}
	catch(Exception e)
	{
            Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
            throw new HCCreateException(e);
	}
    }

    /**
    * Gets the data from HomeWorkContent Table by aptly SQL
    * @param clause a string that represents the SQL
    * @return the <code>ListChunk</code> that have the HomeWorkContent information
    *         corresponding to HomeWorkContent accounts.     *
    * @exception <code>HCSearchException</code>
    */
    public ListChunk searchHomeWorkContent(String clause, int startindex, int count, String value) throws HCSearchException
    {
	try
	{
            getDAO();
            return dao.searchHomeWorkContent(clause, startindex, count, value);
	}
	catch(HCDAOFindException pde)
	{
	  throw new HCSearchException(pde);
	}
    }


    /**
     * find record by the primary key
     */
    public Vector getHomeWorkID(Integer primaryKey)
    {
        Vector returnValue=new Vector();
          try
          {
              getDAO();
              returnValue= dao.getHomeWorkID(primaryKey);
          }
          catch(Exception pde)
          {
              System.out.println(pde);
          }
          return returnValue;
    }

    /**
     * get the unapproved student number
     */
    public int getRestCount(Integer projectID)
    {
        int returnValue=0;
        try
        {
            returnValue=dao.getRestCount(projectID);
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return returnValue;
    }

    /**
     * get the information of unhand in students including sutendentID and Student Name
     */
    public Vector unHandInStudent(Integer projectID)
    {
        Vector returnValue=new Vector();
        try
        {
            returnValue=dao.unHandInStudent(projectID);
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return returnValue;
    }
    /**
     * Create an instance of HomeWorkMgrDAO
     */
    private void getDAO()
    {
        try
        {
            dao = new HWContentMgrDAO();
        }catch(Exception e)
          {
            String str = "Exception while HWContentMgrDAO creating :" + e.getMessage();
            Debug.println(str);
            throw new EJBException(str);
          }
    }

    public Integer isHaveDocURL(String docURL)
    {
      Integer returnValue=new Integer(0);
      try
      {
        getDAO();
	returnValue=dao.isHaveDocURL(docURL);
      }catch(Exception e)
      {
        System.out.println(e);
      }
      return returnValue;
    }

    public boolean isHaveOther(String homeworkId)
    {
      boolean returnValue=false;
      try
      {
        getDAO();
	returnValue=dao.isHaveOther(homeworkId);
      }catch(Exception e)
      {
        System.out.println(e);
      }
      return returnValue;
    }

    public HWContentModel getHomeWorkContent(String homeWorkContentId)
    {
	HWContentModel hwm = new HWContentModel();
	try
	{
	    HWContentEBHome home=EJBUtil.getHWContentEBHome();
	    HWContentEB remote=home.findByPrimaryKey(new Integer(homeWorkContentId));
	    hwm=remote.getDetails();
	} catch(Exception se)
	{
	    System.out.println("getUser():" + se.getMessage());
	}
	return hwm;
    }
}