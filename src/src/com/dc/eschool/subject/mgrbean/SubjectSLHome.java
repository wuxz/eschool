package com.dc.eschool.subject.mgrbean;

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
 * The home Interface of SubjectSLBean(Stateless Session Bean)
 */
public interface SubjectSLHome extends EJBHome {
    public SubjectSL create() throws RemoteException, CreateException;
}