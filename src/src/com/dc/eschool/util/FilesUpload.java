package com.dc.eschool.util;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * <b>Title:</b> 文件上传<br>
 * <b>Description:</b>  多文件同时上传JavaBean.<br>
 * <b>Copyright:</b>    Copyright (c) 2000<br>
 * <b>Company:</b>      Digital Western China co.,Ltd<br>
 * <b>@author</b> Eric Chen<br>
 * <b>@version</b> 1.2<br><br><br>
 *                <b>Html重要代码：</b><br><br>
 *                &lt;form name="uploadform" method="post" action="filesUpload.jsp" ENCTYPE="multipart/form-data" &gt;<br><br>
 *
 *                 <b>详情请见例子：</b><a href="filesUpload.html">filesUpload.html</a><br><br><br>
 *                <b>Jsp的使用方法：</b><br><br>
 *                &lt;jsp:useBean id="bean0" scope="page" class="fileupload.FileUpload" /&gt;<br>
 *                &lt;jsp:setProperty name="bean0" property="*" /&gt;<br>
 *                &lt;%bean0.initialize(request) ;<br>
 *                 bean0.save();%&gt;<br><br>
 *                 <b>详情请见例子：</b>
 *                 <a href="filesUpload.jsp">filesUpload.jsp</a><br>
 */
public class FilesUpload
{
    private ServletRequest request;//递交的网页的内容
    private ServletInputStream DATA;//上传网页的数据
    private int FormSize;//上传网页的数据大小（包括Html）
    private FileOutputStream os; //写文件流
    private DataInputStream is;//读文件流
    private byte[] b;
    private byte t;
    private String sListSeparator;//整个上传页的Content_type，即所有文件的分割符
    private String fileName;
    private int start1=0,start2=0,k,count;
    private String temp="";//文件
    private String sMessage;//错误信息提示
    private String strS;//文件名称转换
    private Vector vFiles=new Vector();//所有上传文件
    private Vector vFileName=new Vector();//每个上传文件的名称
    private Vector vFileContentType=new Vector();//每个上传文件的Content-type
    private Vector vFileType=new Vector();//每个上传文件的后缀名
    private Vector vInputName=new Vector();//每个上传文件的 Input名称
    private Vector vFile=new Vector();//每个上传文件的数据流字符
    private Vector vFileSize=new Vector();//每个上传文件的大小
    private Vector vSaveFileName=new Vector();//每个上传文件最后存储的名字（包括当前路径）
    private Vector vSaveFilesName=new Vector();//每个上传文件最后存储的名字（包括当前路径）
    private boolean saveS;
    private String STRNAME;
    private String sPath;
    private String s;
    private String kindStr;

  /**<b>初始环境变量</b><br><br>
  * JSP设置方法： bean0.initialize(request);<br>
  * @param request 环境参数（类型：HttpServletRequest）
  */
    public void initialize(HttpServletRequest request) throws IOException
    {

        //获得上传界面的所有参数及其内容
        this.request=request;
	//System.out.println(request.getContentLength());

        //获得上传界面的数据，包括上传文件。
        DATA = request.getInputStream();

        //获得上传界面的数据的大小（字节），包括上传文件
        FormSize=request.getContentLength();

        //获得上传文件的分割符
        sListSeparator=request.getContentType().toString();
        sListSeparator="--"+sListSeparator.substring(30,sListSeparator.length());
    }

  /**<b>设置存储的文件名称</b><br><br>
  *
  * @param sPath 图片文件上传后存储的路径（类型：String），null：image/
  * @param s     图片文件上传后存储的文件名（类型：String）,null：当前的日期时间。
  * @param hPath Html文件上传后存储的路径（类型：String），null：html/
  * @param h     Html文件上传后存储的文件名（类型：String）,null：当前的日期时间。
  */
    private boolean saveFileName(){

        //初始存储路径

        if (sPath==null) sPath="/";
	sPath=request.getRealPath(sPath);

        //判断文件名称是否为空

        if(vFileName.elementAt(k).toString().length()>0){
          //判断文件类型

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

          //处理中文名称
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

  /**<b>获得要上传的文件的数据流</b><br><br>
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

  /**<b>存储上传文件（写进文件）</b><br><br>
  *
  */
    public boolean save(String sPath,String s,String kindStrIn)
    {
      if(kindStrIn==null) kindStrIn="";
      this.sPath=sPath;
      this.s=s;
      this.kindStr=kindStrIn;
      saveS=false;
      //分割每个上传文件
      divideFile();

      //对每个上传文件进行详细分割
      for(int i=0;i<vFiles.size();i++) divideEachFile(vFiles.elementAt(i).toString());


      //如果没有错误信息，则建立文件并存储
      if (createMessage()){

        for (k=0,count=0;k<vFileName.size();k++) {
          try{

            if (vFileName.elementAt(k).toString().length()>0){

              //确定文件的存储名称和路径
              saveFileName();


		if(kindStr.equals("homework"))
		{
		  Integer hwId=EJBUtil.getHWContentSLHome().create().isHaveDocURL(vSaveFilesName.elementAt(count).toString());
		  if(hwId.intValue()<=0)
		  {
		    sMessage=sMessage+"对不起，你上传的文件("+vSaveFileName.elementAt(count).toString()+")没有备案，请保证您上传的文件和下载时同名<br>";
		    return false;
		  }
		}
              //建立文件
                File f1=new File(vSaveFileName.elementAt(count).toString());
                os=new FileOutputStream(f1);

                byte[] img=vFile.elementAt(k).toString().getBytes("ISO8859_1");

                //写入数据
                for (int m=0;m<img.length;m++){
                  os.write(img[m]);
                }
                os.close();
                saveS=true;
		count=count+1;
            }

          }catch(Exception e){
                sMessage=sMessage+"上传文件失败，可能是上传的文件的路径错误或没有上传文件<br>";
                vSaveFilesName=null;
		System.out.println(e);
            return false;
          }
        }
      }
            if(saveS) {
              sMessage=sMessage+"文件上传成功<br>";
            }else{
                sMessage=sMessage+"上传文件失败，可能是上传的文件的路径错误或没有上传文件<br>";
                vSaveFilesName=null;
              }
      return saveS;
    }


  /**<b>得到当前时间的数字字符</b><br><br>
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

  /**<b>存储的上传文件的名称</b><br><br>
  *
  */
    public Vector getFilename(){
        return vSaveFilesName;
    }

  /**<b>错误信息提示</b><br><br>
  *
  */
    public String getMessage(){
        return sMessage;
    }

  /**<b>判断文件的类型、大小，如果不符合要求则生成错误信息</b><br><br>
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
            sMessage=sMessage+vInputName.elementAt(i).toString()+"(第"+String.valueOf(i+1)+"个上传文件:"+vFileName.elementAt(i).toString()+"),上传失败，类型错误<br>";
            returnValue=false;
          }
          /**
          if (Integer.valueOf(vFileSize.elementAt(i).toString()).intValue()>51200){
            sMessage=sMessage+vInputName.elementAt(i).toString()+"(第"+String.valueOf(i+1)+"个上传文件："+vFileName.elementAt(i).toString()+"),上传失败，文件的大小不能大于50K.<br>";
            returnValue=false;
          }
          */
        }
      }
      return returnValue;
    }

  /**<b>对每个文件进行仔细的分割，取出文件名称，文件的后缀名、文件的大小和文件的读入流</b><br><br>
  * @param sFile 每个上传文件的详细数据流字符
  */
    private boolean divideEachFile(String sFile){

      //得到Input名称
      start1=sFile.indexOf("form-data; name=") ;
      sFile=sFile.substring(start1) ;

      start1=sFile.indexOf("\"") ;
      sFile=sFile.substring(start1+1) ;
      start1=sFile.indexOf("\"") ;

      vInputName.addElement(sFile.substring(0,start1)) ;

      //得到文件的全名
      start1=sFile.indexOf("filename=") ;
      fileName="";

      if (start1>=0)
      {
	  sFile=sFile.substring(start1+1) ;

	  start1=sFile.indexOf("\r\n")-1;//换行
	  fileName=sFile.substring(9,start1) ;
	  try{
	    b=fileName.getBytes("ISO8859_1") ;
	    fileName=new String(b);
	  }catch( UnsupportedEncodingException e) {
	    e.printStackTrace();
	  }
      }

      //如果名字不为空
      if (fileName.length()>0){
        start1=fileName.lastIndexOf("\\");
        fileName=fileName.substring(start1+1) ;

        vFileName.addElement(fileName) ;

        //得到文件的后缀名（.gif）
        start1=fileName.indexOf(".");
        fileName=fileName.substring(start1) ;

        vFileType.addElement(fileName) ;
      }else{
        vFileName.addElement("") ;
        vFileType.addElement("") ;
      }

      //得到文件的MIME类型
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


      //文件的大小
      vFileSize.addElement(String.valueOf(sFile.length())) ;

      return true;
    }

  /**<b>对整个上传文件的HTML读入流进行单个文件的分割</b><br><br>
  *
  */
    private boolean divideFile(){

      getByte();//b

      //得到数据
      try{
        temp=new String(b,"ISO8859_1");
      }catch(UnsupportedEncodingException e){
        return false;
      }

      //分割成单个文件
      while (temp.indexOf("Content-Disposition")>0){
        start1=temp.indexOf("Content-Disposition");
        temp=temp.substring(start1);
        start2=temp.indexOf(sListSeparator);
        int k=temp.indexOf("Content-Type:");

        //判断是否为submit按钮
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
