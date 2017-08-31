package com.wtyt.bas.service;

import com.wtyt.bas.bean.BasProBean;

public interface BasProService {

	void queryBasPro(BasProBean bean) throws Exception;

	void insBasPro(BasProBean bean) throws Exception;
	
	void insBasProErr(BasProBean bean) throws Exception;

	void delBasPro(BasProBean bean) throws Exception;
}
