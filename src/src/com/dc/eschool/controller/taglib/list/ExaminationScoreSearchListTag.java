package com.dc.eschool.controller.taglib.list;

import java.lang.Exception;
import java.util.*;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.web.*;
import com.dc.eschool.examinationscore.model.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ExaminationScoreSearchListTag extends ListTag
{
	private String studentName = null;
	private String studentID = null;
	private String courseID = null;
	private String fromDate = null;
	private String toDate = null;
	private String emptyString = "ExaminationScore search : no match result.";
	private boolean hasNext = false;

	protected Collection findCollection() throws java.lang.Exception
	{
		studentName = pageContext.getRequest().getParameter("studentName");
		studentID = pageContext.getRequest().getParameter("studentID");
		courseID = pageContext.getRequest().getParameter("courseID");
		fromDate = pageContext.getRequest().getParameter("fromDate");
		toDate = pageContext.getRequest().getParameter("toDate");
		if (courseID == null || courseID.equals("") || courseID.equals("null"))
		   return null;

		studentName = InterpretSQL.transform(studentName);

		Debug.println("ListTag_findCollection ...#1");

		ExaminationScoreSearchWebImpl eswi = (ExaminationScoreSearchWebImpl)
									  pageContext.getServletContext().getAttribute(WebKeys.ExaminationScoreSearchKey);

		if(eswi == null)
			Debug.println("ListTag_findCollection ...#2");

		String clause =
			   ", " +
			   InterpretSQL.encodeSign("Course") +
			   InterpretSQL.encodeSign("Project") +
			   InterpretSQL.encodeEndSign("Users") +
			   " where " +
			   InterpretSQL.encodeEndSign("Course") +
			   "." +
			   InterpretSQL.encodeEndSign("CourseID") +
			   " = " +
			   InterpretSQL.encodeEndSign("Project") +
			   " . " +
			   InterpretSQL.encodeEndSign("CourseID") +
			   " and " +
			   InterpretSQL.encodeEndSign("Project") +
			   "." +
			   InterpretSQL.encodeEndSign("ProjectID") +
			   " = " +
			   InterpretSQL.encodeEndSign("ExaminationScore") +
			   " . " +
			   InterpretSQL.encodeEndSign("ProjectID") +
			   " and " +
			   InterpretSQL.encodeEndSign("Users") +
			   "." +
			   InterpretSQL.encodeEndSign("UserID") +
			   " = " +
			   InterpretSQL.encodeEndSign("ExaminationScore") +
			   " . " +
			   InterpretSQL.encodeEndSign("Student");

		if (studentID != null)
		{
			ESchoolWebImpl escwi = (ESchoolWebImpl)
									  pageContext.getSession().getAttribute(WebKeys.ESchoolWebKey);
		   clause +=
				  " and " +
				   InterpretSQL.encodeEndSign("Users") +
				   "." +
				   InterpretSQL.encodeEndSign("UserID") +
				   " = " +
				   escwi.getUserID();
		}
		else if (studentName != null && !studentName.equals("") && !studentName.equals("null"))
		   clause +=
				  " and " +
				   InterpretSQL.encodeEndSign("Users") +
				   "." +
				   InterpretSQL.encodeEndSign("Name") +
				   " = " +
				   InterpretSQL.encodeEndCol(studentName);

		if (courseID != null && !courseID.equals("") && !courseID.equals("null"))
		   clause +=
				  " and " +
				   InterpretSQL.encodeEndSign("Course") +
				   "." +
				   InterpretSQL.encodeEndSign("CourseID") +
				   " = " +
				   courseID;

		if (fromDate != null && !fromDate.equals("") && !fromDate.equals("null"))
		   clause +=
				  " and " +
				   InterpretSQL.encodeEndSign("ExaminationScore") +
				   "." +
				   InterpretSQL.encodeEndSign("LastModifyDate") +
				   " >= " +
				   InterpretSQL.encodeEndCol(fromDate);

		if (toDate != null && !toDate.equals("") && !toDate.equals("null"))
		   clause +=
				  " and " +
				   InterpretSQL.encodeEndSign("ExaminationScore") +
				   "." +
				   InterpretSQL.encodeEndSign("LastModifyDate") +
				   " <= " +
				   InterpretSQL.encodeEndCol(toDate);

		ListChunk lc = null;
		Collection examinationScore = null;

		try
		{
			lc = eswi.searchExaminationScore(clause, startIndex-1, numItems);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if(lc == null)
		{
			Debug.println("no lc gotten");
			return null;
		}
		else Debug.println("lc is not null: " + lc.getTotalCount());

		examinationScore = lc.getCollection();
		Iterator it = examinationScore.iterator();
		int i = startIndex-1;
		while (it.hasNext())
		{
			i ++;
			((ExaminationScoreModel)it.next()).setTestResultItemID(new Integer(i));
		}

		if (numItems==0) numItems=examinationScore.size();
		if (((startIndex -1 + examinationScore.size()) < lc.getTotalCount()) &&
			examinationScore.size() >= numItems) hasNext = true;
		else hasNext = false;

		lastPage = lc.getTotalCount()/numItems;
		if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;
		return(examinationScore);
	}

	public int doEngTag()
	{
	  try
	  {
		if( collection == null
			|| (collection != null && collection.size() == 0) )
		  pageContext.getOut().println(emptyString);
	  }
	  catch(java.io.IOException ie)
	  {
		  Debug.println(ie.getMessage());
	  }
	  return(EVAL_PAGE);

	}

		protected void initParamPrefix()
	{
		paramPrefix = "ExaminationScoreSearch_";
	}

	protected boolean needsNextForm()
	{
		return hasNext;
	}

	public boolean isHasNext()
	{
		return hasNext;
	}

	public void setHasNext(boolean hasNext)
	{
		this.hasNext = hasNext;
	}

	public void setNumItems(String numItemsStr) {
		super.setNumItems(numItemsStr);
	}

	public void setStartIndex(String startIndexStr) {
		super.setNumItems(startIndexStr);
	}

	public Collection getCollection()
	{
		return collection;
	}
}