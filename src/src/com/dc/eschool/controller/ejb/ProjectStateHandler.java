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

import com.dc.eschool.project.mgrbean.ProjectMgrSL;
import com.dc.eschool.project.mgrbean.ProjectMgrSLHome;
import com.dc.eschool.project.model.ProjectModel;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ProjectStateHandler extends StateHandlerSupport
{
    public void perform(MainEvent event) throws ControllerException
    {
        ProjectEvent pe = (ProjectEvent)event;
        switch(pe.getActionType())
        {
            case ProjectEvent.CREATE_PROJECT:
            {
                Debug.println("State Handled PROJECT_CREATE_EVENT");

                try
                {
                    ProjectMgrSL project=getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
                    ProjectModel pm=pe.getPm();
                    pm.setCreateBy(userID);
                    pm.setLastModifyBy(userID);
                    Integer projectID=project.insertProject(pm);
		    machine.setAttribute(WebKeys.ProjectIDKey,projectID);
                }catch(com.dc.eschool.project.exceptions.ProjectCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating project: " + re);
                }
            }break;
            case ProjectEvent.UPDATE_PROJECT:
            {
                Debug.println("State Handled PROJECT_UPDATE_EVENT");

                try
                {
                    ProjectMgrSL project=getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
                    ProjectModel pm=pe.getPm();
                    pm.setLastModifyBy(userID);
                    project.updateProject(pm);
                }catch (Exception re)
                {
                    throw new EJBException("Irrecoverable error while creating project: " + re);
                }
            }break;
            case ProjectEvent.DELETE_PROJECT:
            {
                Debug.println("State Handled PROJECT_UPDATE_EVENT");

                try
                {
                    ProjectMgrSL project=getManager();
                    ProjectModel pm=pe.getPm();
                    project.deleteProject(pm.getProjectID()) ;
                }catch (Exception re)
                {
                    System.out.println("Irrecoverable error while creating project: " + re);
                }
            }break;
        }
    }

    private ProjectMgrSL getManager()
    {
        ProjectMgrSL project=null;
        ProjectMgrSLHome home =null;

        try
        {
            home = EJBUtil.getPMSLHome();
            project = home.create();
        } catch (javax.naming.NamingException ce)
        {
            throw new EJBException("Irrecoverable error creating Project: " + ce);
        } catch (java.rmi.RemoteException re)
        {
            throw new EJBException("Irrecoverable error while creating project: " + re);
        } catch (CreateException ce)
        {
            System.out.println("Unable to create a new account for " + ce + " at this time");
        }
        return project;
    }
}