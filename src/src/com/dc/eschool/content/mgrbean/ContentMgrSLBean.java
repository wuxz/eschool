package com.dc.eschool.content.mgrbean;

import java.rmi.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import javax.ejb.*;

import com.dc.eschool.content.dao.ContentMgrDAO;
import com.dc.eschool.content.ejb.*;
import com.dc.eschool.content.exceptions.*;
import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.projectcontent.ejb.*;
import com.dc.eschool.projectcontent.model.ProjectContentModel;
import com.dc.eschool.sessiondata.*;

import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * Stateless session Bean implementation for ContentEJB EJB.
 */
public class ContentMgrSLBean implements SessionBean
{
	private SessionContext sessionContext;
	private ContentMgrDAO dao = null;
	private Vector selectDate;

	public ContentMgrSLBean()
	{
		selectDate=new Vector();
	}
	/**
	 * the ejbCreate methods that does nothing
	 */
	public void ejbCreate()
	{
	try
	{
		dao = new ContentMgrDAO();
	}
	catch(Exception e)
	{
		String str = "Exception while ContentMgrBean creating :" + e.getMessage();
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
	 * @param cm a ContentModel that represents the properties.
	 * @return an <code>Integer</code> that has the Class information
	 *         corresponding to a Class item.
	 * @exception <code>ContentCreateException</code> for irrecoverable errors
	 */
	public void insertContent(ContentModel cm) throws ContentCreateException
	{

	try
	{
		ContentEBHome home = EJBUtil.getContentHome();
		ContentEB remote = home.create(cm);
			ProjectContentModel pm= new ProjectContentModel();
			pm.setContentID(remote.getDetails().getContentID());
			pm.setProjectID(cm.getProjectID());
			pm.setCreateBy(cm.getCreateBy());
			pm.setLastModifyBy(cm.getLastModifyBy());
			ProjectContentEBHome pcHome=EJBUtil.getProjectContentEBHome();
			ProjectContentEB pcRemote=pcHome.create(pm);
		cm.setProjectContentID(pcRemote.getDetails().getProjectContentID());
			cm.setContentID(pm.getContentID());
			if(cm.getProjectContentID()!=null)
			{
			  SessionSLHome sessionSLHome=EJBUtil.getSessionSLHome();
			  SessionSL sessionSL=sessionSLHome.create();
			  sessionSL.addProject(cm,"upload");
			}
	}
	catch(Exception e)
	{
		Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
		throw new ContentCreateException(e);
	}

	}

	/**
	 * remove record from Class by ClassID
	 * @param primKey int primaty key of record
	 * @exception <code>ContentDeleteException</code>
	 */
	public void deleteContent(ContentModel cm) throws ContentDeleteException
	{
	try
	{
		ProjectContentEBHome pcHome=EJBUtil.getProjectContentEBHome();
		ProjectContentEB pcRemote=pcHome.findByPrimaryKey(cm.getProjectContentID());
		pcRemote.remove();
		boolean isLast=dao.getProjectContentID(cm.getContentID());
		if(!isLast)
		{
		  ContentEBHome home = EJBUtil.getContentHome();
		  ContentEB remote = home.findByPrimaryKey(cm.getContentID());
		  remote.remove();
		}
		SessionSLHome sessionSLHome=EJBUtil.getSessionSLHome();
		SessionSL sessionSL=sessionSLHome.create();
		sessionSL.deleteProject(cm);
	}
	catch(Exception e)
	{
		Debug.print(e,"Exception while delete Class from Class Table");
		throw new ContentDeleteException(e);
	}
	}

	/**
	 * update content record
	 * @param cm ContentModel
	 */
	public void updateContent(ContentModel cm) throws ContentCreateException
	{
	try
	{
		ContentEBHome home = EJBUtil.getContentHome();
		ContentEB remote = home.findByPrimaryKey(cm.getContentID());
		//remote.changeContent(cm);
	}
	catch(Exception e)
	{
		Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
		throw new ContentCreateException(e);
	}
	}

	/**
	 * update content record
	 * @param cm ContentModel
	 */
	public void changeInfo(Integer contentID,String info,String state) throws ContentCreateException
	{
	try
	{
		ContentEBHome home = EJBUtil.getContentHome();
		ContentEB remote = home.findByPrimaryKey(contentID);
		remote.setInfo(info);
		remote.setState(state);
	}
	catch(Exception e)
	{
		Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
		throw new ContentCreateException(e);
	}
	}

	/**
	 * update content record
	 * @param cm ContentModel
	 */
	public void changeHasAnswerItem(Integer contentID,String isString) throws ContentCreateException
	{
	try
	{
		ContentEBHome home = EJBUtil.getContentHome();
		ContentEB remote = home.findByPrimaryKey(contentID);
		remote.setHasAnswerItem(isString);
	}
	catch(Exception e)
	{
		Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
		throw new ContentCreateException(e);
	}
	}

	/**
	 * Gets the data from Class Table by aptly SQL
	 * @param clause a string that represents the SQL
	 * @return the <code>ListChunk</code> that have the Content information
	 *         corresponding to class items.
	 * @exception <code>ContentSearchException</code>
	 */
	public ListChunk searchContents(String clause, int startIndex, int count,String value) throws ContentSearchException
	{
	try
	{
		getDAO();
		return dao.searchContent(clause, startIndex, count,value);
	}
	catch(Exception pde)
	{
		throw new ContentSearchException(pde);
	}
	}

	/**
	 * Gets the data from Class Table by aptly SQL
	 * @param clause a string that represents the SQL
	 * @return the <code>ListChunk</code> that have the Content information
	 *         corresponding to class items.
	 * @exception <code>ContentSearchException</code>
	 */
	public ListChunk searchPC(String clause, int startIndex, int count,String value,String info,String time) throws ContentSearchException
	{
	try
	{
		getDAO();
		return dao.searchPC(clause, startIndex, count,value,info,time);
	}
	catch(Exception pde)
	{
		throw new ContentSearchException(pde);
	}
	}

	public Collection getContentByApp(String projectID)
	{
	  Collection collection=null;
	  try
	  {
		collection=dao.getContentByApp(projectID);
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return collection;
	}

	public Collection getCheckedContentByApp(String courseID,String type)
	{
	  Collection collection=null;
	  try
	  {
		collection=dao.getCheckedContentByApp(courseID,type);
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return collection;
	}
	/**
	 * search record by primKey ContentID
	 * @param contentId String the primarykey of Content table
	 * return ContentModel
	 */
	public ContentModel getContent(String contentId)
	{
	ContentModel cm = new ContentModel();
	try
	{
		ContentEBHome home = EJBUtil.getContentHome();
		cm.setContentID(new Integer(contentId));
		ContentEB remote = home.findByPrimaryKey(new Integer(contentId));
		cm=remote.getDetails();
	} catch(Exception se)
	{
		se.printStackTrace();
//	    System.out.println("getContent():" + se.getMessage());
	}
	return cm;
	}

	/**
	 * Create an instance of ContentMgrDAO
	 */
	private void getDAO()
	{
	try
	{
		dao = new ContentMgrDAO();
	}
	catch(Exception e)
	{
		String str = "Exception while ContentMgrBean creating :" + e.getMessage();
		Debug.println(str);
		throw new EJBException(str);
	}
	}

	public void insertProjectContent(ContentModel cm) throws ContentCreateException
	{

	try
	{
			ProjectContentModel pm= new ProjectContentModel();
			pm.setContentID(cm.getContentID());
			pm.setProjectID(cm.getProjectID());
			pm.setCreateBy(cm.getCreateBy());
			pm.setLastModifyBy(cm.getLastModifyBy());
			ProjectContentEBHome pcHome=EJBUtil.getProjectContentEBHome();
			ProjectContentEB pcRemote=pcHome.create(pm);
		cm.setProjectContentID(pcRemote.getDetails().getProjectContentID());
			if(cm.getProjectContentID()!=null)
			{
			  SessionSLHome sessionSLHome=EJBUtil.getSessionSLHome();
			  SessionSL sessionSL=sessionSLHome.create();
			  sessionSL.addProject(cm,"search");
			}
	}
	catch(Exception e)
	{
		Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
		throw new ContentCreateException(e);
	}

	}
}