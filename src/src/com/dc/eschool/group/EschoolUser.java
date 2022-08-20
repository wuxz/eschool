package com.dc.eschool.group;
/**
 * Title: User
 * Description: 登录用户数据结构
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import com.dc.eschool.group.Person;
import org.w3c.dom.*;
public class EschoolUser implements Person,java.io.Serializable
{
   /**
    * 用户Attributes
    */
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String REMARK = "remark";
    private static final String TYPE = "type";
    private static final String STATUS = "status";
    private static final String GENDER = "gender";
    private static final String IP  ="ip";
   /**
    * 人类型: 教师
    */
    public static final String ESCHOOL_TEACHER = "0";

   /**
    * 人类型: 学生
    */
    public static final String ESCHOOL_STUDENT = "1";

   /**
    * 人类型: 旁听
    */
    public static final String ESCHOOL_AUDITOR = "2";

   /**
    * 性别: 男
    */
    private static final String ESCHOOL_MALE = "0";

   /**
    * 性别: 女
    */
    private static final String ESCHOOL_FEMALE = "1";
   /**
    * 用户上课与否
    */
    private static final String PRESENT = "T";
    private static final String ABESENT = "F";

   /**
    *学生id特征
    */
    private static final String STUDENT_CHARCTER = "Student_";

    private Element userElement;
    public EschoolUser() {}
    public EschoolUser(String newUserID,String newUserName,String newUserType,
		String newUserStatus,String newUserGender,String newUserIP)
    {
        userElement.setAttribute(ID,STUDENT_CHARCTER+newUserID);
        userElement.setAttribute(NAME,newUserName);
        userElement.setAttribute(TYPE,newUserType);
        userElement.setAttribute(STATUS,newUserStatus);
        userElement.setAttribute(GENDER,newUserGender);
        userElement.setAttribute(IP,newUserIP);
    }
    public EschoolUser(Element newElement)
    {
        this.userElement=newElement;
    }
   /**
    *  用户ID
    */
    public void setUserID(String newUserID)
    {
        System.out.println(newUserID+":::ESChoolUser");
        userElement.setAttribute(ID,STUDENT_CHARCTER+newUserID);
        System.out.println(newUserID+":::ESChoolUser");
    }
    public String getUserID()
    {
        String id=userElement.getAttribute(ID);
        int pos=id.indexOf(STUDENT_CHARCTER);
        id=id.substring(pos);
        return id;
    }
   /**
    *  用户名称
    */
    public void setUserName(String newUserName)
    {
        userElement.setAttribute(NAME,newUserName);
    }
    public String getUserName()
    {
        return userElement.getAttribute(NAME);
    }
   /**
    *  用户类别
    */
    public void setUserType(String newUserType)
    {
        userElement.setAttribute(TYPE,newUserType);
    }
    public String getUserType()
    {
        return userElement.getAttribute(TYPE);
    }
    /**
     *  用户状态
     */
    public void setUserStatus(String newUserStatus)
    {
        userElement.setAttribute(STATUS,newUserStatus);
    }
    public String getUserStatus()
    {
        return userElement.getAttribute(STATUS);
    }
    /**
     *  用户性别
     */
    public void setUserGender(String newUserGender)
    {
        userElement.setAttribute(GENDER,newUserGender);
    }
    public String getUserGender()
    {
        return userElement.getAttribute(GENDER);
    }
   /**
    *  用户IP
    */
    public void setUserIP(String newUserIP)
    {
        userElement.setAttribute(IP,STUDENT_CHARCTER+newUserIP);
    }
    public String getUserIP()
    {
        String ip=userElement.getAttribute(IP);
        int pos=ip.indexOf(STUDENT_CHARCTER);
        ip=ip.substring(pos);
        return ip;
    }
   /**
    * 返回人名
    */
    public String getName()
    {
        return getUserName();
    }

    /**
     * 返回人类型: TEACHER, STUDENT OR AUDITOR
     */
    public int getType()
    {
        String strType=getUserType();
        return new Integer(strType).intValue();
    }

    /**
     * 返回人的性别
     */
    public int getGender()
    {
        String strGender=getUserGender();
        return new Integer(strGender).intValue();
    }

   /**
    * 返回LoginName
    */
    public String getLoginName()
    {
        return getUserID();
    }

   /**
    * 如果在教室,返回True
    */
    public boolean InClassroom()
    {
        String strStatus=getUserStatus();
        if (strStatus.equals(PRESENT))
            return true;
        else
            return false;
    }
   /**
    *  获得用户
    */
    public Element getUserElement()
    {
       return this.userElement;
    }
   /**
    *  设置用户
    */
    public void setUserElement(Element newElement)
    {
        this.userElement=newElement;
    }
}
