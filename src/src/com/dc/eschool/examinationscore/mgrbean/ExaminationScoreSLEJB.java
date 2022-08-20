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
public class ExaminationScoreSLEJB implements SessionBean
{
    protected ExaminationScoreMgrDAO dao;

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

    public void setSessionContext(SessionContext sc) throws RemoteException {}

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

    public void destroy() throws RemoteException
    {
      dao = null;
    }

    public Integer createExaminationScore(ExaminationScoreModel em) throws ExaminationScoreCreateException
	{
	    try
	    {
	      ExaminationScoreEBHome home = EJBUtil.getExaminationScoreEBHome();
	      ExaminationScoreEB remote = home.create(em);
	      return (Integer)remote.getPrimaryKey();
	    }
	    catch(Exception e)
	    {
	      Debug.print(e,"Exception while createExaminationScore in EXAMINATIONSCORE_MANAGER.");
	      throw new ExaminationScoreCreateException(e);
	    }
	}

    public ListChunk searchExaminationScore(String clause, int startindex, int count) throws ExaminationScoreSearchException
    {
        try
        {
            return dao.searchExaminationScore(clause, startindex, count);
        }
        catch(ExaminationScoreDAOFinderException ade)
        {
          throw new ExaminationScoreSearchException(ade);
        }
    }
}
