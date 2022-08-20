package com.dc.eschool.sessiondata;

import java.rmi.*;
import javax.ejb.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public interface SessionSLHome extends EJBHome
{
  public SessionSL create() throws RemoteException, CreateException;
}