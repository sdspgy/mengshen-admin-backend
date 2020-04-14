package com.hoolai.baobao.rbac.modules.game.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhiqiu
 * @since 2020-04-14
 */
@TableName("t_user_game")
@Data
public class UserGame {

	private String username;
	private int gameid;
	private String gamename;
	private float currencyrate;
	private int parentid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public float getCurrencyrate() {
		return currencyrate;
	}

	public void setCurrencyrate(float currencyrate) {
		this.currencyrate = currencyrate;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
}
