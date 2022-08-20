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
import java.awt.event.*;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import com.dc.eschool.system.*;
import com.dc.eschool.MainFrm_AboutBox;
import java.io.StringReader;
import javax.swing.text.html.*;
import com.dc.eschool.group.*;
import com.dc.eschool.systemControl.SystemControl;
/**
 * Title: JDialogOnlineTalkStudent
 * Description: 联机讨论
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Ardy
 * @version 1.0
 */

public class JDialogOnlineTalkStudent extends JDialog {
  JPanel panel1 = new JPanel();
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenu1 = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenu jMenu2 = new JMenu();
  JToolBar jToolBar1 = new JToolBar();
  JButton jButton1 = new JButton();
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
    public String allContent="";//记录所有的说话内容
    //HTMLEditorKit htmlKit = new HTMLEditorKit();
    JTextPane jTextPane1 = new JTextPane();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JMenuItem jMenuItem2 = new JMenuItem();
    public JDialogOnlineTalkStudent(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

    public JDialogOnlineTalkStudent(SystemControl sysControl,String port,Hashtable userIDs)//学生
    {
        this(sysControl.mainFrm, "联机讨论", false);
        try
        {
            this.systemControl=sysControl;
            String type=this.systemControl.getUserType();
            if(type!=null&&type.equals(EschoolUser.ESCHOOL_AUDITOR))
            {
                jButton1.setEnabled(false);
                jButton3.setEnabled(false);
            }
            this.init();
            String userID=this.systemControl.getUserID();
            String groupName=this.systemControl.userGroup.getGroupNameByUserID(userID);
            this.systemControl.onlineTalkStudentListModel.setCurrentGroup(groupName);
            this.systemControl.onlineTalkStudentListModel.setData(userIDs);
            systemControl.onlineTalkGroupLIstModel.updateGroup();
            jListGroup.setSelectedValue(groupName,true);
            jListStudent.setSelectedIndex(0);
            this.chat(sysControl.getUserName(),port);
            Teacher teacher=this.systemControl.getTeacherInterface();
            this.systemControl.privateTalk=new PrivateTalk(jTextPane1,teacher,this.systemControl);
        }
        catch(Exception e)
        {
            System.out.println("JDialogOnlineTalkStudent Exception"+e.getMessage());
        }
    }
    void jbInit() throws Exception
    {
        ImageIcon imgBbs = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalkStudent.class.getResource("16bbs.gif"));
        ImageIcon imgBbsg = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalkStudent.class.getResource("16bbsg.gif"));
        ImageIcon imgSound = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalkStudent.class.getResource("sound.gif"));
        ImageIcon imgSoundg = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalkStudent.class.getResource("soundg.gif"));
        ImageIcon imgWayout = new ImageIcon(com.dc.eschool.communication.JDialogOnlineTalkStudent.class.getResource("That-a-Way.gif"));
        panel1.setLayout(gridBagLayout1);
        jMenu1.setText("文件");
        jMenuItem1.setText("退出");
        jMenu2.setText("帮助");
        jButton1.setText("发言");
        jTextPane1.setEditable(false);
    jTextPane1.setContentType("text/html");
    jButton1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton1_actionPerformed(e);
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
        jListStudent.setToolTipText("");
    jListStudent.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mousePressed(MouseEvent e)
      {
        jListStudent_mousePressed(e);
      }
    });

    this.setResizable(false);
    jListGroup.setEnabled(false);
    jListGroup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jLabel3.setText("参与人员");
    jLabel4.setText("参与小组");
        this.setSize(850,600);
        this.setTitle("联机讨论");
        jMenuItem2.setText("关于");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem2_actionPerformed(e);
      }
    });
    jToolBar1.add(jButton1, null);
        jToolBar1.add(jButton3, null);
    panel1.add(jComboBox1, new GridBagConstraints(5, 3, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 21, 4, 41), 6, 1));
    panel1.add(jLabel2, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(11, 15, 2, 8), 7, 4));
    panel1.add(jCheckBox1,      new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(14, 22, 0, 6), -4, -6));
    panel1.add(jLabel4,      new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(4, 7, 4, 70), 141, 1));
    panel1.add(jScrollPane1,      new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 7, 0, 0), -58, 190));
    panel1.add(jScrollPane2,       new GridBagConstraints(1, 2, 5, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 6, 0, 8), 603, 312));
    panel1.add(jScrollPane3,       new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 8, 13, 0), -59, -7));
    panel1.add(jLabel1,      new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(9, 14, 0, 130), 119, 2));
    panel1.add(jScrollPane4,         new GridBagConstraints(1, 4, 5, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 9, 14, 10), 606, 101));
    panel1.add(jTextField1,         new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 9, 0, 0), 272, 4));
    panel1.add(jLabel3,      new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(9, 9, 0, 0), 149, 0));
    panel1.add(jToolBar1,        new GridBagConstraints(0, 0, 4, 1, 1.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 3, 0, 32), 339, 0));
    jScrollPane4.getViewport().add(jTextArea2, null);
    jScrollPane3.getViewport().add(jListGroup, null);
    jScrollPane2.getViewport().add(jTextPane1, null);
    jScrollPane1.getViewport().add(jListStudent, null);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenu1.add(jMenuItem1);
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    jMenu2.add(jMenuItem2);
        this.setJMenuBar(jMenuBar1);
        jButton1.setIcon(imgBbs);
        jButton3.setIcon(imgSound);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//无法关闭
    }
    void init()
    {
        systemControl.onlineTalkStudentListModel=new OnlineTalkStudentListModel(systemControl.userGroup,EschoolUser.ESCHOOL_STUDENT);
        jListStudent.setModel(systemControl.onlineTalkStudentListModel);
        systemControl.onlineTalkGroupLIstModel=new OnlineTalkGroupLIstModel(systemControl.onlineTalkStudentListModel);
        jListGroup.setModel(systemControl.onlineTalkGroupLIstModel);
        System.out.println("init() is ok");
        initComboBox();
        jComboBox1.setSelectedIndex(0);
        centerScreen();

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
       // private String allContent;
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
                    allContent+=message;
                    //htmlKit.read(new StringReader(this.allContent),jTextPane1.getDocument(),0);
                    jTextPane1.setText(allContent);
                    //jTextArea1.append(message + "\n");
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
      //htmlKit.read(new StringReader(this.allContent),jTextPane1.getDocument(),0);
      jTextPane1.setText(allContent);
      //jTextArea1.append(message + "\n");
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
                        int row=jListStudent.getSelectedIndex();
                        if(row<0) return;
                        String toName=(String)systemControl.onlineTalkStudentListModel.getElementAt(row);
                        userName = args[i];
                        prefix = userName +"对"+toName+ "说: ";
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
        String[] args={BROADCASTIP,port,userName};
        System.out.println("port.."+port);
        System.out.println("args.."+args);
        parseArgs(args);
        createSocket();
        //jTextArea2.addActionListener(new SendMessage());
        //jTextArea2.requestFocus();
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
            content=systemControl.getUserName()+"私下对 老师说："+content;
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
                //jTextArea1.append(message + "\n");
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    void jCheckBox1_actionPerformed(ActionEvent e)
    {
        jListStudent.setSelectedIndex(0);

    }

    void jButton3_actionPerformed(ActionEvent e)
    {
        Vector userIDs;
        String id=this.systemControl.getUserID();
        Teacher teacher=this.systemControl.getTeacherInterface();
        userIDs=((OnlineTalkStudentListModel)jListStudent.getModel()).getCurrentGroupUserIDs();
        if (userIDs==null)
        {
            this.systemControl.jmfApi.stopJMFTransmit();
            jButton3.setText("声音广播");
            return;
        }
        userIDs.remove(id);
        if(jButton3.getText().equals("声音广播"))
        {
            this.systemControl.communication.voiceBroadCast(userIDs,teacher);
            jButton3.setText("停止广播");
        }
        else
        {
            this.systemControl.jmfApi.stopJMFTransmit();
            jButton3.setText("声音广播");
        }
        jButton3.setVisible(false);
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
    void initComboBox()
    {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("黑");
        jComboBox1.addItem("蓝");
        jComboBox1.addItem("红");
        jComboBox1.addItem("绿");
    }

    void jListStudent_mousePressed(MouseEvent e)
    {
        if(jCheckBox1.isSelected()==true&&jListStudent.getSelectedIndex()!=0)
            jCheckBox1.setSelected(false);
        int row=jListStudent.getSelectedIndex();
        if(row<0) return;
        String toName=(String)systemControl.onlineTalkStudentListModel.getElementAt(row);
        jTextField1.setText(toName);
        prefix = userName +"对"+toName+ "说: ";
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
}