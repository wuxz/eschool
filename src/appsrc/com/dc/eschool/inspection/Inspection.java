package com.dc.eschool.inspection;

import java.util.*;
import java.awt.* ;
import java.awt.image.* ;
import javax.swing.ImageIcon;
import java.io.* ;
import com.sun.image.codec.jpeg.* ;

import com.dc.eschool.system.Student;
import com.dc.eschool.system.Teacher;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.DrawImageFrm;

/**
 * Title:         监视类
 * Description:   该类实现了监视接口的所有方法，并且调用其他的接口或类来协调实现。
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class Inspection implements InspectionIF
{
   private static  int  maxWidth=800;
   private static  int  maxHeight=600;
   /**    SystemControl实例  */
   private SystemControl      systemControl;
   /**    老师远程接口    */
   private Teacher            ITeacher;
   /**    学生远程接口    */
   private Student            IStudent;
   /**    浏览图片窗口    */
   private DrawImageFrm       drawImageFrm;

   /**  构造器  */
   public Inspection(SystemControl systemControl)
   {
        this.systemControl=systemControl;
   }

   /** 显示抓取的学生的屏幕   */
   private void viewPhoto(ImageIcon image)
   {
       drawImageFrm=new DrawImageFrm(image);
       drawImageFrm.setSize(maxWidth,maxHeight);
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       drawImageFrm.setLocation(new Double((screenSize.getWidth()-maxWidth)/2).intValue(),
                                new Double((screenSize.getHeight()-maxHeight)/2).intValue());
       drawImageFrm.setVisible(true);
   }

   /**  使某一学生黑屏   */
   public void setDarkScreen(String studentID)
   {
        Hashtable    htStudentIDInterface=null;
        try
        {
            htStudentIDInterface=systemControl.getHtStudentIDInterface();
            IStudent=(Student)htStudentIDInterface.get(studentID);
            if (IStudent!=null)
            {
                IStudent.createDarkWindow();
            }
            else
            {
                systemControl.writeLog("在让某一学生黑屏时,没有找到远程接口,远程调用失败！");
            }
        }
        catch (Exception e)
        {
            systemControl.writeLog("在让某一学生黑屏时,远程调用失败！");
        }
   }

   /** 使一群学生黑屏   */
   public void setGroupDarkScreen(Vector studentID)
   {
        for(int i=0;i<studentID.size();i++)
        {
            setDarkScreen((String)studentID.get(i));
        }
   }

   /** 使所有学生黑屏   */
   public void setAllDarkScreen()
   {
       Hashtable    htStudentIDInterface=null;
       htStudentIDInterface=systemControl.getHtStudentIDInterface();
       if (htStudentIDInterface.size()==0)
       {
           systemControl.writeLog("在使所有学生黑屏时,由于没有找到学生的接口,调用失败！");
           return;
       }
       try
       {
           for (Enumeration eInterface = htStudentIDInterface.elements();
                eInterface.hasMoreElements();
               )
           {
               IStudent=(Student)eInterface.nextElement();
               if (IStudent!=null)
               {
                   IStudent.createDarkWindow();
               }
               else
               {
                   systemControl.writeLog("在全体黑屏时,没有找到远程接口,远程调用失败！");
               }
           }
        }
        catch(Exception e)
        {
            systemControl.writeLog("在全体黑屏时,远程调用失败！");
        }
   }

   /** 取消黑屏   */
   public void cancelDarkScreen(String studentID)
   {
        Hashtable    htStudentIDInterface=null;
        try
        {
            htStudentIDInterface=systemControl.getHtStudentIDInterface();
            IStudent=(Student)htStudentIDInterface.get(studentID);
            if (IStudent!=null)
            {
                IStudent.deleteDarkWindow();
            }
            else
            {
                systemControl.writeLog("在取消某一学生黑屏时,没有找到远程接口,远程调用失败！");
            }
        }
        catch (Exception e)
        {
            systemControl.writeLog("在取消某一学生黑屏时,远程调用失败！");
        }
   }

   /** 取消一组学生的黑屏   */
   public void cancelGroupDarkScreen(Vector studentID)
   {
        for(int i=0;i<studentID.size();i++)
        {
            cancelDarkScreen((String)studentID.get(i));
        }
   }

   /** 取消所有学生的黑屏   */
   public void cancelAllDarkScreen()
   {
       Hashtable    htStudentIDInterface=null;
       htStudentIDInterface=systemControl.getHtStudentIDInterface();
       if (htStudentIDInterface.size()==0)
       {
           systemControl.writeLog("在取消所有学生黑屏时,由于没有找到学生的接口,调用失败！");
           return;
       }
       try
       {
           for (Enumeration eInterface = htStudentIDInterface.elements();
                eInterface.hasMoreElements();
               )
           {
               IStudent=(Student)eInterface.nextElement();
               if (IStudent!=null)
               {
                   IStudent.deleteDarkWindow();
               }
               else
               {
                   systemControl.writeLog("在取消全体黑屏时,没有找到远程接口,远程调用失败！");
               }
           }
        }
        catch(Exception e)
        {
            systemControl.writeLog("在取消全体黑屏时,远程调用失败！");
        }
   }

   /** 警告学生  */
   public void warnStudent(String userID,String strContent)
   {
        Hashtable    htStudentIDInterface=null;
        htStudentIDInterface=systemControl.getHtStudentIDInterface();
        IStudent=(Student)htStudentIDInterface.get(userID);
        try
        {
             if (IStudent!=null)
             {
                 IStudent.showWarnMessage(strContent);
             }
             else
             {
                 systemControl.writeLog("在警告时,没有取得学生的远程接口,调用失败！");
             }
        }
        catch(Exception e)
        {
            systemControl.writeLog("在警告时,远程调用失败！");
        }
   }


   /** 警告学生  */
   public void warnStudent(Vector studentID,String strContent)
   {
        /**   用户ID    */
        String       userID;
        Hashtable    htStudentIDInterface=null;
        htStudentIDInterface=systemControl.getHtStudentIDInterface();
        for (int i=0;i<studentID.size();i++)
        {
            userID=(String)studentID.get(i);
            IStudent=(Student)htStudentIDInterface.get(userID);
            try
            {
                 if (IStudent!=null)
                 {
                     IStudent.showWarnMessage(strContent);
                 }
                 else
                 {
                     systemControl.writeLog("在警告时,没有取得学生的远程接口,调用失败！");
                 }
            }
            catch(Exception e)
            {
                systemControl.writeLog("在警告时,远程调用失败！");
            }
        }
   }

   /** 接受屏幕   */
   public void reciveScreen(String userID)
   {
       ImageIcon  image=CaptureScreen(userID);
       viewPhoto(image);
   }

   /** 老师询问学生是否认真听讲   */
   public void hasCome(String userID)
   {
        Hashtable    htStudentIDInterface=null;
        htStudentIDInterface=systemControl.getHtStudentIDInterface();
        IStudent=(Student)htStudentIDInterface.get(userID);
        if (IStudent==null)
        {
            systemControl.writeLog("在点名时,没有取得学生的远程接口,调用失败！");
            return;
        }
        try
        {
             IStudent.hasCome();
        }
        catch(Exception e)
        {
             systemControl.writeLog("在点名时,远程调用失败！");
        }
   }
   /**   老师询问一组学生是否在认真听讲   */
   public void hasCome(Vector studentID)
   {
        String   userID;
        if(studentID==null)
        {
            systemControl.writeLog("老师在点名时,由于没有取到学生的信息,点名失败");
            return;
        }
        for (int i=0;i<studentID.size();i++)
        {
            userID=(String)studentID.get(i);
            hasCome(userID);
        }
   }

   /** 学生回答老师  */
   public void alreadyCome(String userID)
   {
        ITeacher=systemControl.getTeacherInterface();
        if (ITeacher==null)
        {
            systemControl.writeLog("在学生回应老师的的点名时,没有找到老师的远程接口,调用失败");
            return;
        }
        try
        {
            ITeacher.alreadyCome(userID);
        }
        catch(Exception e)
        {
            systemControl.writeLog("在学生回应老师的的点名时,远程调用失败");
        }
   }

   /** 抓取某一学生的屏幕   */
   public ImageIcon CaptureScreen(String userID)
   {
        ImageIcon image=null;
        Hashtable    htStudentIDInterface=null;
        htStudentIDInterface=systemControl.getHtStudentIDInterface();
        IStudent=(Student)htStudentIDInterface.get(userID);
        if (IStudent==null)
        {
            systemControl.writeLog("在抓取某一学生的屏幕时,没有发现远程接口,调用失败！");
            return null;
        }
        try
        {
             image=IStudent.CaptureScreen();
        }
        catch(Exception e)
        {
             systemControl.writeLog("在抓取某一学生的屏幕时,远程调用失败！");
        }
        return image;
   }

   /** 学生自己抓取自己的屏幕   */
   public ImageIcon selfCaptureScreen()
   {
       try
       {
             BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0,0,maxWidth,maxHeight)) ;
             OutputStream out = new BufferedOutputStream(new FileOutputStream ("temp.jpeg"));
             JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder (out) ;
             encoder.encode(screenshot) ;
             out.close() ;
       }
       catch (Exception exc)
       {
           systemControl.writeLog("在学生抓取自己屏幕时,抓取和压缩失败！");
       }
       Image image=Toolkit.getDefaultToolkit().getImage("temp.jpeg");
       ImageIcon imageIcon=new ImageIcon(image);
       return imageIcon;
   }

   /** 让学生退出课堂     */
   public void  exitClass(String userID)
   {
        Hashtable    htStudentIDInterface=null;
        htStudentIDInterface=systemControl.getHtStudentIDInterface();
        IStudent=(Student)htStudentIDInterface.get(userID);
        if (IStudent==null)
        {
            systemControl.writeLog("在强制学生退出时,没有发现远程接口,调用失败！");
            return;
        }
        try
        {
             IStudent.exitClass();
        }
        catch(Exception e)
        {
             systemControl.writeLog("在强制某一学生退出时,远程调用失败！");
        }
   }
}
