package controller;

import model.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class ShowPersonController {
    @RequestMapping(value="/show",method= RequestMethod.GET)
    public UserInfo showUser() {
        UserInfo userInfo = new UserInfo();
        return userInfo;
    }
}