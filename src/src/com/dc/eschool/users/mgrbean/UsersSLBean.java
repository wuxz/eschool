package com.dc.eschool.users.mgrbean;

import java.rmi.*;
import java.util.Vector;
import java.util.Collection;
import javax.ejb.*;

import com.dc.eschool.users.dao.UsersMgrDAO;
import com.dc.eschool.users.ejb.*;
import com.dc.eschool.users.model.UsersModel;

import com.dc.eschool.users.exceptions.*;
import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * Stateless session Bean implementation for UsersEJB EJB.
 */

public class UsersSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private UsersMgrDAO dao = null;

    /**
     * the ejbCreate methods that does nothing
     */
    public void ejbCreate()
    {
    }

    /**
     * the ejbRemove methods
     */
    public void ejbRemove() throws RemoteException
    {
        dao = null;
    }

    /**
     * the ejbActivate methods that does nothing
     */
    public void ejbActivate() throws RemoteException
    {
    }

    /**
     * the ejbPassivate methods
     */
    public void ejbPassivate() throws RemoteException
    {
        dao = null;
    }

    /**
     * Sets the session context
     * @param sc the <code>SessionContext</code> for this instance
     */
    public void setSessionContext(SessionContext sessionContext) throws RemoteException
    {
        this.sessionContext = sessionContext;
    }

    /**
       * Insert an data to Users Table
       * @param pm a UsersModel that represents the properties.
       * @return an <code>Integer</code> that has the Users information
       *         corresponding to a Users account.
       * @exception <code>UcreateException</code> for irrecoverable errors
       */
    public void insertUsers(UsersModel pm) throws UCreateException
    {
	try
	{
	    UsersEBHome home = EJBUtil.getUsersEBHome();
	    UsersEB remote = home.create(pm);
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while usersClass in Users_MANAGER.");
	    throw new UCreateException(e);
	}
    }

    /**
     * Modify record
     * @param um UsersModel
     * @ecxeption UCreateException
     */
    public void updateUser(UsersModel um) throws UCreateException
    {
	try
	{
	    UsersEBHome home = EJBUtil.getUsersEBHome();
	    UsersEB remote = home.findByPrimaryKey(um.getUserID());
	    remote.setLoginName(um.getLoginName());
	    remote.setName(um.getName());
	    remote.setGender(um.getGender());
	    remote.setBirthday(um.getBirthday());
	    remote.setTel(um.getTel());
	    remote.setEmail(um.getEmail());
	    remote.setAddress(um.getAddress());
	    remote.setClassID(um.getClassID());
	    remote.setUserType(um.getUserType());
	    remote.setState(um.getState());
	    remote.setLastModifyBy(um.getLastModifyBy());
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new UCreateException(e);
	}
    }

    /**
     * Modify record
     * @param um UsersModel
     * @ecxeption UCreateException
     */
    public void updateUserByApp(UsersModel um) throws UCreateException
    {
	try
	{
	    UsersEBHome home = EJBUtil.getUsersEBHome();
	    UsersEB remote = home.findByPrimaryKey(um.getUserID());
	    remote.setName(um.getName());
	    remote.setGender(um.getGender());
	    remote.setBirthday(um.getBirthday());
	    remote.setTel(um.getTel());
	    remote.setUserType(um.getUserType());
	    remote.setLastModifyBy(um.getLastModifyBy());
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while createClass in ECLASS_MANAGER.");
	    throw new UCreateException(e);
	}
    }

    /**
       * remove record from Users by UserID
       * @param primKey int primaty key of record
       * @exception <code>UDeleteException</code>
       */
    public void deleteUsers(Integer primKey) throws UDeleteException
    {
	try
	{
	    UsersEBHome home = EJBUtil.getUsersEBHome();
	    UsersEB remote = home.findByPrimaryKey(primKey);
	    remote.remove();
	}
	catch(Exception e)
	{
	    Debug.print(e,"Exception while delete user from Users Table");
	    throw new UDeleteException(e);
	}
    }
    /**
       * Gets the data from Users Table by aptly SQL
       * @param clause a string that represents the SQL
       * @return the <code>ListChunk</code> that have the Users information
       *         corresponding to user accounts.     *
       * @exception <code>USearchException</code>
       */
    public ListChunk searchUsers(String clause, String value, int startIndex, int count) throws USearchException
    {
	try
	{
	    getDAO();
	    return dao.searchUsers(clause, value, startIndex, count);
	}
	catch(UDAOFindException pde)
	{
	    throw new USearchException(pde);
	}
    }

    public Collection searchUsersForApp(String courseID)
    {
      Collection collection=null;
	try
	{
	    getDAO();
	    collection= dao.searchUsersForApp(courseID);
	}
	catch(Exception pde)
	{
	    System.out.println(pde);
	}
    return collection;
    }

    /**
     * Get user by userID, the primary key of user table
     * @param userId String
     * @return UsersModel
     */
    public UsersModel getUser(String userId)
    {
	UsersModel um=new UsersModel();
	try
	{
	    UsersEBHome home = EJBUtil.getUsersEBHome();
	    um.setUserID(new Integer(userId));
	    UsersEB remote=home.findByPrimaryKey(new Integer(userId));
	    um=remote.getDetails();
	} catch(Exception se)
	{
	    System.out.println("getUser():" + se.getMessage());
	}
	return um;
    }

    /**
     * Indentify the login user
     */
    public UsersModel isLogin(String loginName,String password)
    {
	  UsersModel userModel=new UsersModel();
	  try
	  {
	      getDAO();
	      userModel=dao.isLogin(loginName,password);
	  } catch(Exception se)
	  {
	      System.out.println("getUser():SQLException while getting " +
			  "User " + loginName + " : " + se.getMessage());
	  }
	  return userModel;
      }

    /**
     * Modify the password
     */
    public void changePassword(Integer userID, String password)
    {
	  try
	  {
	      UsersEBHome home = EJBUtil.getUsersEBHome();
	      UsersEB remote = home.findByPrimaryKey(userID);
	      remote.setPassword(password);
	  } catch(Exception se)
	  {
	      System.out.println("getUser():SQLException while changing password " +
			  se.getMessage());
	  }
      }

    /**
     * Modify the allow field
     */
    public void changeAllow(Integer userID, String allow)
    {
	  try
	  {
	      UsersEBHome home = EJBUtil.getUsersEBHome();
	      UsersEB remote = home.findByPrimaryKey(userID);
	      remote.setAllow(allow);
	  } catch(Exception se)
	  {
	      System.out.println("getUser():SQLException while changing allow : " + se.getMessage());
	  }
      }

    /**
     * Create an instance of UsersMgrDAO
     */
    private void getDAO()
    {
	try
	{
	    dao = new UsersMgrDAO();
	}
	catch(Exception e)
	{
	    String str = "Exception while UsersMgrBean creating :" + e.getMessage();
	    Debug.println(str);
	    throw new EJBException(str);
	}
    }
}