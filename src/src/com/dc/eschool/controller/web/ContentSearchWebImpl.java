package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import javax.ejb.FinderException;

import com.dc.eschool.content.dao.ContentMgrDAO;
import com.dc.eschool.content.mgrbean.*;

import com.dc.eschool.content.ejb.*;
import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.content.exceptions.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ContentSearchWebImpl implements Serializable
{
  private ContentMgrSLHome home = null;

  public ContentSearchWebImpl() {}
  private ContentMgrSL getManager() throws ControllerException
  {
	ContentMgrSL remote = null;
	try
	{
	  if(home == null)
		home = EJBUtil.getContentMgrHome();

	  remote = home.create();
	}
	catch(Exception e)
	{
	  String str = "unknown Exception while get manager: "+e.getMessage();
	  Debug.println(str);
	  throw new ControllerException(str);
	}
	return remote;
  }
  public ListChunk searchContents(String clause, int startindex, int count,String value)
  {
	ListChunk lc = null;
	try
	{
	  lc = getManager().searchContents(clause,startindex,count,value);
	}
	catch(Exception e)
	{
	  String str = e.getMessage();
	  Debug.println(str);
	}

	return lc;

  }
  public ListChunk searchPC(String clause, int startindex, int count,String value,String info,String time)
  {
	ListChunk lc = null;
	try
	{
	  lc = getManager().searchPC(clause,startindex,count,value,info,time);
	}
	catch(Exception e)
	{
	  String str = e.getMessage();
	  Debug.println(str);
	}

	return lc;

  }

  public ContentModel getContentDetails(String pk)
  {
	ContentModel cm = new ContentModel();
	try
	{
	  cm = getManager().getContent(pk);
	}
	catch(Exception e)
	{
	  Debug.print(e);
	}
	return cm;
  }
  public boolean changeInfo(String userType, String userID, Integer contentID,String info,String state)
  {
	try
	{
		if (!"π‹¿Ì‘±".equals(userType))
		{
			ContentMgrSL remote = getManager();
			ContentModel cm = remote.getContent(contentID.toString());
			if (!cm.getCreateBy().toString().equals(userID))
				return false;
		}

		getManager().changeInfo(contentID,InterpretSQL.transform(info),InterpretSQL.transform(state));
	}catch(Exception e)
	{
	  System.out.println(e);
	}

	return true;
  }
}