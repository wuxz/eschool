package com.dc.eschool.eclass.mgrbean;

import java.rmi.*;
import javax.ejb.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public interface EClassSLHome extends EJBHome
{
    public EClassSL create() throws RemoteException, CreateException;
}