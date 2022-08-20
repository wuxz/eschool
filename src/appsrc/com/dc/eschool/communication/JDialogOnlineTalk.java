package com.dc.eschool.communication;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import com.sun.multicast.util.AssertFailedException;
import com.sun.multicast.util.ImpossibleException;
import com.sun.multicast.reliable.RMException;
import com.sun.multicast.util.UnsupportedException;
import com.sun.multicast.reliable.transport.InvalidMulticastAddressException;
import com.sun.multicast.reliable.transport.InvalidTransportProfileException;
import com.sun.multicast.reliable.transport.RMPacketSocket;
import com.sun.multicast.reliable.transport.TransportProfile;
import com.sun.multicast.reliable.transport.lrmp.LRMPTransportProfile;
import javax.swing.*;
import javax.swing.text.html.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.Hashtable;
import com.dc.eschool.system.*;
import com.dc.eschool.MainFrm_AboutBox;
import java.util.Enumeration;
import com.dc.eschool.group.*;
import java.io.StringReader;

import com.dc.eschool.systemControl.SystemControl;
/**
 * Title: JDialogOnlineTalk
 * Description: 联机讨论
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Ardy
 * @version 1.0
 */

public class JDialogOnlineTalk extends JDialog {
    public String allContent="";//记录所有的说话内容
    JPanel panel1 = new JPanel();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JToolBar jToolBar1 = new JToolBar();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JScrollPane jScrollPane2 = new JScrollPane();
    JScrollPane jScrollPane3 = new JScrollPane();
    JList jListGroup = new JList();
    JList jListStudent = new JList();
    JLabel jLabel1 = new JLabel();
    JScrollPane jScrollPane4 = new JScrollPane();
    JTextArea jTextArea2 = new JTextArea();
    JTextField jTextField1 = new JTextField();
    JCheckBox jCheckBox1 = new JCheckBox();
    JLabel jLabel2 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();

    //-->>Chat Variable
    boolean done = false;
    String userName;
    String prefix;
    String transportName = "LRMP";
    boolean verbose = false;
    InetAddress ia;
    int port;
    RMPacketSocket socket = null;
    ReceiverThread receiver = null;
    private SystemControl systemControl;
    private static final String BROADCASTIP = "224.1.1.1";
    private String strColor="black";
    //HTMLEditorKit htmlKit = new HTMLEditorKit();
    JTextPane jTextPane1 = new JTextPane();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JMenuItem jMenuItem2 = new JMenuItem();
    public JDialogOnlineTalk(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

    public JDialogOnlineTalk(SystemControl sysControl)//老师
    {
        this(sysControl.mainFrm, "联机讨论", false);
        try
        {
            this.systemControl=sysControl;
            this.init();
            Student student=null;
            this.systemControl.privateTalk=new PrivateTalk(jTextPane1,student,this.systemControl);
            System.out.println("Construction is over");
        }
        catch(Exception e)
        {
            System.out.println("Socket Exception"+e.getMessage());
        }
    }

    void jbInit() throws Exception
    {
        ImageIcon imgBbs = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalk.class.getResource("16bbs.gif"));
        ImageIcon imgBbsg = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalk.class.getResource("16bbsg.gif"));
        ImageIcon imgSound = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalk.class.getResource("sound.gif"));
        ImageIcon imgSoundg = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalk.class.getResource("soundg.gif"));
        ImageIcon imgWayout = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalk.class.getResource("That-a-Way.gif"));
        panel1.setLayout(gridBagLayout1);
        jMenu1.setText("文件");
        jMenuItem1.setText("退出");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem1_actionPerformed(e);
      }
    });
        jMenu2.setText("帮助");
        jButton1.setText("发言");
    jButton1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton1_actionPerformed(e);
      }
    });
        //jTextPane1.setEditorKit(htmlKit);
        jTextPane1.setEditable(false);
    jTextPane1.setContentType("text/html");
        //jButton1.addActionListener(new SendMessage());

        jButton2.setText("结束讨论");
    jButton2.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton2_actionPerformed(e);
      }
    });
        jButton3.setText("声音广播");
    jButton3.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton3_actionPerformed(e);
      }
    });
        jLabel1.setText("对话信息");
        jTextField1.setEditable(false);
        jCheckBox1.setText("私下");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            jCheckBox1_actionPerformed(e);
          }
        });
        jLabel2.setText("颜色");
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
        public void windowClosing(WindowEvent e)
        {
        //close();
        this_windowClosing(e);
        }
        });
        jListStudent.setToolTipText("");
    jListStudent.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mousePressed(MouseEvent e)
      {
        jListStudent_mousePressed(e);
      }
    });
        jListGroup.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mousePressed(MouseEvent e)
      {
        jListGroup_mousePressed(e);
      }
    });
    this.setResizable(false);
    jLabel3.setText("参与人员");
    jLabel4.setText("参与小组");
    jMenuItem2.setText("关于");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem2_actionPerformed(e);
      }
    });
    getContentPane().add(panel1);
        panel1.add(jToolBar1,               new GridBagConstraints(0, 0, 4, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 35), 256, 0));
        this.setSize(850,600);
        this.setTitle("联机讨论");
        jToolBar1.add(jButton1, null);
        jToolBar1.add(jButton2, null);
        jToolBar1.add(jButton3, null);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenu1.add(jMenuItem1);
        panel1.add(jScrollPane1,               new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 7, 0, 0), -58, 189));
        jScrollPane1.getViewport().add(jListStudent, null);
        panel1.add(jScrollPane2,                new GridBagConstraints(1, 2, 5, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 6, 0, 7), 603, 310));
        jScrollPane2.getViewport().add(jTextPane1, null);
        panel1.add(jScrollPane3,                 new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 8, 12, 0), -60, -9));
        panel1.add(jLabel1,               new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(9, 14, 0, 130), 119, 2));
        panel1.add(jScrollPane4,                   new GridBagConstraints(1, 4, 5, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 9, 14, 9), 604, 95));
        panel1.add(jTextField1,                 new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 9, 0, 0), 289, 4));
    panel1.add(jLabel3,               new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 7, 0, 0), 153, 2));
    panel1.add(jComboBox1,                    new GridBagConstraints(5, 3, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 0, 4, 27), 9, 1));
    panel1.add(jLabel2,               new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(9, 15, 3, 2), 13, 4));
    panel1.add(jCheckBox1,             new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 3, 3), -7, -6));
    panel1.add(jLabel4,   new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(14, 9, 0, 71), 81, -2));
        jScrollPane4.getViewport().add(jTextArea2, null);
        jScrollPane3.getViewport().add(jListGroup, null);
    jMenu2.add(jMenuItem2);
        this.setJMenuBar(jMenuBar1);
        jButton1.setIcon(imgBbs);
        jButton2.setIcon(imgWayout);
        jButton3.setIcon(imgSound);
    }
    void init()
    {
        try
        {
            systemControl.onlineTalkStudentListModel=new OnlineTalkStudentListModel(systemControl.userGroup,EschoolUser.ESCHOOL_TEACHER);
            jListStudent.setModel(systemControl.onlineTalkStudentListModel);
            systemControl.onlineTalkGroupLIstModel=new OnlineTalkGroupLIstModel(systemControl.onlineTalkStudentListModel);
            jListGroup.setModel(systemControl.onlineTalkGroupLIstModel);
            initComboBox();
            jComboBox1.setSelectedIndex(0);
            centerScreen();
        }
        catch(Exception e)
        {
            System.out.println("JDialogOnlineTalk Exception:"+e.getMessage());
            e.printStackTrace();
        }

    }
    public void centerScreen()
    {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - 750) / 2,(dim.height - 550) / 2);
    }
   /**
     * Inner class to receive messages and display them
     */
    class ReceiverThread extends Thread
    {
        private boolean done = false;
        //private String allContent;
        public ReceiverThread(String allContent)
        {
            //this.allContent=allContent;
        }
        public void run()
        {
            while (!done)
            {
                try
                {

                    DatagramPacket dp = socket.receive();
                    String message = new String(dp.getData(), "UTF8");
                    allContent=allContent+message;
                    //htmlKit.read(new StringReader(this.allContent),jTextPane1.getDocument(),0);
                    jTextPane1.setText(allContent);
                    //jTextArea1.append(message + "\n");  ddd
		    }
                catch (Exception ex) {}
            }
        }

        public void terminate()
        {
            done = true;
        }

    }
   /**
     * Inner class to send a message.
     */
    class SendMessage implements ActionListener
    {
        /**
         * When a message is entered, send it.
         */
        public void actionPerformed(ActionEvent e) {
            try
            {
                jButton1_actionPerformed(e);
            }
             catch (Exception ex) {}
        }

    }
    void close() {
        if (receiver != null) {
            receiver.terminate();
        }
        if (socket != null) {
	    sendSignoff();
            socket.close();
        }
        dispose();
    }
  /**
     * Sends a final signoff message when window is closing
     */
    void sendSignoff() {
	String message = prefix + " signing off";

	try {
	byte data[] = message.getBytes("UTF8");
	DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
	socket.send(dp);
      this.allContent+=message;
     // htmlKit.read(new StringReader(this.allContent),jTextPane1.getDocument(),0);
      jTextPane1.setText(allContent);
      //jTextArea1.append(message + "\n"); ddd
      } catch (Exception ex) {}
    }

    /**
     * Creates an RMPacketSocket.
     * @exception IOException if an I/O error occurs
     * @exception RMException if a reliable multicast related exception occurs
     */
    void createSocket() throws IOException, RMException {
        TransportProfile tp = null;

        if (transportName.equals("LRMP")) {

            /*
             * Obtain a new LRMPTransportProfile with the address and
             * port specified.
             */
            LRMPTransportProfile lrmptp;

            try {
                lrmptp = new LRMPTransportProfile(ia, port);
            } catch (InvalidMulticastAddressException e) {
                throw new ImpossibleException(e);
            }

            tp = lrmptp;

            lrmptp.setTTL((byte) 1);
            lrmptp.setOrdered(false);
        } else {
            throw new AssertFailedException();
        }

        try {
            socket = tp.createRMPacketSocket(TransportProfile.SEND_RECEIVE);
        } catch (InvalidTransportProfileException e) {
            throw new ImpossibleException(e);
        } catch (UnsupportedException e) {
            throw new ImpossibleException(e);
        }

        receiver = new ReceiverThread(this.allContent);

        receiver.start();
    }
    /**
     * Parse the command line arguments.
     *
     * @param args the command line arguments
     * @exception IllegalArgumentException if there is a problem
     * parsing the args
     * (also prints the usage to System.out)
     */
    void parseArgs(String[] args) throws IllegalArgumentException {
        int mainArgs = 0;

        try {
            for (int i = 0; i < args.length; i++) {

                // @@@ Should check for duplicate flags

                if (args[i].startsWith("-")) {
                    if (args[i].equals("-transport")) {
                        if (i + 1 >= args.length) {
                            throw new IllegalArgumentException();
                        }

                        i += 1;
                        transportName = args[i];

                        if (!transportName.equals("LRMP")) {
                            throw new IllegalArgumentException();
                        }
                    } else if (args[i].equals("-verbose")) {
                        verbose = true;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    switch (mainArgs) {

                    case 0:
                        try {
                            ia = InetAddress.getByName(args[i]);
                        } catch (UnknownHostException e) {
                            throw new IllegalArgumentException();
                        }

                        if (!ia.isMulticastAddress()) {
                            throw new IllegalArgumentException();
                        }

                        break;

                    case 1:
                        port = Integer.parseInt(args[i]);

                        break;

                    case 2:
                        userName = args[i];
                        int row=jListStudent.getSelectedIndex();
                        if(row<0) return;
                        String toName=(String)systemControl.onlineTalkStudentListModel.getElementAt(row);
                        prefix = "老师对"+toName+ "说: ";

                        break;

                    default:
                        throw new IllegalArgumentException();
                    }

                    mainArgs += 1;
                }
            }

            if (mainArgs != 3) {
                throw new IllegalArgumentException();
            }
            if (verbose) {
                System.out.println("Multicast Address   = "
                                   + ia.getHostAddress());
                System.out.println("Multicast Port      = " + port);
                System.out.println("Transport Name      = " + transportName);
                System.out.println("User Name           = " + userName);
            }
        } catch (IllegalArgumentException e) {

            throw e;
        }
    }
   /**
     * Lets the chat run until the window is closed.
     */

  public void chat(String userName,String port)
            throws IllegalArgumentException, IOException, RMException {
        try
        {
            String[] args={BROADCASTIP,port,userName};
            parseArgs(args);
            createSocket();
            //jTextArea2.addActionListener(new SendMessage());
            //jTextArea2.requestFocus();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("JDialogOnlineTalk.chat Exception"+e.getMessage());
        }
    }

    void jButton1_actionPerformed(ActionEvent e)
    {
        int row=jListStudent.getSelectedIndex();
        if(row<0) return;
        if(jCheckBox1.isSelected()==true)
        {
            String content=jTextArea2.getText();
            this.strColor=(String)jComboBox1.getSelectedItem();
            this.strColor=this.getColor(this.strColor);
            content=this.setStyle(content,strColor,false,false,false);
            jTextArea2.setText("");
            String userName=(String)((OnlineTalkStudentListModel)jListStudent.getModel()).getElementAt(row);
            content="老师私下对 "+userName+"说："+content;
            this.systemControl.privateTalk.sendText(content);
        }
        else
        {
            try
            {
                String message=jTextArea2.getText();
                this.strColor=(String)jComboBox1.getSelectedItem();
                this.strColor=this.getColor(this.strColor);
                message=this.setStyle(message,strColor,false,false,false);
                message=prefix+message;
                jTextArea2.setText("");
                byte data[] = message.getBytes("UTF8");
                DatagramPacket dp = new DatagramPacket(data, data.length, ia,
                                                       port);
                socket.send(dp);
                this.allContent+=message;
                //htmlKit.read(new StringReader(this.allContent),jTextPane1.getDocument(),0);
                jTextPane1.setText(allContent);
                //jTextArea1.append(message + "\n");  ddd
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    void jCheckBox1_actionPerformed(ActionEvent e)
    {
        int row=jListStudent.getSelectedIndex();
        if(row<0) return;

        if(jCheckBox1.isSelected()==true)
        {
            String userID=(String)((OnlineTalkStudentListModel)jListStudent.getModel()).getUserIDAt(row);
            Hashtable htUser=this.systemControl.getHtStudentIDInterface();
            Student student=(Student)htUser.get(userID);
            this.systemControl.privateTalk.setStudent(student);
            //this.systemControl.privateTalk.setContent(this.allContent);
        }

    }

    void jButton2_actionPerformed(ActionEvent e)
    {
        systemControl.communication.endOnlineTalk();
    }

    void jButton3_actionPerformed(ActionEvent e)
    {
        Vector userIDs;
        userIDs=((OnlineTalkStudentListModel)jListStudent.getModel()).getCurrentGroupUserIDs();
        if(jButton3.getText().equals("声音广播"))
        {
            if(userIDs==null)
            {
                showInfoMsg(this,"请选择一讨论小组加入！");
                return;
            }
            this.systemControl.communication.voiceBroadCast(userIDs,null);
            jButton3.setText("停止广播");
        }
        else
        {
            this.systemControl.jmfApi.stopJMFTransmit();
            jButton3.setText("声音广播");
        }
    }
    String setStyle(String content,String color,boolean bold,boolean italic,boolean underline)
    {
        if(color!=null) content="<font color="+color+">"+content+"</font>";
        if(bold==true) content="<B>"+content+"</B>";
        if(italic==true) content="<I>"+content+"</I>";
        if(underline==true) content="<U>"+content+"</U>";
        content+="<br>";
        return content;
    }

    /**
    * MOTHOD: initComboBox
    * DESC  : 初始化课程下拉框。
    * CREATE: 1.0 Ardy 2001-09-29
    * MODIFY:
    */
    void initComboBox()
    {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("黑");
        jComboBox1.addItem("蓝");
        jComboBox1.addItem("红");
        jComboBox1.addItem("绿");
    }
    String getColor(String strColor)
    {
        if(strColor.equals("黑"))
        return "black";
        if(strColor.equals("蓝"))
        return "blue";
        if(strColor.equals("红"))
        return "red";
        if(strColor.equals("绿"))
        return "green";
        return null;
    }
    void this_windowClosing(WindowEvent e)
    {
        systemControl.communication.endOnlineTalk();
    }

    void jListStudent_mousePressed(MouseEvent e)
    {
        int row=jListStudent.getSelectedIndex();
        if(row<0) return;

        if(jCheckBox1.isSelected()==true)
        {
            String userID=(String)((OnlineTalkStudentListModel)jListStudent.getModel()).getUserIDAt(row);
            Hashtable htUser=this.systemControl.getHtStudentIDInterface();
            Student student=(Student)htUser.get(userID);
            this.systemControl.privateTalk.setStudent(student);
        }
        String toName=(String)systemControl.onlineTalkStudentListModel.getElementAt(row);
        jTextField1.setText(toName);
        prefix = "老师对"+toName+ "说: ";
    }

    void jListGroup_mousePressed(MouseEvent e)
    {
        try
        {
            Vector userIDs;
            userIDs=((OnlineTalkStudentListModel)jListStudent.getModel()).getCurrentGroupUserIDs();
            if(jButton3.getText().equals("停止广播"))
            {
                this.systemControl.communication.stopVoiceBroadCast(userIDs,null);
                jButton3.setText("声音广播");
            }
            String groupName=(String)jListGroup.getSelectedValue();
            if(groupName==null) return;
            jListStudent.setSelectedIndex(0);
            this.systemControl.onlineTalkStudentListModel.setCurrentGroup(groupName);
            String port=systemControl.chatServer.getPortByGroupName(groupName);
            this.chat(systemControl.getUserName(),port);

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void jMenuItem1_actionPerformed(ActionEvent e)
    {
        systemControl.communication.endOnlineTalk();
    }

    void jMenuItem2_actionPerformed(ActionEvent e)
    {
        MainFrm_AboutBox dlg = new MainFrm_AboutBox(null);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = Toolkit.getDefaultToolkit().getScreenSize();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 , (frmSize.height - dlgSize.height) / 2 );
        dlg.setModal(true);
        dlg.show();
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
            pane.showMessageDialog(this, "提示信息：" + infoMsg,"联机讨论",JOptionPane.INFORMATION_MESSAGE);
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