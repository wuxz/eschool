package com.dc.eschool.system;

/**
 * Title:         课程信息
 * Description:   课程信息接口，该接口提供与课程信息相关的各种方法。
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public interface CourseIF
{

   /** 取得课程的名称  */
   public String getCourseName(String courseID);

   /** 取得课程的开始日期 */
   public String getStartDate(String courseID);

   /** 取得课程结束日期 */
   public String getEndDate(String courseID);

   /**  获得课程的信息 */
   public String getCourseInfo(String courseID);

   /** 获得课程的状态  */
   public String getCourseState(String courseID);
}
