package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.CourseStudentEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.coursestudent.model.CourseStudentModel;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;

/**
 * CourseStudent Handler
 *
*/
public class CourseStudentHandler extends RequestHandlerSupport
{

    public MainEvent processRequest(HttpServletRequest request) throws ControllerException
    {
        Debug.println("Started creation of an CourseStudent Event");

        String action = request.getParameter("action");
        Debug.println("CourseStudentHandler (web): action=" + action);
        if (action == null)
        {
            return null;
        } else if (action.equals("createcoursestudent"))
        {
            return createNewCourseStudentEvent(request);
        } else if (action.equals("deletecoursestudent"))
        {
            return createDeleteCourseStudentEvent(request);
        }
        return null;
    }

    private MainEvent createNewCourseStudentEvent(HttpServletRequest request)
    {
        CourseStudentEvent event = new CourseStudentEvent();
	CourseStudentModel csm=new CourseStudentModel();

        Integer student=new Integer(request.getParameter("student").trim());
        Integer courseID=new Integer(request.getParameter("courseId").trim());

	csm.setStudent(student);
	csm.setCourseID(courseID);

        event.setActionType(event.CREATE_COURSESTUDENT);
	event.setCsm(csm);
        return event;
    }

    private MainEvent createDeleteCourseStudentEvent(HttpServletRequest request)
    {
        CourseStudentEvent event = new CourseStudentEvent();
	CourseStudentModel csm=new CourseStudentModel();

        Integer student=new Integer(request.getParameter("student").trim());
        Integer courseID=new Integer(request.getParameter("courseId").trim());

        csm.setStudent(student);
	csm.setCourseID(courseID);

        event.setActionType(event.DELETE_COURSESTUDENT);
	event.setCsm(csm);
        return event;
    }
}

