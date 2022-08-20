package com.dc.eschool.util;

/**
 * Title:         JMFAPI
 * Description:   JMF的基础方法
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version 1.0
 */
import java.util.*;
import java.net.URL;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.format.*;
import javax.media.control.TrackControl;
import javax.media.control.QualityControl;
import javax.media.rtp.*;
import javax.media.rtp.rtcp.*;
import com.sun.media.rtp.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.*;
import javax.swing.event.*;
import com.dc.eschool.systemControl.SystemControl;
public class JMFAPI
{
  private static int MAXVALUE=5;
  public static final String AUDIOURL = "javasound://44100.0";    //screen://10,10,320,400/5
  public static final String BROADCASTIP = "224.1.1.1";
  public static final String LOCALIP = "127.0.0.1";
  // file:/C:/media/test.wav

  /**  AV发送端实例  */
  private AVTransmit avTransmiter;
  /**  AV接收端实例  */
  private AVReceive  avReceiver;
  /**  IP      */
  private String     IP;
  /**  Port   */
  //private String     port;
  private Random randomInt;
  private Processor processor = null;
  private SystemControl systemControl;
  /**  构造器  */
  public JMFAPI(SystemControl sysControl)
  {
     this.avReceiver=null;
     this.avTransmiter=null;
     this.IP=null;
     this.systemControl=sysControl;
     int seed=getSeed();
     this.randomInt=new Random(seed);
  }
  public JMFAPI()
  {
     this.avReceiver=null;
     this.avTransmiter=null;
     this.IP=null;
     int seed=7880;
     this.randomInt=new Random(seed);
  }
  /**   声音发送  */
  public String audioTransmit(String url,String IPAddress,boolean broadcastORNot)
  {
        String  port;
        String type=null;
       if  (broadcastORNot==true)
        {
            IP=this.BROADCASTIP;
        }
        else
        {
            IP=IPAddress;
        }
        String result="DC's bug";
        int    count=0;
        while ((result != null)&&(count<MAXVALUE))
        {
            port = randomPort();
            System.out.println("port : " + port);
            avTransmiter = new AVTransmit(new MediaLocator(url),IP,port,null,null,this.systemControl);
            result = avTransmiter.start();
          	if (result != null)
            {
			    System.out.println("Error : " + result);
            }
            else
            {
                 processor=avTransmiter.getProcessor();
                 if (processor==null) System.out.println("jmfApi:"+"processor is null");
                return port;
            }
            count++;
        }
        return null;
  }
  /**   声音发送  */
  public String audioTransmit(String url,String IPAddress,String port,boolean broadcastORNot)
  {
        if  (broadcastORNot==true)
        {
            IP=this.BROADCASTIP;
        }
        else
        {
            IP=IPAddress;
        }
        String result="DC's bug";
        int    count=0;
        while ((result != null)&&(count<MAXVALUE))
        {
            System.out.println("port : " + port);
            avTransmiter = new AVTransmit(new MediaLocator(url),IP,port,null,null,this.systemControl);
            result = avTransmiter.start();
          	if (result != null)
            {
                System.out.println("Error : " + result);
            }
            else
            {
                 processor=avTransmiter.getProcessor();
                 if (processor==null) System.out.println("jmfApi:"+"processor is null");
                return port;
            }
            count++;
        }
        return null;
  }
  /**   声音`接收  */
  public boolean audioRecieve(String IPAddress,String port)
  {
        String temp = IPAddress+"/"+port;
        String []info = new String[1];
        info[0] = temp;
        avReceiver = new AVReceive(info);
        if (!avReceiver.initialize())
        {
		System.out.println("Failed to initialize the sessions.");
            return false;
        }
        else
        {
            return true;
        }
  }
  /**   影音发送  */
  public String videoTransmit(String url,String IPAddress,boolean broadcastORNot)
  {
        String  port;
        if  (broadcastORNot==true)
        {
            IP=this.BROADCASTIP;
        }
        else
        {
            IP=IPAddress;
        }
        String result="DC's bug";
        int    count=0;
        while ((result != null)&&(count<MAXVALUE))
        {
            port = randomPort();
		    avTransmiter = new AVTransmit(new MediaLocator(url),IP,port,null,null,this.systemControl);
		    result = avTransmiter.start();
	    	if (result != null)
            {
			    System.out.println("Error : " + result);
            }
            else
            {
                return port;
            }
            count++;
        }
        return null;
  }
  /**   影音接收  */
  public boolean videoRecieve(String IPAddress,String port)
  {
        String temp = IPAddress+"/"+port;
        String []info = new String[1];
        info[0] = temp;
        avReceiver = new AVReceive(info);
	  	if (!avReceiver.initialize())
        {
		    System.out.println("Failed to initialize the sessions.");
            return false;
        }
        else
        {
            return true;
        }
  }
  /**  屏幕发送  */
  public String screenTransmit(String url,String IPAddress,boolean broadcastORNot)
  {
        String  port;
        if  (broadcastORNot==true)
        {
            IP=this.BROADCASTIP;
        }
        else
        {
            IP=IPAddress;
        }
        String result="DC's bug";
        int    count=0;
        try
        {
            while ((result != null)&&(count<MAXVALUE))
            {
                port = randomPort();
                System.out.println("screenTransmit's port:"+port);
                    avTransmiter = new AVTransmit(new MediaLocator(url),IP,port,null,"SCREEN",this.systemControl);
                    result = avTransmiter.start();
                if (result != null)
                {
                          System.out.println("Error : " + result);
                }
                else
                {
                    return port;
                }
                count++;
            }
        }
        catch(Exception e)
        {
            System.out.println("screenTransmit Exception:"+e.getMessage());
            e.printStackTrace();
        }
        return null;
  }
  /**  屏幕接收  */
  public boolean screenRecieve(String IPAddress,String port)
  {
        String temp = IPAddress+"/"+port;
        String []info = new String[1];
        info[0] = temp;
        avReceiver = new AVReceive(info,"SCREEN",this.systemControl);
	  	if (!avReceiver.initialize())
        {
		    System.out.println("Failed to initialize the sessions.");
            return false;
        }
        else
        {
            return true;
        }
  }
  /**   停止释放播放器 */
  public void stopJMFDevice()
  {
      if  (avTransmiter != null)
      {
          avTransmiter.stop();
          avTransmiter=null;
      }
      if  (avReceiver != null)
      {
          avReceiver.close();
          avReceiver=null;
      }
  }
  /**   停止释放播放器传送 */
  public void stopJMFTransmit()
  {
      if  (avTransmiter != null)
      {
          avTransmiter.stop();
          avTransmiter=null;
      }
  }
  /**   停止释放播放器接收 */
  public void stopJMFReceive()
  {
      if  (avReceiver != null)
      {
          avReceiver.close();
          avReceiver=null;
      }
  }
  /**   打开录音机  */
  public void startRecorder()
  {
     try
     {
        String s;
        Process myProcess = null;

        myProcess = Runtime.getRuntime().exec ("sndrec32.exe");
        BufferedReader in = new BufferedReader(new
        InputStreamReader(myProcess.getErrorStream()));
        while ((s = in.readLine()) != null)
        {
            System.out.println("这是进程myProcess捕获到的异常："+s);
         }
      }catch(Exception e){System.out.println("这是主线程捕获到的异常");}
   }
  /**   生成随机端口 */
  public String randomPort()
  {
       //Random randomInt=new Random(8000);
       int intPort=randomInt.nextInt(5000);
       if (intPort<2000)
       {
            intPort = intPort +2048;
       }
       return new Integer(intPort).toString();

  }
  public Processor getProcessor()
  {
    return processor;
  }
  /**   生成随机种子 */
  private int getSeed()
  {
       int seed=8000;
       String ip=this.systemControl.getUserIP();
       if(ip==null) return seed;
       String ip1,ip2,ip3,ip4;
       int pos1,pos2,pos3;
       pos1=ip.indexOf(".");
       if(pos1==-1) return seed;
       ip1=ip.substring(0,pos1);
       pos2=ip.indexOf(".",pos1+1);
       if(pos2==-1) return seed;
       ip2=ip.substring(pos1+1,pos2);
       pos3=ip.indexOf(".",pos2+1);
       if(pos3==-1) return seed;
       ip3=ip.substring(pos2+1,pos3);
       ip4=ip.substring(pos3+1);
       int IP1=(new Integer(ip1)).intValue();
       int IP2=(new Integer(ip2)).intValue();
       int IP3=(new Integer(ip3)).intValue();
       int IP4=(new Integer(ip4)).intValue();
       if(IP1>0) seed=1*IP1;
       if(IP2>0) seed=seed*IP2;
       if(IP3>0) seed=seed*IP3;
       if(IP4>0) seed=seed*IP4;
       return seed;
  }
}