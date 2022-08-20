package com.dc.eschool;

import javax.swing.table.*;
import java.util.*;


/**
 * Title:        课堂交流
 * Description:  信息显示编辑的table model
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author  :Robin Liu
 * @version 1.0
 */

public class InfoTableModel extends AbstractTableModel
{
  static final int NAME_COL = 0;
  static final int VALUE_COL = 1;

  static final String COL_NAME[] = { "项目", "信息" };

  Vector infoList ;
  boolean editable ;

  public InfoTableModel(boolean editable)
  {
    infoList = new Vector();
    this.editable = editable;
  }
  public int getColumnCount()
  {
    return 2;
    /**@todo: implement this javax.swing.table.AbstractTableModel abstract method*/
  }
  public Object getValueAt(int row, int column)
  {
    Object rv = null;
    Info i = (Info)this.infoList.get(row);
    if ( i != null )
      if ( column == NAME_COL )
        rv = i.name;
      else
        rv = i.value;
    return rv;
    /**@todo: implement this javax.swing.table.AbstractTableModel abstract method*/
  }
  public int getRowCount()
  {
    return this.infoList.size();
    /**@todo: implement this javax.swing.table.AbstractTableModel abstract method*/
  }

  public void setValueAt(Object aValue, int row, int column)
  {
    if ( column == NAME_COL )
      return;

    Info i = (Info)this.infoList.get(row);
    if ( column == this.VALUE_COL )
    {
      i.value = aValue.toString();
      this.fireTableCellUpdated(row,column);
    }
  }

  public boolean isCellEditable(int row, int column)
  {
    if ( column == this.VALUE_COL )
      return this.editable;
    else
      return false;
  }

  public void addInfo( String name, String value )
  {
    if ( name == null )
      return ;
    Info i = new Info(name, value);
    this.infoList.add(i);
    this.fireTableDataChanged();
  }

  public String getColumnName(int column)
  {
    return this.COL_NAME[column];
  }

  public String getValueByName( String name )
  {
    String rv = null;
    Info info = null;
    for ( int i = 0; i < this.infoList.size(); i++ )
    {
      info = (Info) this.infoList.get(i);
      if ( info.name.equals(name) )
      {
        rv = info.value;
        break;
      }
    }
    return rv;
  }

  class Info
  {
    public Info( String name, String value )
    {
      this.name = name;
      this.value = value;
    }
    public String name = null;
    public String value = null;
  }
}