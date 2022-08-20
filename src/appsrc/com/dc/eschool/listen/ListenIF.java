package com.dc.eschool.listen;

import java.util.Collection;
import java.util.Vector;

/**
 * Title:        �����ӿ�
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author lau_hz
 * @version 1.0
 */

public interface ListenIF
{
    /** ���������ϰ�������Ϣ  */
    public Collection getListenExerciseInfo(String courseID);
    /** �������Ƭ�ϵ������Ϣ  */
    public Collection getListenSnippetInfo(String exerciseID);
    /** ����������ϰ   */
    public void   saveListenExerciseInfo(Vector listenExerciseID);
    /**  ȡ��������ϰ  */
    public void   cancelListenExercise(String courseID);
    /**  ȡ������Ƭ���б�  */
    public Collection getListenSnippetList(String courseID);
    /**  ȡ������Ƭ�ϵ�URL */
    public String   getListenSnippetURL(String sinppetID);
}