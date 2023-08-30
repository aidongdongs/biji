package com.regin.filter;

import com.alibaba.fastjson.JSON;
import com.regin.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
//检查用户是否已经完成登录
@WebFilter(filterName ="LoginCheckFilter" ,urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路劲匹配器
    public static  final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("sesion中数据判断"+request.getSession().getAttribute("employee"));
        //1 - 获取本次请求的url
        String requestURI = request.getRequestURI();
        // 定义不需要处理的路劲
        String [] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login"
        };
        //2- 判断本次请求是否需要处理
        if (check(requestURI,urls)){
            //3- 如果不需要处理，则直接放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //4 - 判断登录状态，如果登录成功，则直接放行
        if ( request.getSession().getAttribute("employee")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //4 -1 判断登录状态(移动端)，如果登录成功，则直接放行
        if ( request.getSession().getAttribute("user")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        System.out.println("走到拦截器"+request.getSession().getAttribute("employee")!=null);
        //5-  未登录，则返回未登录的结果,通过输出流，向客户端响应数据
        PrintWriter writer = servletResponse.getWriter();
        writer.write(JSON.toJSONString(R.error("NOTLOGIN")));
        writer.close();
        return;
    }

    /**
     * 路劲匹配，检查本次求情是否需要放行
     * @param requestURI
     * @uris
     * @return
     */
    public boolean check (String requestURI,String [] uris){
        for (String s : uris) {
            boolean match = PATH_MATCHER.match(s, requestURI);
           if (match){
               return true;
           }
        }
        return false;
    }
}
