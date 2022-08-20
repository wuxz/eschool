package com.dc.eschool.communication;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import javax.swing.AbstractListModel;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import com.dc.eschool.group.*;
public class OnlineTalkGroupLIstModel extends AbstractListModel
{
    private Vector data;
    private OnlineTalkStudentListModel onlineTalkStudentListModel;
    public OnlineTalkGroupLIstModel(OnlineTalkStudentListModel onlineTalkStudentListModel)
    {
        this.onlineTalkStudentListModel=onlineTalkStudentListModel;
        updateGroup();
    }
    public void updateGroup()
    {
        Hashtable userIDs=onlineTalkStudentListModel.getUserIDData();
        System.out.println("userIDs'size="+userIDs.size());
        data=new Vector();
        for(Enumeration er=userIDs.keys(); er.hasMoreElements();)
        {
            String groupName=(String)er.nextElement();
            System.out.println("OnlineTalkGroupListModel groupName="+groupName);
            data.addElement(groupName);
        }
        fireContentsChanged(this,0,0);
    }
    public void setData(Vector data)
    {
        this.data=data;
    }
    public Vector getGroupData()
    {
        return data;
    }

    public boolean addItem(String groupName)
    {
        if(data.contains(groupName)==true) return false;
        data.addElement(groupName);
        //fireIntervalAdded(Object source, int index0, int index1);
        fireContentsChanged(this,0,0);
        return true;
    }

   /**
    * Returns the length of the list.
    */
    public int getSize()
    {
        return data.size();
    }

    public Object getElementAt(int index)
    {
        String groupName=(String)data.elementAt(index);
        return groupName;
    }
    /**
    * Returns the name at the specified index.
    */

}