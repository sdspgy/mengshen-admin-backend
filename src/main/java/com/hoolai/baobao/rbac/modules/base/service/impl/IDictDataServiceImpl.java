package com.hoolai.baobao.rbac.modules.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.common.constant.CommonConstant;
import com.hoolai.baobao.rbac.modules.base.entity.DictData;
import com.hoolai.baobao.rbac.modules.base.mapper.DictDataMapper;
import com.hoolai.baobao.rbac.modules.base.service.IDictDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("jdbc-admin")
public class IDictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements IDictDataService {

	@Override
	public IPage<DictData> findByCondition(DictData dictData, Page page) {
		QueryWrapper<DictData> queryWrapper = new QueryWrapper();
		//模糊搜素
		if (StrUtil.isNotBlank(dictData.getTitle())) {
			queryWrapper.like("title", dictData.getTitle());
		}

		//状态
		if (dictData.getStatus() != null) {
			queryWrapper.eq("status", dictData.getStatus());
		}

		//所属字典
		if (StrUtil.isNotBlank(dictData.getDictId())) {
			queryWrapper.eq("dict_id", dictData.getDictId());
		}
		return this.page(page, queryWrapper);
	}

	@Override
	public List<DictData> findByDictId(String dictId) {
		QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("dict_id", dictId).eq("status", CommonConstant.STATUS_NORMAL).orderByAsc("sort_order");
		return this.list(queryWrapper);
	}

	@Override
	public void deleteByDictId(String dictId) {
		QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("dict_id", dictId);
		this.remove(queryWrapper);
	}
}
