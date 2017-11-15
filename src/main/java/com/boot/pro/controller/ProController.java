package com.boot.pro.controller;

import com.boot.pro.bean.ProBean;
import com.boot.pro.service.ProService;
import com.boot.common.base.BaseController;
import com.boot.common.aop.LoggerAnnotation;
import com.boot.user.bean.AjaxBean;
import com.boot.util.RedisUtils;
import com.boot.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("bas")
public class ProController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProService basProService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查询产品列表
     *
     * @return
     */
    @RequestMapping("/pro")
    @LoggerAnnotation(description="BasProController.querypro")
    public String querypro(ModelMap modelMap, ProBean bean) {
        try {
            redisUtils.set("bean",bean);
            basProService.queryBasPro(bean);
            modelMap.addAttribute("proBean", bean);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            modelMap.addAttribute("info", Constants.SYS_FAIL_MSG);
            return EXCEPTION_PAGE;
        }
        return "/bas/bas_pro";
    }

    /**
     * 进入新增产品页面
     *
     * @return
     */
    @RequestMapping(value = "/goinspro", method = RequestMethod.GET)
    @LoggerAnnotation(description="BasProController.goinspro")
    public String goinspro(ModelMap modelMap) {
        return "/bas/bas_pro_add";
    }

    /**
     * 新增产品
     *
     * @return
     */
    @RequestMapping(value = "/inspro", method = RequestMethod.PUT)
    @LoggerAnnotation(description="BasProController.inspro")
    @ResponseBody
    public AjaxBean inspro(ProBean bean) throws Exception {
        basProService.insBasPro(bean);
        AjaxBean ajaxBean = new AjaxBean();
        return ajaxBean;
    }

    /**
     * 新增产品-用异常来测试事物
     *
     * @return
     */
    @RequestMapping(value = "/insproerr", method = RequestMethod.PUT)
    @LoggerAnnotation(description="BasProController.insproerr")
    @ResponseBody
    public AjaxBean insproerr(ProBean bean) throws Exception {
        basProService.insBasProErr(bean);
        AjaxBean ajaxBean = new AjaxBean();
        return ajaxBean;
    }

    /**
     * 删除产品
     *
     * @return
     */
    @RequestMapping(value = "/delpro/{id}", method = RequestMethod.DELETE)
    @LoggerAnnotation(description="BasProController.delBasPro")
    @ResponseBody
    public AjaxBean delBasPro(@PathVariable String id) throws Exception {
        ProBean bean = new ProBean();
        bean.setId(Long.valueOf(id));
        basProService.delBasPro(bean);
        AjaxBean ajaxBean = new AjaxBean();
        return ajaxBean;
    }

}
