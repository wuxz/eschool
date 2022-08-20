package com.dc.eschool.scorestatistic.mgrbean;

import java.util.Collection;
import java.util.Locale;
import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;

import com.dc.eschool.scorestatistic.exceptions.*;
import com.dc.eschool.scorestatistic.model.*;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * This interface is the remote interface for the ScoreStatisticSL EJB.
 * It provides the ejb-tier implementation of the List functions
 */

public interface ScoreStatisticSL extends EJBObject
{
  public void deleteScoreStatistic(Integer primKey) throws ScoreStatisticDeleteException, RemoteException;
  public ScoreStatisticModel getScoreStatistic(String scoreStatisticId) throws RemoteException;
  public void insertScoreStatistic(ScoreStatisticModel sm) throws ScoreStatisticCreateException, RemoteException;
  public com.dc.eschool.util.ListChunk searchScoreStatistic(String clause, int startIndex, int count) throws ScoreStatisticSearchException, RemoteException;
  public com.dc.eschool.util.ListChunk searchScoreStatisticProject(String projectID, int startIndex, int count, boolean isAsc) throws ScoreStatisticSearchException, RemoteException;
  public void updateScoreStatistic(ScoreStatisticModel sm) throws ScoreStatisticCreateException, RemoteException;
}
