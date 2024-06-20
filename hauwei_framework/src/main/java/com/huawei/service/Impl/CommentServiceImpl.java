package com.huawei.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huawei.constants.SystemConstants;
import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.Comment;
import com.huawei.domain.vo.CommentVo;
import com.huawei.domain.vo.PageVo;
import com.huawei.enums.AppHttpCodeEnum;
import com.huawei.exception.SystemException;
import com.huawei.mapper.CommentMapper;
import com.huawei.service.CommentService;
import com.huawei.service.UserService;
import com.huawei.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-06-18 14:32:55
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;
    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论articleId rootId
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        queryWrapper.eq(Comment::getRootId,-1);

        //评论类型
        queryWrapper.eq(Comment::getType,commentType);

        //分页查询
        Page<Comment> page = new Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());


        //查询所有子评论集合
        for (CommentVo commentVo : commentVoList) {
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }


        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {

        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    private List<CommentVo> getChildren(Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(comments);

        return commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
//        commentVos.stream()
//                .forEach(commentVo -> commentVo.setUsername(userService.getById(commentVo.getCreateBy()).getNickName()));
//
//        commentVos.stream()
//                .filter(commentVo -> commentVo.getToCommentId()!=-1)
//                .forEach(commentVo -> commentVo.setToCommentUserName(userService.getById(commentVo.getToCommentUserId()).getNickName()));

        for (CommentVo commentVo : commentVos) {
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);

            if(commentVo.getToCommentId() != -1){
                String toCommentUsername = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUsername);
            }
        }


        return commentVos;
    }
}

















