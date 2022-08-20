package com.dc.eschool;

import javax.swing.UIManager;
import java.awt.*;
import java.util.Enumeration;
import javax.swing.*;

import com.dc.eschool.systemControl.SystemControl;


/**
 * Title:         主类
 * Description:   整个应用程序的主类
 *
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class MainClass
{
    boolean packFrame = false;
    private SystemControl systemControl;

    /**Construct the application*/
    public MainClass()
    {
        //start welcome window
        Welcome welcome = new Welcome();
        Thread welcomet = new Thread(welcome);
        welcomet.setDaemon(true);
        welcomet.start();

        systemControl=new SystemControl();
        //WaitFrm waitFrm = new WaitFrm();
        //systemControl.waitFrm=waitFrm;
        setSystemFont("宋体");
        MainFrm frame = new MainFrm(systemControl);
        systemControl.mainFrm=frame;
        String type=systemControl.getUserType();
        if(type==null&&systemControl.getTeacherInterface()==null)
        {
            this.systemControl.mainFrm.sendInformation("老师还未上课！");
            System.exit(0);
        }
        //Validate frames that have preset sizes
        //Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame)
        {
            frame.pack();
        }
        else
        {
            frame.validate();
        }
        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height)
        {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width)
        {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        Image imge = (new ImageIcon(com.dc.eschool.MainClass.class.getResource("logo16.gif"))).getImage();
        frame.setIconImage(imge);
        frame.setVisible(true);
        //close Welcome window
        welcome.stop();
        welcomet=null;
    }
    /**    设置系统字体   */
    private  void setSystemFont(String fontName)
    {
        if (fontName==null)
            return;
        Object key = null;
        Font f = null;
        for (Enumeration e = UIManager.getDefaults().keys() ; e.hasMoreElements() ;)
        {
            key = e.nextElement();
            if (key.toString().indexOf(".font") > 0 )
            {
                f = (Font)UIManager.getDefaults().get(key);
                //f = new Font( fontName, f.getStyle(), f.getSize());
                f = new Font( fontName, Font.PLAIN, f.getSize());
                UIManager.getDefaults().put(key, f);
            }
        }
    }
    /**Main method*/
    public static void main(String[] args)
    {
        try
        {
         //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        new MainClass();
  }
}