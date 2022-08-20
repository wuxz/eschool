package com.dc.eschool.system;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.ImageIcon;

/**
 * Title:         学生远程接口
 * Description:   定义了学生所有的远程方法
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public interface Student extends Remote
{

   /** 结束考试   */
   public void endExam() throws RemoteException;

   /** 开始考试   */
   public void startExam() throws RemoteException;

   /**
    * 在学生界面上创建黑色的窗口，来覆盖所有的操作区域，
    * 也就时禁止学生对Application的所有操作。
    */
   public void createDarkWindow() throws RemoteException;

   /**
    * 删除已经存在的黑色窗口，学生的操作界面恢复正常。
    */
   public void deleteDarkWindow() throws RemoteException;

   /** 创建一窗体，显示警告信息   */
   public void showWarnMessage(String warnContent) throws RemoteException;

   /**
    * 老师会调用该方法开始抓屏，该方法会调用CreatePlayer(),
    * broadcastPlayer()方法协调完成学生端的广播。
    */
   //public void startCaptureScreen() throws RemoteException;

   /** 取消抓屏  */
   //public void cancelCaptureScreen() throws RemoteException;

   /** 询问学生是否在听课，该方法会调用一个对话框，显示老师的提示。  */
   public void hasCome() throws RemoteException;

   /** 抓取学生的屏幕  */
   public ImageIcon CaptureScreen() throws RemoteException;

   /**  退出课堂     */
   public void exitClass() throws RemoteException;

   /**
    * 以下代码由Ardy_yan编写
    */

    public String answerFromTeacher(String port) throws RemoteException;
    public void sendText(String content) throws RemoteException;
    public void  notifyStartOnlineTalk(Hashtable userIDs,String port) throws RemoteException;
    public void  notifyEndOnlineTalk() throws RemoteException;
    public void  notifyStartDemonstrate(String port) throws RemoteException;
    public void  notifyEndDemonstrate() throws RemoteException;
    public void  notifyStartAnswerCompetition(Vector userIDs,String port) throws RemoteException;
    public void notifyBeginOrEndAnswerCompetition(boolean b) throws RemoteException;
    public void addAnswerCompetitionSequence(String userID) throws RemoteException;
    public void sendInformation(String info) throws RemoteException;
    public void voiceReceive(String port) throws RemoteException;
    public void endMediaReceive() throws RemoteException;
    public void notifyAllowAnswer(boolean b) throws RemoteException;
    public void listenReceive(String port) throws RemoteException;
    public void updateOtherUser(String userID,String userIP,int userCount,Student a_student) throws RemoteException;
    public boolean logout() throws RemoteException;
    public void moveUser(String fromGroupName,String fromUserID,String toGroupName,String toUserID) throws RemoteException;
    public void updateGroup() throws RemoteException;
    public String startDemonstrate() throws RemoteException;
    public void changeStatus(String userID) throws RemoteException;
    public void  notifyCloseAnswerCompetition() throws RemoteException;
    public void  closeTalkWithTeacher() throws RemoteException;
}
