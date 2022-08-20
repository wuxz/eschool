package com.dc.eschool.subject.ejb;

import java.rmi.*;
import javax.ejb.*;
import com.dc.eschool.subject.model.SubjectModel;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public interface SubjectEB extends EJBObject
{
  public SubjectModel getDetails() throws RemoteException;
  public void setAllow(String p0) throws RemoteException;
  public void setLastModifyBy(Integer p0) throws RemoteException;
  public void setName(String p0) throws RemoteException;
}