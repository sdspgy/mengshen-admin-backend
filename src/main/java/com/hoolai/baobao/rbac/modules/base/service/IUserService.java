package com.hoolai.baobao.rbac.modules.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.common.vo.SearchVo;
import com.hoolai.baobao.rbac.modules.base.entity.User;

public interface IUserService extends IService<User> {

	User findByUsername(String username);

	IPage<User> findByCondition(User user, SearchVo searchVo, Page page);

}
