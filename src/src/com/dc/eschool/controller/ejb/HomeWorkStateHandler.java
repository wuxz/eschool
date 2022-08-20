package com.dc.eschool.controller.ejb;

import java.rmi.RemoteException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.FinderException;
import javax.ejb.EJBException;

import com.dc.eschool.controller.web.StateMachine;
import com.dc.eschool.controller.event.*;

import com.dc.eschool.homework.mgrbean.HomeWorkSL;
import com.dc.eschool.homework.mgrbean.HomeWorkSLHome;
import com.dc.eschool.homework.model.HomeWorkModel;
import com.dc.eschool.homeworkcontent.mgrbean.HWContentSL;
import com.dc.eschool.homeworkcontent.mgrbean.HWContentSLHome;
import com.dc.eschool.homeworkcontent.model.HWContentModel;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.exception.*;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class HomeWorkStateHandler extends StateHandlerSupport
{
  public void perform(MainEvent event) throws ControllerException
  {
    HomeWorkEvent ue = (HomeWorkEvent)event;
    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
    switch(ue.getActionType())
    {
      case HomeWorkEvent.CREATE_HOMEWORK:
      {
	Debug.println("State Handled HOMEWORK_CREATE_EVENT");

	try
	{
	  HomeWorkSL homework=getManager();
	  HomeWorkModel hm=ue.getHm();
	  hm.setCreateBy(userID);
	  hm.setLastModifyBy(userID);
          hm.setStudent(userID);
	  homework.insertHomeWork(hm);
	}catch(Exception cce)
	{
	  System.out.println(cce);
	}
      }break;
      case HomeWorkEvent.UPDATE_HOMEWORK:
      {
	Debug.println("State Handled HOMEWORK_UPDATE_EVENT");

	try
	{
	  HWContentSL homework=getHWManager();
	  HomeWorkModel hm=ue.getHm();
	  HWContentModel hcm=new HWContentModel();
	  hcm.setDocURL(hm.getDocURL());
	  hcm.setSize(hm.getSize());
	  hcm.setLastModifyBy(userID);
          hcm.setState("ÒÑÅúÔÄ");
	  homework.updateHomeWorkContent(hcm) ;
	}catch (Exception re)
	{
	  System.out.println("Irrecoverable error while creating subject: " + re);
	}
      }break;
      case HomeWorkEvent.DELETE_HOMEWORK:
      {
	Debug.println("State Handled HOMEWORK_DELETE_EVENT");

	try
	{
	  HomeWorkSL homework=getManager();
	  HomeWorkModel hm =ue.getHm();
	  homework.deleteHomeWork(hm) ;
	}catch (Exception re)
	{
	  System.out.println("Irrecoverable error while creating subject: " + re);
	}
      }break;
    }
  }

  private HomeWorkSL getManager()
  {
    HomeWorkSL homework=null;
    HomeWorkSLHome home =null;

    try
    {
      home = EJBUtil.getHomeWorkSLHome();
      homework = home.create();
    } catch (javax.naming.NamingException ce)
    {
      throw new EJBException("Irrecoverable error creating HomeWork: " + ce);
    } catch (java.rmi.RemoteException re)
    {
      throw new EJBException("Irrecoverable error while creating HomeWork: " + re);
    } catch (CreateException ce)
    {
      System.out.println("Unable to create a new account for " + ce + " at this time");
    }
    return homework;
  }

  private HWContentSL getHWManager()
  {
    HWContentSL homework=null;
    HWContentSLHome home =null;
    try
    {
      home = EJBUtil.getHWContentSLHome();
      homework = home.create();
    } catch (javax.naming.NamingException ce)
    {
      throw new EJBException("Irrecoverable error creating HomeWork: " + ce);
    } catch (java.rmi.RemoteException re)
    {
      throw new EJBException("Irrecoverable error while creating HomeWork: " + re);
    } catch (CreateException ce)
    {
      System.out.println("Unable to create a new account for " + ce + " at this time");
    }
    return homework;
  }
}