package com.dc.eschool.controller.taglib;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;


import com.dc.eschool.util.*;
import com.dc.eschool.controller.web.ESchoolWebImpl;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class EschoolPublicTag extends BodyTagSupport
{

  private String attribute = null;
  private Integer projectID=new Integer(0);
  private String projectName="";
  private String courseName="";
  private Integer courseID=new Integer(0);
  private String projectType="";


  public int doStartTag() throws JspTagException
  {

    ESchoolWebImpl escwi = (ESchoolWebImpl)
	      pageContext.getSession().getAttribute(WebKeys.ESchoolWebKey);
    projectName=escwi.getProjectName();

    if(pageContext.getRequest().getParameter("type")==null)
    {
      projectType=escwi.getProjectType();
    }else
    {
      projectType=pageContext.getRequest().getParameter("type");
    }
    courseName=escwi.getCourseName();
    courseID=escwi.getCourseID();

    if(pageContext.getRequest().getParameter("projectId")==null)
	  projectID=escwi.getProjectID();

    try
    {
      JspWriter out = pageContext.getOut();
      out.print(printDetail());
    }
    catch(IOException ioe)
    {
        Debug.println("exception Contentdetails tag out :" + ioe);
    }

    return (SKIP_BODY);
  }
  public int doEndTag()
  {
    return (EVAL_PAGE);
  }
  public String getAttribute()
  {
    return attribute;
  }
  public void setAttribute(String attribute)
  {
    this.attribute = attribute;
  }

  protected String printDetail()
  {
    boolean isUpdate = true;
    PropertiesManager properMgr=new PropertiesManager();

    if(attribute == null)
    {
      return "";
    } else if(attribute.equalsIgnoreCase("projectid"))
    {
      return projectID+"";
    }else if(attribute.equalsIgnoreCase("projectName"))
    {
      return InterpretSQL.transformChinese(projectName);
    }else if(attribute.equalsIgnoreCase("mailpath"))
    {
      return properMgr.getMailPath();
    }else if(attribute.equalsIgnoreCase("coursename"))
    {
      return InterpretSQL.transformChinese(courseName);
    } else if(attribute.equalsIgnoreCase("courseid"))
    {
      return courseID+"";
    }else if(attribute.equalsIgnoreCase("projecttype"))
    {
      if(projectType.equals("test"))
      {
	return InterpretSQL.transformChinese("考试");
      }else if(projectType.equals("assignment"))
      {
	return InterpretSQL.transformChinese("作业");
      }else if(projectType.equals("listening"))
      {
	return InterpretSQL.transformChinese("听力");
      }else if(projectType.equals("selfstudy"))
      {
	return InterpretSQL.transformChinese("自习");
      }else if(projectType.equals("selftest"))
      {
	return InterpretSQL.transformChinese("自测");
      }else if(projectType.equals("review"))
      {
	return InterpretSQL.transformChinese("复习");
      }
    }
    return "";
  }
}
