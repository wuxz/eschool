package com.dc.eschool.group;
/**
 * Title: User
 * Description: ��¼�û����ݽṹ
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
    * �û�Attributes
    */
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String REMARK = "remark";
    private static final String TYPE = "type";
    private static final String STATUS = "status";
    private static final String GENDER = "gender";
    private static final String IP  ="ip";
   /**
    * ������: ��ʦ
    */
    public static final String ESCHOOL_TEACHER = "0";

   /**
    * ������: ѧ��
    */
    public static final String ESCHOOL_STUDENT = "1";

   /**
    * ������: ����
    */
    public static final String ESCHOOL_AUDITOR = "2";

   /**
    * �Ա�: ��
    */
    private static final String ESCHOOL_MALE = "0";

   /**
    * �Ա�: Ů
    */
    private static final String ESCHOOL_FEMALE = "1";
   /**
    * �û��Ͽ����
    */
    private static final String PRESENT = "T";
    private static final String ABESENT = "F";

   /**
    *ѧ��id����
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
    *  �û�ID
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
    *  �û�����
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
    *  �û����
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
     *  �û�״̬
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
     *  �û��Ա�
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
    *  �û�IP
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
    * ��������
    */
    public String getName()
    {
        return getUserName();
    }

    /**
     * ����������: TEACHER, STUDENT OR AUDITOR
     */
    public int getType()
    {
        String strType=getUserType();
        return new Integer(strType).intValue();
    }

    /**
     * �����˵��Ա�
     */
    public int getGender()
    {
        String strGender=getUserGender();
        return new Integer(strGender).intValue();
    }

   /**
    * ����LoginName
    */
    public String getLoginName()
    {
        return getUserID();
    }

   /**
    * ����ڽ���,����True
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
    *  ����û�
    */
    public Element getUserElement()
    {
       return this.userElement;
    }
   /**
    *  �����û�
    */
    public void setUserElement(Element newElement)
    {
        this.userElement=newElement;
    }
}
