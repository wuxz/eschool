package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.HomeWorkEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.MissingFormDataException;
import com.dc.eschool.homework.model.HomeWorkModel;

import com.dc.eschool.util.*;

/**
 * HomeWork Handler
 *
*/
public class HomeWorkHandler extends RequestHandlerSupport
{
    public MainEvent processRequest(HttpServletRequest request) throws ControllerException
    {
        Debug.println("Started creation of an HomeWork Event");

        String action = request.getParameter("action");
        Debug.println("HomeWorkHandler (web): action=" + action);
        if (action == null)
        {
            return null;
        } else if (action.equals("createhomework"))
        {
            return createNewHomeWorkEvent(request);
        }else if (action.equals("updatehomework"))
        {
            return createUpdateHomeWorkEvent(request);
        } else if (action.equals("deletehomework"))
        {
            return createDeleteHomeWorkEvent(request);
        }
        return null;
    }

    private MainEvent createNewHomeWorkEvent(HttpServletRequest request)
    {
        HomeWorkEvent event = new HomeWorkEvent();
	HomeWorkModel hm= new HomeWorkModel();
        PropertiesManager properMgr=new PropertiesManager();

	try
	{
	    String uploadPath=properMgr.getUploadPath();
	    Integer projectID=new Integer(request.getParameter("projectId"));
	    ArrayList missingFields = null;

	    FilesUpload f_up= new FilesUpload();
	    f_up.initialize(request);
	    String uploadFileName="H"+projectID.toString();
	    boolean isupload=f_up.save(uploadPath,uploadFileName,"newhomework");
	    if(isupload)
	    {
              String docURL=f_up.getFilename().elementAt(0).toString();
	      String name=f_up.getVfile().elementAt(0).toString();
	      Integer fileSize=new Integer(f_up.getFilesSize().elementAt(0).toString());
	      hm.setDocURL(docURL);
              System.out.println(projectID);
	      hm.setProjectID(projectID);
	      hm.setSize(fileSize);

	      event.setHm(hm);
	      event.setActionType(event.CREATE_HOMEWORK);
	    }else
	    {
		String sMessage=f_up.getMessage();
		if (missingFields == null)
		{
		    missingFields = new ArrayList();
		}
		missingFields.add(InterpretSQL.transformChinese(sMessage));
		if (missingFields != null)
		{
		    MissingFormDataException ex = new MissingFormDataException(InterpretSQL.transformChinese("上传文件错误"), missingFields);
		    request.setAttribute(WebKeys.MissingFormDataKey, ex);
		    return null;
		}
	    }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return event;
    }

    private MainEvent createUpdateHomeWorkEvent(HttpServletRequest request)
    {
        HomeWorkEvent event = new HomeWorkEvent();
	HomeWorkModel hm=new HomeWorkModel();
        PropertiesManager properMgr=new PropertiesManager();

	try
	{
	    String uploadPath=properMgr.getUploadPath();
	    ArrayList missingFields = null;

	    FilesUpload f_up= new FilesUpload();
	    f_up.initialize(request);
	    boolean isupload=f_up.save(uploadPath,null,"homework");

	    if(isupload)
	    {
              String docURL=f_up.getFilename().elementAt(0).toString();
	      Integer fileSize=new Integer(f_up.getFilesSize().elementAt(0).toString());
	      hm.setDocURL(docURL);
	      hm.setSize(fileSize);

	      event.setHm(hm);
	      event.setActionType(event.UPDATE_HOMEWORK);
	    }else
	    {
		String sMessage=f_up.getMessage();
		if (missingFields == null)
		{
		    missingFields = new ArrayList();
		}
		missingFields.add(InterpretSQL.transformChinese(sMessage));
		if (missingFields != null)
		{
		    MissingFormDataException ex = new MissingFormDataException(InterpretSQL.transformChinese("上传文件错误"), missingFields);
		    request.setAttribute(WebKeys.MissingFormDataKey, ex);
		    return null;
		}
	    }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return event;
    }

    private MainEvent createDeleteHomeWorkEvent(HttpServletRequest request)
    {
        HomeWorkEvent event = new HomeWorkEvent();
	HomeWorkModel hm=new HomeWorkModel();
        Integer projectID=new Integer(request.getParameter("projectId").trim());
	Integer homeworkContentID=new Integer(request.getParameter("homeWorkContentId").trim());
	hm.setProjectID(projectID);
	hm.setHomeWorkContentID(homeworkContentID);

        event.setActionType(event.DELETE_HOMEWORK);
        event.setHm(hm);
        return event;
    }
}

