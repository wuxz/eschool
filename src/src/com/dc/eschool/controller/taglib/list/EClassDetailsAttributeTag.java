package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.eclass.model.EClassModel;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.InterpretSQL;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */


/*
 * EClassDetailAttributeTag
 * -------------------------
 */

public class EClassDetailsAttributeTag extends TagSupport
{
    protected EClassModel em = new EClassModel();
    protected String attribute = null;

    public int doStartTag() throws JspTagException
    {
        EClassDetailsTag eclassDetail = (EClassDetailsTag)
                        findAncestorWithClass(this, EClassDetailsTag.class);
        if (eclassDetail == null)
        {
            throw new JspTagException("eclassDetailAttrTag: eclassDetailTag tag not" +
                        "found");
        }
        em = (EClassModel)eclassDetail.getCurrentEClass();

        try
        {
            JspWriter out = pageContext.getOut();
            out.print(sendDetails());
        } catch(IOException ioe)
        {
            Debug.println("EClassDetailAttributeTag: " +
                                                        "Error printing attribute: " + ioe);
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
	if(em.getClassID()!=null)
        {
            if(attribute.equalsIgnoreCase("Name"))
            {
                return InterpretSQL.transformChinese(em.getName());
            }else if(attribute.equalsIgnoreCase("Info"))
            {
                return InterpretSQL.transformChinese(em.getInfo());
            }else if(attribute.equalsIgnoreCase("title"))
            {
                return(InterpretSQL.transformChinese("±à¼­"));
            }else if(attribute.equalsIgnoreCase("button"))
            {
                return("save");
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
                return("");
            }
        }
    }
}
