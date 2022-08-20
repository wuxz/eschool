package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.course.mgrbean.*;
import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.coursestudent.model.CourseStudentModel;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
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

public class CSAttributeTag extends ItemAttributeTag
{
    protected String createText()
    {
        CourseStudentModel csm = (CourseStudentModel)item;
        if(attribute == null) return "";

        if(csm.getCourseStudentID()!=null)
        {
          if(attribute.equalsIgnoreCase("courseStudentID"))
          {
              return csm.getCourseStudentID()+"";
          }else if(attribute.equalsIgnoreCase("student"))
          {
              return csm.getStudent()+"";
          }else if(attribute.equalsIgnoreCase("studentName"))
          {
            try
            {
                UsersSLHome home=EJBUtil.getUsersSLHome();
                UsersSL remote=home.create();
                UsersModel um=remote.getUser(csm.getStudent().toString());
                return InterpretSQL.transformChinese(um.getName());
            }catch(Exception e)
            {
              System.out.println(e);
              return csm.getStudent()+"";
            }
          }else if(attribute.equalsIgnoreCase("courseID"))
          {
              return csm.getCourseID()+"";
          }else if(attribute.equalsIgnoreCase("courseName"))
          {
                  try
                  {
                      CourseSLHome home=EJBUtil.getCourseSLHome();
                      CourseSL remote=home.create();
                      CourseModel cm=remote.getCourse(csm.getCourseID().toString());
                      return InterpretSQL.transformChinese(cm.getCourseName());
                  }catch(Exception e)
                  {
                    System.out.println(e);
                    return csm.getCourseID()+"";
                  }
          }
        }else
        {
            return "";
        }
        return "";
    }
}