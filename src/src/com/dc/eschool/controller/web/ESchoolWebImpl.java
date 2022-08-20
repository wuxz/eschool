package com.dc.eschool.controller.web;

import java.util.Vector;
import java.util.ArrayList;

import com.dc.eschool.controller.web.ModelManager;
import com.dc.eschool.controller.web.ModelUpdateListener;
import com.dc.eschool.controller.exception.ListenerException;
import com.dc.eschool.controller.ejb.EJBControllerSF;
import com.dc.eschool.controller.web.StateMachine;
import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JNDINames;
import com.dc.eschool.util.ListChunk;
import com.dc.eschool.util.EJBUtil;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ESchoolWebImpl implements ModelUpdateListener,java.io.Serializable
{
  private ModelManager mm;
  private Integer projectID=new Integer(0);
  private String userType="";
  private EJBControllerSF ejbController;
  private Integer userID=new Integer(0);
  private String docURL="";
  private String docName="";
  private String projectType="";
  private Integer courseID=new Integer(0);
  private String courseName="";
  private String projectName="";
  private Vector selectDate=new Vector();

  public ESchoolWebImpl()
  {
  }

  public ESchoolWebImpl(ModelManager mm)
  {
    try
    {
      this.mm = mm;
      mm.addListener(JNDINames.EJBCTRL_EJBHOME,this);
    }catch(Exception e)
    {
      System.out.println(e);
    }
  }

  public Integer getProjectID()
  {
    return projectID;
  }
  public void setProjectID(Integer projectID)
  {
    this.projectID = projectID;
  }
  public void setUserType(String userType)
  {
    this.userType = userType;
  }
  public String getUserType()
  {
    return userType;
  }
    public void performUpdate()
    {
        StateMachine machine;
        try {
            if (ejbController == null)
            {
                ejbController = mm.getECEJB();
            }
            if (ejbController != null)
            {
                machine=ejbController.getSm();

                if (machine.getAttribute(WebKeys.ProjectIDKey)!=null)
                    this.projectID=(Integer)machine.getAttribute(WebKeys.ProjectIDKey);
                if (machine.getAttribute(WebKeys.UserTypeKey)!=null)
                    this.userType=(String)machine.getAttribute(WebKeys.UserTypeKey);
                if (machine.getAttribute(WebKeys.UserIDKey)!=null)
                    this.userID=(Integer)machine.getAttribute(WebKeys.UserIDKey);
                if (machine.getAttribute(WebKeys.DocURLKey)!=null)
                    this.userID=(Integer)machine.getAttribute(WebKeys.DocURLKey);
            }
         } catch(Exception e) {
            System.out.println("*** UsersWebImpl: preformUpdate caught " + e);
        }
    }
  public void setUserID(Integer userID)
  {
    this.userID = userID;
  }
  public Integer getUserID()
  {
    return userID;
  }
  public void setDocURL(String docURL)
  {
    this.docURL = docURL;
  }
  public String getDocURL()
  {
    return docURL;
  }
  public void setDocName(String docName)
  {
    this.docName = docName;
  }
  public String getDocName()
  {
    return docName;
  }
  public void setProjectType(String projectType)
  {
    this.projectType = projectType;
  }
  public String getProjectType()
  {
    return projectType;
  }
  public void setCourseID(Integer courseID)
  {
    this.courseID = courseID;
  }
  public Integer getCourseID()
  {
    return courseID;
  }
  public void setCourseName(String courseName)
  {
    this.courseName = courseName;
  }
  public String getCourseName()
  {
    return courseName;
  }
  public void setProjectName(String projectName)
  {
    this.projectName = projectName;
  }
  public String getProjectName()
  {
    return projectName;
  }
}