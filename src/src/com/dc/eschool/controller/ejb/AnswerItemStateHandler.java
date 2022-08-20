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

import com.dc.eschool.answeritem.mgrbean.AnswerItemSL;
import com.dc.eschool.answeritem.mgrbean.AnswerItemSLHome;
import com.dc.eschool.answeritem.model.AnswerItemModel;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class AnswerItemStateHandler extends StateHandlerSupport
{
    public void perform(MainEvent event) throws ControllerException
    {
        AnswerItemEvent ae = (AnswerItemEvent)event;
        switch(ae.getActionType())
        {
            case AnswerItemEvent.CREATE_ANSWERITEM:
            {
                Debug.println("State Handled ANSWERITEM_CREATE_EVENT");

                try
                {
                    AnswerItemSL answerItem=getManager();
		    AnswerItemModel am=ae.getAm();

                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		    am.setCreateBy(userID);
		    am.setLastModifyBy(userID);
                    answerItem.insertAnswerItem(am);
                }catch(com.dc.eschool.answeritem.exceptions.AnswerItemCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating answerItem: " + re);
                }
            }break;
            case AnswerItemEvent.UPDATE_ANSWERITEM:
            {
                Debug.println("State Handled ANSWERITEM_UPDATE_EVENT");

                try
                {
                    AnswerItemSL answerItem=getManager();
		    AnswerItemModel am=ae.getAm();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		    am.setLastModifyBy(userID);
                    answerItem.updateAnswerItem(am);
                }catch(com.dc.eschool.answeritem.exceptions.AnswerItemCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating answerItem: " + re);
                }
            }break;
            case AnswerItemEvent.DELETE_ANSWERITEM:
            {
                Debug.println("State Handled ANSWERITEM_UPDATE_EVENT");

                try
                {
                    AnswerItemSL answerItem=getManager();
		    AnswerItemModel am=ae.getAm();

                    answerItem.deleteAnswerItem(am.getAnswerItemID()) ;
                }catch (Exception re)
                {
                    System.out.println("Irrecoverable error while creating answerItem: " + re);
                }
            }break;
        }
    }

    private AnswerItemSL getManager()
    {
        AnswerItemSL answerItem=null;
        AnswerItemSLHome home =null;

        try
        {
            home = EJBUtil.getAnswerItemSLHome();
            answerItem = home.create();
        } catch (javax.naming.NamingException ce)
        {
            throw new EJBException("Irrecoverable error creating AnswerItem: " + ce);
        } catch (java.rmi.RemoteException re)
        {
            throw new EJBException("Irrecoverable error while creating answerItem: " + re);
        } catch (CreateException ce)
        {
            System.out.println("Unable to create a new account for " + ce + " at this time");
        }
        return answerItem;
    }
}