package com.deadelo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.entity.Menu;
import com.deadelo.domain.entity.Role;
import com.deadelo.mapper.MenuMapper;
import com.deadelo.mapper.RoleMapper;
import com.deadelo.service.LinkService;
import com.deadelo.service.MenuService;
import com.deadelo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2022-08-14 15:25:09
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectParamsByUserId(Long id) {
        //如果是管理员返回所有权限
        if(id == 1L){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType,SystemConstants.MENU,SystemConstants.BUTTON);
            queryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }

        //否则返回具有的所有权限
        return getBaseMapper().selectParamsByUserId(id);
    }


}

