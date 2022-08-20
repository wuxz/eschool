package com.dc.eschool.content.mgrbean;

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

public interface ContentMgrSLHome extends EJBHome
{
  public ContentMgrSL create() throws RemoteException, CreateException;
}