package com.deadelo.vo;/*
*@program:Deadelo
*@author: Deadelo
*@Time: 2022/7/19  20:01


*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkListVo{

    private Long id;


    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;

}
