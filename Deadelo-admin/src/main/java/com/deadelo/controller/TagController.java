package com.deadelo.controller;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/8/10  18:02
 */

import com.deadelo.service.TagService;
import com.deadelo.vo.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(){
        return ResponseResult.okResult(tagService.list());
    }

}
