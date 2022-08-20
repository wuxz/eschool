package com.dc.eschool.group;

import javax.swing.table.*;
import java.util.Vector;
import java.awt.event.MouseListener;
import javax.swing.event.TableModelEvent;

/**
 * Title:        Group
 * Description:  转换groupModel到TableModel
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class GroupTableModel extends AbstractTableModel
{
  GroupModel groupModel;
  String columnHeader = "";

  int columnCount;

  Vector classroomSeats = new Vector();

  /**
   * 用GroubModel创建GroupTableModel
   */
  public GroupTableModel( GroupModel gm, int columnCount )
  {
    this.groupModel = gm;
    this.columnCount = columnCount;
    resetSeats(false);
  }

  /**
   * @todo: implement this javax.swing.table.AbstractTableModel abstract method
   */
  public int getColumnCount()
  {
    return this.columnCount;
    /**@todo: implement this javax.swing.table.AbstractTableModel abstract method*/
  }

  /**
   * 设置列数量
   */
  public void setColumnCount( int columnCount )
  {
    this.columnCount = columnCount;
    this.fireTableDataChanged();
  }


  /**
   * @todo: implement this javax.swing.table.AbstractTableModel abstract method
   * 实现groupModel到tableModel过虑
   */
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    //System.out.println("rowIndex:" + rowIndex + "  columnIndex:" + columnIndex );
    PersonSeat rv = (PersonSeat)((Vector)this.classroomSeats.get(rowIndex)).get(columnIndex);
    //Person p = null;
    int offsetIndex = 0;
    Vector groupNames = null;
    Vector studentNames = null;
    String groupName = null;
    int studentCount = 0;
    int groupRowCount = 0;
    int studentIndex = 0;
    boolean isAuditor = true;

    //teacher
    if ( rowIndex == 0 )
    {
      if ( columnIndex == 0 )
      {
        rv.setPerson( groupModel.getTeacher());
      }
    }
    else
    {
      //student
      offsetIndex ++;
      //System.out.println("offsetIndex:" + offsetIndex);
      groupNames = groupModel.getGroupNames();
      for ( int i = 0; i < groupNames.size(); i++)
      {
        groupName = (String)groupNames.get(i);
        studentNames = groupModel.getStudentLoginNames(groupName);
        studentCount = studentNames.size();

        //groupRows = (int)Math.floor( (double)studentCount / (double)columnCount + 1f );
        //groupRows = (studentCount + (columnCount - 1 ))/ columnCount;
        groupRowCount = (studentCount-1) / columnCount + 1;
        if ( rowIndex >= offsetIndex && rowIndex < offsetIndex + groupRowCount )
        {
          studentIndex = ( rowIndex - offsetIndex ) * columnCount + columnIndex;
          rv.setGroupName( groupName );
          if ( studentIndex < studentNames.size())
            rv.setPerson( groupModel.getStudent( groupName, (String)studentNames.get(studentIndex)));
          isAuditor = false;
          break;
        }
        else
        {
          offsetIndex = offsetIndex + groupRowCount;
        }
      }

      //auditor
      if (isAuditor)
      {
        studentNames = groupModel.getAuditorLoginNames();
        studentCount = studentNames.size();

        groupRowCount = (studentCount-1) / columnCount + 1;
        if ( rowIndex >= offsetIndex && rowIndex < offsetIndex + groupRowCount )
        {
          studentIndex = ( rowIndex - offsetIndex ) * columnCount + columnIndex;
          if ( studentIndex < studentNames.size())
            rv.setPerson( groupModel.getAuditor((String)studentNames.get(studentIndex)));
        }
        else
        {
          offsetIndex = offsetIndex + groupRowCount;
        }
      }
    }

    //System.out.println("Person:" + rv.getPerson() );

    return rv;
    /**@todo: implement this javax.swing.table.AbstractTableModel abstract method*/
  }

  /**
   * @todo: implement this javax.swing.table.AbstractTableModel abstract method
   *
   */
  public int getRowCount()
  {
    int rv = 1;
    Vector groupNames = null;
    Vector studentNames = null;
    String groupName = null;
    int studentCount = 0;
    int groupRowCount = 0;

    groupNames = groupModel.getGroupNames();
    for (int i=0; i<groupNames.size(); i++)
    {
      groupName = (String)groupNames.get(i);
      studentNames = groupModel.getStudentLoginNames(groupName);
      studentCount = studentNames.size();

      groupRowCount = (studentCount-1) / columnCount + 1;

      //System.out.println("Rows:" + groupRows + "studentCount:" + studentCount + "ColumnCount:" + columnCount );

      rv = rv + groupRowCount;
    }

    studentNames = groupModel.getAuditorLoginNames();
    studentCount = studentNames.size();

    groupRowCount = (studentCount-1) / columnCount + 1;

    rv = rv + groupRowCount;

    return rv;
    /**@todo: implement this javax.swing.table.AbstractTableModel abstract method*/
  }

  /**
   * 初始化座位
   */
  void resetSeats(boolean columnChange)
  {
    if (columnChange)
    {
      this.classroomSeats.removeAllElements();
    }

    int rowCount = this.getRowCount();
    Vector rowSeats = null;

    for (int i=this.classroomSeats.size(); i < rowCount; i++)
    {
      rowSeats = new Vector();
      for ( int j = 0; j < columnCount; j++ )
      {
        rowSeats.add( new PersonSeat());
      }
      this.classroomSeats.add(rowSeats);
    }
  }

  public Class getColumnClass(int columnIndex)
  {
    return PersonSeat.class;
  }

  public String getColumnName(int column)
  {
    return columnHeader;
  }

  /**
   * 覆盖方法清空座位
   */
  public void fireTableChanged(TableModelEvent e)
  {
    resetSeats(true);//清空座位
    super.fireTableChanged(e);
    //System.out.println("mode fire");
  }
}