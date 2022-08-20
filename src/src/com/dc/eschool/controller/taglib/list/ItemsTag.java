package com.dc.eschool.controller.taglib.list;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;
import java.util.Iterator;

import com.dc.eschool.util.Debug;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class ItemsTag extends BodyTagSupport
{

  private Iterator iterator = null;
  private Object item = null;

  public int doStartTag() throws JspTagException
  {
    // check if items tag is in list tag
    ListTag listTag = (ListTag) findAncestorWithClass(this, ListTag.class);
    if (listTag == null)
    {
      throw new JspTagException("ItemsTag: items tag not inside items tag");
    }
    iterator = listTag.getIterator();
    if (iterator == null || !iterator.hasNext()) return(SKIP_BODY);
    item = iterator.next();
    return(EVAL_BODY_TAG);
  }

  // process the body again with the next item if it exists
  public int doAfterBody()
  {
    if (iterator.hasNext())
    {
      item = iterator.next();
      return(EVAL_BODY_TAG);
    }
    else return(SKIP_BODY);
  }

  // print out the resulting body content to the JSP page and evaluate the
  // rest of the page
  public int doEndTag()
  {
    try
    {
      BodyContent body = getBodyContent();
      if (body != null)
      {
        JspWriter out = body.getEnclosingWriter();
        out.print(body.getString());
      }
    }
    catch(IOException ioe)
    {
      Debug.println("Error handling items tag: " + ioe);
    }
    return(EVAL_PAGE);
  }

  // getter for inner tags
  public Object getCurrentItem()
  {
    return item;
  }
}