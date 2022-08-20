package com.dc.eschool.controller.taglib.list;

import java.util.ArrayList;
import java.util.Collection;
import java.lang.Exception;
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

public class UsersSearchListTag extends ListTag
{
    private String clause = "";
    private String emptyString = "no match result.";
    private boolean hasNext = false;
    private String userType;
    private String value;

    protected Collection findCollection() throws java.lang.Exception
    {
        Debug.println("Fetching data from users......");

        UsersSearchWebImpl uswi = (UsersSearchWebImpl)
                                      pageContext.getServletContext().getAttribute(WebKeys.UsersSearchKey);

        if(uswi == null)
          Debug.println("The usersSearchWebImpl is null......");

        ListChunk lc = null;
        Collection users = null;
        lc = uswi.searchUsers(clause,value, startIndex-1, numItems);

        if(lc == null)
        {
            Debug.println("Can not get any data");
            return null;
        }

        users = lc.getCollection();

        if (numItems==0) numItems=users.size();
        if (((startIndex -1 + users.size()) < lc.getTotalCount()) &&
           users.size() >= numItems) hasNext = true;
        else hasNext = false;

        lastPage = lc.getTotalCount()/numItems;
        if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;

        return(users);
    }

    public int doEngTag()
    {
        try
        {
            if( collection == null||(collection != null && collection.size() == 0) )
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
        paramPrefix = "userSearch_";

        if (clause!=null)
        {
            if (clause.equals("course"))
              paramPrefix = paramPrefix + "course_";
        }
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

    public void setUserType(String userType)
    {
        this.userType = userType;
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