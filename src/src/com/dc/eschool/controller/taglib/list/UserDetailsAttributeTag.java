package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.users.model.UsersModel;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.WebKeys;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */


/*
 * UserDetailAttributeTag
 * -------------------------
 */

public class UserDetailsAttributeTag extends TagSupport
{
	protected UsersModel um = new UsersModel();
	protected String attribute = null;

	public int doStartTag() throws JspTagException
	{
		UserDetailsTag userDetail = (UserDetailsTag)
						findAncestorWithClass(this, UserDetailsTag.class);
		if (userDetail == null)
		{
			throw new JspTagException("userDetailAttrTag: userDetailTag tag not" +
						"found");
		}
		um = (UsersModel)userDetail.getCurrentUser();
		try
		{
			JspWriter out = pageContext.getOut();
			out.print(sendDetails());
		} catch(IOException ioe)
		{
			Debug.println("UserDetailAttributeTag: " + "Error printing attribute: " + ioe);
		}
		return(SKIP_BODY);
	}

	public void setAttribute(String attribute)
	{
		this.attribute = attribute;
	}

	public int doEndTag() {
		return(EVAL_PAGE);
	}

	protected String sendDetails()
	{
		if(um.getUserID()!=null)
		{
			if(attribute.equalsIgnoreCase("LoginName"))
			{
				return InterpretSQL.transformChinese(um.getLoginName());
			}else if(attribute.equalsIgnoreCase("userId"))
			{
				return um.getUserID()+"";
			}else if(attribute.equalsIgnoreCase("Name"))
			{
				return InterpretSQL.transformChinese(um.getName());
			}else if(attribute.equalsIgnoreCase("Gender"))
			{
				return InterpretSQL.transformChinese(um.getGender());
			}else if(attribute.equalsIgnoreCase("maleSelected"))
			{
                            if(um.getGender().equals("ÄÐ"))
                            {
				return " selected ";
                            }else
                            {
                                return "";
                            }
			}else if(attribute.equalsIgnoreCase("femaleSelected"))
			{
                            if(um.getGender().equals("Å®"))
                            {
				return " selected ";
                            }else
                            {
                                return "";
                            }
			}else if(attribute.equalsIgnoreCase("Birthday"))
			{
				return InterpretSQL.transformChinese(um.getBirthday());
			}else if(attribute.equalsIgnoreCase("Tel"))
			{
				return InterpretSQL.transformChinese(um.getTel());
			}else if(attribute.equalsIgnoreCase("Email"))
			{
				return InterpretSQL.transformChinese(um.getEmail());
			}else if(attribute.equalsIgnoreCase("Address"))
			{
				return InterpretSQL.transformChinese(um.getAddress());
			}else if(attribute.equalsIgnoreCase("ClassID"))
			{
				return um.getClassID()+"";
			}else if(attribute.equalsIgnoreCase("UserType"))
			{
				return InterpretSQL.transformChinese(um.getUserType());
			}else if(attribute.equalsIgnoreCase("State"))
			{
				return InterpretSQL.transformChinese(um.getState());
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

				if ("usertype".equalsIgnoreCase(strField))
				   return strValue.equalsIgnoreCase(um.getUserType() + "") ? " selected" : "";
				else if ("state".equalsIgnoreCase(strField))
				   return strValue.equalsIgnoreCase(um.getState() + "") ? " selected" : "";
				else
					return "";
		   }else{
				return "";
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
				return "";
			}
		}
	}
}
