package com.huawei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

}
