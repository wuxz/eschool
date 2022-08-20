package com.dc.eschool.content.util;

import java.io.Serializable;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class UploadFile implements Serializable
{
  private HttpServletRequest request = null;
  protected java.util.Hashtable pageElements;
  protected javax.servlet.ServletInputStream is;
  public static final String FILENAME_STRING = "\"; filename=\"";
  public static final String NAME_STRING =
                              "Content-Disposition: form-data; name=\"";

  private int filesize = 0;
  private String file = null;

  public UploadFile(HttpServletRequest request) throws Exception
  {
    Debug.println("construct uploadfile class");
    this.request = request;
    int formlength = request.getContentLength();
    byte[] bBuf = new byte[formlength];
    int iSize = 0;
    String buf = null;
    String str = "";
    is = request.getInputStream();
//    DataInputStream in = new DataInputStream(is);
    Debug.println("starting read buffer"+formlength);
    while( iSize < formlength)
    {
      //int byteread = in.available();
      //if(is.available() == 0) continue;
      //in.readFully();
      int byteread = is.read(bBuf,iSize,bBuf.length);
      iSize += byteread;
      if(iSize >= formlength)
      {
        break;
      }
      Debug.println(""+iSize);
    }

    str = new String(bBuf);
    bBuf = null;
//    in.close();
    is.close();
    Debug.println("ended read buffer"+str.length());
    String bound = str.substring(0,str.indexOf("\n"));
    Debug.println(bound);
    str = str.substring(str.indexOf("\n")+1);
    Debug.println(str);

    do
    {
      String section = str.substring(0,str.indexOf(bound)-1);
      str = str.substring(str.indexOf(bound)+bound.length()+1);
      String endkey = section.substring(NAME_STRING.length());
      String key = endkey.substring(0,endkey.indexOf("\""));
      endkey = endkey.substring(endkey.indexOf("\""));
      if(endkey.indexOf(FILENAME_STRING) != -1)
      {
        String filename = endkey.substring(0,endkey.indexOf("\r\n"));
        filename = filename.substring(filename.indexOf(FILENAME_STRING)+13,(filename.length()-1));
        //System.out.println("Filename :"+key+" "+filename);
        file = section.substring(section.indexOf("\r\n\r\n")+4);
        filesize = file.getBytes().length;
      }
      else
      {
        String value = section.substring(section.indexOf("\r\n\r\n")+4);
        pageElements.put(key,value);
      }

    } while(str.indexOf(bound) != -1);

    Debug.println("end loop to parse parameters");
    str = str.substring(0,(str.length()-bound.length()-4));
    String endkey = str.substring(NAME_STRING.length());
    String key = endkey.substring(0,endkey.indexOf("\""));
    endkey = endkey.substring(endkey.indexOf("\""));
    if(endkey.indexOf(FILENAME_STRING) != -1)
    {
      String filename = endkey.substring(0,endkey.indexOf("\r\n"));
      filename = filename.substring(filename.indexOf(FILENAME_STRING)+13,(filename.length()-1));
      file = str.substring(str.indexOf("\r\n\r\n")+4);
      filesize = file.getBytes().length;
    }
    else
    {
      String value = str.substring(str.indexOf("\r\n\r\n")+4);
      pageElements.put(key,value);
    }


  }
  public String getFile()
  {
    return file;
  }
  public int getFilesize()
  {
    return filesize;
  }
  public java.util.Hashtable getPageElements()
  {
    return pageElements;
  }
  public void writeToFile(String strFileName)  throws java.lang.Exception
  {
    File objFile = new File(strFileName);

    if ( objFile.isDirectory() )
      throw new Exception("can not write a directory.");

    if ( objFile.isFile() && objFile.exists())
      throw new Exception("file exsits");

    FileOutputStream streamFileOutput = new FileOutputStream(objFile);
    streamFileOutput.write(file.getBytes("GBK"));
    streamFileOutput.close();
  }


}