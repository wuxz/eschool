package com.dc.eschool.controller.ejb;

import com.dc.eschool.controller.web.StateHandler;
import com.dc.eschool.controller.web.StateMachine;
import com.dc.eschool.controller.event.MainEvent;

import com.dc.eschool.controller.exception.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 *          lastModifyBy Eric CHEN
 * @version 1.0
 */

public class StateHandlerSupport implements StateHandler,java.io.Serializable
{
    protected StateMachine machine = null;

    public void init(StateMachine machine)
    {
      this.machine = machine;
    }

    public void doStart()
    {
    }

    public void perform(MainEvent event) throws ControllerException
    {
    }

    public void doEnd()
    {
    }
}