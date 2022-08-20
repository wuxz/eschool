package com.dc.eschool.homeworkcontent.mgrbean;

import java.rmi.*;
import javax.ejb.*;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * The home Interface of HWContentSLBean(Stateless Session Bean)
 */
public interface HWContentSLHome extends EJBHome {
  public HWContentSL create() throws RemoteException, CreateException;
}