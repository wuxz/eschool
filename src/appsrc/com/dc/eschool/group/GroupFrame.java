package com.dc.eschool.group;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GroupFrame extends JFrame implements PopupMenuBuilder
{
  JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();

  GroupModel groupModel = new GroupModelDemo();

  GroupComponent groupView = new GroupComponent(groupModel);
  JScrollPane groupScroll = new JScrollPane();
  JPopupMenu teacherPopupMenu = new JPopupMenu();
  JPopupMenu studentPopupMenu = new JPopupMenu();
  JPopupMenu auditorPopupMenu = new JPopupMenu();
  JMenuItem nameItem = new JMenuItem();


  /**Construct the frame*/
  public GroupFrame()
  {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  /**Component initialization*/
  private void jbInit() throws Exception
  {
    //setIconImage(Toolkit.getDefaultToolkit().createImage(GroupFrame.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(800, 600));
    this.setTitle("Frame Title");

    contentPane.add( groupScroll, BorderLayout.CENTER);
    groupScroll.getViewport().add(groupView);
    teacherPopupMenu.add(new JMenuItem("老师"));
    teacherPopupMenu.add(new JMenuItem("老师2"));
    studentPopupMenu.add(new JMenuItem("学生"));
    studentPopupMenu.add(new JMenuItem("学生2"));
    studentPopupMenu.add(nameItem);
    auditorPopupMenu.add(new JMenuItem("旁听"));
    auditorPopupMenu.add(new JMenuItem("旁听2"));

    groupView.setPopupMenuBuilder(this);

    //groupView.addPopupMenu( Person.TEACHER, teacherPopupMenu);
    //groupView.addPopupMenu( Person.STUDENT, studentPopupMenu);
    //groupView.addPopupMenu( Person.AUDITOR, auditorPopupMenu);
  }
  /**File | Exit action performed*/
  public void jMenuFileExit_actionPerformed(ActionEvent e)
  {
    System.exit(0);
  }
  /**Help | About action performed*/
  /**Overridden so we can exit when window is closed*/
  protected void processWindowEvent(WindowEvent e)
  {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING)
    {
      jMenuFileExit_actionPerformed(null);
    }
  }
  /**
   * 返回当前的PopupMenu 实现PopupMenuBuilder方法
   */
  public JPopupMenu getPersonPopupMenu( GroupComponent gc, PersonSeat ps, int x, int y, int rowIndex, int columnIndex )
  {
    JPopupMenu pm = null;
    if ( ps == null ) return null;

    Person p = ps.getPerson();

    if ( p == null ) return null;

    int type = p.getType();

    if ( type == Person.TEACHER )
      pm = this.teacherPopupMenu ;
    else if ( type == Person.STUDENT )
      pm = this.studentPopupMenu;
    else if ( type == Person.AUDITOR )
      pm = this.auditorPopupMenu;

    //pm.add(new JLabel(p.getName()));
    nameItem.setText("Name: " + p.getName() + " LoginName: " + p.getLoginName() );
    return pm;

  };

}