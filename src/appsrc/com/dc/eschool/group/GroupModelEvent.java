package com.dc.eschool.group;

import java.util.EventObject;

/**
 * Title:        Group Test
 * Description:  GroupModelEvent is used to notify listeners that a Group model has changed.
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class GroupModelEvent extends EventObject
{

  public GroupModelEvent( GroupModel source )
  {
    super(source);
  }
}