package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.project.model.ProjectModel;
import com.dc.eschool.util.EJBUtil;
import com.dc.eschool.util.InterpretSQL;

import com.dc.eschool.controller.taglib.ProjectDetailsTag;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class CourseAttributeTag extends ItemAttributeTag
{
	protected String createText()
	{
		CourseModel cm = (CourseModel)item;
				boolean isHave=false;
				if (cm.getCourseID()!=null) isHave=true;
		if(attribute == null) return "";

		if(isHave)
		{
			if(attribute.equalsIgnoreCase("courseID"))
			{
				return cm.getCourseID()+"";
			}else if(attribute.equalsIgnoreCase("courseName"))
			{
				return InterpretSQL.transformChinese(cm.getCourseName());
			}else if(attribute.equalsIgnoreCase("teacher"))
			{
				return cm.getTeacher()+"";
			}else if(attribute.equalsIgnoreCase("teacherName"))
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
			}
			else if (attribute.startsWith("selected_"))
			{
				int nFieldPos = attribute.indexOf("_") + 1;
				if (nFieldPos == -1)
				   return "";

				int nValuePos = attribute.indexOf("_", nFieldPos);
				if (nValuePos == -1 || nValuePos == attribute.length() - 1)
				   return "";

				String strField = attribute.substring(nFieldPos, nValuePos);
				String strValue = attribute.substring(nValuePos + 1);

				if ("course".equalsIgnoreCase(strField))
				   return strValue.equalsIgnoreCase(cm.getCourseID() + "") ? "selected" : "";
			}else if(attribute.equalsIgnoreCase("selectedprojectid"))
			{
				ProjectDetailsTag project = (ProjectDetailsTag)
						findAncestorWithClass(this, ProjectDetailsTag.class);

				if (project == null)
				   return "";

				ProjectModel pm = project.getPm();
				if (pm == null)
					return "";

				if(pm.getProjectID()!=null)
								{
								  if (pm.getCourseID().equals(cm.getCourseID()))
									 return " selected";
				}else
								{
					return "";
								}
			}
		}else
		{
			return "";
		}
		return "";
	}
}