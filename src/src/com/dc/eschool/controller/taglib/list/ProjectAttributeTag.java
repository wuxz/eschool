package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.project.model.ProjectModel;
import com.dc.eschool.course.mgrbean.*;
import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class ProjectAttributeTag extends ItemAttributeTag
{


  protected String createText()
  {

	ProjectModel pm = (ProjectModel)item;
	if (pm==null) return"";
	if(attribute == null) return "";

	if(pm.getProjectID()!=null)
	{
	  if(attribute.equalsIgnoreCase("name"))
	  {
		return InterpretSQL.transformChinese(pm.getName());
	  }else if(attribute.equalsIgnoreCase("id"))
	  {
		return pm.getProjectID()+"";
	  }else if(attribute.equalsIgnoreCase("courseid"))
	  {
		  return pm.getCourseID()+"";
	  }else if(attribute.equalsIgnoreCase("coursename"))
	  {
				  try
				  {
					  CourseSLHome home=EJBUtil.getCourseSLHome();
					  CourseSL remote=home.create();
					  CourseModel cm=remote.getCourse(pm.getCourseID().toString());
					  return InterpretSQL.transformChinese(cm.getCourseName());
				  }catch(Exception e)
				  {
					System.out.println(e);
					return pm.getCourseID()+"";
				  }
	  }else if(attribute.equalsIgnoreCase("info"))
	  {
		return InterpretSQL.transformChinese(pm.getInfo());
	  }else if(attribute.equalsIgnoreCase("state"))
	  {
		return InterpretSQL.transformChinese(pm.getState());
	  }else if(attribute.equalsIgnoreCase("startdate"))
	  {
		return InterpretSQL.transformChinese(pm.getStartDate());
	  }
	  else if(attribute.equalsIgnoreCase("enddate"))
	  {
		return InterpretSQL.transformChinese(pm.getEndDate());
	  }else if(attribute.equalsIgnoreCase("createby"))
	  {
		return pm.getCreateBy()+"";
	  }else if(attribute.equalsIgnoreCase("creator"))
	  {
				  try
				  {
					  UsersSLHome home=EJBUtil.getUsersSLHome();
					  UsersSL remote=home.create();
					  UsersModel um=remote.getUser(pm.getCreateBy().toString());
					  return InterpretSQL.transformChinese(um.getName());
				  }catch(Exception e)
				  {
					System.out.println(e);
					return pm.getCreateBy()+"";
				  }
	  }else if(attribute.equalsIgnoreCase("type"))
	  {
		return InterpretSQL.transformChinese(pm.getType());
	  }else if(attribute.equalsIgnoreCase("modifydate"))
	  {
		return InterpretSQL.transformChinese(pm.getLastModifyDate());
	  }
	}else
	{
	  return "";
	}

	return "";
  }
}