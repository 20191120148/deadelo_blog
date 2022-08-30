package com.deadelo.vo;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/18  13:05
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    private List rows;
    private Long total;
}
