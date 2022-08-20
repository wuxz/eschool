package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.controller.web.SubjectSearchWebImpl;
import com.dc.eschool.subject.model.SubjectModel;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.JSPUtil;
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
 * SubjectDetailsTag
 * -----------------
 */

public class SubjectDetailsTag extends BodyTagSupport
{
    private SubjectModel sm = new SubjectModel();
    private String subjectId;

    public int doStartTag() throws JspTagException
    {
        SubjectSearchWebImpl eswi = (SubjectSearchWebImpl)
                                  pageContext.getServletContext().getAttribute(WebKeys.SubjectSearchKey);

        if(eswi == null)  Debug.println("The SubjectSearchWebImpl is null");

        subjectId=pageContext.getRequest().getParameter("subjectId");
        try
        {
            sm=eswi.getSubject(subjectId);
        } catch (Exception e)
        {
            throw new JspTagException("Exception while getting Subject " + e);
        }
        return(EVAL_BODY_TAG);
    }

    public int doEndTag()
    {
        try
        {
            BodyContent body = getBodyContent();
            if (body != null)
            {
              JspWriter out = body.getEnclosingWriter();
              out.print(body.getString());
            }
        } catch(IOException ioe)
        {
            Debug.println("Error handling itsms tag: " + ioe);
        }
            return(SKIP_BODY);
    }

    public void setSubjectId(String subjectId)
    {
        this.subjectId = subjectId;
    }

    public Object getCurrentSubject()
    {
        return (Object)sm;
    }

}
