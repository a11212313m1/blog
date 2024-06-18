package com.huawei.service.Impl;

import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.LoginUser;
import com.huawei.domain.entity.User;
import com.huawei.domain.vo.BlogUserLoginVo;
import com.huawei.domain.vo.UserInfoVo;
import com.huawei.service.BlogLoginService;
import com.huawei.utils.BeanCopyUtils;
import com.huawei.utils.JwtUtil;
import com.huawei.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class BlogLoginServiceImpl implements BlogLoginService {

    private final RedisCache redisCache;
    private final AuthenticationManager authenticationManager;
    @Override
    public ResponseResult login(User user) {
        System.out.println(user.getAvatar());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断是否用户认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名密码错误");
        }
        //System.out.println(authenticate);
        //不为空，获取userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String userId = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userId);
        //吧用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);
        //吧token和userinfo封装返回

        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);

        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }


}
