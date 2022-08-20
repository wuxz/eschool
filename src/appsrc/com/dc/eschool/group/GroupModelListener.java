package com.dc.eschool.group;

import java.util.EventListener;

/**
 * Title:        Group Test
 * Description:  GroupModelListener defines the interface for an object that listens to changes in a GroupModel.
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public interface GroupModelListener extends EventListener
{

  /**
   * This fine grain notification tells listeners the exact range of Person that changed.
   */
  void groupChanged();
}