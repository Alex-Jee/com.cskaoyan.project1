package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.User;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.service.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    Gson gson=new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String requestURI=request.getRequestURI();
        String action=requestURI.replace("/api/admin/user/","");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI=request.getRequestURI();
        String action=requestURI.replace("/api/admin/user/","");
        if("allUser".equals(action)){
            allUser(request,response);
        }else if("searchUser".equals(action.substring(0,10))){
            searchUser(request,response);
        }else if("deleteUser".equals(action.substring(0,10))){
            deleteUser(request,response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param=request.getParameter("id");
        int id=new Integer(param);
        int status=userService.deleteUser(id);
        Result result = new Result();
        if(status==0){
            result.setCode(500);
            result.setMessage("500:服务器异常！");
        }else{
            result.setCode(200);
            result.setMessage("删除成功");
        }
        response.getWriter().print(gson.toJson(result));
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param=request.getParameter("word");
        List<User> data=userService.searchUser(param);
        Result result=new Result(data);
        response.getWriter().print(gson.toJson(result));
    }

    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> data=userService.allUser();
        Result result = new Result(data);
        response.getWriter().print(gson.toJson(result));
    }
}
