package com.dc.eschool.controller.web;

import com.dc.eschool.controller.web.ModelManager;
import com.dc.eschool.controller.web.ModelUpdateListener;
import com.dc.eschool.controller.exception.ListenerException;

import com.dc.eschool.schoolresource.ejb.SchoolResourceEB;
import com.dc.eschool.schoolresource.model.SchoolResourceModel;

import com.dc.eschool.util.JNDINames;


/**
 * Title: Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:  DC
 * @author wangshui
 * @version 1.0
 */

public class SchoolResourceWebImpl extends SchoolResourceModel
                            implements ModelUpdateListener,
                                      java.io.Serializable
{
  private ModelManager mm;
  private SchoolResourceEB SchoolResourceEB;

  public SchoolResourceWebImpl()
  {
    super();
  }
  public SchoolResourceWebImpl(ModelManager mm)
  {
    super();
    this.mm = mm;
    mm.addListener(JNDINames.SCHOOLRESOURCE_EJBHOME,this);
  }
  public void performUpdate() throws ListenerException
  {
//    this.copy(); copy from ejb to update data.
    return;
  }
}