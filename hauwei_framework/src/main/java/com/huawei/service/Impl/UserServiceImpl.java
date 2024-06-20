package com.huawei.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.User;
import com.huawei.domain.vo.UserInfoVo;
import com.huawei.mapper.UserMapper;
import com.huawei.service.UserService;
import com.huawei.utils.BeanCopyUtils;
import com.huawei.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-06-18 15:15:08
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        UserInfoVo vo = BeanCopyUtils.copyBean(user,UserInfoVo.class);

        return ResponseResult.okResult(vo);
    }

}

