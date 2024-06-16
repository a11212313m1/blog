package com.huawei.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huawei.constants.SystemConstants;
import com.huawei.domain.ResponseResult;
import com.huawei.domain.entity.Article;
import com.huawei.domain.entity.Category;
import com.huawei.domain.vo.CategoryVo;
import com.huawei.mapper.CategoryMapper;
import com.huawei.service.ArticleService;
import com.huawei.service.CategoryService;
import com.huawei.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-06-16 10:08:32
 */
@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    private final ArticleService articleService;
    public ResponseResult getCategoryList() {
        //查询文章表，状态为已发布
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);

        //查询文章分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> category.getStatus().equals(SystemConstants.STATUS_NORMAL))
                .collect(Collectors.toList());
        List<Category> categoryVos = BeanCopyUtils.copyBeanList(categories, Category.class);


        return ResponseResult.okResult(categoryVos);
    }
}

