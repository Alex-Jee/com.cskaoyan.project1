package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bo.GoodsBO;
import com.cskaoyan.mall.model.*;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.service.GoodsServiceImpl;
import com.cskaoyan.mall.utils.FileUploadUtils;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {
    Gson gson=new Gson();
    private GoodsService goodsService=new GoodsServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI=request.getRequestURI();
        String action=requestURI.replace("/api/admin/goods/","");
        if("addType".equals(action)){
            addType(request,response);
        }else if("imgUpload".equals(action)){
            imgUpload(request,response);
        }else if("addGoods".equals(action)){
            addGoods(request,response);
        }else if("reply".equals(action)){
            reply(request,response);
        }
    }

    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody=HttpUtils.getRequestBody(request);
        Msg msg=gson.fromJson(requestBody,Msg.class);
        goodsService.reply(msg);
        Result result = new Result();
        result.setCode(0);
        response.getWriter().print(gson.toJson(result));
    }

    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody=HttpUtils.getRequestBody(request);
        GoodsBO goodsBO=gson.fromJson(requestBody,GoodsBO.class);
        goodsService.addGoods(goodsBO);
        Result result = new Result();
        result.setCode(0);
        response.getWriter().print(gson.toJson(result));
    }

    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> map= FileUploadUtils.parseRequest(request);
        String file=(String)map.get("file");
        String domain=(String)getServletContext().getAttribute("domain");
        Result result=new Result();
        result.setCode(0);
        result.setData(domain+file);
        response.getWriter().print(gson.toJson(result));
    }

    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Type type=gson.fromJson(requestBody, Type.class);
        int status=goodsService.addType(type);
        Result result = new Result();
        if(status==0){
            result.setCode(500);
            result.setMessage("添加类目失败：类目已存在");
        }else {
            result.setCode(0);
            result.setMessage("添加成功！");
        }
        response.getWriter().print(gson.toJson(result));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI=request.getRequestURI();
        String action=requestURI.replace("/api/admin/goods/","");
        if("getType".equals(action)){
            getType(request,response);
        }else if("getGoodsByType".equals(action)){
            getGoodsByType(request,response);
        }else if("getGoodsInfo".equals(action)){
            getGoodsInfo(request,response);
        }else if("deleteGoods".equals(action)){
            deleteGoods(request,response);
        }else if("noReplyMsg".equals(action)){
            noReplyMsg(request,response);
        }else if("repliedMsg".equals(action)){
            repliedMsg(request,response);
        }
    }

    private void repliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Msg> list=goodsService.repliedMsg();
        response.getWriter().print(gson.toJson(new Result(list)));
    }

    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Msg> list=goodsService.noReplyMsg();
        response.getWriter().print(gson.toJson(new Result(list)));
    }

    private void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id=new Integer(request.getParameter("id"));
        ServletContext context=getServletContext();
        goodsService.deleteGoods(id,context);
        Result result = new Result();
        result.setCode(0);
        response.getWriter().print(gson.toJson(result));
    }

    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id=new Integer(request.getParameter("id"));
        GoodsInfo goodsInfo=goodsService.getGoodsInfo(id);
        Result result=new Result();
        result.setCode(0);
        result.setData(goodsInfo);
        response.getWriter().print(gson.toJson(result));
    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param=request.getParameter("typeId");
        int typeId=new Integer(param);
        List<Goods> data=goodsService.getGoodsByType(typeId);
        Result result=new Result(data);
        response.getWriter().print(gson.toJson(result));
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList=goodsService.getType();
        Result result = new Result(typeList);
        response.getWriter().print(gson.toJson(result));
    }
}
