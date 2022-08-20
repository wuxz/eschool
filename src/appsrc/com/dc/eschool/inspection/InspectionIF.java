package com.dc.eschool.inspection;

import java.util.*;
import javax.swing.ImageIcon;

/**
 * Title:         监视接口
 * Description:   该接口提供了监视部分的所有方法
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public interface InspectionIF
{

   /**让某一学生黑屏   */
   public void setDarkScreen(String studentID);

   /**
    * 让某一组或者几组学生黑屏，
    * 它会循环调用setDarkScreen(studentName:String)方法
    */
   public void setGroupDarkScreen(Vector studentID);

   /** 让所有学生黑屏,
     *  它会循环调用setDarkScreen(studentName:String)方法
     */
   public void setAllDarkScreen();

   /** 取消某一学生的黑屏  */
   public void cancelDarkScreen(String studentID);

   /**取消某一组学生的黑屏，需要循环调用cancelDarkScreen()方法  */
   public void cancelGroupDarkScreen(Vector studentID);

   /** 取消全体学生的黑屏，需要循环调用cancelDarkScreen()方法   */
   public void cancelAllDarkScreen();

   /** 警告某一学生   */
   public void warnStudent(String userID,String strContent);

   /** 警告学生  */
   public void warnStudent(Vector studentID,String strContent);

   /** 调用远程学生接口的方法开始抓屏
     *public void startCaptureScreen(String userName);
     */
   /** 接受学生端传来的屏幕图片，并播放   */
   public void reciveScreen(String userID);

   /** 取消学生端的抓屏程序
     *  public void cancelCaptureScreen(String userName);
     */
   /** 老师询问学生是否认真听讲   */
   public void hasCome(String userID);

   /**   老师询问一组学生是否在认真听讲   */
   public void hasCome(Vector studentID);

   /** 学生告诉老师认真听讲 */
   public void alreadyCome(String userID);

   /** 抓取学生的屏幕   */
   public ImageIcon CaptureScreen(String userID);

   /** 学生抓取自己的屏幕  */
   public ImageIcon selfCaptureScreen();

   /** 让学生退出课堂     */
   public void  exitClass(String userID);
}
