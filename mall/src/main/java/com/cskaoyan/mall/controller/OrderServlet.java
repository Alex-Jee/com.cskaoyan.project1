package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bo.ChangeOrderBO;
import com.cskaoyan.mall.bo.OrderBO;
import com.cskaoyan.mall.bo.PageOrderBO;
import com.cskaoyan.mall.com.cskaoyan.mall.vo.PageOrderVO;
import com.cskaoyan.mall.model.Result;
import com.cskaoyan.mall.service.OrderService;
import com.cskaoyan.mall.service.OrderServiceImpl;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.cskaoyan.mall.utils.HttpUtils.getRequestBody;

@WebServlet("/api/admin/order/*")
public class OrderServlet extends HttpServlet {
    private Gson gson=new Gson();
    private OrderService orderService=new OrderServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI=request.getRequestURI();
        String action=requestURI.replace("/api/admin/order/","");
        if("ordersByPage".equals(action)){
            ordersByPage(request,response);
        }else if("changeOrder".equals(action)){
            changeOrder(request,response);
        }
    }

    private void changeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody=HttpUtils.getRequestBody(request);
        ChangeOrderBO changeOrderBO=gson.fromJson(requestBody,ChangeOrderBO.class);
        orderService.changeOrder(changeOrderBO);
        Result result=new Result();
        result.setCode(0);
        response.getWriter().print(gson.toJson(result));
    }

    /**
     * 根据条件分页显示订单
     * @param request
     * @param response
     */
    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody= getRequestBody(request);
        PageOrderBO pageOrderBO=gson.fromJson(requestBody, PageOrderBO.class);
        PageOrderVO pageOrderVO=orderService.ordersByPage(pageOrderBO);
        Result result = new Result();
        result.setCode(0);
        result.setData(pageOrderVO);
        response.getWriter().print(gson.toJson(result));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI=request.getRequestURI();
        String action=requestURI.replace("/api/admin/order/","");
        if("order".equals(action)){
            order(request,response);
        }else if("deleteOrder".equals(action)){
            deleteOrder(request,response);
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        int orderId=new Integer(id);
        orderService.deleteOrder(orderId);
        Result result = new Result();
        result.setCode(0);
        response.getWriter().print(gson.toJson(result));
    }

    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id=new Integer(request.getParameter("id"));
        OrderBO orderBO=orderService.order(id);
        Result result = new Result();
        result.setCode(0);
        result.setData(orderBO);
        response.getWriter().print(gson.toJson(result));
    }
}
