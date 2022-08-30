package com.deadelo.service;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/8  15:14
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.deadelo.domain.entity.Article;
import com.deadelo.vo.ResponseResult;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);
}
