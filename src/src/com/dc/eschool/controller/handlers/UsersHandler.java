package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.UsersEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.UsersWebImpl;
import com.dc.eschool.controller.web.MissingFormDataException;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.InterpretSQL;

/**
 * Users Handler
 *
*/
public class UsersHandler extends RequestHandlerSupport
{


    public MainEvent processRequest(HttpServletRequest request) throws ControllerException
    {
        Debug.println("Started creation of an Users Event");
        String action = request.getParameter("action");
        Debug.println("UsersHandler (web): action=" + action);
        if (action == null)
        {
            return null;
        } else if (action.equals("createUser"))
        {
            return createNewUsersEvent(request);
        } else if (action.equals("updateUser"))
        {
            return createUpdateUsersEvent(request);
        }
        return null;
    }

    private MainEvent createNewUsersEvent(HttpServletRequest request)
    {
        UsersEvent event = new UsersEvent();
	UsersModel um =new UsersModel();
	ArrayList missingFields = null;

        String loginName = request.getParameter("loginName").trim();
	if (loginName.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("登录名"));
        }

        String name=request.getParameter("name").trim();
	if (name.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("姓名"));
        }

        String gender=request.getParameter("gender").trim();
	if (gender.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("性别"));
        }

        String birthday=request.getParameter("birthday").trim();
	if (birthday.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("生日"));
        }

        String tel=request.getParameter("tel").trim();
	if (tel.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("电话"));
        }

        String email=request.getParameter("email").trim();
	if (email.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("邮件地址"));
        }

        String address=request.getParameter("address").trim();
	if (address.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("通讯地址"));
        }

        Integer classID=new Integer(request.getParameter("classID").trim());
	if (classID.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("组别"));
        }

        String userType=request.getParameter("userType").trim();
	if (userType.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("用户身份"));
        }

        String state=request.getParameter("state").trim();
	if (state.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("状态"));
        }

	if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException(InterpretSQL.transformChinese("需确认的用户数据"), missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

	um.setLoginName(InterpretSQL.transform(loginName));
	um.setName(InterpretSQL.transform(name));
	um.setGender(InterpretSQL.transform(gender));
	um.setBirthday(InterpretSQL.transform(birthday));
	um.setTel(InterpretSQL.transform(tel));
	um.setEmail(InterpretSQL.transform(email));
	um.setAddress(InterpretSQL.transform(address));
	um.setClassID(classID);
	um.setUserType(InterpretSQL.transform(userType));
	um.setState(InterpretSQL.transform(state));

        event.setActionType(event.CREATE_USER);
	event.setUm(um);
        return event;
    }

    private MainEvent createUpdateUsersEvent(HttpServletRequest request)
    {
        UsersEvent event = new UsersEvent();
	UsersModel um= new UsersModel();
	ArrayList missingFields = null;

        Integer userID=new Integer(request.getParameter("userId").trim());

        String loginName = request.getParameter("loginName").trim();
	if (loginName.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("登录名"));
        }

        String name=request.getParameter("name").trim();
	if (name.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("姓名"));
        }

        String gender=request.getParameter("gender").trim();
	if (gender.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("性别"));
        }

        String birthday=request.getParameter("birthday").trim();
	if (birthday.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("生日"));
        }

        String tel=request.getParameter("tel").trim();
	if (tel.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("电话"));
        }

        String email=request.getParameter("email").trim();
	if (email.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("邮件地址"));
        }

        String address=request.getParameter("address").trim();
	if (address.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("通讯地址"));
        }

        Integer classID=new Integer(request.getParameter("classID").trim());
	if (classID.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("组别"));
        }

        String userType=request.getParameter("userType").trim();
	if (userType.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("用户身份"));
        }

        String state=request.getParameter("state").trim();
	if (state.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("状态"));
        }

	if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException("Missing Address Data", missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }
        um.setUserID(userID);
	um.setLoginName(InterpretSQL.transform(loginName));
	um.setName(InterpretSQL.transform(name));
	um.setGender(InterpretSQL.transform(gender));
	um.setBirthday(InterpretSQL.transform(birthday));
	um.setTel(InterpretSQL.transform(tel));
	um.setEmail(InterpretSQL.transform(email));
	um.setAddress(InterpretSQL.transform(address));
	um.setClassID(classID);
	um.setUserType(InterpretSQL.transform(userType));
	um.setState(InterpretSQL.transform(state));

        event.setActionType(event.UPDATE_USER);
	event.setUm(um);

        return event;
    }
}

