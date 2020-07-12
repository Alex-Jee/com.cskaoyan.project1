package com.cskaoyan.mall.controller.mall;

import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.model.Type;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.service.GoodsServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/mall/index/*")
public class IndexServlet extends HttpServlet {
    Gson gson=new Gson();
    private GoodsService goodsService=new GoodsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/index/", "");
        if("getType".equals(action)){
            getType(request,response);
        }
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList=goodsService.getType();
        Result result = new Result(typeList);
        response.getWriter().print(gson.toJson(result));
    }
}
