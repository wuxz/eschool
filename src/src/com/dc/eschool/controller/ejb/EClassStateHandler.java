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

import com.dc.eschool.eclass.mgrbean.EClassSL;
import com.dc.eschool.eclass.mgrbean.EClassSLHome;
import com.dc.eschool.eclass.model.EClassModel;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class EClassStateHandler extends StateHandlerSupport
{
    public void perform(MainEvent event) throws ControllerException
    {
        EClassEvent ee = (EClassEvent)event;
        switch(ee.getActionType())
        {
            case EClassEvent.CREATE_ECLASS:
            {
                Debug.println("State Handled ECLASS_CREATE_EVENT");
                try
                {
                    System.out.println("test::::");
		    EClassSL eclass=getManager();
		    System.out.println("test::::");
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		    System.out.println("Test:::::");
		    EClassModel ecm=ee.getEcm();
		    ecm.setCreateBy(userID);
		    ecm.setLastModifyBy(userID);
                    eclass.insertEClass(ecm);
                }catch(com.dc.eschool.eclass.exceptions.EClassCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating eclass: " + re);
                }
            }break;
            case EClassEvent.UPDATE_ECLASS:
            {
                Debug.println("State Handled ECLASS_UPDATE_EVENT");

                try
                {
                    EClassSL eclass=getManager();
                    Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		    EClassModel ecm=ee.getEcm();
		    ecm.setLastModifyBy(userID);
                    eclass.updateEClass(ecm);
                }catch(com.dc.eschool.eclass.exceptions.EClassCreateException uce)
                {
                    System.out.println(uce);
                } catch (java.rmi.RemoteException re)
                {
                    throw new EJBException("Irrecoverable error while creating eclass: " + re);
                }
            }break;
            case EClassEvent.DELETE_ECLASS:
            {
                Debug.println("State Handled ECLASS_UPDATE_EVENT");

                try
                {
                    EClassSL eclass=getManager();
		    EClassModel ecm=ee.getEcm();
                    eclass.deleteEClass(ecm.getClassID()) ;
                }catch (Exception re)
                {
                    System.out.println("Irrecoverable error while creating eclass: " + re);
                }
            }break;
        }
    }

    private EClassSL getManager()
    {
        EClassSL eclass=null;
        EClassSLHome home =null;

        try
        {
	    System.out.println("getmanager");
            home = EJBUtil.getEClassSLHome();
	    System.out.println("getmanager");
            eclass = home.create();
	    System.out.println("getmanager");
        } catch (javax.naming.NamingException ce)
        {
            throw new EJBException("Irrecoverable error creating EClass: " + ce);
        } catch (java.rmi.RemoteException re)
        {
            throw new EJBException("Irrecoverable error while creating eclass: " + re);
        } catch (CreateException ce)
        {
            System.out.println("Unable to create a new account for " + ce + " at this time");
        }
        return eclass;
    }
}