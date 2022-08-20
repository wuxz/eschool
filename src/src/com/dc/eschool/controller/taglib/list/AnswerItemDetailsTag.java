package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.answeritem.model.AnswerItemModel;
import com.dc.eschool.controller.web.AnswerItemSearchWebImpl;
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
 * AnswerItemDetailsTag
 * -----------------
 */

  public class AnswerItemDetailsTag extends BodyTagSupport
  {
      private AnswerItemModel cm = new AnswerItemModel();
      private String answeritemId;

      public int doStartTag() throws JspTagException
      {
          AnswerItemSearchWebImpl eswi = (AnswerItemSearchWebImpl)
                                    pageContext.getServletContext().getAttribute(WebKeys.AnswerItemSearchKey);

          if(eswi == null)
                    Debug.println("The AnswerItemSearchWebImpl is null");


          answeritemId=pageContext.getRequest().getParameter("answerItemId");
          try
          {
	      cm=eswi.getAnswerItem(answeritemId);
          } catch (Exception e)
          {
	      throw new JspTagException("Exception while getting AnswerItem " + e);
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
          } catch(IOException ioe)
          {
              Debug.println("Error handling itcms tag: " + ioe);
          }
          return(SKIP_BODY);
      }

      public void setAnswerItemId(String answeritemId)
      {
        this.answeritemId = answeritemId;
      }

      public Object getCurrentAnswerItem()
      {
        return (Object)cm;
      }
}
