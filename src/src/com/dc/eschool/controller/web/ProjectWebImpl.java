package com.dc.eschool.controller.web;

import com.dc.eschool.controller.exception.*;
import com.dc.eschool.project.ejb.*;
import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric Chen
 * @version 1.0
 */

public class ProjectWebImpl extends com.dc.eschool.project.model.ProjectModel
                            implements ModelUpdateListener,
                                      java.io.Serializable
{
  private ModelManager mm;
  private ProjectEB projEjb;

  public ProjectWebImpl()
  {
    super();
  }
  public ProjectWebImpl(ModelManager mm)
  {
    super();
    this.mm = mm;
    mm.addListener(JNDINames.PROJECT_EJBHOME,this);
  }
  public void performUpdate() throws ListenerException
  {
//    this.copy(); copy from ejb to update data.
    return;
  }
}