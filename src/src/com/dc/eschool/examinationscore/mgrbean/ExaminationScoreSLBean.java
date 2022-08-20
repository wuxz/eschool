package com.dc.eschool.examinationscore.mgrbean;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import javax.ejb.FinderException;
import javax.ejb.EJBException;

import java.util.Collection;
import java.util.Locale;

import com.dc.eschool.examinationscore.dao.ExaminationScoreMgrDAO;
import com.dc.eschool.examinationscore.model.ExaminationScoreModel;

import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAOSysException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreCreateException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreSearchException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDAOFinderException;
import com.dc.eschool.examinationscore.exceptions.ExaminationScoreDeleteException;

import com.dc.eschool.examinationscore.ejb.*;

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
 * Session Bean implementation of ExaminationScore
 *
 */
public class ExaminationScoreSLBean implements SessionBean
{
    protected ExaminationScoreMgrDAO dao;
    private SessionContext sessionContext;

    public void ejbCreate()
    {
        try
        {
            dao = new ExaminationScoreMgrDAO();
        }
        catch(Exception e)
        {
          String str = "Exception while ExaminationScoreMgrBean creating :" + e.getMessage();
          Debug.println(str);
          throw new EJBException(str);
        }
    }

    public void setSessionContext(SessionContext sessionContext) throws RemoteException
    {
        this.sessionContext = sessionContext;
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
    public void insertExaminationScore(ExaminationScoreModel em) throws ExaminationScoreCreateException
    {
	try
	{
	    ExaminationScoreEBHome home = EJBUtil.getExaminationScoreEBHome();
	    ExaminationScoreEB remote = home.create(em);
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in EXAMINATIONSCORE_MANAGER.");
	    throw new ExaminationScoreCreateException(e);
	}
    }

    /**
     * remove record from Class by ClassID
     * @param primKey int primaty key of record
     * @exception <code>SubjectDeleteException</code>
     */
    public void deleteExaminationScore(Integer primKey) throws ExaminationScoreDeleteException
    {
	try
	{
	    ExaminationScoreEBHome home = EJBUtil.getExaminationScoreEBHome();
	    ExaminationScoreEB remote = home.findByPrimaryKey(primKey);
	    remote.remove();
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while delete Class from Class Table");
	    throw new ExaminationScoreDeleteException(e);
	}
    }

    /**
     * update subject record
     * @param sm SubjectModel
     */
    public void updateExaminationScore(ExaminationScoreModel em) throws ExaminationScoreCreateException
    {
	try
	{
	    ExaminationScoreEBHome home = EJBUtil.getExaminationScoreEBHome();
	    ExaminationScoreEB remote = home.findByPrimaryKey(em.getExaminationID());
	    remote.changeExaminationScore(em);
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in EXAMINATIONSCORE_MANAGER.");
	    throw new ExaminationScoreCreateException(e);
	}
    }

    /**
     * Gets the data from Class Table by aptly SQL
     * @param clause a string that represents the SQL
     * @return the <code>ListChunk</code> that have the Subject information
     *         corresponding to class items.
     * @exception <code>SubjectSearchException</code>
     */
    public ListChunk searchExaminationScore(String clause, int startIndex, int count) throws ExaminationScoreSearchException
    {
	try
	{
	    getDAO();
	    return dao.searchExaminationScore(clause, startIndex, count);
	}
	catch(ExaminationScoreDAOFinderException pde)
	{
	    throw new ExaminationScoreSearchException(pde);
	}
    }

    /**
     * search record by primKey SubjectID
     * @param subjectId String the primarykey of Subject table
     * return SubjectModel
     */
    public ExaminationScoreModel getExaminationScore(String examinationId)
    {
	ExaminationScoreModel em = null;
	try
	{
	    getDAO();
	    em = dao.getExaminationScore(examinationId);
	} catch(Exception se)
	{
	    System.out.println("getUser():" + se.getMessage());
	}
	return em;
    }

    /**
     * Create an instance of SubjectMgrDAO
     */
    private void getDAO()
    {
	try
	{
	    dao = new ExaminationScoreMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while ExaminationScoreMgrBean creating :" + e.getMessage();
	    Debug.println(str);
	    throw new EJBException(str);
	}
    }
}
