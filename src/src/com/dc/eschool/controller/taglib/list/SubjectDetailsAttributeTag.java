package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.subject.model.SubjectModel;

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
 * SubjectDetailAttributeTag
 * -------------------------
 */

public class SubjectDetailsAttributeTag extends TagSupport
{
    protected SubjectModel sm = new SubjectModel();
    protected String attribute = null;

    public int doStartTag() throws JspTagException
    {
        SubjectDetailsTag subjectDetail = (SubjectDetailsTag)
                        findAncestorWithClass(this, SubjectDetailsTag.class);
        if (subjectDetail == null)
        {
            throw new JspTagException("subjectDetailAttrTag: subjectDetailTag tag not" +
                        "found");
        }
        sm = (SubjectModel)subjectDetail.getCurrentSubject();
        try
        {
            JspWriter out = pageContext.getOut();
            out.print(sendDetails());
        } catch(IOException ioe)
        {
            Debug.println("SubjectDetailAttributeTag: " +
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
        if( sm.getSubjectID()!= null)
        {
            if(attribute.equalsIgnoreCase("Name"))
            {
                return InterpretSQL.transformChinese(sm.getName());
            }else if(attribute.equalsIgnoreCase("title"))
            {
                return(InterpretSQL.transformChinese("±à¼­"));
            }else if(attribute.equalsIgnoreCase("button"))
            {
                return("save");
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
                return "";
            }
        }
    }
}
