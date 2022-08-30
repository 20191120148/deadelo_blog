package com.deadelo.vo;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/18  1:45
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {
    private Long id;

    //分类名
    private String name;
}
