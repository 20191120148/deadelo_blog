package com.deadelo.controller;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/19  19:48
 */

import com.deadelo.service.LinkService;
import com.deadelo.vo.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }
}
