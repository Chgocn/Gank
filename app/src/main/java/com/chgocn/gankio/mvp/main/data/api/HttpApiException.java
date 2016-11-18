package com.chgocn.gankio.mvp.main.data.api;

/**
 * Created by chgocn(chgocn@gmail.com).
 */
public class HttpApiException extends RuntimeException {
    private int ret;
    private String msg;

    public HttpApiException() {
        super();
    }

    public HttpApiException(String message) {
        super(message);
        msg = message;
    }

    public HttpApiException(int ret, String msg) {
        super(msg);
        this.ret = ret;
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public String getMsg() {
        return msg;
    }
}