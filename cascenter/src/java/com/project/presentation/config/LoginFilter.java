package com.project.presentation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String ticket_prefix = "application:ticket:";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //2.如果是请求中没有session的话判断有无ticket参数，如果是ticket参数的话需要在这个filter中拿到sessionId,然后获取用户信息
        //3.如果是请求已经有session的就正常放行


//        "/login"
//        request.getRequestURI()

//        String path = request.getServletPath()+request.getRequestURI();
//        String id = request.getSession().getId();
//
//        String ticket = request.getParameter("ticket");
//        //没登录也没有票据的话就跳转CAS系统登录
//        if(StringUtils.isEmpty(id)&&StringUtils.isEmpty(ticket)){
//            response.sendRedirect("http://cas.com?fromPage="+path);
//        }
//
//        //如果有票据的话从redis中取出sessionId
//        if(!StringUtils.isEmpty(ticket)){
//            String sessionId = (String)redisTemplate.opsForValue().get(ticket_prefix + ticket);
////            Cookie cookie
////            response.addCookie();
//        }
        filterChain.doFilter(request,response);


    }
}
