package com.dc.eschool.group;

import java.util.EventObject;

/**
 * Title:        Group Test
 * Description:  GroupComponent���������������� GroupComponent ���
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class GroupComponentEvent extends EventObject
{

  /**
   * ���췽��
   */
  public GroupComponentEvent(GroupComponent source)
  {
    super(source);
  }

}