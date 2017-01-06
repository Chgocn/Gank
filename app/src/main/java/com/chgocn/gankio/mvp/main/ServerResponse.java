package com.chgocn.gankio.mvp.main;

/**
 * Created by chgocn.
 */
public class ServerResponse<T> {

    private T data;

    public ServerResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}