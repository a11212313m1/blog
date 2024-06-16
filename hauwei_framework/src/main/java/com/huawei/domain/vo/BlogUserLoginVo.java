package com.huawei.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BlogUserLoginVo {
    private String token;
    private UserInfoVo userInfo;



}
