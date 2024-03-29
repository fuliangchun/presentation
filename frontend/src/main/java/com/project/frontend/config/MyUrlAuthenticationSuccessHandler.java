package com.project.frontend.config;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author FULIANGCHUN288
 * @description
 * @date 2019年11月13日 23:25
 * @since 1.0.0
 **/
public class MyUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public MyUrlAuthenticationSuccessHandler() {
        super();
    }

    public MyUrlAuthenticationSuccessHandler(String defaultTargetUrl) {
        super(defaultTargetUrl);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        if (isAlwaysUseDefaultTargetUrl()) {
            return this.getDefaultTargetUrl();
        }

        // Check for the parameter and use that if available
        String targetUrl = null;

        if (this.getTargetUrlParameter() != null) {
            targetUrl = request.getParameter(this.getTargetUrlParameter());

            if (StringUtils.hasText(targetUrl)) {
                logger.debug("Found targetUrlParameter in request: " + targetUrl);

                return targetUrl;
            }
        }

        if (!StringUtils.hasText(targetUrl)) {
            HttpSession session = request.getSession();
            targetUrl = (String) session.getAttribute(HttpParamsFilter.REQUESTED_URL);
        }

        if (!StringUtils.hasText(targetUrl)) {
            targetUrl = this.getDefaultTargetUrl();
            logger.debug("Using default Url: " + targetUrl);
        }

        return targetUrl;
    }
}
