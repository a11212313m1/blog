package com.huawei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huawei.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;


/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2024-06-16 15:12:19
 */

@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}

