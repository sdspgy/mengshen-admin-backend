package com.hoolai.baobao.rbac.modules.advert.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zhiqiu
 * @since 2019-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GameAdvert对象", description = "")
public class GameAdvert implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private int id;

	@ApiModelProperty(value = "游戏id")
	private Integer gameid;

	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "广告位id")
	private String advertid;

	@ApiModelProperty(value = "广告位名称")
	private String advertName;

}
