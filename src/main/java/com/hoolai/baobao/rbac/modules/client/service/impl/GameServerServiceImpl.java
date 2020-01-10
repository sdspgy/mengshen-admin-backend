package com.hoolai.baobao.rbac.modules.client.service.impl;

import java.util.Objects;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.client.entity.GameServer;
import com.hoolai.baobao.rbac.modules.client.mapper.GameServerMapper;
import com.hoolai.baobao.rbac.modules.client.service.GameServerSerivce;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author ：房家辉
 * @description：TODO
 * @date ：2019/12/11 0011 11:55
 */
@Service
@DS("jdbc-game")
public class GameServerServiceImpl extends ServiceImpl<GameServerMapper, GameServer> implements GameServerSerivce {

	@Override
	public IPage<GameServer> selectClient(GameServer gameServer, Page page) {
		QueryWrapper<GameServer> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(gameServer.getServerid())) {
			queryWrapper.like("serverid", gameServer.getServerid());
		}
		if (Objects.nonNull(gameServer.getGameid())) {
			queryWrapper.like("gameid", gameServer.getGameid());
		}
		return this.page(page, queryWrapper);
	}
}
