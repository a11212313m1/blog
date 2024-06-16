package com.huawei.service;

import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);
}
