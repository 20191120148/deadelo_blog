package com.deadelo.vo;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/17  16:00
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {
    private Long id;
    //标题
    private String title;
    //访问量
    private Long viewCount;
}
