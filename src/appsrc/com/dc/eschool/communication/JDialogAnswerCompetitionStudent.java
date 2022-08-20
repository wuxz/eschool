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
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import com.dc.eschool.system.*;
import com.dc.eschool.MainFrm_AboutBox;
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.group.*;
import com.sun.multicast.util.AssertFailedException;
import com.sun.multicast.util.ImpossibleException;
import com.sun.multicast.reliable.RMException;
import com.sun.multicast.util.UnsupportedException;
import com.sun.multicast.reliable.transport.InvalidMulticastAddressException;
import com.sun.multicast.reliable.transport.InvalidTransportProfileException;
import com.sun.multicast.reliable.transport.RMPacketSocket;
import com.sun.multicast.reliable.transport.TransportProfile;
import com.sun.multicast.reliable.transport.lrmp.LRMPTransportProfile;
/**
 * Title: JDialogAnswerCompetitionStudent
 * Description: 电子抢答
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Ardy
 * @version 1.0
 */

public class JDialogAnswerCompetitionStudent extends JDialog {
    JPanel panel1 = new JPanel();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JToolBar jToolBar1 = new JToolBar();
    JButton jButtonWordAnswer = new JButton();
    JButton jButtonVoiceAnswer = new JButton();
    JButton jButtonAnswerOver = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JScrollPane jScrollPane2 = new JScrollPane();
    JScrollPane jScrollPane3 = new JScrollPane();
    JList jListSequence = new JList();
    JTextArea jTextAreaInformation = new JTextArea();
    JLabel jLabel1 = new JLabel();
    JScrollPane jScrollPane4 = new JScrollPane();
    JTextArea jTextAreaInput = new JTextArea();
    JLabel jLabel2 = new JLabel();
    JButton jButtonStartAnswerCompetition = new JButton();
    JList jListStudent = new JList();
    private SystemControl systemControl;
    private Vector userIDs;
    private Teacher teacher;
    private static final String BROADCASTIP = "224.1.1.1";
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
    BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JMenuItem jMenuItem2 = new JMenuItem();
    public JDialogAnswerCompetitionStudent(Frame frame, String title, boolean modal)
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

    public JDialogAnswerCompetitionStudent(SystemControl sysControl,String port,Vector userIDs)
    {
        this(sysControl.mainFrm, "电子抢答", false);
        try
        {
            this.systemControl=sysControl;
            this.init();
            String type=this.systemControl.getUserType();
            if(type!=null&&type.equals(EschoolUser.ESCHOOL_AUDITOR))
            {
                jButtonWordAnswer.setEnabled(false);
                jButtonVoiceAnswer.setEnabled(false);
                jButtonAnswerOver.setEnabled(false);
                jButtonStartAnswerCompetition.setEnabled(false);
            }
            this.systemControl.answerCompetitionListModelStudent.setData(userIDs);
            jButtonWordAnswer.setEnabled(false);
            jButtonVoiceAnswer.setEnabled(false);
            jButtonAnswerOver.setEnabled(false);
            jButtonStartAnswerCompetition.setEnabled(false);
            try
            {
                this.chat(systemControl.getUserName(),port);
            }
            catch(Exception ex)
            {
                System.out.println("JDialogAnswerCompetitionStudent chat Exception:"+ex.getMessage());
                ex.printStackTrace();
            }
        }
        catch(Exception e)
        {
            System.out.println("Socket Exception"+e.getMessage());
        }
    }
    void jbInit() throws Exception
    {
        ImageIcon imgBbs = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("16bbs.gif"));
        ImageIcon imgBbsg = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("16bbsg.gif"));
        ImageIcon imgSound = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("sound.gif"));
        ImageIcon imgSoundg = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("soundg.gif"));
        ImageIcon imgWayout = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("That-a-Way.gif"));
        ImageIcon imglink = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("16link.gif"));
        ImageIcon imglinkg = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("16linkg.gif"));
        ImageIcon imgstop = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("stop.gif"));
        ImageIcon imgstopg = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("stopg.gif"));
        ImageIcon imgyes = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("yes.gif"));
        ImageIcon imgyesg = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("yesg.gif"));
        ImageIcon imgno = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("no.gif"));
        ImageIcon imgnog = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("nog.gif"));
        ImageIcon imghand2 = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("hand2.gif"));
        ImageIcon imghand2g = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("hand2g.gif"));
        ImageIcon imgok = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("ok.gif"));
        ImageIcon imgokg = new ImageIcon(com.dc.eschool.communication.JDialogAnswerCompetition.class.getResource("okg.gif"));

        jButtonWordAnswer.setIcon(imgBbs);
        jButtonVoiceAnswer.setIcon(imgSound);
        jButtonAnswerOver.setIcon(imgok);

        panel1.setLayout(gridBagLayout1);
        jMenu1.setText("文件");
        jMenuItem1.setText("退出");
        jMenu2.setText("帮助");
        jButtonWordAnswer.setActionCommand("开始抢答");
        jButtonWordAnswer.setText("文字回答");
    jButtonWordAnswer.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonWordAnswer_actionPerformed(e);
      }
    });
     // jButtonWordAnswer.addActionListener(new SendMessage());
        jButtonVoiceAnswer.setText("语音回答");
    jButtonVoiceAnswer.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonVoiceAnswer_actionPerformed(e);
      }
    });
        jButtonAnswerOver.setText("回答完毕");
    jButtonAnswerOver.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonAnswerOver_actionPerformed(e);
      }
    });
        jLabel1.setText("对话信息");
        this.setResizable(false);
    this.addKeyListener(new java.awt.event.KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        this_keyPressed(e);
      }
    });
    this.getContentPane().setLayout(borderLayout1);
        jLabel2.setText("输入");
        jButtonStartAnswerCompetition.setText("开始抢答");
    jButtonStartAnswerCompetition.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonStartAnswerCompetition_actionPerformed(e);
      }
    });
    jListSequence.setEnabled(false);
    jListStudent.setEnabled(false);
    panel1.setMaximumSize(new Dimension(850, 650));
    panel1.setMinimumSize(new Dimension(850, 650));
    panel1.setPreferredSize(new Dimension(850, 650));
    jTextAreaInformation.setEditable(false);
    jLabel3.setText("参与人员");
    jLabel4.setText("抢答顺序");
    jMenuItem2.setText("关于");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem2_actionPerformed(e);
      }
    });
    jToolBar1.add(jButtonWordAnswer, null);
    jToolBar1.add(jButtonVoiceAnswer, null);
    jToolBar1.add(jButtonAnswerOver, null);
    panel1.add(jButtonStartAnswerCompetition, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(3, 0, 3, 26), 1, -5));
    panel1.add(jLabel3,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 95), 109, -1));
    panel1.add(jLabel4,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(7, 5, 0, 89), 115, 8));
    panel1.add(jScrollPane3,  new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 7, 0), -7, -34));
    panel1.add(jScrollPane2,  new GridBagConstraints(1, 2, 2, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 7), 485, 250));
    panel1.add(jLabel1,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 216), 119, 2));
    panel1.add(jLabel2,  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 9, 221), 138, 3));
    panel1.add(jScrollPane4,  new GridBagConstraints(1, 4, 2, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 7, 7), 485, 75));
    jScrollPane4.getViewport().add(jTextAreaInput, null);
    jScrollPane2.getViewport().add(jTextAreaInformation, null);
    jScrollPane3.getViewport().add(jListSequence, null);
    panel1.add(jScrollPane1,  new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), -7, 139));
    jScrollPane1.getViewport().add(jListStudent, null);
    panel1.add(jToolBar1,  new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 323, 6));
    jMenuBar1.add(jMenu1);
    jMenuBar1.add(jMenu2);
    jMenu1.add(jMenuItem1);
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    jMenu2.add(jMenuItem2);
    this.setJMenuBar(jMenuBar1);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//无法关闭
    }

    void init()
    {
        systemControl.answerCompetitionListModelStudent=new AnswerCompetitionListModel(this.systemControl.userGroup);
        jListStudent.setModel(systemControl.answerCompetitionListModelStudent);
        systemControl.answerCompetitionSequenceListModel=new AnswerCompetitionSequenceListModel(this.systemControl.userGroup);
        jListSequence.setModel(systemControl.answerCompetitionSequenceListModel);
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

        public void run()
        {
            while (!done)
            {
                try
                {

                    DatagramPacket dp = socket.receive();
                    String message = new String(dp.getData(), "UTF8");

                    jTextAreaInformation.append(message + "\n");
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
    class SendMessage implements ActionListener {

        /**
         * When a message is entered, send it.
         */
        public void actionPerformed(ActionEvent e)
        {
           jButtonWordAnswer_actionPerformed(e);
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
      jTextAreaInformation.append(message + "\n");
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

        receiver = new ReceiverThread();

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
                        prefix = userName + ": ";

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
        throws IllegalArgumentException, IOException, RMException
    {
        String[] args={BROADCASTIP,port,userName};
        parseArgs(args);
        createSocket();
        //jTextAreaInput.aaddActionListener(new SendMessage());
        //jTextAreaInput.requestFocus();
    }


    void jButtonWordAnswer_actionPerformed(ActionEvent e)
    {
        try {
                String message = prefix + jTextAreaInput.getText();

                jTextAreaInput.setText("");

                byte data[] = message.getBytes("UTF8");
                DatagramPacket dp = new DatagramPacket(data, data.length, ia,
                                                       port);

                socket.send(dp);
                jTextAreaInformation.append(message + "\n");
                jButtonAnswerOver.setEnabled(true);
            }
            catch (Exception ex) {}
    }


    /**通知学生是否可以开始抢答*/
    public void setBeginAnswerCompetition(boolean b)
    {
        jButtonStartAnswerCompetition.setEnabled(b);
    }
    /**通知学生是否可以开始回答*/
    public void setBeginAnswer(boolean b)
    {
        jButtonWordAnswer.setEnabled(b);
        jButtonVoiceAnswer.setEnabled(b);
        if(b==false)
        {
            userIDs=this.systemControl.answerCompetitionListModelStudent.getUserIDData();
            teacher=(Teacher)this.systemControl.getTeacherInterface();
            this.systemControl.communication.stopVoiceBroadCast(userIDs,teacher);
        }
    }
    void jButtonVoiceAnswer_actionPerformed(ActionEvent e)
    {
        userIDs=this.systemControl.answerCompetitionListModelStudent.getUserIDData();
        String id=this.systemControl.getUserID();
        boolean b=userIDs.remove(id);//删除自己
        teacher=(Teacher)this.systemControl.getTeacherInterface();
        this.systemControl.communication.voiceBroadCast(userIDs,teacher);
        jButtonAnswerOver.setEnabled(true);
    }

    void jButtonAnswerOver_actionPerformed(ActionEvent e)
    {
       //this.systemControl.communication.sendInformationToTeacher("回答完毕！");
       try {
                String message = prefix + "回答完毕！";

                jTextAreaInput.setText("");

                byte data[] = message.getBytes("UTF8");
                DatagramPacket dp = new DatagramPacket(data, data.length, ia,
                                                       port);

                socket.send(dp);
                jTextAreaInformation.append(message + "\n");
                jButtonAnswerOver.setEnabled(true);
            }
            catch (Exception ex) {}
       userIDs=this.systemControl.answerCompetitionListModelStudent.getUserIDData();
       teacher=(Teacher)this.systemControl.getTeacherInterface();
       this.systemControl.communication.stopVoiceBroadCast(userIDs,teacher);
       jButtonAnswerOver.setEnabled(false);
       jButtonWordAnswer.setEnabled(false);
       jButtonVoiceAnswer.setEnabled(false);
    }

    void jButtonStartAnswerCompetition_actionPerformed(ActionEvent e)
    {
        this.systemControl.communication.studentAnswerCompetition(systemControl.getUserID());
    }

    void this_keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==e.VK_F12)
          this.systemControl.communication.studentAnswerCompetition(systemControl.getUserID());
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