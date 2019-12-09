package com.hoolai.baobao.rbac.modules.advert.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.modules.advert.entity.GameAdvert;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhiqiu
 * @since 2019-11-29
 */
public interface IGameAdvertService extends IService<GameAdvert> {

	IPage<GameAdvert> selectAdvert(GameAdvert gameAdvert, Page initMpPage);
}
