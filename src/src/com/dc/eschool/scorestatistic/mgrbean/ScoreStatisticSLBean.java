package com.dc.eschool.scorestatistic.mgrbean;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import javax.ejb.FinderException;
import javax.ejb.EJBException;

import java.util.Collection;
import java.util.Locale;

import com.dc.eschool.scorestatistic.dao.ScoreStatisticMgrDAO;
import com.dc.eschool.scorestatistic.model.ScoreStatisticModel;
import com.dc.eschool.scorestatistic.ejb.*;

import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAOSysException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticSearchException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticCreateException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDAOFinderException;
import com.dc.eschool.scorestatistic.exceptions.ScoreStatisticDeleteException;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.util.EJBUtil;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * Session Bean implementation of ScoreStatistic
 *
 */
public class ScoreStatisticSLBean implements SessionBean
{
	protected ScoreStatisticMgrDAO dao;
	private SessionContext sessionContext;

	public void ejbCreate()
	{
		try
		{
			dao = new ScoreStatisticMgrDAO();
		}
		catch(Exception e)
		{
		  String str = "Exception while ScoreStatisticMgrBean creating :" + e.getMessage();
		  Debug.println(str);
		  throw new EJBException(str);
		}
	}

	public void setSessionContext(SessionContext sc) throws RemoteException
	{
	  this.sessionContext = sc;
	}

	public void ejbRemove() throws RemoteException
	{
	  dao = null;
	}

	public void ejbActivate() throws RemoteException
	{
		dao = null;
	}

	public void ejbPassivate() throws RemoteException
	{
	  dao = null;
	}

	 /**
	 * Insert an data to Class Table
	 * @param sm a SubjectModel that represents the properties.
	 * @return an <code>Integer</code> that has the Class information
	 *         corresponding to a Class item.
	 * @exception <code>SubjectCreateException</code> for irrecoverable errors
	 */
	  public void insertScoreStatistic(ScoreStatisticModel sm) throws ScoreStatisticCreateException
	  {
		  try
		  {
			  ScoreStatisticEBHome home = EJBUtil.getScoreStatisticEBHome();
			  ScoreStatisticEB remote = home.create(sm);
		  }
		  catch(Exception e)
		  {
			  Debug.print(e,"Exception while createClass in SCORESTATISTIC_MANAGER.");
			  throw new ScoreStatisticCreateException(e);
		  }
	  }

	 /**
	 * remove record from Class by ClassID
	 * @param primKey int primaty key of record
	 * @exception <code>SubjectDeleteException</code>
	 */
	public void deleteScoreStatistic(Integer primKey) throws ScoreStatisticDeleteException
	{
	try
	{
		ScoreStatisticEBHome home = EJBUtil.getScoreStatisticEBHome();
		ScoreStatisticEB remote = home.findByPrimaryKey(primKey);
		remote.remove();
	}
	catch(Exception e)
	{
		Debug.print(e,"Exception while delete Class from Class Table");
		throw new ScoreStatisticDeleteException(e);
	}
	}

	/**
	 * update subject record
	 * @param sm SubjectModel
	 */
	public void updateScoreStatistic(ScoreStatisticModel sm) throws ScoreStatisticCreateException
	{
	try
	{
		ScoreStatisticEBHome home = EJBUtil.getScoreStatisticEBHome();
		ScoreStatisticEB remote = home.findByPrimaryKey(sm.getScoreStatisticID());
		remote.changeScoreStatistic(sm);
	}
	catch(Exception e)
	{
		Debug.print(e,"Exception while createClass in SCORESTATISTIC_MANAGER.");
		throw new ScoreStatisticCreateException(e);
	}
	}

	/**
	 * Gets the data from Class Table by aptly SQL
	 * @param clause a string that represents the SQL
	 * @return the <code>ListChunk</code> that have the Subject information
	 *         corresponding to class items.
	 * @exception <code>SubjectSearchException</code>
	 */
	public ListChunk searchScoreStatistic(String clause, int startIndex, int count) throws ScoreStatisticSearchException
	{
	try
	{
		getDAO();
		return dao.searchScoreStatistic(clause, startIndex, count);
	}
	catch(ScoreStatisticDAOFinderException pde)
	{
		throw new ScoreStatisticSearchException(pde);
	}
	}

	/**
	 * Gets the data from Class Table by aptly SQL
	 * @param clause a string that represents the SQL
	 * @return the <code>ListChunk</code> that have the Subject information
	 *         corresponding to class items.
	 * @exception <code>SubjectSearchException</code>
	 */
	public ListChunk searchScoreStatisticProject(String projectID, int startIndex, int count, boolean isAsc) throws ScoreStatisticSearchException
	{
	try
	{
		getDAO();
		return dao.searchScoreStatisticProject(projectID, startIndex, count, isAsc);
	}
	catch(ScoreStatisticDAOFinderException pde)
	{
		throw new ScoreStatisticSearchException(pde);
	}
	}

	/**
	 * search record by primKey SubjectID
	 * @param subjectId String the primarykey of Subject table
	 * return SubjectModel
	 */
	public ScoreStatisticModel getScoreStatistic(String scoreStatisticId)
	{
	ScoreStatisticModel sm = null;
	try
	{
		getDAO();
		sm = dao.getScoreStatistic(scoreStatisticId);
	} catch(Exception se)
	{
		System.out.println("getUser():" + se.getMessage());
	}
	return sm;
	}

	/**
	 * Create an instance of SubjectMgrDAO
	 */
	private void getDAO()
	{
	try
	{
		dao = new ScoreStatisticMgrDAO();
	}
	catch(Exception e)
	{
		String str = "Exception while SubjectMgrBean creating :" + e.getMessage();
		Debug.println(str);
		throw new EJBException(str);
	}
	}
}
