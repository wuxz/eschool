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
public class ManualGroupListModel extends AbstractListModel
{
    private UserGroup userGroup;
    /**
    * ×éidÌØÕ÷
    */
    private String groupName;
    public ManualGroupListModel(){}
    public ManualGroupListModel(UserGroup userGroup,String groupName)
    {
        this.userGroup=userGroup;
        this.groupName=groupName;
        userGroup.addGroup(UserGroup.REMAINDER,UserGroup.REMAINDER);
    }
    public ManualGroupListModel(UserGroup userGroup)
    {
        this.userGroup=userGroup;
        userGroup.addGroup(UserGroup.REMAINDER,UserGroup.REMAINDER);
    }
    public void setUserGroup(UserGroup userGroup)
    {
        this.userGroup = userGroup;
    }
    public void setgroupName(String groupName)
    {
        this.groupName=groupName;
        fireContentsChanged(this,0,0);
    }
    public String getUserIDAt(int index)
    {
        String userID=null;
        Vector userIDs=userGroup.getUserIDsByGroupName(groupName);
        if(userIDs!=null&& userIDs.size()>0)
        {
            userID=(String)userIDs.elementAt(index);
        }
        return userID;
    }
   /**
    * Returns the length of the list.
    */
    public int getSize()
    {
        Vector userIds=userGroup.getUserIDsByGroupName(groupName);
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
        Vector userNames=userGroup.getUserNamesByGroupName(groupName);
        if(userNames!=null&& userNames.size()>0)
        {
            name=(String)userNames.elementAt(index);
        }
        return name;
    }

}