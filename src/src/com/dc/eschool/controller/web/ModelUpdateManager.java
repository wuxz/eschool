package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.ejb.EJBException;
import com.dc.eschool.util.*;
import com.dc.eschool.controller.event.*;



/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * This class uses the MainEvent type to deduce the list of
 * models that need to be updated because of this event.
 * @author Eric
 */
public class ModelUpdateManager implements Serializable
{

  public ModelUpdateManager()
  {
  }
  public Collection getUpdatedModels(MainEvent ese)
  {
       Debug.println("ModelUpdateManager: getUpdateModels");
       ArrayList modelList = new ArrayList();

	/* attention following codes. add listener as event type.*/
	// Event drive Ejb Methods just like create,remove or modifiers.

	if(ese instanceof ProjectEvent)
	{
	  Debug.println("ModelUpdateMnager: ProjectEvent");
	  modelList.add(JNDINames.PROJECT_EJBHOME);
          modelList.add(JNDINames.EJBCTRL_EJBHOME);
	}else if(ese instanceof UsersEvent)
	{
	  Debug.println("ModelUpdateManager: UsersEvent");
	  modelList.add(JNDINames.USERS_MANAGER_EJBHOME);
	}else if(ese instanceof SigninEvent)
	{
	  Debug.println("ModelUpdateManager: SigninEvent");
	  modelList.add(JNDINames.USERS_MANAGER_EJBHOME);
          modelList.add(JNDINames.EJBCTRL_EJBHOME);
	}else if(ese instanceof EClassEvent)
	{
	  Debug.println("ModelUpdateManager: EClassEvent");
	  modelList.add(JNDINames.ECLASS_MANAGER_EJBHOME);
	}else if(ese instanceof SubjectEvent)
	{
	  Debug.println("ModelUpdateManager: SubjectEvent");
	  modelList.add(JNDINames.SUBJECT_MANAGER_EJBHOME);
	}else if(ese instanceof CourseEvent)
	{
	  Debug.println("ModelUpdateManager: CourseEvent");
	  modelList.add(JNDINames.COURSE_MANAGER_EJBHOME);
	}else if(ese instanceof CourseStudentEvent)
	{
	  Debug.println("ModelUpdateManager: CourseStudentEvent");
	  modelList.add(JNDINames.COURSESTUDENT_MANAGER_EJBHOME);
	}else if(ese instanceof AnswerItemEvent)
	{
	  Debug.println("ModelUpdateManager: AnswerItemEvent");
	  modelList.add(JNDINames.ANSWERITEM_MANAGER_EJBHOME);
	}else if(ese instanceof HomeWorkEvent)
	{
	  Debug.println("ModelUpdateManager: HomeWorkEvent");
	  modelList.add(JNDINames.HOMEWORK_MANAGER_EJBHOME);
	}
        else if(ese instanceof ContentEvent)
        {
          Debug.println("ModelUpdateManager: ContentEvent");
          modelList.add(JNDINames.CONTENTMGR_EJBHOME);
        }else if(ese instanceof SchoolResourceEvent)
        {
          Debug.println("ModelUpdateManager: SchoolResourceEvent");
          modelList.add(JNDINames.SCHOOLRESOURCE_MANAGEE_EJBHOME);
        }
	/*
	if (ese instanceof SOMEEvent)
	{
		Debug.println("ModelUpdateManager: SOMEEvent");
		modelList.add(JNDINames.SOME_EJBHOME);
	}
	else if (ese instanceof ANOTHEREvent)
	{
		Debug.println("ModelUpdateManager: ANOTHEREvent");
		modelList.add(JNDINames.ANOTHER_EJBHOME);
	}
	*/
	return modelList;
    }
}