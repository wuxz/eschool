package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;

import com.dc.eschool.util.EJBUtil;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.PropertiesManager;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ContentAttributeTag extends ItemAttributeTag
{

  protected String createText()
  {
	ContentModel cm = (ContentModel)item;
	PropertiesManager properMgr=new PropertiesManager();
	String DOC_PATH=properMgr.getUploadPath();
	if(attribute == null) return "";

		if(cm.getContentID()!=null)
		{
		  if(attribute.equalsIgnoreCase("filesize"))
		  {
			return cm.getFileSize()+"";
		  }else if(attribute.equalsIgnoreCase("modifydate"))
		  {
			return InterpretSQL.transformChinese(cm.getLastModifyDate());
		  }else if(attribute.equalsIgnoreCase("projectContentID"))
		  {
			return cm.getProjectContentID()+"";
		  }else if(attribute.equalsIgnoreCase("id"))
		  {
			return cm.getContentID()+"";
		  }else if(attribute.equalsIgnoreCase("name"))
		  {
			return InterpretSQL.transformChinese(cm.getName());
		  }else if(attribute.equalsIgnoreCase("docurl"))
		  {
			return InterpretSQL.transformChinese(cm.getDocURL());
		  }else if(attribute.equalsIgnoreCase("info"))
		  {
			return InterpretSQL.transformChinese(cm.getInfo());
		  }else if(attribute.equalsIgnoreCase("state"))
		  {
			return InterpretSQL.transformChinese(cm.getState());
		  }else if(attribute.equalsIgnoreCase("type"))
		  {
			return InterpretSQL.transformChinese(cm.getType());
		  }else if(attribute.equalsIgnoreCase("hasansweritem"))
		  {
			return InterpretSQL.transformChinese(cm.getHasAnswerItem());
		  }else if(attribute.equalsIgnoreCase("createby"))
		  {
			return cm.getCreateBy()+"";
		  }else if(attribute.equalsIgnoreCase("creatorname"))
		  {
			 try
			 {
						  UsersSLHome home=EJBUtil.getUsersSLHome();
						  UsersSL remote=home.create();
						  UsersModel um=remote.getUser(cm.getCreateBy().toString());
						  return InterpretSQL.transformChinese(um.getName());
				  }catch(Exception e)
				  {
					System.out.println(e);
					return cm.getCreateBy()+"";
				  }
		  }else if(attribute.equalsIgnoreCase("createdate"))
		  {
				  return InterpretSQL.transformChinese(cm.getCreateDate());
		  }else if(attribute.equalsIgnoreCase("hashcode"))
		  {
				  return cm.getDocURL().hashCode() + "";
		  }else if(attribute.equalsIgnoreCase("contentid"))
		  {
			return cm.getContentID()+"";
		  }else if(attribute.equalsIgnoreCase("projectid"))
		  {
			return cm.getProjectID()+"";
		  }else if(attribute.equalsIgnoreCase("forpasssavename"))
		  {
			return cm.getDocURL();
		  }else if(attribute.equalsIgnoreCase("passedsavename"))
		  {
			return cm.getDocURL();
		  }
		  else if (attribute.startsWith("checked_"))
		  {
				  int nFieldPos = attribute.indexOf("_") + 1;
				  if (nFieldPos == -1)
					 return "";

				  int nValuePos = attribute.indexOf("_", nFieldPos);
				  if (nValuePos == -1 || nValuePos == attribute.length() - 1)
					 return "";

				  String strField = attribute.substring(nFieldPos, nValuePos);
				  String strValue = attribute.substring(nValuePos + 1);

				  if ("state".equalsIgnoreCase(strField))
					 return strValue.equalsIgnoreCase(cm.getState()) ? "checked" : "";
		  }
		}else
		{
			return "";
		}

	return "";
  }
}