package com.dc.eschool.util;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;


/**
 * This class is a Singleton and thepurpose is to read the properties from
 * property files to get the upload information such path as upload path.
 */
public class PropertiesManager {
    static private PropertiesManager instance;       // The single instance
    static private int clients;

    private Vector drivers = new Vector();
    private PrintWriter log;
    private Hashtable pools = new Hashtable();
    private String uploadPath;
    private String vitualPath;
    private String ftpPath;
    private String mailPath;

    /**
     * A private constructor since this is a Singleton
     */
    public PropertiesManager() {
        init();
    }


    /**
     * Read the properties from propertity file
     *
     * @param props The property file's properties
     */
    private void loadProperties(Properties props) {
        Enumeration propNames = props.propertyNames();
        while (propNames.hasMoreElements()) {
            String name = (String) propNames.nextElement();
            if (name.endsWith(".uploadPath")) {
                String poolName = name.substring(0, name.lastIndexOf("."));
                uploadPath = props.getProperty(poolName + ".uploadPath");
                if (uploadPath == null) {
                    log("No uploadPath specified for " + poolName);
                    continue;
                }
                vitualPath = props.getProperty(poolName + ".vitualPath");
                ftpPath = props.getProperty(poolName + ".ftpPath");
                mailPath = props.getProperty(poolName + ".mailPath");
            }
        }
    }

    /**
     * Loads properties and initializes the instance with its values.
     */
    private void init()
    {
        InputStream is = getClass().getResourceAsStream("/eschool.properties");
        Properties dbProps = new Properties();
        try {
            dbProps.load(is);
        }
        catch (Exception e) {
            System.err.println("Can't read the properties file. " +
                "Make sure eschool.properties is exist ");
            return;
        }
        loadProperties(dbProps);
    }

    /**
     * Writes a message to the log file.
     */
    private void log(String msg) {
        log.println(new Date() + ": " + msg);
    }

    public String getUploadPath()
    {
      return uploadPath;
    }

    public String getVitualPath()
    {
      return vitualPath;
    }

    public String getFtpPath()
    {
      return ftpPath;
    }

    public String getMailPath()
    {
      return mailPath;
    }

}
