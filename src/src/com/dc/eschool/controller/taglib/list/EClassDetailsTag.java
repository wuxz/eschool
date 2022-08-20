package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.eclass.model.EClassModel;
import com.dc.eschool.controller.web.EClassSearchWebImpl;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.Debug;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */


/*
 * EClassDetailsTag
 * -----------------
 */

  public class EClassDetailsTag extends BodyTagSupport
  {
      private EClassModel em = null;
      private String classId;
      public int doStartTag() throws JspTagException
      {
          EClassSearchWebImpl eswi = (EClassSearchWebImpl)
                                  pageContext.getServletContext().getAttribute(WebKeys.EClassSearchKey);

          if(eswi == null)
                  Debug.println("The EClassSearchWebImpl is null");

          classId=pageContext.getRequest().getParameter("classId");
          try
          {
              em=eswi.getEClass(classId);
          } catch (Exception e)
          {
              throw new JspTagException("Exception while getting EClass " + e);
          }
          return(EVAL_BODY_TAG);
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
          } catch(IOException ioe) {
              Debug.println("Error handling items tag: " + ioe);
          }
          return(SKIP_BODY);
      }

      public void setClassId(String classId)
      {
          this.classId = classId;
      }

      public Object getCurrentEClass()
      {
          return (Object)em;
      }
  }
