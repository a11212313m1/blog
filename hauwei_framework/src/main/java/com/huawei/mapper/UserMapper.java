package com.huawei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huawei.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2024-06-16 15:38:02
 */
public interface UserMapper extends BaseMapper<User> {

}

