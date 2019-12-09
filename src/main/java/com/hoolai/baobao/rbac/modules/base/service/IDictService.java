package com.hoolai.baobao.rbac.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.modules.base.entity.Dict;

import java.util.List;

public interface IDictService extends IService<Dict> {

	/**
	 * 排序获取全部
	 *
	 * @return
	 */
	List<Dict> findAllOrderBySortOrder();

	/**
	 * 通过type获取
	 *
	 * @param type
	 * @return
	 */
	Dict findByType(String type);

	/**
	 * 模糊搜索
	 *
	 * @param key
	 * @return
	 */
	List<Dict> findByTitleOrTypeLike(String key);

}
