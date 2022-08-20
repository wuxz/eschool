package com.dc.eschool.project.mgrbean;

import java.rmi.*;
import javax.ejb.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public interface ProjectMgrSLHome extends EJBHome
{
  public ProjectMgrSL create() throws RemoteException, CreateException;
}