package com.boot.user.controller;

import com.boot.common.base.BaseController;
import com.boot.common.aop.LoggerAnnotation;
import com.boot.user.bean.UserBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/index")
    @LoggerAnnotation(description="PubCommController.index")
    public String index(Model model) {
        UserBean pubUserBean = new UserBean();
        model.addAttribute("user", pubUserBean);
        return "/index";
    }

    @RequestMapping("*")
    public String page404() {
        return "/404";
    }

    @RequestMapping("left")
    public String left() {
        return "/left";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "/welcome";
    }
}
