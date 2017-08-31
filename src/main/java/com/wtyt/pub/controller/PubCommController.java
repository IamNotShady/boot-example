package com.wtyt.pub.controller;

import com.wtyt.pub.aop.LoggerAnnotation;
import com.wtyt.pub.bean.PubUserBean;
import com.wtyt.util.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PubCommController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    @LoggerAnnotation(description="PubCommController.index")
    public String index(Model model) {
        PubUserBean pubUserBean = new PubUserBean();
        model.addAttribute("user", pubUserBean);
        return "/login";
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
