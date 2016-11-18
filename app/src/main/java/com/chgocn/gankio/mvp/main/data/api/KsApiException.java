package com.chgocn.gankio.mvp.main.data.api;

/**
 * Created by chgocn(chgocn@gmail.com).
 */
public class KsApiException extends RuntimeException {
    private String code;  //异常对应的返回码
    private String msg;  //异常对应的描述信息

    public KsApiException() {
        super();
    }

    public KsApiException(String message) {
        super(message);
        msg = message;
    }

    public KsApiException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
