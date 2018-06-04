package com.github.boot.pro.service;

import com.github.boot.pro.bean.ProBean;

public interface ProService {

    void queryBasPro(ProBean bean) throws Exception;

    void insBasPro(ProBean bean) throws Exception;

    void insBasProErr(ProBean bean) throws Exception;

    void delBasPro(ProBean bean) throws Exception;
}
