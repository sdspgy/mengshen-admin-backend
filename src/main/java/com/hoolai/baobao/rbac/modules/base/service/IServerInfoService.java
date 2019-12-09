package com.hoolai.baobao.rbac.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.modules.base.entity.ServerInfo;

import java.util.List;

public interface IServerInfoService extends IService<ServerInfo> {

	List<ServerInfo> getListByEnable();

}
