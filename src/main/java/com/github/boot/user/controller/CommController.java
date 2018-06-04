package com.github.boot.user.controller;

import com.github.boot.common.base.BaseController;
import com.github.boot.common.aop.LoggerAnnotation;
import com.github.boot.user.bean.UserBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @LoggerAnnotation(description = "PubCommController.index")
    public String index(Model model) {
        model.addAttribute("user", new UserBean());
        return "redirect:/login";
    }

    @RequestMapping(value = "*", method = RequestMethod.GET)
    public String page404() {
        return "/404";
    }

    @RequestMapping(value = "/left", method = RequestMethod.GET)
    public String left() {
        return "/left";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "/welcome";
    }
}
