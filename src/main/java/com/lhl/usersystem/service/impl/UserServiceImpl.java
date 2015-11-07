package com.lhl.usersystem.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.lhl.usersystem.service.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lhl.usersystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	public Map getByUsername(String username) {
		String sql ="select * from user_info where username =?";
		try {
			return this.getJdbcTemplate().queryForMap(sql, username);
		} catch (Exception e) {
			return null;
		}
	}
	public Map get(String id) {
		String sql ="select * from user_info where accountid =?";
		return this.getJdbcTemplate().queryForMap(sql, id);
	}

	public Map checkLogin(String username, String password) {
		String sql = "select * from user_info where username=? and password=?";
		try {
			return this.getJdbcTemplate().queryForMap(sql, username, password);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void register(String username, String password) {
		String sql = "insert into user_info(username, password) values(?,?)";
		this.getJdbcTemplate().update(sql, username, password);
	}
	
	public void save(UserInfo userInfo) {
		String sql = "update user_info set nickname=?,avatar=?,birthday=?,city=?,height=? where accountid=?";
		this.getJdbcTemplate().update(sql, userInfo.nickname, userInfo.avatar, userInfo.birthday,userInfo.city,userInfo.height, userInfo.accountid);
	}

    @Override
    public void updateAvatar(String accountid, String avatar) {
        String sql = "update user_info set avatar=? where accountid=?";
        this.getJdbcTemplate().update(sql, avatar,accountid);
    }

    public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
