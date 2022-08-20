package com.dc.eschool.system;

import java.util.Collection;
import java.util.Iterator;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.DataModel.UserInfoModel;

/**
 * Title:         用户信息
 * Description:   该类用来获得用户的各种信息。
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */
public class UserInfo implements UserIF
{
   /****************************************************
    * 该类的私有变量
    ****************************************************/
   /** 用户ID  */
   private String ID;

   /** 用户名 */
   private String nameForLogin;

   /** 用户的姓名 */
   private String name;

   /** 用户性别 */
   private String gender;

   /** 用户的出生日期  */
   private String birthday;

   /** 用户的电话  */
   private String telephone;

   /** 用户的类别、身份  */
   private String typeForUser;


   /**  EJBAcess  */
   private EJBAccess ejbAccess;
   /** SystemControl */
   private SystemControl systemControl;

   /** 构造器 */
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
    * 改类中的私有方法，用来获得用户的的信息，包括用户ID,姓名，性别，
    *                      出生年月，电话，用户类型
    * 方法有两种：1、通过用户ID来获得
    *           2、通过用户名来获得
    * 共同的特点：获得信息后，会赋值给相应的私有变量
    ********************************************************/

   /**获得用户的信息，并且赋值给UserInfo类的私有变量  */
   private void getUserInfo(String userID)
   {
       UserInfoModel userInfo;
       Collection tempCollection=ejbAccess.getUserInfo(userID);
       if (tempCollection==null)
       {
           systemControl.writeLog("没有取到用户的信息！");
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
               systemControl.writeLog("读取的学生信息不可识辨！");
           }

       }
   }

   /**通过用户名获得的用户信息 ,并且赋值给UserInfo类的私有变量  */
   private void getUserInfoByName(String loginName)
   {
   }

   /******************************************************
    *逐项获得用户的信息
    ******************************************************/

   /** 通过用户ID获得姓名  */
   public String getName(String userID)
   {
      if (ID.equals(userID))
        return name;
      else
        {  getUserInfo(userID);
           return name;
        }
   }

   /** 通过用户ID获得用户性别  */
   public String getGender(String userID)
   {
      if (ID.equals(userID))
        return gender;
      else
        {  getUserInfo(userID);
           return gender;
        }
   }

   /** 通过用户ID获得用户生日   */
   public String getBirthday(String userID)
   {
      if (ID.equals(userID))
        return birthday;
      else
        {  getUserInfo(userID);
           return birthday;
        }
   }

   /** 通过用户的ID来获得用户的电话号码 */

   public String getTelephone(String userID)
   {
      if (ID.equals(userID))
        return telephone;
      else
        {  getUserInfo(userID);
           return telephone;
        }
   }

   /** 通过用户的ID来获得用户的电话号码   */
   public String getUserType(String userID)
   {
      if (ID.equals(userID))
            return typeForUser;
      else
        {  getUserInfo(userID);
           return typeForUser;
        }
   }

   /**  通过用户名获得用户的姓名 */
   public String getNameByName(String loginName)
   {
      if (nameForLogin.equals(loginName))
        return name;
      else
        {  getUserInfoByName(loginName);
           return name;
        }
   }

   /** 通过用户名获得用户的性别  */
   public String getGenderByName(String loginName)
   {
      if (nameForLogin.equals(loginName))
        return gender;
      else
        {  getUserInfoByName(loginName);
           return gender;
        }
   }

   /**  通过用户名获得用户的生日 */
   public String getBirthdayByName(String loginName)
   {
       if (nameForLogin.equals(loginName))
        return birthday;
      else
        {  getUserInfoByName(loginName);
           return birthday;
        }
   }

   /** 通过用户名获得用户的电话  */
   public String getTelephoneByName(String loginName)
   {
      if (nameForLogin.equals(loginName))
        return telephone;
      else
        {  getUserInfoByName(loginName);
           return telephone;
        }
   }

   /** 通过用户名获得用户的身份  */
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
    * 修改用户的信息                                  *
    * 参数：用户ID,姓名，性别，出生日期，电话，用户类别
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
