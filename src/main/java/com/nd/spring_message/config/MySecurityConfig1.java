package com.nd.spring_message.config;

import com.nd.spring_message.config.securityConfig.MyAuthenctiationFailureHandler;
import com.nd.spring_message.config.securityConfig.MyAuthenctiationSuccessHandler;
import com.nd.spring_message.service.impl.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig1 extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;		//认证成功处理类
    @Autowired
    MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;		//认证失败处理类

    /****
     * 数据库
     * ------> 实现UserDetailsService 重写->loadUserByUsername方法
     * @param auth
     * @throws Exception
     */
    @Autowired
    SecurityUserDetailsService securityUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 1.从内存中读取
//        auth.inMemoryAuthentication().passwordEncoder
//                (new BCryptPasswordEncoder()).withUser("user")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("USER");

        //2.从数据库读取
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());


    }
    /****
     * 授权-----------> loginPage("/login") 路径必须跟登入表单的路径一致
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests() /* 需要授权的请求 */
                .antMatchers("/login","/home").permitAll() /* 过滤不需要认证的路径 */
                .anyRequest().authenticated() /* 对任何一个请求，都需要认证 */
                .and() /* 完成上一个配置，进行下一步配置 */
                //.httpBasic();
                .formLogin() /* 配置表单登录 */
                .loginPage("/login") /* 设置登录页面 */
                .successHandler(myAuthenctiationSuccessHandler)  /* 设置成功处理器 */
                .failureHandler(myAuthenctiationFailureHandler)  /* 设置失败处理器*/
                .and()
                .logout() /* 登出 */
                .logoutSuccessUrl("/home"); /* 设置退出页面 */
        // 关闭CSRF跨域
        http.csrf().disable();

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

}
