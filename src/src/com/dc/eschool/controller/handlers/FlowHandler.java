package com.dc.eschool.controller.handlers;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import com.dc.eschool.controller.exception.ControllerException;
/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

/**
 * This class is the base interface to flow handlers on the
 * web tier.
 *@author Eric
 */
public interface FlowHandler extends java.io.Serializable
{

    public void doStart(HttpServletRequest request);
    public String processFlow(HttpServletRequest request) throws ControllerException;
    public void doEnd(HttpServletRequest request);

}
