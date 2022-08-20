package com.dc.eschool.controller.taglib.list;

import java.util.*;

import com.dc.eschool.coursestudent.model.CourseStudentModel;
import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.project.model.ProjectModel;
import com.dc.eschool.controller.taglib.*;
import com.dc.eschool.controller.web.*;
import com.dc.eschool.util.*;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.controller.taglib.ProjectDetailsTag;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class CourseStudentAttributeTag extends ItemAttributeTag
{
	protected String createText()
	{
		if (attribute.equalsIgnoreCase("testapprove"))
		{
			String text = "";

			try
			{
				ProjectDetailsTag project = (ProjectDetailsTag)
									findAncestorWithClass(this, ProjectDetailsTag.class);
				if(project == null)
				{
				  Debug.println("can not find parent tag object.");

				  return "";
				}

				ContentModel cm = (ContentModel)item;

				ProjectModel pm = project.getPm();

				CSSearchWebImpl ecswi = (CSSearchWebImpl)
									  pageContext.getServletContext().getAttribute(WebKeys.CourseStudentSearchKey);

				if(ecswi == null)
				{
					Debug.println("ListTag_findCollection ...#2");
					return "";
				}

				ListChunk lc = null;
				Collection courseStudent = null;
				String clause =
					   " where " +
					   InterpretSQL.encodeEndSign("CourseID") +
					   " = " +
					   pm.getCourseID();

				lc = ecswi.searchCourseStudent(clause, 0, 0);

				if(lc == null)
				{
					Debug.println("no lc gotten");
					return null;
				}
				else
					Debug.println("lc is not null: " + lc.getTotalCount());

				courseStudent = lc.getCollection();


				Iterator it = courseStudent.iterator();

				while (it.hasNext())
				{
					UsersSLHome home = EJBUtil.getUsersSLHome();
					UsersSL remote = home.create();
					CourseStudentModel csm = (CourseStudentModel)it.next();
					text += "<tr><td bgcolor=\"#f0f0f0\" align=\"center\"><a href=\"";

					javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)pageContext.getRequest();
					text += request.getContextPath();
					text += "/main/judgepaper?action=" + request.getParameter("action");
					text += "&projectId=" + request.getParameter("projectId");
					text += "&type=" + request.getParameter("type");
					text += "&contentID=" + cm.getContentID();
					text += "&studentID=" + csm.getStudent();

					text += "\"> ";

					UsersModel um = remote.getUser(csm.getStudent().toString());
					text += InterpretSQL.transformChinese(um.getName());

					text += "</a></td></tr>\n";
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			return text;
		}
		else
		{
			return "";
		}
	}
}