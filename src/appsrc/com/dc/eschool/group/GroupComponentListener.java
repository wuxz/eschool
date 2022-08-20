package com.dc.eschool.group;

import java.util.EventListener;

/**
 * Title:        Group Test
 * Description:  GroupComponent的监听器
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public interface GroupComponentListener extends EventListener
{
  /**
   * 当GroupComponent中的人selectedPerson被选定时激发personSelected方法
   */
  public void personSelected(GroupComponentEvent e, Person selectedPerson);
}