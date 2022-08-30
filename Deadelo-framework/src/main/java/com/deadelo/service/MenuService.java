package com.deadelo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deadelo.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2022-08-14 15:25:09
 */
public interface MenuService extends IService<Menu> {

    List<String> selectParamsByUserId(Long id);
}
