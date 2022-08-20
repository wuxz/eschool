package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.answeritem.model.AnswerItemModel;
import com.dc.eschool.util.InterpretSQL;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class AnswerItemAttributeTag extends ItemAttributeTag
{
    protected String createText()
    {
        AnswerItemModel em = (AnswerItemModel)item;

        if(attribute == null) return "";
        if(em.getAnswerItemID()!=null)
        {
          if(attribute.equalsIgnoreCase("answerItemID"))
          {
              return em.getAnswerItemID()+"";
          }else if(attribute.equalsIgnoreCase("type"))
          {
              return InterpretSQL.transformChinese(em.getType());
          }else if(attribute.equalsIgnoreCase("answer"))
          {
              return InterpretSQL.transformChinese(em.getAnswer());
          }else if(attribute.equalsIgnoreCase("memo"))
          {
              return InterpretSQL.transformChinese(em.getMemo());
          }else if(attribute.equalsIgnoreCase("answerNumber"))
          {
              return InterpretSQL.transformChinese(em.getAnswerNumber());
          }else if(attribute.equalsIgnoreCase("itemNumber"))
          {
              return em.getItemNumber()+"";
          }
        }else
        {
          return "";
        }

        return "";
    }
}