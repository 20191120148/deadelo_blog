package com.deadelo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deadelo.domain.entity.Link;
import com.deadelo.vo.ResponseResult;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-07-19 19:51:25
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();


}
