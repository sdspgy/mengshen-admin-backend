package com.hoolai.baobao.rbac.modules.base.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.common.vo.SearchVo;
import com.hoolai.baobao.rbac.modules.base.entity.Log;
import com.hoolai.baobao.rbac.modules.base.mapper.LogMapper;
import com.hoolai.baobao.rbac.modules.base.service.ILogService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@DS("jdbc-admin")
public class ILogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

	@Override
	public IPage<Log> findByConfition(Integer type, String key, SearchVo searchVo, Page page) {
		QueryWrapper<Log> queryWrapper = new QueryWrapper();
		//类型
		if (type != null) {
			queryWrapper.eq("log_type", type);
		}
		//模糊搜素
		if (StrUtil.isNotBlank(key)) {
			queryWrapper.and(wrapper -> wrapper
							.like("name", key).or()
							.like("username", key).or()
							.like("request_url", key).or()
							.like("request_type", key).or()
							.like("request_param", key).or()
							.like("ip_info", key).or()
							.like("ip", key));
		}

		//创建时间
		if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
			Date start = DateUtil.parse(searchVo.getStartDate());
			Date end = DateUtil.parse(searchVo.getEndDate());
			queryWrapper.ge("create_time", start).le("create_time", end);

		}
		return this.page(page, queryWrapper);
	}
}
