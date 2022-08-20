package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.scorestatistic.model.ScoreStatisticModel;
import com.dc.eschool.answeritem.ejb.*;
import com.dc.eschool.answeritem.model.*;
import com.dc.eschool.util.*;

/**
 * Title: Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company: DC
 * @author wangshui
 * @version 1.0
 */

public class ScoreStatisticAttributeTag extends ItemAttributeTag
{
	AnswerItemEBHome answerItemEBHome = null;

  protected String createText()
  {

	ScoreStatisticModel sm = (ScoreStatisticModel)item;
	if(attribute == null) return "";

	if(sm != null)
	{
	  if(attribute.equalsIgnoreCase("ScoreStatisticID"))
	  {
		  return InterpretSQL.transformChinese(sm.getScoreStatisticID()+"");
	  }
	  else if(attribute.equalsIgnoreCase("Student"))
	  {
		return InterpretSQL.transformChinese(sm.getStudent() + "");
	  }
	  else if(attribute.equalsIgnoreCase("AnswerItemID"))
	  {
		return InterpretSQL.transformChinese(sm.getAnswerItemID() + "");
	  }
	  else if(attribute.equalsIgnoreCase("ProjectContentID"))
	  {
		  return InterpretSQL.transformChinese(sm.getProjectContentID()+"");
	  }
	  else if(attribute.equalsIgnoreCase("Statistic"))
	  {
		return InterpretSQL.transformChinese(sm.getStatistic());
	  }
	  else if(attribute.equalsIgnoreCase("Allow"))
	  {
		return sm.getAllow();
	  }
	  else if(attribute.equalsIgnoreCase("RightAnswer"))
	  {
		return InterpretSQL.transformChinese(sm.getRightAnswer() + "");
	  }
	  else if(attribute.equalsIgnoreCase("WrongAnswer"))
	  {
		return InterpretSQL.transformChinese(sm.getWrongAnswer() + "");
	  }
	  else if(attribute.equalsIgnoreCase("ItemNumber"))
	  {
		try
		{
			if (answerItemEBHome == null)
				answerItemEBHome = EJBUtil.getAnswerItemEBHome();

			return answerItemEBHome.findByPrimaryKey(new Integer(sm.getAnswerItemID())).getDetails().getItemNumber().toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			return "";
		}
	  }
	}else {
	  return "";
	}
	return "";
  }
}