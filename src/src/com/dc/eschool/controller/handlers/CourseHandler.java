package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.CourseEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.MissingFormDataException;
import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.InterpretSQL;

/**
 * Course Handler
 *
*/
public class CourseHandler extends RequestHandlerSupport
{
    public MainEvent processRequest(HttpServletRequest request) throws ControllerException
    {
        Debug.println("Started creation of an Course Event");

        String action = request.getParameter("action");
        Debug.println("CourseHandler (web): action=" + action);
        if (action == null)
        {
            return null;
        } else if (action.equals("createcourse"))
        {
            return createNewCourseEvent(request);
        } else if (action.equals("updatecourse"))
        {
            return createUpdateCourseEvent(request);
        } else if (action.equals("deletecourse"))
        {
            return createDeleteCourseEvent(request);
        }
        return null;
    }

    private MainEvent createNewCourseEvent(HttpServletRequest request)
    {
        CourseEvent event = new CourseEvent();
	CourseModel cm= new CourseModel();
	ArrayList missingFields = null;

        String courseName=request.getParameter("courseName").trim();
	if (courseName.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("课程名称"));
        }

        Integer teacher=new Integer(request.getParameter("teacher"));
	if (teacher.intValue()<=0) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("教师"));
        }

        String startDate=request.getParameter("startDate");
	if (startDate.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("开始时间"));
        }

        String endDate=request.getParameter("endDate");
	if (endDate.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("结束时间"));
        }

        String info=request.getParameter("info");
	if (info.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("说明"));
        }

        String state=request.getParameter("state");
	if (state.equals(""))
	{
            if (missingFields == null)
	    {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("状态"));
        }

	if (missingFields != null)
	{
            MissingFormDataException ex = new MissingFormDataException(InterpretSQL.transformChinese("需确认的数据"), missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

	cm.setCourseName(InterpretSQL.transform(courseName));
	cm.setTeacher(teacher);
	cm.setStartDate(InterpretSQL.transform(startDate));
	cm.setEndDate(InterpretSQL.transform(endDate));
	cm.setInfo(InterpretSQL.transform(info));
	cm.setState(InterpretSQL.transform(state));

	event.setActionType(event.CREATE_COURSE);
        event.setCm(cm);
        return event;
    }

    private MainEvent createUpdateCourseEvent(HttpServletRequest request)
    {
        CourseEvent event = new CourseEvent();
	CourseModel cm= new CourseModel();
	ArrayList missingFields = null;

        String courseName=request.getParameter("courseName").trim();
	if (courseName.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("课程名称"));
        }

        Integer teacher=new Integer(request.getParameter("teacher"));
	if (teacher.intValue()<=0) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("教师"));
        }

        String startDate=request.getParameter("startDate");
	if (startDate.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("开始时间"));
        }

        String endDate=request.getParameter("endDate");
	if (endDate.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("结束时间"));
        }

        String info=request.getParameter("info");
	if (info.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("说明"));
        }

        String state=request.getParameter("state");
	if (state.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("状态"));
        }

	if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException("需确认的数据", missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

        Integer courseID=new Integer(request.getParameter("courseId").trim());

        cm.setCourseID(courseID);
	cm.setCourseName(InterpretSQL.transform(courseName));
	cm.setTeacher(teacher);
	cm.setStartDate(InterpretSQL.transform(startDate));
	cm.setEndDate(InterpretSQL.transform(endDate));
	cm.setInfo(InterpretSQL.transform(info));
	cm.setState(InterpretSQL.transform(state));

	event.setActionType(event.UPDATE_COURSE);
        event.setCm(cm);

        return event;
    }

    private MainEvent createDeleteCourseEvent(HttpServletRequest request)
    {

        CourseEvent event = new CourseEvent();
	CourseModel cm= new CourseModel();

        Integer courseID=new Integer(request.getParameter("courseId").trim());
	cm.setCourseID(courseID);
        event.setActionType(event.DELETE_COURSE);
        event.setCm(cm);
        return event;
    }
}

