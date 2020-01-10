package com.hoolai.baobao.rbac.modules.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.modules.client.entity.GameServer;

public interface GameServerSerivce extends IService<GameServer> {

	IPage<GameServer> selectClient(GameServer gameServer, Page page);

}
