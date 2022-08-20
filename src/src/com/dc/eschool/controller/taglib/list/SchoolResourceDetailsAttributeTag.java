package com.dc.eschool.controller.taglib.list;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;

import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.schoolresource.model.SchoolResourceModel;
import com.dc.eschool.util.WebKeys;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.InterpretSQL;
/**
 * stitle:Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:DC
 * @author wangshui
 * @version 1.0
 */


/*
 * SchoolResourceDetailAttributeTag
 * -------------------------
 */

public class SchoolResourceDetailsAttributeTag extends TagSupport
{
	protected SchoolResourceModel sm = null;
	protected String attribute = null;

	public int doStartTag() throws JspTagException
	{
		SchoolResourceDetailsTag schoolresourceDetail = (SchoolResourceDetailsTag)
						findAncestorWithClass(this, SchoolResourceDetailsTag.class);

		if (schoolresourceDetail == null)
		{
			throw new JspTagException("schoolresourceDetailAttrTag: schoolresourceDetailTag tag not" +
						"found");
		}

		sm = (SchoolResourceModel)schoolresourceDetail.getCurrentSchoolResource();

		try
		{
			JspWriter out = pageContext.getOut();
			out.print(sendDetails());
		} catch(IOException ioe)
		{
			Debug.println("SchoolResourceDetailAttributeTag: " + "Error printing attribute: " + ioe);
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
		try
		{
	  if(sm!=null)
	  {
		if(attribute.equalsIgnoreCase("name"))
		{
		  return InterpretSQL.transformChinese(sm.getName());
		}
		else if(attribute.equalsIgnoreCase("docURL"))
		{
		  return InterpretSQL.transformChinese(sm.getDocURL());
		}
		else if(attribute.equalsIgnoreCase("subjectID"))
		{
			return (sm.getSubjectID()+"");
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
		}else if(attribute.equalsIgnoreCase("endDate"))
		{
		  return InterpretSQL.transformChinese(sm.getEndDate());
		}
		else if(attribute.equalsIgnoreCase("allow"))
		{
		  return InterpretSQL.transformChinese(sm.getAllow());
		}
		else if(attribute.equalsIgnoreCase("Explain"))
		{
		  return InterpretSQL.transformChinese(sm.getExplain());
		}
		else if(attribute.equalsIgnoreCase("stitle"))
		{
			return(InterpretSQL.transformChinese("±à¼­"));
		}
		else if(attribute.equalsIgnoreCase("explain"))
		{
			return(InterpretSQL.transformChinese(sm.getExplain()));
		}
		else if(attribute.equalsIgnoreCase("button"))
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

			if ("allow".equalsIgnoreCase(strField))
			   return strValue.equalsIgnoreCase(sm.getAllow()) ? " selected" : "";
			else
				return "";
		}
		else
		{
		  return"";
		}
	  }else
	  {
		if(attribute.equalsIgnoreCase("stitle"))
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
	catch (Exception e)
	{
		e.printStackTrace();
		return "";
	}
	}

}
