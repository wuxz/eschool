package com.dc.eschool.controller.ejb;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.web.StateMachine;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.util.EJBUtil;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author    Eric
 * @version 1.0
 */

/**
 * Session Bean implementation for EJBController EJB.
 */
public class EJBControllerSFBean implements SessionBean
{
  private SessionContext sc;
  private StateMachine sm;


  public void ejbCreate()
  {
    sm = new StateMachine(this, sc);
  }
  public void ejbRemove() throws RemoteException
  {
  }
  public void ejbActivate() throws RemoteException
  {
  }
  public void ejbPassivate() throws RemoteException
  {
  }
  public void setSessionContext(SessionContext sessionContext) throws RemoteException
  {
    this.sc = sessionContext;
  }

  public Collection handleEvent(MainEvent ese)
        throws ControllerException
  {
          return (sm.handleEvent(ese));
  }
  public StateMachine getSm()
  {
    return sm;
  }

}