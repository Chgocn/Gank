package com.chgocn.gankio.mvp.network.bean.response;


import java.io.Serializable;

/**
 * Created by chgocn(chgocn@gmail.com).
 */
public class ResponseResult<T> implements Serializable {
    private String code;
    private T info;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code='" + code + '\'' +
                ", info=" + info +
                ", msg='" + msg + '\'' +
                '}';
    }
}
