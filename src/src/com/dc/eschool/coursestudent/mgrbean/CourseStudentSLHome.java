package com.dc.eschool.coursestudent.mgrbean;

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
 * The home Interface of CourseStudentSLBean(Stateless Session Bean)
 */
public interface CourseStudentSLHome extends EJBHome {
    public CourseStudentSL create() throws RemoteException, CreateException;
}