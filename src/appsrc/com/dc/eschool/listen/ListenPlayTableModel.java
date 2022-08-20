package com.dc.eschool.listen;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

import java.util.Vector;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.table.AbstractTableModel;
import com.dc.eschool.DataModel.*;
public class ListenPlayTableModel extends AbstractTableModel
{
    final String[] columnNames = {"Æ¬¶ÏÃû³Æ","ËµÃ÷"};
    private ListenIF listen;
    private Collection listenCollection;
    private String[] listenData;
    public ListenPlayTableModel(){}
    public ListenPlayTableModel(ListenIF listen,String courseID)
    {
        this.listen=listen;
        listenCollection=this.listen.getListenSnippetList(courseID);
        int size=listenCollection.size();

    }
    public int getColumnCount()
    {
        return columnNames.length;
    }
    public int getRowCount()
    {
        return listenCollection.size();
    }
    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col)
    {
        Iterator iter=listenCollection.iterator();
        ListenSnippetModel listenSnippetModel=new ListenSnippetModel();
        for(int i=0;i<row+1;i++)
        {
            listenSnippetModel=(ListenSnippetModel)iter.next();

        }
        if(col==0)
            return listenSnippetModel.getSnippetName();
        else if(col==1)
          return listenSnippetModel.getSnippetInfo();
        else
          return listenSnippetModel.getSnippetID();


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
    public void removeRow(int row)
    {
        Iterator iter=listenCollection.iterator();
        ListenSnippetModel listenSnippetModel=new ListenSnippetModel();
        for(int i=0;i<row+1;i++)
        {
            listenSnippetModel=(ListenSnippetModel)iter.next();

        }
        listenCollection.remove(listenSnippetModel);
        fireTableDataChanged();
    }

}