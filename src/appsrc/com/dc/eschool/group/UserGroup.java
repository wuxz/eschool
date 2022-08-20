package com.dc.eschool.group;
/**
 * Title: UserGroup
 * Description: 组的处理
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import java.util.Hashtable;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.util.Enumeration;
import javax.swing.event.EventListenerList;
import org.w3c.dom.*;
import java.io.*;
import com.dc.eschool.system.FileAccess;
import com.dc.eschool.DataModel.*;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.system.Student;
public class UserGroup implements GroupModel,GroupComponentListener
{

   /**
    * NODE's TAG NAME
    */
    public static final String GROUP = "Group";
    public static final String STUDENT = "Student";
    public static final String TEACHER = "Teacher";
    public static final String AUDITOR = "Auditor";
    public static final String REMAINDER = "Remainder";
    public static final String GROUP_CHARACTER = "Group_";

   /**
    * 随机分组每组人数
    */
    private static final double MAXINGROUP = 7;
    private SystemControl systemControl;
    private Document groupDoc;
    protected EventListenerList listenerList = new EventListenerList();
    public UserGroup()
    {
        try
        {
            DocumentBuilderFactory documentBuilderFactory;
            DocumentBuilder documentBuilder;
            documentBuilderFactory=DocumentBuilderFactory.newInstance();
            documentBuilder=documentBuilderFactory.newDocumentBuilder();
            groupDoc = documentBuilder.newDocument();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public UserGroup(Vector vecUsers,EschoolUser theTeacher,SystemControl sysControl)
    {
        this();
        this.systemControl=sysControl;
        try{
                EschoolUser eschoolUser;
                addTeacher(theTeacher);
                addGroup(GROUP,GROUP);
                addGroup(AUDITOR,AUDITOR);
                for(int i=0;i<vecUsers.size();i++)
                {
                    eschoolUser=(EschoolUser)vecUsers.elementAt(i);
                    if(eschoolUser.getUserType().equals(eschoolUser.ESCHOOL_AUDITOR))
                        addUser(AUDITOR,(EschoolUser)vecUsers.elementAt(i));
                    else
                        addUser(GROUP,(EschoolUser)vecUsers.elementAt(i));
                }
                randomGroup();
            }

            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }


    }
    public UserGroup(Vector vecData,SystemControl sysControl)
    {
        this();
        this.systemControl=sysControl;
        Hashtable htData;
        Vector vecGroup,vecUser;
        try
        {
            GroupUserModel teacherModel=(GroupUserModel)vecData.elementAt(0);
            this.addTeacher(teacherModel);
            for(int i=1;i<vecData.size();i++)
            {
                htData=(Hashtable)vecData.elementAt(i);
                vecGroup=(Vector)htData.get(this.GROUP);
                this.addGroup((String)vecGroup.elementAt(0),(String)vecGroup.elementAt(1));
                vecUser=(Vector)htData.get(this.STUDENT);
                for(int j=0;j<vecUser.size();j++)
                {
                    this.addUser((String)vecGroup.elementAt(0),(GroupUserModel)vecUser.elementAt(j));
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public UserGroup(String fileName)
    {
        loadGroupFromFile(fileName);
    }

   /**
    * METHOD: randomGroup
    * DESC  : 随机分组操作。
    * CREATE: 1.0 Ardy 2001-10-11
    * MODIFY:
    */
    public void randomGroup()
    {
        NodeList groupNodeList = null;
        int k=0;
        Vector vecUserNode=new Vector();
        groupNodeList=groupDoc.getDocumentElement().getElementsByTagName(GROUP);
        int num=groupNodeList.getLength();
        for (int i=0; i< num; i++)
        {
            NodeList userNodeList=groupNodeList.item(i).getChildNodes();
            for (int z=0; z< userNodeList.getLength(); z++)
            {
                vecUserNode.addElement(userNodeList.item(z));
            }
        }
        for(int i=0;i<num;i++)
        {
            groupDoc.getDocumentElement().removeChild(groupNodeList.item(0));
        }
        for(int j=0;j<vecUserNode.size();j++)
        {
            if(Math.ceil(j/MAXINGROUP)!=Math.ceil((j+1)/MAXINGROUP))
            {
                addGroup(String.valueOf(k),"");
                k++;
            }
            //NodeList n=groupDoc.getDocumentElement().getChildNodes();
            Element newElement=groupDoc.createElement(this.STUDENT);
            Element tmp=(Element)vecUserNode.elementAt(j);
            newElement.setAttribute(EschoolUser.ID,tmp.getAttribute(EschoolUser.ID));
            newElement.setAttribute(EschoolUser.NAME,tmp.getAttribute(EschoolUser.NAME));
            newElement.setAttribute(EschoolUser.TYPE,tmp.getAttribute(EschoolUser.TYPE));
            newElement.setAttribute(EschoolUser.STATUS,tmp.getAttribute(EschoolUser.STATUS));
            newElement.setAttribute(EschoolUser.GENDER,tmp.getAttribute(EschoolUser.GENDER));
            newElement.setAttribute(EschoolUser.IP,tmp.getAttribute(EschoolUser.IP));
            newElement.setAttribute(EschoolUser.HANDUP,tmp.getAttribute(EschoolUser.HANDUP));
            newElement.setAttribute(EschoolUser.EXAMING,tmp.getAttribute(EschoolUser.EXAMING));
            newElement.setAttribute(EschoolUser.CALLING,tmp.getAttribute(EschoolUser.CALLING));
            groupDoc.getDocumentElement().getLastChild().appendChild(newElement);
        }
        fireGroupDataChanged();
    }
   /**
    * METHOD: matchGroup
    * DESC  : 组匹配操作。
    * CREATE: 1.0 Ardy 2001-10-11
    * MODIFY:
    */
    public void matchGroup(UserGroup otherGroup)
    {
        NodeList groupNodeList,userNodeList;
        String userID;
        Element userElement;
        Element newElement;
        Document otherDoc;
        Vector vecTmp;
        otherDoc=otherGroup.getGroupDoc();
        groupNodeList=groupDoc.getDocumentElement().getChildNodes();
        int groupCount=groupNodeList.getLength();
        for (int i=0; i< groupNodeList.getLength(); i++)
        {
            vecTmp=new Vector();
            userNodeList=groupNodeList.item(i).getChildNodes();
            String userid=((Element)groupNodeList.item(i)).getAttribute(EschoolUser.ID);
            int userCount=userNodeList.getLength();
            for (int j=0; j< userNodeList.getLength(); j++)
            {
                Element element=(Element)userNodeList.item(j);
                userID=element.getAttribute(EschoolUser.ID);
                userElement=getUserElementByUserID(otherDoc,userID);
                if(userElement!=null)
                {
                    String id=userElement.getAttribute(EschoolUser.ID);
                    String name=userElement.getAttribute(EschoolUser.NAME);
                    String type=userElement.getAttribute(EschoolUser.TYPE);
                    String status=userElement.getAttribute(EschoolUser.STATUS);
                    String gender=userElement.getAttribute(EschoolUser.GENDER);
                    String ip=userElement.getAttribute(EschoolUser.IP);
                    String handup=userElement.getAttribute(EschoolUser.HANDUP);
                    String examing=userElement.getAttribute(EschoolUser.EXAMING);
                    String calling=userElement.getAttribute(EschoolUser.CALLING);
                    element.setAttribute(EschoolUser.ID,id);
                    element.setAttribute(EschoolUser.NAME,name);
                    element.setAttribute(EschoolUser.TYPE,type);
                    element.setAttribute(EschoolUser.STATUS,status);
                    element.setAttribute(EschoolUser.GENDER,gender);
                    element.setAttribute(EschoolUser.IP,ip);
                    element.setAttribute(EschoolUser.HANDUP,handup);
                    element.setAttribute(EschoolUser.EXAMING,examing);
                    element.setAttribute(EschoolUser.CALLING,calling);
                }
                else
                    vecTmp.addElement(element);

            }
            for(int k=0;k<vecTmp.size();k++)
            {
                groupNodeList.item(i).removeChild((Element)vecTmp.elementAt(k));
            }
        }
        //fireGroupDataChanged();
    }
   /**
    * METHOD: loadGroupFromFile
    * DESC  : 从文件中调入分组信息。
    * CREATE: 1.0 Ardy 2001-10-11
    * MODIFY:
    */
    public void loadGroupFromFile(String fileName)
    {
        FileAccess fileAccess=new FileAccess();
        Document doc=null;
        doc=fileAccess.xmlFileRead(fileName);
        if(doc==null)
        {
            System.out.println("错误的分组文件！");
            this.systemControl.mainFrm.sendInformation("错误的分组文件！");
            return;
        }
        groupDoc=doc;
        fireGroupDataChanged();
    }
   /**
    * METHOD: saveGroupToFile
    * DESC  : 把分组信息保存到文件。
    * CREATE: 1.0 Ardy 2001-10-11
    * MODIFY:
    */
    public void saveGroupToFile(String fileName)
    {
        FileAccess fileAccess=new FileAccess();
        boolean bl=fileAccess.xmlFileWrite(groupDoc,fileName);
        if(bl==false) return;
        //fireGroupDataChanged();
    }
   /**
    * METHOD: getGroupRemarks
    * DESC  : 返回组说明。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public Vector getGroupRemarks()
    {
        NodeList groupNodeList;
        Vector vecRemark=new Vector();
        groupNodeList=groupDoc.getDocumentElement().getChildNodes();
        for (int i=0; i< groupNodeList.getLength(); i++)
        {
            String strRemark=((Element)groupNodeList.item(i)).getAttribute(EschoolUser.REMARK);
            if(strRemark.equals(this.REMAINDER))
            continue;
            vecRemark.addElement(strRemark);
        }
        return vecRemark;
    }
   /**
    * METHOD: addGroup
    * DESC  : 增加一组。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public boolean addGroup(String groupName,String groupRemark)
    {
        Element newGroup;
        //if(groupDoc.getElementById(GROUP_CHARACTER+groupName)!=null)
        if(getGroupElementByGroupName(groupDoc,groupName)!=null)
            return false;
        newGroup=groupDoc.createElement(GROUP);
        newGroup.setAttribute(EschoolUser.ID,GROUP_CHARACTER+groupName);
        newGroup.setAttribute(EschoolUser.REMARK,groupRemark);
        groupDoc.getDocumentElement().appendChild(newGroup);
        fireGroupDataChanged();
        return true;
    }
   /**
    * METHOD: modifyGroupName
    * DESC  : 修改组的名称。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void modifyGroupName(String oldGroupName,String newGroupName)
    {
       // Element groupElement=groupDoc.getElementById(GROUP_CHARACTER+oldGroupName);
        Element groupElement=getGroupElementByGroupName(groupDoc,oldGroupName);
        if(groupElement!=null)
            groupElement.setAttribute(EschoolUser.ID,this.GROUP_CHARACTER+newGroupName);
        fireGroupDataChanged();
    }
   /**
    * METHOD: modifyGroupRemark
    * DESC  : 修改组的说明信息。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void modifyGroupRemark(String groupName,String newGroupRemark)
    {
        //Element groupElement=groupDoc.getElementById(GROUP_CHARACTER+groupName);
        Element groupElement=getGroupElementByGroupName(groupDoc,groupName);
        if(groupElement!=null)
            groupElement.setAttribute(EschoolUser.REMARK,newGroupRemark);
        fireGroupDataChanged();
    }
   /**
    * METHOD: delGroup
    * DESC  : 删除一组。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public boolean delGroup(String groupName)
    {
        //Element element=groupDoc.getElementById(GROUP_CHARACTER+groupName);
        Element element=getGroupElementByGroupName(groupDoc,groupName);
        if(element==null) return false;
        groupDoc.getDocumentElement().removeChild(element);
        fireGroupDataChanged();
        return true;
    }
    /**
    * METHOD: moveUser
    * DESC  : 移动一个人从一个组到另一个组。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void moveUser( String userID, String fromGroupName, String toGroupName )
    {
        //if(fromGroupName.equals(toGroupName)) return;
        Element toGroupElement=getGroupElementByGroupName(groupDoc,toGroupName);
        Element fromUserElement=getUserElementByUserID(groupDoc,userID);
        Element fromGroupElement=getGroupElementByGroupName(groupDoc,fromGroupName);
        fromGroupElement.removeChild(fromUserElement);
        toGroupElement.appendChild(fromUserElement);
        fireGroupDataChanged();

    }
   /**
    * METHOD: moveUser
    * DESC  : 移动一个人从一个组到另一个组(指定位置)。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void moveUser(String fromGroupName,String fromUserID,String toGroupName,String toUserID)
    {
        if(fromUserID==null) return;
        Element toGroupElement;
        Element toUserElement;
        Element fromGroupElement;
        Element fromUserElement;
        if(toUserID!=null)
        {
            toGroupElement=getGroupElementByGroupName(groupDoc,toGroupName);
            toUserElement=getUserElementByUserID(groupDoc,toUserID);
            fromGroupElement=getGroupElementByGroupName(groupDoc,fromGroupName);
            fromUserElement=getUserElementByUserID(groupDoc,fromUserID);
            if(toGroupElement==null||fromUserElement==null||fromGroupElement==null) return;
            if(!fromGroupName.equals(toGroupName))
                fromGroupElement.removeChild(fromUserElement);
            toGroupElement.insertBefore(fromUserElement,toUserElement);
        }
        else
        {
            toGroupElement=getGroupElementByGroupName(groupDoc,toGroupName);
            fromUserElement=getUserElementByUserID(groupDoc,fromUserID);
            fromGroupElement=getGroupElementByGroupName(groupDoc,fromGroupName);
            fromGroupElement.removeChild(fromUserElement);
            toGroupElement.appendChild(fromUserElement);
        }
        fireGroupDataChanged();
    }
   /**
    * METHOD: addTeacher
    * DESC  :增加老师。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void addTeacher(EschoolUser theTeacher )
    {
        try
        {
            Element el;
            el=groupDoc.createElement(this.TEACHER);
            el.setAttribute(EschoolUser.ID,EschoolUser.TEACHER_CHARCTER+theTeacher.getUserID());
            el.setAttribute(EschoolUser.NAME,theTeacher.getUserName());
            el.setAttribute(EschoolUser.TYPE,theTeacher.getUserType());
            el.setAttribute(EschoolUser.STATUS,theTeacher.getUserStatus());
            el.setAttribute(EschoolUser.GENDER,theTeacher.getUserGender());
            el.setAttribute(EschoolUser.IP,theTeacher.getUserIP());
            el.setAttribute(EschoolUser.HANDUP,theTeacher.getHandUp());
            el.setAttribute(EschoolUser.EXAMING,theTeacher.getExaming());
            el.setAttribute(EschoolUser.CALLING,theTeacher.getCalling());
            groupDoc.appendChild(el);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        fireGroupDataChanged();
    }
    /**
    * METHOD: addTeacher
    * DESC  :增加老师。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void addTeacher(GroupUserModel theTeacher )
    {
        try
        {
            Element el;
            el=groupDoc.createElement(this.TEACHER);
            el.setAttribute(EschoolUser.ID,theTeacher.getUserID());
            el.setAttribute(EschoolUser.NAME,theTeacher.getUserName());
            el.setAttribute(EschoolUser.TYPE,theTeacher.getUserType());
            el.setAttribute(EschoolUser.STATUS,theTeacher.getUserStatus());
            el.setAttribute(EschoolUser.GENDER,theTeacher.getUserGender());
            el.setAttribute(EschoolUser.IP,theTeacher.getUserIP());
            el.setAttribute(EschoolUser.HANDUP,EschoolUser.ABESENT);
            el.setAttribute(EschoolUser.EXAMING,EschoolUser.ABESENT);
            el.setAttribute(EschoolUser.CALLING,EschoolUser.ABESENT);
            groupDoc.appendChild(el);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        fireGroupDataChanged();
    }
   /**
    * METHOD: getUser
    * DESC  :返回一个学生(EschoolUser)。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public EschoolUser getUser(String groupName,String userID)
    {
        Element theElement;
        theElement=getUserElementByUserID(groupDoc,userID,groupName);
        String id=theElement.getAttribute(EschoolUser.ID);
        String name=theElement.getAttribute(EschoolUser.NAME);
        String type=theElement.getAttribute(EschoolUser.TYPE);
        String status=theElement.getAttribute(EschoolUser.STATUS);
        String gender=theElement.getAttribute(EschoolUser.GENDER);
        String ip=theElement.getAttribute(EschoolUser.IP);
        String handup=theElement.getAttribute(EschoolUser.HANDUP);
        String examing=theElement.getAttribute(EschoolUser.EXAMING);
        String calling=theElement.getAttribute(EschoolUser.CALLING);
        int pos=EschoolUser.STUDENT_CHARCTER.length();
        id=id.substring(pos);
        EschoolUser eschoolUser=new EschoolUser(id,name,type,status,gender,ip,handup,examing,calling);///xxx
        return eschoolUser;
    }
    /**
    * METHOD: getAllUser
    * DESC  :返回所有学生(Vector)。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public Vector getAllUser()
    {
        Element theElement;
        Vector vecGroup,vecUser,vecRemark;
        Vector vecUserModel,vecGroupData;
        Hashtable htTmp;
        Vector vecData=new Vector();
        GroupUserModel userModel;
        userModel=this.getTeacherData();
        vecData.addElement(userModel);
        vecGroup=this.getGroupNames();
        vecRemark=this.getGroupRemarks();
        if(vecGroup==null) return null;
        for(int i=0;i<vecGroup.size();i++)
        {
            String groupName=(String)vecGroup.elementAt(i);
            String groupRemark=(String)vecRemark.elementAt(i);
            vecUser=this.getUserIDsByGroupName(groupName);
            htTmp=new Hashtable();
            vecUserModel=new Vector();
            vecGroupData=new Vector();
            for(int j=0;j<vecUser.size();j++)
            {
                String userID=(String)vecUser.elementAt(j);
                theElement=getUserElementByUserID(groupDoc,userID,groupName);
                userModel=new GroupUserModel();
                String id=theElement.getAttribute(EschoolUser.ID);
                String name=theElement.getAttribute(EschoolUser.NAME);
                String type=theElement.getAttribute(EschoolUser.TYPE);
                String status=theElement.getAttribute(EschoolUser.STATUS);
                String gender=theElement.getAttribute(EschoolUser.GENDER);
                String ip=theElement.getAttribute(EschoolUser.IP);
                String handup=theElement.getAttribute(EschoolUser.HANDUP);
                String examing=theElement.getAttribute(EschoolUser.EXAMING);
                String calling=theElement.getAttribute(EschoolUser.CALLING);
                userModel.setUserID(id);
                userModel.setUserName(name);
                userModel.setUserType(type);
                userModel.setUserStatus(status);
                userModel.setUserGender(gender);
                userModel.setUserIP(ip);
                userModel.setHandUp(handup);
                userModel.setExaming(examing);
                //userModel.setCalling(calling);
                vecUserModel.addElement(userModel);
            }
            vecGroupData.addElement(groupName);
            vecGroupData.addElement(groupRemark);
            htTmp.put(this.GROUP,vecGroupData);
            htTmp.put(this.STUDENT,vecUserModel);
            vecData.addElement(htTmp);
        }
        return vecData;
    }
   /**
    * METHOD: getUserNameByUserID
    * DESC  :返回一个学生名。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public String getUserNameByUserID(String userID)
    {
        Element theElement;
        //theElement=groupDoc.getElementById(EschoolUser.STUDENT_CHARCTER+userID);
        theElement=getUserElementByUserID(groupDoc,userID);
        String userName=theElement.getAttribute(EschoolUser.NAME);
        return userName;
    }
    /**
    * METHOD: getUserTypeByUserID
    * DESC  :返回一个学生类型。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public String getUserTypeByUserID(String userID)
    {
        Element theElement;
        theElement=getUserElementByUserID(groupDoc,userID);
        String userType=theElement.getAttribute(EschoolUser.TYPE);
        return userType;
    }
    /**
    * METHOD: getUserIPByUserID
    * DESC  :返回一个学生类型。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public String getUserIPByUserID(String userID)
    {
        Element theElement;
        theElement=getUserElementByUserID(groupDoc,userID);
        String userIP=theElement.getAttribute(EschoolUser.IP);
        return userIP;
    }
    /**
    * METHOD: getUserStatusByUserID
    * DESC  :返回一个学生状态。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public String getUserStatusByUserID(String userID)
    {
        Element theElement;
        theElement=getUserElementByUserID(groupDoc,userID);
        String userStatus=theElement.getAttribute(EschoolUser.STATUS);
        return userStatus;
    }
    /**
    * METHOD: getgroupNameByUserID
    * DESC  :返回一个学生所在组名。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public String getGroupNameByUserID(String userID)
    {
        Element theElement;
        theElement=getUserElementByUserID(groupDoc,userID);
        String groupName=((Element)theElement.getParentNode()).getAttribute(EschoolUser.ID);
        int pos=this.GROUP_CHARACTER.length();
        groupName=groupName.substring(pos);
        return groupName;
    }
   /**
    * METHOD: getTheTeacher
    * DESC  :返回老师。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public EschoolUser getTheTeacher()
    {
        Element theElement=groupDoc.getDocumentElement();
        String id=theElement.getAttribute(EschoolUser.ID);
        String name=theElement.getAttribute(EschoolUser.NAME);
        String type=theElement.getAttribute(EschoolUser.TYPE);
        String status=theElement.getAttribute(EschoolUser.STATUS);
        String gender=theElement.getAttribute(EschoolUser.GENDER);
        String ip=theElement.getAttribute(EschoolUser.IP);
        String handup=theElement.getAttribute(EschoolUser.HANDUP);
        String examing=theElement.getAttribute(EschoolUser.EXAMING);
        String calling=theElement.getAttribute(EschoolUser.CALLING);
        int pos=EschoolUser.TEACHER_CHARCTER.length();
        id=id.substring(pos);
        EschoolUser eschoolUser=new EschoolUser(id,name,type,status,gender,ip,handup,examing,calling);//xxx
        return eschoolUser;
    }
    /**
    * METHOD: getTeacherData
    * DESC  :返回老师。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public GroupUserModel getTeacherData()
    {
        Element theElement=groupDoc.getDocumentElement();
        String id=theElement.getAttribute(EschoolUser.ID);
        String name=theElement.getAttribute(EschoolUser.NAME);
        String type=theElement.getAttribute(EschoolUser.TYPE);
        String status=theElement.getAttribute(EschoolUser.STATUS);
        String gender=theElement.getAttribute(EschoolUser.GENDER);
        String ip=theElement.getAttribute(EschoolUser.IP);
        String handup=theElement.getAttribute(EschoolUser.HANDUP);
        String examing=theElement.getAttribute(EschoolUser.EXAMING);
        GroupUserModel groupUserModel=new GroupUserModel(id,name,type,status,gender,ip,handup,examing);
        return groupUserModel;
    }
   /**
    * METHOD: addUser
    * DESC  :增加一学生。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public boolean addUser(String groupName,EschoolUser newUser)
    {
        //Element element=groupDoc.getElementById(GROUP_CHARACTER+groupName);
        try
        {
            Element element=getGroupElementByGroupName(groupDoc,groupName);
            if(element==null) return false;
            String id=newUser.getUserID();
            if(getUserElementByUserID(groupDoc,id)!=null) return false;
            Element el=groupDoc.createElement(STUDENT);
            el.setAttribute(EschoolUser.ID,EschoolUser.STUDENT_CHARCTER+newUser.getUserID());
            el.setAttribute(EschoolUser.NAME,newUser.getUserName());
            el.setAttribute(EschoolUser.TYPE,newUser.getUserType());
            el.setAttribute(EschoolUser.STATUS,newUser.getUserStatus());
            el.setAttribute(EschoolUser.GENDER,newUser.getUserGender());
            el.setAttribute(EschoolUser.IP,newUser.getUserIP());
            el.setAttribute(EschoolUser.HANDUP,newUser.getHandUp());
            el.setAttribute(EschoolUser.EXAMING,newUser.getExaming());
            el.setAttribute(EschoolUser.CALLING,newUser.getCalling());
            element.appendChild(el);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        fireGroupDataChanged();
        return true;
    }
    /**
    * METHOD: addUser
    * DESC  :增加一学生。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public boolean addUser(String groupName,GroupUserModel newUser)
    {
        try
        {
            Element element=getGroupElementByGroupName(groupDoc,groupName);
            if(element==null) return false;
            String id=newUser.getUserID();
            if(getUserElementByUserID(groupDoc,id)!=null)
            {
                System.out.println("id already exists");
                return false;
            }
            Element el=groupDoc.createElement(this.STUDENT);
            el.setAttribute(EschoolUser.ID,newUser.getUserID());
            el.setAttribute(EschoolUser.NAME,newUser.getUserName());
            el.setAttribute(EschoolUser.TYPE,newUser.getUserType());
            el.setAttribute(EschoolUser.STATUS,newUser.getUserStatus());
            el.setAttribute(EschoolUser.GENDER,newUser.getUserGender());
            el.setAttribute(EschoolUser.IP,newUser.getUserIP());
            el.setAttribute(EschoolUser.HANDUP,EschoolUser.ABESENT);
            el.setAttribute(EschoolUser.EXAMING,EschoolUser.ABESENT);
            el.setAttribute(EschoolUser.CALLING,EschoolUser.ABESENT);
            element.appendChild(el);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        fireGroupDataChanged();
        return true;
    }
   /**
    * METHOD: delUser
    * DESC  : 删除一学生。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void delUser(String groupName,EschoolUser oldUser)
    {
        //Element element=groupDoc.getElementById(GROUP_CHARACTER+groupName);
        Element element=getGroupElementByGroupName(groupDoc,groupName);
        if(element!=null)
        {
            element.removeChild(oldUser.getUserElement());
            fireGroupDataChanged();
        }
    }
   /**
    * METHOD: getUserIDsByGroupName
    * DESC  : 获得某组学生ID。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public Vector getUserIDsByGroupName( String groupName )
    {
        NodeList userNodeList;
        String userID;
        Vector userIDs=new Vector();
        int pos;
        //Element groupElement=groupDoc.getElementById(GROUP_CHARACTER+groupName);
        Element groupElement=getGroupElementByGroupName(groupDoc,groupName);
        if(groupElement==null)
            return null;
        userNodeList=groupElement.getChildNodes();
        for(int i=0;i<userNodeList.getLength();i++)
        {
            userID=((Element)userNodeList.item(i)).getAttribute(EschoolUser.ID);
            pos=EschoolUser.STUDENT_CHARCTER.length();
            userID=userID.substring(pos);
            userIDs.addElement(userID);
        }
        return userIDs;
    }
   /**
    * METHOD: getUserIDsByUserID
    * DESC  : 获得某组学生ID。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public Vector getUserIDsByUserID( String userID)
    {
        NodeList userNodeList;
        String id;
        Vector userIDs=new Vector();
        int pos;
        //Element studentElement=groupDoc.getElementById(EschoolUser.STUDENT_CHARCTER+userID);
        Element studentElement=getUserElementByUserID(groupDoc,userID);
        Node groupNode=studentElement.getParentNode();
        userNodeList=groupNode.getChildNodes();
        for(int i=0;i<userNodeList.getLength();i++)
        {
            id=((Element)userNodeList.item(i)).getAttribute(EschoolUser.ID);
            pos=EschoolUser.STUDENT_CHARCTER.length();
            id=id.substring(pos);
            userIDs.addElement(id);
        }
        return userIDs;
    }
   /**
    * METHOD: getUserCountOfGroup
    * DESC  : 获得某组学生人数。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public int getUserCountOfGroup( String groupName )
    {
        Vector tmp=new Vector();
        tmp=getUserIDsByGroupName(groupName);
        return tmp.size();
    }
   /**
    * METHOD: getPresentUsersByUserID
    * DESC  : 获得某组学生ID。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public Vector getPresentUsersByUserID( String userID)
    {
        NodeList userNodeList;
        String id,status;
        Vector userIDs=new Vector();
        int pos;
        //Element studentElement=groupDoc.getElementById(EschoolUser.STUDENT_CHARCTER+userID);
        Element studentElement=getUserElementByUserID(groupDoc,userID);
        Node groupNode=studentElement.getParentNode();
        userNodeList=groupNode.getChildNodes();
        for(int i=0;i<userNodeList.getLength();i++)
        {
            status=((Element)userNodeList.item(i)).getAttribute(EschoolUser.STATUS);
            if(!status.equals(EschoolUser.PRESENT))
                continue;
            id=((Element)userNodeList.item(i)).getAttribute(EschoolUser.ID);
            pos=EschoolUser.STUDENT_CHARCTER.length();
            id=id.substring(pos);
            userIDs.addElement(id);
        }
        return userIDs;
    }
   /**
    * METHOD: getUserNamesByGroupName
    * DESC  : 获得某组学生名称。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public Vector getUserNamesByGroupName( String groupName )
    {
        NodeList userNodeList;
        String userName;
        Vector userNames=new Vector();
        //Element groupElement=groupDoc.getElementById(GROUP_CHARACTER+groupName);
        Element groupElement=getGroupElementByGroupName(groupDoc,groupName);
        userNodeList=groupElement.getChildNodes();
        for(int i=0;i<userNodeList.getLength();i++)
        {
            userName=((Element)userNodeList.item(i)).getAttribute(EschoolUser.NAME);
            userNames.addElement(userName);
        }
        return userNames;
    }

   /**
    * METHOD: getUserNamesByUserID
    * DESC  : 获得某组学生名称。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public Vector getUserNamesByUserID( String userID)
    {
        NodeList userNodeList;
        String userName;
        Vector userNames=new Vector();
        //Element studentElement=groupDoc.getElementById(EschoolUser.STUDENT_CHARCTER+userID);
        Element studentElement=getUserElementByUserID(groupDoc,userID);
        Node groupNode=studentElement.getParentNode();
        userNodeList=groupNode.getChildNodes();
        for(int i=0;i<userNodeList.getLength();i++)
        {
            userName=((Element)userNodeList.item(i)).getAttribute(EschoolUser.NAME);
            userNames.addElement(userName);
        }
        return userNames;

    }
   /**
    * METHOD: changeUserName
    * DESC  : 修改学生名称。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void changeUserName(String userID,String userName)
    {
        if(userID==null) return;
        Element userElement=getUserElementByUserID(groupDoc,userID);
        userElement.setAttribute(EschoolUser.NAME,userName);
        fireGroupDataChanged();
    }
   /**
    * METHOD: changeUserStatus
    * DESC  : 修改学生状态。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void changeUserStatus(String userID,String stauts)
    {
        if(userID==null) return;
        Element userElement=getUserElementByUserID(groupDoc,userID);
        userElement.setAttribute(EschoolUser.STATUS,stauts);
        fireGroupDataChanged();
    }
   /**
    * METHOD: changeUserType
    * DESC  : 修改学生类别。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void changeUserType(String userID,String type)
    {
        if(userID==null) return;
        Element userElement=getUserElementByUserID(groupDoc,userID);
        userElement.setAttribute(EschoolUser.TYPE,type);
        fireGroupDataChanged();
    }
   /**
    * METHOD: changeUserGender
    * DESC  : 修改学生性别。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void changeUserGender(String userID,String gender)
    {
        if(userID==null) return;
        Element userElement=getUserElementByUserID(groupDoc,userID);
        userElement.setAttribute(EschoolUser.GENDER,gender);
        fireGroupDataChanged();
    }
   /**
    * METHOD: changeUserIP
    * DESC  : 修改学生IP。
    * CREATE: 1.0 Ardy 2001-10-26
    * MODIFY:
    */
    public void changeUserIP(String userID,String userIP)
    {

        if(userID==null) return;
        Element userElement=getUserElementByUserID(groupDoc,userID);
        userElement.setAttribute(EschoolUser.IP,userIP);
        fireGroupDataChanged();
    }
   /**
    * METHOD: getGroupDoc
    * DESC  : 获得组DOC。
    * CREATE: 1.0 Ardy 2001-10-30
    * MODIFY:
    */
    public Document getGroupDoc()
    {
        return groupDoc;
    }
   /**
    * METHOD: setGroupDoc
    * DESC  : 设置组DOC。
    * CREATE: 1.0 Ardy 2001-10-30
    * MODIFY:
    */
    public void setGroupDocument(Document doc)
    {
        this.groupDoc=doc;
        fireGroupDataChanged();
    }
   /**
    * METHOD: copyDocument
    * DESC  : 复制Document
    * CREATE: 1.0 Ardy 2001-10-16
    * MODIFY:
    */
    private Document copyDocument()
    {
        Document newDoc=null;
        try
        {
            DocumentBuilderFactory documentBuilderFactory;
            DocumentBuilder documentBuilder;
            documentBuilderFactory=DocumentBuilderFactory.newInstance();
            documentBuilder=documentBuilderFactory.newDocumentBuilder();
            newDoc = documentBuilder.newDocument();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            Source source = new DOMSource(groupDoc);
            Result result = new DOMResult(newDoc);
            t.transform(source,result);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return newDoc;
    }
   /**
    * METHOD: copyTo
    * DESC  : 复制组数据
    * CREATE: 1.0 Ardy 2001-10-16
    * MODIFY:
    */
    public void copyTo( UserGroup otherUserGroup )
    {
        Document d = this.copyDocument();
        otherUserGroup.setGroupDocument(d);
    }
    /**
    * METHOD: getGroupElementByGroupName
    * DESC  : 通过组名获取组Element
    * CREATE: 1.0 Ardy 2001-10-16
    * MODIFY:
    */
    public Element getGroupElementByGroupName(Document document,String groupName)
    {
        String name,groupNM;
        Element element=null;
        if(groupName.indexOf(GROUP_CHARACTER)!=-1)
            groupNM=groupName;
        else
            groupNM=GROUP_CHARACTER+groupName;
        NodeList nodeList=document.getDocumentElement().getChildNodes();
        for(int i=0;i<nodeList.getLength();i++)
        {
            element=(Element)nodeList.item(i);
            name=element.getAttribute(EschoolUser.ID);
            if(groupNM.equals(name))
                break;
            element=null;
        }
        return element;
    }

    /**
    * METHOD: getUserElementByUserID
    * DESC  : 通过用户ID获取Element
    * CREATE: 1.0 Ardy 2001-10-16
    * MODIFY:
    */
    public Element getUserElementByUserID(Document document,String userID,String groupName)
    {
        String id,userid;
        Element groupElement=null;
        Element userElement=null;
        groupElement=getGroupElementByGroupName(document,groupName);
        if(groupElement==null) return null;
        NodeList nodeList=groupElement.getChildNodes();
        if(userID.indexOf(EschoolUser.STUDENT_CHARCTER)!=-1)
            userid=userID;
        else
            userid=EschoolUser.STUDENT_CHARCTER+userID;
        for(int i=0;i<nodeList.getLength();i++)
        {
            userElement=(Element)nodeList.item(i);
            id=userElement.getAttribute(EschoolUser.ID);
            if(userid.equals(id))
                break;
            userElement=null;
        }
        return userElement;
    }
    /**
    * METHOD: getUserElementByUserID
    * DESC  : 通过用户ID获取Element
    * CREATE: 1.0 Ardy 2001-10-16
    * MODIFY:
    */
    public Element getUserElementByUserID(Document document,String userID)
    {
        String id,userid;
        Element userElement=null;
        NodeList userList,groupList;
        int t=1;
        if(userID.indexOf(EschoolUser.STUDENT_CHARCTER)!=-1)
            userid=userID;
        else
            userid=EschoolUser.STUDENT_CHARCTER+userID;
       groupList=document.getDocumentElement().getChildNodes();
        for(int i=0;i<groupList.getLength();i++)
        {
            userList=groupList.item(i).getChildNodes();
            for(int j=0;j<userList.getLength();j++)
            {
                userElement=(Element)userList.item(j);
                id=userElement.getAttribute(EschoolUser.ID);
                if(userid.equals(id))
                {
                    t=0;
                    break;
                 }
                userElement=null;
            }
            if(t==0) break;
        }
        return userElement;
    }
   /**
    * Forwards the given notification event to all
    * <code>GroupModelListener</code> that registered
    * themselves as listeners for this Group model.
    *
    * @param e  the event to be forwarded
    *
    * @see #addGroupModelListener
    * @see GroupModelEvent
    * @see EventListenerList
    */
    public void fireGroupDataChanged()
    {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2)
        {
            if (listeners[i]==GroupModelListener.class)
            {
                ((GroupModelListener)listeners[i+1]).groupChanged();
            }
        }
    }
    //----------->>Following,implements Class GroupModel.
   /**
    * Adds a listener to the list that is notified each time a change to the data model occurs.
    */
    public void addGroupModelListener( GroupModelListener l )
    {
        listenerList.add(GroupModelListener.class, l);
    }

   /**
    * Removes a listener from the list that is notified each time a change to the data model occurs.
    */
    public void removeGroupModelListener( GroupModelListener l )
    {
        listenerList.remove(GroupModelListener.class, l);
    }

   /**
    * 返回Group的老师
    */
    public Person getTeacher()
    {
        Person person;
        person=getTheTeacher();
        return person;
    }

    /**
    * 返回GroupNames
    */
    public Vector getGroupNames()
    {
        NodeList groupNodeList;
        int len;
        Vector vecGroupName=new Vector();
        groupNodeList=groupDoc.getDocumentElement().getChildNodes();
        for (int i=0; i< groupNodeList.getLength(); i++)
        {
            String strGroupName=((Element)groupNodeList.item(i)).getAttribute(EschoolUser.ID);
            len=this.GROUP_CHARACTER.length();
            strGroupName=strGroupName.substring(len);
            if(strGroupName.equals(this.REMAINDER))
            continue;
            vecGroupName.addElement(strGroupName);
        }
        return vecGroupName;
    }

   /**
    * 返回学生一组登录名
    */
    public Vector getStudentLoginNames( String groupName )
    {
        return getUserIDsByGroupName(groupName);
    }

   /**
    * 返回一个学生
    */
    public Person getStudent( String groupName, String loginName )
    {
        Person person;
        person=getUser(groupName,loginName);
        return person;
    }

  /**
   * 返回旁听生登录名
   */
    public Vector getAuditorLoginNames()
    {
        return new Vector();
    }

  /**
   * 返回旁听生
   */
    public Person getAuditor( String loginName )
    {
        return null;
    }

   /**
    * 移动fromLoginName从fromGroupName到toGroupName的toLoginName前,
    */
    public void movePerson( String fromGroupName, String fromLoginName, String toGroupName, String toLoginName )
    {
        this.moveUser(fromGroupName,fromLoginName,toGroupName,toLoginName);
        Hashtable htUser=this.systemControl.getHtStudentIDInterface();
        for(Enumeration er=htUser.elements(); er.hasMoreElements();)
        {
            try
            {
                Student student=(Student)er.nextElement();
                student.moveUser(fromGroupName,fromLoginName,toGroupName,toLoginName);
            }
            catch(Exception e)
            {
                System.out.println("movePerson Exception:"+e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public void personSelected(GroupComponentEvent e, Person selectedPerson)
   {
      //2001-12-07由刘怀忠修改
      if (selectedPerson !=null && (selectedPerson.calling() || selectedPerson.isHandUp()))
      {
        String userID=selectedPerson.getLoginName();
        changeHandUp(userID,false);
        changeCalling(userID,false);
        fireGroupDataChanged();
      }
   }
   public void changeExaming(String userID,boolean b)
   {
      if(userID==null) return;
      String examing;
      if(b==true)
          examing=EschoolUser.PRESENT;
      else
          examing=EschoolUser.ABESENT;
        Element userElement=getUserElementByUserID(groupDoc,userID);
        userElement.setAttribute(EschoolUser.EXAMING,examing);
        fireGroupDataChanged();
    }
    public void changeHandUp(String userID,boolean b)
   {
      if(userID==null) return;
      String handup;
      if(b==true)
          handup=EschoolUser.PRESENT;
      else
          handup=EschoolUser.ABESENT;
        Element userElement=getUserElementByUserID(groupDoc,userID);
        userElement.setAttribute(EschoolUser.HANDUP,handup);
        fireGroupDataChanged();
    }
    public void changeCalling(String userID,boolean b)
   {
      if(userID==null) return;
      String calling;
      if(b==true)
          calling=EschoolUser.PRESENT;
      else
          calling=EschoolUser.ABESENT;
        Element userElement=getUserElementByUserID(groupDoc,userID);
        userElement.setAttribute(EschoolUser.CALLING,calling);
        fireGroupDataChanged();
    }
}