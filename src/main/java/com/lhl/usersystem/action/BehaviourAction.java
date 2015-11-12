package com.lhl.usersystem.action;

import com.lhl.usersystem.service.BehaviourInfo;
import com.lhl.usersystem.service.BehaviourService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by king on 15/11/12.
 */

@Controller
@RequestMapping("/behaviour")
public class BehaviourAction {

    @Resource
    private BehaviourService behaviourService;
    @RequestMapping(value="/insert",method= RequestMethod.POST)
    @ResponseBody
    public Object insert(@RequestBody BehaviourInfo behaviourInfo) {
        behaviourService.insert(behaviourInfo);
        return null;
    }
}