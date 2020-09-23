package com.nd.spring_message.service.impl;


import com.nd.spring_message.domain.Role;
import com.nd.spring_message.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {


    @Autowired
    private UserServiceImpl userService;


    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private PermissionServiceImpl permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userService.findByUserName(username);
        if(userInfo == null)
        {
          throw  new UsernameNotFoundException("用户名不存在！");
        }
        //处理自己的用户对象封装成UserDetails ,
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }


    //返回一个List集合，Role集合装入权限代码
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList();
        for (Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

}
