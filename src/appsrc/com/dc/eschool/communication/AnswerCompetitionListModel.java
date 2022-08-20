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
import com.dc.eschool.group.*;
import java.util.Vector;
public class AnswerCompetitionListModel extends AbstractListModel
{
    private Vector data=null;
    private UserGroup userGroup=null;
    public AnswerCompetitionListModel(UserGroup userGroup)
    {
        data=new Vector();
        this.userGroup=userGroup;
    }

    public void setData(Vector data)
    {
        this.data=data;
        fireContentsChanged(this,0,0);
    }
    public Vector getUserIDData()
    {
        return data;
    }
    public Vector getGroupData()
    {
        Vector groups=new Vector();
        for(int i=0;i<data.size();i++)
        {
            String userID=(String)data.elementAt(i);
            String groupName=this.userGroup.getGroupNameByUserID(userID);
            groups.addElement(groupName);
        }
        return groups;
    }
    public boolean addItem(String userID)
    {
        if(data.contains(userID)==true) return false;
        data.addElement(userID);
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

   /**
    * Returns the value at the specified index.
    */
    public Object getElementAt(int index)
    {
        String userID=(String)data.elementAt(index);
        String userName=this.userGroup.getUserNameByUserID(userID);
        String groupName=this.userGroup.getGroupNameByUserID(userID);
        return userName+"   "+groupName;
    }

}