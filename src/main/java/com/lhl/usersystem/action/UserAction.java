package com.lhl.usersystem.action;

import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.lhl.usersystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Resource
	private UserService userService;

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest req) {
		Map result = userService.checkLogin(username, password);
		if (result != null) {
			String token = DigestUtils.md5DigestAsHex(req.getSession().getId().getBytes());
			req.getSession().setAttribute("OnlineUser", result);
			req.getSession().setAttribute("token", token);
			return new ModelAndView("redirect:/pages/welcome.jsp");
		}
		return new ModelAndView("redirect:/pages/index.jsp?login=failed");
	}

	@RequestMapping("/save")
	public String save(@RequestParam("id") String id,
					   @RequestParam("token") String token,
					   @RequestParam("name") String name,
					   @RequestParam("sex") String sex,
					   @RequestParam("birthday") String birthday,
					   HttpServletRequest req) {
		String sesstoken = DigestUtils.md5DigestAsHex(req.getSession().getId().getBytes());
		if (!StringUtils.equals(sesstoken, token)) {
			return "redirect:/pages/welcome.jsp?save=invalidtoken";
		}
		userService.save(id, name, sex, birthday);
		Map result = userService.get(id);
		req.getSession().setAttribute("OnlineUser", result);

		return "redirect:/pages/welcome.jsp?save=success";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		req.getSession().removeAttribute("OnlineUser");
		req.getSession().removeAttribute("token");
		return "redirect:/pages/index.jsp";
	}

	@RequestMapping("/register")
	public String register(@RequestParam("username") String username,
						   @RequestParam("password") String password) {
		Map result = userService.getByUsername(username);
		if (result != null) {
			return "redirect:/pages/index.jsp?register=exists";
		}
		userService.register(username, password);
		return "redirect:/pages/index.jsp?register=success";
	}

	@RequestMapping(value="/jsonfeed",method= RequestMethod.GET)
    @ResponseBody
	public Object getJSON(@RequestParam Map<String, String> valueMap) {
		List<String> list = new ArrayList<String>();
		list.add("wang");
		list.add("wang");
		list.add("wang");
		list.add("wang");
		list.add("wang");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("items", list);
		map.put("status", 1);
        map.put("result",valueMap);
        return map;
	}
    @RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
    public Object addUser(@RequestBody Map<String, String> valueMap,
                          BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            map.put("errorCode", "40001");
            map.put("errorMsg", bindingResult.getFieldError().getDefaultMessage());
        }
        map.put("success", "true");
		return map;
    }
    @RequestMapping("/testError")
    @ResponseBody
    public Object testEroor(@RequestParam Map<String, String> valueMap,HttpServletResponse response) throws IOException {
        List<String> list = new ArrayList<String>();
        list.add("wang");
        list.add("wang");
        list.add("wang");
        list.add("wang");
        list.add("wang");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("items", list);
        map.put("status", 1);
        map.put("result",valueMap);
        response.setStatus(400);
        return map;
    }
}
