package com.huawei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-06-16 15:12:21
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

}

