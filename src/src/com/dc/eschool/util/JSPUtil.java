package com.dc.eschool.util;


import java.util.Locale;
import java.util.Vector;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.BreakIterator;
import java.util.Locale;
import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpSession;


/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class JSPUtil
{
    //access to eventCounter is only through the
    //accessor method getEventId()
    private static int eventCounter;

    /**
     * Converts a String SJIS or JIS URL encoded hex encoding to a Unicode String
     *
    */
    public static String convertJISEncoding(String target)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (target == null) return null;
        String paramString = target.trim();

        for (int loop =0; loop < paramString.length(); loop++)
	{
            int i = (int)paramString.charAt(loop);
            bos.write(i);
        }
        String convertedString = null;
        try
	{
            convertedString =  new String(bos.toByteArray(), "JISAutoDetect");
        } catch (java.io.UnsupportedEncodingException uex)
	{
	}
        return convertedString;
    }

    public static String formatCurrency(String amountString)
    {
        try
	{
            double amount = Double.parseDouble(amountString);
            return formatCurrency(amount);
        } catch (NumberFormatException ex)
	{
            return null;
        }
    }

    public static String formatCurrency(String amountString, Locale locale)
    {
        try
	{
            double amount = Double.parseDouble(amountString);
            return formatCurrency(amount, locale);
        } catch (NumberFormatException ex)
	{
            return null;
        }
    }

    public static String formatCurrency(double amount)
    {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat)nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "$###,###.00";
        df.applyPattern(pattern);
        return df.format(amount);
    }

    public static String formatPlainCurrency(double amount)
    {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat)nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "###,###";
        df.applyPattern(pattern);
        return df.format(amount);
    }

    public static String formatCurrency(double amount, Locale locale)
    {
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(amount);
    }

    public static Vector parseKeywords(String keywordString)
    {
        if (keywordString != null)
	{
            Vector keywords = new Vector();
            BreakIterator breakIt = BreakIterator.getWordInstance();
            int index=0;
            int previousIndex =0;
            breakIt.setText(keywordString);
            try
	    {
                while(index < keywordString.length())
		{
                    previousIndex = index;
                    index = breakIt.next();
                    String word = keywordString.substring(previousIndex, index);
                    if (!word.trim().equals("")) keywords.addElement(word);
                }
                return keywords;
            } catch (Throwable e)
	    {
                System.out.print(e.getMessage()+"Error while parsing search string");
            }

        }
        return null;
    }

    public static Vector parseKeywords(String keywordString, Locale locale)
    {
        if (keywordString != null)
	{
            Vector keywords = new Vector();
            BreakIterator breakIt = BreakIterator.getWordInstance(locale);
            int index=0;
            int previousIndex =0;
            breakIt.setText(keywordString);
            try{
                while(index < keywordString.length())
		{
                    previousIndex = index;
                    index = breakIt.next();
                    String word = keywordString.substring(previousIndex, index);
                    if (!word.trim().equals("")) keywords.addElement(word);
                }
                return keywords;
            } catch (Throwable e)
	    {
                System.out.print(e.getMessage()+":Error while parsing search string" );
            }

        }
        return null;
    }

    public static int getEventId()
    {
        return eventCounter++;
    }

    /**
     * Get the Locale specified in the session or return a default locale.
     */
    public static Locale getLocale(HttpSession session)
    {
        Locale locale = (Locale)session.getAttribute(WebKeys.LanguageKey);
        if (locale == null) locale = Locale.US;
        return locale;
    }

    /**
     * Get the Locale specified in the session or return a default locale.
     */
    public static Locale getLocaleFromLanguage(String language)
    {
        Locale locale = Locale.US;
        if (language.equals("English")) locale = Locale.US;

        //if following codes at next line causes errors, we have to comment it.
        else if (language.equals("Chinese")) locale = Locale.CHINA;
        else if (language.equals("Japanese")) locale = Locale.JAPAN;

        return locale;
    }

}