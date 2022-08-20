package com.dc.eschool.course.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.course.exceptions.*;
import com.dc.eschool.util.ListChunk;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */


/**
 * The remote interface of CourseSLBean(Statelesee Session bean)
 */
public interface CourseSL extends EJBObject
{
    public void deleteCourse(Integer primKey) throws CourseDeleteException, RemoteException;
    public CourseModel getCourse(String courseId) throws RemoteException;
    public void insertCourse(CourseModel sm) throws CourseCreateException, RemoteException;
    public ListChunk searchCourse(String clause, int startIndex, int count, String value) throws CourseSearchException, RemoteException;
    public void updateCourse(CourseModel sm) throws CourseCreateException, RemoteException;
}