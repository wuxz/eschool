package com.dc.eschool.controller.taglib;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;
import java.util.Locale;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.web.ContentSearchWebImpl;
import com.dc.eschool.content.model.ContentModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class ContentDetailsTag extends BodyTagSupport
{
  private ContentModel cm = new ContentModel();
  private String contentId;

  public int doStartTag() throws JspTagException
  {
    ContentSearchWebImpl cswi = (ContentSearchWebImpl)
    pageContext.getServletContext().getAttribute(WebKeys.ContentSearchModelKey);
    if(cswi == null)
    {
      String ex = "CONTENT_DETAIL_TAG: content search webimpl is null.";
      Debug.println(ex);
      throw new JspTagException(ex);
    }
    if (contentId==null) contentId=pageContext.getRequest().getParameter("contentID");

    Debug.println(""+contentId);
    cm = cswi.getContentDetails(contentId);
    if(cm == null)
    {
      String ex = "CONTENT_DETAIL_TAG: Model is null.";
      Debug.println(ex);
      throw new JspTagException(ex);
    }

    return (EVAL_BODY_TAG);
  }
  public ContentModel getCm()
  {
    return cm;
  }
  public void setCm(ContentModel cm)
  {
    this.cm = cm;
  }
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
    return(SKIP_BODY);
  }
  public void setContentId(String contentId)
  {
    this.contentId = contentId;
  }
  public String getContentId()
  {
    return contentId;
  }
}