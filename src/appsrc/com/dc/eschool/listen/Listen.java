package com.dc.eschool.listen;

import java.util.Collection;
import java.util.Vector;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.system.EJBAccess;

/**
 * Title:        听力实现类
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author       lau_hz
 * @version      1.0
 */

public class Listen implements ListenIF
{
  /**    SystemControl实例  */
  private SystemControl      systemControl;
  /**    EJBAccess实例      */
  private EJBAccess          ejbAccess;
  /**  构造器  */
  public Listen(SystemControl systemControl)
  {
      this.systemControl=systemControl;
      this.ejbAccess=systemControl.ejbAccess;
  }
  /** 获得听力练习的相关信息  */
  public Collection getListenExerciseInfo(String courseID)
  {
      Collection colTemp=ejbAccess.getListenExerciseInfo(courseID);
      return colTemp;
  }
  /** 获得听力片断的相关信息  */
  public Collection getListenSnippetInfo(String exerciseID)
  {
      Collection colTemp=ejbAccess.getListenSnippetInfo(exerciseID);
      return colTemp;
  }
  /** 保存听力练习   */
  public void saveListenExerciseInfo(Vector listenExerciseID)
  {
      ejbAccess.saveListenExerciseInfo(listenExerciseID);
  }
  /**  取消听力练习  */
  public void cancelListenExercise(String courseID)
  {
      ejbAccess.cancelListenExercise(courseID);
  }
  /**  取得听力片断列表  */
  public Collection getListenSnippetList(String courseID)
  {
      Collection colTemp=ejbAccess.getListenSnippetList(courseID);
      return colTemp;
  }
  /**  取得听力片断的URL */
  public String   getListenSnippetURL(String sinppetID)
  {
      String tempURL=ejbAccess.getListenSnippetURL(sinppetID);
      return tempURL;
  }
}