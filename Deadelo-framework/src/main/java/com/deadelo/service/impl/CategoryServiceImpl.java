package com.deadelo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.entity.Article;
import com.deadelo.domain.entity.Category;
import com.deadelo.mapper.CategoryMapper;
import com.deadelo.service.ArticleService;
import com.deadelo.service.CategoryService;
import com.deadelo.utils.BeanCopyUtils;
import com.deadelo.vo.CategoryVo;
import com.deadelo.vo.ResponseResult;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-07-18 01:10:23
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();

        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id并且去重
        Set<Long> categoryId = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryId);

        categories = categories.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装数据
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}

