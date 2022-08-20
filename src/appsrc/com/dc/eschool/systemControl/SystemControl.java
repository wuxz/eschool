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
 * Title:         SystemControl��
 * Description:   ���ඨ�������г����нӿں�UI���֣�����Ҫ������ͨ���������
 *                ���г�����ʵ���ķ���Ȩ����
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
   /**   �γ���Ϣ�ӿ�   */
   public CourseIF        ICourse;
   /**   �û���Ϣ�ӿ�   */
   public UserIF          IUser;
   /**   ���Խӿ�      */
   public ExamIF          IExam;
   /**   ���ӽӿ�      */
   public InspectionIF    IInspection;
   /**   �����ӿ�      */
   public ListenIF        IListen;

   /***************************************************
    *  Class
    ***************************************************/
   /**   EJB Access  */
   public EJBAccess       ejbAccess;
   /**   �û�������    */
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
   /**   ��ʦԶ�̽ӿڵ�ʵ����   */
   public TeacherImpl teacherImpl;
   /**   ѧ��Զ�̽ӿ�ʵ����    */
   public StudentImpl studentImpl;

   /**************************************************
    * UI Class
    **************************************************/
   /**    ������        */
   public MainFrm           mainFrm;
   /**    ������      */
   public AnswerExamFrm     answerExamFrm;
   /**    ���Է������   */
   public ExamGroupFrm      examGroupFrm;
   /**    ѡ���Խ���   */
   public SelectExamFrm     selectExamFrm;
   /**    ��ʼ���Խ���   */
   public StartExamFrm      startExamFrm;
   /**    �������      */
   public WarningDialog     warningDialog;
   /**    ��ʾ�������   */
   public ShowWarnDialog    showWarnDialog;
   /**    ���ڽ���      */
   public CheckOnWorkFrm    checkOnWorkFrm;
   /**    ��ʾѧ����Ļ�Ľ���  */
   public DrawImageFrm      drawImageFrm;
   /**    ��ɫ����     */
   public DarkFrm           darkFrm;
   /**    �ȴ�����     */
   //public WaitFrm           waitFrm;
   public JDialogTalkWithTeacher jDialogTalkWithTeacher;
   public JDialogOnlineTalk      jDialogOnlineTalk;
   public JDialogOnlineTalkStudent      jDialogOnlineTalkStudent;
   public JDialogAnswerCompetition jDialogAnswerCompetition;
   public JDialogAnswerCompetitionStudent jDialogAnswerCompetitionStudent;


   /**************************************************
    * ��������
    **************************************************/
    /**    �û�ID    */
    private  String      userID;
    /**    �û�����   */
    private  String      userName;
    /**    �û�����   */
    private  String      userType;
    /**    �û�IP   */
    private String      userIP;
    /**    ��ʦIP   */
    private String      teacherIP;
    /**    �γ�ID    */
    private  String      courseID;
    /**     �γ�����  */
    private  String      courseName;
    /**    ��ʦ����  */
    private  String      teacherName;
    /**    ��ʦ�ӿ�  */
    private  Teacher     teacherInterface;
    /**    ѧ���û�ID���û������Ĺ�ϣ��   */
    private  Hashtable   htStudentIDName;
    /**     ѧ���û�ID��Զ�̽ӿڵĹ�����  */
    private  Hashtable   htStudentIDInterface;
    /**     ����ѧ���б�   */
    private  Vector      vectHandin;
    /**     ��¼ѧ����     */
    private  String      loginCount;
    /**      ����״̬      */
    private  boolean     startExamOrNot=false;

   /** ������ */
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
    * ������������
    **************************************************/
    /**  ����û�ID   */
    public String getUserID()
    {
        return userID;
    }
    /**  �����û�ID  */
    public void setUserID(String userID)
    {
        this.userID=userID;
    }
    /**  ����û�����  */
    public String getUserName()
    {
        return userName;
    }
    /**  �����û�����   */
    public void setUserName(String userName)
    {
        this.userName=userName;
    }
    /**  ����û�IP   */
    public String getUserIP()
    {
        return userIP;
    }
    /**  �����û�IP  */
    public void setUserIP(String userIP)
    {
        this.userIP=userIP;
    }
    /**  ����û�����  */
    public String getUserType()
    {
        return userType;
    }
    /**  �����û�����   */
    public void setUserType(String userType)
    {
        this.userType=userType;
    }
    /**  ��ÿγ�ID   */
    public String getCourseID()
    {
        return courseID;
    }
    /**  ���ÿγ�ID   */
    public void setCourseID(String courseID)
    {
        this.courseID=courseID;
    }
    /**  ��ÿγ�����  */
    public String getCourseName()
    {
        return courseName;
    }
    /**  ���ÿγ�ID   */
    public void setCourseName(String courseName)
    {
        this.courseName=courseName;
    }
    /**  �����ʦ���� */
    public String getTeacherName()
    {
        return teacherName;
    }
    /**  ������ʦ����   */
    public void setTeacherName(String teacherName)
    {
        this.teacherName=teacherName;
    }
    /**  �����ʦIP */
    public String getTeacherIP()
    {
        return teacherIP;
    }
    /**  ������ʦIP   */
    public void setTeacherIP(String teacherIP)
    {
        this.teacherIP=teacherIP;
    }
    /**  �����ʦ�ӿ� */
    public Teacher getTeacherInterface()
    {
        return teacherInterface;
    }
    /**  ������ʦ�ӿ�   */
    public void setTeacherInterface(Teacher teacherInterface)
    {
        this.teacherInterface=teacherInterface;
    }
    /**  ���ѧ��ID���û������Ĺ�ϣ��  */
    public Hashtable getHtStudentIDName()
    {
        return htStudentIDName;
    }
    /**  ����ѧ��ID�������Ĺ�ϣ��  */
    public void setHtStudentIDName(Hashtable htStudentIDName)
    {
        this.htStudentIDName=htStudentIDName;
    }
    /**  ���ѧ��ID��Զ�̽ӿڵĹ�ϣ��  */
    public Hashtable getHtStudentIDInterface()
    {
        return htStudentIDInterface;
    }
    /**  ����ѧ��ID��Զ�̽ӿڵĹ�ϣ��  */
    public void setHtStudentIDInterface(Hashtable htStudentIDInterface)
    {
        this.htStudentIDInterface=htStudentIDInterface;
    }
    /**  ��ý���ѧ�����б� */
    public Vector getHandinStudent()
    {
        return  vectHandin;
    }
    /**  ���ý���ѧ���б� */
    public void  setHandinStudent(Vector vectHandinStudent)
    {
        this.vectHandin=vectHandinStudent;
    }
    /**  ���ȫ��ѧ����Ŀ  */
    public int getAllStudentCount()
    {
        if (htStudentIDName==null)
           return 0;
        return  htStudentIDName.size();
    }
    /**  (��ʦ)ȡ���Ͽ�ѧ��������  */
    public int getStudentCountOnClass()
    {
        if (htStudentIDInterface==null)
            return 0;
        return htStudentIDInterface.size();
    }
    /**  ���õ�¼ѧ��������  */
    public void setLoginStudentCount(String count)
    {
        this.loginCount=count;
    }
    /**  ѧ��ȡ����¼ѧ����  */
    public String getLoginStudentCount()
    {
        return loginCount;
    }
    /**  ȡ���Ͽ�ѧ����ID���  */
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
    /**  �������־�ļ�       */
    public void writeLog(String contentInfo)
    {
        GregorianCalendar gc=null;
        String            strTemp;
        String            strDate;
        try
        {
            gc=new GregorianCalendar();
            strDate=gc.get(Calendar.YEAR)+"��"+
                    (gc.get(Calendar.MONTH)+1)+"��"+
                    gc.get(Calendar.DAY_OF_MONTH)+"��";
            strTemp=gc.get(Calendar.YEAR)+"��"+
                    (gc.get(Calendar.MONTH)+1)+"��"+
                    gc.get(Calendar.DAY_OF_MONTH)+"��"+
                    gc.get(Calendar.HOUR)+"ʱ"+
                    gc.get(Calendar.MINUTE)+"��"+
                    gc.get(Calendar.SECOND)+"��";
            new File("../log").mkdir();
            FileOutputStream fos=new FileOutputStream("../log/"+strDate+"��־.txt",true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write("\n");
            osw.write(strTemp+":"+contentInfo);
            osw.close();
            fos.close();
        }
        catch(IOException e)
        {
            System.out.println("д����־�ǳ���");
        }
    }
    /**   ���ؿ��Ա�ʾ   */
    public boolean getStartExamOrNot()
    {
        return this.startExamOrNot;
    }
    /**  ���ÿ��Ա�ʶ    */
    public void setStartExamOrNot(boolean startExamOrNot)
    {
        this.startExamOrNot=startExamOrNot;
    }
}
