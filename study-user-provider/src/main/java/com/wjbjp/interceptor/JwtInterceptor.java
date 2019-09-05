package com.wjbjp.interceptor;

import com.wzl.commons.model.TokenUser;
import com.wzl.commons.utlity.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt拦器
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static final String BEARER_IDENTIFIER = "Bearer "; // space is important


    /**
     * 进入方法之前，返回ture表示可以执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Inside JWT interceptor, checking request ..."+request.getRequestURL());
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith(BEARER_IDENTIFIER)) {
            String jwt = authorization.substring(BEARER_IDENTIFIER.length());
            TokenUser t=TokenUtil.parseUserFromToken(jwt);

            request.setAttribute("Claims_user",t);
        }
        return  true;
    }

    /**
     * 执行方法之后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 响应之前
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
