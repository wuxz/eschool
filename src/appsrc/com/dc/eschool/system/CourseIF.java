package com.dc.eschool.system;

/**
 * Title:         �γ���Ϣ
 * Description:   �γ���Ϣ�ӿڣ��ýӿ��ṩ��γ���Ϣ��صĸ��ַ�����
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public interface CourseIF
{

   /** ȡ�ÿγ̵�����  */
   public String getCourseName(String courseID);

   /** ȡ�ÿγ̵Ŀ�ʼ���� */
   public String getStartDate(String courseID);

   /** ȡ�ÿγ̽������� */
   public String getEndDate(String courseID);

   /**  ��ÿγ̵���Ϣ */
   public String getCourseInfo(String courseID);

   /** ��ÿγ̵�״̬  */
   public String getCourseState(String courseID);
}
