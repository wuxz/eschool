package com.dc.eschool.controller.event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;
import com.dc.eschool.testresultsitem.model.TestResultsItemModel;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:  DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This event is sent from the web tier to the EJB Controller to notify
 * the EJB Controller that a change needs to be made in the TESTRESULTITEM
 * and TESTRESULTITEMMgr model data.
 */
public class TestResultItemEvent extends MainEventSupport {
	public static final int CREATE_TESTRESULTITEM = 0;
	public static final int DELETE_TESTRESULTITEM = 1;
	public static final int UPDATE_TESTRESULTITEM = 2;
	public static final int REFRESH_TESTRESULTITEM = 3;

	private int actionType;
	private Vector models = null;

	public TestResultItemEvent() {}

	public TestResultItemEvent(int actionType)
	{
		this.actionType = actionType;
	}

	public int getActionType()
	{
		return actionType;
	}

	public void setActionType(int actionType)
	{
		this.actionType = actionType;
	}

	public Vector getModels()
	{
		return models;
	}
	public void setModels(Vector models)
	{
		this.models = models;
	}

	public String getEventName()
	{
		return "java:comp/env/event/TestResultItemEvent";
	}
}
