package com.dc.eschool.projectcontent.ejb;

import java.rmi.*;
import javax.ejb.*;
import com.dc.eschool.projectcontent.model.ProjectContentModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public interface ProjectContentEBHome extends EJBHome
{
  public ProjectContentEB create(ProjectContentModel pm) throws CreateException, RemoteException;
  public ProjectContentEB findByPrimaryKey(Integer primKey) throws ObjectNotFoundException, RemoteException, FinderException;
  public ProjectContentEB findByProjectContent(Integer projectID, Integer contentID) throws ObjectNotFoundException, RemoteException, FinderException;
}