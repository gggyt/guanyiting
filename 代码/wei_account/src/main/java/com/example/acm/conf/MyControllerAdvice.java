package com.example.acm.conf;

import com.example.acm.common.ResultBean;
import com.example.acm.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ggg on 2019/3/24.
 */
@ControllerAdvice
public class MyControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultBean errorHandler(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        LOG.error(request.getRequestURI()+"--"+ex.getMessage());
        return new ResultBean(ResultCode.SYSTEM_FAILED);
    }

}
