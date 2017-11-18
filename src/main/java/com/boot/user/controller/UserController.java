package com.boot.user.controller;

import com.boot.common.aop.LoggerAnnotation;
import com.boot.common.base.BaseController;
import com.boot.common.base.BaseException;
import com.boot.user.bean.UserBean;
import com.boot.user.service.UserService;
import com.boot.util.Constants;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService pubUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String intoLogin(Model model) {
        model.addAttribute("user", new UserBean());
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerAnnotation(description = "PubUserController.login")
    public String login(Model model, UserBean pubUserBean) {
        try {
            UserBean user = pubUserService.getUserByName(pubUserBean);
            session.setAttribute("user", user);
        } catch (BaseException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("user", pubUserBean);
            model.addAttribute("info", e.getMessage());
            return "redirect:/";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            model.addAttribute("info", Constants.SYS_FAIL_MSG);
            return EXCEPTION_PAGE;
        }
        return "redirect:/main";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @LoggerAnnotation(description = "PubUserController.logout")
    public String logout(Model model) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "/login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        return "/main";
    }
}
