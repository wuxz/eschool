package com.dc.eschool.controller.taglib.list;

import java.lang.Exception;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Locale;

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

public class CourseSearchListTag extends ListTag
{
    private String clause = "";
    private String emptyString = "No match result.";
    private boolean hasNext = false;
    private String value;

    protected Collection findCollection() throws java.lang.Exception
    {
        Debug.println("Fetching data from course......");

        CourseSearchWebImpl eswi = (CourseSearchWebImpl)
                                      pageContext.getServletContext().getAttribute(WebKeys.CourseSearchKey);

        if(eswi == null)
            Debug.println("CourseSearchWebImpl is null");


        if (clause==null) clause="";
        if(clause.equals("teacher")||clause.equals("student"))
        {
            ESchoolWebImpl escwi = (ESchoolWebImpl)
                                      pageContext.getSession().getAttribute(WebKeys.ESchoolWebKey);
            value=escwi.getUserID().toString();
            if(escwi.getUserType().equals("π‹¿Ì‘±"))
              value="eschool_admin";
        }

        ListChunk lc = null;
        Collection Course = null;

        lc = eswi.searchCourse(clause, startIndex-1, numItems,value);

        if(lc == null)
        {
            Debug.println("can not get any data from course");
            return null;
        }

        Course = lc.getCollection();

        if (numItems==0) numItems=Course.size();
        if (((startIndex -1 + Course.size()) < lc.getTotalCount()) &&
            Course.size() >= numItems) hasNext = true;
        else hasNext = false;

        lastPage = lc.getTotalCount()/numItems;
        if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;
        return(Course);
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
        paramPrefix = "CourseSearch_";
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
    public void setValue(String value)
    {
      this.value = value;
    }
    public String getValue()
    {
      return value;
    }

}