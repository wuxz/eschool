package com.dc.eschool.controller.taglib.list;

import com.dc.eschool.eclass.model.EClassModel;
import com.dc.eschool.controller.taglib.list.UserDetailsTag;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.util.InterpretSQL;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class EClassAttributeTag extends ItemAttributeTag
{
    protected String createText()
    {
        EClassModel em = (EClassModel)item;
        if(attribute == null) return "";

        if(em.getClassID()!=null)
        {
          if(attribute.equalsIgnoreCase("classID"))
          {
              return em.getClassID()+"";
          }else if(attribute.equalsIgnoreCase("selecteduserid"))
          {
              UserDetailsTag user = (UserDetailsTag)
                  findAncestorWithClass(this, UserDetailsTag.class);

              if (user == null)
                return "";

              UsersModel um = (UsersModel)user.getCurrentUser();

              if(um.getClassID()!=null)
              {
                if (um.getClassID().equals(em.getClassID()))
                return " selected";
              }else
              {
                return "";
              }
          }else if(attribute.equalsIgnoreCase("name"))
          {
              return InterpretSQL.transformChinese(em.getName());
          }else if(attribute.equalsIgnoreCase("info"))
          {
              return InterpretSQL.transformChinese(em.getInfo());
          }
        }else
        {
          return "";
        }
        return "";
    }
}