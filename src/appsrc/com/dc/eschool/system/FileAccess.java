//Source file: D:\\yan_work\\eschool\\src\\com\\dc\\eschool\\Communication\\FileAccess.java


/**
 * Title:        FileAccess
 * Description:  文件读取保存
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
package com.dc.eschool.system;
import java.awt.FileDialog;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.util.Enumeration;
import org.w3c.dom.*;
import java.io.File;
public class FileAccess
{
    public FileAccess()
    {
    }

   /**
    * MOTHOD: fileWrite
    * DESC  : 将组数据保存到文件
    * CREATE: 1.0,Ardy,2001-10-10
    * MODIFY:
    */
    public boolean xmlFileWrite(Document groupDoc,String fileName)
    {
        try
        {
            System.out.println("fileName"+fileName);
            if ( fileName == null ) return false;
            TransformerFactory tfactory = TransformerFactory.newInstance();
            Transformer transformer= tfactory.newTransformer();
            transformer.transform( new DOMSource(groupDoc),new StreamResult(new File(fileName)));
        }
        catch (Exception ex)
        {
            System.out.println("File write: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        return true;
    }
   /**
    * MOTHOD: fileRead
    * DESC  : 从文件中读取组数据
    * CREATE: 1.0,Ardy,2001-10-10
    * MODIFY:
    */
    public Document xmlFileRead(String fileName)
    {
        Document groupDoc=null;
        DocumentBuilderFactory documentBuilderFactory;
        DocumentBuilder documentBuilder;
        try
        {
            documentBuilderFactory=DocumentBuilderFactory.newInstance();
            documentBuilder=documentBuilderFactory.newDocumentBuilder();
            System.out.println("fileName"+fileName);
            if ( fileName == null ) return null;
            groupDoc = documentBuilder.parse( new File(fileName));
        }
        catch (Exception ex)
        {
            System.out.println("File read: " + ex.getMessage());
            ex.printStackTrace();
        }
        return groupDoc;
    }
}
