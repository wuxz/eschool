package com.dc.eschool.examinationscore.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ExaminationScoreDeleteException extends Exception
{
    public ExaminationScoreDeleteException()
    {
      super();
    }

    public ExaminationScoreDeleteException(String str)
    {
      super(str);
    }

    public ExaminationScoreDeleteException(Exception e)
    {
      super("ExaminationScoreDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
    }
}