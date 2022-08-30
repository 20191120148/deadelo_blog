package com.deadelo.vo;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/18  13:00
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {
    private Long id;
    //标题
    private String title;

    private String summary;
    //所属分类id
    private Long categoryName;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;
    private Date createTime;


}
