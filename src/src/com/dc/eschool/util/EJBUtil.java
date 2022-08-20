package com.dc.eschool.util;

import java.rmi.RemoteException;
import javax.rmi.PortableRemoteObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.CreateException;

//import ejbHome classes here.
import com.dc.eschool.controller.ejb.EJBControllerSFHome;
import com.dc.eschool.homework.ejb.HomeWorkEBHome;
import com.dc.eschool.homework.mgrbean.HomeWorkSLHome;
import com.dc.eschool.homeworkcontent.ejb.HWContentEBHome;
import com.dc.eschool.homeworkcontent.mgrbean.HWContentSLHome;
import com.dc.eschool.project.ejb.ProjectEBHome;
import com.dc.eschool.project.mgrbean.ProjectMgrSLHome;
import com.dc.eschool.projectcontent.ejb.ProjectContentEBHome;
import com.dc.eschool.users.ejb.UsersEBHome;
import com.dc.eschool.users.mgrbean.UsersSLHome;
import com.dc.eschool.eclass.ejb.EClassEBHome;
import com.dc.eschool.eclass.mgrbean.EClassSLHome;
import com.dc.eschool.subject.ejb.SubjectEBHome;
import com.dc.eschool.subject.mgrbean.SubjectSLHome;
import com.dc.eschool.course.ejb.CourseEBHome;
import com.dc.eschool.course.mgrbean.CourseSLHome;
import com.dc.eschool.coursestudent.ejb.CourseStudentEBHome;
import com.dc.eschool.coursestudent.mgrbean.CourseStudentSLHome;
import com.dc.eschool.answeritem.ejb.AnswerItemEBHome;
import com.dc.eschool.answeritem.mgrbean.AnswerItemSLHome;
import com.dc.eschool.testresultsitem.mgrbean.TestResultsItemSLHome;
import com.dc.eschool.testresultsitem.ejb.TestResultsItemEBHome;
import com.dc.eschool.examinationscore.ejb.ExaminationScoreEBHome;
import com.dc.eschool.examinationscore.mgrbean.ExaminationScoreSLHome;
import com.dc.eschool.schoolresource.ejb.SchoolResourceEBHome;
import com.dc.eschool.scorestatistic.ejb.ScoreStatisticEBHome;
import com.dc.eschool.scorestatistic.mgrbean.ScoreStatisticSLHome;
import com.dc.eschool.content.ejb.ContentEBHome;
import com.dc.eschool.content.mgrbean.ContentMgrSLHome;
import com.dc.eschool.schoolresource.mgrbean.SchoolResourceSLHome;
import com.dc.eschool.sessiondata.SessionSLHome;
import com.dc.eschool.studentexamcontent.ejb.StudentExamContentEBHome;
import com.dc.eschool.studentexamcontent.mgrbean.StudentExamContentSLHome;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */


/**
 * This is a utility class for obtaining EJB references.
 */
public final class EJBUtil
{

	//here is method template
	/*
	public static EJBHome getEJBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.EJB_EJBHOMENAME);
			return (EJBHome)
				PortableRemoteObject.narrow(objref, EJBHome.class);
	}
	*/

	public static com.dc.eschool.controller.ejb.EJBControllerSFHome getEJBCtrlHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.EJBCTRL_EJBHOME);
			return (EJBControllerSFHome)PortableRemoteObject.narrow(objref, EJBControllerSFHome.class);
	}

	public static HomeWorkEBHome getHomeWorkEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.HOMEWORK_EJBHOME);
			return (HomeWorkEBHome)
				PortableRemoteObject.narrow(objref, HomeWorkEBHome.class);
	}

	public static ProjectEBHome getProjectEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.PROJECT_EJBHOME);
			return (ProjectEBHome)
				PortableRemoteObject.narrow(objref, ProjectEBHome.class);
	}

	public static ProjectContentEBHome getProjectContentEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.PROJECT_CONTENT_EJBHOME);
			return (ProjectContentEBHome)
				PortableRemoteObject.narrow(objref, ProjectContentEBHome.class);
	}

	public static UsersEBHome getUsersEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.USERS_EJBHOME);
			return (UsersEBHome)
				PortableRemoteObject.narrow(objref, UsersEBHome.class);
	}

	public static EClassEBHome getEClassEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.ECLASS_EJBHOME);
			return (EClassEBHome)
				PortableRemoteObject.narrow(objref, EClassEBHome.class);
	}

	public static SubjectEBHome getSubjectEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.SUBJECT_EJBHOME);
			return (SubjectEBHome)
				PortableRemoteObject.narrow(objref, SubjectEBHome.class);
	}

	public static CourseEBHome getCourseEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.COURSE_EJBHOME);
			return (CourseEBHome)
				PortableRemoteObject.narrow(objref, CourseEBHome.class);
	}

	public static CourseStudentEBHome getCourseStudentEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.COURSESTUDENT_EJBHOME);
			return (CourseStudentEBHome)
				PortableRemoteObject.narrow(objref, CourseStudentEBHome.class);
	}

	public static HWContentEBHome getHWContentEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.HWCONTENT_EJBHOME);
			return (HWContentEBHome)
				PortableRemoteObject.narrow(objref, HWContentEBHome.class);
	}

	public static HWContentSLHome getHWContentSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.HWCONTENT_MANAGER_EJBHOME);
			return (HWContentSLHome)
				PortableRemoteObject.narrow(objref, HWContentSLHome.class);
	}

	public static ProjectMgrSLHome getPMSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.PMEJBHOME_EJBHOME);
			return (ProjectMgrSLHome)
				PortableRemoteObject.narrow(objref, ProjectMgrSLHome.class);
	}

	public static HomeWorkSLHome getHomeWorkSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.HOMEWORK_MANAGER_EJBHOME);
			return (HomeWorkSLHome)
				PortableRemoteObject.narrow(objref, HomeWorkSLHome.class);
	}

	public static UsersSLHome getUsersSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.USERS_MANAGER_EJBHOME);
			return (UsersSLHome)
				PortableRemoteObject.narrow(objref, UsersSLHome.class);
	}

	public static EClassSLHome getEClassSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.ECLASS_MANAGER_EJBHOME);
			return (EClassSLHome)
				PortableRemoteObject.narrow(objref, EClassSLHome.class);
	}

	public static SubjectSLHome getSubjectSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.SUBJECT_MANAGER_EJBHOME);
			return (SubjectSLHome)
				PortableRemoteObject.narrow(objref, SubjectSLHome.class);
	}

	public static CourseSLHome getCourseSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.COURSE_MANAGER_EJBHOME);
			return (CourseSLHome)
				PortableRemoteObject.narrow(objref, CourseSLHome.class);
	}

	public static CourseStudentSLHome getCourseStudentSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.COURSESTUDENT_MANAGER_EJBHOME);
			return (CourseStudentSLHome)
				PortableRemoteObject.narrow(objref, CourseStudentSLHome.class);
	}

	public static AnswerItemEBHome getAnswerItemEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.ANSWERITEM_EJBHOME);
			return (AnswerItemEBHome)
				PortableRemoteObject.narrow(objref, AnswerItemEBHome.class);
	}

	public static AnswerItemSLHome getAnswerItemSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.ANSWERITEM_MANAGER_EJBHOME);
			return (AnswerItemSLHome)
				PortableRemoteObject.narrow(objref, AnswerItemSLHome.class);
	}

	public static TestResultsItemSLHome getTestResultsItemSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.TESTRESULTSITEM_MANAGER_EJBHOME);
			return (TestResultsItemSLHome)
				PortableRemoteObject.narrow(objref, TestResultsItemSLHome.class);
	}

	public static TestResultsItemEBHome getTestResultsItemEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.TESTRESULTSITEM_EJBHOME);
			return (TestResultsItemEBHome)
				PortableRemoteObject.narrow(objref, TestResultsItemEBHome.class);
	}

	public static ExaminationScoreEBHome getExaminationScoreEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.EXAMINATIONSCORE_EJBHOME);
			return (ExaminationScoreEBHome)
				PortableRemoteObject.narrow(objref, ExaminationScoreEBHome.class);
	}

	public static ExaminationScoreSLHome getExaminationScoreSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.EXAMINATIONSCORE_MANAGER_EJBHOME);
			return (ExaminationScoreSLHome)
				PortableRemoteObject.narrow(objref, ExaminationScoreSLHome.class);
	}

	public static ScoreStatisticEBHome getScoreStatisticEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.SCORESTATISTIC_EJBHOME);
			return (ScoreStatisticEBHome)
				PortableRemoteObject.narrow(objref, ScoreStatisticEBHome.class);
	}

	public static ScoreStatisticSLHome getScoreStatisticSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.SCORESTATISTIC_MANAGER_EJBHOME);
			return (ScoreStatisticSLHome)
				PortableRemoteObject.narrow(objref, ScoreStatisticSLHome.class);
	}

	public static ContentEBHome getContentHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.CONTENT_EJBHOME);
			return (ContentEBHome)
				PortableRemoteObject.narrow(objref, ContentEBHome.class);
	}

	public static SessionSLHome getSessionSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.SESSION_MANAGER_EJBHOME);
			return (SessionSLHome)
				PortableRemoteObject.narrow(objref, SessionSLHome.class);
	}

	public static ContentMgrSLHome getContentMgrHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.CONTENTMGR_EJBHOME);
			return (ContentMgrSLHome)
				PortableRemoteObject.narrow(objref, ContentMgrSLHome.class);
	}

	public static SchoolResourceSLHome getSchoolResourceSLHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.SCHOOLRESOURCE_MANAGEE_EJBHOME);
			return (SchoolResourceSLHome)
				PortableRemoteObject.narrow(objref, SchoolResourceSLHome.class);
	}

	public static SchoolResourceEBHome getSchoolResourceEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.SCHOOLRESOURCE_EJBHOME);
			return (SchoolResourceEBHome)
				PortableRemoteObject.narrow(objref, SchoolResourceEBHome.class);
	}

	public static StudentExamContentEBHome getStudentExamContentEBHome() throws javax.naming.NamingException
	{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.STUDENTEXAMCONTENT_EJBHOME);
			return (StudentExamContentEBHome)
				PortableRemoteObject.narrow(objref, StudentExamContentEBHome.class);
	}

	public static StudentExamContentSLHome getStudentExamContentSLHome() throws javax.naming.NamingException
	{
                        InitialContext initial = new InitialContext();
			Object objref = initial.lookup(JNDINames.STUDENTEXAMCONTENT_MANAGER_EJBHOME);
			return (StudentExamContentSLHome)
				PortableRemoteObject.narrow(objref, StudentExamContentSLHome.class);
	}
}
