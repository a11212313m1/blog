package com.huawei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huawei.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2024-06-18 14:32:52
 */
public interface CommentMapper extends BaseMapper<Comment> {

}

