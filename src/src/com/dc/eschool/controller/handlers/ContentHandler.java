package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.ContentEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.MissingFormDataException;
import com.dc.eschool.content.model.ContentModel;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.InterpretSQL;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.FilesUpload;
import com.dc.eschool.util.PropertiesManager;

/**
 * Content Handler
 *
*/
public class ContentHandler extends RequestHandlerSupport
{
    Vector userVector=new Vector();

    public MainEvent processRequest(HttpServletRequest request) throws ControllerException
    {
        Debug.println("Started creation of an Content Event");

        String action = request.getParameter("action");
        Debug.println("ContentHandler (web): action=" + action);
        if (action == null)
        {
            return null;
        }else if (action.equals("createcontent"))
        {
            return createNewContentEvent(request);
        }else if (action.equals("deletecontent"))
        {
            return createDeleteContentEvent(request);
        }else if (action.equals("createpc"))
        {
            return createProjectContentEvent(request);
        }
        return null;
    }

    private MainEvent createNewContentEvent(HttpServletRequest request)
    {
        ContentEvent event = new ContentEvent();
        ContentModel cm= new ContentModel();
	PropertiesManager properMgr=new PropertiesManager();

	try
	{
	    String uploadPath=properMgr.getUploadPath();
	    ArrayList missingFields = null;

	    FilesUpload f_up= new FilesUpload();
	    f_up.initialize(request);
	    boolean isupload=f_up.save(uploadPath,null,"project");

	    if(isupload)
	    {
	      String docURL=f_up.getFilename().elementAt(0).toString();
	      String name=f_up.getVfile().elementAt(0).toString();
	      Integer fileSize=new Integer(f_up.getFilesSize().elementAt(1).toString());

	      Integer projectID=new Integer(request.getParameter("projectId"));
	      cm.setName(InterpretSQL.transform(name));
	      cm.setDocURL(docURL);
	      cm.setFileSize(fileSize);
	      cm.setProjectID(projectID);
	      event.setActionType(event.CREATE_CONTENT);
	      event.setCm(cm);
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

    private MainEvent createDeleteContentEvent(HttpServletRequest request)
    {
        ContentEvent event = new ContentEvent();
        ContentModel cm= new ContentModel();

        Integer contentID=new Integer(request.getParameter("contentID").trim());
        Integer projectID=new Integer(request.getParameter("projectId").trim());
	Integer projectContentID=new Integer(request.getParameter("projectContentID").trim());
        cm.setContentID(contentID);
        cm.setProjectID(projectID);
	cm.setProjectContentID(projectContentID);
        event.setActionType(event.DELETE_CONTENT);
        event.setCm(cm);
        return event;
    }

    private MainEvent createProjectContentEvent(HttpServletRequest request)
    {
        ContentEvent event = new ContentEvent();
        ContentModel cm= new ContentModel();

	try
	{
          Integer projectID=new Integer(request.getParameter("projectId"));
	  Integer contentID=new Integer(request.getParameter("contentID"));
          cm.setContentID(contentID);
          cm.setProjectID(projectID);
          event.setActionType(event.CREATE_PROJRCT_CONTENT);
          event.setCm(cm);
        }catch(Exception e)
	{
	  System.out.println(e);
	}
        return event;
    }
}

