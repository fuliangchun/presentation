package com.project.presentation.controller;

import com.learning.common.UserForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Controller
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String TICKET_PREFIX = "application:ticket:";

    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request, Model model){
//        if(StringUtils.isEmpty()){
//            System.out.println("no");
//        }
        System.out.println(request.getSession().getId());
        String fromPage = request.getParameter("fromPage");
        UserForm  u = new UserForm();
        u.setBackurl(fromPage);
        model.addAttribute("user",u);
        return "/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "user") UserForm userForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if("admin".equals(userForm.getUsername())&&"123".equals(userForm.getPassword())){
            HttpSession session = request.getSession();
            session.setAttribute("sessionLoginState",1);
            session.setAttribute("sessionAccount",1);
            String backurl = userForm.getBackurl();
            StringBuffer urlBuffer = new StringBuffer(backurl);
            urlBuffer.append(StringUtils.contains(backurl, "?") ? "&" : "?");
            String ticket = UUID.randomUUID().toString();
            String id = request.getSession().getId();

            Arrays.stream(request.getCookies()).forEach(cookie -> {
                System.out.println(cookie.getName()+"->"+cookie.getValue());
            });
            //将ticket绑定用户信息,ticket在用户退出的时候需要删除掉
            redisTemplate.opsForValue().set(TICKET_PREFIX+ ticket,userForm);

            urlBuffer.append("ticket=").append(ticket);
            response.sendRedirect(urlBuffer.toString());

        }
        return "/login";
    }


    @RequestMapping(value = "/validTicket",method = RequestMethod.POST)
    @ResponseBody
    public UserForm validTicket(@RequestBody String ticket){
        //验证ticket的真伪
        UserForm userForm = (UserForm) redisTemplate.opsForValue().get(TICKET_PREFIX + ticket);
       return userForm;


    }


}
