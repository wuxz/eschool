package com.dc.eschool.controller.taglib.list;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Collection;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public abstract class ListTag extends TagSupport
{
  protected Collection collection;

  // this should be initialized in initParamPrefix
  protected String paramPrefix = null;

  // tag attributes
  protected int numItems = -1;
  protected int startIndex = 1;

  // constants
  protected final String NEXT_PARAM = "next";
  protected final String PREV_PARAM = "prev";
  protected final String STARTINDEX_PARAM = "startIndex";

  // parameter values retrieved from session object
  protected String nextParamValue = null;
  protected String prevParamValue = null;
  protected String startIndexParamValue = null;

  // other variables referred to by inner tags
  protected boolean hasNextForm = true;
  protected boolean hasPrevForm = false;
  protected Iterator iterator = null;
  protected int lastPage=0;

  //protected HttpSession session = null;

  public int doStartTag() throws JspTagException
  {

    // make sure attributes are valid
    if (numItems < 0)
      throw new JspTagException("ListTag: invalid numItems");
    if (startIndex < 1)
      throw new JspTagException("ListTag: invalid startIndex");

    // initialize param prefix and values from session attribute
    initParamPrefix();
    nextParamValue = pageContext.getRequest().getParameter(paramPrefix + NEXT_PARAM);
    prevParamValue = pageContext.getRequest().getParameter(paramPrefix + PREV_PARAM);
    startIndexParamValue = pageContext.getRequest().getParameter(paramPrefix + STARTINDEX_PARAM);
    // change startIndex if necessary
    if ((nextParamValue != null || prevParamValue != null) &&
        (startIndexParamValue != null))
        {
      startIndex = Integer.parseInt(startIndexParamValue);
    }
    // minimum startindex is 1; if minimum no prev form should appear
    if (startIndex <= 1)
    {
      startIndex = 1;
      hasPrevForm = false;
    }
    else
    {
      hasPrevForm = true;
    }
    // set up iterator
    try
    {
        collection = findCollection();
        if (collection == null ||
             ((collection != null) && collection.size() == 0))
        {
            return(SKIP_BODY);
        }

        iterator = collection.iterator();
        hasNextForm = needsNextForm();
    }
    catch(Exception e)
    {
        collection = null;
        return(SKIP_BODY);
    }
    //System.out.println(lastPage);
    return(EVAL_BODY_INCLUDE);
  }

  public int doEndTag()
  {
    collection = null;
    return(EVAL_PAGE);
  }

  // getters for inner tags
  public String getParamPrefix()
  {
    return paramPrefix;
  }

  public int getNumItems()
  {
    return numItems;
  }

  public int getStartIndex()
  {
    return startIndex;
  }

  public String getStartIndexParam()
  {
    return STARTINDEX_PARAM;
  }

  public String getNextParam()
  {
    return NEXT_PARAM;
  }

  public String getPrevParam()
  {
    return PREV_PARAM;
  }

  public boolean hasNextForm()
  {
    return hasNextForm;
  }

  public boolean hasPrevForm()
  {
    return hasPrevForm;
  }

  public Iterator getIterator()
  {
    return iterator;
  }

  // setters
  public void setNumItems(String numItemsStr)
  {
    numItems = Integer.parseInt(numItemsStr);
  }

  public void setStartIndex(String startIndexStr)
  {
    startIndex = Integer.parseInt(startIndexStr);
  }

  // protected abstract methods

  // should set the prefix to something like "myList_" for MyListTag, e.g.
  protected abstract void initParamPrefix();

  protected abstract Collection findCollection() throws Exception;

  // should be called after findCollection
  protected abstract boolean needsNextForm();
  public int getLastPage()
  {
    return lastPage;
  }
}