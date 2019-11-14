package com.project.frontend.config;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author FULIANGCHUN288
 * @description
 * @date 2019年11月13日 19:57
 * @since 1.0.0
 **/
@ConfigurationProperties(prefix = "security.cas.server")
public class CasServerProperties {
    private String host;
    private String login;
    private String logout;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }
}
