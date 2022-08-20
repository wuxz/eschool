package com.dc.eschool.controller.taglib.list;

import java.util.Collection;

import java.util.Collection;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Locale;

import com.dc.eschool.controller.web.ContentSearchWebImpl;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.web.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ContentSearchListTag extends ListTag
{
  private String clause = "";
  private String emptyString = "content search : no match result.";
  private boolean hasNext = false;
  private String method;
  private String value;

  protected Collection findCollection() throws java.lang.Exception
  {
	Debug.println("ListTag_findCollection ...#1");
	ContentSearchWebImpl cswi = (ContentSearchWebImpl)
	    pageContext.getServletContext().getAttribute(WebKeys.ContentSearchModelKey);
	if(cswi == null)
	{
	  Debug.println("webimpl is null");
	  return null;
	}

	ListChunk lc = null;
	Collection contents = null;

	try
	{
	  if(clause.equals("session"))
	  {
                Integer projectId=new Integer(pageContext.getRequest().getParameter("projectId"));
	        lc=EJBUtil.getSessionSLHome().create().getProject(projectId,method);

	  }else
	  {
		lc = cswi.searchContents(clause, startIndex-1, numItems,value);
	  }
	}catch (Exception e)
	{
	  System.out.println(e);
	}

	if(lc == null)
	{
	  Debug.println("no lc gotten");
	  return null;
	}
	else Debug.println("lc is not null: " + lc.getTotalCount());
	contents = lc.getCollection();

	System.out.println(contents.size());

	if (numItems==0) numItems=contents.size();
	if (((startIndex -1 + contents.size()) < lc.getTotalCount()) &&
	   contents.size() >= numItems) hasNext = true;
	else hasNext = false;

	lastPage = lc.getTotalCount()/numItems;
	if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;
	return(contents);
  }
  protected void initParamPrefix()
  {
	paramPrefix = "contentSearch_";
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
  public int doEndTag()
  {
	try
	{
	  if( collection == null
		  || ((collection != null)
		  && collection.size() == 0) )
		pageContext.getOut().println(emptyString);
	}
	catch(java.io.IOException ie)
	{
	  Debug.println(ie.getMessage());
	}
	return(EVAL_PAGE);
  }
  public void setNumItems(String numItemsStr)
  {
	super.setNumItems(numItemsStr);
  }

  public void setStartIndex(String startIndexStr)
  {
	super.setNumItems(startIndexStr);
  }
  public void setEmptyString(String emptyString)
  {
	this.emptyString = emptyString;
  }
  public void setMethod(String method)
  {
    this.method = method;
  }
  public String getMethod()
  {
    return method;
  }
  public void setValue(String value) {
    this.value = value;
  }
  public String getValue() {
    return value;
  }
}