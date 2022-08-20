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

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

public class EschoolUser implements Person,java.io.Serializable
{
   /**
    * �û�Attributes
    */
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String REMARK = "remark";
    public static final String TYPE = "type";
    public static final String STATUS = "status";
    public static final String GENDER = "gender";
    public static final String IP  ="ip";
    public static final String HANDUP="handup";
    public static final String EXAMING="examing";
    public static final String CALLING="calling";
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
    public static final String ESCHOOL_MALE = "0";
    public static final String CHINESE_MALE = "0";

   /**
    * �Ա�: Ů
    */
    public static final String ESCHOOL_FEMALE = "1";
    public static final String CHINESE_FEMALE = "1";
   /**
    * �û��Ͽ���񣬾�����񣬿������
    */
    public static final String PRESENT = "T";
    public static final String ABESENT = "F";

   /**
    *ѧ��id����
    */
    public static final String STUDENT_CHARCTER = "Student_";
    public static final String TEACHER_CHARCTER = "Teacher_";
    public static final String AUDITOR_CHARCTER = "Auditor_";

    private Element userElement;
    public EschoolUser() {}
    public EschoolUser(String newUserID,String newUserName,String newUserType,
		String newUserStatus,String newUserGender,String newUserIP,String newHandUp,String newExaming,String newCalling)
    {
        try
        {
            Document document;
            DocumentBuilderFactory documentBuilderFactory;
            DocumentBuilder documentBuilder;
            documentBuilderFactory=DocumentBuilderFactory.newInstance();
            documentBuilder=documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            if(newUserType.equals(ESCHOOL_TEACHER))
                userElement=document.createElement("Teacher");
            else if(newUserType.equals(ESCHOOL_STUDENT))
                userElement=document.createElement("Student");
            else
                userElement=document.createElement("Auditor");
            userElement.setAttribute(ID,newUserID);
            userElement.setAttribute(NAME,newUserName);
            userElement.setAttribute(TYPE,newUserType);
            userElement.setAttribute(STATUS,newUserStatus);
            userElement.setAttribute(GENDER,newUserGender);
            userElement.setAttribute(IP,newUserIP);
            userElement.setAttribute(HANDUP,newHandUp);
            userElement.setAttribute(EXAMING,newExaming);
            userElement.setAttribute(CALLING,newCalling);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public EschoolUser(Element newElement)
    {
        this.userElement=newElement;
    }
   /**
    *  �û�ID
    */
    public void setUserID(String newUserID,String type)
    {
        userElement.setAttribute(ID,newUserID);
    }
    public String getUserID()
    {
        String id=userElement.getAttribute(ID);
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
        String name=userElement.getAttribute(NAME);
        return name;
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
        int rv = Person.AUDITOR;
        String type = this.getUserType();
        if ( type.equals(this.ESCHOOL_STUDENT))
            rv = Person.STUDENT;
        else if ( type.equals(this.ESCHOOL_TEACHER))
            rv = Person.TEACHER;
        return rv;
    }

    /**
     * �����˵��Ա�
     */
    public int getGender()
    {
        int rv = Person.FEMALE;
        String gender = this.getUserGender();
        if ( gender.equals(this.ESCHOOL_MALE))
            rv = Person.MALE;
        return rv;
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
    public static String turnGenderIntoID(String newGender)
    {
        if(newGender.equals("0")) return newGender;
        if(newGender.equals("1")) return newGender;
        if(newGender.equals("Ů")) return ESCHOOL_FEMALE;
        if(newGender.equals("��")) return ESCHOOL_MALE;
        return null;
    }
    public boolean isHandUp()
    {
        String strHandUp=getHandUp();
        if(strHandUp==null) return false;
        if (strHandUp.equals(PRESENT))
            return true;
        else
            return false;
    }
    public String getHandUp()
    {
        return userElement.getAttribute(HANDUP);
    }
    public void setHandUp( boolean b)
    {
        if(b==true)
            userElement.setAttribute(HANDUP,PRESENT);
        else
            userElement.setAttribute(HANDUP,ABESENT);
    }
    public void setUserHandUp(String newHandup)
    {
        userElement.setAttribute(HANDUP,newHandup);
    }

    public boolean examing()
    {
        String strExaming=getExaming();
        if(strExaming==null) return false;
        if (strExaming.equals(PRESENT))
            return true;
        else
            return false;
    }
    public String getExaming()
    {
        return userElement.getAttribute(EXAMING);
    }

    public void setExaming( boolean b)
    {
        if(b==true)
            userElement.setAttribute(EXAMING,PRESENT);
        else
            userElement.setAttribute(EXAMING,ABESENT);
    }

    public boolean calling()
    {
        String strCalling=getCalling();
        if(strCalling==null) return false;
        if (strCalling.equals(PRESENT))
            return true;
        else
            return false;
    }
    public String getCalling()
    {
        return userElement.getAttribute(CALLING);
    }

    public void setCalling( boolean b)
    {
        if(b==true)
            userElement.setAttribute(CALLING,PRESENT);
        else
            userElement.setAttribute(CALLING,ABESENT);
    }
}
