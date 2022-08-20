package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.subject.model.SubjectModel;
import com.dc.eschool.schoolresource.model.SchoolResourceModel;
import com.dc.eschool.util.InterpretSQL;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SubjectAttributeTag extends ItemAttributeTag
{
	protected String createText()
	{
		SubjectModel em = (SubjectModel)item;
		if(attribute == null) return em.getName();

				if(em.getSubjectID()!=null)
				{
				  if(attribute.equalsIgnoreCase("subjectID"))
				  {
						  return em.getSubjectID()+"";
				  }else if(attribute.equalsIgnoreCase("name"))
				  {
						  return InterpretSQL.transformChinese(em.getName());
				  }else if(attribute.equalsIgnoreCase("selectedcourseid"))
				  {
						  SchoolResourceDetailsTag school = (SchoolResourceDetailsTag)
										  findAncestorWithClass(this, SchoolResourceDetailsTag.class);

						  if (school == null)
							 return "";

						  SchoolResourceModel sm = (SchoolResourceModel)school.getCurrentSchoolResource();
						  if (sm == null)
							return "";

						  if(sm.getSubjectID()!=null && sm.getSubjectID().equals(em.getSubjectID()))
							 return " selected";
						  else
							return "";
				  }
				}else
				{
				  return "";
				}
		return "";
	}
}