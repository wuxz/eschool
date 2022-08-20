package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.answeritem.model.AnswerItemModel;
import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.AnswerItemEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.MissingFormDataException;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.InterpretSQL;

/**
 * AnswerItem Handler
 *
*/
public class AnswerItemHandler extends RequestHandlerSupport
{
    public MainEvent processRequest(HttpServletRequest request) throws ControllerException
    {
        Debug.println("Started creation of an AnswerItem Event");

        String action = request.getParameter("action");
        Debug.println("AnswerItemHandler (web): action=" + action);
        if (action == null)
        {
            return null;
        } else if (action.equals("createansweritem"))
        {
            return createNewAnswerItemEvent(request);
        } else if (action.equals("updateansweritem"))
        {
            return createUpdateAnswerItemEvent(request);
        } else if (action.equals("deleteansweritem"))
        {
            return createDeleteAnswerItemEvent(request);
        }
        return null;
    }

    private MainEvent createNewAnswerItemEvent(HttpServletRequest request)
    {
        AnswerItemEvent event = new AnswerItemEvent();
	AnswerItemModel am=new AnswerItemModel();
	ArrayList missingFields = null;

        String type=request.getParameter("answertype").trim();
	if (type.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("类型"));
        }

        String itemNumberStr= request.getParameter("itemNumber").trim();
	if (itemNumberStr.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("题号"));
        }

        String answerNumber=request.getParameter("answerNumber").trim();
	if (answerNumber.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("备选项"));
        }

        String answer=request.getParameter("answer").trim();
	if (answer.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("答案"));
        }

        String memo=request.getParameter("memo").trim();
	if (memo.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("备注"));
        }

	if (missingFields != null)
	{
            MissingFormDataException ex = new MissingFormDataException(InterpretSQL.transformChinese("需确认的数据"), missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

        Integer itemNumber= new Integer(itemNumberStr);
	Integer contentID=new Integer(request.getParameter("contentID"));

	am.setType(InterpretSQL.transform(type));
	am.setItemNumber(itemNumber);
	am.setAnswerNumber(InterpretSQL.transform(answerNumber));
	am.setAnswer(InterpretSQL.transform(answer));
	am.setMemo(InterpretSQL.transform(memo));
	am.setContentID(contentID);

        event.setActionType(event.CREATE_ANSWERITEM);
	event.setAm(am);
        return event;
    }

    private MainEvent createUpdateAnswerItemEvent(HttpServletRequest request)
    {
        AnswerItemEvent event = new AnswerItemEvent();
	AnswerItemModel am=new AnswerItemModel();

	ArrayList missingFields = null;

        String type=request.getParameter("answertype").trim();
	if (type.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("类型"));
        }

        String itemNumberStr= request.getParameter("itemNumber").trim();
	if (itemNumberStr.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("题号"));
        }

        String answerNumber=request.getParameter("answerNumber").trim();
	if (answerNumber.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("备选项"));
        }

        String answer=request.getParameter("answer").trim();
	if (answer.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("答案"));
        }

        String memo=request.getParameter("memo").trim();
	if (memo.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add(InterpretSQL.transformChinese("备注"));
        }

	if (missingFields != null)
	{
            MissingFormDataException ex = new MissingFormDataException(InterpretSQL.transformChinese("需确认的数据"), missingFields);
            request.setAttribute(WebKeys.MissingFormDataKey, ex);
            return null;
        }

        Integer itemNumber= new Integer(itemNumberStr);
        Integer answerItemID=new Integer(request.getParameter("answerItemId").trim());
        Integer contentID=new Integer(request.getParameter("contentID"));

        am.setAnswerItemID(answerItemID);
	am.setType(InterpretSQL.transform(type));
	am.setItemNumber(itemNumber);
	am.setAnswerNumber(InterpretSQL.transform(answerNumber));
	am.setAnswer(InterpretSQL.transform(answer));
	am.setMemo(InterpretSQL.transform(memo));
	am.setContentID(contentID);

        event.setActionType(event.UPDATE_ANSWERITEM);
	event.setAm(am);

        return event;
    }

    private MainEvent createDeleteAnswerItemEvent(HttpServletRequest request)
    {
        AnswerItemEvent event = new AnswerItemEvent();
	AnswerItemModel am=new AnswerItemModel();

        Integer answerItemID=new Integer(request.getParameter("answerItemId").trim());

	am.setAnswerItemID(answerItemID);

        event.setActionType(event.DELETE_ANSWERITEM);
	event.setAm(am);

        return event;
    }
}

