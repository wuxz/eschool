package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.WebKeys;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.EJBUtil;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */


/*
 * CourseDetailAttributeTag
 * -------------------------
 */

public class CourseDetailsAttributeTag extends TagSupport
{
	protected CourseModel cm = new CourseModel();
	protected String attribute = null;

	public int doStartTag() throws JspTagException
	{
		CourseDetailsTag courseDetail = (CourseDetailsTag)
						findAncestorWithClass(this, CourseDetailsTag.class);
		if (courseDetail == null)
		{
			throw new JspTagException("courseDetailAttrTag: courseDetailTag tag not" +
						"found");
		}

		cm = (CourseModel)courseDetail.getCurrentCourse();


		try
		{
			JspWriter out = pageContext.getOut();
			out.print(sendDetails());
		} catch(IOException ioe)
		{
			Debug.println("CourseDetailAttributeTag: " + "Error printing attribute: " + ioe);
		}
		return(SKIP_BODY);
	}

	public void setAttribute(String attribute)
	{
		this.attribute = attribute;
	}

	public int doEndTag()
	{
		return(EVAL_PAGE);
	}

	protected String sendDetails()
	{
		if(cm.getCourseID()!=null)
		{
			if(attribute.equalsIgnoreCase("CourseName"))
			{
				return InterpretSQL.transformChinese(cm.getCourseName());
			}else if(attribute.equalsIgnoreCase("Teacher"))
			{
				return (cm.getTeacher()+"");
			}else if(attribute.equalsIgnoreCase("TeacherName"))
			{
				try
                                {
                                        UsersSLHome home=EJBUtil.getUsersSLHome();
                                        UsersSL remote=home.create();
                                        UsersModel um=remote.getUser(cm.getTeacher().toString());
                                        return InterpretSQL.transformChinese(um.getName());
                                }catch(Exception e)
                                {
                                  System.out.println(e);
                                  return cm.getTeacher()+"";
                                }
			}else if(attribute.equalsIgnoreCase("startDate"))
			{
				return InterpretSQL.transformChinese(cm.getStartDate());
			}else if(attribute.equalsIgnoreCase("endDate"))
			{
				return InterpretSQL.transformChinese(cm.getEndDate());
			}else if(attribute.equalsIgnoreCase("info"))
			{
				return InterpretSQL.transformChinese(cm.getInfo());
			}else if(attribute.equalsIgnoreCase("state"))
			{
				return InterpretSQL.transformChinese(cm.getState());
			}else if(attribute.equalsIgnoreCase("title"))
			{
				return(InterpretSQL.transformChinese("±à¼­"));
			}else if(attribute.equalsIgnoreCase("button"))
			{
				return("save");
			}
			else if (attribute.startsWith("selected_"))
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
				   return strValue.equalsIgnoreCase(cm.getState() + "") ? " selected" : "";
				else
					return "";
		   }else
			{
				return("");
			}
		}else
		{
			if(attribute.equalsIgnoreCase("title"))
			{
				return(InterpretSQL.transformChinese("Ìí¼ÓÐÂ"));
			}else if(attribute.equalsIgnoreCase("button"))
			{
				return("add");
			}else
			{
				return("");
			}
		}
	}
}
