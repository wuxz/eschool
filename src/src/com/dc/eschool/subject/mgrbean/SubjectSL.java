package com.dc.eschool.subject.mgrbean;

import java.rmi.*;
import javax.ejb.*;

import com.dc.eschool.subject.exceptions.*;
import com.dc.eschool.subject.model.SubjectModel;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */


/**
 * The remote interface of SubjectSLBean(Statelesee Session bean)
 */
public interface SubjectSL extends EJBObject
{
  public void deleteSubject(Integer primKey) throws SubjectDeleteException, RemoteException;
  public SubjectModel getSubject(String subjectId) throws RemoteException;
  public void insertSubject(SubjectModel sm) throws SubjectCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchSubject(String clause, int startIndex, int count) throws SubjectSearchException, RemoteException;
  public void updateSubject(SubjectModel sm) throws SubjectCreateException, RemoteException;
}