package com.dc.eschool.controller.ejb;

import java.rmi.*;
import java.util.Collection;
import javax.ejb.*;

import com.dc.eschool.controller.exception.*;
import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.web.StateMachine;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Eric
 * @version 1.0
 */

/**
 * This is the EJB-tier controller of the MVC.
 * It is implemented as a session EJB. It controls all the activities
 * that happen in a client session.
 * It also provides mechanisms to access other session EJBs.
 * @author Eric
 */
public interface EJBControllerSF extends EJBObject
{
  public Collection handleEvent(MainEvent ese) throws ControllerException, RemoteException;
  public StateMachine getSm() throws RemoteException;
}