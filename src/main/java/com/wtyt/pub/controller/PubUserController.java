package com.wtyt.pub.controller;

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
    public String login(Model model,PubUserBean pubUserBean) {
        log.info("进入login");
        try {
            PubUserBean user = pubUserService.getUserByName(pubUserBean);
            session.setAttribute(SbConstants.USER_KEY, user);
        } catch (BaseException e) {
            model.addAttribute("user", pubUserBean);
            model.addAttribute("info", e.getMessage());
            return "login";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            model.addAttribute("info", SbConstants.SYS_EXCEPTION);
            return EXCEPTION_PAGE;
        } finally {
            log.info("离开login");
        }
        return "/main";
    }

    @RequestMapping("logout")
    public String logout(Model model) {
        log.info("进入logout");
        model.addAttribute("user", new PubUserBean());
        session.removeAttribute(SbConstants.USER_KEY);
        log.info("离开logout");
        return "/login";
    }
}
