package com.lhl.usersystem.service;

import java.util.Map;

public interface UserService {

	public Map getByUsername(String username);
    public Map getByPhone(String phone);
	public Map get(String id);
	public void register(String username, String password);
    public void register(String phone);
    public Map checkLogin(String username, String password);
	public void save(UserInfo userInfo);
    public void updateAvatar(String accountid, String avatar);
}

