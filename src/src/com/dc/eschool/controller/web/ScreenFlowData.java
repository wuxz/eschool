package com.dc.eschool.controller.web;

import java.io.Serializable;

import java.util.HashMap;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class ScreenFlowData implements java.io.Serializable
{
    private HashMap exceptionMappings;
    private HashMap screenDefinitionMappings;
    private String defaultScreen = null;
    private String signinScreen = null;
    private String teacherHome = null;
    private String studentHome = null;
    private String adminHome = null;
    private String signinErrorScreen = null;

    public ScreenFlowData (HashMap exceptionMappings,
                           HashMap screenDefinitionMappings,
                           String defaultScreen,
                           String signinScreen,
                           String teacherHome,
                           String studentHome,
                           String adminHome,
                           String signinErrorScreen)
    {
        this.exceptionMappings = exceptionMappings;
        this.screenDefinitionMappings = screenDefinitionMappings;
        this.defaultScreen = defaultScreen;
        this.signinScreen = signinScreen;
        this.teacherHome = teacherHome;
        this.studentHome = studentHome;
        this.adminHome = adminHome;
        this.signinErrorScreen = signinErrorScreen;
    }

    public String getSigninScreen()
    {
        return signinScreen;
    }

    public String getSigninErrorScreen()
    {
        return signinErrorScreen;
    }

    public String getDefaultScreen()
    {
        return defaultScreen;
    }

    public HashMap getScreenDefinitionMappings()
    {
        return screenDefinitionMappings;
    }

    public HashMap getExceptionMappings()
    {
        return exceptionMappings;
    }

    public String toString()
    {
        return "ScreenFlowData: {defaultScreen=" + defaultScreen + ", " +
                    " signinScreen=" + signinScreen + ", " +
                    " screenDefinitionMappings=" + screenDefinitionMappings + ", " +
                    " exceptionMappings=" + exceptionMappings + "}";
    }
    public String getAdminHome()
    {
      return adminHome;
    }
    public String getStudentHome()
    {
      return studentHome;
    }
    public String getTeacherHome()
    {
      return teacherHome;
    }
}