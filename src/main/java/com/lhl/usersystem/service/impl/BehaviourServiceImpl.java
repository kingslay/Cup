package com.lhl.usersystem.service.impl;

import com.lhl.usersystem.service.BehaviourInfo;
import com.lhl.usersystem.service.BehaviourService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by king on 15/11/12.
 */
@Service
public class BehaviourServiceImpl implements BehaviourService {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(BehaviourInfo behaviourInfo) {
        String sql = "insert into behaviour_info(accountid, clock,explanation,temperature) values(?,?,?,?)";
        this.getJdbcTemplate().update(sql, behaviourInfo.accountid, behaviourInfo.clock,behaviourInfo.explanation,behaviourInfo.temperature);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
