package com.project.frontend.config;

import com.project.frontend.service.CustomCasUserDetailsService;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FULIANGCHUN288
 * @description
 * @date 2019年11月13日 20:17
 * @since 1.0.0
 **/
@Configuration
@EnableConfigurationProperties({CasServerProperties.class,CasServiceProperties.class})
public class CasConfiguration {

    @Autowired
    private CasServiceProperties casServiceProperties;
    @Autowired
    private CasServerProperties casServerProperties;

    @Autowired
    private CustomCasUserDetailsService customCasUserDetailsService;




    @Bean
    public AuthenticationManager authenticationManager() {

        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(casAuthenticationProvider());

        ProviderManager providerManager = new ProviderManager(providers);

        return providerManager;

    }

    /**认证的入口*/
    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(casServerProperties.getLogin());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

    /**指定service相关信息*/
    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(casServiceProperties.getHost()+ casServiceProperties.getLogin());
        serviceProperties.setAuthenticateAllArtifacts(true);
        return serviceProperties;
    }

    /**CAS认证过滤器*/
    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        //给过滤器设置我们应用的基本配置
        casAuthenticationFilter.setServiceProperties(serviceProperties());
        //给过滤器设置认证管理器
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
        //设置过滤器到cas server认证的地址
        casAuthenticationFilter.setFilterProcessesUrl(casServiceProperties.getLogin());
        //设置是否继续执行其他过滤器，在完成认证前
        casAuthenticationFilter.setContinueChainBeforeSuccessfulAuthentication(false);
        //设置认证成功后的处理handler
//        casAuthenticationFilter.setAuthenticationSuccessHandler(new MyUrlAuthenticationSuccessHandler());
        return casAuthenticationFilter;
    }


    /**cas 认证 Provider*/
    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
        casAuthenticationProvider.setKey("casAuthenticationProviderKey");
        return casAuthenticationProvider;
    }


    /**用户自定义的AuthenticationUserDetailsService*/
    @Bean
    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService(){
        return customCasUserDetailsService;
//        return null;
    }

    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator(casServerProperties.getHost());
    }

    /**单点登出过滤器*/
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casServerProperties.getHost());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    /**请求单点退出过滤器*/
    @Bean
    public LogoutFilter casLogoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(casServerProperties.getLogout()+"?service=" +casServiceProperties.getHost(), new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl(casServiceProperties.getLogout());
        return logoutFilter;
    }


    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener(){
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean =
                new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        return servletListenerRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean httpParamsFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new HttpParamsFilter());
        filterRegistrationBean.setOrder(-999);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }



}
