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

import com.dc.eschool.content.mgrbean.ContentMgrSL;
import com.dc.eschool.content.mgrbean.ContentMgrSLHome;
import com.dc.eschool.content.model.ContentModel;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ContentStateHandler extends StateHandlerSupport
{
    public void perform(MainEvent event) throws ControllerException
    {
        ContentEvent pe = (ContentEvent)event;
        switch(pe.getActionType())
        {
            case ContentEvent.CREATE_CONTENT:
            {
                Debug.println("State Handled CONTENT_CREATE_EVENT");

                try
                {
                    ContentMgrSL content=getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
                    ContentModel cm=pe.getCm();
                    cm.setState("²»¹«²¼");
                    cm.setHasAnswerItem("N");
                    cm.setCreateBy(userID);
                    cm.setLastModifyBy(userID);
                    content.insertContent(cm);
                }catch (Exception re)
                {
                    System.out.println("Irrecoverable error while creating content: " + re);
                }
            }break;
            case ContentEvent.DELETE_CONTENT:
            {
                Debug.println("State Handled CONTENT_DELETE_EVENT");

                try
                {
                    ContentMgrSL content=getManager();
                    ContentModel cm=pe.getCm();
                    content.deleteContent(cm) ;
                }catch (Exception re)
                {
                    System.out.println("Irrecoverable error while creating content: " + re);
                }
            }break;
	    case ContentEvent.CREATE_PROJRCT_CONTENT:
            {
                Debug.println("State Handled CREATE_PROJRCT_CONTENT");

                try
                {

                    ContentMgrSL content=getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
                    ContentModel cm=pe.getCm();
                    cm.setCreateBy(userID);
                    cm.setLastModifyBy(userID);
                    content.insertProjectContent(cm);
                }catch (Exception re)
                {
                    System.out.println("Irrecoverable error while creating content: " + re);
                }
            }break;
        }
    }

    private ContentMgrSL getManager()
    {
        ContentMgrSL content=null;
        ContentMgrSLHome home =null;

        try
        {
            home = EJBUtil.getContentMgrHome();
            content = home.create();
        } catch (javax.naming.NamingException ce)
        {
            throw new EJBException("Irrecoverable error creating Content: " + ce);
        } catch (java.rmi.RemoteException re)
        {
            throw new EJBException("Irrecoverable error while creating content: " + re);
        } catch (CreateException ce)
        {
            System.out.println("Unable to create a new account for " + ce + " at this time");
        }
        return content;
    }
}