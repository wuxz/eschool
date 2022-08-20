//Source file: D:\\lau\\coding\\roseCode\\com\\dc\\eschool\\System\\IUser.java

package com.dc.eschool.system;

 /**
 * Title:         用户信息
 * Description:   用户信息接口，该接口提供用户信息的各种方法
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public interface UserIF
{

   /** 得到用户的姓名 */
   public String getName(String userID);

   /** 得到用户的性别 */
   public String getGender(String userID);

   /** 得到用户的出生年月 */
   public String getBirthday(String userID);

   /**  得到用户的电话号码 */
   public String getTelephone(String userID);

   /**  得到用户的类型  */
   public String getUserType(String userID);

   /**  通过用户名获得用户的性别 */
   public String getNameByName(String loginName);

   /**  通过用户名获得用户的性别 */
   public String getGenderByName(String loginName);

   /** 通过用户名获得用户的出生年月 */
   public String getBirthdayByName(String loginName);

   /** 通过用户名获得用户的电话 */
   public String getTelephoneByName(String loginName);

   /** 通过用户名获得用户的身份 */
   public String getUserTypeByName(String loginName);

   /**
    * 修改用户的信息
    * 参数：用户ID,姓名，性别，出生日期，电话，用户类别
    */
   public void setUserInfo(String userID,String userName,
                           String userGender,String birthday,
                           String telephone,String userType);


}
