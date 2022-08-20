package com.dc.eschool.controller.taglib.list;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.dc.eschool.controller.web.*;
import com.dc.eschool.users.model.UsersModel;

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
 * UserDetailsTag
 * -----------------
 */

public class UserDetailsTag extends BodyTagSupport
{
    private UsersModel um = new UsersModel();
    private String userId;

    public int doStartTag() throws JspTagException
    {
        UsersSearchWebImpl uswi = (UsersSearchWebImpl)
            pageContext.getServletContext().getAttribute(WebKeys.UsersSearchKey);
        ESchoolWebImpl eswi = (ESchoolWebImpl)
            pageContext.getSession().getAttribute(WebKeys.ESchoolWebKey);

        if(uswi == null)
            Debug.println("The UsersSearchImpl is null");

        userId=pageContext.getRequest().getParameter("userId");
        String action=pageContext.getRequest().getParameter("action");
        if(action!=null)
        {
          if(!action.equals("createUser"))
          {
            if (userId==null) userId=eswi.getUserID().toString();
          }
        }else
        {
          if (userId==null) userId=eswi.getUserID().toString();
        }

        try
        {
            um=uswi.getUser(userId);
        } catch (Exception e)
        {
            throw new JspTagException("Exception while getting User " + e);
        }
        return(EVAL_BODY_TAG);
    }

    public int doEndTag()
    {
        try {
            BodyContent body = getBodyContent();
            if (body != null)
            {
                JspWriter out = body.getEnclosingWriter();
                out.print(body.getString());
            }
        } catch(IOException ioe)
        {
            Debug.println("Error handling items tag: " + ioe);
        }
        return(SKIP_BODY);
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Object getCurrentUser()
    {
        return (Object)um;
    }
}
