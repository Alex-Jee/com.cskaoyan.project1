package com.cskaoyan.mall.filter;

import com.cskaoyan.mall.model.Admin;
import com.cskaoyan.mall.model.Result;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: zsquirrel
 * Date: 2020/4/28
 * Time: 4:15 下午
 */
@WebFilter("/api/admin/*")
public class AdminFilter implements Filter {
    Gson gson = new Gson();

    public AdminFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");

        if(!request.getMethod().equals("OPTIONS")){
            String requestURI = request.getRequestURI();
            if(auth(requestURI)){
                //当前请求的URI需要权限
                Admin admin=(Admin)request.getSession().getAttribute("admin");
                if(admin==null){
                    Result result = new Result();
                    result.setData("拒绝访问：当前页面需要登录查看");
                    result.setCode(401);
                    response.getWriter().print(gson.toJson(result));
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 是否需要认证，需要，返回true，不需要，返回false
     * @param requestURI
     * @return
     */
    private boolean auth(String requestURI){
        if("/api/admin/admin/login".equals(requestURI)||
            "/api/admin/admin/logoutAdmin".equals(requestURI)){
            return false;
        }
        return true;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
