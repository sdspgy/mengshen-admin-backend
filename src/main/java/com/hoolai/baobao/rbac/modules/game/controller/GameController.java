package com.hoolai.baobao.rbac.modules.game.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.modules.game.entity.Game;
import com.hoolai.baobao.rbac.modules.game.service.impl.GameServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhiqiu
 * @since 2019-11-29
 */
@Slf4j
@RestController
@RequestMapping("/baobao-admin")
@DS("jdbc-game")
public class GameController {

	@Resource
	private GameServiceImpl gameService;

	@GetMapping("/game/queryAllGame")
	public Object queryAllGame(@ModelAttribute Game game,
					@ModelAttribute PageVo pageVo) {
		IPage<Game> gameGameIPage = gameService.selectGame(game, PageUtil.initMpPage(pageVo));
		return gameGameIPage;
	}

	@RequestMapping(value = "/game/addGame", method = RequestMethod.POST)
	public Object addGame(@ModelAttribute Game game) {
		return gameService.save(game);
	}

	@RequestMapping(value = "/game/updateGame", method = RequestMethod.POST)
	public Object updateGame(@ModelAttribute Game game) {
		return gameService.updateById(game);
	}

	@RequestMapping(value = "/game/delGameByIds/{ids}", method = RequestMethod.DELETE)
	public Object delAdvertByIds(@PathVariable String[] ids) {
		for (String id : ids) {
			gameService.removeById(id);
		}
		throw new RbacException("批量通过id删除数据成功", true);
	}
}
