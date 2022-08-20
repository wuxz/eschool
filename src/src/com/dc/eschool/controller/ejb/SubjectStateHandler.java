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

import com.dc.eschool.subject.mgrbean.SubjectSL;
import com.dc.eschool.subject.mgrbean.SubjectSLHome;
import com.dc.eschool.subject.model.SubjectModel;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SubjectStateHandler extends StateHandlerSupport
{
    public void perform(MainEvent event) throws ControllerException
    {
        SubjectEvent se = (SubjectEvent)event;
        switch(se.getActionType())
        {
            case SubjectEvent.CREATE_SUBJECT:
            {
                Debug.println("State Handled SUBJECT_CREATE_EVENT");

                try
                {
                    SubjectSL subject=getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		    SubjectModel sm=se.getSm();
		    sm.setCreateBy(userID);
		    sm.setLastModifyBy(userID);
                    subject.insertSubject(sm);
                }catch(com.dc.eschool.subject.exceptions.SubjectCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating subject: " + re);
                }
            }break;
            case SubjectEvent.UPDATE_SUBJECT:
            {
                Debug.println("State Handled SUBJECT_UPDATE_EVENT");

                try
                {
                    SubjectSL subject=getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		    SubjectModel sm=se.getSm();
		    sm.setLastModifyBy(userID);
                    subject.updateSubject(sm);
                }catch(com.dc.eschool.subject.exceptions.SubjectCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating subject: " + re);
                }
            }break;
            case SubjectEvent.DELETE_SUBJECT:
            {
                Debug.println("State Handled SUBJECT_UPDATE_EVENT");

                try
                {
                    SubjectSL subject=getManager();
		    SubjectModel sm=se.getSm();
                    subject.deleteSubject(sm.getSubjectID()) ;
                }catch (Exception re)
                {
                    System.out.println("Irrecoverable error while creating subject: " + re);
                }
            }break;
        }
    }

    private SubjectSL getManager()
    {
        SubjectSL subject=null;
        SubjectSLHome home =null;

        try
        {
            home = EJBUtil.getSubjectSLHome();
            subject = home.create();
        } catch (javax.naming.NamingException ce)
        {
            throw new EJBException("Irrecoverable error creating Subject: " + ce);
        } catch (java.rmi.RemoteException re)
        {
            throw new EJBException("Irrecoverable error while creating subject: " + re);
        } catch (CreateException ce)
        {
            System.out.println("Unable to create a new account for " + ce + " at this time");
        }
        return subject;
    }
}