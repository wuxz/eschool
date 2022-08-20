package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.schoolresource.model.SchoolResourceModel;
import com.dc.eschool.util.*;

import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.subject.mgrbean.*;
import com.dc.eschool.subject.model.*;

/**
 * Title: Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company: DC
 * @author wangshui
 * @version 1.0
 */

public class SchoolResourceAttributeTag extends ItemAttributeTag
{
  protected String createText()
  {

	SchoolResourceModel sm = (SchoolResourceModel)item;
	if(attribute == null) return "";

	if(sm.getSchoolResourceID()!= null)
	{
	  if(attribute.equalsIgnoreCase("schoolResourceID"))
	  {
		  return InterpretSQL.transformChinese(sm.getSchoolResourceID()+"");
	  }
	  else if(attribute.equalsIgnoreCase("name"))
	  {
		return InterpretSQL.transformChinese(sm.getName());
	  }
	  else if(attribute.equalsIgnoreCase("docURL"))
	  {
		return InterpretSQL.transformChinese(sm.getDocURL());
	  }
	  else if(attribute.equalsIgnoreCase("subjectID"))
	  {
		  return InterpretSQL.transformChinese(sm.getSubjectID()+"");
	  }
	  /*
	  else if(attribute.equalsIgnoreCase("subjectName"))
	  {
		return InterpretSQL.transformChinese(sm.getSubjectName());
	  }
	  */
	  else if(attribute.equalsIgnoreCase("startDate"))
	  {
		return InterpretSQL.transformChinese(sm.getStartDate());
	  }
	  else if(attribute.equalsIgnoreCase("createByName"))
	  {
		try
		{
			UsersSLHome home = EJBUtil.getUsersSLHome();
			UsersSL remote = home.create();
			UsersModel um = remote.getUser(sm.getCreateBy().toString());
			return InterpretSQL.transformChinese(um.getName());
		}
		catch (Exception e)
		{
			e.printStackTrace();

			return "";
		}
	  }
	  else if(attribute.equalsIgnoreCase("lastModifyDate"))
	  {
		return InterpretSQL.transformChinese(sm.getLastModifyDate());
	  }
	  else if(attribute.equalsIgnoreCase("subjectName"))
	  {
		try
		{
			SubjectSLHome home = EJBUtil.getSubjectSLHome();
			SubjectSL remote = home.create();
			SubjectModel sjm = remote.getSubject(sm.getSubjectID().toString());

			return InterpretSQL.transformChinese(sjm.getName());
		}
		catch (Exception e)
		{
			e.printStackTrace();

			return "";
		}
	  }
	  else if(attribute.equalsIgnoreCase("endDate"))
	  {
		return InterpretSQL.transformChinese(sm.getEndDate());
	  }
	}else {
	  return "";
	}
	return "";
  }
}