package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.EClassEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.ModelManager;
import com.dc.eschool.controller.web.UsersWebImpl;
import com.dc.eschool.controller.web.MissingFormDataException;
import com.dc.eschool.eclass.model.EClassModel;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.InterpretSQL;

/**
 * EClass Handler
 *
*/
public class EClassHandler extends RequestHandlerSupport
{
    public MainEvent processRequest(HttpServletRequest request)
        throws ControllerException
    {
        Debug.println("Started creation of an EClass Event");


        String action = request.getParameter("action");
        Debug.println("EClassHandler (web): action=" + action);
        if (action == null)
	{
            return null;
        } else if (action.equals("createeclass"))
	{
            return createNewEClassEvent(request);
        } else if (action.equals("updateeclass"))
	{
            return createUpdateEClassEvent(request);
        } else if (action.equals("deleteeclass"))
	{
            return createDeleteEClassEvent(request);
        }
        return null;
    }

    private MainEvent createNewEClassEvent(HttpServletRequest request)
    {
        EClassEvent event = new EClassEvent();
	EClassModel ecm=new EClassModel();
	ArrayList missingFields = null;

        String name=request.getParameter("name").trim();
	if (name.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("名称"));
        }

        String info=request.getParameter("info").trim();
	if (info.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("说明"));
        }

	if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException(InterpretSQL.transformChinese("需确认的用户数据"), missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

	ecm.setName(InterpretSQL.transform(name));
	ecm.setInfo(InterpretSQL.transform(info));

        event.setActionType(event.CREATE_ECLASS);
	event.setEcm(ecm);
        return event;
    }

    private MainEvent createUpdateEClassEvent(HttpServletRequest request)
    {
        EClassEvent event = new EClassEvent();
	EClassModel ecm=new EClassModel();
	ArrayList missingFields = null;

        String name=request.getParameter("name").trim();
	if (name.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("名称"));
        }

        String info=request.getParameter("info").trim();
	if (info.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("说明"));
        }

	if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException("需确认的用户数据", missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

	Integer classID=new Integer(request.getParameter("classId").trim());

        ecm.setClassID(classID);
	ecm.setName(InterpretSQL.transform(name));
	ecm.setInfo(InterpretSQL.transform(info));

        event.setActionType(event.UPDATE_ECLASS);
	event.setEcm(ecm);

        return event;
    }

    private MainEvent createDeleteEClassEvent(HttpServletRequest request)
    {
        EClassEvent event = new EClassEvent();
	EClassModel ecm=new EClassModel();

        Integer classID=new Integer(request.getParameter("classId").trim());
	ecm.setClassID(classID);
        event.setActionType(event.DELETE_ECLASS);
        event.setEcm(ecm);
        return event;
    }
}

