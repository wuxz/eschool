package com.dc.eschool.controller.taglib;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;
import java.util.Locale;
import java.util.Iterator;
import java.util.Collection;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.web.ProjectSearchWebImpl;
import com.dc.eschool.project.model.ProjectModel;
import com.dc.eschool.course.mgrbean.*;
import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.examinationscore.model.ExaminationScoreModel;
import com.dc.eschool.examinationscore.mgrbean.*;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class ProjectDetailsAttributeTag extends BodyTagSupport
{

  private String attribute = null;
  private ProjectModel pm = new ProjectModel();

  public int doStartTag() throws JspTagException
  {
	ProjectDetailsTag project = (ProjectDetailsTag)
						findAncestorWithClass(this, ProjectDetailsTag.class);
	if(project == null)
	{

	  Debug.println("can not find parent tag object.");
	  throw new JspTagException("can not find parent tag object.");
	}
	pm = project.getPm();
	Debug.println("PROJECT_DETAILS_ATTRIBUTE: "+pm.getProjectID()+"");
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
  public ProjectModel getPm()
  {
	return pm;
  }
  public void setPm(ProjectModel pm)
  {
	this.pm = pm;
  }
  protected String printDetail()
  {
	boolean isUpdate = true;
	if(pm == null) return "";

	if(pm.getProjectID()!=null)
		{
		  if(attribute == null)
		  {
			return "";
		  }else if(attribute.equalsIgnoreCase("title"))
		  {
			return(InterpretSQL.transformChinese("编辑"));
		  }else if(attribute.equalsIgnoreCase("button"))
		  {
				return("save");
		  } else if(attribute.equalsIgnoreCase("type"))
		  {
			return InterpretSQL.transformChinese(pm.getType());
		  }else if(attribute.equalsIgnoreCase("projectId"))
		  {
			return pm.getProjectID()+"";
		  }else if(attribute.equalsIgnoreCase("name"))
		  {
			return InterpretSQL.transformChinese(pm.getName());
		  } else if(attribute.equalsIgnoreCase("coursename"))
		  {
			try
			{
				  CourseSLHome home=EJBUtil.getCourseSLHome();
		  CourseSL remote=home.create();
		  CourseModel cm= remote.getCourse(pm.getCourseID().toString());
		  return InterpretSQL.transformChinese(cm.getCourseName());
			}
			catch(Exception e)
			{
				  Debug.println(e.getMessage());
				  return "科目编号: "+pm.getCourseID();
			}
		  }else if(attribute.equalsIgnoreCase("info"))
		  {
			return InterpretSQL.transformChinese(pm.getInfo());
		  }else if(attribute.equalsIgnoreCase("state"))
		  {
			return InterpretSQL.transformChinese(pm.getState());
		  } else if(attribute.equalsIgnoreCase("startdate"))
		  {
			return InterpretSQL.transformChinese(pm.getStartDate());
		  } else if(attribute.equalsIgnoreCase("enddate"))
		  {
			return InterpretSQL.transformChinese(pm.getEndDate());
		  }
		else if (attribute.equalsIgnoreCase("score"))
		{
			String studentName = InterpretSQL.transformChinese("学生姓名：");
			String score = InterpretSQL.transformChinese("成绩：");

			try
			{
				Debug.println("attribute:" + attribute);

				ExaminationScoreSLHome eshome = (ExaminationScoreSLHome)EJBUtil.getExaminationScoreSLHome();
				ExaminationScoreSL essl = eshome.create();

				String clause =
					   " where " +
					   InterpretSQL.encodeEndSign("ProjectID") +
					   " = " +
					   pm.getProjectID() +
					   " and " +
					   InterpretSQL.encodeEndSign("Student") +
					   " = " +
					   pageContext.getRequest().getParameter("studentID");

				ListChunk lc = essl.searchExaminationScore(clause, 0, 0);
				Collection esc = lc.getCollection();
				Iterator it = esc.iterator();
				if (it.hasNext())
				{
					ExaminationScoreModel esm = (ExaminationScoreModel)it.next();
					score += esm.getScore();

					UsersSLHome home = EJBUtil.getUsersSLHome();
					UsersSL remote = home.create();
					UsersModel um = remote.getUser(esm.getStudent().toString());
					studentName += InterpretSQL.transformChinese(um.getName());
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			return studentName + "&nbsp;&nbsp;&nbsp;&nbsp;" + score;
		}
		  else if (attribute.startsWith("checked_"))
		  {
				  Debug.println("attribute:" + attribute);
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
					 return strValue.equalsIgnoreCase(pm.getState() + "") ? " checked" : "";
				  else if ("publishresult".equalsIgnoreCase(strField))
					 return strValue.equalsIgnoreCase(pm.getPublishResult() + "") ? " checked" : "";
		  }
		}else
		{
		  if(attribute.equalsIgnoreCase("title"))
		  {
			  return(InterpretSQL.transformChinese("添加新"));
		  }else if(attribute.equalsIgnoreCase("button"))
		  {
			  return("add");
		  }else
		  {
			  return "";
		  }
		}
	return "";
  }
}