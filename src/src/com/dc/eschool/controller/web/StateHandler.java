package com.dc.eschool.controller.web;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.exception.*;
/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */



public interface StateHandler
{
  public void init(StateMachine urc);

  public void doStart();

  public void perform(MainEvent event) throws ControllerException;

  public void doEnd();
}