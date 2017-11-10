package com.boot.common.base;

import java.sql.SQLException;

import com.boot.pub.bean.AjaxBean;
import com.boot.util.common.SbConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理 处理控制器抛出的异常
 * Created by zhouxiaoxiao on 17/4/19.
 */
@ControllerAdvice
public class ExceptionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BaseException.class)
    public AjaxBean handleValidationException(BaseException baseException) {
        log.error(baseException.getMessage());
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setReCode(SbConstants.SYS_FAIL_FLAG);
        ajaxBean.setReInfo(baseException.getMessage());
        return ajaxBean;
    }

    @ExceptionHandler(SQLException.class)
    public AjaxBean handleSQLException(SQLException sqlException) {
        log.error(sqlException.getMessage());
        sqlException.printStackTrace();
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setReCode(SbConstants.SYS_FAIL_FLAG);
        ajaxBean.setReInfo(SbConstants.SYS_EXCEPTION);
        return ajaxBean;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public AjaxBean handleException(Exception exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setReCode(SbConstants.SYS_FAIL_FLAG);
        ajaxBean.setReInfo(SbConstants.SYS_EXCEPTION);
        return ajaxBean;
    }
}
