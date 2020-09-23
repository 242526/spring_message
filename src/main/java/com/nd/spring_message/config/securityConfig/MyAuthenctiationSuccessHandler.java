package com.nd.spring_message.config.securityConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/****
 * 定义自定义成功处理类，
 */
@Component("myAuthenctiationSuccessHandler")
public class MyAuthenctiationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.info("登录成功");
        response.setContentType("application/json;charset=UTF-8");
        /****
         *  我的是-->直接回写在页面
         *  这里可以作一些自己的业务逻辑
         *    ->  1.比如 登入成功页面跳转到主页
         *    ->  2.比如 重定向到某个页面，把成功信息带过去等等
         *
         */
        response.getWriter().write( objectMapper.writeValueAsString(authentication));
    }

}
