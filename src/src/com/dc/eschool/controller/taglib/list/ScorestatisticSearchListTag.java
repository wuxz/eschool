package com.dc.eschool.controller.taglib.list;

import java.util.Collection;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Locale;

import com.dc.eschool.controller.web.ScoreStatisticSearchWebImpl;

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

public class ScoreStatisticSearchListTag extends ListTag
{
  private String clause = "";
  private String value = "";
  private String emptyString = "ScoreStatistic search : no match result.";
  private boolean hasNext = false;
  //private String userType;

  protected Collection findCollection() throws java.lang.Exception
  {
	Debug.println("ListTag_findCollection ...#1");

	ScoreStatisticSearchWebImpl sswi = (ScoreStatisticSearchWebImpl)
								  pageContext.getServletContext().getAttribute(WebKeys.ScoreStatisticSearchKey);

	if(sswi == null)
	  Debug.println("ListTag_findCollection ...#2");

	ListChunk lc = null;
	Collection scorestatistic = null;
/*
	if(userType!=null){
	  clause=" where \"UserType\"="+userType;
	}else{
	  clause=" ";
	}
	//clause=clause+" Order by \"UserID\"";
*/

	clause +=
		" and "
		+ InterpretSQL.encodeEndSign("ProjectContent")
		+ "."
		+ InterpretSQL.encodeEndSign("ContentID")
		+ " = "
		+ pageContext.getRequest().getParameter("contentID");

	boolean isAsc = true;
	if ("desc".equals(value))
		isAsc = false;

	lc = sswi.searchScoreStatisticProject(clause, startIndex-1, numItems, isAsc);

	if(lc == null)
	{
	  Debug.println("no lc gotten");
	  return null;
	}
	else Debug.println("lc is not null: " + lc.getTotalCount());

	scorestatistic = lc.getCollection();
	if (numItems==0) numItems=scorestatistic.size();
	if (((startIndex -1 + scorestatistic.size()) < lc.getTotalCount()) &&
	   scorestatistic.size() >= numItems) hasNext = true;
	else hasNext = false;

	lastPage = lc.getTotalCount()/numItems;
	if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;

	return(scorestatistic);
  }
  protected void initParamPrefix()
  {
	paramPrefix = "scoreStatisticSearch_";
  }
  protected boolean needsNextForm()
  {
	return hasNext;
  }
  public String getClause()
  {
	return clause;
  }
  public String getValue()
  {
	return value;
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
  public void setValue(String value)
  {
	this.value = value;
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
/*
  public void setUserType(String userType)
  {
	this.userType = userType;
  }
*/
}