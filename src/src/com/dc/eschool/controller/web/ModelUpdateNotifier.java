package com.dc.eschool.controller.web;

import java.io.Serializable;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.dc.eschool.controller.exception.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * This class is responsible for providing methods to add objects as listeners
 * for a particular model update event and for notifying the listeners when the
 * event actually occurs.
 * @author Eric
 */

public class ModelUpdateNotifier implements Serializable
{
    private HashMap listenerMap;

    public ModelUpdateNotifier()
    {
        listenerMap = new HashMap();
    }

    public void notifyListeners(Collection updatedModelList) throws
                                                    ControllerException,ListenerException
    {

        for (Iterator it1 = updatedModelList.iterator() ; it1.hasNext() ;)
        {
            String modelType = (String) it1.next();
            Collection listeners = (Collection)listenerMap.get(modelType);
            if (listeners != null)
            {
                for (Iterator it2 = listeners.iterator(); it2.hasNext(); )
                {
                    ((ModelUpdateListener) it2.next()).performUpdate();
                }
            }
        }
    }

    public void addListener(String modelType, Object listener)
    {
        if (listenerMap.get(modelType) == null)
        {
            ArrayList listeners = new ArrayList();
            listeners.add(listener);
            listenerMap.put(modelType,listeners);
        } else
        {
            ((ArrayList) listenerMap.get(modelType)).add(listener);
        }
    }
}