package com.lhl.usersystem.action;

import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhl.usersystem.service.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lhl.usersystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Resource
	private UserService userService;

	@RequestMapping(value="/regist",method= RequestMethod.PUT)
	@ResponseBody
	public Object register(@RequestBody UserInfo userInfo,HttpServletRequest request,HttpServletResponse response) {
		Map result = userService.getByUsername(userInfo.username);
		if (result != null) {
			response.setStatus(400);
			Map<String,String> map = new HashMap<String,String>();
			map.put("message","用户名已存在");
			return map;
		}
		userService.register(userInfo.username, userInfo.password);
		return userService.getByUsername(userInfo.username);
	}

	@RequestMapping(value="/login",method= RequestMethod.GET)
	@ResponseBody
	public Object login(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletRequest request, HttpServletResponse response) {
		Map result = userService.checkLogin(username, password);
		if (result != null) {
			String token = DigestUtils.md5DigestAsHex(request.getSession().getId().getBytes());
			Map map = userService.getByUsername(username);
			map.put("token",token);
			return map;

		}else{
			response.setStatus(400);
			Map<String,String> map = new HashMap<String,String>();
			if ( userService.getByUsername(username) == null) {
				map.put("message","用户名不存在");
			}else{
				map.put("message", "密码错误");
			}
			return map;
		}
	}

	@RequestMapping(value="/saveme",method= RequestMethod.PUT)
    @ResponseBody
    public Object save(@RequestBody UserInfo userInfo) {
        userService.save(userInfo);
        return null;
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		req.getSession().removeAttribute("OnlineUser");
		req.getSession().removeAttribute("token");
		return "redirect:/pages/index.jsp";
	}

	@RequestMapping(value = "/updateProfile.do",method= RequestMethod.POST)
	@ResponseBody
	public Object updateProfile(@RequestHeader("accountid") String accountid,HttpServletRequest request) throws  IOException{
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
			FileUtils.saveFile(request, multipartHttpServletRequest.getFile("file"));
            String url = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+request.getContextPath()+"/" + FileUtils.getFilePath();
            userService.updateAvatar(accountid,url);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("url",url);
            return map;
		}
        return null;

	}
}
