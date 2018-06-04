package com.github.boot.pro.mapper;

import com.github.boot.common.base.BaseMapper;
import com.github.boot.pro.bean.ProBean;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProMapper extends BaseMapper<ProBean> {

    @Cacheable(value = "basPro", key = "'getBasProList_'+#p0.getPro_name()+#p0.getPro_code()", unless = "#result == null")
    List<ProBean> getBasProList(ProBean bean);

}
