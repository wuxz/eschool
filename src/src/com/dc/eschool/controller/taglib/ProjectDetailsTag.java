package com.dc.eschool.controller.taglib;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;
import java.util.Locale;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.web.*;
import com.dc.eschool.course.mgrbean.*;
import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.project.model.ProjectModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class ProjectDetailsTag extends BodyTagSupport
{

  protected ProjectModel pm = new ProjectModel();
  private String projectId;
  private String courseName="";

  public int doStartTag() throws JspTagException
  {
    ProjectSearchWebImpl pswi = (ProjectSearchWebImpl)
	      pageContext.getServletContext().getAttribute(WebKeys.SearchModelKey);
    ESchoolWebImpl escwi = (ESchoolWebImpl)
	      pageContext.getSession().getAttribute(WebKeys.ESchoolWebKey);

    if(pswi == null)
    {
      Debug.println("PROJECT_DETAILS_TAG: WebImpl is null.");
      throw new JspTagException("PROJECT_DETAILS_TAG: WebImpl is null.");
    }

    projectId=pageContext.getRequest().getParameter("projectId");
    //if(projectId==null) projectId=escwi.getProjectID().toString();


      pm = pswi.getProjectDetails(projectId);

    Debug.println("PROJECT_DETAIL: gotten model:"+projectId);
    if(pm == null)
    {
      Debug.println("PROJECT_DETAILS_TAG: Model is null.");
      throw new JspTagException("PROJECT_DETAILS_TAG: Model is null.");
    }

    try
      {
        CourseSLHome home=EJBUtil.getCourseSLHome();
	CourseSL remote=home.create();
	CourseModel cm= remote.getCourse(pm.getCourseID().toString());
	System.out.println(pm.getCourseID()+"::::::::");
	courseName=cm.getCourseName();
	System.out.println(courseName+":::::::CourseName");
      }
      catch(Exception e)
      {
        System.out.println(e);
      }
    escwi.setProjectID(pm.getProjectID());
    escwi.setProjectType(pm.getType());
    escwi.setCourseID(pm.getCourseID());
    escwi.setProjectName(pm.getName());
    escwi.setCourseName(courseName);
    return (EVAL_BODY_TAG);

  }

  public String getProjectId()
  {
    return projectId;
  }
  public ProjectModel getPm()
  {
    return pm;
  }
  public void setPm(ProjectModel pm)
  {
    this.pm = pm;
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
  public void setProjectId(String projectId)
  {
    this.projectId = projectId;
  }


}