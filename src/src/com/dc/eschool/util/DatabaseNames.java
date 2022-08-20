package com.dc.eschool.util;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * This interface stores the name of all the database tables.
 * The String constants in this class should be used by other
 * classes instead of hardcoding the name of a database table
 * into the source code.
 */
public interface DatabaseNames
{
    public static final String ANSWERITEM_TABLE = "AnswerItem";
    public static final String CLASS_TABLE   = "Class";
    public static final String CONTENT_TABLE = "Content";
    public static final String COURSE_TABLE   = "Course";
    public static final String COURSESTUDENT_TABLE   = "CourseStudent";
    public static final String EXAMINATIONSCORE_TABLE = "ExaminationScore";
    public static final String HOMEWORK_TABLE   = "HomeWork";
    public static final String HOMEWORKCONTENT_TABLE   = "HomeWorkContent";
    public static final String PROJECT_TABLE = "Project";
    public static final String PROJ_CONT_TABLE = "ProjectContent";
    public static final String SCHOOLRESOURCE_TABLE = "SchoolResourse";
    public static final String SCORESTATISTIC_TABLE = "ScoreStatistic";
    public static final String STUDENTEXAMCONTENT_TABLE   = "StudentExamContent";
    public static final String SUBJECT_TABLE   = "Subject";
    public static final String TESTRESULTSITEM_TABLE = "TestResultItem";
    public static final String USERS_TABLE   = "Users";
}