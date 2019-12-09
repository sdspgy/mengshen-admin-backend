package com.hoolai.baobao.rbac.modules.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.modules.base.entity.DictData;

import java.util.List;

public interface IDictDataService extends IService<DictData> {

	/**
	 * 多条件获取
	 *
	 * @param dictData
	 * @param page
	 * @return
	 */
	IPage<DictData> findByCondition(DictData dictData, Page page);

	/**
	 * 通过dictId获取启用字典 已排序
	 *
	 * @param dictId
	 * @return
	 */
	List<DictData> findByDictId(String dictId);

	/**
	 * 通过dictId删除
	 *
	 * @param dictId
	 */
	void deleteByDictId(String dictId);
}
