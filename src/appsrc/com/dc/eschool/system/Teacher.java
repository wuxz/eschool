package com.dc.eschool.system;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.dc.eschool.group.*;
import java.util.Vector;
/**
 * Title:         ��ʦԶ�̽ӿ�
 * Description:   ��������ʦ���е�Զ�̷���
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public interface Teacher extends Remote
{

   /** ѧ�����ø÷�����������ʦ�Ѿ�����  */
   public void handinExam(String userID) throws RemoteException;

   /**ѧ�����ø÷�����������ʦ�����������  */
   public void alreadyCome(String userID) throws RemoteException;

   /**
    * ����ΪArdy_yan���ֵĴ���
    */

   public void login( Student a_student,String userID,String userIP) throws RemoteException;
   public void askFromStudent(String userID) throws RemoteException;
   public void sendText(String content) throws RemoteException;
   public void studentAnswerCompetition(String userID) throws RemoteException;
   public void sendInformation(String info) throws RemoteException;
   public void voiceReceive(String port) throws RemoteException;
   public void endMediaReceive() throws RemoteException;
   public void listenReceive(String port) throws RemoteException;
   public Vector getGroupInformation() throws RemoteException;
   public boolean logout(String userID) throws RemoteException;
   public void  notifyStartDemonstrate(String port) throws RemoteException;
}
