package com.dc.eschool.rmi.ejb;

import java.rmi.*;
import javax.ejb.*;

/**
 * Title:        
 * Description:  
 * Copyright:    Copyright (c) 2001
 * Company:      
 * @author 
 * @version 1.0
 */

public interface RMIInterfaceSLHome extends EJBHome
{
  public RMIInterfaceSL create() throws RemoteException, CreateException;
}