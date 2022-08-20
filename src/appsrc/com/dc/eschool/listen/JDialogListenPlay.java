package com.dc.eschool.listen;

import java.awt.*;
import javax.swing.*;
import com.dc.eschool.systemControl.*;
import java.awt.event.*;
import com.dc.eschool.util.*;
import javax.media.*;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
import com.dc.eschool.communication.JDialogMessageBox;
import com.borland.jbcl.layout.*;

/**
 * Title:         JDialogListenPlay
 * Description:   听力播放
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class JDialogListenPlay extends JDialog
{
    JPanel panel1 = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTableListen = new JTable();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JLabel jLabel1 = new JLabel();
    private SystemControl systemControl;
    private Processor processor = null;
    private Player player;
    private Vector userIDs=new Vector();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  BorderLayout borderLayout2 = new BorderLayout();
    public JDialogListenPlay(Frame frame, String title, boolean modal)
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

    public JDialogListenPlay(SystemControl sysControl)
    {
        this(sysControl.mainFrm, "播放听力", false);
        this.systemControl=sysControl;
        if(this.systemControl.IListen==null)
        this.systemControl.IListen=new Listen(sysControl);
        String courseID=sysControl.getCourseID();
        ListenPlayTableModel listenPlayTableModel=new ListenPlayTableModel(sysControl.IListen,courseID);
        jTableListen.setModel(listenPlayTableModel);
        //jmfApi=new JMFAPI(this.systemControl);
        Hashtable Htuser=this.systemControl.getHtStudentIDInterface();
        for(Enumeration er=Htuser.keys();er.hasMoreElements();)
        {
            String userID=(String)er.nextElement();
            userIDs.addElement(userID);
        }
        centerScreen();
    }
    void jbInit() throws Exception
    {
        panel1.setLayout(gridBagLayout1);
        jButton1.setText("试听");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
              jButton1_actionPerformed(e);
            }
        });
        jButton2.setText("删除");
    jButton2.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton2_actionPerformed(e);
      }
    });
        jButton3.setText("播放");
    jButton3.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton3_actionPerformed(e);
      }
    });
        jLabel1.setText("听力列表");
    this.getContentPane().setLayout(borderLayout1);
    this.setResizable(false);
    jPanel1.setLayout(borderLayout2);
    this.getContentPane().add(panel1,  BorderLayout.NORTH);
    panel1.add(jLabel1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(3, 6, 0, 0), 139, 2));
    panel1.add(jScrollPane1,  new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 6, 0, 9), -67, -198));
    panel1.add(jButton3,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(17, 13, 0, 108), 15, -5));
    panel1.add(jButton2,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(16, 22, 0, 25), 11, -4));
    panel1.add(jButton1,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(17, 15, 0, 0), 15, -5));
    this.getContentPane().add(jPanel1,  BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTableListen, null);
    }

    void jButton1_actionPerformed(ActionEvent e)
    {
        try
        {
            int row=jTableListen.getSelectedRow();
            if(row<0) return;
            if(jButton1.getText().equals("试听"))
            {
                String listenSnippet=(String)((ListenPlayTableModel)jTableListen.getModel()).getValueAt(row,2);
                String url=this.systemControl.IListen.getListenSnippetURL(listenSnippet);
                System.out.println("the URL is:"+url);
                String port=this.systemControl.jmfApi.audioTransmit(url,JMFAPI.BROADCASTIP,false);
                //processor=jmfApi.getProcessor();
                //addPlayPanel(processor);
                if(port!=null)
                    this.systemControl.jmfApi.audioRecieve(JMFAPI.BROADCASTIP,port);
                else
                {
                    showInfoMsg(this,"无效听力文件！");
                    return;
                }
                jButton1.setText("停止试听");
            }
            else
            {
                this.systemControl.jmfApi.stopJMFDevice();
                jButton1.setText("试听");
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
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
    void addPlayPanel(Player player)
    {
        this.player=player;
        PlayerPanel playPanel=new PlayerPanel(player);
        playPanel.setBounds(new Rectangle(15, 198, 377, 2));
        //this.getContentPane().add(playPanel, null);
        jPanel1.add("South",playPanel);
        //this.getContentPane().add(playPanel,null);
    }

    void jButton2_actionPerformed(ActionEvent e)
    {
        int row=jTableListen.getSelectedRow();
        if(row<0) return;
        ((ListenPlayTableModel)jTableListen.getModel()).removeRow(row);
    }

    void jButton3_actionPerformed(ActionEvent e)
    {
        try
        {
            int row=jTableListen.getSelectedRow();
            if(row<0) return;
            if(jButton3.getText().equals("播放"))
            {
                String listenSnippet=(String)((ListenPlayTableModel)jTableListen.getModel()).getValueAt(row,2);
                String url=this.systemControl.IListen.getListenSnippetURL(listenSnippet);
                System.out.println("the URL is:"+url);
                Hashtable Htuser=this.systemControl.getHtStudentIDInterface();
                String port=this.systemControl.jmfApi.audioTransmit(url,null,true);
                if(port==null)
                {
                    showInfoMsg(this,"无效听力文件！");
                    return;
                }
                processor=this.systemControl.jmfApi.getProcessor();
                addPlayPanel(processor);
                boolean b=this.systemControl.jmfApi.audioRecieve(JMFAPI.BROADCASTIP,port);
                this.systemControl.communication.listenPlay(userIDs,null,url,port);
                jButton3.setText("停止播放");
            }
            else
            {
                this.systemControl.communication.stopListenPlay(userIDs,null);
                jButton3.setText("播放");
                jPanel1.removeAll();
            }
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
        setLocation((dim.width - 420) / 2,(dim.height - 230) / 2);
    }
    /**
    * MOTHOD: showInfoMsg
    * DESC  : 显示信息消息。
    * CREATE: 1.0 Ardy 2001-09-29
    * MODIFY:
    */
    void showInfoMsg(Component parentComponent,String infoMsg)
    {
        JOptionPane pane = new javax.swing.JOptionPane();

        parentComponent.setEnabled(false);
        try
        {
            pane.showMessageDialog(this, "提示信息：" + infoMsg,"播放听力",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            System.out.println("信息对话框->" + e.getMessage());
        }
        finally
        {
              parentComponent.setEnabled(true);
        }

    }
}