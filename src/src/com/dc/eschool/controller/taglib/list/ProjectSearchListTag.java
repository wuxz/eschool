package com.dc.eschool.controller.taglib.list;

import java.util.Collection;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Locale;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.web.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class ProjectSearchListTag extends ListTag
{
  private String clause = "";
  private String emptyString = "no match result.";
  private boolean hasNext = false;
  private String course;
  private String value;

  protected Collection findCollection() throws java.lang.Exception
  {
	ProjectSearchWebImpl pswi = (ProjectSearchWebImpl)
								  pageContext.getServletContext().getAttribute(WebKeys.SearchModelKey);

	if(pswi == null)
	  return null;

	ListChunk lc = null;
	Collection projects = null;
	ESchoolWebImpl escwi = (ESchoolWebImpl)
									  pageContext.getSession().getAttribute(WebKeys.ESchoolWebKey);
	value=escwi.getUserID().toString();
	if (clause==null) clause="test";
	clause = InterpretSQL.transform(clause);
	String userType=escwi.getUserType();

	lc = pswi.searchProjects(clause, startIndex-1, numItems,course,value,userType);
	if(lc == null)
	{
	  return null;
	}

	projects = lc.getCollection();
	if (numItems==0) numItems=projects.size();
	if (((startIndex -1 + projects.size()) < lc.getTotalCount()) &&
	   projects.size() >= numItems) hasNext = true;
	else hasNext = false;

	lastPage = lc.getTotalCount()/numItems;
	if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;

	return(projects);
  }
  protected void initParamPrefix()
  {
	paramPrefix = "projectSearch_";
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
  public void setCourse(String course)
  {
	this.course = course;
  }
  public String getCourse()
  {
	return course;
  }
  public void setValue(String value)
  {
	this.value = value;
  }
  public String getValue()
  {
	return value;
  }

}