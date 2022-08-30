package com.deadelo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deadelo.domain.entity.Category;
import com.deadelo.vo.ResponseResult;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-07-18 01:10:23
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
