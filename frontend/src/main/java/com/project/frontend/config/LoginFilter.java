//package com.project.frontend.config;
//
//import com.learning.common.UserForm;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
////@Component
//public class LoginFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    private static final String TICKET_PREFIX = "application:ticket:";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String path = request.getRequestURL().toString();
//        HttpSession session = request.getSession();
//
//        String ticket = request.getParameter("ticket");
////        boolean isLogin = false;
//        //没登录也没有票据的话就跳转CAS系统登录
//        UserForm userForm = null;
//        //如果有票据的话从redis中取出sessionId
//        if(!StringUtils.isEmpty(ticket)){
//            userForm = (UserForm)redisTemplate.opsForValue().get(TICKET_PREFIX + ticket);
//            if(ObjectUtils.isNotEmpty(userForm)){
//                session.setAttribute("sessionLoginState",1);
//                session.setAttribute("sessionAccount",1L);
//                response.sendRedirect(path);
//                return;
//            }
//        }
//
//        if(!isLogin(session)){
//            response.sendRedirect("http://www.cas.com:7001/toLogin?fromPage="+path);
//            return;
//        }
//
//        filterChain.doFilter(request,response);
//
//
//    }
//
//    private boolean isLogin(HttpSession session) {
//        if(session == null){
//            return false;
//        }
//        Integer sessionLoginState = (Integer)session.getAttribute("sessionLoginState");
//        Long sessionAccount = (Long)session.getAttribute("sessionAccount");
//        if(sessionLoginState == null || 1 != sessionLoginState){
//            return false;
//        }
//          if(sessionAccount == null ){
//            return false;
//        }
//      return true;
//    }
//}
