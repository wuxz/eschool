package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;

import com.dc.eschool.eclass.dao.EClassMgrDAO;
import com.dc.eschool.eclass.exceptions.*;
import com.dc.eschool.eclass.mgrbean.*;
import com.dc.eschool.eclass.model.EClassModel;
import com.dc.eschool.eclass.ejb.*;

import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class EClassSearchWebImpl implements Serializable
{
    private EClassSLHome home = null;

    public EClassSearchWebImpl()
    {
    }

    public ListChunk searchEClass(String clause, int startindex, int count)
    {
        ListChunk lc = null;
        try
        {
            lc = getManager().searchEClass(clause, startindex, count);
        }
        catch(Exception e)
        {
            String str = e.getMessage();
            Debug.println(str);
        }
        return lc;
    }

    public EClassModel getEClass(String classId)
    {
        EClassModel um=new EClassModel();
        try
        {
            um=getManager().getEClass(classId);
        } catch(Exception se)
        {
            System.out.println("getUser():" + se.getMessage());
        }
        return um;
    }

    private EClassSL getManager() throws ControllerException
    {
        EClassSL remote = null;
        try
        {
            if(home == null)
              home = EJBUtil.getEClassSLHome();
            remote = home.create();
        }
        catch(javax.naming.NamingException ne)
        {
            String str = "NamingException while get manager: "+ne.getMessage();
            Debug.println(str);
            throw new ControllerException(str);
        }
        catch(javax.ejb.CreateException ce)
        {
            String str = "CreateException while get manager: "+ce.getMessage();
            Debug.println(str);
            throw new ControllerException(str);
        }
        catch(java.rmi.RemoteException re)
        {
            String str = "RemoteException while get manager: "+re.getMessage();
            Debug.println(str);
            throw new ControllerException(str);
        }
        catch(Exception e)
        {
            String str = "unknown Exception while get manager: "+e.getMessage();
            Debug.println(str);
            throw new ControllerException(str);
        }
        return remote;
    }
}