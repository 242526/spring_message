package com.nd.spring_message.config.securityConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/****
 * 定义自定义失败处理类，
 */
@Component("myAuthenctiationFailureHandler")
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //看自己的选择
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        logger.info("进入认证失败处理类" ,exception.getMessage());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,String> sb=new HashMap<>();
        sb.put("status","error");
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            sb.put("msg","用户名或密码输入错误，登录失败!");
        } else if (exception instanceof DisabledException) {
            sb.put("msg","账户不可用");
        } else {
            sb.put("msg","授权失败!");
        }
        HttpSession session = request.getSession();
        session.setAttribute("error",sb);
        response.sendRedirect("/security/login?error=true");
    }


}
