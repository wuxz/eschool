package com.dc.eschool.system;

import java.util.*;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.DataModel.CourseInfoModel;

/**
 * Title:         �γ���Ϣ
 * Description:   ����������ÿγ̵���Ϣ��
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class CourseInfo implements CourseIF
{

   /* �û�ID   */
   private String IDForCourse;

   /* �γ����� */
   private String nameForCourse;

   /** ��ʼʱ�� */
   private String startDate;

   /** ����ʱ�� */
   private String endDate;

   /** �γ�˵�� */
   private String courseInfo;

   /** ״̬  */
   private String state;
   /**
    * ���õ�EJBʵ��
    */
   private EJBAccess ejbAccess;
   /**  SystemControl  */
   private SystemControl systemControl;

   /** ������   */
   public CourseInfo(SystemControl systemControl)
   {
       this.IDForCourse="";
       this.nameForCourse=null;
       this.startDate=null;
       this.endDate=null;
       this.courseInfo=null;
       this.state=null;
       this.systemControl=systemControl;
       if (systemControl.ejbAccess==null)
       {
           this.ejbAccess=new EJBAccess();
           systemControl.ejbAccess=this.ejbAccess;
       }
       else
       {
           this.ejbAccess=systemControl.ejbAccess;
       }
   }

   /** ��ÿγ̵�������Ϣ  */
   private void getCourseAllInfo(String courseID)
   {
       Collection tempCollection=ejbAccess.getCourseAllInfo(courseID);
       Iterator   iterator=tempCollection.iterator();
       if (iterator.hasNext())
       {
           CourseInfoModel tempModel=(CourseInfoModel)iterator.next();
           this.nameForCourse=tempModel.getCourseName();
           this.courseInfo=tempModel.getCourseInfo();
           this.startDate=tempModel.getStartDate();
           this.endDate=tempModel.getEndDate();
           this.state=tempModel.getState();
       }
   }

   /** ��ÿγ̵�����  */
   public String getCourseName(String courseID)
   {
      if (IDForCourse.equals(courseID))
          return nameForCourse;
      else
          {
              getCourseAllInfo(courseID);
              return  nameForCourse;
          }
   }

   /** ��ÿγ̵���ʼ���� */
   public String getStartDate(String courseID)
   {
       if (IDForCourse.equals(courseID))
          return startDate;
       else
          {
              getCourseAllInfo(courseID);
              return  startDate;
          }
   }

   /** ��ÿγ̵Ľ�������  */
   public String getEndDate(String courseID)
   {
       if (IDForCourse.equals(courseID))
          return endDate;
       else
          {
              getCourseAllInfo(courseID);
              return  endDate;
          }
   }

   /** �õ��γ̵�˵��  */
   public String getCourseInfo(String courseID)
   {
       if (IDForCourse.equals(courseID))
          return courseInfo;
       else
          {
              getCourseAllInfo(courseID);
              return  courseInfo;
          }
   }

   /** �õ��γ̵�״̬  */
   public String getCourseState(String courseID)
   {
       if (IDForCourse.equals(courseID))
          return state;
       else
          {
              getCourseAllInfo(courseID);
              return  state;
          }
   }
}
