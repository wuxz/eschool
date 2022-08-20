package com.dc.eschool.examinationscore.mgrbean;

import java.util.Collection;
import java.util.Locale;
import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import com.dc.eschool.examinationscore.exceptions.*;
import com.dc.eschool.examinationscore.model.*;


/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface is the remote interface for the ExaminationScoreSL EJB.
 * It provides the ejb-tier implementation of the List functions
 */

public interface ExaminationScoreSL extends EJBObject
{
  public void deleteExaminationScore(Integer primKey) throws ExaminationScoreDeleteException, RemoteException;
  public ExaminationScoreModel getExaminationScore(String examinationId) throws RemoteException;
  public void insertExaminationScore(ExaminationScoreModel em) throws ExaminationScoreCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchExaminationScore(String clause, int startIndex, int count) throws ExaminationScoreSearchException, RemoteException;
  public void updateExaminationScore(ExaminationScoreModel em) throws ExaminationScoreCreateException, RemoteException;
}
