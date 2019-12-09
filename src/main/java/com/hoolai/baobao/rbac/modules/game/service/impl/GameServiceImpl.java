package com.hoolai.baobao.rbac.modules.game.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.game.entity.Game;
import com.hoolai.baobao.rbac.modules.game.mapper.GameMapper;
import com.hoolai.baobao.rbac.modules.game.service.IGameService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhiqiu
 * @since 2019-11-29
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements IGameService {

	@Override
	public IPage<Game> selectGame(Game game, Page page) {
		QueryWrapper<Game> queryWrapper = new QueryWrapper<>();
		if (Objects.nonNull(game.getName())) {
			queryWrapper.like("name", game.getName());
		}
		return this.page(page, queryWrapper);
	}

	@Override
	public IPage<Game> selectGames(Page initMpPage) {
		QueryWrapper<Game> queryWrapper = new QueryWrapper<>();
		return this.page(initMpPage, queryWrapper);
	}
}
