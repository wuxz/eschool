package com.dc.eschool.group;

/**
 * Title:        Group Test
 * Description:  ����һ���˵�ȫ����Ϣ
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public interface Person
{
  /**
   * ������: ��ʦ
   */
  public static final int TEACHER = 0;

  /**
   * ������: ѧ��
   */
  public static final int STUDENT = 1;

  /**
   * ������: ����
   */
  public static final int AUDITOR = 2;

  /**
   * �Ա�: ��
   */
  public static final int MALE = 0;

  /**
   * �Ա�: Ů
   */
  public static final int FEMALE = 1;

  /**
   * ��������
   */
  public String getName();

  /**
   * ����������: TEACHER, STUDENT OR AUDITOR
   */
  public int getType();

  /**
   * �����˵��Ա�
   */
  public int getGender();

  /**
   * ����LoginName
   */
  public String getLoginName();

  /**
   * ����ڽ���,����True
   */
  public boolean InClassroom();

  /**
   * ���ؾ���״̬, ������ַ���true
   */
  public boolean isHandUp();

  /**
   * ���þ���״̬
   */
  public void setHandUp( boolean b);

  /**
   * ���ؿ���״̬ ����ڿ��Է���true
   */
  public boolean examing();

  /**
   * ���ÿ���״̬
   */
  public void setExaming( boolean b);

  /**
   * ���ر���״̬ �����������true
   */
  public boolean calling();

  /**
   * ���ñ���״̬
   */
  public void setCalling( boolean b);

}