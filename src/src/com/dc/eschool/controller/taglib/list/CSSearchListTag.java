package com.dc.eschool.controller.taglib.list;

import java.util.ArrayList;
import java.util.Collection;
import java.lang.Exception;
import java.util.Locale;

import com.dc.eschool.controller.web.CSSearchWebImpl;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.web.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class CSSearchListTag extends ListTag
{
	private String clause = "";
	private String emptyString = "No match result.";
	private boolean hasNext = false;

	protected Collection findCollection() throws java.lang.Exception
	{
		Debug.println("Fetching data from CourseStudent......");

		CSSearchWebImpl csswi = (CSSearchWebImpl)
									  pageContext.getServletContext().getAttribute(WebKeys.CourseStudentSearchKey);

		if(csswi == null)
		  Debug.println("The CSSearchWebImpl is null");

		ListChunk lc = null;
		Collection CourseStudent = null;

		lc = csswi.searchCourseStudent(clause, startIndex-1, numItems);

		if(lc == null)
		{
			Debug.println("Can not get any data from coursestudent table");
			return null;
		}

		CourseStudent = lc.getCollection();
		if (numItems==0) numItems=CourseStudent.size();
		if (((startIndex -1 + CourseStudent.size()) < lc.getTotalCount()) &&
		   CourseStudent.size() >= numItems) hasNext = true;
		else hasNext = false;

		lastPage = lc.getTotalCount()/numItems;
		if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;

		return(CourseStudent);
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
		paramPrefix = "CourseStudentSearch_";
	}

	protected boolean needsNextForm()
	{
		return hasNext;
	}

	public String getClause()
	{
		return clause;
	}

	public boolean isHasNext()
	{
		return hasNext;
	}

	public void setHasNext(boolean hasNext)
	{
		this.hasNext = hasNext;
	}

	public void setClause(String clause)
	{
		this.clause = clause;
	}

	public void setNumItems(String numItemsStr) {
	  super.setNumItems(numItemsStr);
	}

	public void setStartIndex(String startIndexStr) {
	  super.setNumItems(startIndexStr);
	}
}