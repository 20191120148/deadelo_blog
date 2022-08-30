package com.deadelo.service;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/30  22:42
 */

import com.deadelo.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult uploadImg(MultipartFile file);
}
