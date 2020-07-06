package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.service.AdminServiceImpl;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * User: zsquirrel
 * Date: 2020/4/28
 * Time: 4:00 下午
 */
@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        // login allAdmins...
        String action = requestURI.replace("/api/admin/admin/", "");
        if("login".equals(action)){
            login(request, response);
        }else if("getSearchAdmins".equals(action)){
            getSearchAdmins(request,response);
        }else if("addAdminss".equals(action)){
            addAdminss(request,response);
        }
    }

    private void addAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody=HttpUtils.getRequestBody(request);
        Admin admin=gson.fromJson(requestBody,Admin.class);
        int code=adminService.addAdminss(admin);
        Result result = new Result();
        if(code==200){
            result.setCode(200);
            result.setMessage("创建管理员成功！");
        }else if(code==500){
            result.setCode(500);
            result.setMessage("服务器异常！");
        }else if(code==403){
            result.setCode(403);
            result.setMessage("用户已存在，创建失败!");
        }
        response.getWriter().print(gson.toJson(result));
    }

    /**
     * 登录的具体业务逻辑
     * 1.抓包：抓请求报文（服务器上面、localhost）
     * 2.分析逻辑
     * 3.返回响应报文（响应体，格式是什么样的呢？抓包服务器）
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody, Admin.class);
        //接下来应该怎么做
        int code = adminService.login(admin);
        // code service 返回  dao返回
        Result result = new Result();
        if(code == 200){
            //返回正确的结果
            result.setCode(0);
            HashMap<Object, Object> map = new HashMap<>();
            map.put("token", admin.getEmail());
            map.put("name", admin.getEmail());
            result.setData(map);
        }else{
            //返回错误的信息 需要返回10000
            result.setCode(10000);
            result.setMessage("服务器异常");
        }
        response.getWriter().println(gson.toJson(result));
    }

    /**
     * 简单版多条件查询实现：admin管理员账户信息
     * @param request
     * @param response
     */
    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin=gson.fromJson(requestBody,Admin.class);
        List<Admin> admins=adminService.getSearchAdmins(admin);
        Result result = new Result();
        result.setCode(0);
        result.setData(admins);
        response.getWriter().println(gson.toJson(result));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if("allAdmins".equals(action)){
            allAdmins(request, response);
        }
    }

    /**
     * 展示所有的管理员信息
     * 1.抓包---没有请求参数
     * 2.分析逻辑
     * 3.抓包，响应体应该是什么
     * @param request
     * @param response
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.allAdmins();
        Result result = new Result();
        result.setCode(0);
        result.setData(adminList);
        response.getWriter().println(gson.toJson(result));
    }
}
