package com.hoolai.baobao.rbac.modules.base.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.base.entity.Role;
import com.hoolai.baobao.rbac.modules.base.mapper.RoleMapper;
import com.hoolai.baobao.rbac.modules.base.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
@DS("jdbc-admin")
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
