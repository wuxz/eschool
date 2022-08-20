package com.dc.eschool.group;

import java.awt.*;
import javax.swing.*;
import com.dc.eschool.system.*;
import com.dc.eschool.systemControl.SystemControl;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import java.io.File;
//import java.io.FileFilter;
import java.util.Hashtable;
import java.util.Enumeration;
import com.dc.eschool.MainFrm_AboutBox;

/**
 * Title:        JDialogSortGroup
 * Description:  手工分组
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class JDialogSortGroup extends JDialog
{
    JPanel panel1 = new JPanel();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuItemLoad = new JMenuItem();
    JMenuItem jMenuItemSave = new JMenuItem();
    JMenuItem jMenuItemRun = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JLabel jLabel1 = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JButton jButtonModify = new JButton();
    JButton jButtonAdd = new JButton();
    JButton jButtonDel = new JButton();
    JLabel jLabel2 = new JLabel();
    JLabel jLabelGroupName = new JLabel();
    JLabel jLabel4 = new JLabel();
    JScrollPane jScrollPane2 = new JScrollPane();
    JList jListRemainder = new JList();
    JButton jButtonRight = new JButton();
    JButton jButtonLeft = new JButton();
    JButton jButtonAllRight = new JButton();
    JButton jButtonAllLeft = new JButton();
    JLabel jLabel5 = new JLabel();
    JScrollPane jScrollPane3 = new JScrollPane();
    JButton jButtonView = new JButton();
    JButton jButtonRun = new JButton();
    JButton jButtonSave = new JButton();
    JButton jButtonCancel = new JButton();
    JTable jTableGroup = new JTable();
    Vector[] vecData=new Vector[2];
    JList jListGroup = new JList();
   /**
    * 新增组临时信息
    */
    private static final String NEWGROUPNAME = "请填入组名";
    private static final String NEWGROUPREMARK = "组说明";
    private static final String REMAINDER = "Remainder";

    private SystemControl systemControl=null;
    private UserGroup userGroup=new UserGroup();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JMenuItem jMenuItem1 = new JMenuItem();
    public JDialogSortGroup(Frame frame, String title, boolean modal)
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
    public JDialogSortGroup(SystemControl sysControl)
    {
        this(sysControl.mainFrm, "手工分组", true);
        this.systemControl=sysControl;
        init();
        centerScreen();
    }
    void jbInit() throws Exception
    {
        panel1.setLayout(gridBagLayout1);
        jMenu1.setText("文件");
        jMenuItemLoad.setText("调入分组");
    jMenuItemLoad.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItemLoad_actionPerformed(e);
      }
    });
        jMenuItemSave.setText("保存");
    jMenuItemSave.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItemSave_actionPerformed(e);
      }
    });
        jMenuItemRun.setText("执行分组");
    jMenuItemRun.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItemRun_actionPerformed(e);
      }
    });
        jMenu2.setText("帮助");
        jLabel1.setText("已存在的分组");
    jLabel1.setBounds(new Rectangle(5, 0, 404, 18));
        jButtonModify.setText("修改");
    jButtonModify.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonModify_actionPerformed(e);
      }
    });
        jButtonAdd.setText("添加");
    jButtonAdd.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonAdd_actionPerformed(e);
      }
    });
        jButtonDel.setText("删除");
    jButtonDel.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonDel_actionPerformed(e);
      }
    });
        jLabel2.setText("分组");
        jLabelGroupName.setText("某某小组");
        jLabel4.setText("未分配的学生");
        jButtonRight.setText("＞");
    jButtonRight.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonRight_actionPerformed(e);
      }
    });
        jButtonLeft.setText("＜");
    jButtonLeft.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonLeft_actionPerformed(e);
      }
    });
        jButtonAllRight.setMaximumSize(new Dimension(47, 29));
    jButtonAllRight.setMinimumSize(new Dimension(47, 29));
    jButtonAllRight.setPreferredSize(new Dimension(47, 29));
    jButtonAllRight.setText("＞＞");
    jButtonAllRight.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonAllRight_actionPerformed(e);
      }
    });
        jButtonAllLeft.setMaximumSize(new Dimension(47, 29));
    jButtonAllLeft.setMinimumSize(new Dimension(47, 29));
    jButtonAllLeft.setPreferredSize(new Dimension(47, 29));
    jButtonAllLeft.setText("＜＜");
    jButtonAllLeft.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonAllLeft_actionPerformed(e);
      }
    });
        jLabel5.setText("分配到该组的学生");
        jButtonView.setText("预览");
    jButtonView.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        jButtonView_mouseMoved(e);
      }
    });
    jButtonView.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonView_actionPerformed(e);
      }
    });
        jButtonRun.setText("执行");
    jButtonRun.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        jButtonRun_mouseMoved(e);
      }
    });
    jButtonRun.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonRun_actionPerformed(e);
      }
    });
        jButtonSave.setText("保存");
    jButtonSave.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        jButtonSave_mouseMoved(e);
      }
    });
    jButtonSave.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonSave_actionPerformed(e);
      }
    });
        jButtonCancel.setText("取消");
    jButtonCancel.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButtonCancel_actionPerformed(e);
      }
    });
        this.setResizable(false);
        this.getContentPane().setLayout(null);
        panel1.setMaximumSize(new Dimension(434, 531));
    panel1.setMinimumSize(new Dimension(434, 531));
    panel1.setPreferredSize(new Dimension(434, 531));
    panel1.setBounds(new Rectangle(0, 18, 464, 477));
        jTableGroup.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        jTableGroup_mouseClicked(e);
      }
      public void mousePressed(MouseEvent e)
      {
        jTableGroup_mousePressed(e);
      }
    });
    jMenuItem1.setText("关于");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jMenuItem1_actionPerformed(e);
      }
    });
    panel1.add(jButtonCancel,  new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(22, 10, 20, 26), 20, -6));
    panel1.add(jButtonDel,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(11, 9, 0, 28), 19, -6));
    panel1.add(jButtonModify,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(11, 7, 0, 0), 19, -6));
    panel1.add(jScrollPane2,  new GridBagConstraints(0, 4, 1, 4, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 7, 0, 0), -99, 62));
    panel1.add(jLabel4,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 7, 0, 37), 50, 1));
    panel1.add(jLabelGroupName,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 7, 0, 62), 51, 1));
    panel1.add(jLabel2,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 7, 0, 35), 104, 5));
    panel1.add(jButtonAllLeft,  new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(14, 22, 0, 0), 19, -7));
    panel1.add(jButtonRight,  new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(21, 22, 0, 0), 19, -7));
    panel1.add(jButtonLeft,  new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(15, 22, 0, 0), 19, -7));
    panel1.add(jScrollPane3,      new GridBagConstraints(2, 4, 2, 4, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 23, 0, 27), -99, 62));
    panel1.add(jButtonSave,  new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(22, 15, 20, 0), 20, -6));
    panel1.add(jButtonRun,  new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(22, 10, 20, 0), 20, -6));
    panel1.add(jButtonView,  new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(22, 91, 20, 0), 20, -6));
    panel1.add(jButtonAdd,  new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(11, 12, 0, 0), 19, -6));
    panel1.add(jLabel5,      new GridBagConstraints(2, 2, 2, 2, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(17, 25, 0, 45), 11, 6));
    panel1.add(jScrollPane1,       new GridBagConstraints(0, 0, 4, 1, 1.0, 1.0
            ,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 6, 0, 22), -2, 96));
    panel1.add(jButtonAllRight,   new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
            ,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(13, 22, 36, 0), 19, -7));
    this.getContentPane().add(jLabel1, null);
    jScrollPane1.getViewport().add(jTableGroup, null);
    jScrollPane3.getViewport().add(jListGroup, null);
    jScrollPane2.getViewport().add(jListRemainder, null);
    this.getContentPane().add(panel1, null);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenu1.add(jMenuItemLoad);
        jMenu1.add(jMenuItemSave);
        jMenu1.add(jMenuItemRun);
    jMenu2.add(jMenuItem1);
        this.setJMenuBar(jMenuBar1);
    }
   /**
    * MOTHOD: init
    * DESC  : 初始化构造。
    * CREATE: 1.0 Ardy 2001-10-31
    * MODIFY:
    */
    void init()
    {
        systemControl.userGroup.copyTo(userGroup);
        ManualGroupTableModel manualGroupTableModel=new ManualGroupTableModel(userGroup);
        jTableGroup.setModel(manualGroupTableModel);
        String groupName=(String)manualGroupTableModel.getValueAt(0,0);
        if(groupName==null) return;
        ManualGroupListModel manualGroupListModel=new ManualGroupListModel(userGroup,groupName);
        jListGroup.setModel(manualGroupListModel);
        ManualGroupRemainderListModel manualGroupRemainderListModel=new ManualGroupRemainderListModel(userGroup);
        jListRemainder.setModel(manualGroupRemainderListModel);
        int row=jTableGroup.getRowCount();
        if(row>0)
        {
            jTableGroup.setRowSelectionInterval(0,0);
            jLabelGroupName.setText(groupName+" 组");
        }
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
            pane.showMessageDialog(this, "错误信息：" + infoMsg,"课堂交流->手工分组",JOptionPane.ERROR_MESSAGE);
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

    void jButtonModify_actionPerformed(ActionEvent e)
    {
        ((ManualGroupTableModel)jTableGroup.getModel()).setEnabled(true);
    }

    void jButtonAdd_actionPerformed(ActionEvent e)
    {
        boolean bl=userGroup.addGroup(NEWGROUPNAME,NEWGROUPREMARK);
        if(bl==false)
        {
            showInfoMsg(this,"组名不能重复！");
            return;
        }
        ((ManualGroupTableModel)jTableGroup.getModel()).setUserGroup(this.userGroup);
        ((ManualGroupTableModel)jTableGroup.getModel()).setEnabled(true);
    }

    void jButtonDel_actionPerformed(ActionEvent e)
    {
        int row=jTableGroup.getSelectedRow();
        String groupName=(String)((ManualGroupTableModel)jTableGroup.getModel()).getValueAt(row,0);
        boolean bl=userGroup.delGroup(groupName);
        if(bl==false)
        {
            showInfoMsg(this,"组删除失败！");
            return;
        }
        ((ManualGroupTableModel)jTableGroup.getModel()).setUserGroup(userGroup);
        int cuRow=jTableGroup.getSelectedRow();
        if(cuRow<0) return;
        String name=(String)((ManualGroupTableModel)jTableGroup.getModel()).getValueAt(cuRow,0);
        ((ManualGroupListModel)jListGroup.getModel()).setgroupName(name);

    }

    void jTableGroup_mouseClicked(MouseEvent e)
    {

    }

    void jButtonRight_actionPerformed(ActionEvent e)
    {
        Vector userIds=userGroup.getUserIDsByGroupName(UserGroup.REMAINDER);
        if(userIds==null||userIds.size()<=0) return;
        int row=jTableGroup.getSelectedRow();
        if(row<0) return;
        String groupName=(String)((ManualGroupTableModel)jTableGroup.getModel()).getValueAt(row,0);
        int index=jListRemainder.getSelectedIndex();
        if(index<0) return;
        String userId=(String)userIds.elementAt(index);
        userGroup.moveUser(userId,UserGroup.REMAINDER,groupName);
        ((ManualGroupListModel)jListGroup.getModel()).setgroupName(groupName);
        ((ManualGroupRemainderListModel)jListRemainder.getModel()).updateData();
    }

    void jButtonLeft_actionPerformed(ActionEvent e)
    {
        int row=jTableGroup.getSelectedRow();
        if(row<0) return;
        String groupName=(String)((ManualGroupTableModel)jTableGroup.getModel()).getValueAt(row,0);
        Vector userIds=userGroup.getUserIDsByGroupName(groupName);
        if(userIds==null||userIds.size()<=0) return;
        int index=jListGroup.getSelectedIndex();
        if(index<0) return;
        String userId=(String)userIds.elementAt(index);
        userGroup.moveUser(userId,groupName,UserGroup.REMAINDER);
        ((ManualGroupListModel)jListGroup.getModel()).setgroupName(groupName);
        ((ManualGroupRemainderListModel)jListRemainder.getModel()).updateData();
    }

    void jButtonAllRight_actionPerformed(ActionEvent e)
    {
        if(jListRemainder.getSelectedIndex()<0) return;
        Vector userIds=userGroup.getUserIDsByGroupName(UserGroup.REMAINDER);
        if(userIds==null||userIds.size()<=0) return;
        int row=jTableGroup.getSelectedRow();
        if(row<0) return;
        String groupName=(String)((ManualGroupTableModel)jTableGroup.getModel()).getValueAt(row,0);
        for(int i=0;i<userIds.size();i++)
        {
            userGroup.moveUser((String)userIds.elementAt(i),UserGroup.REMAINDER,groupName);
        }
        ((ManualGroupListModel)jListGroup.getModel()).setgroupName(groupName);
        ((ManualGroupRemainderListModel)jListRemainder.getModel()).updateData();
    }

    void jButtonAllLeft_actionPerformed(ActionEvent e)
    {
        if(jListGroup.getSelectedIndex()<0) return;
        int row=jTableGroup.getSelectedRow();
        if(row<0) return;
        String groupName=(String)((ManualGroupTableModel)jTableGroup.getModel()).getValueAt(row,0);
        Vector userIds=userGroup.getUserIDsByGroupName(groupName);
        if(userIds==null||userIds.size()<=0) return;
        for(int i=0;i<userIds.size();i++)
        {
            userGroup.moveUser((String)userIds.elementAt(i),groupName,UserGroup.REMAINDER);
        }
        ((ManualGroupListModel)jListGroup.getModel()).setgroupName(groupName);
        ((ManualGroupRemainderListModel)jListRemainder.getModel()).updateData();
    }

    void jButtonCancel_actionPerformed(ActionEvent e)
    {
        userGroup=null;
        dispose();
    }

    void jButtonSave_actionPerformed(ActionEvent e)
    {
        userGroup.matchGroup(systemControl.userGroup);
        userGroup.copyTo(systemControl.userGroup);
        String fileName;
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("."));
        MyFileFilter filter = new MyFileFilter();
        filter.addExtension("xml");
        filter.setDescription("xml File");
        FileFilter f=jFileChooser.getFileFilter();
        jFileChooser.removeChoosableFileFilter(f);
        jFileChooser.setFileFilter(filter);
        int result =jFileChooser.showSaveDialog(this);
        if(result==jFileChooser.CANCEL_OPTION) return;
        String path=jFileChooser.getCurrentDirectory().getPath();
        //if(jFileChooser.getSelectedFile()==null) return;
        if(path.length()==3)//根目录
            fileName=jFileChooser.getSelectedFile().getName();
        else
            fileName="/" + jFileChooser.getSelectedFile().getName();
        if(fileName.indexOf(".xml")==-1)
            fileName+=".xml";
        fileName=path+ fileName;
        System.out.println("fileName :"+fileName);
        //systemControl.userGroup.saveGroupToFile(fileName);
        userGroup.saveGroupToFile(fileName);
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
                System.out.println("jButtonRun_actionPerformed Exception:"+ex.getMessage());
                ex.printStackTrace();
            }
        }
        dispose();
    }

    void jButtonRun_actionPerformed(ActionEvent e)
    {
        userGroup.matchGroup(systemControl.userGroup);
        userGroup.copyTo(systemControl.userGroup);
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
                System.out.println("jButtonRun_actionPerformed Exception:"+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    void jMenuItemLoad_actionPerformed(ActionEvent e)
    {
        String fileName;
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("."));
        MyFileFilter filter = new MyFileFilter();
        filter.addExtension("xml");
        filter.setDescription("xml File");
        FileFilter f=jFileChooser.getFileFilter();
        jFileChooser.removeChoosableFileFilter(f);
        jFileChooser.setFileFilter(filter);

        int result =jFileChooser.showOpenDialog(this);
        if(result==jFileChooser.CANCEL_OPTION) return;
        //if(jFileChooser.getSelectedFile()==null) return;
        fileName="/" + jFileChooser.getSelectedFile().getName();
        fileName=jFileChooser.getCurrentDirectory().getPath()+ fileName;
        System.out.println("fileName :"+fileName);
        userGroup.loadGroupFromFile(fileName);
        ((ManualGroupRemainderListModel)jListRemainder.getModel()).updateData();
        ((ManualGroupTableModel)jTableGroup.getModel()).setUserGroup(userGroup);
        int row=jTableGroup.getSelectedRow();
        if(row<0) return;
        String groupName=(String)((ManualGroupTableModel)jTableGroup.getModel()).getValueAt(row,0);
        ((ManualGroupListModel)jListGroup.getModel()).setgroupName(groupName);
    }

    void jMenuItemSave_actionPerformed(ActionEvent e)
    {
        jButtonSave_actionPerformed(e);
    }

    void jMenuItemRun_actionPerformed(ActionEvent e)
    {
        jButtonRun_actionPerformed(e);
    }

    void jButtonView_actionPerformed(ActionEvent e)
    {
        userGroup.matchGroup(systemControl.userGroup);
        JDialogGroupView jDialogGroupView=new JDialogGroupView(systemControl,this.userGroup);
        this.setEnabled(false);
        jDialogGroupView.setSize(700,400);
        jDialogGroupView.show();
        this.setEnabled(true);
    }
    public void centerScreen()
    {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - 450) / 2,(dim.height - 550) / 2);
    }

    void jTableGroup_mousePressed(MouseEvent e)
    {
        int row=jTableGroup.getSelectedRow();
        if(row<0) return;
        String groupName=(String)((ManualGroupTableModel)jTableGroup.getModel()).getValueAt(row,0);
        ((ManualGroupListModel)jListGroup.getModel()).setgroupName(groupName);
        jListGroup.setSelectedIndex(0);
        jListRemainder.setSelectedIndex(0);
        jLabelGroupName.setText(groupName+" 组");
    }

    void jButtonView_mouseMoved(MouseEvent e)
    {
        TableCellEditor cellEdit=jTableGroup.getCellEditor();
        if (cellEdit!=null)
        {
            cellEdit.stopCellEditing();
        }
    }

    void jButtonRun_mouseMoved(MouseEvent e)
    {
        TableCellEditor cellEdit=jTableGroup.getCellEditor();
        if (cellEdit!=null)
        {
            cellEdit.stopCellEditing();
        }
    }

    void jButtonSave_mouseMoved(MouseEvent e)
    {
        TableCellEditor cellEdit=jTableGroup.getCellEditor();
        if (cellEdit!=null)
        {
            cellEdit.stopCellEditing();
        }
    }

    void jMenuItem1_actionPerformed(ActionEvent e)
    {
        MainFrm_AboutBox dlg = new MainFrm_AboutBox(null);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = Toolkit.getDefaultToolkit().getScreenSize();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 , (frmSize.height - dlgSize.height) / 2 );
        dlg.setModal(true);
        dlg.show();
    }
}