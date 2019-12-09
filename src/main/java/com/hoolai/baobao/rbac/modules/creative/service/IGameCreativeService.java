package com.hoolai.baobao.rbac.modules.creative.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.modules.creative.entity.GameCreative;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhiqiu
 * @since 2019-11-20
 */
public interface IGameCreativeService extends IService<GameCreative> {

	IPage<GameCreative> selectCreative(GameCreative gameCreative, Page page);

}
