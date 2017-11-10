package com.boot.pro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.boot.pro.bean.ProBean;
import com.boot.pro.mapper.ProMapper;
import com.boot.pro.service.ProService;
import com.boot.common.aop.LoggerAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProServiceImpl implements ProService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProMapper basProMapper;

    @LoggerAnnotation(description="BasProServiceImpl.queryBasPro")
    public void queryBasPro(ProBean bean) throws Exception {
        if (bean.getPage() != null && bean.getRows() != null) {
            PageHelper.startPage(bean.getPage(), bean.getRows());
        }
        List<ProBean> list = basProMapper.getBasProList(bean);
        PageInfo<ProBean> pageInfo = new PageInfo<ProBean>(list);
        bean.setProList(list);
    }

    public void insBasPro(ProBean bean) throws Exception {
        basProMapper.insertSelective(bean);
    }

    public void insBasProErr(ProBean bean) throws Exception {
        basProMapper.insert(bean);
    }

    @CacheEvict(value = "basPro",allEntries=true)
    public void delBasPro(ProBean bean) throws Exception {
        basProMapper.delete(bean);
    }
}
