package com.hoolai.baobao.rbac.modules.base.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.base.entity.Dict;
import com.hoolai.baobao.rbac.modules.base.mapper.DictMapper;
import com.hoolai.baobao.rbac.modules.base.service.IDictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("jdbc-admin")
public class IDictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

	@Override
	public List<Dict> findAllOrderBySortOrder() {
		QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByAsc("sort_order");
		return this.list(queryWrapper);
	}

	@Override
	public Dict findByType(String type) {
		QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("type", type);
		return this.getOne(queryWrapper);
	}

	@Override
	public List<Dict> findByTitleOrTypeLike(String key) {
		QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("title", key).or().like("type", key).orderByAsc("sort_order");
		return this.list(queryWrapper);
	}
}
