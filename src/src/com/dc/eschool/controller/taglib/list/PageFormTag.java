package com.dc.eschool.controller.taglib.list;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.InterpretSQL;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class PageFormTag extends TagSupport
{
  private ListTag listTag = null;

  public int doStartTag() throws JspTagException
  {

    // check if inside list tag
    listTag = (ListTag) findAncestorWithClass(this, ListTag.class);
    if (listTag == null)
    {
      throw new JspTagException("PageTag: Page tag not inside list" +
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
      out.println(InterpretSQL.transformChinese("µÚ" +(listTag.getStartIndex()/listTag.getNumItems()+1) +
                  "Ò³/¹²"+ listTag.getLastPage()+"Ò³"));

    }
    catch(IOException ioe)
    {
      Debug.println("PageTag: error printing <form> tag");
    }
    return(EVAL_BODY_INCLUDE);
  }

  public int doEndTag()
  {
    return(EVAL_PAGE);
  }
}