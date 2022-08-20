//Source file: D:\\yan_work\\eschool\\src\\com\\dc\\eschool\\Communication\\CommunicationIF.java

package com.dc.eschool.communication;
import com.dc.eschool.system.Teacher;
import java.util.Hashtable;
import java.util.Vector;
import com.dc.eschool.group.*;
import java.awt.*;
import javax.swing.JOptionPane;
public interface CommunicationIF
{
    public void askForTeacher();
    public void answerForStudent(EschoolUser user);
    public void  addAllToOnlineTalk();
    public void  notifyStartOnlineTalk(Hashtable userIDs,String port);
    public void endOnlineTalk();
    public void notifyEndOnlineTalk();
    public void startDemonstrate(String url);
    public void notifyStartDemonstrate(String port);
    public void askFromStudent(String userID);
    public void  notifyEndDemonstrate();
    public void addAllToAnswerCompetition();
    public void addStudentToAnswerCompetition(String userID);
    public void addGroupToAnswerCompetition(String userID);
    public void  notifyStartAnswerCompetition(Vector userIDs,String port);
    public void notifyBeginOrEndAnswerCompetition(boolean b);
    public void beginAnswerCompetition(Vector userIDs);
    public String answerFromTeacher(String port);
    public void endAnswerCompetition(Vector userIDs);
    public void sendInformationToStudent(String userID,String info);
    public void voiceBroadCast(Vector userIDs,Teacher teacher);
    public void startVoiceReceive(String port);
    public void stopVoiceBroadCast(Vector userIDs,Teacher teacher);
    public void closeMedia();
    public void allowAnswer(String userID,boolean b);
    public void notifyAllowAnswer(boolean b);
    public void answerCompetitionSequence(String userID);
    public void addAnswerCompetitionSequence(String userID);
    public void studentAnswerCompetition(String userID);
    //public String randomPort();
    public void showInfoMsg(Component parentComponent,String infoMsg);
    public void sendInformationToTeacher(String info);
    public void stopListenPlay(Vector userIDs,Teacher teacher);
    public void startListenReceive(String port);
    public void listenPlay(Vector userIDs,Teacher teacher,String url,String port);
    public void addStudentToOnlineTalk(String userID);
    public void addGroupToOnlineTalk(String userID);
    public void startStudentDemonstrate(String userID);
    public void startDemonstrate(String url,String userID);
    public String studentDemonstrate(String url);
    public void endDemonstrate();
    public void closeAnswerCompetition(Vector userIDs);
    public void notifyCloseAnswerCompetition();
}
