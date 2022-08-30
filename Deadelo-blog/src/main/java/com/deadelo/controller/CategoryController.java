package com.deadelo.controller;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/18  1:19
 */

import com.deadelo.service.CategoryService;
import com.deadelo.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}
