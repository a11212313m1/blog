package com.huawei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huawei.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2024-06-16 10:08:27
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

