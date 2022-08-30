package com.deadelo.runner;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/8/9  0:35
 */

import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.entity.Article;
import com.deadelo.mapper.ArticleMapper;
import com.deadelo.utils.RedisCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private RedisCache redisCache;


    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(),
                        new Function<Article, Integer>() {
                    @Override
                    public Integer apply(Article article) {
                        return article.getViewCount().intValue();
                    }
                }));
        redisCache.setCacheMap(SystemConstants.REDIS_VIEWCOUNT,viewCountMap);


        //存储到redis中
    }
}
