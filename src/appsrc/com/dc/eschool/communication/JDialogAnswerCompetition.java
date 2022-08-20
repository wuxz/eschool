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
import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.group.*;
import com.dc.eschool.MainFrm_AboutBox;
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
 * Title: JDialogAnswerCompetition
 * Description: 电子抢答
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Ardy
 * @version 1.0
 */

public class JDialogAnswerCompetition extends JDialog {
    JPanel panel1 = new JPanel();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JToolBar jToolBar1 = new JToolBar();
    JButton jButtonBeginAnswer = new JButton();
    JButton jButtonEndAnswer = new JButton();
    JButton jButtonAnswerRight = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JScrollPane jScrollPane2 = new JScrollPane();
    JScrollPane jScrollPane3 = new JScrollPane();
    JList jListSequence = new JList();
    JTextArea jTextAreaInformation = new JTextArea();
    JLabel jLabel1 = new JLabel();
    JScrollPane jScrollPane4 = new JScrollPane();
    JTextArea jTextAreaInput = new JTextArea();
    JButton jButtonAnswerWrong = new JButton();
    JButton jButtonExplain = new JButton();
    JButton jButtonAllowAnswer = new JButton();
    JLabel jLabel2 = new JLabel();
    JButton jButtonSend = new JButton();
    JList jListStudent = new JList();
    private SystemControl systemControl;
    private Vector userIDs=new Vector();
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
    public JDialogAnswerCompetition(Frame frame, String title, boolean modal)
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

    public JDialogAnswerCompetition(SystemControl sysControl,String port)
    {
        this(sysControl.mainFrm, "电子抢答", false);
        try
        {
            this.systemControl=sysControl;
            this.init();
            jButtonBeginAnswer.setEnabled(true);
            jButtonEndAnswer.setEnabled(false);
            jButtonAnswerRight.setEnabled(true);
            jButtonAnswerWrong.setEnabled(true);
            jButtonExplain.setEnabled(true);
            jButtonAllowAnswer.setEnabled(false);
            try
            {
                this.chat(systemControl.getUserName(),port);
            }
            catch(Exception ex)
            {
                System.out.println("JDialogAnswerCompetition chat Exception:"+ex.getMessage());
                ex.printStackTrace();
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception"+e.getMessage());
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

        jButtonBeginAnswer.setIcon(imglink);
        jButtonEndAnswer.setIcon(imgstop);
        jButtonAnswerRight.setIcon(imgyes);
        jButtonAnswerWrong.setIcon(imgno);
        jButtonExplain.setIcon(imgSound);
        jButtonAllowAnswer.setIcon(imghand2);
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
        jButtonBeginAnswer.setActionCommand("开始抢答");
        jButtonBeginAnswer.setText("开始抢答");
    jButtonBeginAnswer.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonBeginAnswer_actionPerformed(e);
      }
    });
        jButtonEndAnswer.setText("停止抢答");
    jButtonEndAnswer.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonEndAnswer_actionPerformed(e);
      }
    });
        jButtonAnswerRight.setText("回答正确");
    jButtonAnswerRight.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonAnswerRight_actionPerformed(e);
      }
    });
        jLabel1.setText("对话信息");
        this.setResizable(false);
    this.getContentPane().setLayout(borderLayout1);
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
        public void windowClosing(WindowEvent e)
        {
        //close();
        this_windowClosing(e);
        }
        });
        jButtonAnswerWrong.setText("回答错误");
    jButtonAnswerWrong.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonAnswerWrong_actionPerformed(e);
      }
    });
        jButtonExplain.setText("语音讲解");
    jButtonExplain.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonExplain_actionPerformed(e);
      }
    });
        jButtonAllowAnswer.setText("允许回答");
        jButtonAllowAnswer.addActionListener(new java.awt.event.ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            jButtonAllowAnswer_actionPerformed(e);
          }
        });
        jLabel2.setText("输入");
        jButtonSend.setText("发送");
    jButtonSend.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonSend_actionPerformed(e);
      }
    });

    //jButtonSend.addActionListener(new SendMessage());
    panel1.setMaximumSize(new Dimension(850, 600));
    panel1.setMinimumSize(new Dimension(850, 600));
    panel1.setPreferredSize(new Dimension(850, 600));
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
    jToolBar1.add(jButtonBeginAnswer, null);
        jToolBar1.add(jButtonEndAnswer, null);
    jToolBar1.add(jButtonAllowAnswer, null);
        jToolBar1.add(jButtonAnswerRight, null);
        jToolBar1.add(jButtonAnswerWrong, null);
        jToolBar1.add(jButtonExplain, null);
    panel1.add(jLabel3,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 0, 20), 168, -1));
    panel1.add(jLabel4,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(6, 2, 0, 13), 175, 2));
    panel1.add(jLabel1,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 12, 0, 13), 100, 2));
    panel1.add(jLabel2,  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(6, 11, 0, 0), 138, 3));
    panel1.add(jScrollPane4,    new GridBagConstraints(1, 4, 2, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 7, 11, 4), 490, 161));
    panel1.add(jButtonSend,  new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 194, 0, 55), 24, -8));
    panel1.add(jScrollPane3,     new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 2, 11, 0), -23, 51));
    panel1.add(jScrollPane1,  new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 2, 0, 0), -23, 188));
    panel1.add(jScrollPane2,  new GridBagConstraints(1, 2, 2, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 9, 0, 4), 488, 299));
    panel1.add(jToolBar1,  new GridBagConstraints(0, 0, 3, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 0, 121), 2, 6));
    jScrollPane2.getViewport().add(jTextAreaInformation, null);
    jScrollPane1.getViewport().add(jListStudent, null);
    jScrollPane3.getViewport().add(jListSequence, null);
    jScrollPane4.getViewport().add(jTextAreaInput, null);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenu1.add(jMenuItem1);
    this.getContentPane().add(panel1,  BorderLayout.CENTER);
    jMenu2.add(jMenuItem2);
        this.setJMenuBar(jMenuBar1);
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
    class SendMessage implements ActionListener
    {

        /**
         * When a message is entered, send it.
         */
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                jButtonSend_actionPerformed(e);
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
       //    jTextAreaInput.aaddActionListener(new SendMessage());
        //jTextAreaInput.requestFocus();
    }


    void jButtonBeginAnswer_actionPerformed(ActionEvent e)
    {
        userIDs=this.systemControl.answerCompetitionListModelStudent.getUserIDData();
        this.systemControl.communication.beginAnswerCompetition(userIDs);
        jButtonBeginAnswer.setEnabled(false);
        jButtonEndAnswer.setEnabled(true);
        jButtonAllowAnswer.setEnabled(false);
        this.systemControl.answerCompetitionSequenceListModel.setData(new Vector());
    }

    void jButtonAllowAnswer_actionPerformed(ActionEvent e)
    {
        if(jButtonAllowAnswer.getText().equals("允许回答"))
        {
            int row=jListSequence.getSelectedIndex();
            if(row<0) return;
            String userID=(String)((AnswerCompetitionSequenceListModel)jListSequence.getModel()).getUserIDAt(row);
            this.systemControl.communication.allowAnswer(userID,true);
            jButtonAllowAnswer.setText("停止回答");
            jButtonBeginAnswer.setEnabled(false);
        }
        else
        {
            int row=jListSequence.getSelectedIndex();
            if(row<0) return;
            String userID=(String)((AnswerCompetitionSequenceListModel)jListSequence.getModel()).getUserIDAt(row);
            this.systemControl.communication.allowAnswer(userID,false);
            jButtonAllowAnswer.setText("允许回答");
            jButtonBeginAnswer.setEnabled(true);
        }
    }
    void jButtonEndAnswer_actionPerformed(ActionEvent e)
    {
        userIDs=this.systemControl.answerCompetitionListModelStudent.getUserIDData();
        this.systemControl.communication.endAnswerCompetition(userIDs);
        jButtonBeginAnswer.setEnabled(true);
        jButtonEndAnswer.setEnabled(false);
        jButtonAllowAnswer.setEnabled(true);
    }

    void jButtonAnswerRight_actionPerformed(ActionEvent e)
    {
        int row=jListSequence.getSelectedIndex();
        if(row<0) return;
        try
        {
            String message = prefix + "回答正确！";
            jTextAreaInput.setText("");
            byte data[] = message.getBytes("UTF8");
            DatagramPacket dp = new DatagramPacket(data, data.length, ia,
                                                   port);
            socket.send(dp);
            jTextAreaInformation.append(message + "\n");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void jButtonExplain_actionPerformed(ActionEvent e)
    {
        userIDs=this.systemControl.answerCompetitionListModelStudent.getUserIDData();
        if(jButtonExplain.getText().equals("语音讲解"))
        {
            this.systemControl.communication.voiceBroadCast(userIDs,null);
            jButtonExplain.setText("停止语音");
        }
        else
        {
            this.systemControl.communication.stopVoiceBroadCast(userIDs,null);
            jButtonExplain.setText("语音讲解");
        }
    }

    void jButtonAnswerWrong_actionPerformed(ActionEvent e)
    {
        int row=jListSequence.getSelectedIndex();
        if(row<0) return;
        try
        {
            String message = prefix + "回答错误！";
            jTextAreaInput.setText("");
            byte data[] = message.getBytes("UTF8");
            DatagramPacket dp = new DatagramPacket(data, data.length, ia,
                                                   port);
            socket.send(dp);
            jTextAreaInformation.append(message + "\n");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void jButtonSend_actionPerformed(ActionEvent e)
    {
        try
        {
            String message = prefix + jTextAreaInput.getText();
            jTextAreaInput.setText("");
            byte data[] = message.getBytes("UTF8");
            DatagramPacket dp = new DatagramPacket(data, data.length, ia,
                                                   port);
            socket.send(dp);
            jTextAreaInformation.append(message + "\n");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    void this_windowClosing(WindowEvent e)
    {
        userIDs=this.systemControl.answerCompetitionListModelStudent.getUserIDData();
        this.systemControl.communication.closeAnswerCompetition(userIDs);
    }

    void jMenuItem1_actionPerformed(ActionEvent e)
    {
        userIDs=this.systemControl.answerCompetitionListModelStudent.getUserIDData();
        this.systemControl.communication.closeAnswerCompetition(userIDs);
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