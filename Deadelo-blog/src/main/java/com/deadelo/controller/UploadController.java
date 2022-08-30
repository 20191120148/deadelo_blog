package com.deadelo.controller;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/30  22:37
 */

import com.deadelo.service.UploadService;
import com.deadelo.vo.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.font.MultipleMaster;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;
    @PostMapping
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
