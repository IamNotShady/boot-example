package com.boot.pro.mapper;

import java.util.List;

import com.boot.common.base.BaseMapper;
import com.boot.pro.bean.ProBean;

import org.springframework.stereotype.Repository;

@Repository
public interface ProMapper extends BaseMapper<ProBean> {


    List<ProBean> getBasProList(ProBean bean);

}
