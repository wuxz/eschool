package com.dc.eschool.controller.taglib.list;

import java.lang.Exception;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Locale;

import com.dc.eschool.controller.web.AnswerItemSearchWebImpl;
import com.dc.eschool.controller.web.*;

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

public class AnswerItemSearchListTag extends ListTag
{
    private String clause = "";
    private String emptyString = "No match data";
    private boolean hasNext = false;

    protected Collection findCollection() throws java.lang.Exception
    {
        Debug.println("Fetching data from answeritem table......");

        //SchoolResourceSearchWebImpl sswi = (SchoolResourceSearchWebImpl)
          //                        pageContext.getServletContext().getAttribute(WebKeys.SchoolResourceSearchKey);

        AnswerItemSearchWebImpl eswi = (AnswerItemSearchWebImpl)
                                    pageContext.getServletContext().getAttribute(WebKeys.AnswerItemSearchKey);

        if(eswi == null)
            Debug.println("The AnsweritemSearch WebImpl is null.");

        ListChunk lc = null;
        Collection AnswerItem = null;

        lc = eswi.searchAnswerItem(clause, startIndex-1, numItems);

        if(lc == null)
        {
            Debug.println("Can not get any data");
            return null;
        }


        AnswerItem = lc.getCollection();

        if (numItems==0) numItems=AnswerItem.size();
        if (((startIndex -1 + AnswerItem.size()) < lc.getTotalCount()) &&
            AnswerItem.size() >= numItems) hasNext = true;
        else hasNext = false;

        lastPage = lc.getTotalCount()/numItems;
        if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;
        return(AnswerItem);
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