package com.wtyt.pub.controller;

import com.wtyt.pub.aop.LoggerAnnotation;
import com.wtyt.pub.bean.PubUserBean;
import com.wtyt.pub.service.PubUserService;
import com.wtyt.util.base.BaseController;
import com.wtyt.util.base.BaseException;
import com.wtyt.util.common.SbConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PubUserController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PubUserService pubUserService;

    @RequestMapping("login")
    @LoggerAnnotation(description="PubUserController.login")
    public String login(Model model,PubUserBean pubUserBean) {
        try {
            PubUserBean user = pubUserService.getUserByName(pubUserBean);
            session.setAttribute(SbConstants.USER_KEY, user);
        } catch (BaseException e) {
            log.error(e.getMessage(),e);
            model.addAttribute("user", pubUserBean);
            model.addAttribute("info", e.getMessage());
            return "login";
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            model.addAttribute("info", SbConstants.SYS_EXCEPTION);
            return EXCEPTION_PAGE;
        }
        return "/main";
    }

    @RequestMapping("logout")
    @LoggerAnnotation(description="PubUserController.logout")
    public String logout(Model model) {
        model.addAttribute("user", new PubUserBean());
        session.removeAttribute(SbConstants.USER_KEY);
        return "/login";
    }
}
