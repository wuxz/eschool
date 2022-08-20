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
 * @author Eric
 * @version 1.0
 */

public class LastFormTag extends TagSupport
{
  private String action = null;
  private ListTag listTag = null;

  public int doStartTag() throws JspTagException
  {

    // check if inside list tag
    listTag = (ListTag) findAncestorWithClass(this, ListTag.class);
    if (listTag == null)
    {
      throw new JspTagException("LastFormTag: nextForm tag not inside list" +
                                "tag");
    }

    // assume servlet request is http servlet request
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

    // if no next form is needed, don't process the body
    if (listTag.getLastPage() == 0) return (SKIP_BODY);

    // print out <form> tag and hidden input for startIndex
    try
    {
      JspWriter out = pageContext.getOut();
      out.print("<form");
      out.print("  method=\"post\"");
      out.println("  action=\"" + action +"?"+ request.getQueryString()+ "\">");
      out.println("  <input type=\"hidden\" name=\"" +
                  listTag.getParamPrefix() + listTag.getStartIndexParam() +
                  "\" value=\""+ ((listTag.getLastPage()-1)*listTag.getNumItems()+1)+"\">");

      out.print("  <input type=\"hidden\" name=\"" +
                listTag.getParamPrefix() + listTag.getNextParam());
      out.println("\" value=\"true\">");

    }
    catch(IOException ioe)
    {
      Debug.println("LastFormTag: error printing <form> tag");
    }
    return(EVAL_BODY_INCLUDE);
  }

  public int doEndTag()
  {
    // if next form has been printed out, print out </form> tag
    if (listTag.getLastPage() > 0)
    {
      try
      {
        JspWriter out = pageContext.getOut();
        out.print("</form>");
      }
      catch(IOException ioe)
      {
        Debug.println("LastFormTag: error printing <form> tag");
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