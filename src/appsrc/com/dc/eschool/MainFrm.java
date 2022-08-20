package com.dc.eschool;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

import com.dc.eschool.systemControl.SystemControl;
import com.dc.eschool.exam.ExamIF;
import com.dc.eschool.exam.Examination;
import com.dc.eschool.inspection.InspectionIF;
import com.dc.eschool.inspection.Inspection;
import com.dc.eschool.group.GroupComponent;
import com.dc.eschool.group.GroupModel;
import com.dc.eschool.group.Person;
import com.dc.eschool.group.EschoolUser;
import com.dc.eschool.group.PopupMenuBuilder;
import com.dc.eschool.group.PersonSeat;
import com.dc.eschool.listen.*;
import javax.swing.event.*;
import com.dc.eschool.system.*;
import com.dc.eschool.group.*;
import com.dc.eschool.util.JMFAPI;

/**
 * Title:         ������
 * Description:   ����Ӧ�ó����������
 *
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class MainFrm extends JFrame implements PopupMenuBuilder
{
  /**  SystemControlʵ��  */
  private SystemControl systemControl=null;
  /**  ���Խӿ�           */
  private ExamIF        IExam=null;
  /**  ���Ӽ����ӿ�       */
  private InspectionIF  IInspection;
  /**  �����ӿ�          */
  private ListenIF      IListen;
  /**   ��������         */
  private static String[]   weekName={"","������","����һ","���ڶ�","������","������","������","������"};
  /** �����֤���*/
  private boolean verifyPass=true;
  Dimension dim = getToolkit().getScreenSize();
  int w=dim.width;
  int h=dim.height;
  public String SCREENURL = "screen://10,10,"+String.valueOf(w)+","+String.valueOf(h)+",/5";
  JPanel contentPane;
  JMenuBar MenuBar = new JMenuBar();
  JMenu menuSystem = new JMenu();
  JMenuItem menuUserInfo = new JMenuItem();
  JToolBar toolBar = new JToolBar();
  JButton btnSound = new JButton();
  JButton btnNetWork = new JButton();
  JButton btnMovie = new JButton();

  JPanel statusBar = new JPanel();

  JToolBar newToolBar = new JToolBar();

  //GroupModel groupModel = new GroupModelDemo();
  GroupModel groupModel=null;
  GroupComponent groupView=null;

  JScrollPane jScrollPane1 = new JScrollPane();

  BorderLayout borderLayout1 = new BorderLayout();
  JMenuItem menuCourseInfo = new JMenuItem();
  JMenuItem menuExit = new JMenuItem();
  JMenu menuOperate = new JMenu();
  JMenu menuSingleOperate = new JMenu();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenuItem jMenuItem6 = new JMenuItem();
  JMenuItem jMenuItem7 = new JMenuItem();
  JMenuItem jMenuItem8 = new JMenuItem();
  JMenuItem menuCheckOn = new JMenuItem();
  JMenuItem menuLogin = new JMenuItem();
  JMenuItem menuCancelDark = new JMenuItem();
  JMenuItem menuDarkScreen = new JMenuItem();
  JMenuItem menuWarning = new JMenuItem();
  JMenu menuGroupOperate = new JMenu();
  JMenuItem jMenuItem4 = new JMenuItem();
  JMenuItem jMenuItem14 = new JMenuItem();
  JMenuItem menuGroupCheck = new JMenuItem();
  JMenuItem jMenuItem16 = new JMenuItem();
  JMenuItem menuGroupCancelDark = new JMenuItem();
  JMenuItem menuGroupDark = new JMenuItem();
  JMenuItem menuGroupWarn = new JMenuItem();
  JMenuItem menuGroupFinishExam = new JMenuItem();
  JMenu menuAllOperate = new JMenu();
  JMenuItem jMenuItem5 = new JMenuItem();
  JMenuItem jMenuItem21 = new JMenuItem();
  JMenuItem menuClassCinema = new JMenuItem();
  JMenuItem jMenuItem23 = new JMenuItem();
  JMenuItem menuAllCheck = new JMenuItem();
  JMenuItem jMenuItem25 = new JMenuItem();
  JMenuItem menuAllCancelDark = new JMenuItem();
  JMenuItem menuAllDark = new JMenuItem();
  JMenuItem menuAllWarn = new JMenuItem();
  JMenuItem jMenuItem29 = new JMenuItem();
  JMenuItem menuAllStartExam = new JMenuItem();
  JMenuItem menuAllFinishExam = new JMenuItem();
  JMenu menuGroup = new JMenu();
  JMenuItem menuRandomGroup = new JMenuItem();
  JMenuItem menuHandGroup = new JMenuItem();
  JMenu menuExam = new JMenu();
  JMenuItem menuSelectExam = new JMenuItem();
  JMenuItem menuCancelExam = new JMenuItem();
  JMenuItem menuExamGroup = new JMenuItem();
  JMenuItem menuStartExam = new JMenuItem();
  JMenuItem menuFinishExam = new JMenuItem();
  JMenu menuListen = new JMenu();
  JMenuItem menuSelectListen = new JMenuItem();
  JMenuItem menuCancelListen = new JMenuItem();
  JMenuItem jMenuItem41 = new JMenuItem();
  JMenu menuCommunion = new JMenu();
  JMenuItem jMenuItem42 = new JMenuItem();
  JMenuItem jMenuItem43 = new JMenuItem();
  JMenu menuHelp = new JMenu();
  JMenuItem menuAbout = new JMenuItem();
  JPopupMenu popupMenu = new JPopupMenu();
  JMenuItem popMenuTalkWithTeacher = new JMenuItem();
  JMenuItem jMenuItem46 = new JMenuItem();
  JMenuItem jMenuItem47 = new JMenuItem();
  JMenuItem jMenuItem48 = new JMenuItem();
  JMenuItem popMenuCheckOn = new JMenuItem();
  JMenuItem popMenuLogInfo = new JMenuItem();
  JMenuItem popMenuCancelDark = new JMenuItem();
  JMenuItem popMenuDark = new JMenuItem();
  JMenuItem popMenuWarn = new JMenuItem();
  JMenu jMenu10 = new JMenu();
  JMenuItem jMenuItem54 = new JMenuItem();
  JMenuItem jMenuItem55 = new JMenuItem();
  JMenuItem popMenuGroupCheck = new JMenuItem();
  JMenuItem jMenuItem57 = new JMenuItem();
  JMenuItem popMenuGroupCancelDark = new JMenuItem();
  JMenuItem popMenuGroupDark = new JMenuItem();
  JMenuItem popMenuGroupWarn = new JMenuItem();
  JMenuItem popMenuFinishExam = new JMenuItem();
  JLabel jLabel1 = new JLabel();
  JTextField tfCourse = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField tfTeacher = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField tfStudentNum = new JTextField();
  JLabel jLabel4 = new JLabel();
  JLabel lbDateTime = new JLabel();
  JTextField tfComeNum = new JTextField();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPopupMenu teacherPopMenu = new JPopupMenu();
  JMenuItem menuLoginInfo = new JMenuItem();
  JButton btnWizard = new JButton();
  JButton btnPCUser = new JButton();
  JButton btnCheckOn = new JButton();
  JButton btnQuickAnswer = new JButton();
  JButton btnFinishExam = new JButton();
  JButton btnRandomGroup = new JButton();
  JButton btnRecord = new JButton();
  JButton btnCancelDark = new JButton();
  JButton btnDark = new JButton();
  JButton btnWarn = new JButton();
  Component component1;
  Component component2;
  Component component3;
  JButton btnInspect = new JButton();
  JMenuItem menuSingleFinishExam = new JMenuItem();
  JMenuItem popMenuExitClass = new JMenuItem();

  /**Construct the frame*/
  public MainFrm() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**   �Զ��幹����  */
  public MainFrm(SystemControl systemControl)
  {
     this();
     this.systemControl=systemControl;
     LoginIF login=new Login(this.systemControl);
     verifyPass=login.getVerifyResult();
     init();
  }
  /**Component initialization*/
  private void jbInit() throws Exception  {
    /**  ����ͼ��  */
    ImageIcon imgSound = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("sound.gif"));
    ImageIcon imgSoundGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("soundg.gif"));
    ImageIcon imgNetWork = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("network.gif"));
    ImageIcon imgNetWorkGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("networkg.gif"));
    ImageIcon imgMovie = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("mov_movie.gif"));
    ImageIcon imgMovieGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("mov_movieg.gif"));
    ImageIcon imgWizard = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("wizard.gif"));
    ImageIcon imgWizardGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("wizardg.gif"));
    ImageIcon imgPCUser = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("pc_user.gif"));
    ImageIcon imgPCUserGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("pc_userg.gif"));
    ImageIcon imgCheckOn = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("person2.gif"));
    ImageIcon imgCheckOnGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("person2g.gif"));
    ImageIcon imgQuickAnswer = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("d_start25.gif"));
    ImageIcon imgQuickAnswerGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("d_start25g.gif"));
    ImageIcon imgFinishExam = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("docs.gif"));
    ImageIcon imgFinishExamGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("docsg.gif"));
    ImageIcon imgRandomGroup = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("group.gif"));
    ImageIcon imgRandomGroupGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("groupg.gif"));
    ImageIcon imgRecord = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("controls9.gif"));
    ImageIcon imgRecordGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("controls9g.gif"));
    ImageIcon imgCancelDark = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("key.gif"));
    ImageIcon imgCancelDarkGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("keyg.gif"));
    ImageIcon imgDark = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("lock.gif"));
    ImageIcon imgDarkGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("lockg.gif"));
    ImageIcon imgWarn = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("info.gif"));
    ImageIcon imgWarnGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("infog.gif"));
    ImageIcon imgViewUser = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("view_user.gif"));
    ImageIcon imgViewUserGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("view_userg.gif"));
    ImageIcon imgHand = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("hand.gif"));
    ImageIcon imgHandGray = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("handg.gif"));

    JButton btnHand = new JButton();
    btnHand.setToolTipText("���Ӿ���");
    btnHand.setIcon(imgHand);
    btnHand.addActionListener(new java.awt.event.ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            btnHand_actionPerformed(e);
        }
    });


    menuOperate.addMenuListener(new javax.swing.event.MenuListener()
    {
      public void menuSelected(MenuEvent e)
      {
        menuOperate_menuSelected(e);
      }
      public void menuDeselected(MenuEvent e)
      {
      }
      public void menuCanceled(MenuEvent e)
      {
      }
    });
    this.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowOpened(WindowEvent e)
      {
        this_windowOpened(e);
      }
      public void windowClosing(WindowEvent e)
      {
        this_windowClosing(e);
      }
    });
    menuExam.addMenuListener(new javax.swing.event.MenuListener()
    {
      public void menuSelected(MenuEvent e)
      {
        menuExam_menuSelected(e);
      }
      public void menuDeselected(MenuEvent e)
      {
      }
      public void menuCanceled(MenuEvent e)
      {
      }
    });
    menuRandomGroup.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuRandomGroup_actionPerformed(e);
      }
    });
    menuHandGroup.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuHandGroup_actionPerformed(e);
      }
    });
    popMenuTalkWithTeacher.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuTalkWithTeacher_actionPerformed(e);
      }
    });
    btnNetWork.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnNetWork_actionPerformed(e);
      }
    });
    btnWizard.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnWizard_actionPerformed(e);
      }
    });
    btnQuickAnswer.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnQuickAnswer_actionPerformed(e);
      }
    });
    jMenuItem41.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem41_actionPerformed(e);
      }
    });
    jMenuItem46.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem46_actionPerformed(e);
      }
    });
    jMenuItem55.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem55_actionPerformed(e);
      }
    });
    btnSound.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnSound_actionPerformed(e);
      }
    });
    menuListen.addMenuListener(new javax.swing.event.MenuListener()
    {
      public void menuSelected(MenuEvent e)
      {
        menuListen_menuSelected(e);
      }
      public void menuDeselected(MenuEvent e)
      {
      }
      public void menuCanceled(MenuEvent e)
      {
      }
    });
    btnRandomGroup.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnRandomGroup_actionPerformed(e);
      }
    });
    btnRecord.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnRecord_actionPerformed(e);
      }
    });
    jMenuItem3.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem3_actionPerformed(e);
      }
    });
    jMenuItem6.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem6_actionPerformed(e);
      }
    });
    jMenuItem7.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem7_actionPerformed(e);
      }
    });
    jMenuItem42.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem42_actionPerformed(e);
      }
    });
    jMenuItem43.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem43_actionPerformed(e);
      }
    });
    jMenuItem14.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem14_actionPerformed(e);
      }
    });
    jMenuItem4.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem4_actionPerformed(e);
      }
    });
    jMenuItem16.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem16_actionPerformed(e);
      }
    });
    jMenuItem5.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem5_actionPerformed(e);
      }
    });
    jMenuItem54.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem54_actionPerformed(e);
      }
    });
    jMenuItem21.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem21_actionPerformed(e);
      }
    });
    jMenuItem23.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem23_actionPerformed(e);
      }
    });
    jMenuItem47.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem47_actionPerformed(e);
      }
    });
    btnPCUser.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnPCUser_actionPerformed(e);
      }
    });
    jMenuItem8.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem8_actionPerformed(e);
      }
    });
    jMenuItem48.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem48_actionPerformed(e);
      }
    });
    jMenuItem57.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem57_actionPerformed(e);
      }
    });
    jMenuItem25.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem25_actionPerformed(e);
      }
    });
    this.addKeyListener(new java.awt.event.KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        this_keyPressed(e);
      }
    });
    menuClassCinema.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuClassCinema_actionPerformed(e);
      }
    });
    btnMovie.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnMovie_actionPerformed(e);
      }
    });
    popMenuExitClass.setText("�˳�����");
    popMenuExitClass.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuExitClass_actionPerformed(e);
      }
    });
    newToolBar.add(btnHand);

    //setIconImage(Toolkit.getDefaultToolkit().createImage(MainFrm.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
    component1 = Box.createHorizontalStrut(23);
    component2 = Box.createHorizontalStrut(23);
    component3 = Box.createHorizontalStrut(23);
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(730, 550));
    this.setTitle("���ý���");
    //statusBar.setText(" ");
    menuSystem.setText("ϵͳ");
    menuUserInfo.setText("�û���Ϣ");
    menuUserInfo.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        menuUserInfo_actionPerformed(e);
      }
    });
    btnSound.setIcon(imgSound);
    btnSound.setToolTipText("�����㲥");
    btnSound.setDisabledIcon(imgSoundGray);
    btnNetWork.setIcon(imgNetWork);
    btnNetWork.setToolTipText("��������");
    btnNetWork.setDisabledIcon(imgNetWorkGray);
    btnMovie.setIcon(imgMovie);
    btnMovie.setToolTipText("����ӰԺ");
    btnMovie.setDisabledIcon(imgMovieGray);
    menuCourseInfo.setText("�γ���Ϣ");
    menuCourseInfo.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuCourseInfo_actionPerformed(e);
      }
    });
    menuExit.setText("�˳�ϵͳ");
    menuExit.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuExit_actionPerformed(e);
      }
    });
    menuOperate.setText("�߼�����");
    menuSingleOperate.setText("���˲���");
    jMenuItem3.setText("�����Խ�");
    jMenuItem6.setText("��������");
    jMenuItem7.setText("��Ļ��ʾ");
    jMenuItem8.setText("ѧ����ʾ");
    menuCheckOn.setText("��    ��");
    menuCheckOn.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuCheckOn_actionPerformed(e);
      }
    });
    menuLogin.setText("��½��Ϣ");
    menuLogin.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuLogin_actionPerformed(e);
      }
    });
    menuCancelDark.setText("ȡ������");
    menuCancelDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuCancelDark_actionPerformed(e);
      }
    });
    menuDarkScreen.setText("��    ��");
    menuDarkScreen.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuDarkScreen_actionPerformed(e);
      }
    });
    menuWarning.setText("��    ��");
    menuWarning.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuWarning_actionPerformed(e);
      }
    });
    menuGroupOperate.setText("�������");
    jMenuItem4.setText("�����㲥");
    jMenuItem14.setText("��������");
    menuGroupCheck.setText("��    ��");
    menuGroupCheck.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuGroupCheck_actionPerformed(e);
      }
    });
    jMenuItem16.setText("��    ��");
    menuGroupCancelDark.setText("ȡ������");
    menuGroupCancelDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuGroupCancelDark_actionPerformed(e);
      }
    });
    menuGroupDark.setText("��    ��");
    menuGroupDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuGroupDark_actionPerformed(e);
      }
    });
    menuGroupWarn.setText("��    ��");
    menuGroupWarn.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuGroupWarn_actionPerformed(e);
      }
    });
    menuGroupFinishExam.setText("���Խ���");
    menuGroupFinishExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuGroupFinishExam_actionPerformed(e);
      }
    });
    menuAllOperate.setText("ȫ�����");
    jMenuItem5.setText("�����㲥");
    jMenuItem21.setText("��������");
    menuClassCinema.setText("����ӰԺ");
    jMenuItem23.setText("��Ļ��ʾ");
    menuAllCheck.setText("��    ��");
    menuAllCheck.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuAllCheck_actionPerformed(e);
      }
    });
    jMenuItem25.setText("��    ��");
    menuAllCancelDark.setText("ȡ������");
    menuAllCancelDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuAllCancelDark_actionPerformed(e);
      }
    });
    menuAllDark.setText("��    ��");
    menuAllDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuAllDark_actionPerformed(e);
      }
    });
    menuAllWarn.setText("��    ��");
    menuAllWarn.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuAllWarn_actionPerformed(e);
      }
    });
    jMenuItem29.setText("��������");
    menuAllStartExam.setText("��������");
    menuAllStartExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuAllStartExam_actionPerformed(e);
      }
    });
    menuAllFinishExam.setText("���Խ���");
    menuAllFinishExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuAllFinishExam_actionPerformed(e);
      }
    });
    menuGroup.setText("����");
    menuRandomGroup.setText("���ٷ���");
    menuHandGroup.setText("�ֹ�����");
    menuExam.setText("����");
    menuSelectExam.setText("ѡ����");
    menuSelectExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuSelectExam_actionPerformed(e);
      }
    });
    menuCancelExam.setText("ȡ������");
    menuCancelExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuCancelExam_actionPerformed(e);
      }
    });
    menuExamGroup.setText("�������");
    menuExamGroup.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuExamGroup_actionPerformed(e);
      }
    });
    menuStartExam.setText("��ʼ����");
    menuStartExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuStartExam_actionPerformed(e);
      }
    });
    menuFinishExam.setText("���Խ���");
    menuFinishExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuFinishExam_actionPerformed(e);
      }
    });
    menuListen.setText("����");
    menuSelectListen.setActionCommand("ѡ��������ϰ");
    menuSelectListen.setText("ѡ��������ϰ");
    menuSelectListen.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuSelectListen_actionPerformed(e);
      }
    });
    menuCancelListen.setText("ȡ��������ϰ");
    menuCancelListen.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuCancelListen_actionPerformed(e);
      }
    });
    jMenuItem41.setText("��������");
    menuCommunion.setText("���ý���");
    jMenuItem42.setText("���Ӿ���");
    jMenuItem43.setText("����¼��");
    menuHelp.setText("����");
    menuAbout.setActionCommand("�����������");
    menuAbout.setText("�����������");
    menuAbout.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuAbout_actionPerformed(e);
      }
    });
    popMenuTalkWithTeacher.setText("�����Խ�");
    jMenuItem46.setText("��������");
    jMenuItem47.setText("��Ļ��ʾ");
    jMenuItem48.setText("ѧ����ʾ");
    popMenuCheckOn.setText("��   ��");
    popMenuCheckOn.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuCheckOn_actionPerformed(e);
      }
    });
    popMenuLogInfo.setText("��½��Ϣ");
    popMenuLogInfo.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuLogInfo_actionPerformed(e);
      }
    });
    popMenuCancelDark.setText("ȡ������");
    popMenuCancelDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuCancelDark_actionPerformed(e);
      }
    });
    popMenuDark.setText("����");
    popMenuDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuDark_actionPerformed(e);
      }
    });
    popMenuWarn.setText("����");
    popMenuWarn.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuWarn_actionPerformed(e);
      }
    });
    jMenu10.setText("�������");
    jMenuItem54.setText("�����㲥");
    jMenuItem55.setText("��������");
    popMenuGroupCheck.setText("��    ��");
    popMenuGroupCheck.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuGroupCheck_actionPerformed(e);
      }
    });
    jMenuItem57.setText("��    ��");
    popMenuGroupCancelDark.setText("ȡ������");
    popMenuGroupCancelDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuGroupCancelDark_actionPerformed(e);
      }
    });
    popMenuGroupDark.setText("��    ��");
    popMenuGroupDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuGroupDark_actionPerformed(e);
      }
    });
    popMenuGroupWarn.setText("��    ��");
    popMenuGroupWarn.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuGroupWarn_actionPerformed(e);
      }
    });
    popMenuFinishExam.setText("��������");
    popMenuFinishExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        popMenuFinishExam_actionPerformed(e);
      }
    });
    statusBar.setLayout(gridBagLayout1);
    jLabel1.setText("�γ�");
    statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
    jLabel2.setText("��ʦ");
    jLabel3.setText("ѧ����");
    jLabel4.setText("��¼��");
    lbDateTime.setText("jLabel5");
    tfCourse.setEditable(false);
    tfTeacher.setEditable(false);
    tfStudentNum.setEditable(false);
    tfComeNum.setEditable(false);
    menuLoginInfo.setText("��¼��Ϣ");
    menuLoginInfo.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuLoginInfo_actionPerformed(e);
      }
    });
    btnWizard.setToolTipText("��Ļ��ʾ");
    btnWizard.setDisabledIcon(imgWizardGray);
    btnWizard.setIcon(imgWizard);
    btnPCUser.setToolTipText("ѧ����ʾ");
    btnPCUser.setDisabledIcon(imgPCUserGray);
    btnPCUser.setIcon(imgPCUser);
    toolBar.setToolTipText("����Сͼ����в�����");
    btnCheckOn.setToolTipText("����");
    btnCheckOn.setDisabledIcon(imgCheckOnGray);
    btnCheckOn.setIcon(imgCheckOn);
    btnCheckOn.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnCheckOn_actionPerformed(e);
      }
    });
    btnQuickAnswer.setToolTipText("����");
    btnQuickAnswer.setDisabledIcon(imgQuickAnswerGray);
    btnQuickAnswer.setIcon(imgQuickAnswer);
    btnFinishExam.setToolTipText("��������");
    btnFinishExam.setDisabledIcon(imgFinishExamGray);
    btnFinishExam.setIcon(imgFinishExam);
    btnFinishExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnFinishExam_actionPerformed(e);
      }
    });
    btnRandomGroup.setToolTipText("���ٷ���");
    btnRandomGroup.setDisabledIcon(imgRandomGroupGray);
    btnRandomGroup.setIcon(imgRandomGroup);
    btnRecord.setToolTipText("����¼��");
    btnRecord.setDisabledIcon(imgRecordGray);
    btnRecord.setIcon(imgRecord);
    btnCancelDark.setToolTipText("ȡ������");
    btnCancelDark.setDisabledIcon(imgCancelDarkGray);
    btnCancelDark.setIcon(imgCancelDark);
    btnCancelDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnCancelDark_actionPerformed(e);
      }
    });
    btnDark.setToolTipText("����");
    btnDark.setDisabledIcon(imgDarkGray);
    btnDark.setIcon(imgDark);
    btnDark.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnDark_actionPerformed(e);
      }
    });
    btnWarn.setToolTipText("����");
    btnWarn.setDisabledIcon(imgWarnGray);
    btnWarn.setIcon(imgWarn);
    btnWarn.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnWarn_actionPerformed(e);
      }
    });
    btnInspect.setToolTipText("����ѧ����Ļ");
    btnInspect.setDisabledIcon(imgViewUserGray);
    btnInspect.setIcon(imgViewUser);
    btnInspect.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        btnInspect_actionPerformed(e);
      }
    });
    menuSingleFinishExam.setText("��������");
    menuSingleFinishExam.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        menuSingleFinishExam_actionPerformed(e);
      }
    });
    toolBar.add(btnSound);
    toolBar.add(btnNetWork);
    toolBar.add(btnMovie);
    toolBar.add(btnWizard, null);
    toolBar.add(btnPCUser, null);
    toolBar.add(btnCheckOn, null);
    toolBar.add(btnQuickAnswer, null);
    toolBar.add(component1, null);
    menuSystem.add(menuUserInfo);
    menuSystem.add(menuCourseInfo);
    menuSystem.add(menuExit);
    MenuBar.add(menuSystem);
    MenuBar.add(menuOperate);
    MenuBar.add(menuGroup);
    MenuBar.add(menuExam);
    MenuBar.add(menuListen);
    MenuBar.add(menuCommunion);
    MenuBar.add(menuHelp);
    this.setJMenuBar(MenuBar);
    contentPane.add(toolBar, BorderLayout.NORTH);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    statusBar.add(jLabel1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 7, 0, 0), 0, 0));
    statusBar.add(tfCourse,  new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 6, 0, 0), 91, 0));
    statusBar.add(jLabel2,  new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 8, 0, 0), 0, 0));
    statusBar.add(tfTeacher,  new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 8, 0, 0), 91, 0));
    contentPane.add(jScrollPane1, BorderLayout.CENTER);

    menuOperate.add(menuSingleOperate);
    menuOperate.addSeparator();
    menuOperate.add(menuGroupOperate);
    menuOperate.addSeparator();
    menuOperate.add(menuAllOperate);
    menuSingleOperate.add(jMenuItem3);
    menuSingleOperate.add(jMenuItem6);
    menuSingleOperate.add(jMenuItem7);
    menuSingleOperate.add(jMenuItem8);
    menuSingleOperate.add(menuCheckOn);
    menuSingleOperate.add(menuLogin);
    menuSingleOperate.addSeparator();
    menuSingleOperate.add(menuCancelDark);
    menuSingleOperate.add(menuDarkScreen);
    menuSingleOperate.add(menuWarning);
    menuSingleOperate.addSeparator();
    menuSingleOperate.add(menuSingleFinishExam);
    menuGroupOperate.add(jMenuItem4);
    menuGroupOperate.add(jMenuItem14);
    menuGroupOperate.add(menuGroupCheck);
    menuGroupOperate.add(jMenuItem16);
    menuGroupOperate.addSeparator();
    menuGroupOperate.add(menuGroupCancelDark);
    menuGroupOperate.add(menuGroupDark);
    menuGroupOperate.add(menuGroupWarn);
    menuGroupOperate.addSeparator();
    menuGroupOperate.add(menuGroupFinishExam);
    menuAllOperate.add(jMenuItem5);
    menuAllOperate.add(jMenuItem21);
    menuAllOperate.add(menuClassCinema);
    menuAllOperate.add(jMenuItem23);
    menuAllOperate.add(menuAllCheck);
    menuAllOperate.add(jMenuItem25);
    menuAllOperate.addSeparator();
    menuAllOperate.add(menuAllCancelDark);
    menuAllOperate.add(menuAllDark);
    menuAllOperate.add(menuAllWarn);
    menuAllOperate.addSeparator();
    menuAllOperate.add(jMenuItem29);
    menuAllOperate.add(menuAllStartExam);
    menuAllOperate.add(menuAllFinishExam);
    menuGroup.add(menuRandomGroup);
    menuGroup.add(menuHandGroup);
    menuExam.add(menuSelectExam);
    menuExam.add(menuCancelExam);
    menuExam.add(menuExamGroup);
    menuExam.add(menuStartExam);
    menuExam.add(menuFinishExam);
    menuListen.add(menuSelectListen);
    menuListen.add(menuCancelListen);
    menuListen.add(jMenuItem41);
    menuCommunion.add(jMenuItem42);
    menuCommunion.add(jMenuItem43);
    menuHelp.add(menuAbout);
    popupMenu.add(popMenuTalkWithTeacher);
    popupMenu.add(jMenuItem46);
    popupMenu.add(jMenuItem47);
    popupMenu.add(jMenuItem48);
    popupMenu.add(popMenuCheckOn);
    popupMenu.add(popMenuLogInfo);
    popupMenu.addSeparator();
    popupMenu.add(popMenuCancelDark);
    popupMenu.add(popMenuDark);
    popupMenu.add(popMenuWarn);
    popupMenu.add(popMenuExitClass);
    popupMenu.addSeparator();
    popupMenu.add(jMenu10);
    jMenu10.add(jMenuItem54);
    jMenu10.add(jMenuItem55);
    jMenu10.add(popMenuGroupCheck);
    jMenu10.add(jMenuItem57);
    jMenu10.addSeparator();
    jMenu10.add(popMenuGroupCancelDark);
    jMenu10.add(popMenuGroupDark);
    jMenu10.add(popMenuGroupWarn);
    jMenu10.addSeparator();
    jMenu10.add(popMenuFinishExam);



    statusBar.add(lbDateTime,  new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 7, 0, 9), 166, 0));
    statusBar.add(jLabel3,  new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 7, 0, 0), 0, 0));
    statusBar.add(tfStudentNum,  new GridBagConstraints(5, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 0, 0), 44, 0));
    statusBar.add(jLabel4,  new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 9, 0, 0), 0, 0));
    statusBar.add(tfComeNum,    new GridBagConstraints(7, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(1, 0, 1, 0), 44, 0));
    teacherPopMenu.add(menuLoginInfo);
    toolBar.add(btnFinishExam, null);
    toolBar.add(component2, null);
    toolBar.add(btnRandomGroup, null);
    toolBar.add(btnRecord, null);
    toolBar.add(component3, null);
    toolBar.add(btnInspect, null);
    toolBar.add(btnCancelDark, null);
    toolBar.add(btnDark, null);
    toolBar.add(btnWarn, null);
  }

  /**Help | About action performed*/
  /**Overridden so we can exit when window is closed*/
  protected void processWindowEvent(WindowEvent e)
  {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING)
    {
      System.exit(0);
    }
  }

  /**   ʵ�ֽӿڵķ���
   *    ���ص�ǰ��PopupMenu, �˷������������Ҽ������˵���ʾ����̬���쵯���˵�
   */
  public JPopupMenu getPersonPopupMenu( GroupComponent gc,
                                        PersonSeat ps,
                                        int x,
                                        int y,
                                        int rowIndex,
                                        int columnIndex )
  {
      String userType=null;
      userType=systemControl.getUserType();
      JPopupMenu pm = null;
      if ( ps == null ) return null;

      Person p = ps.getPerson();

      if ( p == null ) return null;

      int type = p.getType();

      if (userType.equals("0"))
      {
          if ( type == Person.TEACHER )
              pm = this.teacherPopMenu;
          else if ( type == Person.STUDENT )
          {   String logUserID=p.getLoginName();
              if (systemControl.userGroup.getUserStatusByUserID(logUserID).equals("F"))
                  return this.teacherPopMenu;
              pm = this.popupMenu;
          }
          else if ( type == Person.AUDITOR )
          {   String logUserID=p.getLoginName();
              if (systemControl.userGroup.getUserStatusByUserID(logUserID).equals("F"))
                  return this.teacherPopMenu;
              pm = this.popupMenu;
          }
      }
      else
      {
          if ( type == Person.TEACHER )
              pm = this.teacherPopMenu;
          else if ( type == Person.STUDENT )
              pm = this.teacherPopMenu;
          else if ( type == Person.AUDITOR )
              pm = this.teacherPopMenu;
      }

      return pm;
  }

  /**   ϵͳ��ʼ��   */
  public void init()
  {
      tfCourse.setText(systemControl.getCourseName());
      tfTeacher.setText(systemControl.getTeacherName());
      tfStudentNum.setText(""+systemControl.getAllStudentCount());
      TimeThread timeThread=new TimeThread();
      timeThread.start();

      groupModel = systemControl.userGroup;
      if (groupModel==null)
      {
          return;
      }
      groupView = new GroupComponent(groupModel);
      /**  ����GroupView �ĵ����˵�����  */
      groupView.setPopupMenuBuilder(this);
      jScrollPane1.getViewport().add(groupView);
      groupView.addMouseListener(new java.awt.event.MouseAdapter()
      {
          public void mousePressed(MouseEvent e)
          {
              groupView_mousePressed(e);
          }
      });
      groupView.addKeyListener(new java.awt.event.KeyAdapter()
      {
          public void keyReleased(KeyEvent e)
          {
              groupView_keyReleased(e);
          }
      });
      String strTemp;
      String userType=null;
      userType=systemControl.getUserType();
      if (userType.equals("0"))
      {
          menuCommunion.setVisible(false);
          btnInspect.setEnabled(false);
          btnPCUser.setEnabled(false);
          btnFinishExam.setEnabled(false);
      }
      else if (userType.equals("1"))
      {
          menuOperate.setVisible(false);
          menuGroup.setVisible(false);
          menuExam.setVisible(false);
          menuListen.setVisible(false);
          contentPane.remove(toolBar);
          newToolBar.add(btnRecord);
          contentPane.add(newToolBar, BorderLayout.NORTH);
          groupView.setEnableMovePerson(false);
      }
      else if (userType.equals("2"))
      {
          menuOperate.setVisible(false);
          menuGroup.setVisible(false);
          menuExam.setVisible(false);
          menuListen.setVisible(false);
          menuCommunion.setVisible(false);
          toolBar.setVisible(false);
          groupView.setEnableMovePerson(false);
      }
      groupView.addGroupComponentListener(systemControl.userGroup);
  }
  /**  ����ϵͳ  */
  void menuAbout_actionPerformed(ActionEvent e)
  {
      MainFrm_AboutBox dlg = new MainFrm_AboutBox(this);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setModal(true);
      dlg.show();
  }

  /**  �û���Ϣ  */
  public void menuUserInfo_actionPerformed(ActionEvent e)
  {
      /**   �û�ID           */
      String        userID;
      UserInfoDialog dlg;
      userID=systemControl.getUserID();
      dlg = new UserInfoDialog(this,systemControl,true,userID);
      //dlg.setSize(340,390);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.show();
  }
  /**  �γ���Ϣ  */
  void menuCourseInfo_actionPerformed(ActionEvent e)
  {
      CourseDialog dlg;
      dlg = new CourseDialog(this,systemControl);
      dlg.setSize(380,390);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.show();
  }
  /**  �˳�ϵͳ  */
  void menuExit_actionPerformed(ActionEvent e)
  {
      exitClass();
  }
  /**    ������ID   */
  private Vector getGroupUserID()
  {
      Vector groupUserID=null;
      String userID=null;
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      if (eschoolUser==null)
      {
          systemControl.writeLog("�޷�ȡ�õ�ǰ�û���ID!");
          showInformationWindow("���ý���","�޷�ȡ�õ�ǰС����Ϣ,��ȷ���Ƿ�ѡ��ѧ����");
          return null;
      }
      userID=eschoolUser.getLoginName();
      groupUserID=systemControl.userGroup.getUserIDsByUserID(userID);
      return groupUserID;
  }
  /**    ��õ�ǰ�û���ID   */
  private String getCurrentUserID()
  {
      String       userID;
      Person  person=null;
      person=groupView.getSelectedPerson();
      if (person==null)
      {
          systemControl.writeLog("�޷�ȡ�õ�ǰ�û���ID!");
          showInformationWindow("���ý���","�޷�ȡ�õ�ǰ�û���Ϣ,��ȷ���Ƿ�ѡ��ѧ����");
          return null;
      }
      userID=person.getLoginName();
      return userID;
  }
  /**
   * ��ʾ��Ϣ����
   *
   * �÷�������Ϊ�ô����ṩ��ʾ��
   */
  public void showInformationWindow(String strTitle,String strContent)
  {
      JOptionPane.showMessageDialog(this,
                                    strContent,
                                    strTitle,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null);
  }
  /**
   * �߳���
   *
   * ����Ϊһ�ڲ��࣬��������ȡϵͳʱ�䣬����ˢ�½�����ʾ��
   *
   */
  class TimeThread extends Thread
  {
      GregorianCalendar gc=null;
      public TimeThread()
      {
      }
      public void run()
      {
          String strTemp;
          String strDateTime;
          while (true)
          {
              gc=new GregorianCalendar();
              if (systemControl.getUserType()==null)
              {
                  tfComeNum.setText("δ֪");
              }
              else if (systemControl.getUserType().equals("0"))
              {
                  tfComeNum.setText(""+systemControl.getStudentCountOnClass());
              }
              else
              {
                  tfComeNum.setText(systemControl.getLoginStudentCount());
              }
              strDateTime=weekName[gc.get(Calendar.DAY_OF_WEEK)];
              strTemp=""+gc.get(Calendar.HOUR);
              if (strTemp.length()==1)
                  strTemp="0"+strTemp;
              strDateTime=strDateTime+"  "+strTemp;
              strTemp=""+gc.get(Calendar.MINUTE);
              if (strTemp.length()==1)
                  strTemp="0"+strTemp;
              strDateTime=strDateTime+":"+strTemp;
              strTemp=""+gc.get(Calendar.SECOND);
              if (strTemp.length()==1)
                  strTemp="0"+strTemp;
              strDateTime=strDateTime+":"+strTemp;
              lbDateTime.setText(strDateTime);
              strTemp=null;
              try
              {
                  sleep(1000);
              }
              catch (Exception e)
              {
              }
          }
      }
  }
  /**  ѡ������Ϣ  */
  void menuSelectExam_actionPerformed(ActionEvent e)
  {
      SelectExamFrm dlg;
      dlg = new SelectExamFrm(this,systemControl);
      dlg.setSize(500,440);
      Dimension dlgSize = dlg.getSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.show();
  }
  /**  �������  */
  void menuExamGroup_actionPerformed(ActionEvent e)
  {
      ExamGroupFrm dlg;
      try
      {
          dlg = new ExamGroupFrm(this,systemControl);
          dlg.setSize(480,490);
          Dimension dlgSize = dlg.getSize();
          Dimension frmSize = getSize();
          Point loc = getLocation();
          dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
                          (frmSize.height - dlgSize.height) / 2 + loc.y);
          dlg.show();
      }
      catch (Exception evt)
      {
          dlg=null;
      }
  }
  /**   ȡ������  */
  void menuCancelExam_actionPerformed(ActionEvent e)
  {
      if (JOptionPane.showConfirmDialog(this,
                                        "ȡ�����Ի�ɾ������ѡ��Ŀ���,���ȡ��������",
                                        "ȡ������",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null)==JOptionPane.CANCEL_OPTION)
      return;
      /**   �γ�ID   */
      String    courseID;

      if (systemControl.IExam==null)
      {
          IExam=new Examination(systemControl);
          systemControl.IExam=IExam;
      }
      else
      {
          IExam=systemControl.IExam;
      }
      courseID=systemControl.getCourseID();
      IExam.cancelExam(courseID);
  }
  /**  ��ʼ����   */
  void menuStartExam_actionPerformed(ActionEvent e)
  {
      /**   ���ѧ��ID��ʸ��  */
      Vector      vectStudentID=new Vector();
      /**   ѧ��            */
      Hashtable   htStudentIDInterface=null;
      /**   ѧ��ID��ö��     */
      Enumeration enumStudentID=null;
      /**   ��ʱ�ַ���       */
      String      strTemp;
      /**   ȡ�ÿ�ʼ���Ե����� */
      htStudentIDInterface=systemControl.getHtStudentIDInterface();
      if (htStudentIDInterface==null)
      {
          return;
      }
      enumStudentID=htStudentIDInterface.keys();
      for (;enumStudentID.hasMoreElements();)
      {
          strTemp=(String)enumStudentID.nextElement();
          vectStudentID.addElement(strTemp);
      }

      if (systemControl.IExam==null)
      {
          IExam=new Examination(systemControl);
          systemControl.IExam=IExam;
      }
      else
      {
          IExam=systemControl.IExam;
      }

      try
      {
          IExam.notifyStartExam(vectStudentID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("�ڿ�ʼ����ʱ,Զ�̵���ʧ�ܣ�");
      }
      systemControl.setStartExamOrNot(true);
      btnFinishExam.setEnabled(true);
  }
  /**   ��������  */
  void menuFinishExam_actionPerformed(ActionEvent e)
  {
      /**   ���ѧ��ID��ʸ��  */
      Vector      vectStudentID=new Vector();
      vectStudentID=systemControl.getStudentIDOnClass();

      if (systemControl.IExam==null)
      {
          IExam=new Examination(systemControl);
          systemControl.IExam=IExam;
      }
      else
      {
          IExam=systemControl.IExam;
      }
      String courseID=systemControl.getCourseID();
      String userID=systemControl.getUserID();
      try
      {
          IExam.forceAllEnd(vectStudentID);
          IExam.finishExam(courseID,userID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("�ڽ�������ʱ,Զ�̵���ʧ�ܣ�");
      }
      systemControl.setStartExamOrNot(false);
      btnFinishExam.setEnabled(false);
      this.showInformationWindow("����","���Խ�����ȫ��ѧ���ѽ���");
  }
  /**   ����   */
  void menuCheckOn_actionPerformed(ActionEvent e)
  {
      String       userID;
      userID=getCurrentUserID();

      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.hasCome(userID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("�ڵ���ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**  ��ʦ��Ϣ  */
  void menuLoginInfo_actionPerformed(ActionEvent e)
  {
      String       userID;
      userID=getCurrentUserID();

      UserInfoDialog dlg;
      dlg = new UserInfoDialog(this,systemControl,false,userID);
      dlg.setSize(340,390);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.show();
  }
  /**   ѧ���ĵ�¼��Ϣ  */
  void popMenuLogInfo_actionPerformed(ActionEvent e)
  {
      menuLoginInfo_actionPerformed(e);
  }
  /**  ȫ���������     */
  void menuAllFinishExam_actionPerformed(ActionEvent e)
  {
      menuFinishExam_actionPerformed(e);
  }
  /**  ѧ���ĵ�¼��Ϣ   */
  void menuLogin_actionPerformed(ActionEvent e)
  {
      menuLoginInfo_actionPerformed(e);
  }
  /**  ȫ������Ŀ�ʼ���� */
  void menuAllStartExam_actionPerformed(ActionEvent e)
  {
      menuStartExam_actionPerformed(e);
  }
  /**  ȡ������         */
  void popMenuCancelDark_actionPerformed(ActionEvent e)
  {
      String       userID;
      userID=getCurrentUserID();

      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.cancelDarkScreen(userID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("��ȡ��ѧ������ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**  ����  */
  void popMenuDark_actionPerformed(ActionEvent e)
  {
      String       userID;
      userID=getCurrentUserID();

      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.setDarkScreen(userID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("��ȡ��ѧ������ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**   ���˵���ȡ������(����)   */
  void menuCancelDark_actionPerformed(ActionEvent e)
  {
      popMenuCancelDark_actionPerformed(e);
  }
  /**  ���˵�����ĳ�˺���       */
  void menuDarkScreen_actionPerformed(ActionEvent e)
  {
      popMenuDark_actionPerformed(e);
  }
  /**  ����ĳһѧ��           */
  void popMenuWarn_actionPerformed(ActionEvent e)
  {
      String       userID;
      userID=getCurrentUserID();
      if (userID==null)
      {
          systemControl.writeLog("����ʦ����ĳһѧ��ʱ,û��ȡ����ѧ����ID,����ʧ�ܣ�");
          showInformationWindow("���ý���","��ȷ���Ƿ�ѡ����ѧ��,����ʧ�ܣ�");
          return;
      }
      WarningDialog dlg=new WarningDialog(this,userID,systemControl);
      dlg.setSize(450,230);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.show();
  }
  /**   ���˵���ȫ�����      */
  void menuAllCheck_actionPerformed(ActionEvent e)
  {
      Vector   vectTemp=null;
      vectTemp=systemControl.getStudentIDOnClass();
      if (vectTemp.size()==0)
      {
          systemControl.writeLog("û��ѧ����Ϣ,�޷����е���������");
          showInformationWindow("���ý���","û��ѧ����Ϣ,�޷����е���������");
          return;
      }

      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.hasCome(vectTemp);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("�ڵ���ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**   �������   */
  void popMenuGroupCheck_actionPerformed(ActionEvent e)
  {
      Vector   vectTemp=null;
      vectTemp=getGroupUserID();

      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.hasCome(vectTemp);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("�ڵ���ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**   ���˵��������   */
  void menuGroupCheck_actionPerformed(ActionEvent e)
  {
      popMenuGroupCheck_actionPerformed(e);
  }
  /**  ���˵��з������  */
  void menuGroupDark_actionPerformed(ActionEvent e)
  {
      Vector      studentID;
      studentID=getGroupUserID();
      if (studentID==null)
      {
          systemControl.writeLog("�ڷ������ʱ,û��ȡ��ѧ������Ϣ��");
          return;
      }

      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.setGroupDarkScreen(studentID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("��ȡ��ѧ������ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**  ̸���˵��ķ������  */
  void popMenuGroupDark_actionPerformed(ActionEvent e)
  {
      menuGroupDark_actionPerformed(e);
  }
  /**  ���˵���ȡ���������   */
  void menuGroupCancelDark_actionPerformed(ActionEvent e)
  {
      Vector      studentID;
      studentID=getGroupUserID();
      if (studentID==null)
      {
          systemControl.writeLog("�ڷ������ʱ,û��ȡ��ѧ������Ϣ��");
          return;
      }

      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.cancelGroupDarkScreen(studentID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("��ȡ��ѧ������ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**  �����˵��еķ���ȡ������  */
  void popMenuGroupCancelDark_actionPerformed(ActionEvent e)
  {
      menuGroupCancelDark_actionPerformed(e);
  }
  /**  ���˵��з��龯��        */
  void menuGroupWarn_actionPerformed(ActionEvent e)
  {
      Vector      studentID;
      studentID=getGroupUserID();
      if (studentID==null)
      {
          systemControl.writeLog("����ʦ����ĳ��ѧ��ʱ,û��ȡ��ѧ����ID,����ʧ�ܣ�");
          showInformationWindow("���ý���","��ȷ���Ƿ�ѡ����ѧ��,����ʧ�ܣ�");
          return;
      }
      WarningDialog dlg=new WarningDialog(this,systemControl,studentID);
      dlg.setSize(450,230);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.show();
  }
  /**   ����ʽ�˵��з�������  */
  void popMenuGroupWarn_actionPerformed(ActionEvent e)
  {
      menuGroupWarn_actionPerformed(e);
  }
  /**   ���˵��еķ����������  */
  void menuGroupFinishExam_actionPerformed(ActionEvent e)
  {
      Vector      studentID;
      studentID=getGroupUserID();

      if (systemControl.IExam==null)
      {
          IExam=new Examination(systemControl);
          systemControl.IExam=IExam;
      }
      else
      {
          IExam=systemControl.IExam;
      }

      try
      {
          IExam.forceAllEnd(studentID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("�ڽ�������ʱ,Զ�̵���ʧ�ܣ�");
      }
      this.showInformationWindow("����","���Խ�������С��ѧ���ѽ���");
  }
  /**   �����˵��еķ����������   */
  void popMenuFinishExam_actionPerformed(ActionEvent e)
  {
      menuGroupFinishExam_actionPerformed(e);
  }
  /**  ���˵��ľ���ĳһѧ��  */
  void menuWarning_actionPerformed(ActionEvent e)
  {
      popMenuWarn_actionPerformed(e);
  }
  /**  ���˵���ȡ������ѧ���ĺ���  */
  void menuAllCancelDark_actionPerformed(ActionEvent e)
  {
      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.cancelAllDarkScreen();
      }
      catch (Exception evt)
      {
          systemControl.writeLog("��ȡ������ѧ������ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**  ���˵���������ѧ������   */
  void menuAllDark_actionPerformed(ActionEvent e)
  {
      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.setAllDarkScreen();
      }
      catch (Exception evt)
      {
          systemControl.writeLog("��������ѧ������ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**   ���˵��и�ȫ��ѧ��������  */
  void menuAllWarn_actionPerformed(ActionEvent e)
  {
      Vector      studentID;
      studentID=systemControl.getStudentIDOnClass();
      if (studentID.size()==0)
      {
          systemControl.writeLog("����ʦ����ȫ��ѧ��ʱ,û��ȡ��ѧ����ID,����ʧ�ܣ�");
          showInformationWindow("���ý���","����ʧ�ܣ�");
          return;
      }
      WarningDialog dlg=new WarningDialog(this,systemControl,studentID);
      dlg.setSize(450,230);
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
                      (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.show();
  }
  /**  ��ݰ�ť�ĵ���    */
  void btnCheckOn_actionPerformed(ActionEvent e)
  {
      menuAllCheck_actionPerformed(e);
  }
  /**  ��ݰ�ť�Ľ�������  */
  void btnFinishExam_actionPerformed(ActionEvent e)
  {
      menuFinishExam_actionPerformed(e);
  }
  /**   ��ݰ�ť��ȡ������   */
  void btnCancelDark_actionPerformed(ActionEvent e)
  {
      menuAllCancelDark_actionPerformed(e);
  }
  /**  ��ݰ�ť��������ѧ������  */
  void btnDark_actionPerformed(ActionEvent e)
  {
      menuAllDark_actionPerformed(e);
  }
  /**  ��ݰ�ť�ĸ�����ѧ��������  */
  void btnWarn_actionPerformed(ActionEvent e)
  {
      menuAllWarn_actionPerformed(e);
  }
  /**  ����ѧ���Ŀ�ݰ�ť        */
  void btnInspect_actionPerformed(ActionEvent e)
  {
      String  userID;
      userID=getCurrentUserID();

      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.reciveScreen(userID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("�ڼ���ѧ����Ļʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**   ǿ�ƽ���ĳ�������Ŀ���  */
  void menuSingleFinishExam_actionPerformed(ActionEvent e)
  {
      String  userID;
      userID=getCurrentUserID();

      if (systemControl.IExam==null)
      {
          IExam=new Examination(systemControl);
          systemControl.IExam=IExam;
      }
      else
      {
          IExam=systemControl.IExam;
      }

      try
      {
          IExam.forceOneEnd(userID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("�ڽ���ĳѧ���Ŀ���ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**  ���˵��ϵ�ѡ������  */
  void menuSelectListen_actionPerformed(ActionEvent e)
  {
      SelectListenFrm dlg;
      dlg = new SelectListenFrm(this,systemControl);
      dlg.setSize(490,510);
      Dimension dlgSize = dlg.getSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.show();
  }
  /**  ���˵���ȡ������   */
  void menuCancelListen_actionPerformed(ActionEvent e)
  {
      if (systemControl.IListen==null)
      {
          IListen=new Listen(systemControl);
          systemControl.IListen=IListen;
      }
      else
      {
          IListen=systemControl.IListen;
      }
      String courseID=systemControl.getCourseID();
      IListen.cancelListenExercise(courseID);
  }

  /**   ����groupView������¼� �����ڿ�ݰ�ť  */
  void groupView_mousePressed(MouseEvent e)
  {
      Person  person=null;
      String  userState;
      person=groupView.getSelectedPerson();
      if (person==null)
      {
          btnInspect.setEnabled(false);
          btnPCUser.setEnabled(false);
      }
      else
      {
          String userID=person.getLoginName();
          int userType=person.getType();
          if (userType==0)
              userState="F";
          else
              userState=systemControl.userGroup.getUserStatusByUserID(userID);
          if (userState.equals("T"))
          {
              btnInspect.setEnabled(true);
              btnPCUser.setEnabled(true);
          }
          else
          {
              btnInspect.setEnabled(false);
              btnPCUser.setEnabled(false);
          }
      }
  }
  /**   ����groupView�ļ����¼� �����ڿ�ݰ�ť */
  void groupView_keyReleased(KeyEvent e)
  {
      if (groupView==null)
      {
          return;
      }
      Person  person=null;
      String  userState;
      person=groupView.getSelectedPerson();
      if (person==null)
      {
          btnInspect.setEnabled(false);
          btnPCUser.setEnabled(false);
      }
      else
      {
          String userID=person.getLoginName();
          int userType=person.getType();
          if (userType==0)
              userState="F";
          else
              userState=systemControl.userGroup.getUserStatusByUserID(userID);
          if (userState.equals("T"))
          {
              btnInspect.setEnabled(true);
              btnPCUser.setEnabled(true);
          }
          else
          {
              btnInspect.setEnabled(false);
              btnPCUser.setEnabled(false);
          }
      }
  }
  /**  �������˵�   */
  void menuOperate_menuSelected(MenuEvent e)
  {
      if (groupView==null)
      {
          return;
      }
      Person  person=null;
      person=groupView.getSelectedPerson();
      if (person==null)
      {
          menuSingleOperate.setEnabled(false);
          menuGroupOperate.setEnabled(false);
      }
      else
      {
          String studentState;
          if (person.getType()==0)
          {
              studentState="F";
          }
          else
          {
              String loginUserID=person.getLoginName();
              studentState=systemControl.userGroup.getUserStatusByUserID(loginUserID);
          }
          if (studentState.equals("F"))
          {
              menuSingleOperate.setEnabled(false);
              menuGroupOperate.setEnabled(false);
          }
          else
          {
              menuSingleOperate.setEnabled(true);
              menuGroupOperate.setEnabled(true);
          }
      }
      if (systemControl.getStudentCountOnClass()==0)
      {
          menuAllOperate.setEnabled(false);
      }
      else
      {
          menuAllOperate.setEnabled(true);
      }

      if (systemControl.getStartExamOrNot()==true)
      {
          menuAllStartExam.setEnabled(false);
          menuAllFinishExam.setEnabled(true);
          menuGroupFinishExam.setEnabled(true);
          menuSingleFinishExam.setEnabled(true);
      }
      else
      {
          menuAllStartExam.setEnabled(true);
          menuAllFinishExam.setEnabled(false);
          menuGroupFinishExam.setEnabled(false);
          menuSingleFinishExam.setEnabled(false);
      }
      String courseID=systemControl.getCourseID();
      if (systemControl.IExam==null)
      {
          IExam=new Examination(systemControl);
          systemControl.IExam=IExam;
      }
      else
      {
          IExam=systemControl.IExam;
      }
      if (IExam.examGroupOrNot(courseID)==true)
      {
          menuAllStartExam.setEnabled(true);
      }
      else
      {
          menuAllStartExam.setEnabled(false);
      }
  }


  /**  ���ڿ������˵�*/
  void menuExam_menuSelected(MenuEvent e)
  {
      if (systemControl.IExam==null)
      {
          IExam=new Examination(systemControl);
          systemControl.IExam=IExam;
      }
      else
      {
          IExam=systemControl.IExam;
      }
      String courseID=systemControl.getCourseID();
      if (IExam.selectExamOrNot(courseID)==true)
      {
          menuSelectExam.setEnabled(false);
          menuExamGroup.setEnabled(true);
          menuCancelExam.setEnabled(true);
          menuStartExam.setEnabled(false);
          menuFinishExam.setEnabled(false);
          if (IExam.examGroupOrNot(courseID)==true)
          {
              menuExamGroup.setEnabled(false);
              menuCancelExam.setEnabled(true);
              menuStartExam.setEnabled(true);
              menuFinishExam.setEnabled(false);
          }
      }
      else
      {
          menuSelectExam.setEnabled(true);
          menuExamGroup.setEnabled(false);
          menuCancelExam.setEnabled(false);
          menuStartExam.setEnabled(false);
          menuFinishExam.setEnabled(false);
      }
      if (systemControl.getStartExamOrNot()==true)
      {
          menuCancelExam.setEnabled(false);
          menuStartExam.setEnabled(false);
          menuFinishExam.setEnabled(true);
      }
      else
      {
          menuFinishExam.setEnabled(false);
      }
  }
  /**  �����������˵� */
  void menuListen_menuSelected(MenuEvent e)
  {
      if (systemControl.IExam==null)
      {
          IExam=new Examination(systemControl);
          systemControl.IExam=IExam;
      }
      else
      {
          IExam=systemControl.IExam;
      }
      String courseID=systemControl.getCourseID();
      if (IExam.selectListenOrNot(courseID)==true)
      {
          menuSelectListen.setEnabled(false);
          menuCancelListen.setEnabled(true);
          jMenuItem41.setEnabled(true);
      }
      else
      {
          menuSelectListen.setEnabled(true);
          menuCancelListen.setEnabled(false);
          jMenuItem41.setEnabled(false);
      }
  }
  /**  ����ӰԺ  */
  void menuClassCinema_actionPerformed(ActionEvent e)
  {
      ClassCinemaFrm classCinemaFrm=new ClassCinemaFrm(this,"",true);
      classCinemaFrm.setSize(450, 120);
      classCinemaFrm.setResizable(false);
      Dimension dlgSize = classCinemaFrm.getSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      classCinemaFrm.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      classCinemaFrm.show();
  }
  /**  ��ݰ�ť�Ŀ���ӰԺ  */
  void btnMovie_actionPerformed(ActionEvent e)
  {
      menuClassCinema_actionPerformed(e);
  }
  /**   */
  void popMenuExitClass_actionPerformed(ActionEvent e)
  {
      String  userID;
      userID=getCurrentUserID();

      if (systemControl.IInspection==null)
      {
          IInspection=new Inspection(systemControl);
          systemControl.IInspection=IInspection;
      }
      else
      {
          IInspection=systemControl.IInspection;
      }

      try
      {
          IInspection.exitClass(userID);
      }
      catch (Exception evt)
      {
          systemControl.writeLog("��ǿ��ѧ���˳�ʱ,Զ�̵���ʧ�ܣ�");
      }
  }
  /**  �˳�ϵͳ  */
  public void exitClass()
  {
      Logout logout=new Logout(this.systemControl);
      boolean b=logout.logout();
      if(b==false) return;
      System.exit(0);
  }
  /**  ����������  */
  public void hideMainFrm()
  {
      this.setVisible(false);
  }
  /**  ��ݰ�ť���Ӿ���   */
  void btnHand_actionPerformed(ActionEvent e)
  {
      this.systemControl.communication.askForTeacher();
  }

  void this_windowOpened(WindowEvent e)
  {
      if (verifyPass==false)//���������֤�����˳�
          dispose();
  }

  void menuRandomGroup_actionPerformed(ActionEvent e)
  {
      this.systemControl.userGroup.randomGroup();
      Hashtable htUser=this.systemControl.getHtStudentIDInterface();
        for(Enumeration er=htUser.elements(); er.hasMoreElements();)
        {
            try
            {
                Student student=(Student)er.nextElement();
                student.updateGroup();
            }
            catch(Exception ex)
            {
                System.out.println("menuRandomGroup_actionPerformed Exception:"+ex.getMessage());
                ex.printStackTrace();
            }
        }
  }

  void menuHandGroup_actionPerformed(ActionEvent e)
  {
      JDialogSortGroup jDialogSortGroup=new JDialogSortGroup(systemControl);
      this.setEnabled(false);
      jDialogSortGroup.setSize(450,550);
      jDialogSortGroup.show();
      this.setEnabled(true);
  }

  void popMenuTalkWithTeacher_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      this.systemControl.communication.answerForStudent(eschoolUser);
  }

  void btnNetWork_actionPerformed(ActionEvent e)
  {
      this.systemControl.communication.addAllToOnlineTalk();
  }

  void btnWizard_actionPerformed(ActionEvent e)
  {
      this.systemControl.communication.startDemonstrate(SCREENURL);
  }

  void btnQuickAnswer_actionPerformed(ActionEvent e)
  {
      this.systemControl.communication.addAllToAnswerCompetition();
  }
  /**
    * MOTHOD: showInfoMsg
    * DESC  : ��ʾ��Ϣ��Ϣ��
    * CREATE: 1.0 Ardy 2001-09-29
    * MODIFY:
    */
    void showInfoMsg(Component parentComponent,String infoMsg)
    {
        JOptionPane pane = new javax.swing.JOptionPane();

        parentComponent.setEnabled(false);
        try
        {
            pane.showMessageDialog(this, "Զ����Ϣ��" + infoMsg,"���ý���",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            System.out.println("��Ϣ�Ի���->" + e.getMessage());
        }
        finally
        {
              parentComponent.setEnabled(true);
        }

    }
    public void sendInformation(String info)
    {
        showInfoMsg(this,info);
    }

  void this_windowClosing(WindowEvent e)
  {
      Logout logout=new Logout(this.systemControl);
      boolean b=logout.logout();
      if(b==false) return;
      System.exit(0);
  }

  void jMenuItem41_actionPerformed(ActionEvent e)
  {
      JDialogListenPlay jDialogListenPlay=new JDialogListenPlay(systemControl);
      this.setEnabled(false);
      jDialogListenPlay.setSize(430,350);
      jDialogListenPlay.show();
      this.setEnabled(true);
  }

  void jMenuItem46_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.addStudentToOnlineTalk(userID);
  }

  void jMenuItem55_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.addGroupToOnlineTalk(userID);
  }

  void btnSound_actionPerformed(ActionEvent e)
  {
      ImageIcon imgSound = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("sound.gif"));
      ImageIcon imgSoundStop = new ImageIcon(com.dc.eschool.MainFrm.class.getResource("soundstop.gif"));
      Hashtable HtUser=this.systemControl.getHtStudentIDInterface();
        Vector userIDs=new Vector();
        for(Enumeration er=HtUser.keys(); er.hasMoreElements();)
        {
            String userID=(String)er.nextElement();
            userIDs.addElement(userID);
        }
        if(btnSound.getToolTipText().equals("�����㲥"))
        {
            this.systemControl.communication.voiceBroadCast(userIDs,null);
            btnSound.setToolTipText("ֹͣ�㲥");
            btnSound.setIcon(imgSoundStop);
        }
        else
        {
            this.systemControl.communication.stopVoiceBroadCast(userIDs,null);
            btnSound.setToolTipText("�����㲥");
            btnSound.setIcon(imgSound);
        }

  }

  void btnRandomGroup_actionPerformed(ActionEvent e)
  {
      this.systemControl.userGroup.randomGroup();
      Hashtable htUser=this.systemControl.getHtStudentIDInterface();
        for(Enumeration er=htUser.elements(); er.hasMoreElements();)
        {
            try
            {
                Student student=(Student)er.nextElement();
                student.updateGroup();
            }
            catch(Exception ex)
            {
                System.out.println("btnRandomGroup_actionPerformed Exception:"+ex.getMessage());
                ex.printStackTrace();
            }
        }
  }

  void btnRecord_actionPerformed(ActionEvent e)
  {
      this.systemControl.jmfApi.startRecorder();
  }

  void jMenuItem3_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      this.systemControl.communication.answerForStudent(eschoolUser);
  }

  void jMenuItem6_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.addStudentToOnlineTalk(userID);
  }

  void jMenuItem7_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.startDemonstrate(SCREENURL,userID);
  }

  void jMenuItem42_actionPerformed(ActionEvent e)
  {
      this.systemControl.communication.askForTeacher();
  }

  void jMenuItem43_actionPerformed(ActionEvent e)
  {
      this.systemControl.jmfApi.startRecorder();
  }

  void jMenuItem14_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.addGroupToOnlineTalk(userID);
  }

  void jMenuItem4_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      Vector userIDs=this.systemControl.userGroup.getUserIDsByUserID(userID);
      this.systemControl.communication.voiceBroadCast(userIDs,null);
  }

  void jMenuItem16_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.addGroupToAnswerCompetition(userID);
  }

  void jMenuItem5_actionPerformed(ActionEvent e)
  {
      Hashtable HtUser=this.systemControl.getHtStudentIDInterface();
      Vector userIDs=new Vector();
      for(Enumeration er=HtUser.keys(); er.hasMoreElements();)
      {
          String userID=(String)er.nextElement();
          userIDs.addElement(userID);
      }
      this.systemControl.communication.voiceBroadCast(userIDs,null);
  }

  void jMenuItem54_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      Vector userIDs=this.systemControl.userGroup.getUserIDsByUserID(userID);
      this.systemControl.communication.voiceBroadCast(userIDs,null);
  }

  void jMenuItem21_actionPerformed(ActionEvent e)
  {
      this.systemControl.communication.addAllToOnlineTalk();
  }

  void jMenuItem23_actionPerformed(ActionEvent e)
  {
      this.systemControl.communication.startDemonstrate(SCREENURL);
  }

  void jMenuItem47_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.startDemonstrate(SCREENURL,userID);
  }

  void btnPCUser_actionPerformed(ActionEvent e)
  {
      //EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      //String userID=eschoolUser.getUserID();
      String userID=this.getCurrentUserID();
      if(userID==null) return;
      this.systemControl.communication.startStudentDemonstrate(userID);
  }

  void jMenuItem8_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.startStudentDemonstrate(userID);
  }

  void jMenuItem48_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.startStudentDemonstrate(userID);
  }

  void jMenuItem57_actionPerformed(ActionEvent e)
  {
      EschoolUser eschoolUser=(EschoolUser)groupView.getSelectedPerson();
      String userID=eschoolUser.getUserID();
      this.systemControl.communication.addGroupToAnswerCompetition(userID);
  }

  void jMenuItem25_actionPerformed(ActionEvent e)
  {
      this.systemControl.communication.addAllToAnswerCompetition();
  }

  void this_keyPressed(KeyEvent e)
  {
      String type=this.systemControl.getUserType();
      if(!type.equals(EschoolUser.ESCHOOL_STUDENT)) return;
      if(e.getKeyCode()==e.VK_F11)
          this.systemControl.communication.askForTeacher();
  }




}