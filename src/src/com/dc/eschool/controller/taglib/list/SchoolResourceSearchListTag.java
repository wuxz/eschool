package com.dc.eschool.controller.taglib.list;

import java.util.Collection;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Locale;

import com.dc.eschool.controller.web.SchoolResourceSearchWebImpl;

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

public class SchoolResourceSearchListTag extends ListTag
{
  private String clause = "";
  private String keyword = "";
  private String subjectID = "";
  private String time = "";
  private String emptyString = "schoolresource search : no match result.";
  private boolean hasNext = false;
  private String role = "";
  //private String userType;

  protected Collection findCollection() throws java.lang.Exception
  {
	Debug.println("ListTag_findCollection ...#1");

	SchoolResourceSearchWebImpl sswi = (SchoolResourceSearchWebImpl)
								  pageContext.getServletContext().getAttribute(WebKeys.SchoolResourceSearchKey);

	if(sswi == null)
	  Debug.println("ListTag_findCollection ...#2");

	ListChunk lc = null;
	Collection schoolresource = null;
/*
	if(userType!=null){
	  clause=" where \"UserType\"="+userType;
	}else{
	  clause=" ";
	}
	//clause=clause+" Order by \"UserID\"";
*/

	if (subjectID == null || subjectID.equals("null"))
	   return null;

	if (subjectID.equals(""))
		subjectID = null;

	if (keyword == null || keyword.equals("") || keyword.equals("null"))
	   keyword = "";

	keyword = InterpretSQL.transform(keyword);

	if (time == null || time.equals("") || time.equals("null"))
	   time = null;

	if (role == null || role.equals("") || role.equals("null"))
	   role = null;

	lc = sswi.searchSchoolResource(keyword, subjectID, time, role, startIndex-1, numItems);

	if(lc == null)
	{
	  Debug.println("no lc gotten");
	  return null;
	}
	else Debug.println("lc is not null: " + lc.getTotalCount());
	schoolresource = lc.getCollection();
	if (numItems==0) numItems=schoolresource.size();
	if (((startIndex -1 + schoolresource.size()) < lc.getTotalCount()) &&
	   schoolresource.size() >= numItems) hasNext = true;
	else hasNext = false;

	lastPage = lc.getTotalCount()/numItems;
	if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;

	return(schoolresource);
  }
  protected void initParamPrefix()
  {
	paramPrefix = "schooolResourceSearch_";
  }
  protected boolean needsNextForm()
  {
	return hasNext;
  }
  public String getClause()
  {
	return clause;
  }
  public String getKeyword()
  {
	return keyword;
  }
  public String getRole()
  {
	return role;
  }
  public String getSubjectID()
  {
	return subjectID;
  }
  public String getTime()
  {
	return time;
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
  public void setKeyword(String keyword)
  {
	this.keyword = keyword;
  }
  public void setSubjectID(String subjectID)
  {
	this.subjectID = subjectID;
  }
  public void setTime(String time)
  {
	this.time = time;
  }
  public void setRole(String role)
  {
	this.role = role;
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