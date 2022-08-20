package com.dc.eschool.course.mgrbean;

import java.rmi.*;
import javax.ejb.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author eric
 * @version 1.0
 */

/**
 * The home Interface of CourseSLBean(Stateless Session Bean)
 */
public interface CourseSLHome extends EJBHome {
    public CourseSL create() throws RemoteException, CreateException;
}