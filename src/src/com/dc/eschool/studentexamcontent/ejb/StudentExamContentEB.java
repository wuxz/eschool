package com.dc.eschool.studentexamcontent.ejb;

import java.rmi.*;
import javax.ejb.*;
import com.dc.eschool.studentexamcontent.model.StudentExamContentModel;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public interface StudentExamContentEB extends EJBObject
{
  public StudentExamContentModel getDetails() throws RemoteException;

}