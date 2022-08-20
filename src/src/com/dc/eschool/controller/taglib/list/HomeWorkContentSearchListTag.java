package com.dc.eschool.controller.taglib.list;

import java.lang.Exception;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Locale;

import com.dc.eschool.controller.web.ESchoolWebImpl;
import com.dc.eschool.controller.web.HomeWorkContentSearchWebImpl;

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

public class HomeWorkContentSearchListTag extends ListTag
{
	private String clause = "";
	private String emptyString = "";
	private boolean hasNext = false;
	private String value="";

	protected Collection findCollection() throws java.lang.Exception
	{
		Debug.println("ListTag_findCollection ...#1");

		HomeWorkContentSearchWebImpl hwswi = (HomeWorkContentSearchWebImpl)
									  pageContext.getServletContext().getAttribute(WebKeys.HomeWorkContentSearchKey);
		ESchoolWebImpl eswi = (ESchoolWebImpl)
									  pageContext.getSession().getAttribute(WebKeys.ESchoolWebKey);

		if(hwswi == null)
			Debug.println("ListTag_findCollection ...#2");

		ListChunk lc = null;
		Collection HomeWorkContent = null;

		if(clause.equals("homework"))
		{
			value=eswi.getProjectID().toString();
		}else if(clause.equals("uploaded"))
		{
			clause = eswi.getUserID().toString();
			value=eswi.getProjectID().toString();
		}
		else
		{
			value=eswi.getUserID().toString();
		}

		if(clause.equals("session"))
		{
		  Integer projectId=new Integer(pageContext.getRequest().getParameter("projectId"));
		  lc=EJBUtil.getSessionSLHome().create().getHomeWork(projectId);
		}else
		{
		  lc = hwswi.searchHomeWorkContent(clause, startIndex-1, numItems,value);
		}

		if(lc == null)
		{
			Debug.println("no lc gotten");
			return null;
		}
		else Debug.println("lc is not null: " + lc.getTotalCount());

		HomeWorkContent = lc.getCollection();

		if (numItems==0) numItems=HomeWorkContent.size();
		if (((startIndex -1 + HomeWorkContent.size()) < lc.getTotalCount()) &&
			HomeWorkContent.size() >= numItems) hasNext = true;
		else hasNext = false;

		lastPage = lc.getTotalCount()/numItems;
		if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;
		return(HomeWorkContent);
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
		paramPrefix = "answerItemSearch_";
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