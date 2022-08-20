package com.dc.eschool.listen;

import java.util.Collection;
import java.util.Vector;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.system.EJBAccess;

/**
 * Title:        ����ʵ����
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class Listen implements ListenIF
{
  /**    SystemControlʵ��  */
  private SystemControl      systemControl;
  /**    EJBAccessʵ��      */
  private EJBAccess          ejbAccess;
  /**  ������  */
  public Listen(SystemControl systemControl)
  {
      this.systemControl=systemControl;
      this.ejbAccess=systemControl.ejbAccess;
  }
  /** ���������ϰ�������Ϣ  */
  public Collection getListenExerciseInfo(String courseID)
  {
      Collection colTemp=ejbAccess.getListenExerciseInfo(courseID);
      return colTemp;
  }
  /** �������Ƭ�ϵ������Ϣ  */
  public Collection getListenSnippetInfo(String exerciseID)
  {
      Collection colTemp=ejbAccess.getListenSnippetInfo(exerciseID);
      return colTemp;
  }
  /** ����������ϰ   */
  public void saveListenExerciseInfo(Vector listenExerciseID)
  {
      ejbAccess.saveListenExerciseInfo(listenExerciseID);
  }
  /**  ȡ��������ϰ  */
  public void cancelListenExercise(String courseID)
  {
      ejbAccess.cancelListenExercise(courseID);
  }
  /**  ȡ������Ƭ���б�  */
  public Collection getListenSnippetList(String courseID)
  {
      Collection colTemp=ejbAccess.getListenSnippetList(courseID);
      return colTemp;
  }
  /**  ȡ������Ƭ�ϵ�URL */
  public String   getListenSnippetURL(String sinppetID)
  {
      String tempURL=ejbAccess.getListenSnippetURL(sinppetID);
      return tempURL;
  }
}