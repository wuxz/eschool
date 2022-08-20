package com.dc.eschool.group;

import javax.swing.JPopupMenu;
/**
 * Title:        Group Test
 * Description:  弹出菜单构造器, 用来为Group界面提供动态右键弹出菜单
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public interface PopupMenuBuilder
{
  /**
   * 返回当前的PopupMenu, 此方法在用于在右键弹出菜单显示器动态构造弹出菜单
   */
  public JPopupMenu getPersonPopupMenu( GroupComponent gc, PersonSeat ps, int x, int y, int rowIndex, int columnIndex );
}