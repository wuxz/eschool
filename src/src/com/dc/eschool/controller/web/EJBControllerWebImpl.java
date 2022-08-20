package com.dc.eschool.controller.web;


import java.util.Locale;
import java.util.Collection;
import javax.rmi.PortableRemoteObject;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

import java.rmi.RemoteException;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

import com.dc.eschool.controller.event.*;
import com.dc.eschool.util.*;
import com.dc.eschool.controller.handlers.*;
import com.dc.eschool.controller.ejb.*;

import com.dc.eschool.controller.exception.ControllerException;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * This class is essentially just a proxy object that calls methods on the
 * EJB tier using the com.dc.eschoo.controller.ejb.EJBControllerSFBean
 * object. All the methods that access the EJB are synchronized so
 * that concurrent requests do not happen to the stateful session bean.
 *
 * @see com.dc.eschoo.controller.ejb.EJBControllerSF
 * @see com.dc.eschoo.controller.ejb.EJBControllerSFBean
 * @see com.dc.eschoo.controller.event.MainEvent
 * @author Eric
 */
public class EJBControllerWebImpl  implements java.io.Serializable
{
    private EJBControllerSF ecEjb;
    private HttpSession session;

  public EJBControllerWebImpl()
  {
  }

  public EJBControllerWebImpl(HttpSession session) throws ControllerException
  {
    this.session = session;
    ModelManager mm = (ModelManager)session.getAttribute(WebKeys.ModelManagerKey);
    ecEjb = mm.getECEJB();
  }

    /**
     * feeds the specified event to the state machine of the business logic.
     *
     * @param ese is the current event
     * @return a list of models that got updated because of the
     *         processing of this event.
     * @exception com.dc.eschool.controller.exception.ControllerException
     */
    public synchronized Collection handleEvent(MainEvent ese)
        throws ControllerException
    {
        try
        {
            return ecEjb.handleEvent(ese);
        } catch (RemoteException re)
        {
                throw new ControllerException(re.getMessage());
        }
    }

     /**
     * frees up all the resources associated with this controller and
     * destroys itself.
     */
    public synchronized void remove()
    {
        try
        {
            ecEjb.remove();
        }
        catch(RemoveException re)
        {
            // ignore, after all its only a remove() call!
            Debug.print(re);
        }
        catch(RemoteException re)
        {
          Debug.print(re);
        }
    }
}