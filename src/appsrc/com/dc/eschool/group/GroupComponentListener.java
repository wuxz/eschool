package com.dc.eschool.group;

import java.util.EventListener;

/**
 * Title:        Group Test
 * Description:  GroupComponent�ļ�����
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public interface GroupComponentListener extends EventListener
{
  /**
   * ��GroupComponent�е���selectedPerson��ѡ��ʱ����personSelected����
   */
  public void personSelected(GroupComponentEvent e, Person selectedPerson);
}