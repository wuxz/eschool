package com.dc.eschool.homeworkcontent.ejb;

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

import com.dc.eschool.homeworkcontent.dao.HWContentDAO;
import com.dc.eschool.homeworkcontent.model.HWContentModel;

import com.dc.eschool.homeworkcontent.exceptions.HCAppException;
import com.dc.eschool.homeworkcontent.exceptions.HCDAOAppException;
import com.dc.eschool.homeworkcontent.exceptions.HCDAODBUpException;
import com.dc.eschool.homeworkcontent.exceptions.HCDAODuKeyException;
import com.dc.eschool.homeworkcontent.exceptions.HCDAOFindException;
import com.dc.eschool.homeworkcontent.exceptions.HCDAOSysException;

import com.dc.eschool.util.Debug;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class HWContentEBBean extends HWContentModel implements EntityBean
{
  private EntityContext entityContext;
  private transient HWContentDAO dao;

  public Integer ejbCreate(HWContentModel hcm) throws CreateException, RemoteException
  {
	// set the instance data
	try
        {
	  getDAO();
	  dao.setHcm(hcm);
	  dao.create();
	  this.copy(dao.getHcm());
	  return (this.homeWorkContentID);
	}catch(Exception e)
        {
	  String str = "Exception while create ProjectEB: " + e.getMessage();
	  Debug.println(str);
	  throw new CreateException(str);
	}
  }

  public void ejbPostCreate(HWContentModel hcm) throws CreateException, RemoteException
  {
  }

  public void ejbLoad() throws RemoteException
  {
      try
      {
	  getDAO();
	  dao.getHcm().setHomeWorkContentID(this.homeWorkContentID);
	  dao.load();
	  this.copy(dao.getHcm());
      } catch (HCDAOFindException se)
      {
	  throw new EJBException (se.getMessage());
      } catch (HCDAOSysException acs)
      {
	  throw new EJBException(acs.getMessage());
      }
  }

  public void ejbStore() throws RemoteException
  {
      try
      {
	  getDAO();
	  dao.getHcm().copy(this);
	  dao.store();
	  dao.getHcm();
      } catch (HCDAOAppException se)
      {
		throw new EJBException (se.getMessage());
      } catch (HCDAOSysException acs)
      {
		throw new EJBException(acs.getMessage());
      }
  }

  public void ejbRemove() throws RemoveException, RemoteException
  {
      try
      {
	  getDAO();
	  dao.getHcm().setHomeWorkContentID(this.homeWorkContentID);
	  dao.remove();
      } catch (HCDAODBUpException se)
      {
	  entityContext.setRollbackOnly();
	  throw new RemoveException (se.getMessage());
      } catch (HCDAOSysException acs)
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
	  this.homeWorkContentID = dao.findByPrimaryKey(primKey);
	  dao.getHcm();
	  return(this.homeWorkContentID);
	} catch (HCDAOFindException se)
        {
	  throw new FinderException (se.getMessage());
	} catch (HCDAOSysException acs)
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
		dao = new HWContentDAO();
	  }
      }catch(Exception e)
      {
	  String str = "Exception while create HWContentEB: " + e.getMessage();
	  System.out.println(str) ;
      }
  }

  public HWContentModel getDetails()
  {
      getDAO();
      return dao.getHcm();
  }
}