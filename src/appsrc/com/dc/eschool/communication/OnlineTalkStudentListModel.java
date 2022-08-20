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
import java.util.Hashtable;
import java.util.Vector;
import com.dc.eschool.group.*;
public class OnlineTalkStudentListModel extends AbstractListModel
{
    private Hashtable data;
    private UserGroup userGroup=null;
    private String currentGroup=null;
    private String userType=null;
    public OnlineTalkStudentListModel(UserGroup userGroup,String userType)
    {
        data=new Hashtable();
        this.userGroup=userGroup;
        this.userType=userType;
    }
    public OnlineTalkStudentListModel(UserGroup userGroup)
    {
        data=new Hashtable();
        this.userGroup=userGroup;
    }
    public void setData(Hashtable data)
    {
        this.data=data;
        System.out.println("setData data's size"+data.size());
        Vector tmp=(Vector)this.data.get(this.currentGroup);
        tmp.insertElementAt("¿œ ¶",0);
        this.data.put(this.currentGroup,tmp);
        fireContentsChanged(this,0,0);
    }
    public void setCurrentGroup(String groupName)
    {
        this.currentGroup=groupName;
        System.out.println("this.currentGroup="+this.currentGroup);
        System.out.println("this.data's size="+this.data.size());
        fireContentsChanged(this,0,0);
    }
    public Hashtable getUserIDData()
    {
        return data;
    }

    public boolean addItem(String userID)
    {
        try
        {
            String groupName=this.userGroup.getGroupNameByUserID(userID);
            Vector userIDs=(Vector)data.get(groupName);
            if(userIDs==null)
            {
                userIDs=new Vector();
                userIDs.addElement(userID);
                data.put(groupName,userIDs);
            }
            else
            {
                if(userIDs.contains(userID)==true) return false;
                userIDs.addElement(userID);
                data.put(groupName,userIDs);
            }
            fireContentsChanged(this,0,0);
        }
        catch(Exception e)
        {
            System.out.println("OnlineTalkStudentListModel's addItem Exception:"+e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

   /**
    * Returns the length of the list.
    */
    public int getSize()
    {
        int size=0;
        try
        {
            if(this.currentGroup==null) return size;
            Vector userIDs=(Vector)data.get(this.currentGroup);
            if(userIDs==null) return size;
            size=userIDs.size();

        }
        catch(Exception e)
        {
            System.out.println("OnlineTalkStudentListModel's getSize Exception:"+e.getMessage());
            e.printStackTrace();
        }
        return size;
    }

    public Object getElementAt(int index)
    {
        Vector userIDs=(Vector)data.get(this.currentGroup);
        String userID=(String)userIDs.elementAt(index);
        if(index==0&&this.userType!=null&&!this.userType.equals(EschoolUser.ESCHOOL_TEACHER)) return userID;
        String userName=this.userGroup.getUserNameByUserID(userID);
        return userName;
    }
    public Vector getCurrentGroupUserIDs()
    {
        if(this.currentGroup==null) return null;
        Vector userIDs=(Vector)data.get(this.currentGroup);
        if (userIDs==null || userIDs.size() == 0)
           return null;
        if(this.userType!=null&&!this.userType.equals(EschoolUser.ESCHOOL_TEACHER))
            userIDs.remove(0);
        return userIDs;
    }
    /**
    * Returns the name at the specified index.
    */
    public Object getUserIDAt(int index)
    {
        Vector userIDs=(Vector)data.get(this.currentGroup);
        String userID=(String)userIDs.elementAt(index);
        return userID;
    }

}