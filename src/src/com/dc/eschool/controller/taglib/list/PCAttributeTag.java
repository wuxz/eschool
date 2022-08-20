package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.course.mgrbean.*;
import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.content.model.DataModel;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.EJBUtil;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class PCAttributeTag extends ItemAttributeTag
{
  protected String createText()
  {
	DataModel dm = (DataModel)item;
	if(attribute == null) return "";

	if(dm.getProjectID()!=null)
	{
	  if(attribute.equalsIgnoreCase("projectID"))
	  {
		return dm.getProjectID()+"";
	  }else if(attribute.equalsIgnoreCase("projectName"))
	  {
		return InterpretSQL.transformChinese(dm.getProjectName());
	  }else if(attribute.equalsIgnoreCase("courseID"))
	  {
		return dm.getCourseID()+"";
	  }else if(attribute.equalsIgnoreCase("courseName"))
	  {
			try
			{
				CourseSLHome home=EJBUtil.getCourseSLHome();
				CourseSL remote=home.create();
				CourseModel cm=remote.getCourse(dm.getCourseID().toString());
				return InterpretSQL.transformChinese(cm.getCourseName());
			}catch(Exception e)
			{
			  System.out.println(e);
			  return dm.getCourseID()+"";
			}
	  }else if(attribute.equalsIgnoreCase("info"))
	  {
		return InterpretSQL.transformChinese(dm.getProjectInfo());
	  }else if(attribute.equalsIgnoreCase("startDate"))
	  {
		return InterpretSQL.transformChinese(dm.getProjectStartDate());
	  }else if(attribute.equalsIgnoreCase("enddate"))
	  {
		return InterpretSQL.transformChinese(dm.getProjectEndDate());
	  }else if(attribute.equalsIgnoreCase("createBy"))
	  {
		return dm.getProjectCreateBy()+"";
	  }else if(attribute.equalsIgnoreCase("creator"))
	  {
			try
			{
				UsersSLHome home=EJBUtil.getUsersSLHome();
				UsersSL remote=home.create();
				UsersModel um=remote.getUser(dm.getProjectCreateBy().toString());
				return InterpretSQL.transformChinese(um.getName());
			}catch(Exception e)
			{
			  System.out.println(e);
			  return dm.getProjectCreateBy()+"";
			}
		}else if(attribute.equalsIgnoreCase("lastModifyDate"))
		{
			return InterpretSQL.transformChinese(dm.getProjectLastModifyDate());
		}else if(attribute.equalsIgnoreCase("lastmodifytime"))
		{
			return InterpretSQL.transformChinese(dm.getProjectLastModifyTime());
		}else if(attribute.equalsIgnoreCase("contentID"))
		{
			return dm.getContentID()+"";
		}else if(attribute.equalsIgnoreCase("contentName"))
		{
			return InterpretSQL.transformChinese(dm.getContentName());
		}else if(attribute.equalsIgnoreCase("docurl"))
		{
			return InterpretSQL.transformChinese(dm.getDocURL());
		}else if(attribute.equalsIgnoreCase("savename"))
		{
			return InterpretSQL.transformChinese(dm.getDocURL());
		}else if(attribute.equalsIgnoreCase("hashcode"))
		{
			return dm.getDocURL().hashCode() + "";
		}
	}else
	{
	  return "";
	}

	return "";
  }
}