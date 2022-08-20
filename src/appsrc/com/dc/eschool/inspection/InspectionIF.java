package com.dc.eschool.inspection;

import java.util.*;
import javax.swing.ImageIcon;

/**
 * Title:         ���ӽӿ�
 * Description:   �ýӿ��ṩ�˼��Ӳ��ֵ����з���
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public interface InspectionIF
{

   /**��ĳһѧ������   */
   public void setDarkScreen(String studentID);

   /**
    * ��ĳһ����߼���ѧ��������
    * ����ѭ������setDarkScreen(studentName:String)����
    */
   public void setGroupDarkScreen(Vector studentID);

   /** ������ѧ������,
     *  ����ѭ������setDarkScreen(studentName:String)����
     */
   public void setAllDarkScreen();

   /** ȡ��ĳһѧ���ĺ���  */
   public void cancelDarkScreen(String studentID);

   /**ȡ��ĳһ��ѧ���ĺ�������Ҫѭ������cancelDarkScreen()����  */
   public void cancelGroupDarkScreen(Vector studentID);

   /** ȡ��ȫ��ѧ���ĺ�������Ҫѭ������cancelDarkScreen()����   */
   public void cancelAllDarkScreen();

   /** ����ĳһѧ��   */
   public void warnStudent(String userID,String strContent);

   /** ����ѧ��  */
   public void warnStudent(Vector studentID,String strContent);

   /** ����Զ��ѧ���ӿڵķ�����ʼץ��
     *public void startCaptureScreen(String userName);
     */
   /** ����ѧ���˴�������ĻͼƬ��������   */
   public void reciveScreen(String userID);

   /** ȡ��ѧ���˵�ץ������
     *  public void cancelCaptureScreen(String userName);
     */
   /** ��ʦѯ��ѧ���Ƿ���������   */
   public void hasCome(String userID);

   /**   ��ʦѯ��һ��ѧ���Ƿ�����������   */
   public void hasCome(Vector studentID);

   /** ѧ��������ʦ�������� */
   public void alreadyCome(String userID);

   /** ץȡѧ������Ļ   */
   public ImageIcon CaptureScreen(String userID);

   /** ѧ��ץȡ�Լ�����Ļ  */
   public ImageIcon selfCaptureScreen();

   /** ��ѧ���˳�����     */
   public void  exitClass(String userID);
}
