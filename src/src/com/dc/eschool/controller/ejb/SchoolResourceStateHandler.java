package com.dc.eschool.controller.ejb;

import java.rmi.RemoteException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.FinderException;
import javax.ejb.EJBException;

import com.dc.eschool.controller.web.*;
import com.dc.eschool.controller.event.*;

import com.dc.eschool.schoolresource.mgrbean.SchoolResourceSL;
import com.dc.eschool.schoolresource.mgrbean.SchoolResourceSLHome;
import com.dc.eschool.schoolresource.model.SchoolResourceModel;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.exception.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

public class SchoolResourceStateHandler extends StateHandlerSupport
{
  public void perform(MainEvent event) throws ControllerException
  {
	SchoolResourceEvent se = (SchoolResourceEvent)event;
	switch(se.getActionType())
	{
	  case SchoolResourceEvent.CREATE_SCHOOLRESOURCE:
	  {
		Debug.println("State Handled SCHOOLRESOURCE_CREATE_EVENT");

		try
		{
			System.out.println("zzz");
			SchoolResourceSL schoolresource = getManager();

		  schoolresource.insertSchoolResource(new SchoolResourceModel(se.getName(),
																	  se.getDocURL(),
																	  se.getSubjectID(),
																	  se.getAllow(),
																	  se.getExplain(),
																	  new Integer(se.getUserID())));
		}catch(com.dc.eschool.schoolresource.exceptions.SchoolResourceCreateException uce)
		{
		  System.out.println("zzz="+uce);
		}catch(java.rmi.RemoteException re)
		{
		  throw new EJBException("Irrecoverable error while creating schoolresource: " + re);
		}
	  }break;
	  case SchoolResourceEvent.UPDATE_SCHOOLRESOURCE:
	  {
		  Debug.println("State Handled SCHOOLRESOURCE_UPDATE_EVENT");
		  try
		  {
			  SchoolResourceSL schoolresource = getManager();
			  //Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
			  schoolresource.updateSchoolResource(new SchoolResourceModel(se.getSchoolResourceID(),
																		  se.getName(),
																		  se.getDocURL(),
																		  se.getSubjectID(),
																		  se.getAllow(),
																		  se.getExplain(),
																		  new Integer(se.getUserID())));
		  }catch(com.dc.eschool.schoolresource.exceptions.SchoolResourceCreateException uce)
		  {
			System.out.println(uce);
		  }catch(java.rmi.RemoteException re)
		  {
			throw new EJBException("Irrecoverable error while creating schoolresource: " + re);
		  }
	  }break;
	  case SchoolResourceEvent.DELETE_SCHOOLRESOURCE:
	  {
		  Debug.println("State Handled SCHOOLRESOURCE_UPDATE_EVENT");
		  try
		  {
			  SchoolResourceSL schoolresource = getManager();
			  schoolresource.deleteSchoolResource(se.getSchoolResourceID()) ;
		  }catch (Exception re)
		  {
			  System.out.println("Irrecoverable error while creating schoolresource: " + re);
		  }
	  }break;
	}
  }

  private SchoolResourceSL getManager()
  {
	SchoolResourceSL schoolresource = null;
	SchoolResourceSLHome schoolresourcehome =null;

	try
	{
	  System.out.println("in getManager()");
	  schoolresourcehome = EJBUtil.getSchoolResourceSLHome();
	  System.out.println("in getManager()+");
	  schoolresource = schoolresourcehome.create();
	} catch (javax.naming.NamingException ce)
	{
	  throw new EJBException("Irrecoverable error creating schoolresource: " + ce);
	} catch (java.rmi.RemoteException re)
	{
	  throw new EJBException("Irrecoverable error while creating schoolresource: " + re);
	} catch (CreateException ce)
	{
	  System.out.println("Unable to create a new schoolresource for " + ce + " at this time");
	}
	return schoolresource;
  }
}