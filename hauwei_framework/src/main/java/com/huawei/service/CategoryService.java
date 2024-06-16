package com.huawei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-06-16 10:08:31
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

