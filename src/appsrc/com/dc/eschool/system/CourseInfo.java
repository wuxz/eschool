package com.dc.eschool.system;

import java.util.*;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.DataModel.CourseInfoModel;

/**
 * Title:         课程信息
 * Description:   该类用来获得课程的信息。
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class CourseInfo implements CourseIF
{

   /* 用户ID   */
   private String IDForCourse;

   /* 课程名称 */
   private String nameForCourse;

   /** 开始时间 */
   private String startDate;

   /** 结束时间 */
   private String endDate;

   /** 课程说明 */
   private String courseInfo;

   /** 状态  */
   private String state;
   /**
    * 调用的EJB实例
    */
   private EJBAccess ejbAccess;
   /**  SystemControl  */
   private SystemControl systemControl;

   /** 构造器   */
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

   /** 获得课程的所有信息  */
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

   /** 获得课程的名称  */
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

   /** 获得课程的起始日期 */
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

   /** 获得课程的结束日期  */
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

   /** 得到课程的说明  */
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

   /** 得到课程的状态  */
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
