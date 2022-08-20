package com.dc.eschool.util;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public final class Debug
{
    public static final boolean debuggingOn = true;

    public static void assert(boolean condition)
    {
        if (!condition)
	{
            println("Assert Failed: ");
            throw new IllegalArgumentException();
        }
    }

    public static void print(String msg)
    {
        if (debuggingOn)
	{
            System.out.print(msg);
        }
    }

    public static void println(String msg)
    {
        if (debuggingOn)
	{
            System.out.println(msg);
        }
    }

    public static void print(Exception e, String msg)
    {
        print((Throwable)e, msg);
    }

    public static void print(Exception e)
    {
        print(e, null);
    }

    public static void print(Throwable t, String msg)
    {
        if (debuggingOn)
	{
            System.out.println("Received throwable with Message: "+t.getMessage());
            if (msg != null)
                System.out.print(msg);
            t.printStackTrace();
        }
    }

    public static void print(Throwable t)
    {
        print(t, null);
    }
}