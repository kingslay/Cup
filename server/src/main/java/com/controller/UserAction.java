package com.controller;

import com.model.UserInfo;
import com.service.FileUtils;
import com.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private UserRepository userService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public Object register(@RequestBody UserInfo userInfo, HttpServletResponse response) {
        UserInfo result = userService.findByUsername(userInfo.username);
        if (result != null) {
            response.setStatus(400);
            Map<String, String> map = new HashMap<String, String>();
            map.put("message", "用户名已存在");
            return map;
        }

        userService.save(userInfo);
        return userService.findByUsername(userInfo.username);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        UserInfo result = userService.findByUsernameAndPassword(username, password);
        if (result != null) {
            String token = DigestUtils.md5DigestAsHex(request.getSession().getId().getBytes());
            return result;

        } else {
            response.setStatus(400);
            Map<String, String> map = new HashMap<String, String>();
            if (userService.findByUsername(username) == null) {
                map.put("message", "用户名不存在");
            } else {
                map.put("message", "密码错误");
            }
            return map;
        }
    }

    @RequestMapping(value = "/phonelogin", method = RequestMethod.GET)
    public Object phoneLogin(@RequestParam("phone") String phone, HttpServletRequest request, HttpServletResponse response) {
        UserInfo result = userService.findByPhone(phone);
        if (result != null) {
            return result;
        }
        result = new UserInfo();
        result.phone = phone;
        userService.save(result);
        return userService.findByPhone(phone);
    }

    @RequestMapping(value = "/saveme", method = RequestMethod.PUT)
    @ResponseBody
    public Object save(@RequestBody UserInfo userInfo) {
        userService.update(userInfo.avatar, userInfo.birthday, userInfo.city, userInfo.constitution,
                userInfo.height, userInfo.nickname, userInfo.phone, userInfo.scene, userInfo.sex, userInfo.weight, userInfo.accountid);
        return null;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().removeAttribute("OnlineUser");
        req.getSession().removeAttribute("token");
        return "redirect:/pages/index.jsp";
    }

    @RequestMapping(value = "/updateProfile.do", method = RequestMethod.POST)
    @ResponseBody
    public Object updateProfile(@RequestHeader("accountid") Long accountid,
                                @RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                request.getContextPath() + FileUtils.updateProfile(file, accountid + "_" + System.currentTimeMillis() + ".jpg");
        userService.updateAvatar(accountid, url);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", url);
        return map;
    }
}

