package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.ProjectEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.MissingFormDataException;
import com.dc.eschool.project.model.ProjectModel;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;

/**
 * Project Handler
 *
*/
public class ProjectHandler extends RequestHandlerSupport
{
    Vector userVector=new Vector();

    public MainEvent processRequest(HttpServletRequest request) throws ControllerException
    {
        Debug.println("Started creation of an Project Event");

        String action = request.getParameter("action");
        Debug.println("ProjectHandler (web): action=" + action);
        if (action == null)
        {
            return null;
        } else if (action.equals("createproject"))
        {
            return createNewProjectEvent(request);
        } else if (action.equals("updateproject"))
        {
            return createUpdateProjectEvent(request);
        } else if (action.equals("deleteproject"))
        {
            return createDeleteProjectEvent(request);
        }
        return null;
    }

    private MainEvent createNewProjectEvent(HttpServletRequest request)
    {
        ProjectEvent event = new ProjectEvent();
        ProjectModel pm= new ProjectModel();
	ArrayList missingFields = null;

        String name=request.getParameter("name").trim();
	if (name.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("名称"));
        }

	String courseStr=request.getParameter("courseID");

	if (courseStr.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("课程名称"));
        }

        String info=request.getParameter("info").trim();
	if (info.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("说明"));
        }

        String state=request.getParameter("state");
        if (state==null) state="不公布";
        state=state.trim();
	if (state.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("状态"));
        }

        String type=request.getParameter("type").trim();
	if (type.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("类型"));
        }

        String publishResult=request.getParameter("publishResult");
        if(publishResult==null) publishResult="不公布";
        publishResult=publishResult.trim();
	if (publishResult.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("成绩状态"));
        }

        String startDate=request.getParameter("startDate").trim();
	if (startDate.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("开始时间"));
        }

        String endDate=request.getParameter("endDate").trim();
	if (endDate.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("结束时间"));
        }

	if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException(InterpretSQL.transformChinese("需确认的数据"), missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

	Integer courseID= new Integer(courseStr);
        if (!publishResult.equals("不公布")) publishResult=InterpretSQL.transform(publishResult);
        if (!state.equals("不公布")) state=InterpretSQL.transform(state);
        pm.setName(InterpretSQL.transform(name));
        pm.setCourseID(courseID);
        pm.setInfo(InterpretSQL.transform(info));
        pm.setState(state);
        pm.setType(InterpretSQL.transform(type));
        pm.setPublishResult(publishResult);
        pm.setStartDate(InterpretSQL.transform(startDate));
        pm.setEndDate(InterpretSQL.transform(endDate));

        event.setActionType(event.CREATE_PROJECT);
        event.setPm(pm);
        return event;
    }

    private MainEvent createUpdateProjectEvent(HttpServletRequest request)
    {
        ProjectEvent event = new ProjectEvent();
        ProjectModel pm= new ProjectModel();

	ArrayList missingFields = null;

        String name=request.getParameter("name").trim();
	if (name.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("名称"));
        }

	String courseStr=request.getParameter("courseID");

	if (courseStr.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("课程名称"));
        }

        String info=request.getParameter("info").trim();
	if (info.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("说明"));
        }

        String state=request.getParameter("state");
        if (state==null) state="不公布";
        state=state.trim();
	if (state.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("状态"));
        }

        String type=request.getParameter("type").trim();
	if (type.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("类型"));
        }

        String publishResult=request.getParameter("publishResult");
        if(publishResult==null) publishResult="不公布";
        publishResult=publishResult.trim();
	if (publishResult.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("成绩状态"));
        }

        String startDate=request.getParameter("startDate").trim();
	if (startDate.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("开始时间"));
        }

        String endDate=request.getParameter("endDate").trim();
	if (endDate.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("结束时间"));
        }

	if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException("需确认的数据", missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

	Integer courseID= new Integer(courseStr);

        Integer projectID=new Integer(request.getParameter("projectId"));

        if (!publishResult.equals("不公布")) publishResult=InterpretSQL.transform(publishResult);
        if (!state.equals("不公布")) state=InterpretSQL.transform(state);
        pm.setProjectID(projectID);
        pm.setName(InterpretSQL.transform(name));
        pm.setCourseID(courseID);
        pm.setInfo(InterpretSQL.transform(info));
        pm.setState(state);
        pm.setType(InterpretSQL.transform(type));
        pm.setPublishResult(publishResult);
        pm.setStartDate(InterpretSQL.transform(startDate));
        pm.setEndDate(InterpretSQL.transform(endDate));

        event.setPm(pm);
        event.setActionType(event.UPDATE_PROJECT);
        return event;
    }

    private MainEvent createDeleteProjectEvent(HttpServletRequest request)
    {
        ProjectEvent event = new ProjectEvent();
        ProjectModel pm= new ProjectModel();

        Integer projectID=new Integer(request.getParameter("projectId").trim());
        pm.setProjectID(projectID);
        event.setActionType(event.DELETE_PROJECT);
        event.setPm(pm);
        return event;
    }
}

