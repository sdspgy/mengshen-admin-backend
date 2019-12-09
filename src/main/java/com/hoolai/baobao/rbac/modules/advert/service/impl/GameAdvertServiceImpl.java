package com.hoolai.baobao.rbac.modules.advert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.advert.entity.GameAdvert;
import com.hoolai.baobao.rbac.modules.advert.mapper.GameAdvertMapper;
import com.hoolai.baobao.rbac.modules.advert.service.IGameAdvertService;
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
public class GameAdvertServiceImpl extends ServiceImpl<GameAdvertMapper, GameAdvert> implements IGameAdvertService {

	@Override
	public IPage<GameAdvert> selectAdvert(GameAdvert gameAdvert, Page page) {
		QueryWrapper<GameAdvert> queryWrapper = new QueryWrapper<>();
		if (Objects.nonNull(gameAdvert.getAdvertName()) && !gameAdvert.getAdvertName().equals("")) {
			queryWrapper.like("advert_name", gameAdvert.getAdvertName());
		}
		if (Objects.nonNull(gameAdvert.getGameid())) {
			queryWrapper.like("gameid", gameAdvert.getGameid());
		}
		return this.page(page, queryWrapper);
	}
}
