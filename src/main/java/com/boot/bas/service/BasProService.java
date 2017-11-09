package com.boot.bas.service;

import com.boot.bas.bean.BasProBean;

public interface BasProService {

	void queryBasPro(BasProBean bean) throws Exception;

	void insBasPro(BasProBean bean) throws Exception;
	
	void insBasProErr(BasProBean bean) throws Exception;

	void delBasPro(BasProBean bean) throws Exception;
}
