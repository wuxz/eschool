package com.dc.eschool.homework.mgrbean;

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
 * The home Interface of HomeWorkSLBean(Stateless Session Bean)
 */
public interface HomeWorkSLHome extends EJBHome
{
  public HomeWorkSL create() throws RemoteException, CreateException;
}