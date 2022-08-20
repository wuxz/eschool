package com.dc.eschool.coursestudent.mgrbean;

import java.rmi.*;
import javax.ejb.*;
import com.dc.eschool.coursestudent.model.CourseStudentModel;
import com.dc.eschool.coursestudent.exceptions.*;

import com.dc.eschool.util.ListChunk;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * The remote interface of CourseStudentSLBean(Statelesee Session bean)
 */

public interface CourseStudentSL extends EJBObject
{
    public void deleteCourseStudent(Integer student,Integer courseID) throws CSDeleteException, RemoteException;
    public void insertCourseStudent(CourseStudentModel sm) throws CSCreateException, RemoteException;
    public ListChunk searchCourseStudent(String clause, int startIndex, int count) throws CSSearchException, RemoteException;
}