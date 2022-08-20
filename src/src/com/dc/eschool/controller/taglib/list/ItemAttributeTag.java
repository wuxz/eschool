package com.dc.eschool.controller.taglib.list;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;

import com.dc.eschool.util.Debug;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public abstract class ItemAttributeTag extends TagSupport
{
  protected Object item = null;
  protected String attribute = null;

  public int doStartTag() throws JspTagException
  {
    // check if itemAttribute tag is in items tag
    ItemsTag itemsTag = (ItemsTag) findAncestorWithClass(this, ItemsTag.class);
    if (itemsTag == null)
    {
      throw new JspTagException("ItemAttributeTag: itemsAttribute tag not" +
                                "inside items tag");
    }
    item = itemsTag.getCurrentItem();

    // print out attribute
    try
    {
      JspWriter out = pageContext.getOut();
      out.print(createText());
    }
    catch(IOException ioe)
    {
      Debug.println("ItemAttributeTag: Error printing attribute: " + ioe);
    }

    // there should be no body to process
    return(SKIP_BODY);
  }

  // setter for tag attribute
  public void setAttribute(String attribute)
  {
    this.attribute = attribute;
  }

  // protected abstract methods
  protected abstract String createText();
}