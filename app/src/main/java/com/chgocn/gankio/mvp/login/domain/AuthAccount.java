package com.chgocn.gankio.mvp.login.domain;

/**
 * Created by chgocn.
 */

public class AuthAccount {

    private int ksid;
    private String nickname;

    public int getKsid() {
        return ksid;
    }

    public void setKsid(int ksid) {
        this.ksid = ksid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
