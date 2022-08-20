package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.eclass.mgrbean.*;
import com.dc.eschool.eclass.model.EClassModel;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.course.model.CourseModel;
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

public class UsersAttributeTag extends ItemAttributeTag
{
	protected String createText()
	{
		UsersModel um = (UsersModel)item;

		if(attribute == null) return "";

		if(um.getUserID()!=null)
				{
				  if(attribute.equalsIgnoreCase("loginName"))
				  {
						  return InterpretSQL.transformChinese(um.getLoginName());
				  }else if(attribute.equalsIgnoreCase("userID"))
				  {
						  return um.getUserID()+"";
				  }else if(attribute.equalsIgnoreCase("password"))
				  {
						  return InterpretSQL.transformChinese(um.getPassword());
				  }else if(attribute.equalsIgnoreCase("name"))
				  {
						  return InterpretSQL.transformChinese(um.getName());
				  }else if(attribute.equalsIgnoreCase("gender"))
				  {
						  return InterpretSQL.transformChinese(um.getGender());
				  }else if(attribute.equalsIgnoreCase("birthday"))
				  {
						  return InterpretSQL.transformChinese(um.getBirthday());
				  }else if(attribute.equalsIgnoreCase("tel"))
				  {
						  return InterpretSQL.transformChinese(um.getTel());
				  }else if(attribute.equalsIgnoreCase("email"))
				  {
						  return InterpretSQL.transformChinese(um.getEmail());
				  }else if(attribute.equalsIgnoreCase("adrress"))
				  {
						  return InterpretSQL.transformChinese(um.getAddress());
				  }else if(attribute.equalsIgnoreCase("classID"))
				  {
						  return um.getClassID()+"";
				  }else if(attribute.equalsIgnoreCase("userType"))
				  {
						  return InterpretSQL.transformChinese(um.getUserType());
				  }else if(attribute.equalsIgnoreCase("state"))
				  {
						  return InterpretSQL.transformChinese(um.getState());
				  }else if(attribute.equalsIgnoreCase("allow"))
				  {
						  return InterpretSQL.transformChinese(um.getAllow());
				  }else if(attribute.equalsIgnoreCase("selectedteacherid"))
				  {
						  CourseDetailsTag course = (CourseDetailsTag)
										  findAncestorWithClass(this, CourseDetailsTag.class);

						  if (course == null)
							 return "";

						  CourseModel cm = (CourseModel)course.getCurrentCourse();
						  if (cm == null)
							return "";

						  if(cm.getTeacher()!=null)
						  {
							if (cm.getTeacher().equals(um.getUserID()))
							   return " selected";
						  }else
						  {
							return "";
						  }
				  }else if(attribute.equalsIgnoreCase("className"))
				  {
					if (um.getClassID() == null || um.getClassID().intValue() <= 0)
						return "";

				  try
				  {
				  EClassSLHome home=EJBUtil.getEClassSLHome();
				  EClassSL remote=home.create();
				  EClassModel em=remote.getEClass(um.getClassID().toString());
				  return InterpretSQL.transformChinese(em.getName());
				  }catch(Exception e)
				  {
					System.out.println(e);
					return um.getClassID()+"";
				  }
				  }else
				  {
						  return "";
				  }
				}else
				{
				  return "";
				}
				return "";
	}

}