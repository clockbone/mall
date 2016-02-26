package com.clockbone.security;

import com.clockbone.mapper.UserMapper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by qinjun on 2016/2/16.
 */
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(Strings.isNullOrEmpty(username)){
            //校验不通过，会转到  authentication-failure-url="/user/login?login=false"
            throw new UsernameNotFoundException("username is null or empty.");
        }
        com.clockbone.domain.User mangerUser = null;
        try{
            mangerUser = userMapper.getUserByName(username);
        }catch (Exception e){
            throw new UsernameNotFoundException(username+" not found.");
        }

        if(null == mangerUser){
            throw new UsernameNotFoundException(username+" not found.");
        }
        System.out.println("power auth");


        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        //用户校验通过后添加权限，先添加基本权限：ROLE_USER
        //  用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        auths.add(authority);
        if(Objects.equals(username,"admin")){
            auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
       /* String roles = mangerUser.getRoles();
        String[] roleArray = roles.split(",");
        for(String role : roleArray) {
            auths.add(new SimpleGrantedAuthority(role));
        }*/
        //md5 加密的，明文：6
        mangerUser.setPassWord("1679091c5a880faf6fb5e6087eb1b2dc");
        //这里将 数据库的密码放到ｕｓｅｒ　中，框加会自动匹配密码

        User user = new User(username, mangerUser.getPassWord(), auths);
       /* 通过UserDetailsServiceImpl拿到用户信息后，authenticationManager对比用户的密码（即验证用户），*/
        return user;

    }
}
