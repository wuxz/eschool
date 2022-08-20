package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.controller.web.CourseSearchWebImpl;
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
 * CourseDetailsTag
 * -----------------
 */

  public class CourseDetailsTag extends BodyTagSupport
  {
      private CourseModel cm = new CourseModel();
      private String courseId;

      public int doStartTag() throws JspTagException
      {
          CourseSearchWebImpl eswi = (CourseSearchWebImpl)
                                    pageContext.getServletContext().getAttribute(WebKeys.CourseSearchKey);

          if(eswi == null)
                    Debug.println("The CourseSearchWebImpl is null");


          courseId=pageContext.getRequest().getParameter("courseId");
          try
          {
	      cm=eswi.getCourse(courseId);
          } catch (Exception e)
          {
	      throw new JspTagException("Exception while getting Course " + e);
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

      public void setCourseId(String courseId)
      {
        this.courseId = courseId;
      }

      public Object getCurrentCourse()
      {
        return (Object)cm;
      }
}
