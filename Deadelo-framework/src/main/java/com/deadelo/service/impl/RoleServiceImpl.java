package com.deadelo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deadelo.domain.entity.Role;
import com.deadelo.mapper.RoleMapper;
import com.deadelo.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2022-08-14 15:44:04
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

