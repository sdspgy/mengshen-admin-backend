package com.hoolai.baobao.rbac.modules.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hoolai.baobao.rbac.common.annotation.SystemLog;
import com.hoolai.baobao.rbac.common.enums.LogType;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.modules.base.entity.ServerInfo;
import com.hoolai.baobao.rbac.modules.base.service.IServerInfoService;
import com.hoolai.baobao.rbac.modules.base.vo.ServerInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/baobao-admin/server")
public class ServerController {

	@Autowired
	private IServerInfoService iServerInfoService;

	@GetMapping(value = "/getServerByPage")
	public Object getServerByPage(@ModelAttribute PageVo pageVo) {
		IPage<ServerInfo> page = iServerInfoService.page(PageUtil.initMpPage(pageVo));
		return page;
	}

	@GetMapping(value = "/getListByEnable")
	public Object getList() {
		return iServerInfoService.getListByEnable().stream().map(info -> new ServerInfoVO(info.getAddress(), info.getName(), info.getPort())).collect(Collectors.toList());
	}

	@SystemLog(description = "禁用服务器", type = LogType.OPERATION)
	@PostMapping("/disable")
	public Object disableServer(@RequestParam String id) {
		ServerInfo info = iServerInfoService.getById(id);
		if (Objects.isNull(info)) {
			throw new RbacException("没有该服务器", false);
		}
		info.disable();
		iServerInfoService.updateById(info);
		throw new RbacException("禁用成功", true);
	}

	@SystemLog(description = "启用服务器", type = LogType.OPERATION)
	@PostMapping("/enable")
	public Object enableServer(@RequestParam String id) {
		ServerInfo info = iServerInfoService.getById(id);
		if (Objects.isNull(info)) {
			throw new RbacException("没有该服务器", false);
		}
		info.enable();
		iServerInfoService.updateById(info);
		throw new RbacException("启用成功", true);
	}

	@SystemLog(description = "添加服务器", type = LogType.OPERATION)
	@PostMapping("/add")
	public Object addServer(@ModelAttribute ServerInfo serverInfo) {
		checkServerForm(serverInfo);
		iServerInfoService.save(serverInfo);
		throw new RbacException("添加成功", true);
	}

	@SystemLog(description = "更新服务器", type = LogType.OPERATION)
	@PostMapping("/update")
	public Object updateServer(@ModelAttribute ServerInfo serverInfo) {
		checkServerForm(serverInfo);
		iServerInfoService.updateById(serverInfo);
		throw new RbacException("更新成功", true);
	}

	private void checkServerForm(ServerInfo serverInfo) {
		if (StrUtil.isBlank(serverInfo.getName())) {
			throw new RbacException("请输入服务器名称", false);
		}
		if (StrUtil.isBlank(serverInfo.getAddress())) {
			throw new RbacException("请输入服务器地址", false);
		}
	}

}
