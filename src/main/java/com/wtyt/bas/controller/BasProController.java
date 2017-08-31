package com.wtyt.bas.controller;

import com.wtyt.bas.bean.BasProBean;
import com.wtyt.bas.service.BasProService;
import com.wtyt.pub.aop.LoggerAnnotation;
import com.wtyt.pub.bean.AjaxBean;
import com.wtyt.util.base.BaseController;
import com.wtyt.util.common.SbConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("bas")
public class BasProController extends BaseController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BasProService basProService;

    @Resource(name="masterRedis")
    private RedisTemplate redisTemplate;

    /**
     * 查询产品列表
     *
     * @return
     */
    @RequestMapping("/pro")
    @LoggerAnnotation(description="querypro")
    public String querypro(ModelMap modelMap, BasProBean bean) {
        try {
            //添加一个 key
            ValueOperations<String, Object> value = redisTemplate.opsForValue();
            value.set("lp", "hello word");
            //获取 这个 key 的值
            System.out.println(value.get("lp"));
            //添加 一个 hash集合
            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name", "lp");
            map.put("age", "26");
            hash.putAll("lpMap", map);
            //获取 map
            System.out.println(hash.entries("lpMap"));
            //添加 一个 list 列表
            ListOperations<String, Object> list = redisTemplate.opsForList();
            list.rightPush("lpList", "lp");
            list.rightPush("lpList", "26");
            //输出 list
            System.out.println(list.range("lpList", 0, 1));
            //添加 一个 set 集合
            SetOperations<String, Object> set = redisTemplate.opsForSet();
            set.add("lpSet", "lp");
            set.add("lpSet", "26");
            set.add("lpSet", "178cm");
            //输出 set 集合
            System.out.println(set.members("lpSet"));
            //添加有序的 set 集合
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            zset.add("lpZset", "lp", 0);
            zset.add("lpZset", "26", 1);
            zset.add("lpZset", "178cm", 2);
            //输出有序 set 集合
            System.out.println(zset.rangeByScore("lpZset", 0, 2));
            basProService.queryBasPro(bean);
            modelMap.addAttribute("proBean", bean);
        } catch (Exception e) {
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
    public String goinspro(ModelMap modelMap) {
        log.info("进入goinspro");
        log.info("离开goinspro");
        return "/bas/bas_pro_add";
    }

    /**
     * 新增产品
     *
     * @return
     */
    @RequestMapping(value = "/inspro", method = RequestMethod.POST)
    @ResponseBody
    public AjaxBean inspro(BasProBean bean) throws Exception {
        log.info("进入inspro");
        basProService.insBasPro(bean);
        AjaxBean ajaxBean = new AjaxBean();
        log.info("离开inspro");
        return ajaxBean;
    }

    /**
     * 新增产品-用异常来测试事物
     *
     * @return
     */
    @RequestMapping(value = "/insproerr", method = RequestMethod.POST)
    @ResponseBody
    public AjaxBean insproerr(BasProBean bean) throws Exception {
        log.info("进入inspro");
        basProService.insBasProErr(bean);
        AjaxBean ajaxBean = new AjaxBean();
        log.info("离开inspro");
        return ajaxBean;
    }

    /**
     * 删除产品
     *
     * @return
     */
    @RequestMapping(value = "/delpro/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxBean delBasPro(@PathVariable String id) throws Exception {
        log.info("进入delBasPro");
        BasProBean bean = new BasProBean();
        bean.setId(Long.valueOf(id));
        basProService.delBasPro(bean);
        AjaxBean ajaxBean = new AjaxBean();
        log.info("离开delBasPro");
        return ajaxBean;
    }

}
