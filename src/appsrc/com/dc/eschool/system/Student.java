package com.dc.eschool.system;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.ImageIcon;

/**
 * Title:         ѧ��Զ�̽ӿ�
 * Description:   ������ѧ�����е�Զ�̷���
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public interface Student extends Remote
{

   /** ��������   */
   public void endExam() throws RemoteException;

   /** ��ʼ����   */
   public void startExam() throws RemoteException;

   /**
    * ��ѧ�������ϴ�����ɫ�Ĵ��ڣ����������еĲ�������
    * Ҳ��ʱ��ֹѧ����Application�����в�����
    */
   public void createDarkWindow() throws RemoteException;

   /**
    * ɾ���Ѿ����ڵĺ�ɫ���ڣ�ѧ���Ĳ�������ָ�������
    */
   public void deleteDarkWindow() throws RemoteException;

   /** ����һ���壬��ʾ������Ϣ   */
   public void showWarnMessage(String warnContent) throws RemoteException;

   /**
    * ��ʦ����ø÷�����ʼץ�����÷��������CreatePlayer(),
    * broadcastPlayer()����Э�����ѧ���˵Ĺ㲥��
    */
   //public void startCaptureScreen() throws RemoteException;

   /** ȡ��ץ��  */
   //public void cancelCaptureScreen() throws RemoteException;

   /** ѯ��ѧ���Ƿ������Σ��÷��������һ���Ի�����ʾ��ʦ����ʾ��  */
   public void hasCome() throws RemoteException;

   /** ץȡѧ������Ļ  */
   public ImageIcon CaptureScreen() throws RemoteException;

   /**  �˳�����     */
   public void exitClass() throws RemoteException;

   /**
    * ���´�����Ardy_yan��д
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
