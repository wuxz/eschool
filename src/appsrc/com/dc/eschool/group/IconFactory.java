package com.dc.eschool.group;

import javax.swing.ImageIcon;

/**
 * Title:        Group Test
 * Description:  ∂¡»°Icon
 * Copyright:    Copyright (c) 2001
 * Company:      Double chain
 * @author Robin Liu
 * @version 1.0
 */

public class IconFactory
{
  static Class c = IconFactory.class;
  public static final ImageIcon teacherMaleOnIcon = new ImageIcon( c.getResource("teacher_male_on.gif") );
  public static final ImageIcon teacherMaleOffIcon = new ImageIcon( c.getResource("teacher_male_off.gif") );
  public static final ImageIcon teacherFemaleOnIcon = new ImageIcon( c.getResource("teacher_female_on.gif") );
  public static final ImageIcon teacherFemaleOffIcon = new ImageIcon( c.getResource("teacher_female_off.gif") );

  public static final ImageIcon studentMaleOnIcon = new ImageIcon( c.getResource("student_male_on.gif") );
  public static final ImageIcon studentMaleOffIcon = new ImageIcon( c.getResource("student_male_off.gif") );
  public static final ImageIcon studentFemaleOnIcon = new ImageIcon( c.getResource("student_female_on.gif") );
  public static final ImageIcon studentFemaleOffIcon = new ImageIcon( c.getResource("student_female_off.gif") );

  public static final ImageIcon auditorOnIcon = new ImageIcon( c.getResource("auditor_on.gif") );
  public static final ImageIcon auditorOffIcon = new ImageIcon( c.getResource("auditor_off.gif") );

  public static final ImageIcon handupIcon = new ImageIcon( c.getResource("handup.gif") );
  public static final ImageIcon examingIcon = new ImageIcon( c.getResource("examing.gif") );
  public static final ImageIcon callingIcon = new ImageIcon( c.getResource("call.gif") );
}