package com.dc.eschool.group;

/**
 * Title:       ManualGroupTableModel
 * Description: 手工分组表模式
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
public class ManualGroupTableModel extends AbstractTableModel
{
    final String[] columnNames = {"小组名称","说明"};
    boolean isEnabled=false;
    private UserGroup userGroup;
    public ManualGroupTableModel(){}
    public ManualGroupTableModel(UserGroup userGroup)
    {
        this.userGroup=userGroup;
    }
    public void setUserGroup(UserGroup userGroup)
    {
        this.userGroup = userGroup;
        fireTableDataChanged();
    }
    public void setEnabled(boolean bl)
    {
        isEnabled=bl;
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }
    public int getRowCount()
    {
        Vector value;
        value=userGroup.getGroupNames();
        if(value!=null&&value.size()>0)
            return value.size();
        else
            return 0;
    }
    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col)
    {
        Vector value=null;
        if(col==0)
            value=userGroup.getGroupNames();
        else if(col==1)
            value=userGroup.getGroupRemarks();

        if(value!=null)
            return value.elementAt(row);
        else
            return null;
    }

   /*
    * JTable uses this method to determine the default renderer/
    * editor for each cell.  If we didn't implement this method,
    * then the last column would contain text ("true"/"false"),
    * rather than a check box.
    */
    public Class getColumnClass(int col)
    {
        return getValueAt(0, col).getClass();
    }
   /*
    * Don't need to implement this method unless your table's
    * editable.
    */
    public boolean isCellEditable(int row, int col)
    {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if(isEnabled==true)
            return true;//每列都可修改
        else
            return false;
    }
   /*
    * Don't need to implement this method unless your table's
    * data can change.
    */
    public void setValueAt(Object value, int row, int col)
    {
        String groupName=(String)getValueAt(row,0);
        if(col==0)
            userGroup.modifyGroupName(groupName,value.toString());
        else
            userGroup.modifyGroupRemark(groupName,value.toString());
        fireTableCellUpdated(row, col);
    }
    public void removeRow(int row)
    {
        String groupName=(String)getValueAt(row,0);
        userGroup.delGroup(groupName);
        //fireTableRowsDeleted(row,row);
        fireTableDataChanged();
    }
}