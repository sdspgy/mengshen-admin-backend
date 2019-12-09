package com.hoolai.baobao.rbac.modules.advert.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.modules.advert.entity.GameAdvert;
import com.hoolai.baobao.rbac.modules.advert.service.impl.GameAdvertServiceImpl;
import com.hoolai.baobao.rbac.modules.game.entity.Game;
import com.hoolai.baobao.rbac.modules.game.service.impl.GameServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhiqiu
 * @since 2019-11-29
 */
@Slf4j
@Api(tags = "广告", value = "")
@RestController
@RequestMapping("/baobao-admin")
@DS("jdbc-game")
public class GameAdvertController {

	@Resource
	private GameAdvertServiceImpl gameAdvertService;
	@Resource
	private GameServiceImpl gameService;

	@ApiOperation(value = "selectAdvert", notes = "")
	@GetMapping("/game/queryAllAdvert")
	public Object queryAllAdvert(@ModelAttribute GameAdvert gameAdvert,
					@ModelAttribute PageVo pageVo) {
		log.info("-----------------执行----------queryAllAdvert----广告信息------------------");
		IPage<GameAdvert> gameCreativeIPage = gameAdvertService.selectAdvert(gameAdvert, PageUtil.initMpPage(pageVo));
		IPage<Game> gameGameIPage = gameService.selectGames(PageUtil.initMpPage(pageVo));
		List<Object> list = Lists.newArrayList();
		list.add(gameGameIPage);
		list.add(gameCreativeIPage);
		log.info("-----------------结束----------queryAllAdvert----广告信息------------------");
		return list;
	}

	@RequestMapping(value = "/game/addAdvert", method = RequestMethod.POST)
	public Object addAdvert(@ModelAttribute GameAdvert gameAdvert) {
		log.info("-----------------执行----------addAdvert----广告信息------------------");
		if ("".equals(gameAdvert.getAdvertid())) {
			gameAdvert.setAdvertid(UUID.randomUUID().toString());
		}
		log.info("-----------------结束----------addAdvert----广告信息------------------");
		return gameAdvertService.save(gameAdvert);
	}

	@RequestMapping(value = "/game/updateAdvert", method = RequestMethod.POST)
	public Object updateAdvert(@ModelAttribute GameAdvert gameAdvert) {
		log.info("-----------------执行----------updateAdvert----广告信息------------------");
		if ("".equals(gameAdvert.getAdvertid())) {
			gameAdvert.setAdvertid(UUID.randomUUID().toString());
		}
		log.info("-----------------结束----------updateAdvert----广告信息------------------");
		return gameAdvertService.updateById(gameAdvert);
	}

	@RequestMapping(value = "/game/delAdvertByIds/{ids}", method = RequestMethod.DELETE)
	public Object delAdvertByIds(@PathVariable String[] ids) {
		log.info("-----------------执行----------delAdvertByIds----广告信息------------------");
		for (String id : ids) {
			gameAdvertService.removeById(id);
		}
		log.info("-----------------结束----------delAdvertByIds----广告信息------------------");
		throw new RbacException("批量通过id删除数据成功", true);
	}
}
