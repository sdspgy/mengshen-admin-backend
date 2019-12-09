package com.hoolai.baobao.rbac.modules.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.common.vo.SearchVo;
import com.hoolai.baobao.rbac.modules.base.entity.Log;

public interface ILogService extends IService<Log> {

	IPage<Log> findByConfition(Integer type, String key, SearchVo searchVo, Page page);
}
