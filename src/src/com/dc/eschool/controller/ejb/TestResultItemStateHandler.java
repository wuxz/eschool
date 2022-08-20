package com.dc.eschool.controller.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.dc.eschool.controller.event.*;
import com.dc.eschool.controller.exception.*;
import com.dc.eschool.controller.web.StateMachine;

import com.dc.eschool.testresultsitem.mgrbean.TestResultsItemSL;
import com.dc.eschool.testresultsitem.mgrbean.TestResultsItemSLHome;
import com.dc.eschool.testresultsitem.model.TestResultsItemModel;

import com.dc.eschool.util.*;
import java.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author wangshui
 * @version 1.0
 */

public class TestResultItemStateHandler extends StateHandlerSupport
{
	public void perform(MainEvent event) throws ControllerException
	{
		TestResultItemEvent se = (TestResultItemEvent)event;
		TestResultsItemModel tm = new TestResultsItemModel();
		switch(se.getActionType())
		{
/*			case TestResultItemEvent.CREATE_TESTRESULTITEM:
			{
				Debug.println("State Handled TESTRESULTITEM_CREATE_EVENT");

				try
				{
					TestResultsItemSL testresultsitem=getManager();
					//Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
					Integer userID = new Integer(1);

					testresultsitem.insertTestResultsItem(new TestResultsItemModel(se.getStudent(),se.getAnswerMark(),se.getAnswer(),userID));

				}catch(com.dc.eschool.testresultsitem.exceptions.TestResultsItemCreateException uce)
				{
					System.out.println(uce);
				} catch (java.rmi.RemoteException re)
				{
					throw new EJBException("Irrecoverable error while creating TestResultsItem: " + re);
				}
			}break;*/
			case TestResultItemEvent.UPDATE_TESTRESULTITEM:
			{
				Debug.println("State Handled TESTRESULTITEM_UPDATE_EVENT");
				if (se == null)
				   return;

				try
				{
					TestResultsItemSL testresultsitem=getManager();

					testresultsitem.updateTestResultsItem(se.getModels());
				}catch(com.dc.eschool.testresultsitem.exceptions.TestResultsItemCreateException uce)
				{
					System.out.println(uce);
				} catch (java.rmi.RemoteException re)
				{
					throw new EJBException("Irrecoverable error while creating testresultsitem: " + re);
				}
			}break;
/*			case TestResultItemEvent.DELETE_TESTRESULTITEM:
			{
				Debug.println("State Handled TESTRESULTITEM_UPDATE_EVENT");

				try
				{
					TestResultsItemSL testresultsitem=getManager();
					testresultsitem.deleteTestResultsItem(se.getTestResultItemID()) ;
				}catch (Exception re)
				{
					System.out.println("Irrecoverable error while creating testresultsitem: " + re);
				}
			}break;*/
		}
	}

	private TestResultsItemSL getManager()
	{
		TestResultsItemSL testresultsitem=null;
		TestResultsItemSLHome home =null;

		try
		{
			home = EJBUtil.getTestResultsItemSLHome();
			testresultsitem = home.create();
		} catch (javax.naming.NamingException ce)
		{
			throw new EJBException("Irrecoverable error creating testresultsitem: " + ce);
		} catch (java.rmi.RemoteException re)
		{
			throw new EJBException("Irrecoverable error while creating testresultsitem: " + re);
		} catch (CreateException ce)
		{
			System.out.println("Unable to create a new account for " + ce + " at this time");
		}
		return testresultsitem;
	}
}