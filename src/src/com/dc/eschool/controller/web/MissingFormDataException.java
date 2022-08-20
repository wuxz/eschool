package com.dc.eschool.controller.web;

import java.util.Collection;

/**
 * This exception is thrown by the RequestToEventTranslator
 * when a user fails to provide enough form information. This
 * excption contains list of form fields needed. This exception
 * is used by a JSP page to generate an error page.
 * @author Eric CHEN
 */
public class MissingFormDataException extends Exception implements java.io.Serializable
{

    private Collection missingFields;
    private String message;

    public MissingFormDataException(String message, Collection missingFields)
    {
        this.message = message;
        this.missingFields = missingFields;
    }

    public Collection getMissingFields()
    {
        return missingFields;
    }

    public String getMessage()
    {
        return message;
    }

}
