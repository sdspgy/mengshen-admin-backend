package com.hoolai.baobao.rbac.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hoolai.baobao.rbac.common.constant.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	private String createBy;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	private String updateBy;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value = "update_Time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	private Integer delFlag = CommonConstant.STATUS_NORMAL;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
