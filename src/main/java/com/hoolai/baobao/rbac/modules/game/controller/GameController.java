package com.hoolai.baobao.rbac.modules.game.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.modules.game.entity.Game;
import com.hoolai.baobao.rbac.modules.game.service.impl.GameServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		log.info("-----------------执行----------queryAllGame----游戏信息------------------");
		IPage<Game> gameGameIPage = gameService.selectGame(game, PageUtil.initMpPage(pageVo));
		log.info("-----------------结束----------queryAllGame----游戏信息------------------");
		return gameGameIPage;
	}
}
