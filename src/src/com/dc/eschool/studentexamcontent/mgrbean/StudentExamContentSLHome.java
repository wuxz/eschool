package com.dc.eschool.studentexamcontent.mgrbean;

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
 * The home Interface of StudentExamContentSLBean(Stateless Session Bean)
 */
public interface StudentExamContentSLHome extends EJBHome {
    public StudentExamContentSL create() throws RemoteException, CreateException;
}