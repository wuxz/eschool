package com.dc.eschool.controller.taglib.list;

import java.util.*;

import com.dc.eschool.examinationscore.model.ExaminationScoreModel;
import com.dc.eschool.course.mgrbean.*;
import com.dc.eschool.course.model.*;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.project.mgrbean.*;
import com.dc.eschool.project.model.ProjectModel;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.taglib.ProjectDetailsTag;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ExaminationScoreAttributeTag extends ItemAttributeTag
{
	protected String createText()
	{
		ExaminationScoreModel cm = (ExaminationScoreModel)item;
		if(attribute == null) return "";

		if(cm != null)
		{
			if(attribute.equalsIgnoreCase("lastModifyDate"))
			{
				return cm.getLastModifyDate();
			}else if(attribute.equalsIgnoreCase("grade"))
			{
				return cm.getTestResultItemID() + "";
			}else if(attribute.equalsIgnoreCase("score"))
			{
				return cm.getScore() + "";
			}else if(attribute.equalsIgnoreCase("courseName"))
			{
				try
				{
					CourseSLHome home=EJBUtil.getCourseSLHome();
					CourseSL remote=home.create();
					ListChunk lc = remote.searchCourse("searchProjectID", 0, 0, cm.getProjectID() + "");
					Collection cl = lc.getCollection();
					Iterator it = cl.iterator();
					if (it.hasNext())
					{
						CourseModel cmm =(CourseModel)it.next();
						return InterpretSQL.transformChinese(cmm.getCourseName());
					}
					else
						return "";
				}catch(Exception e)
				{
				  System.out.println(e);
				  return cm.getProjectID()+"";
				}
			}else if(attribute.equalsIgnoreCase("projectName"))
			{
				try
				{
					ProjectMgrSLHome home=EJBUtil.getPMSLHome();
					ProjectMgrSL remote=home.create();
					return InterpretSQL.transformChinese(remote.getProject(cm.getProjectID().toString()).getName());
				}catch(Exception e)
				{
				  System.out.println(e);
				  return cm.getProjectID()+"";
				}
			}else if(attribute.equalsIgnoreCase("studentName"))
			{
				try
				{
					UsersSLHome home=EJBUtil.getUsersSLHome();
					UsersSL remote=home.create();
					UsersModel um=remote.getUser(cm.getStudent().toString());
					return InterpretSQL.transformChinese(um.getName());
				}catch(Exception e)
				{
				  System.out.println(e);
				  return cm.getStudent()+"";
				}
			}else if(attribute.equalsIgnoreCase("date"))
			{
				return InterpretSQL.transformChinese(cm.getLastModifyDate());
			}
		}else
		{
			return "";
		}
		return "";
	}
}