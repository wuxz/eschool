package com.dc.eschool.system;

import java.util.Collection;
import java.util.Iterator;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.DataModel.UserInfoModel;

/**
 * Title:         �û���Ϣ
 * Description:   ������������û��ĸ�����Ϣ��
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public class UserInfo implements UserIF
{
   /****************************************************
    * �����˽�б���
    ****************************************************/
   /** �û�ID  */
   private String ID;

   /** �û��� */
   private String nameForLogin;

   /** �û������� */
   private String name;

   /** �û��Ա� */
   private String gender;

   /** �û��ĳ�������  */
   private String birthday;

   /** �û��ĵ绰  */
   private String telephone;

   /** �û���������  */
   private String typeForUser;


   /**  EJBAcess  */
   private EJBAccess ejbAccess;
   /** SystemControl */
   private SystemControl systemControl;

   /** ������ */
   public UserInfo(SystemControl systemControl)
   {
       this.ID="";
       this.nameForLogin=null;
       this.name=null;
       this.gender=null;
       this.birthday=null;
       this.telephone=null;
       this.typeForUser=null;
       this.systemControl=systemControl;
       if (systemControl.ejbAccess==null)
       {
           this.ejbAccess=new EJBAccess();
           systemControl.ejbAccess=this.ejbAccess;
       }
       else
       {
           this.ejbAccess=systemControl.ejbAccess;
       }
   }

   /********************************************************
    * �����е�˽�з�������������û��ĵ���Ϣ�������û�ID,�������Ա�
    *                      �������£��绰���û�����
    * ���������֣�1��ͨ���û�ID�����
    *           2��ͨ���û��������
    * ��ͬ���ص㣺�����Ϣ�󣬻ḳֵ����Ӧ��˽�б���
    ********************************************************/

   /**����û�����Ϣ�����Ҹ�ֵ��UserInfo���˽�б���  */
   private void getUserInfo(String userID)
   {
       UserInfoModel userInfo;
       Collection tempCollection=ejbAccess.getUserInfo(userID);
       if (tempCollection==null)
       {
           systemControl.writeLog("û��ȡ���û�����Ϣ��");
           return;
       }
       Iterator iterator=tempCollection.iterator();
       if  (iterator.hasNext())
       {
           try
           {
               userInfo=(UserInfoModel)iterator.next();
               this.name=userInfo.getUserName();
               this.birthday=userInfo.getUserBirthday();
               this.gender=userInfo.getUserGender();
               this.telephone=userInfo.getUserTelephone();
               this.typeForUser=userInfo.getUserType();
           }
           catch (Exception e)
           {
               systemControl.writeLog("��ȡ��ѧ����Ϣ����ʶ�棡");
           }

       }
   }

   /**ͨ���û�����õ��û���Ϣ ,���Ҹ�ֵ��UserInfo���˽�б���  */
   private void getUserInfoByName(String loginName)
   {
   }

   /******************************************************
    *�������û�����Ϣ
    ******************************************************/

   /** ͨ���û�ID�������  */
   public String getName(String userID)
   {
      if (ID.equals(userID))
        return name;
      else
        {  getUserInfo(userID);
           return name;
        }
   }

   /** ͨ���û�ID����û��Ա�  */
   public String getGender(String userID)
   {
      if (ID.equals(userID))
        return gender;
      else
        {  getUserInfo(userID);
           return gender;
        }
   }

   /** ͨ���û�ID����û�����   */
   public String getBirthday(String userID)
   {
      if (ID.equals(userID))
        return birthday;
      else
        {  getUserInfo(userID);
           return birthday;
        }
   }

   /** ͨ���û���ID������û��ĵ绰���� */

   public String getTelephone(String userID)
   {
      if (ID.equals(userID))
        return telephone;
      else
        {  getUserInfo(userID);
           return telephone;
        }
   }

   /** ͨ���û���ID������û��ĵ绰����   */
   public String getUserType(String userID)
   {
      if (ID.equals(userID))
            return typeForUser;
      else
        {  getUserInfo(userID);
           return typeForUser;
        }
   }

   /**  ͨ���û�������û������� */
   public String getNameByName(String loginName)
   {
      if (nameForLogin.equals(loginName))
        return name;
      else
        {  getUserInfoByName(loginName);
           return name;
        }
   }

   /** ͨ���û�������û����Ա�  */
   public String getGenderByName(String loginName)
   {
      if (nameForLogin.equals(loginName))
        return gender;
      else
        {  getUserInfoByName(loginName);
           return gender;
        }
   }

   /**  ͨ���û�������û������� */
   public String getBirthdayByName(String loginName)
   {
       if (nameForLogin.equals(loginName))
        return birthday;
      else
        {  getUserInfoByName(loginName);
           return birthday;
        }
   }

   /** ͨ���û�������û��ĵ绰  */
   public String getTelephoneByName(String loginName)
   {
      if (nameForLogin.equals(loginName))
        return telephone;
      else
        {  getUserInfoByName(loginName);
           return telephone;
        }
   }

   /** ͨ���û�������û������  */
   public String getUserTypeByName(String loginName)
   {
      if (nameForLogin.equals(loginName))
        return typeForUser;
      else
        {  getUserInfoByName(loginName);
           return typeForUser;
        }
   }

   /************************************************
    * �޸��û�����Ϣ                                  *
    * �������û�ID,�������Ա𣬳������ڣ��绰���û����
    ************************************************/
   public void setUserInfo(String userID,String userName,
                           String userGender,String birthday,
                           String telephone,String userType)
   {
       this.name=userName;
       this.gender=userGender;
       this.birthday=birthday;
       this.telephone=telephone;
       this.typeForUser=userType;
       ejbAccess.saveUserInfo(userID,userName,userGender,
                              birthday,userType,telephone);
   }
}
