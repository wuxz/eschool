package com.dc.eschool.sessiondata;

import java.rmi.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import javax.ejb.*;

import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.homeworkcontent.model.HWContentModel;
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
 * Stateless session Bean implementation for SessionSL EJB.
 */
public class SessionSLBean implements SessionBean
{
    private SessionContext sessionContext;
    private HashMap project;
    private HashMap homework;

    public SessionSLBean()
    {
	project= new HashMap();
	homework= new HashMap();
    }
    /**
     * the ejbCreate methods that does nothing
     */
    public void ejbCreate()
    {
        //selectDate=new Vector();
    }

    /**
     * the ejbRemove methods
     */
    public void ejbRemove() throws RemoteException
    {
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
    }

    /**
     * Sets the session context
     * @param sc the <code>SessionContext</code> for this instance
     */
    public void setSessionContext(SessionContext sessionContext) throws RemoteException
    {
    }

    public void addProject(ContentModel cm,String type)
    {
      HashMap search=new HashMap();
      HashMap pcMap=new HashMap();
      if(type.equals("search"))
      {
        if(project.containsKey("search"))
	{
	  search=(HashMap)project.get("search");
          if(search!=null)
          {
            if(search.containsKey(cm.getProjectID()))
            {
              pcMap=(HashMap)search.get(cm.getProjectID());
            }
          }
	}
        pcMap.put(cm.getProjectContentID(),cm.getContentID());
        search.put(cm.getProjectID(),pcMap);
        project.put("search",search);
      }else if(type.equals("upload"))
      {
        if(project.containsKey("upload"))
	{
	  search=(HashMap)project.get("upload");
          if(search!=null)
          {
            if(search.containsKey(cm.getProjectID()))
            {
              pcMap=(HashMap)search.get(cm.getProjectID());
            }
          }
	}
        pcMap.put(cm.getProjectContentID(),cm.getContentID());
        search.put(cm.getProjectID(),pcMap);
        project.put("upload",search);
      }
    }

    public ListChunk getProject(Integer projectID,String type)
    {
        ArrayList al=new ArrayList();
        int countAll=0;
        try
        {
            HashMap typeMap=new HashMap();
	    HashMap cmMap=new HashMap();
            typeMap=(HashMap) project.get(type);
            if(typeMap!=null)
            {
	      cmMap=(HashMap)typeMap.get(projectID);
              if(cmMap!=null)
              {
                ContentModel cm=new ContentModel();
                Set keySet=cmMap.keySet();
                Iterator iterator=keySet.iterator();
                while(iterator.hasNext())
                {
                  Integer key=(Integer)iterator.next();
                  String contentId=((Integer)cmMap.get(key)).toString();
                  cm=EJBUtil.getContentMgrHome().create().getContent(contentId);
                  cm.setProjectContentID(key);
                  al.add(cm);
                  countAll=countAll+1;
                }
              }
            }

        }catch(Exception e)
        {
          System.out.println(e);
        }
        return new ListChunk(countAll,al,0,countAll);
    }

    public void deleteProject(ContentModel cm)
    {
      try
      {
        HashMap typeMap=new HashMap();
        HashMap cmMap=new HashMap();
	typeMap = (HashMap)project.get("search");
        if(typeMap!=null)
        {
          cmMap=(HashMap)typeMap.get(cm.getProjectID());
          if(cmMap!=null)
          {
            if(cmMap.containsKey(cm.getProjectContentID()))
            {
              cmMap.remove(cm.getProjectContentID());
              typeMap.put(cm.getProjectID(),cmMap);
              project.put("search",typeMap);
            }
          }
        }

	typeMap = (HashMap)project.get("upload");
        if(typeMap!=null)
        {
          cmMap=(HashMap)typeMap.get(cm.getProjectID());
          if(cmMap!=null)
          {
            if(cmMap.containsKey(cm.getProjectContentID()))
            {
              cmMap.remove(cm.getProjectContentID());
              typeMap.put(cm.getProjectID(),cmMap);
              project.put("upload",typeMap);
            }
          }
        }
      }catch(Exception e)
      {
        System.out.println(e);
      }
    }

    public void addHomeWork(Integer homeWorkID,Integer homeWorkContentID)
    {
        HashMap search=new HashMap();
        if(homework.containsKey(homeWorkID))
	{
	  search=(HashMap)homework.get(homeWorkID);
	}
        search.put(homeWorkContentID,homeWorkContentID);
        homework.put(homeWorkID,search);
    }

    public ListChunk getHomeWork(Integer homeWorkID)
    {
        ArrayList al=new ArrayList();
        int countAll=0;
        try
        {
            HashMap typeMap=new HashMap();
	    HashMap cmMap=new HashMap();
            typeMap=(HashMap) homework.get(homeWorkID);

            if(typeMap!=null)
            {
                HWContentModel hwm=new HWContentModel();
                Set keySet=typeMap.keySet();
                Iterator iterator=keySet.iterator();
                while(iterator.hasNext())
                {

                  Integer key=(Integer)iterator.next();
                  String homeWorkContentId=((Integer)typeMap.get(key)).toString();
                  hwm=EJBUtil.getHWContentSLHome().create().getHomeWorkContent(homeWorkContentId);
                  al.add(hwm);
                  countAll=countAll+1;
                }
            }

        }catch(Exception e)
        {
          System.out.println(e);
        }
        return new ListChunk(countAll,al,0,countAll);
    }

    public void deleteHomeWork(Integer homeWorkID,Integer homeWorkContentID)
    {
      try
      {
        HashMap typeMap=new HashMap();
	typeMap = (HashMap)homework.get(homeWorkID);
        if(typeMap!=null)
        {
              typeMap.remove(homeWorkContentID);
              homework.put(homeWorkID,typeMap);
        }

      }catch(Exception e)
      {
        System.out.println(e);
      }
    }

    public void removeAll()
    {
      project.clear();
      homework.clear();
    }
}