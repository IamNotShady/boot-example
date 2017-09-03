package com.wtyt.bas.controller;

import com.wtyt.bas.bean.BasProBean;
import com.wtyt.bas.service.BasProService;
import com.wtyt.pub.aop.LoggerAnnotation;
import com.wtyt.pub.bean.AjaxBean;
import com.wtyt.util.base.BaseController;
import com.wtyt.util.common.RedisUtils;
import com.wtyt.util.common.SbConstants;
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
public class BasProController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BasProService basProService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查询产品列表
     *
     * @return
     */
    @RequestMapping("/pro")
    @LoggerAnnotation(description="BasProController.querypro")
    public String querypro(ModelMap modelMap, BasProBean bean) {
        try {
            redisUtils.set("bean",bean);
            basProService.queryBasPro(bean);
            modelMap.addAttribute("proBean", bean);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            modelMap.addAttribute("info", SbConstants.SYS_EXCEPTION);
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
    @RequestMapping(value = "/inspro", method = RequestMethod.POST)
    @LoggerAnnotation(description="BasProController.inspro")
    @ResponseBody
    public AjaxBean inspro(BasProBean bean) throws Exception {
        basProService.insBasPro(bean);
        AjaxBean ajaxBean = new AjaxBean();
        return ajaxBean;
    }

    /**
     * 新增产品-用异常来测试事物
     *
     * @return
     */
    @RequestMapping(value = "/insproerr", method = RequestMethod.POST)
    @LoggerAnnotation(description="BasProController.insproerr")
    @ResponseBody
    public AjaxBean insproerr(BasProBean bean) throws Exception {
        basProService.insBasProErr(bean);
        AjaxBean ajaxBean = new AjaxBean();
        return ajaxBean;
    }

    /**
     * 删除产品
     *
     * @return
     */
    @RequestMapping(value = "/delpro/{id}", method = RequestMethod.GET)
    @LoggerAnnotation(description="BasProController.delBasPro")
    @ResponseBody
    public AjaxBean delBasPro(@PathVariable String id) throws Exception {
        BasProBean bean = new BasProBean();
        bean.setId(Long.valueOf(id));
        basProService.delBasPro(bean);
        AjaxBean ajaxBean = new AjaxBean();
        return ajaxBean;
    }

}
