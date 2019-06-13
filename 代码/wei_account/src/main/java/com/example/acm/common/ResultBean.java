package com.example.acm.common;

/**
 * Created by ggg on 2018/11/25.
 */

public class ResultBean {
    private int code;
    private String result;
    private String msg;
    private Object resultBean;

    public ResultBean() {
    }

    public ResultBean(ResultCode code) {
        this.code = code.getCode().intValue();
        this.result = code.toMessage();
        this.msg = code.getMsg();
    }

    public ResultBean(ResultCode code, String msg) {
        this.code = code.getCode().intValue();
        this.result = code.toMessage();
        this.msg = msg;
    }

    public ResultBean(ResultCode code, Object resultBean) {
        this.code = code.getCode().intValue();
        this.result = code.toMessage();
        this.msg = code.getMsg();
        this.resultBean = resultBean;
    }

    public ResultBean(ResultCode code, String msg, Object resultBean) {
        this.code = code.getCode().intValue();
        this.result = code.toMessage();
        this.msg = msg;
        this.resultBean = resultBean;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResultBean() {
        return this.resultBean;
    }

    public void setResultBean(Object resultBean) {
        this.resultBean = resultBean;
    }
}