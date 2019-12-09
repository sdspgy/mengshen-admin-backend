package com.hoolai.baobao.rbac.modules.creative.entity;

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
 * @since 2019-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GameCreative对象", description = "")
public class GameCreative implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private int id;

	@ApiModelProperty(value = "游戏id")
	private Integer gameid;

	@ApiModelProperty(value = "渠道id")
	private String creativeid;

	@ApiModelProperty(value = "渠道名称")
	private String creativeName;

}
