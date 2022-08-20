package com.dc.eschool.users.mgrbean;

import java.rmi.*;
import java.util.Vector;
import java.util.Collection;
import javax.ejb.*;
import com.dc.eschool.users.exceptions.UCreateException;
import com.dc.eschool.users.exceptions.USearchException;
import com.dc.eschool.users.exceptions.UDeleteException;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.users.model.UsersModel;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * The remote interface of UsersSLBean(Statelesee Session bean)
 */
public interface UsersSL extends EJBObject
{
  public void changeAllow(Integer userID, String allow) throws RemoteException;
  public void changePassword(Integer userID, String password) throws RemoteException;
  public void deleteUsers(Integer primKey) throws UDeleteException, RemoteException;
  public UsersModel getUser(String userId) throws RemoteException;
  public void insertUsers(UsersModel pm) throws UCreateException, RemoteException;
  public UsersModel isLogin(String loginName, String password) throws RemoteException;
  public ListChunk searchUsers(String clause, String value, int startIndex, int count) throws USearchException, RemoteException;
  public Collection searchUsersForApp(String courseID) throws RemoteException;
  public void updateUser(UsersModel um) throws UCreateException, RemoteException;
  public void updateUserByApp(UsersModel um) throws UCreateException, RemoteException;

}