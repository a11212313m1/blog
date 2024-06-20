package com.huawei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2024-06-18 14:32:55
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

