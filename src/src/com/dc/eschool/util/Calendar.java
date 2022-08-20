package com.dc.eschool.util;

import java.util.Date;
import java.text.DateFormat;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.Locale;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * This class represents a calender
 */
public class Calendar implements java.io.Serializable
{
    public static final int MONTH = java.util.Calendar.MONTH;
    public static final int DATE = java.util.Calendar.DATE;
    public static final int YEAR = java.util.Calendar.YEAR;
    public static final int HOUR = java.util.Calendar.HOUR_OF_DAY;
    public static final int MINUTE = java.util.Calendar.MINUTE;
    public static final int SECOND = java.util.Calendar.SECOND;

    private int month;
    private int day;
    private int year;

    private int hour;
    private int minute;
    private int second;

    private Calendar(int year, int month, int day, int hour, int min, int sec)
    {
        this.month = month;
        this.day= day;
        this.year = year;
        this.hour = hour;
        this.minute = min;
        this.second = sec;
    }

    public static Calendar getInstance()
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        int m = c.get(java.util.Calendar.MONTH);
        int d = c.get(java.util.Calendar.DATE);
        int y = c.get(java.util.Calendar.YEAR);
        int h = c.get(java.util.Calendar.HOUR_OF_DAY);
        int mm = c.get(java.util.Calendar.MINUTE);
        int s = c.get(java.util.Calendar.SECOND);
        return new Calendar(y,m,d,h,mm,s);
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getYear()
    {
        return year;
    }

    public void set(int year, int month, int day)
    {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public void set(int target, int value)
    {
        switch (target)
	{
            case java.util.Calendar.MONTH :
                this.month = value;
                break;
            case java.util.Calendar.YEAR :
                this.year = value;
                break;
            case java.util.Calendar.DATE :
                this.day = value;
                break;
        }
    }

    public int get(int target)
    {
        switch (target)
	{
            case java.util.Calendar.MONTH : return this.month;
            case java.util.Calendar.YEAR : return this.year;
            case java.util.Calendar.DATE : return this.day;
            default: return -1;
        }

    }

    public void setTime(java.util.Date date)
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        this.day = c.get(java.util.Calendar.DATE);
        this.month = c.get(java.util.Calendar.MONTH);
        this.year = c.get(java.util.Calendar.YEAR);
    }

    public void clear()
    {
        this.day = -1;
        this.month = -1;
        this.year = -1;
    }

   /**
    * @return the date encoded in the format  mm/yyyy
    */
    public String getExpiryDateString()
    {
        return ((month > 10)? "0" : "") + month + "/" + ((year > 10)? "0" : "") + year;
    }

   /**
    * @return the date encoded in the format  based on locale
    */
    public String getFullDateString(Locale locale)
    {
        java.util.Calendar c = java.util.Calendar.getInstance(locale);
        c.set(year,month,day);
        Date d = c.getTime();
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT,locale);
        return df.format(d);
    }

   /**
    * @return the date encoded in the format  mm/dd/yyyy
    */
    public String getFullDateString()
    {
        return ((month + 1 < 10)? "0" : "") + (month + 1) + "/" + ((day < 10)? "0" : "") + day + "/" + ((year < 10)? "0" : "") + year;
    }

   /**
    * @return the date encoded in the format  yyyymmdd
    */
    public String getESchoolDateString()
    {
        return (year) + ((month + 1 < 10)? "0" : "") + (month + 1) + ((day < 10)? "0" : "") + day;
    }
    public String getESchoolTimeString()
    {
      return ((hour<10)? "0":"")+ hour+((minute<10)?"0":"")+minute+((second<10)?"0":"")+second;
    }
    /**
     * @return the date encoded in the JDBC format, {d 'yyyy-mm-dd'}
     */

    public String getJDBCDateString()
    {
        return "{d '" + ((year<10) ? "0" : "") + year + "-"
            + ((month+1 < 10) ? "0" : "") + (month+1) + "-"
            + ((day<10) ? "0" : "") + day + "'}";
    }

    public String toString(){
        return "[Year=" + year + ", Month=" + month + ", Day=" + day + "]";
    }
}