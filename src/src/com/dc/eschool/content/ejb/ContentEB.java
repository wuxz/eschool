package com.dc.eschool.content.ejb;

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

public interface ContentEB extends EJBObject
{
  public com.dc.eschool.content.model.ContentModel getDetails() throws RemoteException;
  public void setInfo(java.lang.String p0) throws RemoteException;
  public void setState(java.lang.String p0) throws RemoteException;
  public void setHasAnswerItem(java.lang.String p0) throws RemoteException;
}