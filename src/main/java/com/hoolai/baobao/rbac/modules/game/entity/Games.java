package com.hoolai.baobao.rbac.modules.game.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ：房家辉
 * @date ：Created in 2020/1/11 0011 10:42
 * @description：Games
 * @modified By：
 * @version: 1.0$
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Games对象", description = "")
public class Games implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "游戏id")
    private Integer id;

    @ApiModelProperty(value = "游戏名")
    private String name;

    @ApiModelProperty(value = "type")
    private String type;

    @ApiModelProperty(value = "上线状态")
    private String state;

    @ApiModelProperty(value = "url")
    private String url;
}
