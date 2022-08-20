package com.dc.eschool.communication;
import javax.swing.JTextPane;
import com.dc.eschool.system.*;
import javax.swing.JTextArea;
import java.rmi.RemoteException;
import javax.swing.text.html.*;
import java.io.StringReader;
import com.dc.eschool.systemControl.SystemControl;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class PrivateTalk
{
    private Student student=null;
    private Teacher teacher=null;
    private JTextPane jTextPane=null;
    private JTextArea jTextArea=null;
    //private String allContent=null;
    //private HTMLEditorKit htmlKit;
    private SystemControl systemControl;
    private String type="";
    public PrivateTalk(){}
    public PrivateTalk(JTextPane jTextPane,Student student,SystemControl sysControl)
    {
        this.jTextPane=jTextPane;
        this.student=student;
        this.systemControl=sysControl;
        type="0";
    }
    public PrivateTalk(JTextPane jTextPane,Teacher teacher,SystemControl sysControl)
    {
        this.jTextPane=jTextPane;
        this.teacher=teacher;
        //this.allContent=content;
        this.systemControl=sysControl;
        type="1";
    }
    public PrivateTalk(JTextArea jTextArea,Student student)
    {
        this.jTextArea=jTextArea;
        this.student=student;
    }
    public PrivateTalk(JTextArea jTextArea,Teacher teacher)
    {
        this.jTextArea=jTextArea;
        this.teacher=teacher;
    }
    public void setStudent(Student student)
    {
        this.student=student;
    }
    /**
    * METHOD: sendText
    * DESC  : 老师学生传送信息。
    * CREATE: 1.0 Ardy 2001-10-19
    * MODIFY:
    */
    public void sendText(String content)
    {
        try
        {
            System.out.println("sendText's content:"+content);
            System.out.println("student:"+student);
            System.out.println("teacher:"+teacher);
            if(this.jTextArea!=null)
                this.jTextArea.append(content+"\n");
            else
            {
                //htmlKit=((HTMLEditorKit)this.jTextPane.getEditorKit());
                if(this.type.equals("0"))
                {
                    this.systemControl.jDialogOnlineTalk.allContent+=content;
                    this.jTextPane.setText(this.systemControl.jDialogOnlineTalk.allContent);
                }
                else
                {
                    this.systemControl.jDialogOnlineTalkStudent.allContent+=content;
                    this.jTextPane.setText(this.systemControl.jDialogOnlineTalkStudent.allContent);
                }
            }
            if (student!=null)
                student.sendText(content);
            else
                teacher.sendText(content);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("PrivateTalk Exception"+e.getMessage());
        }
    }
  /**
   * METHOD: receiveText
   * DESC  : 接收信息。
   * CREATE: 1.0 Ardy 2001-10-19
   * MODIFY:
   */
    public void receiveText(String content)
    {
        try
        {
            if(this.jTextArea!=null)
                    this.jTextArea.append(content+"\n");
            else
            {
                if(this.type.equals("0"))
                {
                    this.systemControl.jDialogOnlineTalk.allContent+=content;
                    this.jTextPane.setText(this.systemControl.jDialogOnlineTalk.allContent);
                }
                else
                {
                    this.systemControl.jDialogOnlineTalkStudent.allContent+=content;
                    this.jTextPane.setText(this.systemControl.jDialogOnlineTalkStudent.allContent);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("PrivateTalk Exception :"+e.getMessage());
            e.printStackTrace();
        }
     }

}