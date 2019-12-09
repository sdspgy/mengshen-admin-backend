package com.hoolai.baobao.rbac.modules.creative.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.creative.entity.GameCreative;
import com.hoolai.baobao.rbac.modules.creative.mapper.GameCreativeMapper;
import com.hoolai.baobao.rbac.modules.creative.service.IGameCreativeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhiqiu
 * @since 2019-11-20
 */
@Service
@DS("jdbc-game")
public class GameCreativeServiceImpl extends ServiceImpl<GameCreativeMapper, GameCreative> implements IGameCreativeService {

	@Override
	public IPage<GameCreative> selectCreative(GameCreative gameCreative, Page page) {
		QueryWrapper<GameCreative> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(gameCreative.getCreativeid())) {
			queryWrapper.like("creativeid", gameCreative.getCreativeid());
		}
		if (Objects.nonNull(gameCreative.getGameid())) {
			queryWrapper.like("gameid", gameCreative.getGameid());
		}
		return this.page(page, queryWrapper);
	}

}
