package com.hoolai.baobao.rbac.modules.game.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value = "Game对象", description = "")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private Integer parentId;

	@ApiModelProperty(value = "游戏id")
	private Integer gameid;

	@ApiModelProperty(value = "游戏名称")
	private String name;

	@ApiModelProperty(value = "游戏别名")
	private String alias;

	@ApiModelProperty(value = "游戏logo，服务器图片地址")
	private String logo;

	@ApiModelProperty(value = "月KPI")
	private Double monthKpi;

	@ApiModelProperty(value = "年KPI")
	private Double yearKpi;

	@ApiModelProperty(value = "当前月总收入")
	private Double currMonthKpi;

	@ApiModelProperty(value = "当年总收入")
	private Double currYearKpi;

	@ApiModelProperty(value = "货币类型")
	private String currency;

	@ApiModelProperty(value = "货币汇率（相较RMB)")
	private Float currencyRate;

	@ApiModelProperty(value = "是否开启etl")
	private String stats;

	@ApiModelProperty(value = "世界时区")
	@TableField("timeZone")
	private String timeZone;

	@ApiModelProperty(value = "安装总用户")
	private Integer install;

	@ApiModelProperty(value = "付费总额")
	private Integer payAmount;

	@ApiModelProperty(value = "统计几日留存 0 不开启日志统计")
	private Integer retention;

	@ApiModelProperty(value = "上线时间")
	private Date onlineDate;

	@ApiModelProperty(value = "etl触发时间")
	private Date etlTriggerTime;

	@ApiModelProperty(value = "etl触发id")
	private String etlTriggerId;

	@ApiModelProperty(value = "etl当前状态")
	private String etlStatus;

	@ApiModelProperty(value = "统计优先级")
	private Integer etlOrder;

}
