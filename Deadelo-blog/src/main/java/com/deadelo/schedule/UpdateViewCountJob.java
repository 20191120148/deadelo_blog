package com.deadelo.schedule;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/8/8  18:52
 */

import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.entity.Article;
import com.deadelo.service.ArticleService;
import com.deadelo.utils.RedisCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Component
public class UpdateViewCountJob {

    @Resource
    private RedisCache redisCache;

    @Resource
    private ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.REDIS_VIEWCOUNT);
        List<Article> articleList = new ArrayList<>();
        for (Map.Entry<String,Integer> entry:viewCountMap.entrySet()){
            Article article = new Article();
            article.setId(Long.valueOf(entry.getKey()));
            article.setViewCount(entry.getValue().longValue());
            articleList.add(article);
        }
        //更新到数据库中
        articleService.updateBatchById(articleList);
    }
}
