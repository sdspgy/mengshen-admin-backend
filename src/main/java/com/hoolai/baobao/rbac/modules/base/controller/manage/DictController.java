package com.hoolai.baobao.rbac.modules.base.controller.manage;

import com.hoolai.baobao.rbac.common.annotation.SystemLog;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.modules.base.entity.Dict;
import com.hoolai.baobao.rbac.modules.base.service.IDictDataService;
import com.hoolai.baobao.rbac.modules.base.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baobao-admin/dict")
public class DictController {

	@Autowired
	private IDictService iDictService;

	@Autowired
	private IDictDataService iDictDataService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public Object getAll() {
		return iDictService.findAllOrderBySortOrder();
	}

	@SystemLog(description = "添加字典")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@ModelAttribute Dict dict) {

		if (iDictService.findByType(dict.getType()) != null) {
			throw new RbacException(500, "字典类型Type已存在", false);
		}
		iDictService.save(dict);
		throw new RbacException("添加成功", true);
	}

	@SystemLog(description = "修改字典")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object edit(@ModelAttribute Dict dict) {

		Dict old = iDictService.getById(dict.getId());
		// 若type修改判断唯一
		if (!old.getType().equals(dict.getType()) && iDictService.findByType(dict.getType()) != null) {
			throw new RbacException(500, "字典类型Type已存在", false);
		}
		iDictService.updateById(dict);
		throw new RbacException("编辑成功", true);
	}

	@SystemLog(description = "删除字典")
	@RequestMapping(value = "/delByIds/{id}", method = RequestMethod.DELETE)
	public Object delAllByIds(@PathVariable String id) {

		iDictService.removeById(id);
		iDictDataService.deleteByDictId(id);
		throw new RbacException("删除成功", true);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Object searchPermissionList(@RequestParam String key) {
		return iDictService.findByTitleOrTypeLike(key);
	}
}
