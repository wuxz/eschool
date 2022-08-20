package com.dc.eschool.group;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableCellRenderer;
import java.io.Serializable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Hashtable;
import java.awt.event.*;
import java.util.Vector;



/**
 * Title:        Group Test
 * Description:  GroupComponent中人的图标绘制器
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class PersonRenderer extends JLabel implements TableCellRenderer , Serializable
{

  ImageIcon handUpIcon = null;
  ImageIcon examingIcon = null;
  ImageIcon callingIcon = null;

  protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

  // We need a place to store the color the JLabel should be returned
  // to after its foreground and background colors have been set
  // to the selection background color.
  // These ivars will be made protected when their names are finalized.
  private Color unselectedForeground;
  private Color noGroupBackground = new Color(0,153,218);
  private Color groupBackground[] = {new Color( 191,220,243), new Color( 117,188,232) };
  //private Color noGroupBackground = new Color(140,140,140);
  //private Color groupBackground[] = {new Color( 180,180,180), new Color( 160,160,160) };

  //PersonSeat personSeatOne = null;

  /**
   * Creates a default table cell renderer.
   */
  public PersonRenderer()
  {
    super();
    setOpaque(true);
    setBorder(noFocusBorder);
  }

  /**
   * Overrides <code>JComponent.setForeground</code> to assign
   * the unselected-foreground color to the specified color.
   *
   * @param c set the foreground color to this value
   */
  public void setForeground(Color c)
  {
      super.setForeground(c);
      unselectedForeground = c;
  }

  /**
   * Overrides <code>JComponent.setForeground</code> to assign
   * the unselected-background color to the specified color.
   *
   * @param c0,c1 set the background color to this value, default new Color( 156,154,156), new Color( 206,207,206)
   */
  public void setBackground(Color teacher, Color group0, Color group1)
  {
      super.setBackground(teacher);
      this.noGroupBackground = teacher;
      this.groupBackground[0] = group0;
      this.groupBackground[1] = group1;

  }

  /**
   * Notification from the <code>UIManager</code> that the look and feel
   * [L&F] has changed.
   * Replaces the current UI object with the latest version from the
   * <code>UIManager</code>.
   *
   * @see JComponent#updateUI
   */
  public void updateUI()
  {
      super.updateUI();
      setForeground(null);
      setBackground(null);
  }

  // implements javax.swing.table.TableCellRenderer
  /**
   *
   * Returns the default table cell renderer.
   *
   * @param table  the <code>JTable</code>
   * @param value  the value to assign to the cell at
   *			<code>[row, column]</code>
   * @param isSelected true if cell is selected
   * @param isFocus true if cell has focus
   * @param row  the row of the cell to render
   * @param column the column of the cell to render
   * @return the default table cell renderer
   */
  public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column)
  {
    //setColorIndex( value );

    //personSeatOne = (PersonSeat)value;

    if (isSelected)
    {
      super.setForeground(table.getSelectionForeground());
      super.setBackground(table.getSelectionBackground());
    }
    else
    {
      super.setForeground((unselectedForeground != null) ? unselectedForeground
                                                         : table.getForeground());
      super.setBackground( getGroupBackground( table, value ) );
      //super.setBackground((unselectedBackground != null) ? unselectedBackground[1]
                                                        // : table.getBackground());
    }

    setFont(table.getFont());

    if (hasFocus)
    {
      setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
      //super.setForeground(table.getSelectionForeground());
      //super.setBackground(table.getSelectionBackground());
      if (table.isCellEditable(row, column))
      {
        super.setForeground( UIManager.getColor("Table.focusCellForeground") );
        super.setBackground( UIManager.getColor("Table.focusCellBackground") );
      }
    }
    else
    {
      setBorder(noFocusBorder);
    }

    setValue(value);

    // ---- begin optimization to avoid painting background ----
    Color back = getBackground();
    boolean colorMatch = (back != null) && ( back.equals(table.getBackground()) ) && table.isOpaque();
    setOpaque(!colorMatch);
    // ---- end optimization to aviod painting background ----

    //setPopupMenu();
    return this;

  }

  Color getGroupBackground(JTable table, Object value )
  {
    GroupComponent gc = (GroupComponent)table;
    PersonSeat ps = (PersonSeat)value;

    if ( ps == null || ps.getGroupName() == null ) return this.noGroupBackground;

    GroupModel gm = gc.getGroupModel();
    Vector groupNames = gm.getGroupNames();

    int index = groupNames.indexOf( ps.getGroupName() ) % 2 ;

    return this.groupBackground[index];

  }

  /*
   * The following methods are overridden as a performance measure to
   * to prune code-paths are often called in the case of renders
   * but which we know are unnecessary.  Great care should be taken
   * when writing your own renderer to weigh the benefits and
   * drawbacks of overriding methods like these.
   */

  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  public void validate()
  {}

  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  public void revalidate()
  {}

  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  public void repaint(long tm, int x, int y, int width, int height)
  {}

  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  public void repaint(Rectangle r)
  { }

  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  protected void firePropertyChange(String propertyName, Object oldValue, Object newValue)
  {
      // Strings get interned...
      if (propertyName=="text")
      {
          super.firePropertyChange(propertyName, oldValue, newValue);
      }
  }

  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue)
  { }


  /**
   * Sets the string for the cell being rendered to <code>value</code>.
   *
   * @param value  the string and icon value for this cell; if value is
   *		<code>null</code> it sets the text value to an empty string
   * @see JLabel#setText
   *
   */
  protected void setValue(Object value)
  {
      //setText((value == null) ? "" : value.toString());
    if ( value == null ) return;

    PersonSeat ps = (PersonSeat)value;
    Person p = ps.getPerson();
    Icon i = null;
    super.setText( (p == null) ?  "" : p.getName());

    if ( p != null )
    {
      if ( p.getType() == Person.STUDENT && p.getGender() == Person.MALE )
        if ( p.InClassroom() )
          i = IconFactory.studentMaleOnIcon;
        else
          i = IconFactory.studentMaleOffIcon;
      else if ( p.getType() == Person.STUDENT && p.getGender() == Person.FEMALE)
        if ( p.InClassroom() )
          i = IconFactory.studentFemaleOnIcon;
        else
          i = IconFactory.studentFemaleOffIcon;
      else if (p.getType() == Person.TEACHER && p.getGender() == Person.MALE )
        i = IconFactory.teacherMaleOnIcon;
      else if ( p.getType()== Person.TEACHER && p.getGender() == Person.FEMALE )
        i = IconFactory.teacherFemaleOnIcon;
      else if ( p.getType() == Person.AUDITOR )
        if ( p.InClassroom() )
          i = IconFactory.auditorOnIcon;
        else
          i = IconFactory.auditorOffIcon;

      this.handUpIcon=p.isHandUp()?IconFactory.handupIcon:null;
      this.examingIcon=p.examing()?IconFactory.examingIcon:null;
      this.callingIcon=p.calling()?IconFactory.callingIcon:null;

    }
    else
    {
      i=null;
    }

    super.setIcon(i);

    super.setVerticalAlignment( SwingConstants.BOTTOM );
    super.setHorizontalAlignment( SwingConstants.CENTER );
    super.setVerticalTextPosition( SwingConstants.BOTTOM );
    super.setHorizontalTextPosition( SwingConstants.CENTER );
    super.setIconTextGap(0);

  }

  void paintHangUp(Graphics g)
  {
    int yOff = 3;
    int xOff = 5;
    if (this.handUpIcon != null)
    {
      Image i = handUpIcon.getImage();
      g.drawImage(i,this.getWidth()/2+xOff,yOff, this);
      this.handUpIcon=null;
    }
  }

  void paintExaming(Graphics g)
  {
    int yOff = 8;
    int xOff = 0;
    if (this.examingIcon != null)
    {
      Image i = examingIcon.getImage();
      g.drawImage(i,this.getWidth()/2+xOff,yOff, this);
      this.examingIcon=null;
    }
  }

  void paintCalling(Graphics g)
  {
    int yOff = 8;
    int xOff = 0;
    if (this.callingIcon != null)
    {
      Image i = callingIcon.getImage();
      g.drawImage(i,this.getWidth()/2+xOff,yOff, this);
      this.callingIcon=null;
    }
  }

  /**
  * 覆盖paint方法, 添加Hang Up, examing 的绘制
  */
  public void paint(Graphics g)
  {
    super.paint(g);
    this.paintExaming(g);
    this.paintHangUp(g);
    this.paintCalling(g);
  }



  /**
   * A subclass of <code>DefaultTableCellRenderer</code> that
   * implements <code>UIResource</code>.
   * <code>DefaultTableCellRenderer</code> doesn't implement
   * <code>UIResource</code>
   * directly so that applications can safely override the
   * <code>cellRenderer</code> property with
   * <code>DefaultTableCellRenderer</code> subclasses.
   * <p>
   * <strong>Warning:</strong>
   * Serialized objects of this class will not be compatible with
   * future Swing releases.  The current serialization support is appropriate
   * for short term storage or RMI between applications running the same
   * version of Swing.  A future release of Swing will provide support for
   * long term persistence.
   */
  public static class UIResource extends DefaultTableCellRenderer
      implements javax.swing.plaf.UIResource
  {
  }

}