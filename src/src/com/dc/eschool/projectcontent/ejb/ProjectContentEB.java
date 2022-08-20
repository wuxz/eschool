package com.dc.eschool.projectcontent.ejb;

import java.rmi.*;
import javax.ejb.*;
import java.util.Collection;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public interface ProjectContentEB extends EJBObject
{
  public com.dc.eschool.projectcontent.model.ProjectContentModel getDetails() throws RemoteException;
}