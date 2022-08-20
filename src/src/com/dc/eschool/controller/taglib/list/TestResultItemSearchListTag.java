package com.dc.eschool.controller.taglib.list;

import java.util.Collection;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Locale;

import com.dc.eschool.controller.web.TestResultItemSearchWebImpl;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.web.*;

/**
 * Title: Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:  DC
 * @author wangshui
 * @version 1.0
 */

public class TestResultItemSearchListTag extends ListTag
{
  private String clause = "";
  private String emptyString = "testresultitem search : no match result.";
  private boolean hasNext = false;
  //private String userType;

  protected Collection findCollection() throws java.lang.Exception
  {
	Debug.println("ListTag_findCollection ...#1");

	TestResultItemSearchWebImpl tswi = (TestResultItemSearchWebImpl)
								  pageContext.getServletContext().getAttribute(WebKeys.TestResultItemSearchKey);

	if(tswi == null)
	  Debug.println("ListTag_findCollection ...#2");

	ListChunk lc = null;
	Collection testresultitem = null;

	clause +=
		" and "
		+ InterpretSQL.encodeEndSign("TestResultItem")
		+ "."
		+ InterpretSQL.encodeEndSign("Student")
		+ " = "
		+ pageContext.getRequest().getParameter("studentID");

	lc = tswi.searchTestResultItem(clause, startIndex-1, numItems);

	if(lc == null)
	{
	  Debug.println("no lc gotten");
	  return null;
	}
	else Debug.println("lc is not null: " + lc.getTotalCount());
	testresultitem = lc.getCollection();

	if (numItems==0) numItems=testresultitem.size();
	if (((startIndex -1 + testresultitem.size()) < lc.getTotalCount()) &&
	   testresultitem.size() >= numItems) hasNext = true;
	else hasNext = false;

	lastPage = lc.getTotalCount()/numItems;
	if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;

	return(testresultitem);
  }
  protected void initParamPrefix()
  {
	paramPrefix = "testResultItemSearch_";
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
  public void setNumItems(String numItemsStr) {
	super.setNumItems(numItemsStr);
  }

  public void setStartIndex(String startIndexStr) {
	super.setNumItems(startIndexStr);
  }

}