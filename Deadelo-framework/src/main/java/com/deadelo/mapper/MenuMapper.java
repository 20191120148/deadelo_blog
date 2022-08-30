package com.deadelo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deadelo.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-14 15:25:09
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectParamsByUserId(Long id);
}
