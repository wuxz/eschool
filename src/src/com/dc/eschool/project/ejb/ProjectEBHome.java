package com.dc.eschool.project.ejb;

import java.rmi.*;
import javax.ejb.*;
import com.dc.eschool.project.model.ProjectModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public interface ProjectEBHome extends EJBHome
{
  public ProjectEB create(ProjectModel pm) throws CreateException, RemoteException;
  public ProjectEB findByPrimaryKey(Integer primKey) throws ObjectNotFoundException, RemoteException, FinderException;
}