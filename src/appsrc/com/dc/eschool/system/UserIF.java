//Source file: D:\\lau\\coding\\roseCode\\com\\dc\\eschool\\System\\IUser.java

package com.dc.eschool.system;

 /**
 * Title:         �û���Ϣ
 * Description:   �û���Ϣ�ӿڣ��ýӿ��ṩ�û���Ϣ�ĸ��ַ���
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public interface UserIF
{

   /** �õ��û������� */
   public String getName(String userID);

   /** �õ��û����Ա� */
   public String getGender(String userID);

   /** �õ��û��ĳ������� */
   public String getBirthday(String userID);

   /**  �õ��û��ĵ绰���� */
   public String getTelephone(String userID);

   /**  �õ��û�������  */
   public String getUserType(String userID);

   /**  ͨ���û�������û����Ա� */
   public String getNameByName(String loginName);

   /**  ͨ���û�������û����Ա� */
   public String getGenderByName(String loginName);

   /** ͨ���û�������û��ĳ������� */
   public String getBirthdayByName(String loginName);

   /** ͨ���û�������û��ĵ绰 */
   public String getTelephoneByName(String loginName);

   /** ͨ���û�������û������ */
   public String getUserTypeByName(String loginName);

   /**
    * �޸��û�����Ϣ
    * �������û�ID,�������Ա𣬳������ڣ��绰���û����
    */
   public void setUserInfo(String userID,String userName,
                           String userGender,String birthday,
                           String telephone,String userType);


}
