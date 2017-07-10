package com.insigma.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;

/**
 * Created by Administrator on 2014-12-17.
 */


@Controller

public class BaseController {

    /**
     * �ɹ�����
     * @param message
     * @return
     */
    public AjaxReturnMsg success(String message) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        return dto;
    }


    /**
     * �ɹ�����
     * @param message
     * @param o
     * @return
     */
    public AjaxReturnMsg success(String message,Object o) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        dto.setObj(o);
        return dto;
    }

    /**
     * �ɹ�����
     * @param o
     * @return
     */
    public AjaxReturnMsg success(Object o) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(o);
        return dto;
    }
    /**
     * �ɹ�����
     * @param PageInfo pageinfo
     * @return
     */
    public AjaxReturnMsg success(PageInfo pageinfo) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(pageinfo.getList());
        dto.setTotal(pageinfo.getTotal());
        return dto;
    }

    /**
     * �ɹ�����
     * @param response
     * @param message
     * @throws IOException
     */
    public void success(HttpServletResponse response,String message) throws IOException {
        response.setCharacterEncoding("GBK");
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }


    /**
     * �ɹ�����
     * @param response
     * @param message
     * @param o
     * @throws IOException
     */
    public void success(HttpServletResponse response,String message,Object o) throws IOException{
        response.setCharacterEncoding("GBK");
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        dto.setObj(o);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * �ɹ�����
     * @param response
     * @param o
     * @throws IOException
     */
    public void success(HttpServletResponse response,Object o) throws IOException {
        response.setCharacterEncoding("GBK");
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(o);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }
    
    /**
     * ���󷵻�
     * @param message
     * @return
     */
    public AjaxReturnMsg error(String message) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        return dto;
    }

    /**
     * ���󷵻�
     * @param message
     * @param obj
     * @return
     */
    public AjaxReturnMsg error(String message,Object obj) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        dto.setObj(obj);
        return dto;
    }

    /**
     * ���󷵻�
     * @param o
     * @return
     */
    public AjaxReturnMsg error(Object o) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setObj(o);
        return dto;
    }

    /**
     * ���󷵻�
     * @param e
     * @return
     */
    public AjaxReturnMsg error(Exception e) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(e.getLocalizedMessage());
        return dto;
    }


    /**
     * ���󷵻�
     * @param message
     * @return
     */
    public void error(HttpServletResponse response,String message) throws IOException{
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * ���󷵻�
     * @param response
     * @param message
     * @param obj
     * @throws IOException
     */
    public void error(HttpServletResponse response,String message,Object obj) throws IOException{
        response.setCharacterEncoding("GBK");
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        dto.setObj(obj);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * ���󷵻�
     * @param response
     * @param o
     * @throws IOException
     */
    public void error(HttpServletResponse response,Object o) throws IOException{
        response.setCharacterEncoding("GBK");
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setObj(o);
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * ���󷵻�
     * @param response
     * @param e
     * @throws IOException
     */
    public void error(HttpServletResponse response,Exception e) throws IOException {
        response.setCharacterEncoding("GBK");
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(e.getLocalizedMessage());
        JSONObject jsonObject=JSONObject.fromObject(dto);
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }
    /**
     * ��ajax����dto���س��ַ���
     * @param msg
     * @return
     */
    public String AjaxMsgtoString(AjaxReturnMsg msg){
        return JSONObject.fromObject(msg).toString();
    }

}
