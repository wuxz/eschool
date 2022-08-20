package com.dc.eschool.systemControl;

import java.util.*;
import java.io.*;

import com.dc.eschool.*;
import com.dc.eschool.system.*;
import com.dc.eschool.exam.*;
import com.dc.eschool.inspection.*;
import com.dc.eschool.group.*;
import com.dc.eschool.listen.*;
import com.dc.eschool.communication.*;
import com.dc.eschool.util.JMFAPI;


/**
 * Title:         SystemControl类
 * Description:   该类定义了所有程序中接口和UI部分，其主要功能是通过它来获得
 *                所有程序中实例的访问权利。
 * Copyright:     Copyright (c) 2001
 * Company:       DC
 * @author        lau_hz
 * @version       1.0
 */

public class SystemControl
{
   /***************************************************
    * Interface
    ***************************************************/
   /**   课程信息接口   */
   public CourseIF        ICourse;
   /**   用户信息接口   */
   public UserIF          IUser;
   /**   考试接口      */
   public ExamIF          IExam;
   /**   监视接口      */
   public InspectionIF    IInspection;
   /**   听力接口      */
   public ListenIF        IListen;

   /***************************************************
    *  Class
    ***************************************************/
   /**   EJB Access  */
   public EJBAccess       ejbAccess;
   /**   用户分组类    */
   public UserGroup       userGroup;
   public JMFAPI jmfApi;
   public CommunicationIF communication;
   public ChatServer chatServer;
   public AnswerCompetition answerCompetition;
   public PrivateTalk privateTalk;
   public AnswerCompetitionListModel answerCompetitionListModelStudent;
   public AnswerCompetitionSequenceListModel answerCompetitionSequenceListModel;
   public OnlineTalkStudentListModel onlineTalkStudentListModel;
   public OnlineTalkGroupLIstModel onlineTalkGroupLIstModel;

   /**************************************************
    * Class implements RCemote Interface
    **************************************************/
   /**   老师远程接口的实现类   */
   public TeacherImpl teacherImpl;
   /**   学生远程接口实现类    */
   public StudentImpl studentImpl;

   /**************************************************
    * UI Class
    **************************************************/
   /**    主界面        */
   public MainFrm           mainFrm;
   /**    答卷界面      */
   public AnswerExamFrm     answerExamFrm;
   /**    考试分组界面   */
   public ExamGroupFrm      examGroupFrm;
   /**    选择考试界面   */
   public SelectExamFrm     selectExamFrm;
   /**    开始考试界面   */
   public StartExamFrm      startExamFrm;
   /**    警告界面      */
   public WarningDialog     warningDialog;
   /**    显示警告界面   */
   public ShowWarnDialog    showWarnDialog;
   /**    考勤界面      */
   public CheckOnWorkFrm    checkOnWorkFrm;
   /**    显示学生屏幕的界面  */
   public DrawImageFrm      drawImageFrm;
   /**    黑色窗口     */
   public DarkFrm           darkFrm;
   /**    等待窗口     */
   //public WaitFrm           waitFrm;
   public JDialogTalkWithTeacher jDialogTalkWithTeacher;
   public JDialogOnlineTalk      jDialogOnlineTalk;
   public JDialogOnlineTalkStudent      jDialogOnlineTalkStudent;
   public JDialogAnswerCompetition jDialogAnswerCompetition;
   public JDialogAnswerCompetitionStudent jDialogAnswerCompetitionStudent;


   /**************************************************
    * 公共变量
    **************************************************/
    /**    用户ID    */
    private  String      userID;
    /**    用户姓名   */
    private  String      userName;
    /**    用户类型   */
    private  String      userType;
    /**    用户IP   */
    private String      userIP;
    /**    老师IP   */
    private String      teacherIP;
    /**    课程ID    */
    private  String      courseID;
    /**     课程名称  */
    private  String      courseName;
    /**    老师姓名  */
    private  String      teacherName;
    /**    老师接口  */
    private  Teacher     teacherInterface;
    /**    学生用户ID和用户姓名的哈希表   */
    private  Hashtable   htStudentIDName;
    /**     学生用户ID和远程接口的哈西表  */
    private  Hashtable   htStudentIDInterface;
    /**     交卷学生列表   */
    private  Vector      vectHandin;
    /**     登录学生数     */
    private  String      loginCount;
    /**      考试状态      */
    private  boolean     startExamOrNot=false;

   /** 构造器 */
   public SystemControl()
   {
       this.ICourse=null;
       this.IUser=null;
       this.IExam=null;
       this.IInspection=null;
       this.teacherImpl=null;
       this.studentImpl=null;
       this.mainFrm=null;
       this.answerExamFrm=null;
       this.examGroupFrm=null;
       this.selectExamFrm=null;
       this.warningDialog=null;
       this.showWarnDialog=null;
       this.checkOnWorkFrm=null;
       this.drawImageFrm=null;
       this.darkFrm=null;
       this.htStudentIDInterface=new Hashtable();
       this.htStudentIDName=new Hashtable();
       this.ejbAccess=new EJBAccess();
       this.IListen=null;
       this.userGroup=null;
       //this.jmfApi=new JMFAPI(this);
       this.startExamOrNot=false;
   }

   /**************************************************
    * 公共变量方法
    **************************************************/
    /**  获得用户ID   */
    public String getUserID()
    {
        return userID;
    }
    /**  设置用户ID  */
    public void setUserID(String userID)
    {
        this.userID=userID;
    }
    /**  获得用户姓名  */
    public String getUserName()
    {
        return userName;
    }
    /**  设置用户姓名   */
    public void setUserName(String userName)
    {
        this.userName=userName;
    }
    /**  获得用户IP   */
    public String getUserIP()
    {
        return userIP;
    }
    /**  设置用户IP  */
    public void setUserIP(String userIP)
    {
        this.userIP=userIP;
    }
    /**  获得用户类型  */
    public String getUserType()
    {
        return userType;
    }
    /**  设置用户姓名   */
    public void setUserType(String userType)
    {
        this.userType=userType;
    }
    /**  获得课程ID   */
    public String getCourseID()
    {
        return courseID;
    }
    /**  设置课程ID   */
    public void setCourseID(String courseID)
    {
        this.courseID=courseID;
    }
    /**  获得课程名称  */
    public String getCourseName()
    {
        return courseName;
    }
    /**  设置课程ID   */
    public void setCourseName(String courseName)
    {
        this.courseName=courseName;
    }
    /**  获得老师姓名 */
    public String getTeacherName()
    {
        return teacherName;
    }
    /**  设置老师姓名   */
    public void setTeacherName(String teacherName)
    {
        this.teacherName=teacherName;
    }
    /**  获得老师IP */
    public String getTeacherIP()
    {
        return teacherIP;
    }
    /**  设置老师IP   */
    public void setTeacherIP(String teacherIP)
    {
        this.teacherIP=teacherIP;
    }
    /**  获得老师接口 */
    public Teacher getTeacherInterface()
    {
        return teacherInterface;
    }
    /**  设置老师接口   */
    public void setTeacherInterface(Teacher teacherInterface)
    {
        this.teacherInterface=teacherInterface;
    }
    /**  获得学生ID和用户姓名的哈希表  */
    public Hashtable getHtStudentIDName()
    {
        return htStudentIDName;
    }
    /**  设置学生ID和姓名的哈希表  */
    public void setHtStudentIDName(Hashtable htStudentIDName)
    {
        this.htStudentIDName=htStudentIDName;
    }
    /**  获得学生ID和远程接口的哈希表  */
    public Hashtable getHtStudentIDInterface()
    {
        return htStudentIDInterface;
    }
    /**  设置学生ID和远程接口的哈希表  */
    public void setHtStudentIDInterface(Hashtable htStudentIDInterface)
    {
        this.htStudentIDInterface=htStudentIDInterface;
    }
    /**  获得交卷学生的列表 */
    public Vector getHandinStudent()
    {
        return  vectHandin;
    }
    /**  设置交卷学生列表 */
    public void  setHandinStudent(Vector vectHandinStudent)
    {
        this.vectHandin=vectHandinStudent;
    }
    /**  获得全体学生数目  */
    public int getAllStudentCount()
    {
        if (htStudentIDName==null)
           return 0;
        return  htStudentIDName.size();
    }
    /**  (老师)取得上课学生的数量  */
    public int getStudentCountOnClass()
    {
        if (htStudentIDInterface==null)
            return 0;
        return htStudentIDInterface.size();
    }
    /**  设置登录学生的数量  */
    public void setLoginStudentCount(String count)
    {
        this.loginCount=count;
    }
    /**  学生取出登录学生数  */
    public String getLoginStudentCount()
    {
        return loginCount;
    }
    /**  取得上课学生的ID类表  */
    public Vector getStudentIDOnClass()
    {
        Vector  returnValue=new Vector();
        if (htStudentIDInterface==null)
        {
            return null;
        }
        else
        {
            Enumeration  enumID=htStudentIDInterface.keys();
            while (enumID.hasMoreElements())
            {
                returnValue.addElement(enumID.nextElement());
            }
            return returnValue;
        }

    }
    /**  输出到日志文件       */
    public void writeLog(String contentInfo)
    {
        GregorianCalendar gc=null;
        String            strTemp;
        String            strDate;
        try
        {
            gc=new GregorianCalendar();
            strDate=gc.get(Calendar.YEAR)+"年"+
                    (gc.get(Calendar.MONTH)+1)+"月"+
                    gc.get(Calendar.DAY_OF_MONTH)+"日";
            strTemp=gc.get(Calendar.YEAR)+"年"+
                    (gc.get(Calendar.MONTH)+1)+"月"+
                    gc.get(Calendar.DAY_OF_MONTH)+"日"+
                    gc.get(Calendar.HOUR)+"时"+
                    gc.get(Calendar.MINUTE)+"分"+
                    gc.get(Calendar.SECOND)+"秒";
            new File("../log").mkdir();
            FileOutputStream fos=new FileOutputStream("../log/"+strDate+"日志.txt",true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write("\n");
            osw.write(strTemp+":"+contentInfo);
            osw.close();
            fos.close();
        }
        catch(IOException e)
        {
            System.out.println("写入日志是出错！");
        }
    }
    /**   返回考试标示   */
    public boolean getStartExamOrNot()
    {
        return this.startExamOrNot;
    }
    /**  设置考试标识    */
    public void setStartExamOrNot(boolean startExamOrNot)
    {
        this.startExamOrNot=startExamOrNot;
    }
}
