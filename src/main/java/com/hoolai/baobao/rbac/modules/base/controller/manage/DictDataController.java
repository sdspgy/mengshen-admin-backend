package com.hoolai.baobao.rbac.modules.base.controller.manage;

import com.hoolai.baobao.rbac.common.annotation.SystemLog;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.modules.base.entity.Dict;
import com.hoolai.baobao.rbac.modules.base.entity.DictData;
import com.hoolai.baobao.rbac.modules.base.service.IDictDataService;
import com.hoolai.baobao.rbac.modules.base.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baobao-admin/dictData")
public class DictDataController {

	@Autowired
	private IDictService iDictService;

	@Autowired
	private IDictDataService iDictDataService;

	@RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
	public Object getByCondition(@ModelAttribute DictData dictData, @ModelAttribute PageVo pageVo) {
		return iDictDataService.findByCondition(dictData, PageUtil.initMpPage(pageVo));
	}

	@RequestMapping(value = "/getByType/{type}", method = RequestMethod.GET)
	public Object getByType(@PathVariable String type) {

		Dict dict = iDictService.findByType(type);
		if (dict == null) {
			throw new RbacException(500, "字典类型Type不存在", false);
		}
		return iDictDataService.findByDictId(dict.getId());
	}

	@SystemLog(description = "添加字典数据")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@ModelAttribute DictData dictData) {

		Dict dict = iDictService.getById(dictData.getDictId());
		if (dict == null) {
			throw new RbacException(500, "字典类型id不存在", false);
		}
		iDictDataService.save(dictData);
		throw new RbacException("添加成功", true);
	}

	@SystemLog(description = "修改字典数据")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object edit(@ModelAttribute DictData dictData) {

		iDictDataService.updateById(dictData);
		throw new RbacException("编辑成功", true);
	}

	@SystemLog(description = "删除字典数据")
	@RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
	public Object delByIds(@PathVariable String[] ids) {

		for (String id : ids) {
			iDictDataService.removeById(id);
		}
		throw new RbacException("批量通过id删除数据成功", true);
	}
}
