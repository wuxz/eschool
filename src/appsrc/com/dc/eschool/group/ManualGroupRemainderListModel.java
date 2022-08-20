package com.dc.eschool.group;

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
public class ManualGroupRemainderListModel extends AbstractListModel
{

    private UserGroup userGroup;
    private static final String GROUPNAME="Remainder";
    public ManualGroupRemainderListModel(){}
    public ManualGroupRemainderListModel(UserGroup userGroup)
    {
        this.userGroup=userGroup;
    }
    public void setUserGroup(UserGroup userGroup)
    {
        this.userGroup = userGroup;
    }
    public void updateData()
    {
        fireContentsChanged(this,0,0);
    }
   /**
    * Returns the length of the list.
    */
    public int getSize()
    {
        Vector userIds=userGroup.getUserIDsByGroupName(GROUPNAME);
        if(userIds!=null&&userIds.size()>0)
            return userIds.size();
        else
            return 0;
    }

   /**
    * Returns the value at the specified index.
    */
    public Object getElementAt(int index)
    {
        String name=null;
        Vector userNames=userGroup.getUserNamesByGroupName(GROUPNAME);
        if(userNames!=null&& userNames.size()>0)
        {
            name=(String)userNames.elementAt(index);
        }
        return name;
    }

}