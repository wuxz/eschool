package com.dc.eschool.controller.taglib.list;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;
import java.util.Locale;

//import com.sun.j2ee.blueprints.shoppingcart.catalog.model.Item;
import com.dc.eschool.schoolresource.model.SchoolResourceModel;
import com.dc.eschool.controller.web.SchoolResourceSearchWebImpl;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;

import com.dc.eschool.util.Debug;
/**
 * Title:Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:DC
 * @author wangshui
 * @version 1.0
 */


/*
 * SchoolResourceDetailsTag
 * -----------------
 */

  public class SchoolResourceDetailsTag extends BodyTagSupport {
		private SchoolResourceModel sm = null;
		private String schoolresourceId;


		public int doStartTag() throws JspTagException {

				SchoolResourceSearchWebImpl sswi = (SchoolResourceSearchWebImpl)
								  pageContext.getServletContext().getAttribute(WebKeys.SchoolResourceSearchKey);

				if(sswi == null)
				  Debug.println("ListTag_findCollection ...#2");

				schoolresourceId=pageContext.getRequest().getParameter("schoolResourceID");
				if (schoolresourceId == null || schoolresourceId.equals("") || schoolresourceId.equals("null"))
				{
					Debug.println("no schoolresourceid, you must want to create a new record.");
					return EVAL_BODY_TAG;
				}

				try {
						sm=sswi.getSchoolResource(schoolresourceId);

				} catch (Exception e) {
						throw new JspTagException("Exception while getting User " + e);
				}

				return(EVAL_BODY_TAG);
		}

		public int doEndTag() {
				try {
						BodyContent body = getBodyContent();
						if (body != null) {
								JspWriter out = body.getEnclosingWriter();
								out.print(body.getString());
						}
				} catch(IOException ioe) {
						Debug.println("Error handling items tag: " + ioe);
				}
				return(SKIP_BODY);
		}
  public void setSchoolResourceID(String schoolresourceId)
  {
	this.schoolresourceId = schoolresourceId;
  }

  public Object getCurrentSchoolResource()
  {
	return (Object)sm;
  }

}
