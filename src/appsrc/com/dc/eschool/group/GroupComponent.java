package com.dc.eschool.group;

import java.awt.*;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Vector;
import java.lang.Integer;
import javax.swing.event.TableModelEvent;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.image.*;



/**
 * Title:        Group Test
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class GroupComponent extends JTable
            implements  GroupModelListener,
                        MouseListener,
                        MouseMotionListener
{
  //BorderLayout CompontentLayout = new BorderLayout();
  //JScrollPane groupScrollPane = new JScrollPane();
  //JTable groupTable = new JTable();

  GroupModel groupModel;
  GroupTableModel groupTableModel;
  //int groupColumnCount = 10;

  /**
   * ��Ԫ������
   */
  PersonRenderer personRenerer;

  /**
   * Popup menu table
   */
  //Hashtable personMenus = new Hashtable();
  PopupMenuBuilder popupNenuBuilder = null;

  //���±���Ϊ�Ϸŷ���
  Image draglayer = null;
  Point dragPoint = null;
  boolean dragStart = false;
  private PersonSeat PersonSeatClipboard = null;
  int repaintStep = 0;
  int repaintCounter = 0;
  AlphaFilter alphaFilter = new AlphaFilter();
  boolean enableMovePerson = true;

  public GroupComponent( GroupModel gm )
  {
    super();
    this.groupModel = gm;
    try
    {
      jbInit();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception
  {
    this.groupTableModel = new GroupTableModel( this.groupModel, 10);
    this.groupModel.addGroupModelListener(this);
    setModel( groupTableModel );
    personRenerer = new PersonRenderer();
    setDefaultRenderer( PersonSeat.class,  this.personRenerer);
    setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    setRowHeight( 65 );
    setShowGrid(false);
    setIntercellSpacing( new Dimension(0,0));
    this.setColumnSelectionAllowed(false);
    this.setRowSelectionAllowed(false);
    this.setCellSelectionEnabled(true);
    this.getTableHeader().setReorderingAllowed(false);
    this.getTableHeader().setResizingAllowed(false);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);

  }

  /**
   * ����ѡ�����˵�PersonSeat
   */
  public PersonSeat getSelectedPersonSeat()
  {
    int rowIndex = this.getSelectedRow();
    int columnIndex = this.getSelectedColumn();
    PersonSeat rv = null;
    if ( rowIndex > -1 && columnIndex > -1 )
      rv = (PersonSeat)this.getValueAt( rowIndex,columnIndex );
    return rv;
    //return this.selectedPersonSeat;
  }

  /**
   * ����ѡ������
   */
  public Person getSelectedPerson()
  {
    PersonSeat ps = this.getSelectedPersonSeat();
    if ( ps == null ) return null;

    return ps.getPerson();
  }

  /**
   * ��ѡ�����˵�groupName
   */
  public String getSelectedGroupName()
  {
    PersonSeat ps = this.getSelectedPersonSeat();
    if ( ps == null ) return null;

    return ps.getGroupName();
  }

  /**
   * ����������
   */
  public void setColumnCount( int columnCount )
  {
    this.groupTableModel.setColumnCount( columnCount );
  }

  /**
   * ���õ����˵�������
   */
  public void setPopupMenuBuilder( PopupMenuBuilder p )
  {
    this.popupNenuBuilder = p;
  }

  /**
   * ���ص����˵�������
   */
  public PopupMenuBuilder getPopupMenuBuilder()
  {
    return this.popupNenuBuilder;
  }

  /**
   * �����Ϸ�ѧ����λ״̬,default is true
   */
  public void setEnableMovePerson( boolean b )
  {
    this.enableMovePerson = b;
  }

  /**
   * ����true, �����϶��ŷ�ѧ����λ
   */
  public boolean isEnableMovePerson()
  {
    return enableMovePerson;
  }

  /**
   * This fine grain notification tells listeners the exact range of Person that changed.
   */
  public void groupChanged()
  {
    this.groupTableModel.fireTableDataChanged();//����tableModel�ķ���ˢ��table
    //.fireTableChanged( new TableModelEvent( this.groupTableModel ));
    //this.tableChanged( new TableModelEvent( this.groupTableModel ));
  }

  /**
   * ����GroupModel
   */
  public GroupModel getGroupModel()
  {
    return this.groupModel;
  }

  /**
   * ����GroupModel
   */
  public void setGroupModel( GroupModel gm )
  {
    this.groupModel = gm;
    this.groupChanged();
  }

//  /**
//   * Ϊ��ͨ���͵�������Ҽ������˵�
//   */
//  public void addPopupMenu( int personType, JPopupMenu pm )
//  {
//    if ( pm != null)
//      this.personMenus.put( new Integer(personType), pm );
//  }

//  /**
//   * �Ƴ��Ҽ������˵�
//   */
//  public void removePopupMenu( int personType )
//  {
//    Object key = new Integer( personType);
//    if ( this.personMenus.get(key) != null )
//      this.personMenus.remove(key);
//  }

  //start mouselistener
  public void mousePressed( MouseEvent e)
  {
    //System.out.println("click");
    this.dragStart = false;
    this.dragPoint = e.getPoint();
    if ( this.enableMovePerson )
    {
      this.createDragLayer();
      this.copyPersonSeat();
    }
    this.setCursor( new Cursor(Cursor.HAND_CURSOR));
    showPopupMenu(e);
  }

  public void mouseClicked( MouseEvent e )
  {
    //this.dragPoint = e.getPoint();
    //System.out.println( "height:" + this.getSize().height );
    //System.out.println( "width:" + this.getSize().width );
    //System.out.println( "x:" + e.getX() );
    //System.out.println( "y:" + e.getY() );
    //this.selectionModel.setLeadSelectionIndex(0);
    //this.columnModel.getSelectionModel().setLeadSelectionIndex(0);
    //System.out.println("click");
    //System.out.println( countComponents()+ ":"+findComponentAt( e.getPoint() ));
    //System.out.println( ((PersonSeat)this.getValueAt(e.getPoint())).getPerson().getName());
    //this.getSelectionModel().setSelectionInterval( getRowIndex( e.getY()), getRowIndex( e.getY()));
    //this.getColumnModel().getSelectionModel().setSelectionInterval( getColumnIndex(e.getX()), getColumnIndex(e.getX()));
    showPopupMenu(e);
    if (this.getSelectedPerson() != null)
    {
      this.firePersonSelected();
    }
  }

  public void mouseReleased( MouseEvent e )
  {
    //System.out.println("click");
    if ( dragStart )
    {
      this.pasetPersonSeat();
    }
    this.dragStart = false;
    this.repaint();
    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    showPopupMenu(e);
  }

  public void mouseEntered(MouseEvent e)
  {
    //this.selectionModel.setLeadSelectionIndex(0);
    //this.columnModel.getSelectionModel().setLeadSelectionIndex(0);
    //System.out.println("click");
    this.dragStart = false;
  }

  public void mouseExited(MouseEvent e)
  {
    this.dragStart = false;
  }
  //end mouselistener

  void showPopupMenu( MouseEvent e)
  {
    int rowIndex = getRowIndex( e.getY());
    int columnIndex = getColumnIndex(e.getX());
    //ѡ����ǰѧ��
    this.getSelectionModel().setSelectionInterval( rowIndex, rowIndex );
    this.getColumnModel().getSelectionModel().setSelectionInterval( columnIndex, columnIndex );

//    JPopupMenu pm = null;
//    PersonSeat ps = this.getSelectedPersonSeat();
//    if ( ps == null ) return;
//
//    Person p = ps.getPerson();
//    //this.selectedPersonSeat = ps;
//
//    if ( p == null ) return;
//
//    int type = p.getType();
//
//    if (e.isPopupTrigger())
//    {
//      pm = (JPopupMenu)this.personMenus.get( new Integer(type));
//      if ( pm != null )
//      {
//        pm.show( this, e.getX(), e.getY() );
//      }
//    }
    PersonSeat ps = this.getSelectedPersonSeat();
    JPopupMenu pm = this.popupNenuBuilder == null?
                        null:
                        this.popupNenuBuilder.getPersonPopupMenu(
                                this, ps, e.getX(), e.getY(), rowIndex, columnIndex );
    if (e.isPopupTrigger())
      if ( pm != null )
        pm.show( this, e.getX(), e.getY() );

  }

  /**
   * ���ص�ǰλ�õ�����
   */
  public Object getValueAt( Point p )
  {
    if ( p == null ) return null;
    Object rv = null;
    int rowIndex = getRowIndex( p.y );
    int columnIndex = getColumnIndex( p.x );
    if ( rowIndex != -1 && columnIndex != -1 )
      rv = this.getValueAt(rowIndex, columnIndex);
    return rv;
  }

  /**
   * ��������y��Ӧ��λ��rowIndex, y:����y
   */
  public int getRowIndex( int y )
  {
    int rowIndex = -1;
    int height = y;

    for ( int i = 0; i < this.getRowCount(); )
    {
      height = height - this.getRowHeight(i) - this.getIntercellSpacing().height;

      if ( height < 0 )
      {
        rowIndex = i;
        break;
      }
      i++;
    }
    return rowIndex;
  }

  /**
   * ��������x��Ӧ��λ��columnIndex, x:����x
   */
  public int getColumnIndex( int x )
  {

    int columnIndex = -1;

    int width = x;

    for ( int i = 0; i < this.columnModel.getColumnCount(); )
    {
      width = width - this.columnModel.getColumn(i).getWidth() - this.getIntercellSpacing().width;

      if ( width < 0 )
      {
        columnIndex = i;
        break;
      }

      i++;
    }
    return columnIndex;
  }

  /**
   * ����paint����, ���groupName��dragLayer�Ļ���
   */
  public void paint(Graphics g)
  {
    super.paint(g);
    //g.drawString("Test", 100,100);
    paintGroupName( g );
    paintDragLayer( g );
    //System.out.println("Paint test");
  }

  /**
   * ����Group��Name��Table��
   */
  void paintGroupName(Graphics g)
  {
    //int alpha = 180;
    int x = 3;
    int y = 12 + this.getRowHeight(0);//��������ʦ��
    // System.out.println(this.getRowHeight(0));

    Vector groupNames = this.groupModel.getGroupNames();
    Vector studentNames = null;
    String groupName = null;
    int studentCount = 0;
    int groupRowCount = 0;
    int rowIndex = 1;
    int groupRowHeight = 0;

    Color beforeColor = g.getColor();
    //Color newColor = new Color( beforeColor.getRed(), beforeColor.getGreen(), beforeColor.getBlue()) ;
    //Color newColor2 = new Color( beforeColor.getRed(), beforeColor.getGreen(), beforeColor.getBlue(),alpha ) ;
    //g.setColor( newColor );
    //g.setXORMode(beforeColor);
    for (int i=0; i<groupNames.size(); i++)
    {
      //g.draw3DRect(0,y - 12, this.getWidth() , 1, true );
      g.setColor( Color.lightGray );
      g.drawString(groupNames.get(i) + "", x+1, y+1 );
      g.setColor( Color.gray );
      g.drawString(groupNames.get(i) + "", x+1, y );
      g.setColor( beforeColor );
      g.drawString(groupNames.get(i) + "", x, y );

      groupName = (String)groupNames.get(i);
      studentNames = groupModel.getStudentLoginNames(groupName);
      studentCount = studentNames.size();

      groupRowCount = (studentCount-1) / this.groupTableModel.getColumnCount() + 1;
      //System.out.println("groupRowCount:" + groupRowCount);
      int j = 1;
      groupRowHeight = 0;
      do
      {
        rowIndex += j;
        groupRowHeight += ( this.getRowHeight( rowIndex ) + this.getIntercellSpacing().getHeight());
        j++;
      } while ( j <= groupRowCount );
      //System.out.println("groupRowHeight:" + groupRowHeight);
      y += groupRowHeight;
    }
    g.setColor(beforeColor);
  }


  // start MouseMotionListener

  /**
   * Invoked when a mouse button is pressed on a component and then
   * dragged.  Mouse drag events will continue to be delivered to
   * the component where the first originated until the mouse button is
   * released (regardless of whether the mouse position is within the
   * bounds of the component).
   */
  public void mouseDragged(MouseEvent e)
  {
    int mods = e.getModifiers();
    boolean leftButtonClick = (mods & MouseEvent.BUTTON1_MASK) != 0 ? true:false;
    if ( this.enableMovePerson )
    {

      PersonSeat ps = this.PersonSeatClipboard;

      if (leftButtonClick)
        if (  ps != null )
          if ( ps.getGroupName() != null )
            if ( ps.getPerson() != null)
              {
                this.dragPoint = e.getPoint();
                this.dragStart = true;
              }

      if (dragStart)
      {
        if ( this.repaintCounter == this.repaintStep )
        {
          this.repaint();
          this.repaintCounter = 0;
        }
        else
        {
          this.repaintCounter ++;
        }

      }
    }
  };

  /**
   * Invoked when the mouse button has been moved on a component
   * (with no buttons no down).
   */
  public void mouseMoved(MouseEvent e)
  {
    //this.dragPoint = e.getPoint();
    //this.repaint();
  };

  // end MouseMotionListener

  /**
   * �����ϷŵĲ�
   */
  void paintDragLayer( Graphics g )
  {
    //System.out.println(dragPoint);
    if (this.draglayer == null || this.dragPoint == null ) return;
    if (dragStart)g.drawImage( this.draglayer, this.dragPoint.x -20, this.dragPoint.y-20, this);
    //g.drawImage( this.draglayer, 100, 100, this);
    //g.drawString("drag",100,100);
    //System.out.println("drag");
  }

  /**
   * �����Ϸŵķ���
   */
  void createDragLayer( )
  {
    int alpha = 150; //͸����
    Object value = this.getSelectedPersonSeat();
    if (value == null) return ;

    Component c = this.getDefaultRenderer(value.getClass()).getTableCellRendererComponent
                      (this,value,true,false,this.getSelectedRow(),this.getSelectedColumn()) ;

    int height = this.getRowHeight(this.getSelectedRow() );
    int width = this.getColumnModel().getColumn(this.getSelectedColumn()).getWidth();
    //System.out.println("height:"+height + "width:" + width );
    c.setSize( width,height);
    //Color bgColor = c.getBackground();
    //bgColor = new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), alpha );
    //c.setBackground(bgColor);

    //if (c.getHeight() <= 0 || c.getWidth() <= 0 ) return;

    //this.draglayer = this.createImage(c.getWidth(),c.getHeight());
    Image rv = this.createImage(c.getWidth(),c.getHeight());
    //BufferedImage rv = new BufferedImage(c.getWidth(),c.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics og = rv.getGraphics();
    c.paint(og);
    this.alphaFilter.setAlpha( alpha );
    rv = this.createImage( new FilteredImageSource( rv.getSource(), this.alphaFilter ));
    this.draglayer = rv;
  }

  /**
   * ��ѡ���PersongSeat���������а���
   */
  void copyPersonSeat()
  {
    //StringSelection selection = new StringSelection( this.getSelectedRow()+"," + this.getSelectedColumn() );
    //sysClipboard.setContents(selection, null);
    this.PersonSeatClipboard = this.getSelectedPersonSeat();
  }

  /**
   * �Ѽ��а��е�PersonSeatճ������ǰѡ���Ŀ��
   */
  void pasetPersonSeat()
  {
    String loginName = null;
    String fromGroupName = null;
    String toGroupName = null;
    String toLoginName = null;

    if ( this.getSelectedPersonSeat() == null ) return ;
    if ( this.PersonSeatClipboard == null ) return;

    toGroupName = this.getSelectedPersonSeat().getGroupName();
    if (toGroupName == null) return;

    toLoginName = this.getSelectedPerson()==null? null:this.getSelectedPerson().getLoginName();

    fromGroupName = this.PersonSeatClipboard.getGroupName();
    if ( fromGroupName == null ) return;

    if ( this.PersonSeatClipboard.getPerson() == null ) return;
    loginName = this.PersonSeatClipboard.getPerson().getLoginName();

    this.groupModel.movePerson( fromGroupName, loginName, toGroupName, toLoginName );

    //PersonSeat srcPersonSeat = null;
    //String text = null;
    //int srcRowIndex = 0;
    //int srcColumnIndex = 0;
    //Transferable contents = sysClipboard.getContents(this);
    //if (contents == null) ;
    //try
    //{
    //  text = (String)(contents.getTransferData(DataFlavor.stringFlavor));
    //  srcRowIndex = Integer.parseInt( text.substring(0, text.indexOf(",") ));
    //  srcColumnIndex = Integer.parseInt( text.substring( text.indexOf(",") + 1 ));
    //  srcPersonSeat = (PersonSeat)this.getValueAt( srcRowIndex, srcColumnIndex );
    //  //if
    //}
    //catch(Exception e)
    //{
    //  System.out.println("Error: pasetPersonSeat()" + e.getMessage() );
    //}
  }

  /**
   * ʵ��alpha���ǵ���
   */
  class AlphaFilter extends RGBImageFilter
  {
    int alpha;
    public AlphaFilter()
    {
      this(0);
    }
    public AlphaFilter( int alpha )
    {
      this.canFilterIndexColorModel = true;
      this.setAlpha( alpha );
    }

    public void setAlpha( int alpha )
    {
      this.alpha = ( alpha < 0 || alpha > 255 )? 0: alpha;
    }
    public int filterRGB( int x, int y, int rgb )
    {
      DirectColorModel cm = (DirectColorModel) ColorModel.getRGBdefault();

      int alpha = cm.getAlpha(rgb);
      int red = cm.getRed(rgb);
      int green = cm.getGreen(rgb);
      int blue = cm.getBlue(rgb);

      alpha = alpha == 0 ? 0 : this.alpha;

      return alpha<<24 | red<<16 | green<<8 | blue;
    }
  }

  /**
   * ���GroupComponentListener����������ѡ�˶���
   */
  public void addGroupComponentListener(GroupComponentListener l)
  {
    this.listenerList.add(GroupComponentListener.class, l);
  }

  /**
   * ɾ��GroupComponentListener
   */
  public void removeGroupComponentListener(GroupComponentListener l)
  {
    this.listenerList.remove(GroupComponentListener.class,l);
  }

  /**
   * ��������personSelected�ļ���
   */
  void firePersonSelected()
  {
    Object[] listeners = this.listenerList.getListeners(GroupComponentListener.class );
    Person selectedPerson = this.getSelectedPerson();
    for (int i=0; i<listeners.length; i++ )
    {
      if (listeners[i]!=null)
        ((GroupComponentListener)listeners[i]).personSelected(new GroupComponentEvent(this), selectedPerson );
    }

  }

  public void tableChanged(TableModelEvent e)
  {
    int row = this.getSelectedRow();
    int col = this.getSelectedColumn();
    super.tableChanged(e);
    //System.out.println("row:" + row + " col:" + col);
    if ( col >0 )
      this.setColumnSelectionInterval(col,col);
    if ( row >0 )
      this.setRowSelectionInterval(row,row);
  }

}