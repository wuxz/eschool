package com.dc.eschool.users.mgrbean;

import java.rmi.*;
import javax.ejb.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * The home Interface of UsersSLBean(Stateless Session Bean)
 */
public interface UsersSLHome extends EJBHome {
    public UsersSL create() throws RemoteException, CreateException;
}