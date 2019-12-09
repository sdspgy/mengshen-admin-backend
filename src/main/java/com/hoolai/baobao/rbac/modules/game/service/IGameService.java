package com.hoolai.baobao.rbac.modules.game.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.modules.game.entity.Game;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhiqiu
 * @since 2019-11-29
 */
public interface IGameService extends IService<Game> {

	IPage<Game> selectGame(Game game, Page initMpPage);

	IPage<Game> selectGames(Page initMpPage);
}
