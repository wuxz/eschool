package com.dc.eschool.content.ejb;

import java.rmi.*;
import javax.ejb.*;
import com.dc.eschool.content.model.ContentModel;
/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public interface ContentEBHome extends EJBHome
{
  public ContentEB create(ContentModel cm) throws CreateException, RemoteException;
  public ContentEB findByPrimaryKey(Integer primKey) throws ObjectNotFoundException, RemoteException, FinderException;
}