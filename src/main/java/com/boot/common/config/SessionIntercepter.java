package com.boot.common.config;

import com.boot.util.common.SbConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by zhouxiaoxiao on 17/3/1.
 */
@Component
public class SessionIntercepter implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest HttpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("SESSION拦截器preHandle:" + HttpServletRequest.getContextPath()
                + "--" + HttpServletRequest.getMethod() + "--"
                + HttpServletRequest.getRequestURI());
        String[] uris = HttpServletRequest.getRequestURI().split("/");
        boolean is_inter = false;
        // 判断是否启用拦截
        for (String uri : uris) {
            for (String arr : SbConstants.intercepter_session_array) {
                if (arr.equals(uri)) {
                    is_inter = true;
                }
            }
        }
        if (is_inter) {
            Object obj = HttpServletRequest.getSession().getAttribute(
                    SbConstants.USER_KEY);
            if (obj == null) {
                if (HttpServletRequest.getHeader("x-requested-with") != null
                        && HttpServletRequest.getHeader("x-requested-with")
                        .equalsIgnoreCase("XMLHttpRequest")) {
                    // 如果是ajax请求响应头会有x-requested-with
                    log.info("AJAX请求session失效");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.print("loseSession");// session失效
                    out.flush();
                    return false;
                } else {
                    httpServletResponse.sendRedirect("/sbweb/");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
        log.info("SESSION拦截器postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception {
        log.info("SESSION拦截器afterCompletion");
    }
}
