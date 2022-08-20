package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.answeritem.model.AnswerItemModel;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.WebKeys;

import com.dc.eschool.util.Debug;
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


/*
 * AnswerItemDetailAttributeTag
 * -------------------------
 */

public class AnswerItemDetailsAttributeTag extends TagSupport
{
	protected AnswerItemModel am = new AnswerItemModel();
	protected String attribute = null;

	public int doStartTag() throws JspTagException
	{
		AnswerItemDetailsTag answeritemDetail = (AnswerItemDetailsTag)
						findAncestorWithClass(this, AnswerItemDetailsTag.class);
		if (answeritemDetail == null)
		{
			throw new JspTagException("answeritemDetailAttrTag: answeritemDetailTag tag not" +
						"found");
		}

		am = (AnswerItemModel)answeritemDetail.getCurrentAnswerItem();


		try
		{
			JspWriter out = pageContext.getOut();
			out.print(sendDetails());
		} catch(IOException ioe)
		{
			Debug.println("AnswerItemDetailAttributeTag: " + "Error printing attribute: " + ioe);
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
		if(am.getAnswerItemID()!=null)
		{
			if(attribute.equalsIgnoreCase("itemNumber"))
			{
				return am.getItemNumber()+"";
			}else if(attribute.equalsIgnoreCase("id"))
			{
				return am.getAnswerItemID()+"";
			}else if(attribute.equalsIgnoreCase("answerNumber"))
			{
				return (InterpretSQL.transformChinese(am.getAnswerNumber()));
			}else if(attribute.equalsIgnoreCase("answer"))
			{
				return InterpretSQL.transformChinese(am.getAnswer());
			}else if(attribute.equalsIgnoreCase("memo"))
			{
				return InterpretSQL.transformChinese(am.getMemo());
			}else if(attribute.equalsIgnoreCase("title"))
			{
				return(InterpretSQL.transformChinese("±à¼­"));
			}else if(attribute.equalsIgnoreCase("button"))
			{
				return("save");
			}
			else if (attribute.startsWith("selected_"))
			{
				//Debug.println("attribute:" + attribute);
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

				if ("type".equalsIgnoreCase(strField))
				   return strValue.equalsIgnoreCase(am.getType()+ "") ? " selected" : "";
				else
					return "";
		   }else
			{
				return("");
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
				return("");
			}
		}
	}
}
