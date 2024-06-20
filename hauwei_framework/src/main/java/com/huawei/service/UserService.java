package com.huawei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-06-18 15:15:08
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

}

