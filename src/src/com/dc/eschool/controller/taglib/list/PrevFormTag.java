package com.dc.eschool.controller.taglib.list;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import com.dc.eschool.util.Debug;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class PrevFormTag extends TagSupport
{
  private String action = null;
  private ListTag listTag;

  public int doStartTag() throws JspTagException
  {

    // check if inside list tag
    listTag = (ListTag) findAncestorWithClass(this, ListTag.class);
    if (listTag == null)
    {
      throw new JspTagException("PrevFormTag: prevForm tag not inside list" +
                                "tag");
    }

    // assume servlet request is http servlet request
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

    // if the start index is already at 1, don't need a prev form
    if (!listTag.hasPrevForm()) return(SKIP_BODY);

    // print out <form> tag and hidden input for startIndex
    try
    {
      JspWriter out = pageContext.getOut();
      out.print("<form");
      out.print("  method=\"post\"");
      out.println("  action=\"" + action +"?"+ request.getQueryString()+ "\">");
      out.println("  <input type=\"hidden\" name=\"" +
                listTag.getParamPrefix() + listTag.getStartIndexParam() +
                "\" value=\"" +
                (listTag.getStartIndex() - listTag.getNumItems()) + "\">");
      out.println("  <input type=\"hidden\" name=\"" +
                listTag.getParamPrefix() + listTag.getPrevParam() +
                "\" value=\"true\">");
     }
     catch(IOException ioe)
     {
      Debug.println("PrevFormTag: error printing <form> tag");
    }
    return(EVAL_BODY_INCLUDE);
  }

  public int doEndTag()
  {
    // if the start index isn't already at 1, print out </form> tag
    if (listTag.hasPrevForm())
    {
      try
      {
       JspWriter out = pageContext.getOut();
        out.print("</form>");
      }
      catch(IOException ioe)
      {
        Debug.println("PrevFormTag: error printing <form> tag");
      }
    }
    return(EVAL_PAGE);
  }

  // setter for tag attribute
  public void setAction(String action)
  {
    this.action = action;
  }
}