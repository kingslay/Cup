package com.lhl.usersystem.service;

import java.util.Map;

public interface UserService {

	public Map getByUsername(String username);
	public Map get(String id);
	public void register(String username, String password);
	public Map checkLogin(String username, String password);
	public void save(String id, String name, String sex, String birthday);
}
