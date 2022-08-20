package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.homeworkcontent.model.HWContentModel;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.util.EJBUtil;
import com.dc.eschool.util.InterpretSQL;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class HomeWorkContentAttributeTag extends ItemAttributeTag
{
	protected String createText()
	{
		HWContentModel em = (HWContentModel)item;
		if(attribute == null) return "";
		if(em.getHomeWorkContentID()!=null)
		{
		  if(attribute.equalsIgnoreCase("homeWorkContentID"))
		  {
			  return em.getHomeWorkContentID()+"";
		  }else if(attribute.equalsIgnoreCase("homeWorkID"))
		  {
			  return em.getHomeWorkID()+"";
		  }else if(attribute.equalsIgnoreCase("student"))
		  {
			  return em.getStudent()+"";
		  }else if(attribute.equalsIgnoreCase("studentName"))
		  {
				  try
				  {
					  UsersSLHome home=EJBUtil.getUsersSLHome();
					  UsersSL remote=home.create();
					  UsersModel um=remote.getUser(em.getStudent().toString());
					  return InterpretSQL.transformChinese(um.getName());
				  }catch(Exception e)
				  {
					System.out.println(e);
					return em.getStudent()+"";
				  }
		  }else if(attribute.equalsIgnoreCase("docURL"))
		  {
			  return InterpretSQL.transformChinese(em.getDocURL());
		  }else if(attribute.equalsIgnoreCase("hashcode"))
		  {
				  return em.getDocURL().hashCode() + "";
		  }else if(attribute.equalsIgnoreCase("savename"))
		  {
			return em.getDocURL();
		  }else if(attribute.equalsIgnoreCase("state"))
		  {
			  return InterpretSQL.transformChinese(em.getState());
		  }else if(attribute.equalsIgnoreCase("submiteDate"))
		  {
			  return InterpretSQL.transformChinese(em.getSubmitDate());
		  }else if(attribute.equalsIgnoreCase("submiteTime"))
		  {
			  return InterpretSQL.transformChinese(em.getSubmitTime());
		  }else if(attribute.equalsIgnoreCase("createDate"))
		  {
			  return InterpretSQL.transformChinese(em.getCreateDate());
		  }else if(attribute.equalsIgnoreCase("passDate"))
		  {
			  return InterpretSQL.transformChinese(em.getPassDate());
		  }else if(attribute.equalsIgnoreCase("passTime"))
		  {
			  return InterpretSQL.transformChinese(em.getPassTime());
		  }else if(attribute.equalsIgnoreCase("size"))
		  {
			  return em.getSize()+"";
		  }
		}else
		{
		  return "";
		}

		return "";
	}
}