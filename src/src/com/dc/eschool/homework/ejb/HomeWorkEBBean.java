package com.dc.eschool.homework.ejb;

import java.rmi.RemoteException;

import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.ejb.DuplicateKeyException;
import javax.naming.NamingException;
import javax.naming.InitialContext;

import com.dc.eschool.controller.exception.DAOException;

import com.dc.eschool.homework.dao.HomeWorkDAO;
import com.dc.eschool.homework.model.HomeWorkModel;

import com.dc.eschool.homework.exceptions.HWAppException;
import com.dc.eschool.homework.exceptions.HWDAOAppException;
import com.dc.eschool.homework.exceptions.HWDAODBUpException;
import com.dc.eschool.homework.exceptions.HWDAODuKeyException;
import com.dc.eschool.homework.exceptions.HWDAOFindException;
import com.dc.eschool.homework.exceptions.HWDAOSysException;

import com.dc.eschool.util.Debug;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * Implementation of homeWork as an Entity Bean
 */
public class HomeWorkEBBean extends HomeWorkModel implements EntityBean
{
  private EntityContext entityContext;
  private transient HomeWorkDAO dao;

  public Integer ejbCreate(HomeWorkModel hm) throws CreateException, RemoteException
  {
    // set the instance data
    try
    {
      getDAO();
      dao.setHm(hm);
      dao.create();
      this.copy(dao.getHm());
      return (this.homeWorkID);
    }catch(Exception e)
    {
      String str = "Exception while create ProjectEB: " + e.getMessage();
      Debug.println(str);
      throw new CreateException(str);
    }
  }

  public void ejbPostCreate(HomeWorkModel hm) throws CreateException, RemoteException
  {
  }

  public void ejbLoad() throws RemoteException
  {
    try
    {
      getDAO();
      dao.getHm().setHomeWorkID(this.homeWorkID);
      dao.load();
      this.copy(dao.getHm());
    } catch (HWDAOFindException se)
    {
      throw new EJBException (se.getMessage());
    } catch (HWDAOSysException acs)
    {
      throw new EJBException(acs.getMessage());
    }
  }

  public void ejbStore() throws RemoteException
  {
    try
    {
      getDAO();
      dao.getHm().copy(this);
      dao.store();
      dao.getHm();
    } catch (HWDAOAppException se)
    {
      throw new EJBException (se.getMessage());
    } catch (HWDAOSysException acs)
    {
      throw new EJBException(acs.getMessage());
    }
  }

  public void ejbRemove() throws RemoveException, RemoteException
  {
    try
    {
      getDAO();
      dao.getHm().setHomeWorkID(this.homeWorkID);
      dao.remove();
    } catch (HWDAODBUpException se)
    {
      entityContext.setRollbackOnly();
      throw new RemoveException (se.getMessage());
    } catch (HWDAOSysException acs)
    {
      throw new EJBException(acs.getMessage());
    }
  }

  public void ejbActivate() throws RemoteException
  {
  }

  public void ejbPassivate() throws RemoteException
  {
    this.dao = null;
  }

  public void setEntityContext(EntityContext entityContext) throws RemoteException
  {
    this.entityContext = entityContext;
  }

  public void unsetEntityContext() throws RemoteException
  {
    entityContext = null;
  }
  public Integer ejbFindByPrimaryKey(Integer primKey) throws FinderException
  {
    try
    {
      getDAO();
      this.homeWorkID = dao.findByPrimaryKey(primKey);
      dao.getHm();
      return(this.homeWorkID);
    } catch (HWDAOFindException se)
    {
      throw new FinderException (se.getMessage());
    } catch (HWDAOSysException acs)
    {
      throw new EJBException(acs.getMessage());
    }
  }

  private void getDAO()
  {
    try
    {
      if(dao == null)
      {
        dao = new HomeWorkDAO();
      }
    }catch(Exception e)
    {
      String str = "Exception while create ProjectEB: " + e.getMessage();
      System.out.println(str) ;
    }
  }

  public HomeWorkModel getDetails()
  {
    getDAO();
    return dao.getHm();
  }
}