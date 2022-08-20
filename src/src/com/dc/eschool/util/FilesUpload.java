package com.dc.eschool.util;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * <b>Title:</b> �ļ��ϴ�<br>
 * <b>Description:</b>  ���ļ�ͬʱ�ϴ�JavaBean.<br>
 * <b>Copyright:</b>    Copyright (c) 2000<br>
 * <b>Company:</b>      Digital Western China co.,Ltd<br>
 * <b>@author</b> Eric Chen<br>
 * <b>@version</b> 1.2<br><br><br>
 *                <b>Html��Ҫ���룺</b><br><br>
 *                &lt;form name="uploadform" method="post" action="filesUpload.jsp" ENCTYPE="multipart/form-data" &gt;<br><br>
 *
 *                 <b>����������ӣ�</b><a href="filesUpload.html">filesUpload.html</a><br><br><br>
 *                <b>Jsp��ʹ�÷�����</b><br><br>
 *                &lt;jsp:useBean id="bean0" scope="page" class="fileupload.FileUpload" /&gt;<br>
 *                &lt;jsp:setProperty name="bean0" property="*" /&gt;<br>
 *                &lt;%bean0.initialize(request) ;<br>
 *                 bean0.save();%&gt;<br><br>
 *                 <b>����������ӣ�</b>
 *                 <a href="filesUpload.jsp">filesUpload.jsp</a><br>
 */
public class FilesUpload
{
    private ServletRequest request;//�ݽ�����ҳ������
    private ServletInputStream DATA;//�ϴ���ҳ������
    private int FormSize;//�ϴ���ҳ�����ݴ�С������Html��
    private FileOutputStream os; //д�ļ���
    private DataInputStream is;//���ļ���
    private byte[] b;
    private byte t;
    private String sListSeparator;//�����ϴ�ҳ��Content_type���������ļ��ķָ��
    private String fileName;
    private int start1=0,start2=0,k,count;
    private String temp="";//�ļ�
    private String sMessage;//������Ϣ��ʾ
    private String strS;//�ļ�����ת��
    private Vector vFiles=new Vector();//�����ϴ��ļ�
    private Vector vFileName=new Vector();//ÿ���ϴ��ļ�������
    private Vector vFileContentType=new Vector();//ÿ���ϴ��ļ���Content-type
    private Vector vFileType=new Vector();//ÿ���ϴ��ļ��ĺ�׺��
    private Vector vInputName=new Vector();//ÿ���ϴ��ļ��� Input����
    private Vector vFile=new Vector();//ÿ���ϴ��ļ����������ַ�
    private Vector vFileSize=new Vector();//ÿ���ϴ��ļ��Ĵ�С
    private Vector vSaveFileName=new Vector();//ÿ���ϴ��ļ����洢�����֣�������ǰ·����
    private Vector vSaveFilesName=new Vector();//ÿ���ϴ��ļ����洢�����֣�������ǰ·����
    private boolean saveS;
    private String STRNAME;
    private String sPath;
    private String s;
    private String kindStr;

  /**<b>��ʼ��������</b><br><br>
  * JSP���÷����� bean0.initialize(request);<br>
  * @param request �������������ͣ�HttpServletRequest��
  */
    public void initialize(HttpServletRequest request) throws IOException
    {

        //����ϴ���������в�����������
        this.request=request;
	//System.out.println(request.getContentLength());

        //����ϴ���������ݣ������ϴ��ļ���
        DATA = request.getInputStream();

        //����ϴ���������ݵĴ�С���ֽڣ��������ϴ��ļ�
        FormSize=request.getContentLength();

        //����ϴ��ļ��ķָ��
        sListSeparator=request.getContentType().toString();
        sListSeparator="--"+sListSeparator.substring(30,sListSeparator.length());
    }

  /**<b>���ô洢���ļ�����</b><br><br>
  *
  * @param sPath ͼƬ�ļ��ϴ���洢��·�������ͣ�String����null��image/
  * @param s     ͼƬ�ļ��ϴ���洢���ļ��������ͣ�String��,null����ǰ������ʱ�䡣
  * @param hPath Html�ļ��ϴ���洢��·�������ͣ�String����null��html/
  * @param h     Html�ļ��ϴ���洢���ļ��������ͣ�String��,null����ǰ������ʱ�䡣
  */
    private boolean saveFileName(){

        //��ʼ�洢·��

        if (sPath==null) sPath="/";
	sPath=request.getRealPath(sPath);

        //�ж��ļ������Ƿ�Ϊ��

        if(vFileName.elementAt(k).toString().length()>0){
          //�ж��ļ�����

            if (s==null) s="";
	    if(s.equals(""))
	    {
	      if (kindStr.equals("project"))
	      {
		s=sCurrentTime()+vFileType.elementAt(k).toString();
	      }else if(kindStr.equals("homework"))
	      {
	        s=vFileName.elementAt(k).toString();
	      }
	    }else if(kindStr.equals("newhomework"))
	    {
	      s=s+"_"+sCurrentTime()+vFileType.elementAt(k).toString();
	    }
              strS=s;

            STRNAME=strS;
            strS=sPath+strS;

          //������������
          try{
            b=strS.getBytes("ISO8859_1") ;
            strS=new String(b);
          }
          catch( UnsupportedEncodingException e) {
            e.printStackTrace();
          }
          vSaveFilesName.addElement(STRNAME);
          vSaveFileName.addElement(strS);
        }else{
          vSaveFilesName.addElement(null);
          vSaveFileName.addElement(null);
        }
     // }
        return true;
    }

  /**<b>���Ҫ�ϴ����ļ���������</b><br><br>
  *
  *
  */
    private void getByte(){
      int i=0;
      try{
        is=new DataInputStream(DATA);
        b=new byte[FormSize];

        while (i<FormSize){
          try{
                t=is.readByte();
                b[i]=t;
                i++;
          }catch(EOFException e)
            { break;}
      }
        is.close();}
        catch(IOException e)
        {}
    }

  /**<b>�洢�ϴ��ļ���д���ļ���</b><br><br>
  *
  */
    public boolean save(String sPath,String s,String kindStrIn)
    {
      if(kindStrIn==null) kindStrIn="";
      this.sPath=sPath;
      this.s=s;
      this.kindStr=kindStrIn;
      saveS=false;
      //�ָ�ÿ���ϴ��ļ�
      divideFile();

      //��ÿ���ϴ��ļ�������ϸ�ָ�
      for(int i=0;i<vFiles.size();i++) divideEachFile(vFiles.elementAt(i).toString());


      //���û�д�����Ϣ�������ļ����洢
      if (createMessage()){

        for (k=0,count=0;k<vFileName.size();k++) {
          try{

            if (vFileName.elementAt(k).toString().length()>0){

              //ȷ���ļ��Ĵ洢���ƺ�·��
              saveFileName();


		if(kindStr.equals("homework"))
		{
		  Integer hwId=EJBUtil.getHWContentSLHome().create().isHaveDocURL(vSaveFilesName.elementAt(count).toString());
		  if(hwId.intValue()<=0)
		  {
		    sMessage=sMessage+"�Բ������ϴ����ļ�("+vSaveFileName.elementAt(count).toString()+")û�б������뱣֤���ϴ����ļ�������ʱͬ��<br>";
		    return false;
		  }
		}
              //�����ļ�
                File f1=new File(vSaveFileName.elementAt(count).toString());
                os=new FileOutputStream(f1);

                byte[] img=vFile.elementAt(k).toString().getBytes("ISO8859_1");

                //д������
                for (int m=0;m<img.length;m++){
                  os.write(img[m]);
                }
                os.close();
                saveS=true;
		count=count+1;
            }

          }catch(Exception e){
                sMessage=sMessage+"�ϴ��ļ�ʧ�ܣ��������ϴ����ļ���·�������û���ϴ��ļ�<br>";
                vSaveFilesName=null;
		System.out.println(e);
            return false;
          }
        }
      }
            if(saveS) {
              sMessage=sMessage+"�ļ��ϴ��ɹ�<br>";
            }else{
                sMessage=sMessage+"�ϴ��ļ�ʧ�ܣ��������ϴ����ļ���·�������û���ϴ��ļ�<br>";
                vSaveFilesName=null;
              }
      return saveS;
    }


  /**<b>�õ���ǰʱ��������ַ�</b><br><br>
  *
  */
    private String sCurrentTime(){
        String strCurrent="";
        java.sql.Timestamp currentTime=new java.sql.Timestamp(System.currentTimeMillis());
        strCurrent=currentTime.toString();
        strCurrent=strCurrent.replace(' ','-') ;
        strCurrent=strCurrent.replace(':','-') ;
        strCurrent=strCurrent.replace('.','-') ;
        String strCu="";
        for (int n=0;n<strCurrent.length();n++){
          if (strCurrent.substring(n,n+1).compareTo("-")!=0) strCu=strCu+strCurrent.substring(n,n+1) ;
        }
        strCurrent=strCu;
        return strCurrent;
    }

  /**<b>�洢���ϴ��ļ�������</b><br><br>
  *
  */
    public Vector getFilename(){
        return vSaveFilesName;
    }

  /**<b>������Ϣ��ʾ</b><br><br>
  *
  */
    public String getMessage(){
        return sMessage;
    }

  /**<b>�ж��ļ������͡���С�����������Ҫ�������ɴ�����Ϣ</b><br><br>
  *
  */
    private boolean createMessage(){
      boolean returnValue=true;
      sMessage="";
      for (int i=0;i<vFile.size();i++){
        if (vFileName.elementAt(i).toString().length()>0){
          if ((vFileContentType.elementAt(i).toString().compareTo("application/msword")!=0)
            &&(vFileContentType.elementAt(i).toString().compareTo("application/vnd.ms-powerpoint")!=0)
            &&(vFileContentType.elementAt(i).toString().compareTo("audio/mpeg")!=0)
            &&(vFileContentType.elementAt(i).toString().compareTo("audio/wav")!=0)){
            sMessage=sMessage+vInputName.elementAt(i).toString()+"(��"+String.valueOf(i+1)+"���ϴ��ļ�:"+vFileName.elementAt(i).toString()+"),�ϴ�ʧ�ܣ����ʹ���<br>";
            returnValue=false;
          }
          /**
          if (Integer.valueOf(vFileSize.elementAt(i).toString()).intValue()>51200){
            sMessage=sMessage+vInputName.elementAt(i).toString()+"(��"+String.valueOf(i+1)+"���ϴ��ļ���"+vFileName.elementAt(i).toString()+"),�ϴ�ʧ�ܣ��ļ��Ĵ�С���ܴ���50K.<br>";
            returnValue=false;
          }
          */
        }
      }
      return returnValue;
    }

  /**<b>��ÿ���ļ�������ϸ�ķָȡ���ļ����ƣ��ļ��ĺ�׺�����ļ��Ĵ�С���ļ��Ķ�����</b><br><br>
  * @param sFile ÿ���ϴ��ļ�����ϸ�������ַ�
  */
    private boolean divideEachFile(String sFile){

      //�õ�Input����
      start1=sFile.indexOf("form-data; name=") ;
      sFile=sFile.substring(start1) ;

      start1=sFile.indexOf("\"") ;
      sFile=sFile.substring(start1+1) ;
      start1=sFile.indexOf("\"") ;

      vInputName.addElement(sFile.substring(0,start1)) ;

      //�õ��ļ���ȫ��
      start1=sFile.indexOf("filename=") ;
      fileName="";

      if (start1>=0)
      {
	  sFile=sFile.substring(start1+1) ;

	  start1=sFile.indexOf("\r\n")-1;//����
	  fileName=sFile.substring(9,start1) ;
	  try{
	    b=fileName.getBytes("ISO8859_1") ;
	    fileName=new String(b);
	  }catch( UnsupportedEncodingException e) {
	    e.printStackTrace();
	  }
      }

      //������ֲ�Ϊ��
      if (fileName.length()>0){
        start1=fileName.lastIndexOf("\\");
        fileName=fileName.substring(start1+1) ;

        vFileName.addElement(fileName) ;

        //�õ��ļ��ĺ�׺����.gif��
        start1=fileName.indexOf(".");
        fileName=fileName.substring(start1) ;

        vFileType.addElement(fileName) ;
      }else{
        vFileName.addElement("") ;
        vFileType.addElement("") ;
      }

      //�õ��ļ���MIME����
      start1=sFile.indexOf("Content-Type:") ;
      if (start1>=0)
      {
        sFile=sFile.substring(start1) ;
        start1=sFile.indexOf("\r\n");
        vFileContentType.addElement(sFile.substring(14,start1)) ;
      }else{
        vFileContentType.addElement("");
      }

      start1=sFile.indexOf("\r\n\r\n") ;
      sFile=sFile.substring(start1+4) ;
      vFile.addElement(sFile) ;


      //�ļ��Ĵ�С
      vFileSize.addElement(String.valueOf(sFile.length())) ;

      return true;
    }

  /**<b>�������ϴ��ļ���HTML���������е����ļ��ķָ�</b><br><br>
  *
  */
    private boolean divideFile(){

      getByte();//b

      //�õ�����
      try{
        temp=new String(b,"ISO8859_1");
      }catch(UnsupportedEncodingException e){
        return false;
      }

      //�ָ�ɵ����ļ�
      while (temp.indexOf("Content-Disposition")>0){
        start1=temp.indexOf("Content-Disposition");
        temp=temp.substring(start1);
        start2=temp.indexOf(sListSeparator);
        int k=temp.indexOf("Content-Type:");

        //�ж��Ƿ�Ϊsubmit��ť
        if (k>=0) vFiles.addElement(temp.substring(0,start2-2));
        temp=temp.substring(start2) ;
      }
      return true;
    }

    public Vector getVfile()
    {
      return vFile;
    }

    public Vector getFilesSize()
    {
      return vFileSize;
    }

}
