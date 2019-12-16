package com.hoolai.baobao.rbac.modules.client.controller;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.modules.client.entity.GameServer;
import com.hoolai.baobao.rbac.modules.client.service.impl.GameServerServiceImpl;
import com.hoolai.baobao.rbac.modules.game.entity.Game;
import com.hoolai.baobao.rbac.modules.game.service.impl.GameServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：房家辉
 * @description：TODO
 * @date ：2019/12/11 0011 11:48
 */
@Slf4j
@RestController
@RequestMapping("/baobao-admin")
@Api(tags = "分服", value = "")
@DS("jdbc-game")
public class GameServerController {

	@Resource
	private GameServiceImpl gameservice;
	@Resource
	private GameServerServiceImpl gameserverService;

	@ApiOperation(value = "selectServer", notes = "")
	@GetMapping("/game/queryAllServer")
	public Object queryAllServer(@ModelAttribute GameServer gameserver,
					@ModelAttribute PageVo pageVo) {
		log.info("-----------------执行----------queryAllServer----分服信息------------------");
		IPage<GameServer> gameServerIPage = gameserverService.selectCreative(gameserver, PageUtil.initMpPage(pageVo));
		IPage<Game> gameGameIPage = gameservice.selectGames(PageUtil.initMpPage(pageVo));
		List<Object> list = Lists.newArrayList();
		list.add(gameGameIPage);
		list.add(gameServerIPage);
		log.info("-----------------结束----------queryAllServer----分服信息------------------");
		return list;
	}

	@RequestMapping(value = "/game/addServer", method = RequestMethod.POST)
	public Object addServer(@ModelAttribute GameServer gameServer) {
		log.info("-----------------执行----------addServer----分服信息------------------");
		log.info("-----------------结束----------addServer----分服信息------------------");
		return gameserverService.save(gameServer);
	}

	@RequestMapping(value = "/game/updateServer", method = RequestMethod.POST)
	public Object updateServer(@ModelAttribute GameServer gameServer) {
		log.info("-----------------执行----------updateServer----分服信息------------------");
		log.info("-----------------结束----------updateServer----分服信息------------------");
		return gameserverService.updateById(gameServer);
	}

	@RequestMapping(value = "/game/delServerByIds/{ids}", method = RequestMethod.DELETE)
	public Object delServerByIds(@PathVariable String[] ids) {
		log.info("-----------------执行----------delServerByIds----分服信息------------------");
		for (String id : ids) {
			gameserverService.removeById(id);
		}
		log.info("-----------------结束----------delServerByIds----分服信息------------------");
		throw new RbacException("批量通过id删除数据成功", true);
	}
}
