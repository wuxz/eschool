package com.dc.eschool.group;

import java.util.EventObject;

/**
 * Title:        Group Test
 * Description:  GroupComponent监听器中用来传递 GroupComponent 组件
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class GroupComponentEvent extends EventObject
{

  /**
   * 构造方法
   */
  public GroupComponentEvent(GroupComponent source)
  {
    super(source);
  }

}