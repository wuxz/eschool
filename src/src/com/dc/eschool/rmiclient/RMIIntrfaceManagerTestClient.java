package com.dc.eschool.rmiclient;

import com.dc.eschool.rmi.ejb.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import java.util.Collection;
import java.net.URL;
import java.util.Vector;
import java.util.Hashtable;

public class RMIIntrfaceManagerTestClient {
  private static final String ERROR_NULL_REMOTE = "Remote interface reference is null.  It must be created by calling one of the Home interface methods first.";
  private static final int MAX_OUTPUT_LINE_LENGTH = 100;
  private boolean logging = true;
  private RMIInterfaceSLHome rMIInterfaceSLHome = null;
  private RMIInterfaceSL rMIInterfaceSL = null;

  /**Construct the EJB test client*/
  public RMIIntrfaceManagerTestClient() {
    long startTime = 0;
    if (logging) {
      log("Initializing bean access.");
      startTime = System.currentTimeMillis();
    }

    try {

      //get naming context

      Context ctx = new InitialContext();

      //look up jndi name
      Object ref = ctx.lookup("eschool/RMIInterfaceManager");

      //cast to Home interface
      rMIInterfaceSLHome = (RMIInterfaceSLHome) PortableRemoteObject.narrow(ref, RMIInterfaceSLHome.class);
      rMIInterfaceSL = rMIInterfaceSLHome.create();

      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded initializing bean access.");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed initializing bean access.");
      }
      e.printStackTrace();
    }
  }

  //----------------------------------------------------------------------------
  // Methods that use Home interface methods to generate a Remote interface reference
  //----------------------------------------------------------------------------


  //----------------------------------------------------------------------------
  // Methods that use Remote interface methods to access data through the bean
  //----------------------------------------------------------------------------

  public Collection getUserInfo(String userID) {
    Collection returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in getUserInfo(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling getUserInfo(" + userID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.getUserInfo(userID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: getUserInfo(" + userID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getUserInfo(" + userID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from getUserInfo(" + userID + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public void cancelExam(String courseID) {
    if (rMIInterfaceSL == null) {
      System.out.println("Error in cancelExam(): " + ERROR_NULL_REMOTE);
      return ;
    }
    long startTime = 0;
    if (logging) {
      log("Calling cancelExam(" + courseID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      rMIInterfaceSL.cancelExam(courseID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: cancelExam(" + courseID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: cancelExam(" + courseID + ")");
      }
      e.printStackTrace();
    }
  }

  public void changeProjectState(String projectID, String state) {
    if (rMIInterfaceSL == null) {
      System.out.println("Error in changeProjectState(): " + ERROR_NULL_REMOTE);
      return ;
    }
    long startTime = 0;
    if (logging) {
      log("Calling changeProjectState(" + projectID + ", " + state + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      rMIInterfaceSL.changeProjectState(projectID, state);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: changeProjectState(" + projectID + ", " + state + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: changeProjectState(" + projectID + ", " + state + ")");
      }
      e.printStackTrace();
    }
  }

  public void deleteContent(String ContentID) {
    if (rMIInterfaceSL == null) {
      System.out.println("Error in deleteContent(): " + ERROR_NULL_REMOTE);
      return ;
    }
    long startTime = 0;
    if (logging) {
      log("Calling deleteContent(" + ContentID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      rMIInterfaceSL.deleteContent(ContentID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: deleteContent(" + ContentID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: deleteContent(" + ContentID + ")");
      }
      e.printStackTrace();
    }
  }

  public boolean examGroupOrNot(String courseID) {
    boolean returnValue = false;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in examGroupOrNot(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling examGroupOrNot(" + courseID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.examGroupOrNot(courseID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: examGroupOrNot(" + courseID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: examGroupOrNot(" + courseID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from examGroupOrNot(" + courseID + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public Collection getContentInfo(String projectID) {
    Collection returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in getContentInfo(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling getContentInfo(" + projectID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.getContentInfo(projectID);
      System.out.println(returnValue.size());
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: getContentInfo(" + projectID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getContentInfo(" + projectID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from getContentInfo(" + projectID + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public Collection getContentList(String courseID) {
    Collection returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in getContentList(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling getContentList(" + courseID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.getContentList(courseID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: getContentList(" + courseID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getContentList(" + courseID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from getContentList(" + courseID + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public Collection getCourseAllInfo(String courseID) {
    Collection returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in getCourseAllInfo(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling getCourseAllInfo(" + courseID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.getCourseAllInfo(courseID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: getCourseAllInfo(" + courseID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getCourseAllInfo(" + courseID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from getCourseAllInfo(" + courseID + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public URL getExamContent(String contentID) {
    URL returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in getExamContent(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling getExamContent(" + contentID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.getExamContent(contentID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: getExamContent(" + contentID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getExamContent(" + contentID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from getExamContent(" + contentID + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public URL getExamContent(String userID,String courseID) {
    URL returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in getExamContent(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling getExamContent(" + userID+","+ courseID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.getExamContent(userID,courseID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: getExamContent(" + userID+","+ courseID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getExamContent(" + userID+","+ courseID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from getExamContent(" + userID+","+ courseID + ")" + returnValue + ".");
    }
    return returnValue;
  }

  public Collection getExamInfo(String courseID) {
    Collection returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in getExamInfo(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling getExamInfo(" + courseID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.getExamInfo(courseID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: getExamInfo(" + courseID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getExamInfo(" + courseID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from getExamInfo(" + courseID + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public Collection getQuestionInfo(String userID, String courseID) {
    Collection returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in getQuestionInfo(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling getQuestionInfo(" + userID + ", " + courseID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.getQuestionInfo(userID, courseID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: getQuestionInfo(" + userID + ", " + courseID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getQuestionInfo(" + userID + ", " + courseID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from getQuestionInfo(" + userID + ", " + courseID + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public Collection getUserInfoByName(String userName) {
    Collection returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in getUserInfoByName(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling getUserInfoByName(" + userName + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.getUserInfoByName(userName);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: getUserInfoByName(" + userName + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getUserInfoByName(" + userName + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from getUserInfoByName(" + userName + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public Vector login(String courseid, String ip,String userID) {
    Vector returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in login(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling login(" + courseid + ", " + ip + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.login(courseid, ip,userID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: login(" + courseid + ", " + ip + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: login(" + courseid + ", " + ip + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from login(" + courseid + ", " + ip + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public void saveContentStudent(Vector StudentName, String contentID, String courseID, String teacherID) {
    if (rMIInterfaceSL == null) {
      System.out.println("Error in saveContentStudent(): " + ERROR_NULL_REMOTE);
      return ;
    }
    long startTime = 0;
    if (logging) {
      log("Calling saveContentStudent(" + StudentName + ", " + contentID + ", " + courseID + ", " + teacherID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      rMIInterfaceSL.saveContentStudent(StudentName, contentID, courseID, teacherID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: saveContentStudent(" + StudentName + ", " + contentID + ", " + courseID + ", " + teacherID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: saveContentStudent(" + StudentName + ", " + contentID + ", " + courseID + ", " + teacherID + ")");
      }
      e.printStackTrace();
    }
  }

  public void saveExamAnsweerPaper(String userID, String courseID,Hashtable answeerItem) {
    if (rMIInterfaceSL == null) {
      System.out.println("Error in saveExamAnsweerPaper(): " + ERROR_NULL_REMOTE);
      return ;
    }
    long startTime = 0;
    if (logging) {
      log("Calling saveExamAnsweerPaper(" + userID + ", " + answeerItem + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      rMIInterfaceSL.saveExamAnsweerPaper(userID, courseID,answeerItem);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: saveExamAnsweerPaper(" + userID + ", " + answeerItem + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: saveExamAnsweerPaper(" + userID + ", " + answeerItem + ")");
      }
      e.printStackTrace();
    }
  }

  public void saveExameContent(Vector projectID) {
    if (rMIInterfaceSL == null) {
      System.out.println("Error in saveExameContent(): " + ERROR_NULL_REMOTE);
      return ;
    }
    long startTime = 0;
    if (logging) {
      log("Calling saveExameContent(" + projectID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      rMIInterfaceSL.saveExameContent(projectID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: saveExameContent(" + projectID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: saveExameContent(" + projectID + ")");
      }
      e.printStackTrace();
    }
  }

  public void saveUserInfo(String userID, String name, String gender, String birthday, String userType, String telephone) {
    if (rMIInterfaceSL == null) {
      System.out.println("Error in saveUserInfo(): " + ERROR_NULL_REMOTE);
      return ;
    }
    long startTime = 0;
    if (logging) {
      log("Calling saveUserInfo(" + userID + ", " + name + ", " + gender + ", " + birthday + ", " + userType + ", " + telephone + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      rMIInterfaceSL.saveUserInfo(userID, name, gender, birthday, userType, telephone);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: saveUserInfo(" + userID + ", " + name + ", " + gender + ", " + birthday + ", " + userType + ", " + telephone + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: saveUserInfo(" + userID + ", " + name + ", " + gender + ", " + birthday + ", " + userType + ", " + telephone + ")");
      }
      e.printStackTrace();
    }
  }

  public boolean selectExamOrNot(String courseID) {
    boolean returnValue = false;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in selectExamOrNot(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling selectExamOrNot(" + courseID + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.selectExamOrNot(courseID);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: selectExamOrNot(" + courseID + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: selectExamOrNot(" + courseID + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from selectExamOrNot(" + courseID + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public Hashtable verifyPassword(String userName, String password) {
    Hashtable returnValue = null;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in verifyPassword(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling verifyPassword(" + userName + ", " + password + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.verifyPassword(userName, password);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: verifyPassword(" + userName + ", " + password + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: verifyPassword(" + userName + ", " + password + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from verifyPassword(" + userName + ", " + password + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public boolean logout(String courseid) {
    boolean returnValue = true;
    if (rMIInterfaceSL == null) {
      System.out.println("Error in login(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling logout(" + courseid + ")");
      startTime = System.currentTimeMillis();
    }

    try {
      returnValue = rMIInterfaceSL.logout(courseid);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: logout(" + courseid + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: logout(" + courseid + ")");
      }
      e.printStackTrace();
    }

    if (logging) {
      log("Return value from logout(" + courseid + "): " + returnValue + ".");
    }
    return returnValue;
  }

  public void testRemoteCallsWithDefaultArguments() {
    if (rMIInterfaceSL == null) {
      System.out.println("Error in testRemoteCallsWithDefaultArguments(): " + ERROR_NULL_REMOTE);
      return ;
    }
    getUserInfo("");
    cancelExam("");
    changeProjectState("" ,"");
    deleteContent("");
    examGroupOrNot("");
    getContentInfo("");
    getContentList("");
    getCourseAllInfo("");
    getExamContent("");
    getExamInfo("");
    getQuestionInfo("" ,"");
    getUserInfoByName("");
    login("" ,"","");
    saveContentStudent(null ,"" ,"" ,"");
    saveExamAnsweerPaper("" ,"",null);
    saveExameContent(null);
    saveUserInfo("" ,"" ,"" ,"" ,"" ,"");
    selectExamOrNot("");
    verifyPassword("" ,"");
  }

  //----------------------------------------------------------------------------
  // Utility Methods
  //----------------------------------------------------------------------------

  private void log(String message) {
    if (message == null) {
      System.out.println("-- null");
      return ;
    }
    if (message.length() > MAX_OUTPUT_LINE_LENGTH) {
      System.out.println("-- " + message.substring(0, MAX_OUTPUT_LINE_LENGTH) + " ...");
    }
    else {
      System.out.println("-- " + message);
    }
  }
  /**Main method*/

  public static void main(String[] args) {
    RMIIntrfaceManagerTestClient client = new RMIIntrfaceManagerTestClient();
    // Use the client object to call one of the Home interface wrappers
    // above, to create a Remote interface reference to the bean.
    // If the return value is of the Remote interface type, you can use it
    // to access the remote interface methods.  You can also just use the
    // client object to call the Remote interface wrappers.
  }
}