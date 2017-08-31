package com.wtyt.bas.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wtyt.bas.bean.BasProBean;
import com.wtyt.bas.mapper.BasProMapper;
import com.wtyt.bas.service.BasProService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BasProServiceImpl implements BasProService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BasProMapper basProMapper;

    @Cacheable(value="user-key")
    public void queryBasPro(BasProBean bean) throws Exception {
        if (bean.getPage() != null && bean.getRows() != null) {
            PageHelper.startPage(bean.getPage(), bean.getRows());
        }
        List<BasProBean> list = basProMapper.getBasProList(bean);
        PageInfo<BasProBean> pageInfo = new PageInfo<BasProBean>(list);
        bean.setProList(list);
    }

    public void insBasPro(BasProBean bean) throws Exception {
        basProMapper.insertSelective(bean);
    }

    public void insBasProErr(BasProBean bean) throws Exception {
        basProMapper.insert(bean);
    }

    public void delBasPro(BasProBean bean) throws Exception {
        basProMapper.delete(bean);
    }
}
