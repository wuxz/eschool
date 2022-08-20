package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.course.dao.CourseMgrDAO;
import com.dc.eschool.course.mgrbean.*;
import com.dc.eschool.course.model.CourseModel;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.course.exceptions.*;
import com.dc.eschool.course.ejb.*;
import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class CourseSearchWebImpl implements Serializable
{
    private CourseSLHome home = null;

    public CourseSearchWebImpl()
    {
    }

    public ListChunk searchCourse(String clause, int startindex, int count, String value)
    {
        ListChunk lc = null;
        try
        {
            lc = getManager().searchCourse(clause, startindex, count,value);
        }
        catch(Exception e)
        {
            String str = e.getMessage();
            Debug.println(str);
        }
        return lc;
    }

    public CourseModel getCourse(String courseId)
    {
        CourseModel um=new CourseModel();
        try
        {
            um=getManager().getCourse(courseId);
        } catch(Exception se)
        {
            System.out.println("getCourse():" + se.getMessage());
        }
        return um;
    }

    private CourseSL getManager() throws ControllerException
    {
        CourseSL remote = null;
        try
        {
            if(home == null)
              home = EJBUtil.getCourseSLHome();
              remote = home.create();
        }
        catch(javax.naming.NamingException ne)
        {
            String str = "NamingException while get manager: "+ne.getMessage();
            Debug.println(str);
            throw new ControllerException(str);
        }
        catch(javax.ejb.CreateException ce)
        {
            String str = "CreateException while get manager: "+ce.getMessage();
            Debug.println(str);
            throw new ControllerException(str);
        }
        catch(java.rmi.RemoteException re)
        {
            String str = "RemoteException while get manager: "+re.getMessage();
            Debug.println(str);
            throw new ControllerException(str);
        }
        catch(Exception e)
        {
            String str = "unknown Exception while get manager: "+e.getMessage();
            Debug.println(str);
            throw new ControllerException(str);
        }
        return remote;
    }
}