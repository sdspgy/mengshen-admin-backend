package com.hoolai.baobao.rbac.modules.creative.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.modules.creative.entity.GameCreative;
import com.hoolai.baobao.rbac.modules.creative.service.impl.GameCreativeServiceImpl;
import com.hoolai.baobao.rbac.modules.game.entity.Game;
import com.hoolai.baobao.rbac.modules.game.service.impl.GameServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhiqiu
 * @since 2019-11-20
 */
@Slf4j
@RestController
@RequestMapping("/baobao-admin")
@Api(tags = "渠道", value = "")
@DS("jdbc-game")
public class GameCreativeController {

	@Resource
	private GameCreativeServiceImpl gameCreativeService;
	@Resource
	private GameServiceImpl gameService;

	@ApiOperation(value = "selectCreative", notes = "")
	@GetMapping("/game/queryAllCreative")
	public Object queryAllCreative(@ModelAttribute GameCreative gameCreative,
					@ModelAttribute PageVo pageVo) {
		log.info("-----------------执行----------queryAllCreative----渠道信息------------------");
		IPage<GameCreative> gameCreativeIPage = gameCreativeService.selectCreative(gameCreative, PageUtil.initMpPage(pageVo));
		IPage<Game> gameGameIPage = gameService.selectGames(PageUtil.initMpPage(pageVo));
		List<Object> list = Lists.newArrayList();
		list.add(gameGameIPage);
		list.add(gameCreativeIPage);
		log.info("-----------------结束----------queryAllCreative----渠道信息------------------");
		return list;
	}

	@RequestMapping(value = "/game/addCreative", method = RequestMethod.POST)
	public Object addCreative(@ModelAttribute GameCreative gameCreative) {
		log.info("-----------------执行----------addCreative----渠道信息------------------");
		log.info("-----------------结束----------addCreative----渠道信息------------------");
		return gameCreativeService.save(gameCreative);
	}

	@RequestMapping(value = "/game/updateCreative", method = RequestMethod.POST)
	public Object updateCreative(@ModelAttribute GameCreative gameCreative) {
		log.info("-----------------执行----------updateCreative----渠道信息------------------");
		log.info("-----------------结束----------updateCreative----渠道信息------------------");
		return gameCreativeService.updateById(gameCreative);
	}

	@RequestMapping(value = "/game/delCreativeByIds/{ids}", method = RequestMethod.DELETE)
	public Object delCreativeByIds(@PathVariable String[] ids) {
		log.info("-----------------执行----------delCreativeByIds----渠道信息------------------");
		for (String id : ids) {
			gameCreativeService.removeById(id);
		}
		log.info("-----------------结束----------delCreativeByIds----渠道信息------------------");
		throw new RbacException("批量通过id删除数据成功", true);
	}
}
