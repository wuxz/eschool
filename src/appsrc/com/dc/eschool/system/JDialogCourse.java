package com.dc.eschool.system;

import java.awt.*;
import java.net.InetAddress;
import javax.swing.*;
import java.util.Vector;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.Enumeration;
import java.net.UnknownHostException;
import com.dc.eschool.systemControl.*;
import com.dc.eschool.group.EschoolUser;
//import com.borland.jbcl.layout.*;
/**
 * Title:         JDialogCourse
 * Description:   选择课堂登录
 * Copyright:     Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class JDialogCourse extends JDialog
{
   /**
    * 人类型: 教师
    */
    private static final String ESCHOOL_TEACHER = "0";

   /**
    * 人类型: 学生
    */
    private static final String ESCHOOL_STUDENT = "1";
    JPanel contentPanel = new JPanel();
    JComboBox classroomListComboBox = new JComboBox();
    JButton okButton = new JButton();
    JLabel jLabel1 = new JLabel();
    JButton cancelButton = new JButton();
    private LoginIF login;
    private SystemControl systemControl;
    private Hashtable htCourse;
    private String userType;
    private String userID;
    public  Vector vecUsers;
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel2 = new JLabel(new ImageIcon( JDialogCourse.class.getResource("enter_title.gif") ));
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel rightButtonGroupPanel = new JPanel();
  JPanel leftButtonGroupPanel = new JPanel();
  JPanel buttonPanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel centerButtonGroupPanel = new JPanel();
    public JDialogCourse(Frame frame, String title, boolean modal)
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
    public JDialogCourse(SystemControl sysControl,Login login,Hashtable htCourse)
    {
        this(sysControl.mainFrm, "课堂登录", true);
        this.systemControl=sysControl;
        this.login=login;
        this.htCourse=htCourse;
        this.userID=(String)htCourse.get("userid");
        initComboBox();
        centerScreen();
    }
    void jbInit() throws Exception
    {
        contentPanel.setLayout(flowLayout1);
        okButton.setText("确定");
        okButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                okButton_actionPerformed(e);
            }
            catch(Exception ex)
            {
            }
        }
        });
        this.getContentPane().setLayout(borderLayout1);
        jLabel1.setText("课堂选择：");
        cancelButton.setText("取消");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            cancelButton_actionPerformed(e);
          }
        });
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
    this.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        this_windowClosing(e);
      }
    });
    buttonPanel.setLayout(borderLayout2);
    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.add(jLabel1, null);
    contentPanel.add(classroomListComboBox, null);
    this.getContentPane().add(jLabel2,  BorderLayout.NORTH);
    this.getContentPane().add(buttonPanel,  BorderLayout.SOUTH);
    buttonPanel.add(leftButtonGroupPanel, BorderLayout.WEST);
    buttonPanel.add(rightButtonGroupPanel, BorderLayout.EAST);
    buttonPanel.add(centerButtonGroupPanel,  BorderLayout.CENTER);
    centerButtonGroupPanel.add(okButton, null);
    centerButtonGroupPanel.add(cancelButton, null);

    }
   /**
    * MOTHOD: initComboBox
    * DESC  : 初始化课程下拉框。
    * CREATE: 1.0 Ardy 2001-09-29
    * MODIFY:
    */
    void initComboBox()
    {
        classroomListComboBox.removeAllItems();
        if(htCourse==null||htCourse.size()==0) return;
        userType=(String)htCourse.get(EschoolUser.TYPE);
        htCourse.remove(EschoolUser.TYPE);
        htCourse.remove("userid");
        JComboBoxModel jComboBoxModel=new JComboBoxModel();
        classroomListComboBox.setModel(jComboBoxModel);
        classroomListComboBox.setSelectedIndex(0);

    }
    class JComboBoxModel implements ComboBoxModel
    {
        private Vector vecCourseID=new Vector();
        private Vector vecCourseName=new Vector();
        private String selectedItem;
        public JComboBoxModel()
        {
            for(Enumeration er=htCourse.keys(); er.hasMoreElements();)
            {
                String id=(String)er.nextElement();
                vecCourseID.addElement(id);
                String name=(String)htCourse.get(id);
                vecCourseName.addElement(name);
            }
        }
        /** Return the selected item **/
        public Object getSelectedCourse(int index)
        {
            return vecCourseID.elementAt(index);
        }
        /** Set the selected item **/
        public void setSelectedItem(Object anItem)
        {
            this.selectedItem=(String)anItem;
        }
        /** Return the selected item **/
        public Object getSelectedItem()
        {
            return this.selectedItem;
        }
        /**
       * Returns the length of the list.
       */
        public int getSize()
        {
            return vecCourseID.size();
        }

      /**
       * Returns the value at the specified index.
       */
        public Object getElementAt(int index)
        {
            return vecCourseName.elementAt(index);
        }
        /**
         * Remove a listener from the list that's notified each time a
         * change to the data model occurs.
         * @param l the ListDataListener
         */
        public void removeListDataListener(javax.swing.event.ListDataListener l)
        {
        }
        /**
         * Add a listener to the list that's notified each time a change
         * to the data model occurs.
         * @param l the ListDataListener
         */
        public void addListDataListener(javax.swing.event.ListDataListener l)
        {
        }

    }


    void this_windowOpened(WindowEvent e)
    {
        initComboBox();
    }

    void okButton_actionPerformed(ActionEvent e) throws UnknownHostException
    {
        int row=classroomListComboBox.getSelectedIndex();
        if(row<0) return;
        String ip=null;
        String courseID=(String)((JComboBoxModel)classroomListComboBox.getModel()).getSelectedCourse(row);
        String courseName=(String)((JComboBoxModel)classroomListComboBox.getModel()).getElementAt(row);
        this.systemControl.setCourseName(courseName);
        this.systemControl.setCourseID(courseID);
        if (this.userType.equals(ESCHOOL_TEACHER))
        {
            ip=login.getLocalIP();//获得本地IP
        }
        vecUsers=login.login(courseID,ip,this.userID);
        this.dispose();
        this.jLabel2=null;
    }
    void cancelButton_actionPerformed(ActionEvent e)
    {
        dispose();
        System.exit(0);
    }
    public void centerScreen()
    {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - abounds.width) / 2,(dim.height - abounds.height) / 2);
    }

    void this_windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
}