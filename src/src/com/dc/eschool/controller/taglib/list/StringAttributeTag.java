package com.dc.eschool.controller.taglib.list;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class StringAttributeTag extends ItemAttributeTag
{

  public StringAttributeTag() {}
  protected String createText()
  {

    if ((attribute == null)) return ((String) item);
    else
        if(attribute.equalsIgnoreCase("text")) return ((String) item);
    return(null);
  }
}