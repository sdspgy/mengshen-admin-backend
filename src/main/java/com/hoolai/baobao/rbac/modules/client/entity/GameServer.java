package com.hoolai.baobao.rbac.modules.client.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author ：房家辉
 * @description：TODO
 * @date ：2019/12/11 0011 11:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GameServer对象", description = "")
public class GameServer implements Serializable {

	private static final long serialVersionUID = 1L;
	@TableId
	private int id;

	@ApiModelProperty(value = "游戏id")
	private Integer gameid;

	@ApiModelProperty(value = "服务器id")
	private String serverid;

	@ApiModelProperty(value = "服务器名称")
	private String serverName;
}
