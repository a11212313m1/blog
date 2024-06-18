package com.huawei.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huawei.domain.entity.LoginUser;
import com.huawei.domain.entity.User;
import com.huawei.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor


public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //System.out.println(user);

        //判断是否查到用户，没查到跑出异常，
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在");

        }

        //查到了，返回用户信息

        //TODO 查询权限信息封装
        return new LoginUser(user);
    }










}
