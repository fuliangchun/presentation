package com.project.presentation.controller;

import com.project.presentation.form.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request, Model model){
        if(StringUtils.isEmpty(request.getSession().getId())){
            System.out.println("no");
        }
        String fromPage = request.getParameter("fromPage");
        UserForm  u = new UserForm();
        u.setBackurl(fromPage);
        model.addAttribute("user",u);
        return "/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String toLogin(@ModelAttribute(name = "user") UserForm userForm,HttpServletRequest request){
        if("admin".equals(userForm.getUsername())&&"123".equals(userForm.getPassword())){
            request.getSession()
        }
        return null;
    }
}
