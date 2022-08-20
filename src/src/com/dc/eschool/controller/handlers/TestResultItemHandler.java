package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.TestResultItemEvent;
import com.dc.eschool.testresultsitem.model.TestResultsItemModel;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.ModelManager;
import com.dc.eschool.controller.web.ESchoolWebImpl;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.InterpretSQL;
/**
 * TestResultItem Handler
 *
*/
public class TestResultItemHandler extends RequestHandlerSupport
{
	ModelManager mm = null;

	public MainEvent processRequest(HttpServletRequest request) throws ControllerException
	{
		Debug.println("Started creation of an TestResultItem Event");

		String action = request.getParameter("action");
		Debug.println("TestResultItemHandler (web): action=" + action);
		if (action == null)
		{
			return null;
		}
/*		 else if (action.equals("createtestresultitem"))
		{
			return createNewTestResultItemEvent(request);
		}*/
		 else if (action.equals("updatetestresultitem"))
		{
			return createUpdateTestResultItemEvent(request);
		}
/*		 else if (action.equals("deletesubject"))
		{
			return createDeleteTestResultItemEvent(request);
		}*/
		return null;
	}

/*	private MainEvent createNewTestResultItemEvent(HttpServletRequest request)
	{
		TestResultItemEvent event = new TestResultItemEvent();

		//Integer student = new Integer(1);
		//String  right = request.getParameter("right").trim();
		//if(!(right).equals("right"))
		//right = request.getParameter("wrong").trim();
		//String  answermark = request.getParameter("answerMark").trim();
		//Integer examinationid = new Integer(2);
		String  answer = request.getParameter("answer").trim();
		//Integer answeritemid =  new Integer(request.getParameter("answerItemID").trim());


		javax.servlet.http.HttpSession session = (javax.servlet.http.HttpSession)request.getSession(true);
		ESchoolWebImpl esw = (ESchoolWebImpl)session.getAttribute(WebKeys.ESchoolWebKey);
		if (esw != null && esw.getUserID() != null)
		  event.getTm().setCreateBy(esw.getUserID());
		else
		{
			Debug.println("eschoolwebimpl not found");
		}

		event.setInfo(answer);
		return event;
	}*/

	private MainEvent createUpdateTestResultItemEvent(HttpServletRequest request)
	{
		TestResultItemEvent event = new TestResultItemEvent();
		System.out.println("in this testresultitemHandler");

		Integer testresultitemid;
		String  right = null;
		String  answernumber= null;
		int size = new Integer(request.getParameter("size")).intValue();

		javax.servlet.http.HttpSession session = (javax.servlet.http.HttpSession)request.getSession(true);
		ESchoolWebImpl esw = (ESchoolWebImpl)session.getAttribute(WebKeys.ESchoolWebKey);
		Integer userID = null;
		if (esw != null && esw.getUserID() != null)
		  userID = esw.getUserID();
		else
		{
			Debug.println("eschoolwebimpl not found");
		}

		if (size <= 0)
		   return null;

		Vector models = new Vector(size - 1);
		for(int i=1;i<size;i++)
		{
			TestResultsItemModel tm = new TestResultsItemModel();
			tm.setCreateBy(userID);
			tm.setLastModifyBy(userID);
			tm.setProjectID(new Integer(request.getParameter("projectId")));
			tm.setStudent(new Integer(request.getParameter("studentID")));
			tm.setContentID(new Integer(request.getParameter("contentID")));
			tm.setTestResultItemID(new Integer(request.getParameter("testResultItemID_" + i).trim()));

			right = request.getParameter("right_" + i).trim();
			if(!(right).equals("right"))
				right = "wrong";

			tm.setRight(right);

			answernumber =  request.getParameter("answerNumber_" + i).trim();
			tm.setAnswerNumber(answernumber);

			models.add(tm);
		}

		event.setActionType(2);
		event.setModels(models);

		return event;
	}

/*	private MainEvent createDeleteTestResultItemEvent(HttpServletRequest request)
	{
		TestResultItemEvent event = new TestResultItemEvent();

		Integer testResultItemID=new Integer(request.getParameter("TestResultItemID").trim());
		event.setActionType(1);
		event.setTestResultItemID(testResultItemID);
		return event;
	}*/
}

