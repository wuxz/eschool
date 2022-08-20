package com.dc.eschool.util;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * This class is the central location to store the internal
 * JNDI names of various entities. Any change here should
 * also be reflected in the deployment descriptors.
 */
public interface JNDINames
{

  //
  // JNDI names of EJB home objects
  //

  /** JNDI name of the home interface of EJBControllerSF EJB */
  public static final String EJBCTRL_EJBHOME =
		"java:comp/env/ejb/Controller";
  public static final String SESSION_MANAGER_EJBHOME =
		"java:comp/env/ejb/SessionManager";
  public static final String STUDENTEXAMCONTENT_EJBHOME =
		"java:comp/env/ejb/StudentExamContent";
  public static final String STUDENTEXAMCONTENT_MANAGER_EJBHOME =
		"java:comp/env/ejb/StudentExamContentManager";
  public static final String HOMEWORK_EJBHOME =
		"java:comp/env/ejb/HomeWork";
  public static final String HOMEWORK_MANAGER_EJBHOME =
		"java:comp/env/ejb/HomeWorkManager";
  public static final String HWCONTENT_EJBHOME =
		"java:comp/env/ejb/HomeWorkContent";
  public static final String HWCONTENT_MANAGER_EJBHOME =
		"java:comp/env/ejb/HomeWorkContentManager";
  public static final String PROJECT_EJBHOME =
		"java:comp/env/ejb/Project";
  public static final String PROJECT_CONTENT_EJBHOME =
		"java:comp/env/ejb/ProjectContent";
  public static final String USERS_EJBHOME =
		"java:comp/env/ejb/Users";
  public static final String USERS_MANAGER_EJBHOME =
		"java:comp/env/ejb/UsersManager";
  public static final String ECLASS_EJBHOME =
		"java:comp/env/ejb/EClass";
  public static final String ECLASS_MANAGER_EJBHOME =
		"java:comp/env/ejb/EClassManager";
  public static final String COURSE_EJBHOME =
		"java:comp/env/ejb/Course";
  public static final String COURSE_MANAGER_EJBHOME =
		"java:comp/env/ejb/CourseManager";
  public static final String COURSESTUDENT_EJBHOME =
		"java:comp/env/ejb/CourseStudent";
  public static final String COURSESTUDENT_MANAGER_EJBHOME =
		"java:comp/env/ejb/CourseStudentManager";
  public static final String SUBJECT_EJBHOME =
		"java:comp/env/ejb/Subject";
  public static final String SUBJECT_MANAGER_EJBHOME =
		"java:comp/env/ejb/SubjectManager";
  public static final String PMEJBHOME_EJBHOME =
		"java:comp/env/ejb/ProjectManager";
  public static final String ANSWERITEM_EJBHOME =
		"java:comp/env/ejb/AnswerItem";
  public static final String ANSWERITEM_MANAGER_EJBHOME =
		"java:comp/env/ejb/AnswerItemManager";
  public static final String EXAMINATIONSCORE_EJBHOME =
		"java:comp/env/ejb/ExaminationScore";
  public static final String EXAMINATIONSCORE_MANAGER_EJBHOME =
		"java:comp/env/ejb/ExaminationScoreManager";
  public static final String SCHOOLRESOURCE_EJBHOME =
		"java:comp/env/ejb/SchoolResource";
  public static final String SCHOOLRESOURCE_MANAGEE_EJBHOME =
		"java:comp/env/ejb/SchoolResourceManager";
  public static final String SCORESTATISTIC_EJBHOME =
		"java:comp/env/ejb/ScoreStatistic";
  public static final String SCORESTATISTIC_MANAGER_EJBHOME =
		"java:comp/env/ejb/ScoreStatisticManager";
   public static final String TESTRESULTSITEM_EJBHOME =
		"java:comp/env/ejb/TestResultsItem";
  public static final String TESTRESULTSITEM_MANAGER_EJBHOME =
		"java:comp/env/ejb/TestResultsItemManager";
  public static final String CONTENT_EJBHOME =
		"java:comp/env/ejb/Content";
  public static final String CONTENTMGR_EJBHOME =
		"java:comp/env/ejb/ContentManager";
  // server type definition
  public static final String SERVER_TYPE =
		"java:comp/env/server/ServerType";

  //DataSource name
  public static final String ESCHOOL_DATASOURCE = "java:comp/env/jdbc/Eschool";

}
