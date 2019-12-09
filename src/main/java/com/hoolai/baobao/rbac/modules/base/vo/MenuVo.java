package com.hoolai.baobao.rbac.modules.base.vo;

import com.hoolai.h5.util.annotation.JavaBean;

import java.util.List;

@JavaBean
public class MenuVo {

	private String id;

	private String parentId;

	private String name;

	private Boolean showAlways;

	private Integer type;

	private String title;

	private String path;

	private String component;

	private String icon;

	private String url;

	private String buttonType;

	private List<MenuVo> children;

	private List<String> permTypes;

	public String getId() {
		return id;
	}

	public String getParentId() {
		return parentId;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setPermTypes(List<String> permTypes) {
		this.permTypes = permTypes;
	}

	public void setChildren(List<MenuVo> children) {
		this.children = children;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

	public List<MenuVo> getChildren() {
		return children;
	}

	public List<String> getPermTypes() {
		return permTypes;
	}

}
