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

public class ContentDetailsAttributeTag extends BodyTagSupport
{

  private String attribute = null;
  protected ContentModel cm = new ContentModel();

  public void setAttribute(String attribute)
  {
	this.attribute = attribute;
  }
  public int doStartTag() throws JspTagException
  {
	ContentDetailsTag content = (ContentDetailsTag)
						findAncestorWithClass(this, ContentDetailsTag.class);
	if(content == null)
	  throw new JspTagException("can not find parent tag object.");
	  System.out.println("attribute");
	cm = content.getCm();
		  System.out.println("attribute");
	try
	{
		JspWriter out = pageContext.getOut();
		out.print(printDetail());
	}
	catch(IOException ioe)
	{
		Debug.println("exception Contentdetails tag out :" + ioe);
	}
	return(SKIP_BODY);
  }
  protected String printDetail()
  {
	if (cm==null) return "";

	if(cm.getContentID()!=null)
        {
          if(attribute.equalsIgnoreCase("id"))
          {
            return cm.getContentID()+"";
          }else if(attribute.equalsIgnoreCase("name"))
          {
            return InterpretSQL.transformChinese(cm.getName());
          }else if(attribute.equalsIgnoreCase("filesize"))
          {
            return cm.getFileSize()+"";
          }else if(attribute.equalsIgnoreCase("docURL"))
          {
            return InterpretSQL.transformChinese(cm.getDocURL());
          }else if(attribute.equalsIgnoreCase("info"))
          {
            return InterpretSQL.transformChinese(cm.getInfo());
          }else if(attribute.equalsIgnoreCase("state"))
          {
            return InterpretSQL.transformChinese(cm.getState());
          }else if(attribute.equalsIgnoreCase("type"))
          {
            return InterpretSQL.transformChinese(cm.getType());
          }else if(attribute.equalsIgnoreCase("hasansweritem"))
          {
            return InterpretSQL.transformChinese(cm.getHasAnswerItem());
          }
          else if (attribute.startsWith("checked_"))
          {
                  int nFieldPos = attribute.indexOf("_") + 1;
                  if (nFieldPos == -1)
                     return "";

                  int nValuePos = attribute.indexOf("_", nFieldPos);
                  if (nValuePos == -1 || nValuePos == attribute.length() - 1)
                     return "";

                  String strField = attribute.substring(nFieldPos, nValuePos);
                  String strValue = attribute.substring(nValuePos + 1);
                  try
                  {
                          strValue = new String(strValue.getBytes("ISO8859-1"), "GB2312");
                  }
                  catch (Exception e)
                  {
                          e.printStackTrace();
                  }

                  if ("state".equalsIgnoreCase(strField))
                     return strValue.equalsIgnoreCase(cm.getState() + "") ? " checked" : "";
                  else
                          return "";
          }else
          {
            return "";
          }
        }else
        {
          return "";
        }
  }
  public int doEndTag()
  {
	return (EVAL_PAGE);
  }
}