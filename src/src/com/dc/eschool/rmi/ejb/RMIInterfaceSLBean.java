package com.dc.eschool.rmi.ejb;

import java.net.URL;
import java.rmi.*;
import java.util.*;

import javax.ejb.*;

import com.dc.eschool.answeritem.mgrbean.*;
import com.dc.eschool.answeritem.model.AnswerItemModel;
import com.dc.eschool.content.mgrbean.*;
import com.dc.eschool.content.model.ContentModel;
import com.dc.eschool.course.model.CourseModel;
import com.dc.eschool.course.mgrbean.*;
import com.dc.eschool.DataModel.*;
import com.dc.eschool.project.mgrbean.*;
import com.dc.eschool.project.model.ProjectModel;
import com.dc.eschool.studentexamcontent.mgrbean.*;
import com.dc.eschool.studentexamcontent.model.StudentExamContentModel;
import com.dc.eschool.users.model.*;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.testresultsitem.mgrbean.*;
import com.dc.eschool.testresultsitem.model.*;
import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class RMIInterfaceSLBean implements SessionBean
{
	private SessionContext sessionContext;
	private UsersSLHome usersSLHome=null;
	private UsersSL usersSL=null;
	private CourseSLHome courseSLHome=null;
	private CourseSL courseSL=null;
	private ProjectMgrSLHome projectSLHome=null;
	private ProjectMgrSL projectSL=null;
	private ContentMgrSLHome contentSLHome=null;
	private ContentMgrSL contentSL=null;
	private StudentExamContentSLHome secSLHome=null;
	private StudentExamContentSL secSL=null;
	private AnswerItemSLHome answerItemSLHome=null;
	private AnswerItemSL answerItemSL=null;
	private TestResultsItemSLHome testResultsItemSLHome = null;
	private TestResultsItemSL testResultsItemSL = null;
	private static Vector loginInfo= new Vector();
		private PropertiesManager properMgr=new PropertiesManager();

	public RMIInterfaceSLBean()
	{
	}
	public void ejbCreate()
	{
				try
		{
			usersSLHome=EJBUtil.getUsersSLHome();
			usersSL=usersSLHome.create();

			courseSLHome=EJBUtil.getCourseSLHome();
			courseSL=courseSLHome.create();

			projectSLHome=EJBUtil.getPMSLHome();
			projectSL=projectSLHome.create();

			contentSLHome=EJBUtil.getContentMgrHome();
			contentSL=contentSLHome.create();

			secSLHome=EJBUtil.getStudentExamContentSLHome();
			secSL=secSLHome.create();

			answerItemSLHome=EJBUtil.getAnswerItemSLHome();
			answerItemSL=answerItemSLHome.create();

			testResultsItemSLHome = EJBUtil.getTestResultsItemSLHome();
			testResultsItemSL = testResultsItemSLHome.create();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in initiating ESchoolRMI:" + e);
		}
	}
	public void ejbRemove() throws RemoteException
	{
	}
	public void ejbActivate() throws RemoteException
	{
	}
	public void ejbPassivate() throws RemoteException
	{
	}
	public void setSessionContext(SessionContext sessionContext) throws RemoteException
	{
	  this.sessionContext = sessionContext;
	}

	 /**
	  * ����û���������Ϣ����Ҫ�������û�ID���û��������û��Ա�
	  * �û��������ڡ��û��绰���롢�û������͵ȡ�
	  */
   public Collection getUserInfo(String userID)
   {
	  ArrayList al = new ArrayList();

	  try
	  {
	  UsersModel userModel=usersSL.getUser(userID);
		  if (userModel!=null)
		  {
			UserInfoModel userInfoModel = new UserInfoModel();
			userInfoModel.setUserID(userID);
			userInfoModel.setUserName(userModel.getName());
			userInfoModel.setUserGender(userModel.getGender());
			userInfoModel.setUserBirthday(userModel.getBirthday());
			userInfoModel.setUserType(userModel.getUserType());
			userInfoModel.setUserTelephone(userModel.getTel());
			al.add(userInfoModel);
		  }
	  }catch(Exception e)
	  {
	  System.out.println("Exception in getUserInfo from ESchoolRMI:" + e);
	  }
	  return al;
   }

   /**
	* ͨ���û�������û�����Ϣ����Ҫ�������û�ID���û�������
	* �û��Ա��û��������ڡ��û��绰���롢�û������͵ȡ�
	*/
   public Collection getUserInfoByName(String userName)
   {
   return null;
   }

   /** �����޸ĺ��û�����Ϣ  */
   public void saveUserInfo(String userID,String name, String gender, String birthday, String userType, String telephone)
   {
	  try
	  {
		UsersModel um=new UsersModel();
		um.setUserID(new Integer(userID));
		um.setName(name);
		um.setGender(gender);
		um.setBirthday(birthday);
		um.setUserType(userType);
		um.setTel(telephone);
		um.setLastModifyBy(new Integer(userID));
		usersSL.updateUserByApp(um);
	  }catch(Exception e)
	  {
		  System.out.println(e);
	  }
   }

   /** ������еĿγ���Ϣ����Ҫ�������γ�ID���γ����ơ�
	*  �γ̵Ŀ�ʼ���ںͽ������ڡ��γ̵���Ϣ���γ̵�״̬��
	*/
   public Collection getCourseAllInfo(String courseID)
   {
	  ArrayList al = new ArrayList();
	  try
	  {
		CourseModel cm=new CourseModel();
		cm=courseSL.getCourse(courseID);
		CourseInfoModel cim=new CourseInfoModel();
		cim.setCourseID(cm.getCourseID().toString());
		cim.setCourseName(cm.getCourseName());
		cim.setCourseInfo(cm.getInfo());
		cim.setStartDate(cm.getStartDate());
		cim.setEndDate(cm.getEndDate());
		cim.setState(cm.getState());
		al.add(cim);
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return al;
   }

   /** ��ö�Ӧ�γ̵Ŀ��⡣����������ID���������ơ�ʱ�䡢˵���� */
   public Collection getExamInfo(String courseID)
   {
	  Collection collection=null;
	  Iterator iterator=null;
	  ArrayList al= new ArrayList();
	  try
	  {
		collection=projectSL.getProjectInfoByApp(courseID,"test");
		iterator=collection.iterator();
		while(iterator.hasNext())
		{
		  ProjectModel pm=(ProjectModel)iterator.next();
		  ExamProjectInfoModle epim= new ExamProjectInfoModle();
		  epim.setProjectID(pm.getProjectID().toString());
		  epim.setProjectName(pm.getName());
		  epim.setProjectInfo(pm.getInfo());
		  epim.setProjectDate(pm.getStartDate());
		  al.add(epim);
		}
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return al;
   }

   /** ȡ����Ӧ���Ե��������⡣����������ID������ֽ��״̬�ȡ� */
   public Collection getContentInfo(String projectID)
   {
	  Collection collection=null;
	  Iterator iterator=null;
	  ArrayList al= new ArrayList();
	  try
	  {
		collection=contentSL.getContentByApp(projectID);
		iterator=collection.iterator();
		while(iterator.hasNext())
		{
		  ContentModel cm=(ContentModel)iterator.next();
		  ExamContentInfoModel ecim= new ExamContentInfoModel();
		  ecim.setContentID(cm.getContentID().toString());
		  ecim.setContentName(cm.getName());
		  ecim.setContentState(cm.getState());
		  ecim.setContentAnswerPaper(cm.getHasAnswerItem());
		  al.add(ecim);
		}
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return al;
   }

   /** ���濼�Ե����� modify all the proejcts' state*/
   public void saveExameContent(Vector projectID)
   {
	  try
	  {
		for(int i=0;i<projectID.size();i++)
		{
		  projectSL.updateState(projectID.elementAt(i).toString(),"Ԥ��");
		}
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
   }

   /** �ı����ݿ��п��Ե�״̬  */
   public void changeProjectState(String projectID,String state)
   {
	  try
	  {
		projectSL.updateState(projectID,state);
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
   }

   /** ���������б� fetch data from "MODLFY SATATE"*/
   public Collection getContentList(String courseID)
   {
	  Collection collection=null;
	  Iterator iterator=null;
	  ArrayList al= new ArrayList();

	  try
	  {
		collection=contentSL.getCheckedContentByApp(courseID,"test");
		iterator=collection.iterator();
		while(iterator.hasNext())
		{
		  ContentModel cm=(ContentModel)iterator.next();
		  ExamContentInfoModel ecim= new ExamContentInfoModel();
		  ecim.setContentID(cm.getContentID().toString());
		  ecim.setContentName(cm.getName());
		  ecim.setContentState(cm.getState());
		  ecim.setContentAnswerPaper(cm.getHasAnswerItem());
		  al.add(ecim);
		}
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return al;
   }

   /** ɾ������  */
   public void deleteContent(String ContentID)
   {
   }

   /** ����ѧ�����������Ϣ  new */
   public void saveContentStudent(Vector StudentName, String contentID,String courseID,String teacherID)
   {
	  try
	  {
		for(int i=0;i<StudentName.size();i++)
		{
		  StudentExamContentModel secm=new StudentExamContentModel();
		  secm.setUserID(new Integer(StudentName.elementAt(i).toString()));
		  secm.setContentID(new Integer(contentID));
		  secm.setCourseID(new Integer(courseID));
		  secm.setCreateBy(new Integer(teacherID));
		  secm.setLastModifyBy(new Integer(teacherID));
		  secSL.insertStudentExamContent(secm);
		}
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
   }

   /** ѧ����������URL�� new */
   public URL getExamContent(String contentID)
   {
	  URL returnValue=null;
	  try
	  {
				String docURL=contentSL.getContent(contentID).getDocURL();
				String url_str=null;
				if (docURL!=null)
				  url_str=properMgr.getFtpPath()+contentSL.getContent(contentID).getDocURL();
				if(url_str!=null)
		  returnValue=new URL(url_str);
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return returnValue;
   }

   /** ѧ����������URL�� */
   public URL getExamContent(String userID, String courseID)
   {
	   URL returnValue=null;
	   try
	   {
				   Integer contentID=secSL.getContentIDByApp(userID,courseID);
				   if(contentID!=null)
				   {
					  returnValue=getExamContent(contentID.toString());
				   }
	   }
	   catch(Exception re)
	   {
		   re.printStackTrace();
	   }
	   return returnValue;
   }

   /**  ����Ƿ�ѡ��������  */
   public boolean selectExamOrNot(String courseID)
   {
	  boolean returnValue=false;
	  try
	  {
		returnValue=projectSL.selectExamOrNotForApp(courseID,"test");
	  }catch(Exception e)
	  {
		System.out.println(e);
				return false;
	  }
	  return returnValue;
   }
   /**  ����Ƿ�������������  */
   public boolean examGroupOrNot(String courseID)
   {
	  boolean returnValue=false;
	  try
	  {
		returnValue=secSL.isGrouped(courseID);
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return returnValue;
   }

   /** ȡ�ÿ�����ľ�����Ϣ���������ɴ��  */
   public Collection getQuestionInfo(String userID, String courseID)
   {
	  Collection collection=null;
	  Iterator iterator=null;
	  ArrayList al= new ArrayList();
	  try
	  {
		String contentID=secSL.getContentIDByApp(userID,courseID).toString();
		ListChunk lc=answerItemSL.searchAnswerItem(contentID,0,0);
		collection=lc.getCollection();
		iterator=collection.iterator();
		while(iterator.hasNext())
		{
		  AnswerItemModel am=(AnswerItemModel)iterator.next();
		  AnswerExamItemModel aiim=new AnswerExamItemModel();
		  aiim.setAnswerKey("");
		  aiim.setAnswerType(am.getType());
		  aiim.setExamCode(am.getItemNumber().toString());
		  aiim.setItemCount(Integer.valueOf(am.getAnswerNumber()).intValue());
		  al.add(aiim);
		}
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return al;
   }

   /** �����Ծ����ݿ� */
   public void saveExamAnsweerPaper(String userID, String courseID, Hashtable answeerItem)
   {
		if (answeerItem == null || testResultsItemSL == null)
			return;

		try
		{
			Integer projectID = null;
			Collection projects = projectSL.getProjectInfoByApp(courseID, "test");
			Iterator itProjects = projects.iterator();
			while (itProjects.hasNext())
			{
				ProjectModel pm = (ProjectModel)itProjects.next();
				if ("Ԥ��".equals(pm.getState()))
				{
					projectID = pm.getProjectID();

					break;
				}
			}

			if (projectID == null)
				return;

			Integer contentID=secSL.getContentIDByApp(userID,courseID);
			Iterator it = answeerItem.entrySet().iterator();
			Vector models = new Vector();

			while (it.hasNext())
			{
				TestResultsItemModel tm = new TestResultsItemModel();
				tm.setAnswerMark("");
				tm.setContentID(contentID);
				tm.setCreateBy(new Integer(userID));
				tm.setLastModifyBy(new Integer(userID));
				tm.setRight("wrong");
				tm.setStudent(new Integer(userID));
				tm.setProjectID(projectID);

				Hashtable.Entry entry = (Hashtable.Entry)it.next();
				String itemNumber = (String)entry.getKey();
				String answer = (String)entry.getValue();

				String clause =
					contentID.toString() +
					" and " +
					InterpretSQL.encodeEndSign("ItemNumber") +
					" = "
					+ itemNumber;
				ListChunk lc=answerItemSL.searchAnswerItem(clause,0,0);

				Iterator itAnswer = lc.getCollection().iterator();
				if (itAnswer.hasNext())
				{
					AnswerItemModel am=(AnswerItemModel)itAnswer.next();
					tm.setAnswerItemID(am.getAnswerItemID());
					tm.setAnswer(answer);

					if (answer != null && "ѡ����".equals(am.getType()))
					{
						answer = answer.toUpperCase();
						String rightAnswer = am.getAnswer().toUpperCase();
						String rightUpperAnswer = "";
						if (answer.equals(am.getAnswer()))
							tm.setRight("right");
						else
						{
							for (int i = 0; i < rightAnswer.length(); i ++)
							{
								char ch = rightAnswer.charAt(i);
								if (ch >= 'A' && ch <= 'Z')
									rightUpperAnswer += ch;
							}

							if (answer.equals(rightUpperAnswer))
								tm.setRight("right");
						}
					}
				}

				models.add(tm);
			}

			testResultsItemSL.updateTestResultsItem(models);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
   }

   /**  ȡ������ Modify Project state*/
   public void cancelExam(String courseID)
   {
	  try
	  {
		secSL.deleteByApp(courseID);
		projectSL.updateByapp(courseID,"test","����");
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
   }

   /**ѡ����õ�¼ */
	public synchronized Vector login(String courseid,String ip,String userID) //��ʦipΪnull
	{
		Vector returnValue=new Vector();
		String type="";
				boolean isTeacherLogin=false;
		try
			{
			  for(int i=0;i<loginInfo.size();i++)
			  {
				LoginInfoModel eachLoginIn=(LoginInfoModel)loginInfo.elementAt(i);

								if(ip!=null)
								{
								  if (eachLoginIn.getUserType().equals("��ʦ")&&eachLoginIn.getUserID().equals(userID))
								  {
										eachLoginIn.setIp(ip);
										eachLoginIn.setCourseID(courseid);
					loginInfo.removeElementAt(i);
					loginInfo.insertElementAt(eachLoginIn,i);
										isTeacherLogin=true;
								  }
								}else
								{
								  if (eachLoginIn.getUserType().equals("��ʦ")&&eachLoginIn.getCourseID().equals(courseid))
									isTeacherLogin=true;
								}

								if(isTeacherLogin)
								{
								  GroupUserModel groupUser=new GroupUserModel();
				  groupUser.setUserID(eachLoginIn.getUserID());
				  groupUser.setUserName(eachLoginIn.getName());
				  groupUser.setUserType("0");
				  groupUser.setUserGender(eachLoginIn.getGender());
				  groupUser.setUserIP(eachLoginIn.getIp());
				  returnValue.addElement(groupUser);
				  break;
								}

								/**
								if (eachLoginIn.getUserType().equals("��ʦ")&&eachLoginIn.getUserID().equals(userID))
				{
				  if(ip!=null)
				  {
					eachLoginIn.setIp(ip);
										eachLoginIn.setCourseID(courseid);
					loginInfo.removeElementAt(i);
					loginInfo.insertElementAt(eachLoginIn,i);
				  }
				  GroupUserModel groupUser=new GroupUserModel();
				  groupUser.setUserID(eachLoginIn.getUserID());
				  groupUser.setUserName(eachLoginIn.getName());
				  if(eachLoginIn.getUserType().equals("��ʦ"))
				  {
					type="0";
				  }else if(eachLoginIn.getUserType().equals("ѧ��"))
				  {
					type="1";
				  }else if(eachLoginIn.getUserType().equals("������"))
				  {
					type="2";
				  }
				  groupUser.setUserType(type);
				  groupUser.setUserGender(eachLoginIn.getGender());
				  groupUser.setUserIP(eachLoginIn.getIp());
				  returnValue.addElement(groupUser);
				  break;
				}
							  */
			  }

						if(isTeacherLogin)
						{
							 Collection collection=usersSL.searchUsersForApp(courseid);
							 Iterator iterator=collection.iterator();
							  while(iterator.hasNext())
							  {
										UsersModel um=(UsersModel)iterator.next();
										GroupUserModel groupUser=new GroupUserModel();
										groupUser.setUserID(um.getUserID().toString());
										groupUser.setUserName(um.getName());
										groupUser.setUserGender(um.getGender());
										if(um.getUserType().equals("��ʦ"))
										{
											  type="0";
										}else if(um.getUserType().equals("ѧ��"))
										{
											  type="1";
										}else if(um.getUserType().equals("������"))
										{
											  type="2";
										}
										groupUser.setUserType(type);
										returnValue.addElement(groupUser);
							  }
						}
	   }
	   catch(Exception re)
	   {
		   re.printStackTrace();
	   }
	   return returnValue;
	}
	/**�û���������֤*/
	public synchronized Hashtable verifyPassword(String userName,String password)
	{
		Hashtable returnValue=new Hashtable();
		boolean islogin=false;
		try
		{
		  UsersModel um=usersSL.isLogin(userName,password);
		  for(int i=0;i<loginInfo.size();i++)
		  {
			LoginInfoModel eachLoginIn=(LoginInfoModel)loginInfo.elementAt(i);
			if (eachLoginIn.getUserID().equals(um.getUserID()))
			{
			  islogin=true;
			  break;
			}
		  }

		  if(!islogin)
		  {
			LoginInfoModel lim=new LoginInfoModel();
			lim.setLoginName(um.getLoginName());
			lim.setName(um.getName());
			lim.setUserID(um.getUserID().toString());
			lim.setUserType(um.getUserType());
			lim.setIp("");
			lim.setCourseID("");
			lim.setCourseName("");
			lim.setGender(um.getGender());
			loginInfo.addElement(lim);
		  }

		  if(um.getUserID()!=null)
					  returnValue.put("userid",um.getUserID().toString());
				  if(um.getUserType().equals("��ʦ"))
		  {
						returnValue.put("type","0");
			ListChunk lc=courseSL.searchCourse("teacher",0,0,um.getUserID().toString());
			Collection collection=lc.getCollection();
			Iterator iterator=collection.iterator();
			while(iterator.hasNext())
			{
			  CourseModel cm=(CourseModel)iterator.next();
						  returnValue.put(cm.getCourseID().toString(),cm.getCourseName());
			}
		  }else if(um.getUserType().equals("ѧ��")||um.getUserType().equals("������"))
		  {
			if(um.getUserType().equals("ѧ��"))
			{
			  returnValue.put("type","1");
			}else
			{
			  returnValue.put("type","2");
			}
			ListChunk lc=courseSL.searchCourse("student",0,0,um.getUserID().toString());
			Collection collection=lc.getCollection();
			Iterator iterator=collection.iterator();
			while(iterator.hasNext())
			{
			  CourseModel cm=(CourseModel)iterator.next();
			  for (int j=0;j<loginInfo.size();j++)
			  {
				LoginInfoModel eachLoginIn=(LoginInfoModel)loginInfo.elementAt(j);
								returnValue.put(cm.getCourseID().toString(),cm.getCourseName());
			  }
			}
		  }
		}catch(Exception e)
		{
		  System.out.println(e);
		}
		return returnValue;
	}

   /** ���������ϰ�������Ϣ  */
   public Collection getListenExerciseInfo(String courseID)
   {
	  Collection collection=null;
	  Iterator iterator=null;
	  ArrayList al= new ArrayList();
	  try
	  {
		collection=projectSL.getProjectInfoByApp(courseID,"listening");
		iterator=collection.iterator();
		while(iterator.hasNext())
		{
		  ProjectModel pm=(ProjectModel)iterator.next();
		  ListenExerciseModel epim= new ListenExerciseModel();
		  epim.setProjectID(pm.getProjectID().toString());
		  epim.setProjectName(pm.getName());
		  epim.setProjectInfo(pm.getInfo());
		  epim.setProjectDate(pm.getStartDate());
		  al.add(epim);
		}
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return al;
   }

   /** �������Ƭ�ϵ������Ϣ  */
   public Collection getListenSnippetInfo(String exerciseID)
   {
	  Collection collection=null;
	  Iterator iterator=null;
	  ArrayList al= new ArrayList();
	  try
	  {
		collection=contentSL.getContentByApp(exerciseID);
		iterator=collection.iterator();
		while(iterator.hasNext())
		{
		  ContentModel cm=(ContentModel)iterator.next();
		  ListenSnippetModel ecim= new ListenSnippetModel();
		  ecim.setSinppetID(cm.getContentID().toString());
		  ecim.setSinppetName(cm.getName());
		  ecim.setSnippetInfo(cm.getInfo());
		  al.add(ecim);
		}
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return al;
   }

   /** ����������ϰ   */
   public void saveListenExerciseInfo(Vector listenExerciseID)
   {
	  try
	  {
		  saveExameContent(listenExerciseID);
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
   }
   /**  ȡ��������ϰ  */
   public void cancelListenExercise(String courseID)
   {
	   try
	   {
		   projectSL.updateByapp(courseID,"listening","����");
	   }
	   catch(Exception re)
	   {
		   re.printStackTrace();
	   }
   }
   /**  ȡ������Ƭ���б�  */
   public Collection getListenSnippetList(String courseID)
   {
	  Collection collection=null;
	  Iterator iterator=null;
	  ArrayList al= new ArrayList();

	  try
	  {
		collection=contentSL.getCheckedContentByApp(courseID,"listening");
		iterator=collection.iterator();
		while(iterator.hasNext())
		{
		  ContentModel cm=(ContentModel)iterator.next();
		  ListenSnippetModel ecim= new ListenSnippetModel();
		  ecim.setSinppetID(cm.getContentID().toString());
		  ecim.setSinppetName(cm.getName());
		  ecim.setSnippetInfo(cm.getInfo());
		  al.add(ecim);
		}
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return al;
   }
   /**  ȡ������Ƭ�ϵ�URL */
   public String  getListenSnippetURL(String sinppetID)
   {
	  String returnValue=null;
	  try
	  {
		returnValue=properMgr.getFtpPath()+contentSL.getContent(sinppetID).getDocURL();
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
	  return returnValue;
   }

   /**��ʦ�˳�*/
   public synchronized boolean logout(String courseID)
   {
	   boolean b=true;
	   try
	   {
		   for(int i=loginInfo.size()-1;i>=0;i--)
		   {
			  LoginInfoModel eachLoginIn=(LoginInfoModel)loginInfo.elementAt(i);
			  if (eachLoginIn.getCourseID().equals(courseID));
			  {
				  loginInfo.removeElementAt(i);
			  }
			}
	   }
	   catch(Exception re)
	   {
		   re.printStackTrace();
		   return false;
	   }
	   return b;
   }
  /**   ����Ƿ�ѡ��������ϰ  */
   public boolean selectListenOrNot(String courseID)
   {
		  boolean returnValue=false;
	  try
	  {
		returnValue=projectSL.selectExamOrNotForApp(courseID,"listening");
	  }catch(Exception e)
	  {
		System.out.println(e);
				return false;
	  }
	  return returnValue;
   }

  /**  �������� */
   public void finishExam(String courseID,String userID)
   {
		  try
	  {
		secSL.deleteByApp(courseID);
		projectSL.updateByapp(courseID,"testclosed","�������");
	  }catch(Exception e)
	  {
		System.out.println(e);
	  }
   }
}