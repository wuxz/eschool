package com.dc.eschool.homework.mgrbean;

import java.rmi.*;
import java.util.Vector;
import javax.ejb.*;

import com.dc.eschool.util.*;
import com.dc.eschool.homework.ejb.*;
import com.dc.eschool.homework.model.HomeWorkModel;
import com.dc.eschool.homeworkcontent.mgrbean.*;
import com.dc.eschool.homeworkcontent.model.HWContentModel;
import com.dc.eschool.homeworkcontent.ejb.*;
import com.dc.eschool.homework.dao.HomeWorkMgrDAO;
import com.dc.eschool.sessiondata.*;

import com.dc.eschool.homework.exceptions.*;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * Stateless session Bean implementation for HomeWorkEJB EJB.
 */

public class HomeWorkSLBean implements SessionBean
{
  private SessionContext sessionContext;
  private HomeWorkMgrDAO dao = null;

  /**
   * the ejbCreate methods that does nothing
   */
  public void ejbCreate()
  {

  }

  /**
   * the ejbRemove methods
   */
  public void ejbRemove() throws RemoteException
  {

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
   * Insert an data to HomeWork Table
   *
   * @param hm a HomeWorkModel that represents the properties.
   * @return an <code>Integer</code> that has the HomeWork information
   *         corresponding to a HomeWork account.
   * @exception <code>HWcreateException</code> for irrecoverable errors
   */
  public void insertHomeWork(HomeWorkModel hm) throws HWCreateException
  {
    try
    {
      getDAO();
      Integer primaryKey=dao.searchUserProject(hm.getCreateBy(),hm.getProjectID());
      if(primaryKey.intValue()<=0)
      {
	HomeWorkEBHome home = EJBUtil.getHomeWorkEBHome();
	HomeWorkEB remote = home.create(hm);
	primaryKey=remote.getDetails().getHomeWorkID();
      }
      HWContentModel hcm=new HWContentModel();
      hcm.setDocURL(hm.getDocURL());
      hcm.setSize(hm.getSize());
      hcm.setState("Î´ÅúÔÄ");
      hcm.setHomeWorkID(primaryKey);
      hcm.setCreateBy(hm.getCreateBy());
      hcm.setLastModifyBy(hm.getLastModifyBy());
      HWContentEBHome hwContentHome= EJBUtil.getHWContentEBHome();
      HWContentEB hwContent=hwContentHome.create(hcm);
      Integer hwContentID=hwContent.getDetails().getHomeWorkContentID();
      if(hwContentID.intValue()>0)
      {
        SessionSLHome sessionSLHome=EJBUtil.getSessionSLHome();
        SessionSL sessionSL=sessionSLHome.create();
        sessionSL.addHomeWork(hm.getProjectID(),hwContentID);
      }
    }
    catch(Exception e)
    {
      Debug.print(e,"Exception while createProjct in HomeWork_MANAGER.");
      throw new HWCreateException(e);
    }
  }

  /**
     * remove record from Class by ClassID
     * @param primKey int primaty key of record
     * @exception <code>HomeWorkDeleteException</code>
     */
  public void deleteHomeWork(HomeWorkModel hm) throws HWDeleteException
  {
    try
    {
      HWContentEBHome hsHome=EJBUtil.getHWContentEBHome();
      HWContentEB hsEB=hsHome.findByPrimaryKey(hm.getHomeWorkContentID());
      Integer homeworkID=hsEB.getDetails().getHomeWorkID();
      hsEB.remove();

      boolean isHaveOther=EJBUtil.getHWContentSLHome().create().isHaveOther(homeworkID.toString());

      if (!isHaveOther)
      {
            HomeWorkEBHome home = EJBUtil.getHomeWorkEBHome();
            HomeWorkEB remote = home.findByPrimaryKey(homeworkID);
            remote.remove();
      }
      SessionSLHome sessionSLHome=EJBUtil.getSessionSLHome();
      SessionSL sessionSL=sessionSLHome.create();
      sessionSL.deleteHomeWork(hm.getProjectID(),hm.getHomeWorkContentID());
    }
    catch(Exception e)
    {
      Debug.print(e,"Exception while delete Class from Class Table");
      throw new HWDeleteException(e);
    }
  }

    /**
       * Create an instance of HomeWorkMgrDAO
       */
    private void getDAO()
    {
        try
        {
            dao = new HomeWorkMgrDAO();
        }catch(Exception e)
          {
            String str = "Exception while HomeWorkMgrDAO creating :" + e.getMessage();
            Debug.println(str);
            throw new EJBException(str);
          }
    }

    public HomeWorkModel getHomeWork(String homeWorkId)
    {
	HomeWorkModel hm = new HomeWorkModel();
	try
	{
	    HomeWorkEBHome home=EJBUtil.getHomeWorkEBHome();
	    HomeWorkEB remote=home.findByPrimaryKey(new Integer(homeWorkId));
	    hm=remote.getDetails();
	} catch(Exception se)
	{
	    System.out.println("getUser():" + se.getMessage());
	}
	return hm;
    }
}