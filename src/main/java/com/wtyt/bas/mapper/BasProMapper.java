package com.wtyt.bas.mapper;

import com.wtyt.bas.bean.BasProBean;
import com.wtyt.util.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasProMapper extends BaseMapper<BasProBean> {

    List<BasProBean> getBasProList(BasProBean bean);

}
