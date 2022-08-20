package com.dc.eschool.controller.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.dc.eschool.controller.web.StateMachine;
import com.dc.eschool.controller.event.*;
import com.dc.eschool.controller.exception.*;

import com.dc.eschool.course.mgrbean.CourseSL;
import com.dc.eschool.course.mgrbean.CourseSLHome;
import com.dc.eschool.course.model.CourseModel;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class CourseStateHandler extends StateHandlerSupport
{
    public void perform(MainEvent event) throws ControllerException
    {
        CourseEvent ce = (CourseEvent)event;
        switch(ce.getActionType())
        {
            case CourseEvent.CREATE_COURSE:
            {
              Debug.println("State Handled COURSE_CREATE_EVENT");
                try
                {
                    CourseSL course = getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		    CourseModel cm=ce.getCm();
		    cm.setCreateBy(userID);
		    cm.setLastModifyBy(userID);
                    course.insertCourse(cm);
                }catch(com.dc.eschool.course.exceptions.CourseCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating course: " + re);
                }
            }break;
            case CourseEvent.UPDATE_COURSE:
            {
                Debug.println("State Handled COURSE_UPDATE_EVENT");
                try
                {
                    CourseSL course = getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		    CourseModel cm=ce.getCm();
		    cm.setLastModifyBy(userID);
                    course.updateCourse(cm);
                }catch(com.dc.eschool.course.exceptions.CourseCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating course: " + re);
                }
            }break;
            case CourseEvent.DELETE_COURSE:
            {
                Debug.println("State Handled COURSE_UPDATE_EVENT");
                try
                {
                    CourseSL course=getManager();
		    CourseModel cm=ce.getCm();
                    course.deleteCourse(cm.getCourseID()) ;
                }catch (Exception re)
                {
                    System.out.println("Irrecoverable error while creating course: " + re);
                }
            }break;
        }
    }

    private CourseSL getManager()
    {
        CourseSL course=null;
        CourseSLHome home =null;

        try
        {
            home = EJBUtil.getCourseSLHome();
            course = home.create();
        } catch (javax.naming.NamingException ce)
        {
            throw new EJBException("Irrecoverable error creating Course: " + ce);
        } catch (java.rmi.RemoteException re)
        {
            throw new EJBException("Irrecoverable error while creating course: " + re);
        } catch (CreateException ce)
        {
            System.out.println("Unable to create a new account for " + ce + " at this time");
        }
        return course;
    }
}