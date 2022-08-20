package com.dc.eschool.controller.web;

import com.dc.eschool.controller.exception.ListenerException;
/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * This interface is implemented by objects which are interested in
 * getting the model update events. For example, UsersSearchWebImpl implements
 * this interface to get itself updated when users model gets updated.
 * @author Eric
 */
public interface ModelUpdateListener
{
    public void performUpdate() throws  ListenerException;
}