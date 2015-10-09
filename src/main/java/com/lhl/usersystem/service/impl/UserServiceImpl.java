package com.lhl.usersystem.service.impl;

import java.util.Map;

import javax.annotation.Resource;

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
		String sql ="select * from user_info where id =?";
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
	
	public void save(String id, String name, String sex, String birthday) {
		String sql = "update user_info set name=?,sex=?,birthday=? where id=?";
		this.getJdbcTemplate().update(sql, name, sex, birthday, id);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
