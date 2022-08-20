//Source file: D:\\yan_work\\eschool\\src\\com\\dc\\eschool\\System\\LoginIF.java

package com.dc.eschool.system;
import java.util.Vector;
import java.util.Hashtable;
import java.net.UnknownHostException;
public interface LoginIF
{
   public Hashtable verifyPassword(String userName,char[] password);
   public String getLocalIP() throws UnknownHostException;
   public boolean getVerifyResult();
   public Vector login(String courseid,String ip,String userID);
}
