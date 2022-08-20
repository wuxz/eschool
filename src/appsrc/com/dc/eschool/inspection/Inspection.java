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
 * Title:         ������
 * Description:   ����ʵ���˼��ӽӿڵ����з��������ҵ��������Ľӿڻ�����Э��ʵ�֡�
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class Inspection implements InspectionIF
{
   private static  int  maxWidth=800;
   private static  int  maxHeight=600;
   /**    SystemControlʵ��  */
   private SystemControl      systemControl;
   /**    ��ʦԶ�̽ӿ�    */
   private Teacher            ITeacher;
   /**    ѧ��Զ�̽ӿ�    */
   private Student            IStudent;
   /**    ���ͼƬ����    */
   private DrawImageFrm       drawImageFrm;

   /**  ������  */
   public Inspection(SystemControl systemControl)
   {
        this.systemControl=systemControl;
   }

   /** ��ʾץȡ��ѧ������Ļ   */
   private void viewPhoto(ImageIcon image)
   {
       drawImageFrm=new DrawImageFrm(image);
       drawImageFrm.setSize(maxWidth,maxHeight);
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       drawImageFrm.setLocation(new Double((screenSize.getWidth()-maxWidth)/2).intValue(),
                                new Double((screenSize.getHeight()-maxHeight)/2).intValue());
       drawImageFrm.setVisible(true);
   }

   /**  ʹĳһѧ������   */
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
                systemControl.writeLog("����ĳһѧ������ʱ,û���ҵ�Զ�̽ӿ�,Զ�̵���ʧ�ܣ�");
            }
        }
        catch (Exception e)
        {
            systemControl.writeLog("����ĳһѧ������ʱ,Զ�̵���ʧ�ܣ�");
        }
   }

   /** ʹһȺѧ������   */
   public void setGroupDarkScreen(Vector studentID)
   {
        for(int i=0;i<studentID.size();i++)
        {
            setDarkScreen((String)studentID.get(i));
        }
   }

   /** ʹ����ѧ������   */
   public void setAllDarkScreen()
   {
       Hashtable    htStudentIDInterface=null;
       htStudentIDInterface=systemControl.getHtStudentIDInterface();
       if (htStudentIDInterface.size()==0)
       {
           systemControl.writeLog("��ʹ����ѧ������ʱ,����û���ҵ�ѧ���Ľӿ�,����ʧ�ܣ�");
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
                   systemControl.writeLog("��ȫ�����ʱ,û���ҵ�Զ�̽ӿ�,Զ�̵���ʧ�ܣ�");
               }
           }
        }
        catch(Exception e)
        {
            systemControl.writeLog("��ȫ�����ʱ,Զ�̵���ʧ�ܣ�");
        }
   }

   /** ȡ������   */
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
                systemControl.writeLog("��ȡ��ĳһѧ������ʱ,û���ҵ�Զ�̽ӿ�,Զ�̵���ʧ�ܣ�");
            }
        }
        catch (Exception e)
        {
            systemControl.writeLog("��ȡ��ĳһѧ������ʱ,Զ�̵���ʧ�ܣ�");
        }
   }

   /** ȡ��һ��ѧ���ĺ���   */
   public void cancelGroupDarkScreen(Vector studentID)
   {
        for(int i=0;i<studentID.size();i++)
        {
            cancelDarkScreen((String)studentID.get(i));
        }
   }

   /** ȡ������ѧ���ĺ���   */
   public void cancelAllDarkScreen()
   {
       Hashtable    htStudentIDInterface=null;
       htStudentIDInterface=systemControl.getHtStudentIDInterface();
       if (htStudentIDInterface.size()==0)
       {
           systemControl.writeLog("��ȡ������ѧ������ʱ,����û���ҵ�ѧ���Ľӿ�,����ʧ�ܣ�");
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
                   systemControl.writeLog("��ȡ��ȫ�����ʱ,û���ҵ�Զ�̽ӿ�,Զ�̵���ʧ�ܣ�");
               }
           }
        }
        catch(Exception e)
        {
            systemControl.writeLog("��ȡ��ȫ�����ʱ,Զ�̵���ʧ�ܣ�");
        }
   }

   /** ����ѧ��  */
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
                 systemControl.writeLog("�ھ���ʱ,û��ȡ��ѧ����Զ�̽ӿ�,����ʧ�ܣ�");
             }
        }
        catch(Exception e)
        {
            systemControl.writeLog("�ھ���ʱ,Զ�̵���ʧ�ܣ�");
        }
   }


   /** ����ѧ��  */
   public void warnStudent(Vector studentID,String strContent)
   {
        /**   �û�ID    */
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
                     systemControl.writeLog("�ھ���ʱ,û��ȡ��ѧ����Զ�̽ӿ�,����ʧ�ܣ�");
                 }
            }
            catch(Exception e)
            {
                systemControl.writeLog("�ھ���ʱ,Զ�̵���ʧ�ܣ�");
            }
        }
   }

   /** ������Ļ   */
   public void reciveScreen(String userID)
   {
       ImageIcon  image=CaptureScreen(userID);
       viewPhoto(image);
   }

   /** ��ʦѯ��ѧ���Ƿ���������   */
   public void hasCome(String userID)
   {
        Hashtable    htStudentIDInterface=null;
        htStudentIDInterface=systemControl.getHtStudentIDInterface();
        IStudent=(Student)htStudentIDInterface.get(userID);
        if (IStudent==null)
        {
            systemControl.writeLog("�ڵ���ʱ,û��ȡ��ѧ����Զ�̽ӿ�,����ʧ�ܣ�");
            return;
        }
        try
        {
             IStudent.hasCome();
        }
        catch(Exception e)
        {
             systemControl.writeLog("�ڵ���ʱ,Զ�̵���ʧ�ܣ�");
        }
   }
   /**   ��ʦѯ��һ��ѧ���Ƿ�����������   */
   public void hasCome(Vector studentID)
   {
        String   userID;
        if(studentID==null)
        {
            systemControl.writeLog("��ʦ�ڵ���ʱ,����û��ȡ��ѧ������Ϣ,����ʧ��");
            return;
        }
        for (int i=0;i<studentID.size();i++)
        {
            userID=(String)studentID.get(i);
            hasCome(userID);
        }
   }

   /** ѧ���ش���ʦ  */
   public void alreadyCome(String userID)
   {
        ITeacher=systemControl.getTeacherInterface();
        if (ITeacher==null)
        {
            systemControl.writeLog("��ѧ����Ӧ��ʦ�ĵĵ���ʱ,û���ҵ���ʦ��Զ�̽ӿ�,����ʧ��");
            return;
        }
        try
        {
            ITeacher.alreadyCome(userID);
        }
        catch(Exception e)
        {
            systemControl.writeLog("��ѧ����Ӧ��ʦ�ĵĵ���ʱ,Զ�̵���ʧ��");
        }
   }

   /** ץȡĳһѧ������Ļ   */
   public ImageIcon CaptureScreen(String userID)
   {
        ImageIcon image=null;
        Hashtable    htStudentIDInterface=null;
        htStudentIDInterface=systemControl.getHtStudentIDInterface();
        IStudent=(Student)htStudentIDInterface.get(userID);
        if (IStudent==null)
        {
            systemControl.writeLog("��ץȡĳһѧ������Ļʱ,û�з���Զ�̽ӿ�,����ʧ�ܣ�");
            return null;
        }
        try
        {
             image=IStudent.CaptureScreen();
        }
        catch(Exception e)
        {
             systemControl.writeLog("��ץȡĳһѧ������Ļʱ,Զ�̵���ʧ�ܣ�");
        }
        return image;
   }

   /** ѧ���Լ�ץȡ�Լ�����Ļ   */
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
           systemControl.writeLog("��ѧ��ץȡ�Լ���Ļʱ,ץȡ��ѹ��ʧ�ܣ�");
       }
       Image image=Toolkit.getDefaultToolkit().getImage("temp.jpeg");
       ImageIcon imageIcon=new ImageIcon(image);
       return imageIcon;
   }

   /** ��ѧ���˳�����     */
   public void  exitClass(String userID)
   {
        Hashtable    htStudentIDInterface=null;
        htStudentIDInterface=systemControl.getHtStudentIDInterface();
        IStudent=(Student)htStudentIDInterface.get(userID);
        if (IStudent==null)
        {
            systemControl.writeLog("��ǿ��ѧ���˳�ʱ,û�з���Զ�̽ӿ�,����ʧ�ܣ�");
            return;
        }
        try
        {
             IStudent.exitClass();
        }
        catch(Exception e)
        {
             systemControl.writeLog("��ǿ��ĳһѧ���˳�ʱ,Զ�̵���ʧ�ܣ�");
        }
   }
}
