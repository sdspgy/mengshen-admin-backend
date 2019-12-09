package com.hoolai.baobao.rbac.modules.base.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hoolai.baobao.rbac.common.annotation.SystemLog;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.common.vo.SearchVo;
import com.hoolai.baobao.rbac.modules.base.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baobao-admin/log")
public class LogController {

	@Autowired
	private ILogService iLogService;

	@RequestMapping(value = "/getAllByPage", method = RequestMethod.GET)
	public Object getAllByPage(@RequestParam(required = false) Integer type,
					@RequestParam String key,
					@ModelAttribute SearchVo searchVo,
					@ModelAttribute PageVo pageVo) {
		return iLogService.findByConfition(type, key, searchVo, PageUtil.initMpPage(pageVo));
	}

	@SystemLog(description = "批量删除")
	@RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
	public Object delByIds(@PathVariable String[] ids) {
		for (String id : ids) {
			iLogService.removeById(id);
		}
		throw new RbacException("删除成功", true);
	}

	@SystemLog(description = "全部删除")
	@RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
	public Object delAll() {
		iLogService.remove(new QueryWrapper<>());
		throw new RbacException("删除成功", true);
	}
}
