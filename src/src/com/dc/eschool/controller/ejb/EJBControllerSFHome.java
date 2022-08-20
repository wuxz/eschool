package com.dc.eschool.controller.ejb;

import java.rmi.*;
import javax.ejb.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Eric
 * @version 1.0
 */

/**
 * The Home interface for SEJBController EJB
 */
public interface EJBControllerSFHome extends EJBHome
{
  public EJBControllerSF create() throws RemoteException, CreateException;
}