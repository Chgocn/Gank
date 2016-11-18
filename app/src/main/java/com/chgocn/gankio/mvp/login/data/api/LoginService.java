package com.chgocn.gankio.mvp.login.data.api;

import com.chgocn.gankio.mvp.main.data.api.BaseService;
import com.chgocn.gankio.mvp.main.domain.ResponseResult;
import com.chgocn.gankio.mvp.login.domain.AuthAccount;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chgocn.
 */
public class LoginService extends BaseService {

    private Service mService;

    @Inject
    public LoginService() {
        this.mService = getAdapter().create(Service.class);
    }

    // TODO: Create methods to connect with API

    public Observable<AuthAccount> userLogin(String phone, String pwd) {
        JsonObject json = new JsonObject();
        json.addProperty("service","user.login");
        //json.addProperty("phone","15000000002");
        json.addProperty("phone",phone);
        //json.addProperty("pwd", Tools.getInstance().getMD5("123456"));
        json.addProperty("pwd", pwd);
        json.addProperty("longitude","116.29096985");
        json.addProperty("latitude","39.8208811");
        json.addProperty("brand","HuaWei-P8");
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json.toString());
        return mService.userLogin(body).map(new ResponseResultFunc<>());
    }

    private interface Service {
        // TODO: Complete with calls

        @POST("V0.4/")
        Observable<ResponseResult<AuthAccount>> userLogin(@Body RequestBody body);
    }

}