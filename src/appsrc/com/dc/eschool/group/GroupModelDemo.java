package com.dc.eschool.group;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

/**
 * Title:        Group Test
 * Description:  Demo for GroupModel
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class GroupModelDemo implements GroupModel, GroupComponentListener
{
  Person teacher;
  Hashtable groups = new Hashtable();
  Hashtable auditors = new Hashtable();
  GroupModelListener l;
  Vector groupList = new Vector();
  Hashtable studentListTable = new Hashtable();
  Vector auditorList = new Vector();

  public GroupModelDemo()
  {
    teacher = new PersonDemo( "Teacher", "teacherwang", Person.TEACHER, Person.FEMALE, true );

    Hashtable ht = new Hashtable();
    ht.put("robin0", new PersonDemo( "分组1", "robin0", Person.STUDENT, Person.MALE, true ));
    ht.put("robin1", new PersonDemo( "小麦1", "robin1", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robin2", new PersonDemo( "小麦2", "robin2", Person.TEACHER, Person.MALE, true ));
    ht.put("robin3", new PersonDemo( "小麦3", "robin3", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robin4", new PersonDemo( "小麦4", "robin4", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robin5", new PersonDemo( "小麦5", "robin5", Person.STUDENT, Person.MALE, false ));
    ht.put("robin6", new PersonDemo( "小麦6", "robin6", Person.STUDENT, Person.FEMALE, false ));
    ht.put("robin7", new PersonDemo( "小麦7", "robin7", Person.STUDENT, Person.MALE, true ));
    ht.put("robin8", new PersonDemo( "小麦7", "robin8", Person.STUDENT, Person.MALE, true ));
    ht.put("robin9", new PersonDemo( "小麦7", "robin9", Person.STUDENT, Person.MALE, true ));
    ht.put("robina", new PersonDemo( "小麦7", "robina", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robinb", new PersonDemo( "小麦7", "robinb", Person.STUDENT, Person.MALE, true ));
    ht.put("robinc", new PersonDemo( "小麦7", "robinc", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robind", new PersonDemo( "小麦7", "robind", Person.STUDENT, Person.FEMALE, true ));

    groups.put("分组1", ht );

    ht = new Hashtable();

    ht.put("robin10", new PersonDemo( "分组2", "robin10", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robin11", new PersonDemo( "小麦11", "robin11", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robin12", new PersonDemo( "小麦12", "robin12", Person.STUDENT, Person.MALE, true ));
    ht.put("robin13", new PersonDemo( "小麦13", "robin13", Person.STUDENT, Person.MALE, true ));
    ht.put("robin14", new PersonDemo( "小麦14", "robin14", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robin15", new PersonDemo( "小麦15", "robin15", Person.STUDENT, Person.MALE, true ));
    ht.put("robin16", new PersonDemo( "小麦16", "robin16", Person.STUDENT, Person.MALE, false ));
    ht.put("robin17", new PersonDemo( "小麦17", "robin17", Person.STUDENT, Person.MALE, true ));
    ht.put("robin18", new PersonDemo( "小麦18", "robin18", Person.STUDENT, Person.FEMALE, true ));

    groups.put("分组2", ht );

    ht = new Hashtable();

    ht.put("robin110", new PersonDemo( "分组3", "robin110", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robin111", new PersonDemo( "小麦11", "robin111", Person.STUDENT, Person.MALE, true ));
    ht.put("robin112", new PersonDemo( "小麦12", "robin112", Person.STUDENT, Person.MALE, true ));
    ht.put("robin113", new PersonDemo( "小麦13", "robin113", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robin114", new PersonDemo( "小麦14", "robin114", Person.STUDENT, Person.FEMALE, true ));
    ht.put("robin115", new PersonDemo( "小麦15", "robin115", Person.STUDENT, Person.MALE, true ));
    ht.put("robin116", new PersonDemo( "小麦16", "robin116", Person.STUDENT, Person.MALE, false ));
    PersonDemo pp= new PersonDemo( "小麦17", "robin117", Person.STUDENT, Person.FEMALE, true );
    pp.setHandUp(true);
    ht.put("robin117", pp);
    pp= new PersonDemo( "小麦18", "robin118", Person.STUDENT, Person.MALE, true );
    pp.setHandUp(true);
    pp.setExaming(true);
    ht.put("robin118", pp);
    ht.put("robin119", new PersonDemo( "小麦16", "robin119", Person.STUDENT, Person.FEMALE, false ));
    pp=new PersonDemo( "小麦17", "robin11a", Person.STUDENT, Person.MALE, true );
    pp.setExaming(true);
    ht.put("robin11a", pp);
    ht.put("robin11b", new PersonDemo( "小麦18", "robin11b", Person.AUDITOR, Person.FEMALE, true ));

    groups.put("分组3", ht );

    ht = null;

    //auditors.put( "robina", new PersonDemo( "旁听", "robina", Person.AUDITOR, Person.FEMALE, true ) );

    initValue();
  }
  public void addGroupModelListener(GroupModelListener l)
  {
    this.l = l;
    /**@todo: Implement this com.dc.eschool.group.GroupModel method*/
    //throw new java.lang.UnsupportedOperationException("Method addGroupModelListener() not yet implemented.");
  }
  public void removeGroupModelListener(GroupModelListener l)
  {
    this.l = null;
    /**@todo: Implement this com.dc.eschool.group.GroupModel method*/
    //throw new java.lang.UnsupportedOperationException("Method removeGroupModelListener() not yet implemented.");
  }
  public Person getTeacher()
  {
    return this.teacher;
    /**@todo: Implement this com.dc.eschool.group.GroupModel method*/
    //throw new java.lang.UnsupportedOperationException("Method getTeacher() not yet implemented.");
  }
  public Vector getGroupNames()
  {
    return this.groupList;

    /**@todo: Implement this com.dc.eschool.group.GroupModel method*/
    //throw new java.lang.UnsupportedOperationException("Method getGroupNames() not yet implemented.");
  }
  public Vector getStudentLoginNames(String groupName)
  {
    return (Vector)this.studentListTable.get(groupName);
    /**@todo: Implement this com.dc.eschool.group.GroupModel method*/
    //throw new java.lang.UnsupportedOperationException("Method getStudentLoginNames() not yet implemented.");
  }
  public Person getStudent(String groupName, String loginName)
  {
    Hashtable group = (Hashtable)groups.get(groupName);
    Person rv = (Person)group.get( loginName);
    return rv;
    /**@todo: Implement this com.dc.eschool.group.GroupModel method*/
    //throw new java.lang.UnsupportedOperationException("Method getStudent() not yet implemented.");
  }
  public Vector getAuditorLoginNames()
  {
    return this.auditorList;
    /**@todo: Implement this com.dc.eschool.group.GroupModel method*/
    //throw new java.lang.UnsupportedOperationException("Method getAuditorLoginNames() not yet implemented.");
  }
  public Person getAuditor(String loginName)
  {
    return (Person)this.auditors.get(loginName);
    /**@todo: Implement this com.dc.eschool.group.GroupModel method*/
    //throw new java.lang.UnsupportedOperationException("Method getAuditor() not yet implemented.");
  }

  /**
   * 初始化GroupModel
   */
  void initValue()
  {
    Object key = null;
    Object groupKey = null;
    Hashtable group = null;
    Vector studentList = null;
    for (Enumeration e = this.groups.keys() ; e.hasMoreElements() ;)
    {
      key = e.nextElement();
      this.groupList.add( key );

      group = (Hashtable)groups.get(key);
      studentList = new Vector();
      for (Enumeration ge = group.keys() ; ge.hasMoreElements() ;)
      {
        groupKey = ge.nextElement();
        studentList.add( groupKey );
      }
      this.studentListTable.put(key,studentList);
    }

    for (Enumeration e = this.auditors.keys() ; e.hasMoreElements() ;)
    {
      key = e.nextElement();
      this.auditorList.add(key);
    }

  }
  /**
   * 移动一个人从一个组到另一个组
   */
  public void movePerson( String fromGroupName, String fromLoginName, String toGroupName, String toLoginName )
  {
    if ( fromGroupName.equals(toGroupName) && fromLoginName.equals(toLoginName) ) return;

    int toIndex = -1;

    Person p = this.getStudent( fromGroupName, fromLoginName );

    Hashtable fromGroup = (Hashtable)groups.get( fromGroupName );
    Hashtable toGroup = (Hashtable)groups.get(toGroupName);

    Vector fromGroupList = (Vector)this.studentListTable.get(fromGroupName);
    Vector toGroupList = (Vector)this.studentListTable.get(toGroupName);

    if ( ! fromGroupName.equals(toGroupName) )
    {
      toGroup.put(fromLoginName, p);
      fromGroup.remove(fromLoginName);
    }

    //System.out.println("before: toGroup:" + toGroup.size() + "fromGroup:" + fromGroup.size());
    //System.out.println("before: toGroupList:" + toGroupList.size() + "fromGroupList:" + fromGroupList.size());

    fromGroupList.remove(fromLoginName);

    if ( toLoginName == null )
    {
      toGroupList.add(fromLoginName);
    }
    else
    {
      toIndex = toGroupList.indexOf( toLoginName );
      toGroupList.insertElementAt( fromLoginName, toIndex );
    }


    //System.out.println("after: toGroup:" + toGroup.size() + "fromGroup:" + fromGroup.size());
    //System.out.println("after: toGroupList:" + toGroupList.size() + "fromGroupList:" + fromGroupList.size());

    l.groupChanged();
  };

  public void personSelected(GroupComponentEvent e, Person selectedPerson)
  {
    if (selectedPerson !=null)
    {
      //selectedPerson.setHandUp( ! selectedPerson.isHandUp() );
      selectedPerson.setExaming(false);
      selectedPerson.setCalling(! selectedPerson.calling());
      l.groupChanged();
    }


  };

}