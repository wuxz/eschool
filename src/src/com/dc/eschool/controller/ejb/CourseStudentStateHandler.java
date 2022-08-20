package com.dc.eschool.controller.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.dc.eschool.controller.event.*;
import com.dc.eschool.controller.exception.*;
import com.dc.eschool.controller.web.StateMachine;

import com.dc.eschool.coursestudent.mgrbean.CourseStudentSL;
import com.dc.eschool.coursestudent.mgrbean.CourseStudentSLHome;
import com.dc.eschool.coursestudent.model.CourseStudentModel;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class CourseStudentStateHandler extends StateHandlerSupport
{
    public void perform(MainEvent event) throws ControllerException
    {
        CourseStudentEvent cse = (CourseStudentEvent)event;
        switch(cse.getActionType())
        {
            case CourseStudentEvent.CREATE_COURSESTUDENT:
            {
                Debug.println("State Handled COURSESTUDENT_CREATE_EVENT");

                try
                {
                    CourseStudentSL coursestudent=getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		    CourseStudentModel csm=cse.getCsm();
		    csm.setCreateBy(userID);
		    csm.setLastModifyBy(userID);
                    coursestudent.insertCourseStudent(csm);
                }catch(com.dc.eschool.coursestudent.exceptions.CSCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating coursestudent: " + re);
                }
            }break;

            case CourseStudentEvent.DELETE_COURSESTUDENT:
            {
                Debug.println("State Handled COURSESTUDENT_DELETE_EVENT");

                try
                {
                    CourseStudentSL coursestudent=getManager();
                    coursestudent.deleteCourseStudent(cse.getCsm().getStudent(),cse.getCsm().getCourseID()) ;
                }catch (Exception re)
                {
                    System.out.println("Irrecoverable error while creating coursestudent: " + re);
                }
            }break;
        }
    }

    private CourseStudentSL getManager()
    {
        CourseStudentSL coursestudent=null;
        CourseStudentSLHome home =null;

        try
        {
            home = EJBUtil.getCourseStudentSLHome();
            coursestudent = home.create();
        } catch (javax.naming.NamingException ce)
        {
            throw new EJBException("Irrecoverable error creating CourseStudent: " + ce);
        } catch (java.rmi.RemoteException re)
        {
            throw new EJBException("Irrecoverable error while creating coursestudent: " + re);
        } catch (CreateException ce)
        {
            System.out.println("Unable to create a new account for " + ce + " at this time");
        }
        return coursestudent;
    }
}