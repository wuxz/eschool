package com.dc.eschool.listen;

import java.util.Collection;
import java.util.Vector;

/**
 * Title:        听力接口
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public interface ListenIF
{
    /** 获得听力练习的相关信息  */
    public Collection getListenExerciseInfo(String courseID);
    /** 获得听力片断的相关信息  */
    public Collection getListenSnippetInfo(String exerciseID);
    /** 保存听力练习   */
    public void   saveListenExerciseInfo(Vector listenExerciseID);
    /**  取消听力练习  */
    public void   cancelListenExercise(String courseID);
    /**  取得听力片断列表  */
    public Collection getListenSnippetList(String courseID);
    /**  取得听力片断的URL */
    public String   getListenSnippetURL(String sinppetID);
}