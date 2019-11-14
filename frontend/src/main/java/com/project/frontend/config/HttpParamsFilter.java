package com.project.frontend.config;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author FULIANGCHUN288
 * @description
 * @date 2019年11月13日 23:26
 * @since 1.0.0
 **/

public class HttpParamsFilter extends OncePerRequestFilter {
    public static final String REQUESTED_URL = "CasRequestedUrl";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
            HttpSession session = request.getSession();

            String requestPath = request.getRequestURL().toString();

            session.setAttribute(REQUESTED_URL, requestPath);

            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}