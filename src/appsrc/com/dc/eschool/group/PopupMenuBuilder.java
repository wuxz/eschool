package com.dc.eschool.group;

import javax.swing.JPopupMenu;
/**
 * Title:        Group Test
 * Description:  �����˵�������, ����ΪGroup�����ṩ��̬�Ҽ������˵�
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public interface PopupMenuBuilder
{
  /**
   * ���ص�ǰ��PopupMenu, �˷������������Ҽ������˵���ʾ����̬���쵯���˵�
   */
  public JPopupMenu getPersonPopupMenu( GroupComponent gc, PersonSeat ps, int x, int y, int rowIndex, int columnIndex );
}