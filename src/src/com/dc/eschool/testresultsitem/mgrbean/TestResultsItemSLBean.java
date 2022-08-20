package com.dc.eschool.testresultsitem.mgrbean;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import javax.ejb.FinderException;
import javax.ejb.EJBException;
import javax.ejb.ObjectNotFoundException;

import java.util.Collection;
import java.util.Locale;
import java.util.HashMap;
import java.util.Vector;
import java.util.Enumeration;

import com.dc.eschool.testresultsitem.dao.*;
import com.dc.eschool.testresultsitem.model.TestResultsItemModel;
import com.dc.eschool.testresultsitem.ejb.*;
import com.dc.eschool.projectcontent.ejb.*;
import com.dc.eschool.projectcontent.model.ProjectContentModel;
import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.TestResultItemEvent;

import com.dc.eschool.examinationscore.ejb.ExaminationScoreEBHome;
import com.dc.eschool.examinationscore.ejb.ExaminationScoreEB;
import com.dc.eschool.examinationscore.model.ExaminationScoreModel;
import com.dc.eschool.scorestatistic.ejb.ScoreStatisticEBHome;
import com.dc.eschool.scorestatistic.ejb.ScoreStatisticEB;
import com.dc.eschool.scorestatistic.model.ScoreStatisticModel;


import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAOSysException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemCreateException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDAOFinderException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemSearchException;
import com.dc.eschool.testresultsitem.exceptions.TestResultsItemDeleteException;


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
 * Session Bean implementation of TestResultsItem
 *
 */
public class TestResultsItemSLBean implements SessionBean
{
	protected TestResultsItemMgrDAO dao;
	private SessionContext sessionContext;

	public void ejbCreate()
	{
		try
		{
			dao = new TestResultsItemMgrDAO();
		}
		catch(Exception e)
		{
		  String str = "Exception while TestResultsItemMgrBean creating :" + e.getMessage();
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
	public void insertTestResultsItem(Vector models) throws TestResultsItemCreateException
	{
	}
	 /**
	  * remove record from Class by ClassID
	  * @param primKey int primaty key of record
	  * @exception <code>SubjectDeleteException</code>
	  */
	  public void deleteTestResultsItem(Integer primKey) throws TestResultsItemDeleteException
	  {
		  try
		  {
			  TestResultsItemEBHome home = EJBUtil.getTestResultsItemEBHome();
			  TestResultsItemEB remote = home.findByPrimaryKey(primKey);
			  remote.remove();
		  }
		  catch(Exception e)
		  {
			  Debug.print(e,"Exception while delete Class from Class Table");
			  throw new TestResultsItemDeleteException(e);
		  }
	  }

	/**
	* update TestResultsItem record
	* @param sm TestResultsItemModel
	*/
	public void updateTestResultsItem(Vector models) throws TestResultsItemCreateException
	{
		if (models == null || models.size() == 0)
		   return;

		try
		{
			TestResultsItemEBHome testResultsItemHome = EJBUtil.getTestResultsItemEBHome();
			ExaminationScoreEBHome examinationscorehome = EJBUtil.getExaminationScoreEBHome();
			ScoreStatisticEBHome scorestatistichome = EJBUtil.getScoreStatisticEBHome();
			ProjectContentEBHome pchome = EJBUtil.getProjectContentEBHome();

			TestResultsItemModel tm = null;

			int rightAnswer = 0;
			int wrongAnswer = 0;

			for (int i = 0; i < models.size(); i ++)
			{
				tm = (TestResultsItemModel)models.elementAt(i);
				TestResultsItemEB testResultsItem = null;

				try
				{
					if (tm.getTestResultItemID() != null)
					{
						testResultsItem = testResultsItemHome.findByPrimaryKey(tm.getTestResultItemID());
						tm.setAnswer(testResultsItem.getAnswer());
					}
					else
						testResultsItem = testResultsItemHome.findByAnswerItemIDContentIDStudent(tm.getAnswerItemID().toString(), tm.getContentID().toString(), tm.getStudent().toString());

					testResultsItem.changeTestResultsItem(tm);
				}
				catch (FinderException e)
				{
					testResultsItem = testResultsItemHome.create(tm);
				}

				tm.setTestResultItemID(testResultsItem.getTestResultItemID());

				if(tm.getRight().equals("right"))
					rightAnswer++;
				else
					wrongAnswer++;

				ScoreStatisticModel sm = new ScoreStatisticModel();
				sm.setAnswerItemID(testResultsItem.getAnswerItemID() + "");
				sm.setCreateBy(tm.getLastModifyBy());
				sm.setLastModifyBy(tm.getLastModifyBy());
				sm.setStudent(tm.getStudent());

				ProjectContentEB pcremote = pchome.findByProjectContent(tm.getProjectID(), tm.getContentID());

				sm.setProjectContentID(pcremote.getDetails().getProjectContentID());

				TestResultsItemDAO itemdao = new TestResultsItemDAO();
				int arResults[] = itemdao.calcScoreStatistics(new Integer(sm.getAnswerItemID()));

				if (arResults != null)
				{
					sm.setRightAnswer(new Integer(arResults[0]));
					sm.setWrongAnswer(new Integer(arResults[1]));
					sm.setStatistic(arResults[0] * 1000 / (arResults[0] + arResults[1]) / 10f + "");

					ScoreStatisticEB scoreStatisticremote = null;

					try
					{
						scoreStatisticremote = scorestatistichome.findByAnswerItemID(sm.getAnswerItemID());
						scoreStatisticremote.changeScoreStatistic(sm);
					}
					catch (ObjectNotFoundException e)
					{
						scoreStatisticremote = scorestatistichome.create(sm);
					}
				}
			}

			int score = rightAnswer * 100 / (rightAnswer + wrongAnswer);
			System.out.println("score="+score);

			ExaminationScoreEB examinationscoreremote = null;
			ExaminationScoreModel esm = new ExaminationScoreModel(tm.getProjectID(),tm.getStudent(), new Integer(score),tm.getTestResultItemID(),tm.getLastModifyBy());

			try
			{
				examinationscoreremote = examinationscorehome.findByProjectStudent(tm.getProjectID(), tm.getStudent());
				examinationscoreremote.changeExaminationScore(esm);
			}
			catch (FinderException e)
			{
				examinationscoreremote = examinationscorehome.create(esm);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new TestResultsItemCreateException(e);
		}
	}

	  /**
	   * Gets the data from Class Table by aptly SQL
	   * @param clause a string that represents the SQL
	   * @return the <code>ListChunk</code> that have the Subject information
	   *         corresponding to class items.
	   * @exception <code>SubjectSearchException</code>
	   */
	  public ListChunk searchTestResultsItem(String clause, int startIndex, int count) throws TestResultsItemSearchException
	  {
		  try
		  {
			  getDAO();
			  System.out.println("in search =");
			  return dao.searchTestResultsItem(clause, startIndex, count);
		  }
		  catch(TestResultsItemDAOFinderException pde)
		  {
			  throw new TestResultsItemSearchException(pde);
		  }
	  }

	  /**
	   * search record by primKey SubjectID
	   * @param subjectId String the primarykey of Subject table
	   * return SubjectModel
	   */
	  public TestResultsItemModel getTestResultsItem(String testResultsItemId)
	  {
		  TestResultsItemModel tm = null;
		  try
		  {
			  getDAO();
			  tm = dao.getTestResultsItem(testResultsItemId);
		  } catch(Exception se)
		  {
			  System.out.println("getUser():" + se.getMessage());
		  }
		  return tm;
	  }

	  /**
	   * Create an instance of SubjectMgrDAO
	   */
	  private void getDAO()
	  {
		  try
		  {
			  System.out.println(" in getdao ");
			  dao = new TestResultsItemMgrDAO();
		  }
		  catch(Exception e)
		  {
			  String str = "Exception while TestResultsItemMgrBean creating :" + e.getMessage();
			  Debug.println(str);
			  throw new EJBException(str);
		  }
	  }
}
