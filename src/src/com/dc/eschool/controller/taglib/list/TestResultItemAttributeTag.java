package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.testresultsitem.model.TestResultsItemModel;
import com.dc.eschool.util.*;

/**
 * Title: Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company: DC
 * @author wangshui
 * @version 1.0
 */

public class TestResultItemAttributeTag extends ItemAttributeTag
{
  protected String createText()
  {
	TestResultsItemModel tm = (TestResultsItemModel)item;
	if(attribute == null) return "";
	if(tm != null)
	{
	  if(attribute.equalsIgnoreCase("TestResultItemID"))
	  {
		  return InterpretSQL.transformChinese(tm.getTestResultItemID()+"");
	  }
	  else if(attribute.equalsIgnoreCase("Student"))
	  {
		return InterpretSQL.transformChinese(tm.getStudent() + "");
	  }
	  else if(attribute.equalsIgnoreCase("Right"))
	  {
		return InterpretSQL.transformChinese(tm.getRight());
	  }
	  else if(attribute.equalsIgnoreCase("AnswerMark"))
	  {
		  return InterpretSQL.transformChinese(tm.getAnswerMark() + "");
	  }
	  else if(attribute.equalsIgnoreCase("Answer"))
	  {
		return InterpretSQL.transformChinese(tm.getAnswer());
	  }
	  else if(attribute.equalsIgnoreCase("AnswerItemID"))
	  {
		return InterpretSQL.transformChinese(tm.getAnswerItemID() + "");
	  }
	  else if(attribute.equalsIgnoreCase("ContentID"))
	  {
		return InterpretSQL.transformChinese(tm.getContentID() + "");
	  }
	  else if(attribute.equalsIgnoreCase("AnswerNumber"))
	  {
		return InterpretSQL.transformChinese(tm.getAnswerNumber());
	  }
	  else if(attribute.equalsIgnoreCase("RightAnswer"))
	  {
		  return InterpretSQL.transformChinese(tm.getRightAnswer());
	  }
	  else if(attribute.equalsIgnoreCase("RightChecked"))
	  {
		return "right".equals(tm.getRight()) ? " checked " : "";
	  }
	  else if(attribute.equalsIgnoreCase("WrongChecked"))
	  {
		return "wrong".equals(tm.getRight()) ? " checked " : "";
	  }
	}else {
	  return "";
	}
	return "";
  }
}