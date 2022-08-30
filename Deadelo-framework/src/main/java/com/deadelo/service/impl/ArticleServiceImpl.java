package com.deadelo.service.impl;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/8  15:16
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.entity.Article;
import com.deadelo.domain.entity.Category;
import com.deadelo.mapper.ArticleMapper;
import com.deadelo.service.ArticleService;
import com.deadelo.service.CategoryService;
import com.deadelo.utils.BeanCopyUtils;
import com.deadelo.utils.RedisCache;
import com.deadelo.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Resource
    private CategoryService categoryService;


    @Resource
    private RedisCache redisCache;
    @Override
    //查询热门文章
    public ResponseResult hotArticleList() {
        //分页查询
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章Statu==0
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //查询十条
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> articleList = page.getRecords();
//        List<HotArticleVo> hotArticleVos = new ArrayList<>();
//        //将有效数据封装到vo
//        for (Article article : articleList) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            hotArticleVos.add(vo);
//        }
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articleList,HotArticleVo.class);

        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //如果有categoryId 查询要与传入相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行排序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);


        //查询categoryId
        List<Article> articles = page.getRecords();

                articles.stream()
                .map(new Function<Article, Article>() {
                    @Override
                    public Article apply(Article article) {
                        //获取分类id，查询分类信息，获取分类名称
                        article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
                        //把分类名称set进article
                        return article;
                    }
                })
                .collect(Collectors.toList());
        //articleId->articleName进行设置
//        for (Article article : articles) {
//
//        }
        //封装
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCountInt = redisCache.getCacheMapValue(SystemConstants.REDIS_VIEWCOUNT, id.toString());
        Long viewCount = viewCountInt.longValue();
        //转换成vo
        article.setViewCount(viewCount);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);

        //查询根据分类id查询分类名

        Category category = categoryService.getById(articleDetailVo.getCategoryId());

        if (category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应体
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对于id浏览量
        redisCache.incrementCacheValue(SystemConstants.REDIS_VIEWCOUNT,id.toString(),1);
        return ResponseResult.okResult();
    }
}
