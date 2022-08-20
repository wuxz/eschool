package com.dc.eschool.communication;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.event.*;
import com.sun.media.rtp.*;
import javax.media.*;
import com.dc.eschool.system.*;
import com.dc.eschool.group.*;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.util.JMFAPI;
import java.rmi.RemoteException;
/**
 * Title:        JDialogTalkWithTeacher
 * Description:  语音对讲
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class JDialogTalkWithTeacher extends JDialog
{
    JPanel panel1 = new JPanel();
    JMenuBar jMenuBar1 = new JMenuBar();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextField jTextFieldContent = new JTextField();
    JButton jButton1 = new JButton();
    JLabel jLabel1 = new JLabel();
    private SystemControl systemControl;
    private String userName;
    private String userID;
    private Player player;
    public String sendPort;
    private Student student=null;
    JTextArea jTextAreaTalk = new JTextArea();
  GridLayout gridLayout1 = new GridLayout();
    //private Vector[] vecData=new Vector[1];
    public JDialogTalkWithTeacher(Frame frame, String title, boolean modal)
    {
        super(frame, title, modal);
        try
        {
            jbInit();
            pack();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public JDialogTalkWithTeacher(SystemControl sysControl,EschoolUser eschoolUser)
    {
        this(sysControl.mainFrm, "语音对讲", true);
        this.setSize(new Dimension(418, 297));
        this.systemControl=sysControl;
        this.userName=eschoolUser.getUserName();
        this.userID=eschoolUser.getUserID();
        System.out.println("userID....:"+this.userID);
        Hashtable htUser=this.systemControl.getHtStudentIDInterface();
        student=(Student)htUser.get(this.userID);
        if(student==null)
        {
            System.out.println("student is null");
            dispose();
            return;
        }
        this.systemControl.privateTalk=new PrivateTalk(jTextAreaTalk,student);
        jLabel1.setText("与"+this.userName+"对话");
        this.userName="老师：";
        String ip=this.systemControl.userGroup.getUserIPByUserID(userID);
        String port=this.systemControl.jmfApi.audioTransmit(JMFAPI.AUDIOURL,ip,false);//暂时注释
        //String port=this.systemControl.jmfApi.audioTransmit(JMFAPI.AUDIOURL,JMFAPI.BROADCASTIP,true);
        if (port==null) return;
        Processor processor=this.systemControl.jmfApi.getProcessor();
        if (processor==null) System.out.println("communication: processor is null");
        this.addPlayPanel(processor);
        try
        {
            port=student.answerFromTeacher(port);
            System.out.println("student transmit port :"+port);
            if (port==null) return;
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
            System.out.println("Remote Exception"+e.getMessage());
        }
        this.systemControl.jmfApi.audioRecieve(this.systemControl.getUserIP(),port);//暂时注释
        centerScreen();
    }
    public JDialogTalkWithTeacher(SystemControl sysControl,String port)
    {
        this(sysControl.mainFrm, "语音对讲", false);
        this.systemControl=sysControl;
        this.setSize(new Dimension(418, 297));
        this.userName=this.systemControl.getUserName()+"：";
        System.out.println("userName:"+userName);
        jLabel1.setText("与老师对话");
        this.systemControl=sysControl;
        System.out.println("systemControl:"+this.systemControl);
        Teacher teacher=this.systemControl.getTeacherInterface();
        System.out.println("teacher...:"+teacher);
        this.systemControl.privateTalk=new PrivateTalk(jTextAreaTalk,teacher);

        boolean b=this.systemControl.jmfApi.audioRecieve(systemControl.getUserIP(),port);//暂时注释
        //boolean b=this.systemControl.jmfApi.audioRecieve(JMFAPI.BROADCASTIP,port);
        System.out.println("Student：success start receive!!!");
        sendPort=this.systemControl.jmfApi.audioTransmit(JMFAPI.AUDIOURL,systemControl.getTeacherIP(),false);//暂时注释
        //sendPort="2002";
        centerScreen();
    }
    void jbInit() throws Exception
    {
        panel1.setLayout(gridLayout1);
        //this.setSize(new Dimension(418, 297));
        this.getContentPane().setLayout(null);
        panel1.setBounds(new Rectangle(207, 118, 1, 1));
        jScrollPane1.getViewport().setBackground(Color.white);
        jScrollPane1.setBounds(new Rectangle(3, 28, 409, 151));
        jTextFieldContent.setBounds(new Rectangle(1, 188, 408, 28));
        jButton1.setText("发送");
        jButton1.setBounds(new Rectangle(324, 226, 86, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                jButton1_actionPerformed(e);
            }
        });
        jLabel1.setBounds(new Rectangle(3, 1, 406, 25));
        this.setResizable(false);
    this.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        this_windowClosing(e);
      }
    });
        this.getContentPane().add(panel1, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jTextFieldContent, null);
        this.getContentPane().add(jScrollPane1, null);
        jScrollPane1.getViewport().add(jTextAreaTalk, null);
        this.getContentPane().add(jLabel1, null);
    }
     void jButton1_actionPerformed(ActionEvent e)
    {
        String content=userName+jTextFieldContent.getText();
        this.systemControl.privateTalk.sendText(content);
        jTextFieldContent.setText("");
    }
    class PlayerPanel extends Panel
    {

        Component vc, cc;
        PlayerPanel(Player p)
        {
            setLayout(new BorderLayout());
            if ((vc = p.getVisualComponent()) != null)
                add("Center", vc);
            if ((cc = p.getControlPanelComponent()) != null)
                add("South", cc);
        }
    }
    public void addPlayPanel(Player player)
    {
        this.player=player;
        PlayerPanel playPanel=new PlayerPanel(player);
        playPanel.setBounds(new Rectangle(2, 225, 319, 25));
        this.getContentPane().add(playPanel, null);
    }
    public void close()
    {
        player.close();
        setVisible(false);
        dispose();
    }

    public void addNotify()
    {
        super.addNotify();
        pack();
    }

    void this_windowClosing(WindowEvent e)
    {
       try
       {
           if(student!=null)
           student.closeTalkWithTeacher();
           this.systemControl.jmfApi.stopJMFDevice();
           this.systemControl.jDialogTalkWithTeacher=null;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void centerScreen()
    {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - 418) / 2,(dim.height - 297) / 2);
    }
}