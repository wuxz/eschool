package com.dc.eschool.controller.taglib.list;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import com.dc.eschool.controller.web.*;
import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SubjectSearchListTag extends ListTag
{
    private String clause = "";
    private String emptyString = "No match result.";
    private boolean hasNext = false;

    protected Collection findCollection() throws java.lang.Exception
    {
        Debug.println("Fectching Subject table......");

        SubjectSearchWebImpl eswi = (SubjectSearchWebImpl)
                                      pageContext.getServletContext().getAttribute(WebKeys.SubjectSearchKey);

        if(eswi == null)
            Debug.println("The SubjectSearchWebImpl is null");

        ListChunk lc = null;
        Collection Subject = null;
        lc = eswi.searchSubject(clause, startIndex-1, numItems);
        if(lc == null)
        {
            Debug.println("Can not get any data from subject");
            return null;
        }

        Subject = lc.getCollection();

        if (numItems==0) numItems=Subject.size();
        if (((startIndex -1 + Subject.size()) < lc.getTotalCount()) &&
            Subject.size() >= numItems) hasNext = true;
        else hasNext = false;

        lastPage = lc.getTotalCount()/numItems;
        if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;
        return(Subject);
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
        paramPrefix = "SubjectSearch_";
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