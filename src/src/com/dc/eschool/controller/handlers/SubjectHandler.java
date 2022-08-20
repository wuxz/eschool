package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.SubjectEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.MissingFormDataException;
import com.dc.eschool.subject.model.SubjectModel;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.InterpretSQL;

/**
 * Subject Handler
 *
*/
public class SubjectHandler extends RequestHandlerSupport
{

    public MainEvent processRequest(HttpServletRequest request) throws ControllerException
    {
        Debug.println("Started creation of an Subject Event");

        String action = request.getParameter("action");
        Debug.println("SubjectHandler (web): action=" + action);
        if (action == null)
        {
            return null;
        } else if (action.equals("createsubject"))
        {
            return createNewSubjectEvent(request);
        } else if (action.equals("updatesubject"))
        {
            return createUpdateSubjectEvent(request);
        } else if (action.equals("deletesubject"))
        {
            return createDeleteSubjectEvent(request);
        }
        return null;
    }

    private MainEvent createNewSubjectEvent(HttpServletRequest request)
    {
        SubjectEvent event = new SubjectEvent();
	SubjectModel sm=new SubjectModel();
	ArrayList missingFields = null;

        String name=request.getParameter("name").trim();
	if (name.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("名称"));
        }

	if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException(InterpretSQL.transformChinese("需确认的数据"), missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

	sm.setName(InterpretSQL.transform(name));

	event.setActionType(event.CREATE_SUBJECT);
        event.setSm(sm);
        return event;
    }

    private MainEvent createUpdateSubjectEvent(HttpServletRequest request)
    {
        SubjectEvent event = new SubjectEvent();
	SubjectModel sm=new SubjectModel();
	ArrayList missingFields = null;

        String name=request.getParameter("name").trim();
	if (name.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("名称"));
        }

	if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException("需确认的用户数据", missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

        Integer subjectID=new Integer(request.getParameter("subjectId").trim());

        sm.setSubjectID(subjectID);
	sm.setName(InterpretSQL.transform(name));

	event.setActionType(event.UPDATE_SUBJECT);
        event.setSm(sm);

        return event;
    }

    private MainEvent createDeleteSubjectEvent(HttpServletRequest request)
    {
        SubjectEvent event = new SubjectEvent();
	SubjectModel sm=new SubjectModel();

        Integer subjectID=new Integer(request.getParameter("subjectId").trim());

	sm.setSubjectID(subjectID);

        event.setActionType(event.DELETE_SUBJECT);
        event.setSm(sm);

        return event;
    }
}

