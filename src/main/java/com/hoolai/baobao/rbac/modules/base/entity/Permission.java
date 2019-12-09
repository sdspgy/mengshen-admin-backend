package com.hoolai.baobao.rbac.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hoolai.baobao.rbac.base.BaseEntity;
import com.hoolai.baobao.rbac.common.constant.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * 菜单/权限
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_permission")
public class Permission extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private Boolean showAlways = true;

	private Integer level;

	private Integer type;

	private String title;

	private String path;

	private String component;

	private String icon;

	private String buttonType;

	private String parentId;

	private String description;

	private BigDecimal sortOrder;

	private Integer status = CommonConstant.STATUS_NORMAL;

	private String url;

	@TableField(exist = false)
	private List<Permission> children;

	@TableField(exist = false)
	private List<String> permTypes;

	@TableField(exist = false)
	private Boolean expand = true;

	@TableField(exist = false)
	private Boolean checked = false;

	@TableField(exist = false)
	private Boolean selected = false;

	public Integer getType() {
		return type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}

	public String getTitle() {
		return title;
	}

	public String getPath() {
		return path;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getShowAlways() {
		return showAlways;
	}

	public void setShowAlways(Boolean showAlways) {
		this.showAlways = showAlways;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(BigDecimal sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Permission> getChildren() {
		return children;
	}

	public List<String> getPermTypes() {
		return permTypes;
	}

	public void setPermTypes(List<String> permTypes) {
		this.permTypes = permTypes;
	}

	public Boolean getExpand() {
		return expand;
	}

	public void setExpand(Boolean expand) {
		this.expand = expand;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}