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

public class EClassSearchListTag extends ListTag
{
	private String clause = "";
	private String emptyString = "No match data";
	private boolean hasNext = false;

	protected Collection findCollection() throws java.lang.Exception
	{
		Debug.println("Fetching data from eclass......");


		EClassSearchWebImpl eswi = (EClassSearchWebImpl)
                  pageContext.getServletContext().getAttribute(WebKeys.EClassSearchKey);


		if(eswi == null)
			Debug.println("The EClassSearchWebImpl is null.");
		ListChunk lc = null;
		Collection eclass = null;
		lc = eswi.searchEClass(clause, startIndex-1, numItems);
		if(lc == null)
		{
			Debug.println("Can no get any data");
			return null;
		}

		eclass = lc.getCollection();

                if (numItems==0) numItems=eclass.size();
		if (((startIndex -1 + eclass.size()) < lc.getTotalCount()) &&
			eclass.size() >= numItems) hasNext = true;
		else hasNext = false;

		lastPage = lc.getTotalCount()/numItems;

		if ( lc.getTotalCount()%numItems > 0) lastPage = lastPage+1;

		return(eclass);
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
		paramPrefix = "eclassSearch_";
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

	public void setNumItems(String numItemsStr)
	{
		super.setNumItems(numItemsStr);
	}

	public void setStartIndex(String startIndexStr)
	{
		super.setNumItems(startIndexStr);
	}
}