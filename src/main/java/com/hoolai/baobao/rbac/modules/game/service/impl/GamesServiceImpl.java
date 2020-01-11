package com.hoolai.baobao.rbac.modules.game.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.game.entity.Games;
import com.hoolai.baobao.rbac.modules.game.mapper.GamesMapper;
import com.hoolai.baobao.rbac.modules.game.service.IGamesService;
import org.springframework.stereotype.Service;

/**
 * @author ：房家辉
 * @date ：Created in 2020/1/11 0011 11:38
 * @description：Games
 * @modified By：
 * @version: 1.0$
 */
@Service
@DS("jdbc-wxAdmin")
public class GamesServiceImpl extends ServiceImpl<GamesMapper, Games> implements IGamesService {
}
