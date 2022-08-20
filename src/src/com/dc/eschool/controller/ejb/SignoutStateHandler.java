package com.dc.eschool.controller.ejb;

import com.dc.eschool.controller.event.*;
import com.dc.eschool.controller.exception.*;
import com.dc.eschool.controller.web.StateMachine;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SignoutStateHandler extends StateHandlerSupport
{

  public void perform(MainEvent event) throws ControllerException
  {
      SignoutEvent se = (SignoutEvent)event;
      Debug.println("SignoutEvent: " + se);
      // do whatever cleanup you may want to do
  }
}