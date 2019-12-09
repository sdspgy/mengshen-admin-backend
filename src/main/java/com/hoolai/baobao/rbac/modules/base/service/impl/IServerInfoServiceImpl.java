package com.hoolai.baobao.rbac.modules.base.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.common.constant.CommonConstant;
import com.hoolai.baobao.rbac.modules.base.entity.ServerInfo;
import com.hoolai.baobao.rbac.modules.base.mapper.ServerInfoMapper;
import com.hoolai.baobao.rbac.modules.base.service.IServerInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("jdbc-admin")
public class IServerInfoServiceImpl extends ServiceImpl<ServerInfoMapper, ServerInfo> implements IServerInfoService {

	@Override
	public List<ServerInfo> getListByEnable() {
		QueryWrapper<ServerInfo> queryWrapper = new QueryWrapper();
		queryWrapper.eq("status", CommonConstant.STATUS_NORMAL);
		return this.list(queryWrapper);
	}

}
