package com.danbai.dblogin.Interceptor;


import com.danbai.dblogin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Danbai
 */
@Component
public class PassManagementInterceptor implements HandlerInterceptor {
    @Value("${danbai.dblogin.ifpass}")
    Boolean ifPss;
    @Value("${danbai.dblogin.passwordManagement}")
    String passwordManagement;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (ifPss) {
            String qpasswordManagement = request.getParameter("passwordManagement");
            if (StringUtils.hasLength(qpasswordManagement)) {
                //验证令牌
                if (qpasswordManagement.equals(passwordManagement)) {
                    return true;
                }
            }
            return false;
        }
        // 需要返回true，否则请求不会被控制器处理
        return true;
    }
}
