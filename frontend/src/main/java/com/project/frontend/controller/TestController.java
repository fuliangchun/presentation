package com.project.frontend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FULIANGCHUN288
 * @description
 * @date 2019年11月13日 10:49
 * @since 1.0.0
 **/
@RestController
public class TestController {


    @RequestMapping("/login")
    @PreAuthorize("hasRole('USER')")
    public String login() {

        return "login";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/logout")
    public String logout() {

        return "logout";
    }

    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin")
    public String welcom() {

        return "admin";
    }


}
